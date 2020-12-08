package ssmc;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Class {
	private String Identifier;
	private String modifier;
	private boolean serialized;
	private boolean critical;
	private CompilationUnit originFile;
	private Method[] methods;
	private Attribute[] attribute;
	
	public Class(String identifier, CompilationUnit originFile) {
		this.Identifier = identifier;
		this.originFile = originFile;
		this.serialized = false;
		this.critical = false;
	}
	
	 // Method that finds the starting line and ending line of the class
	// Use AttributeVisitor, MethodVisitor, and StatemenetVisitor to find all values within those lines
   // Return the Methods, Attributes, complexity
  // Line of A/M/S is within Class then put into array (compare)
	
}
