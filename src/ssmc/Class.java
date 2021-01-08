

package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Class {
	private String Identifier;
	private ArrayList<String> modifier;
	private int startLine;
	private int endLine;
	private boolean serialized;
	private boolean critical;
	private boolean Enum;
	private CompilationUnit originFile;
	private ArrayList<Method> methods;
	private ArrayList<Attribute> attributes;

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
	
	// I kind of need a way to differentiate between attributes declared at the class level and 
	// attributes declared within a method and im not sure if that is possible
	public boolean isAttributeInMethod(Attribute a) {
		int attributeLineNum = a.getLineNum();
		for(Method m : methods) {
			if(a.getLineNum() < m.getEndLine() && a.getLineNum() > m.getStartLine()) {
				return true;
			}
		}
		return false;
	}
	
	// Method that finds the starting line and ending line of the class
	// Use AttributeVisitor, MethodVisitor, and StatemenetVisitor to find all values within those lines
    // Return the Methods, Attributes, complexity
    // Line of A/M/S is within Class then put into array (compare)
	
	// Getters
	
	public String getIdentifier() {
		return this.Identifier;
	}
	public ArrayList<String> getModifier() {
		return this.modifier;
	}
	public void isEnum() {
		System.out.println("We in this method1");
		if(this.Enum) {
			System.out.println("We in this method2");
			for(Attribute a : attributes) {
				System.out.println("We in this method3");
				if(a.getLineNum() < this.getEndLine() && a.getLineNum() > this.getStartLine()) {
					System.out.println("===========================================================");
					System.out.println("We in this method");
					System.out.println("===========================================================");
					//a.setModifier("Public Static Final ");
				}
			}
		}
	}
	public void setEnum(boolean b) {
		this.Enum = b;
	}
	public boolean isSerialized() {
		return this.serialized;
	}
	public boolean isCritical() {
		return this.critical;
	}
	public CompilationUnit getCompilationUnit() {
		return this.originFile;
	}
	
	/**
	public Method[] getMethods() {
		return this.methods;
	}
	public Attribute[] getAttributes() {
		return this.attributes;
	}
	**/
	
	// Setters
	
	public void setStartLine(int start) {
		this.startLine = start;
	}
	public void setEndLine(int end) {
		this.endLine = end;
	}
	public void setModifier(ArrayList<String> modifier) {
		this.modifier = modifier;
	}
	public void setSerialized(boolean b) {
		this.serialized = b;
	}
	public void setCritical(boolean b) {
		this.critical = b;
	}
	public void addMethod(Method method) {
		methods.add(method);
	}
	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	public ArrayList<Method> getMethods(){
		return methods;
	}
	public ArrayList<Attribute> getAttributes(){
		return attributes;
	}
	public int getStartLine() {
		// TODO Auto-generated method stub
		return startLine;
	}

	public int getEndLine() {
		// TODO Auto-generated method stub
		return endLine;
	}
	
	public String toString() {
		return this.Identifier;
	}
	
}

