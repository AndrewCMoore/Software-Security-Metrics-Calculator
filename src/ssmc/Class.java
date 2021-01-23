package ssmc; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ReturnStatement;

public class Class {
	
	private ArrayList<Attribute> attributes;
	private CompilationUnit compilationUnit;
	private boolean critical;
	private int endLine;
	private boolean Enum;
	private String Identifier;
	private ArrayList<Method> methods;
	private ArrayList<String> modifier;
	private boolean serialized;
	private int startLine;
	private String superClass;
	private List<String> interfaces;
	
	/**
	 * Constructor method for class
	 * @param identifier String representation of the Class's name
	 * @param compilationUnit CompilationUnit the Class represents
	 */
	public Class(String identifier, CompilationUnit compilationUnit) {
		// Sets variables from parameters 
		this.Identifier = identifier;
		this.compilationUnit = compilationUnit;
		
		// Sets variables to base values
		this.serialized = false;
		this.critical = false;
		this.setEnum(false);
		
		// Initialize ArrayLists
		methods = new ArrayList<Method>();
		attributes = new ArrayList<Attribute>();
		interfaces = new ArrayList<String>();
		this.modifier = new ArrayList<String>();
		
	}
	
	
		
	public void isMutatorMethod(Method m) {
		// If the method name contains the word 'set' 
		if(m.getIdentifier().contains("set")) {
			// The method length should be 1 (or 0) 
			if(m.getMethodLength() <= 1) {
				// The variable should be used 
			}
		}
	}
	
	
	/**
	 * This method is called to add an Attribute object to the 
	 * Class' ArrayList of Attribute objects. 
	 * 
	 * @param attribute Attribute object
	 */
	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	
	/**
	 * This method is called to add an Method object to the 
	 * Class' ArrayList of Method objects. 
	 * 
	 * @param method Method object
	 */
	public void addMethod(Method method) {
		methods.add(method);
	}
	
	/**
	 * This method is logic for the Cyclomatic Complexity.
	 * It returns a HashMap of the number of IfStatement, ForStatement, 
	 * DoStatemnet, SwtichStatement, and whileStatement ASTNodes within 
	 * the Class object.
	 * 
	 * @return HashMap<String, Integer> Number of each type of Statement
	 */
	public HashMap<String, Integer> getNumOfStatements() {
		// Create a HashMap object to be used for the return value and intialize it.
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// Creates a count for each type of statement ASTNode that adds a unqiue complexity.
		// If, For, Do, Switch, While
		int numIf = 0; 
		int numFor = 0;
		int numDo = 0;
		int numSwitch = 0;
		int numWhile = 0;
		
		// Itterate through each Method object this class contains
		for(Method m : methods) {
			// Get the HashMap of Statements from the Method object
			HashMap<String, Integer> tempMap = m.getNumOfStatements();
			// Add the values to the total count
			numIf += tempMap.get("If");
			numFor += tempMap.get("For");
			numDo += tempMap.get("Do");
			numSwitch += tempMap.get("Switch");
			numWhile += tempMap.get("While");	
		}
		// Put the total values across all Method objects into the HashMap
		map.put("If", numIf);
		map.put("For", numFor);
		map.put("Do", numDo);
		map.put("Switch", numSwitch);
		map.put("While", numWhile);
		
		// Return HashMap
		return map;
		
	}
	
	/**
	 * Gets the Class' ArrayList of Attributes
	 * @return ArrayList of Attribute objects
	 */
	public ArrayList<Attribute> getAttributes(){
		return attributes;
	}
	
	/**
	 * Gets the Class' CompilationUnit
	 * @return CompilationUnit
	 */
	public CompilationUnit getCompilationUnit() {
		return this.compilationUnit;
	}
	
	/**
	 * Gets the last line of the Class in the CompilationUnit.
	 * This is deemed as the '}'
	 * @return int Last line of Class
	 */
	public int getEndLine() {
		return endLine;
	}
	
	/**
	 * Gets the String representation of the Class' name
	 * @return String Class name
	 */
	public String getIdentifier() {
		return this.Identifier;
	}
	
	/**
	 * Gets the Class' ArrayList of Method objects.
	 * @return ArrayList of Method objects
	 */
	public ArrayList<Method> getMethods(){
		return methods;
	}
	
