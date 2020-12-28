package ssmc;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Method {
	private int length;
	private int numberOfOutputs;
	private int numberOfInputs;
	private double methodComplexity;
	private boolean isClassified;
	private boolean isWriteClassified;
	private boolean isInherited;
	private boolean isAccessible;
	private String identifier;
	private String modified;
	private int usage;
	private boolean isFinalized;
	private CompilationUnit originFile;
	private int links;
	private int startLine;
	private int endLine;
	
	public Method(String identifier, CompilationUnit originFile) {
		this.length = 0;
		this.numberOfOutputs = 0;
		this.numberOfInputs	 = 0;
		this.methodComplexity = 0.0;
		this.isClassified = false;
		this.isWriteClassified = false;	
		this.isInherited = false;
		this.isAccessible = false;
		this.identifier = identifier;
		this.modified = null;
		this.usage = 0;
		this.isFinalized = false;
		this.originFile = originFile;
		this.links = 0;
		
	}
	
	public int getMethodLength() {
		return length;
			
	}

	public void setMethodLength(int newLength) {
		this.length = newLength;
		
	}

	public int getNumbeOfInputs() {
		return numberOfInputs;
		
		
	}
	
	public void setNumbeOfInputs(int newNumberOfInputs) {
		this.numberOfInputs =newNumberOfInputs;
		
	}
	
	public int getNumberOfOutputs() {
		return numberOfInputs;
	}
	
	public void setNumberOfOutputs(int newNumberOfOutputs) {
		this.numberOfOutputs = newNumberOfOutputs;
	}
	
	public double getMethodComplexity() {
		return methodComplexity;
		
	}
	
	public void setMethodComplexity(double newMethodComplexity) {
		this.methodComplexity = newMethodComplexity;		
	}
	
	public boolean getClassified() {
		return isClassified;		
	}

	public void setClassified(boolean newisClassified) {
		this.isClassified = newisClassified;
	}
	
	

	public boolean getWriteClassified() {
		return isWriteClassified;
	}
	
	public void setWriteClassified(boolean b){
		this.isWriteClassified = b;
	}
	
	public boolean getAccessible(){
		return isAccessible;
	}

	public void setAccessible(boolean b){
		this.isAccessible = b;
	}
	
	public boolean getInherated(){
		return this.isInherited;
	}
	
	
	public void setInherated(boolean b){
		this.isInherited = b;
	}

	public void addUsage() {
		this.usage = this.usage + 1;
	}
	
	
	public String getIdentifier() {
		return this.identifier;
	}
	

	public int getUsage() {
		return this.usage;
	}

	public void setFinalized(boolean b) {
		this.isFinalized = b;
	}

	public boolean getFinalized(){
		return isFinalized;
	}

	public void setModifier(String m){
		this.modified = m;
	}
	
	public void setStartLine(int start) {
		this.startLine = start;
	}
	public int getStartLine() {
		return startLine;
	}
	public void setEndLine(int end) {
		this.endLine = end;
	}
	public int getEndLine() {
		return endLine;
	}
	
/**	
	public String toString() {
		String s = new String();
		s += "Identifier: " + this.Identifier + "\n";
		s += "Modifiers: " + this.modifier + "\n";
		/*
		for(int i = 0; i < this.modifier.length; i++) {
			s += "" + this.modifier[i] + "\n";
		}*/
/**		
		s += "Usage: " + usage + "\n";
		s += "Finalized: ";
		
		if(this.finalized) {
			s += " Final\n";
		} else {
			s += " N/A\n";
		}
					
		return s;
		}
		**/
	
}
