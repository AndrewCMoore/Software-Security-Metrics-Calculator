package ssmc;
  
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
/**
 * 
 * @author AndrewCMoore
 *
 */
public class Method {
	
	private CompilationUnit compilationUnit;
	private int endLine;
	private String identifier;
	private boolean isAccessible;
	private boolean isClassified;
	private boolean isFinalized;
	private boolean isInherited;
	private boolean isWriteClassified;
	private int length;
	private int links;
	private ArrayList<String> modifiers;
	private int numberOfInputs;
	private int numberOfOutputs;
	private int startLine;
	private ArrayList<Statement> statements;
	private int usage;
	private HashMap<String, String>parameters;
	private int numberOfInvocations;
	private boolean accessor;
	private int mutator;
	private boolean isVoid;
	private ArrayList<String> onlyparameterTypes;
	/**
	 * Constructor method
	 * @param identifier String representation of Method's identifier
	 * @param compilationUnit CompilationUnit
	 */
	public Method(String identifier, CompilationUnit compilationUnit) {
		// Set the variables from the parameters
		this.identifier = identifier;
		this.compilationUnit = compilationUnit;
		
		// Set the variables to base values
		this.length = 0;
		this.numberOfOutputs = 0;
		this.numberOfInputs	 = 0;
		this.isClassified = false;
		this.isWriteClassified = false;	
		this.isInherited = false;
		this.isAccessible = false;
		this.usage = 0;
		this.isFinalized = false;
		this.links = 0;
		this.startLine = 0;
		this.endLine = 0;
		this.numberOfInvocations = 0;	
		this.accessor = false;
		this.mutator = 0; 
		this.isVoid = false;
		
		// Initialize ArrayLists
		this.statements = new ArrayList<Statement>();
		this.modifiers = new ArrayList<String>();
		
		this.parameters = new HashMap<String, String>();
		this.onlyparameterTypes = new ArrayList<String>();
		
	}
	public boolean isVoid() {
		return isVoid;
	}
	public void setVoid(boolean isVoid) {
		this.isVoid = isVoid;
	}
	
	public void isAccessor() {
		// If the method name starts with the word 'get' 
		if(getIdentifier().startsWith("get")) {
				// The only statement within the method should be a ReturnStatement
				if(getStatements().get(0).getNode() instanceof ReturnStatement) {
					// At this point we have confirmed it is a getter method
					System.out.println("Accessor: Method |||| Line " + this.identifier + " ||||| " + this.getStartLine());	
					setAccessor();
			}
		}	
	}
	
	
	public void isMutator() {
		// If the method name starts winthe word 'set' 
		if(getIdentifier().startsWith("set") && isVoid) {
			// Then we determine the number of variables it sets by the number of parameters
			setMutator(this.parameters.size());
			System.out.println("Mutator: Method |||| Line " + this.identifier + " ||||| " + this.getStartLine() + " Value: " + this.mutator);	
		}
	}
	public boolean getAccessor() {
		return accessor;
	}
	public int getMutator() {
		return mutator;
	}
	public void setAccessor() {
		this.accessor = true;
    }
    public void setMutator(int i) {
    	this.mutator = i;
    }
	
	public int getNumberOfInvocations() {
		return numberOfInvocations;	
	}
	
	public void addInvocation() {
		this.numberOfInvocations += 1;
	}
	
	public void setParameters(String name, String type) {
		this.parameters.put(name, type);
	}
	
	public HashMap<String, String> getParameters(){
		return parameters;
	}
	/**
	 * Adds a Statmenet object to the ArrayList of Statement objects
	 * within this Method. 
	 * @param statement Statement
	 */
	public void addStatement(Statement statement) {
		// Get the ID of the System's hashcode of the object to be added
		int id = System.identityHashCode(statement.getNode());
		// For each Statement within this Method
		for(Statement s: statements) {
			// If the the ID matches any Statement object's ASTNode within the Method
			if(id == System.identityHashCode(s.getNode())) {
				// It is being revisited and thus does not need to be added
				return ;
			}
		}
		// Did not match any current Statement ASTNode's ID's and is added to the ArrayList
		this.statements.add(statement);
	}

