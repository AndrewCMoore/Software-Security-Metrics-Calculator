
package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Class {
	private String Identifier;
	private String modifier;
	private int startLine;
	private int endLine;
	private boolean serialized;
	private boolean critical;
	private CompilationUnit originFile;
	private ArrayList<Method> methods;
	private Attribute[] attributes;
	
	public Class(String identifier, CompilationUnit originFile) {
		this.Identifier = identifier;
		this.originFile = originFile;
		this.serialized = false;
		this.critical = false;
		this.methods = new ArrayList<Method>();
		this.attributes = new Attribute[0];
		
	}
	
	// Method that finds the starting line and ending line of the class
	// Use AttributeVisitor, MethodVisitor, and StatemenetVisitor to find all values within those lines
    // Return the Methods, Attributes, complexity
    // Line of A/M/S is within Class then put into array (compare)
	
	// Getters
	public int getStartLine() {
		return startLine;
	}
	public int getEndLine() {
		return endLine;
	}
	public String getIdentifier() {
		return this.Identifier;
	}
	public String getModifier() {
		return this.modifier;
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
	public ArrayList<Method> getMethods() {
		return this.methods;
	}
	public Attribute[] getAttributes() {
		return this.attributes;
	}
	
	// Setters
	
	public void setStartLine(int start) {
		this.startLine = start;
	}
    public void setEndLine(int end) {
		this.endLine = end;
	}
	public void setModifier(String modifier) {
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
		attributes[attributes.length] = attribute;
	}
	
	
}

