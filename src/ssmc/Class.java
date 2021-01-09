

package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Class {
	private ArrayList<Attribute> attributes;
	private boolean critical;
	private int endLine;
	private boolean Enum;
	private String Identifier;
	private ArrayList<Method> methods;
	private ArrayList<String> modifier;
	private CompilationUnit originFile;
	private boolean serialized;
	private int startLine;

	public Class(String identifier, CompilationUnit originFile) {
		this.Identifier = identifier;
		this.originFile = originFile;
		this.serialized = false;
		this.critical = false;
		this.Enum = false;
		 methods = new ArrayList<Method>();
		 attributes = new ArrayList<Attribute>();
		 
		this.modifier = new ArrayList<String>();
		
	}
	
	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	
	// Method that finds the starting line and ending line of the class
	// Use AttributeVisitor, MethodVisitor, and StatemenetVisitor to find all values within those lines
    // Return the Methods, Attributes, complexity
    // Line of A/M/S is within Class then put into array (compare)
	
	// Getters
	
	public void addMethod(Method method) {
		methods.add(method);
	}
	public ArrayList<Attribute> getAttributes(){
		return attributes;
	}
	public CompilationUnit getCompilationUnit() {
		return this.originFile;
	}
	public int getEndLine() {
		return endLine;
	}
	public String getIdentifier() {
		return this.Identifier;
	}
	public ArrayList<Method> getMethods(){
		return methods;
	}
	public ArrayList<String> getModifier() {
		return this.modifier;
	}
	
	
	// Setters
	
	public int getStartLine() {
		return startLine;
	}
	// I kind of need a way to differentiate between attributes declared at the class level and 
	// attributes declared within a method and im not sure if that is possible
	public boolean isAttributeInMethod(Attribute a) {
		
		for(Method m : methods) {
			if(a.getLineNum() < m.getEndLine() && a.getLineNum() > m.getStartLine()) {
				return true;
			}
		}
		return false;
	}
	public boolean isCritical() {
		return this.critical;
	}
	public void isEnum() {
		
		if(this.Enum) {
			
			for(Attribute a : attributes) {
				
				if(a.getLineNum() < this.getEndLine() && a.getLineNum() > this.getStartLine()) {
					System.out.println("===========================================================");
					System.out.println("We in this method");
					System.out.println("===========================================================");
					//a.setModifier("Public Static Final ");
				}
			}
		}
	}
	public boolean isSerialized() {
		return this.serialized;
	}
	public void setCritical(boolean b) {
		this.critical = b;
	}
	public void setEndLine(int end) {
		this.endLine = end;
	}
	public void setEnum(boolean b) {
		this.Enum = b;
	}
	public void setModifier(ArrayList<String> modifier) {
		this.modifier = modifier;
	}
	public void setSerialized(boolean b) {
		this.serialized = b;
	}

	public void setStartLine(int start) {
		this.startLine = start;
	}
	
	public String toString() {
		return this.Identifier;
	}
	
}