	/**
	 * This method adds a single usage to the Method object. 
	 */
	public void addUsage() {
		this.usage = this.usage + 1;
	}

	/**
	 * This method returns whether the Method is public or not. 
	 * @return boolean
	 */
	public boolean getAccessible(){
		return isAccessible;
	}
	
	/**
	 * This method returns whether the Method is classified or not.
	 * @return boolean
	 */
	public boolean getClassified() {
		return isClassified;		
	}
	
	/**
	 * This method returns the Method's CompilationUnit.
	 * @return CompilationUnit
	 */
	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}
	
	/**
	 * This method returns the Method's ending line.
	 * @return int
	 */
	public int getEndLine() {
		return endLine;
	}
	
	/**
	 * This method returns whether the Method is finalizaed.
	 * @return boolean
	 */
	public boolean getFinalized(){
		return isFinalized;
	}
	
	/**
	 * This method returns the Method's String representation of its SimpleName.
	 * @return String
	 */
	public String getIdentifier() {
		return this.identifier;
	}
	
	/**
	 * This method returns whether the method is inherited or not.
	 * @return boolean
	 */
	public boolean getInherated(){
		return isInherited;
	}
	
	/**
	 * This method returns the number of links between Methods
	 * @return int
	 */
	public int getLinks() {
		return links;
	}

	/**
	 * This method returns the length of the Method
	 * @return int
	 */
	public int getMethodLength() {
		return length;
			
	}
	
	/**
	 * Returns the modifiers of the Method
	 * @return ArrayList of String objects
	 */
	public ArrayList<String> getModifiers(){
		return this.modifiers;	
	}
	
	/**
	 * Returns the number of parameters to the Method
	 * @return int
	 */
	public int getNumberOfInputs() {
		return numberOfInputs;
	}
	
	/**
	 * Returns the number of outputs to the Method (0 or 1) 
	 * @return int
	 */
	public int getNumberOfOutputs() {
		return numberOfOutputs;
	}

	/**
	 * Returns the start line of the Method
	 * @return int
	 */
	public int getStartLine() {
		return startLine;
	}
	
	/**
	 * Returns the Statement objects within the Method
	 * @return ArrayList of Statement objects
	 */
	public ArrayList<Statement> getStatements(){
		return statements;
	}

	/**
	 * Returns the usage of the Method
	 * @return int
	 */
	public int getUsage() {
		return this.usage;
	}
	
	/**
	 * Returns true if the Method is write classified.
	 * @return boolean
	 */
	public boolean getWriteClassified() {
		return isWriteClassified;
	}

	/**
	 * Returns true if the Method has a public modifier.
	 * @param b boolean
	 */
	public void setAccessible(boolean b){
		this.isAccessible = b;
	}
	
	/**
	 * Sets if the Method is classified or not
	 * @param newIsClassified boolean
	 */
	public void setClassified(boolean newIsClassified) {
		this.isClassified = newIsClassified;
	}
	
	/**
	 * Sets the Method's end line 
	 * @param end int
	 */
	public void setEndLine(int end) {
		this.endLine = end;
	}

	/**
	 * Sets if the Method is finalized or not
	 * @param b boolean
	 */
	public void setFinalized(boolean b) {
		this.isFinalized = b;
	}

	/**
	 * Sets if the Method is inherited or not
	 * @param b boolean
	 */
	public void setInherated(boolean b){
		this.isInherited = b;
	}
	
	/**
	 * Sets the number of links to other methods
	 * @param links int
	 */
	public void setLinks(int links) {
		this.links = links;
	}

	/**
	 * Sets the Method's length
	 * @param newLength int
	 */
	public void setMethodLength(int newLength) {
		this.length = newLength;
		
	}
	
	/**
	 * Sets the Method's modifiers
	 * @param modifiers ArrayList of String objects
	 */
	public void setModifiers(ArrayList<String> modifiers) {
		// For each String object in the ArrayList
		for(String s : modifiers) {
			// Add that object to the modifier ArrayList
			this.modifiers.add(s);
		}
	}
	
	/**
	 * Sets the number of parameters of the Method
	 * @param newNumberOfInputs int
	 */
	public void setNumberOfInputs(int newNumberOfInputs) {
		this.numberOfInputs =newNumberOfInputs;	
	}
	
	/**
	 * Sets the number of outputs of the Method
	 * @param newNumberOfOutputs int
	 */
	public void setNumberOfOutputs(int newNumberOfOutputs) {
		this.numberOfOutputs = newNumberOfOutputs;
	}

	/**
	 * Sets the Method's start line
	 * @param start int
	 */
	public void setStartLine(int start) {
		this.startLine = start;
	}

	/**
	 * Sets if the Method is write classified.
	 * @param b
	 */
	public void setWriteClassified(boolean b){
		this.isWriteClassified = b;
	}

    public  void setOnlyparameterTypes(String type) {//anthony
        onlyparameterTypes.add(type);
    }
    
    public ArrayList<String> getOnlyParameterTypes(){//anthony
        return onlyparameterTypes;
    }    
	
	
	/**
	 * Returns the String representation of the Method's name 
	 * @return String
	 */
	public String toString() {
		return this.identifier;
	}
		
	 
	/**
	 * Returns the number of each type of Statement ASTNode within the 
	 * Statement ArrayList of this Method. 
	 * @return HashMap<String, Integer> 
	 */
	public HashMap<String, Integer> getNumOfStatements() {
		// Create a new HashMap object to return 
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		// Create Integer objects to track the number of each type of statement ASTNode
		int numIf = 0; 
		int numFor = 0;
		int numDo = 0;
		int numSwitch = 0;
		int numWhile = 0;
		int numCatch = 0;
		
		/*
		 * For each Statement object in the Method, check what type of ASTNode it is and'
		 * incremenet that type's count by one
		 */
		for(Statement s : statements) {
			if(s.getNode() instanceof IfStatement) {
				numIf += 1;
			}
			if(s.getNode() instanceof ForStatement) {
				numFor += 1;
			}
			if(s.getNode() instanceof EnhancedForStatement) {
				numFor += 1;
			}
			if(s.getNode() instanceof DoStatement) {
				numDo += 1;
			}
			if(s.getNode() instanceof SwitchStatement) {
				numSwitch += 1;
			}
			if(s.getNode() instanceof WhileStatement) {
				numWhile += 1;
			}
			if(s.getNode() instanceof CatchClause) {
				numCatch += 1;
			}
		}
		
		// Assign the values to the HashMap
		map.put("If", numIf);
		map.put("For", numFor);
		map.put("Do", numDo);
		map.put("Switch", numSwitch);
		map.put("While", numWhile);
		map.put("Catch", numCatch);
		
		// Return the HashMap
		return map;
	}
	
	/**
	 * Returns the method's complexity based on the Statement
	 * objects within the 
	 * @return int
	 */
	public int getMethodComplexity() {
		// Method complexity starts at 1
		int strictComplexity = 1;
		int essentialComplexity = 1;
		int modifiedComplexity = 1;
		int cyclomaticComplexity = 1;
		
		// Initialize the number of return statments to 0
		int numOfReturnStatements = 0;
		// For each Statement in Method
		for(Statement s : statements) {
			// if the statement type is a return statement
			if(s.getNode().getNodeType() == 41) {
				// increment the number of return statments in the method
				numOfReturnStatements += 1;
			}
			//The complexity of the Method is the sum of the current 
			//complexity plus the Statment's complexity
			System.out.println("statement: " + s.getComplexity());
			strictComplexity += s.getComplexity();
		}
		// if the method is a void type
		if(isVoid) {
			// add complexity equal to the number of return statements in the method
			strictComplexity += numOfReturnStatements;
		} 
		// if the method is any other type
		else {
			// add complexity equal to the number of return statments beyond the first
			strictComplexity += numOfReturnStatements - 1;
		}
		// return complexity
		return strictComplexity;
	}
		
}
