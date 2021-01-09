package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Attribute {
	private CompilationUnit compilationUnit;
	private boolean finalized;
	private String Identifier;
	private int lineNum;
	private int links;
	private ArrayList<String> modifier;
	private int usage;

	public Attribute(String identifier, CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
		this.Identifier = identifier;
		this.modifier = new ArrayList<String>();
		this.usage = 0;
		this.finalized = false;
		this.links = 0;
		this.lineNum = 0;
	}

	public void addUsage() {
		this.usage += 1;
	}

	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	public String getIdentifier() {
		return Identifier;
	}

	public int getLineNum() {
		return lineNum;
	}

	public int getLinks() {
		return links;
	}

	public ArrayList<String> getModifier() {
		return modifier;
	}

	public int getUsage() {
		return usage;
	}

	public boolean isFinalized() {
		if(this.modifier.contains("final")) {
			setFinalized(true);
		}
		
		return finalized;
	}

	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}

	public void setIdentifier(String identifier) {
		Identifier = identifier;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public void setLinks(int links) {
		this.links = links;
	}

	public void setModifier(int i) {
		this.modifier = CAMValues.getModifier(i);
	}
	
	public void setModifier(ArrayList<String> modifier) {
		this.modifier = modifier;
	}
	
	public void setUsage(int usage) {
		this.usage = usage;
	}

	@Override
	public String toString() {
		return "Attribute [Identifier=" + Identifier + ", modifier=" + modifier.toString() + ", usage=" + usage + ", finalized="
				+ finalized + ", compilationUnit=" + ", links=" + links + ", lineNum=" + lineNum
				+ "]";
	}

}
