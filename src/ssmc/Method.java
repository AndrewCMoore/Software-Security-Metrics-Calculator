package ssmc;
  
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
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
	
	/**
	 * Constructor method
	 * @param identifier String representation of Method's identifier
	 * @param compilationUnit CompilationUnit
	 */
	public Method(String identifier, CompilationUnit compilationUnit) {
		this.length = 0;
		this.numberOfOutputs = 0;
		this.numberOfInputs	 = 0;
		this.isClassified = false;
		this.isWriteClassified = false;	
		this.isInherited = false;
		this.isAccessible = false;
		this.identifier = identifier;
		this.usage = 0;
		this.isFinalized = false;
		this.compilationUnit = compilationUnit;
		this.setLinks(0);
		
		this.startLine = 0;
		this.endLine = 0;
		
		this.statements = new ArrayList<Statement>();
		this.modifiers = new ArrayList<String>();
		
	}
	
	/**
	 * Adds a Statmenet object to the ArrayList of Statement objects
	 * within this Method. 
	 * @param statement Statement
	 */
	public void addStatement(Statement statement) {
		int id = System.identityHashCode(statement.getNode());
		for(Statement s: statements) {
			//Determines if the Statement object is already in the ArrayList by 
			//checking the system hashcode of both object's ASTNodes. 
			if(id == System.identityHashCode(s.getNode())) {
				return ;
			}
		}
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
		for(String s : modifiers) {
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
		
		int numIf = 0; 
		int numFor = 0;
		int numDo = 0;
		int numSwitch = 0;
		int numWhile = 0;
		int numCatch = 0;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
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
		
		map.put("If", numIf);
		map.put("For", numFor);
		map.put("Do", numDo);
		map.put("Switch", numSwitch);
		map.put("While", numWhile);
		map.put("Catch", numCatch);
		
		return map;
	}
	
	/**
	 * Returns the method's complexity based on the Statement
	 * objects within the 
	 * @return int
	 */
	public int getMethodComplexity() {
		int complexity = 1;
		int numOfReturnStatements = 0;
		for(Statement s : statements) {
			if(s.getNode().getNodeType() == 41) {
				numOfReturnStatements += 1;
			}
			complexity += s.getComplexity();
		}
		if(this.modifiers.contains("void")) {
			complexity += numOfReturnStatements;
		} else {
			complexity += numOfReturnStatements - 1;
		}
		
		return complexity;
	}
		
}
