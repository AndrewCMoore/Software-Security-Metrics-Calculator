package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

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
	private double methodComplexity;
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
	
	public ArrayList<Statement> getStatements(){
		return this.statements;
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
	public double getMethodComplexity() {
		return methodComplexity;
		
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
	public void setMethodComplexity(double newMethodComplexity) {
		this.methodComplexity = newMethodComplexity;		
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
	
}