	/**
	 * Gets the Class' modifiers
	 * @return ArrayList of String objects
	 */
	public ArrayList<String> getModifier() {
		return this.modifier;
	}
	
	/**
	 * Gets the first line of the Class object. This is deemed
	 * as the Class' defintion line. 
	 * @return int First line in the Class
	 */
	public int getStartLine() {
		return startLine;
	}
	
	
	// I kind of need a way to differentiate between attributes declared at the class level and 
	// attributes declared within a method and im not sure if that is possible
	/**
	 * Method takes an Attribute object and determines whether that Attribute is in a Class
	 * object or a Method object. Does this by checking if the Attribute is on a line between
	 * the starting and ending line of the Method object. Checks this for each Method object 
	 * within the Class. If it is found within a method, returns true. If not, it is a global 
	 * variable and thus returns false.
	 * @param a Attribute object
	 * @return boolean if Attribute is in any method in the class
	 */
	public boolean isAttributeInMethod(Attribute a) {
		// For each Method object within this Class
		for(Method m : methods) {
			// If the Attribute's line number is between the start & end line of the Method
			if(a.getLineNum() < m.getEndLine() && a.getLineNum() > m.getStartLine()) {
				// Return true
				return true;
			}
		}
		// If here, the Attribute was not in any Method and thus was a member variable
		return false;
	}
	
	/**
	 * Returns whether the class is deemed to be critical
	 * @return boolean
	 */
	public boolean isCritical() {
		return this.critical;
	}
	
	/**
	 * Determines whether the Class is an Enumeration class or not. If the Class
	 * is an Enum, an ArrayList of Strings is created and filled with the modifiers 
	 * "public static final". These modifiers are set for each Attribute within 
	 * the class. 
	 * @return boolean
	 */
	public boolean isEnum() {
		// If the Class an enumeration Class defined by DeclarationVisitor upon visiting the class
		if(Enum) {
			// Create an ArrayList of the modifiers public static final
			ArrayList<String> enumConstant = new ArrayList<String>();
			enumConstant.add("public");
			enumConstant.add("static");
			enumConstant.add("final");
			
			// For each member named-constant in the enumeration class
			for(Attribute a : attributes) {
				// Set the modifier to public static final
				a.setModifier(enumConstant);
			}
		}
		// Return if an enumeration class
		return Enum;
	}
	
	/**
	 * Returns whether the class is serailized 
	 * @return boolean
	 */
	public boolean isSerialized() {
		return this.serialized;
	}
	
	/**
	 * Sets the Class to be deemed as a critical class.
	 */
	public void setCritical() {
		this.critical = true;
	}
	
	/**
	 * Sets the Class to have the end line of the end parameter. 
	 * @param end int
	 */
	public void setEndLine(int end) {
		this.endLine = end;
	}
	
	/**
	 * Sets the Class to be an Enum or not. Default is non-enum.
	 * @param b boolean
	 */
	public void setEnum(boolean b) {
		Enum = b;
	}

	/**
	 * Sets the modifier of the Class from an ArrayList of String objects.
	 * This is used in the CAMValues' method setModifiers(). 
	 * @param modifier ArrayList of String objects
	 */
	public void setModifier(ArrayList<String> modifier) {
		this.modifier = modifier;
	}
	
	/**
	 * Sets if the Class is serialized or not
	 * @param b boolean
	 */
	public void setSerialized(boolean b) {
		this.serialized = b;
	}

	/**
	 * Sets the Class to have the start line of the start parameter. 
	 * @param start int
	 */
	public void setStartLine(int start) {
		this.startLine = start;
	}

	/**
	 * Returns the String representation of the Class' name.
	 */
	public String toString() {
		return this.Identifier;
	}
	
	/**
	 * Returns the superClass of this Class object. 
	 * @return
	 */
	public String getSuperClass() {
		return superClass;
	}
	
	/**
	 * Sets the superclass of this Class object.
	 * @param superClass String
	 */
	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}
	
	/**
	 * Adds a List of String representations of the interfaces to the Class
	 * @param list List of String objects.
	 */
	public void addInterfaces(List<String> list) {
		this.interfaces.addAll(list);
	}
	
	/**
	 * Returns the List of String representations of the interfaces
	 * to the Class
	 * @return List of String objects.
	 */
	public List<String> getInterfaces() {
		return this.interfaces;
	}
}

