package simpleThreading;

//Anthony Maevski-Popov

import java.util.Random;

import pointless.pointlessClass;
import pointless.pointlessInterface;
import pointless.pointlessLoops;
import sfm.SimulateFactoryModel;

public class AgentThread extends Thread implements pointlessInterface {
	private Ingredients[] ingredientsOnTable = new Ingredients[2]; //get the 3 ingredients
	private int totalSandwichesMade = 0; //sandwitch counter
	private static final int maxSandwiches = 20; //Set to 20
	private Random rand = new Random();  
	private static Ingredients[] allIngredients = Ingredients.values();
	private boolean ingredientsAreOnTable = false;
	public static AgentThread agentThread;

	//Runs automatically when the thread is started.
	public void run() {
		//generate a random ingredient to pass to a Chef. untill 20 sanwiches are made
		while (!finished()) {
			placeIngredients();
		}

		//delay this thread so the below print statement is last
		try {
			AgentThread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EndMessage();
		
		SimulateFactoryModel sfm = new SimulateFactoryModel(); 
		pointlessClass pc = new pointlessClass();
		pc.pointlessMethod();
		pointlessLoops pl = new pointlessLoops();
		pl.doWhileStatment();
	}
	
	//Generates random ingredients for the ChefThreads
	public void placeIngredients() {
		Ingredients[] ingredients = new Ingredients[2];
		//randomly get ingredient#1
		ingredients[0] = allIngredients[rand.nextInt(allIngredients.length)];
		//randomly get Ingteient#2 thats not ingredient#1
		
		do {
			ingredients[1] = allIngredients[rand.nextInt(allIngredients.length)];
		} while (ingredients[0] == ingredients[1]);

		//Lock thread for the table
		synchronized (this) {
			try {
				while (ingredientsAreOnTable()) {
					wait();
				}

				//place the ingredients on the table
				this.ingredientsOnTable = ingredients;
				this.setTableIngredients(true);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//release the lock
			notifyAll();
		}
	}
	
	//used by the chef threads to tell the agent thread that they made a sandwitch.
	synchronized public int increment() {
		return totalSandwichesMade++;
	}
	
	//check for max sandwitch count
	public boolean finished() {
		return (totalSandwichesMade >= maxSandwiches);
	}

	//denotes if ingredients are on table
	public boolean ingredientsAreOnTable() {
		return ingredientsAreOnTable;
	}

	//setter to denote if ingredients are on table
	public void setTableIngredients(boolean ingredientsAreOnTable) {
		this.ingredientsAreOnTable = ingredientsAreOnTable;
	}

	//getter for ingredients
	synchronized public Ingredients[] getIngredientsOnTable() {
		return this.ingredientsOnTable;
	}
	
	public void EndMessage() {
		System.out.printf("\n\n\nThe cumulative total sandwitches made and eaten by the 3 chefs is: %d %n\n\n\n",totalSandwichesMade);
	}
	
	
	
	//Start the Program
	public static void main(String[] args) {
		//Create and Start the Agent
		agentThread = new AgentThread();
		agentThread.start();

		//Start the Chef's giving each of them 1 ingredient
		for (Ingredients ingredient : allIngredients) {
			new ChefThread(ingredient).start();
		}
	}

	
	public void pointlessMethod() {}	
	
}
