package ssmc;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

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
	
	public Method(String identifier, CompilationUnit compilationUnit) {
		this.length = 0;
		this.numberOfOutputs = 0;
		this.numberOfInputs	 = 0;
		this.methodComplexity = 0.0;
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
	
	public void addStatement(Statement statement) {
		this.statements.add(statement);
	}

	public void addUsage() {
		this.usage = this.usage + 1;
	}

	public boolean getAccessible(){
		return isAccessible;
	}
	
	public boolean getClassified() {
		return isClassified;		
	}
	
	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}
	
	public int getEndLine() {
		return endLine;
	}
	
	public boolean getFinalized(){
		return isFinalized;
	}
	
	public String getIdentifier() {
		return this.identifier;
	}
	
	public boolean getInherated(){
		return this.isInherited;
	}
	public int getLinks() {
		return links;
	}

	public int getMethodLength() {
		return length;
			
	}
	
	

	public ArrayList<String> getModifiers(){
		return this.modifiers;
		
	}
	
	public int getNumbeOfInputs() {
		return numberOfInputs;
		
		
	}
	
	public int getNumberOfOutputs() {
		return numberOfOutputs;
	}

	public int getStartLine() {
		return startLine;
	}
	
	public ArrayList<Statement> getStatements(){
		return statements;
	}
	
	public int getUsage() {
		return this.usage;
	}
	
	
	public boolean getWriteClassified() {
		return isWriteClassified;
	}

	public void setAccessible(boolean b){
		this.isAccessible = b;
	}
	
	
	public void setClassified(boolean newisClassified) {
		this.isClassified = newisClassified;
	}
	

	public void setEndLine(int end) {
		this.endLine = end;
	}

	public void setFinalized(boolean b) {
		this.isFinalized = b;
	}

	public void setInherated(boolean b){
		this.isInherited = b;
	}
	
	public void setLinks(int links) {
		this.links = links;
	}

	public void setMethodLength(int newLength) {
		this.length = newLength;
		
	}
	public void setModifiers(ArrayList<String> modifiers) {
		for(String s : modifiers) {
			this.modifiers.add(s);
		}
	}
	public void setNumbeOfInputs(int newNumberOfInputs) {
		this.numberOfInputs =newNumberOfInputs;
		
	}
	public void setNumberOfOutputs(int newNumberOfOutputs) {
		this.numberOfOutputs = newNumberOfOutputs;
	}

	public void setStartLine(int start) {
		this.startLine = start;
	}

	public void setWriteClassified(boolean b){
		this.isWriteClassified = b;
	}

	public String toString() {
		return this.identifier;
	}
	
	
	// Return the number of types of statement within each Method 
	
	public HashMap<String, Integer> getNumOfStatements() {
		
		int numIf = 0; 
		int numFor = 0;
		int numDo = 0;
		int numSwitch = 0;
		int numWhile = 0;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for(Statement s : statements) {
			if(s.getNode() instanceof IfStatement) {
				numIf += 1;
			}
			if(s.getNode() instanceof ForStatement) {
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
		}
		
		map.put("If", numIf);
		map.put("For", numFor);
		map.put("Do", numDo);
		map.put("Switch", numSwitch);
		map.put("While", numWhile);
	
		return map;
	}
	
	public int getMethodComplexity() {
		int complexity = 1;
		int numOfReturnStatements = 0;
		for(Statement s : statements) {
			if(s.getNode().getNodeType() == 41) {
				numOfReturnStatements += 1;
			}
			complexity += s.getComplexity();
		}
		
		complexity += numOfReturnStatements - 1;
		
		return complexity;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
