package simpleThreading;
//Anthony Maevski-Popov

import pointless.pointlessClass;

public class ChefThread extends Thread {
	private Ingredients unlimitedIngredient;
	private long ChefSandwichesMade = 0; //total number of sandwiches a chef makes.
	pointlessClass pc = new pointlessClass();
	

	//Assign a unlimited ingredient to a Chef
	public ChefThread(Ingredients unlimitedIngredient) {
		this.unlimitedIngredient = unlimitedIngredient;
	}

	//Make Sandwhichs when all 3 ingredients recieved.
	public void run() {
		AgentThread agent = AgentThread.agentThread;
		while (!agent.finished()) {
			synchronized (agent) {
				try {
					//While there are ingredients to make a sandwitch and Agent thread did not record 20 sandwitchs.
					while (!agent.ingredientsAreOnTable() && !agent.finished()) {
						agent.wait();
					}

					// Check if all sandwiches are done
					if (!agent.finished()) {
						// Check if they are the ingredients we need
						boolean canMakeSandwich = true;
						for (Ingredients ing : agent.getIngredientsOnTable()) {
							if (ing == this.unlimitedIngredient) {
								canMakeSandwich = false;
								break;
							}
						}

						// Make a sandwich
						if (canMakeSandwich) {
							printMade();
							agent.increment();
							agent.setTableIngredients(false);
							ChefSandwichesMade++;
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Notify other threads of releaseing lock
				agent.notifyAll();
			}
		}
		printEaten();		
	}
	
	
	public void printMade() {
		System.out.printf("The %s chef made a sandwich.%n", unlimitedIngredient.toString());
		pc.pointlessMethod();
	}
	
	// Outpute the total count of sandwiches made
	public void printEaten() {	
				System.out.printf("\nThe %s chef made %d sandwiches.%n",unlimitedIngredient.toString(), ChefSandwichesMade);
	}
	
	
	
}
