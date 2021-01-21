package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;

/**
 * This Class defines this Attribute object. This object is used to store data from ASTNodes when visited.
 * 
 * @author AndrewCMoore
 *
 */
public class Attribute {
	private CompilationUnit compilationUnit;
	private boolean finalized;
	private String Identifier;
	private int lineNum;
	private int links;
	private ArrayList<String> modifier;
	private int usage;

	/**
	 * Constructor for Attribute object
	 * @param identifier String representation of SimpleName of ASTNode
	 * @param compilationUnit CompilationUnit that Attribute is contained in
	 */
	public Attribute(String identifier, CompilationUnit compilationUnit) {
		// Initialize variables from parameters
		this.compilationUnit = compilationUnit;
		this.Identifier = identifier;
		// Initialize core variables
		this.usage = 0;
		this.finalized = false;
		this.links = 0;
		this.lineNum = 0;
		// Intialize ArrayList
		this.modifier = new ArrayList<String>();
	}

	/**
	 * Adds usage to the Attribute object. This method is used for each attribute
	 * found in the program after its defintion
	 */
	public void addUsage() {
		this.usage += 1;
	}

	/**
	 * Returns the CompilaitonUnit of the Attribute object. Used to determine aggregation
	 * within each CompilationUnit
	 * @return CompilationUnit of Attribute object
	 */
	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	/**
	 * Returns the attribute's simple name as a String
	 * @return String representation of attribute's simpleName
	 */
	public String getIdentifier() {
		return Identifier;
	}

	/**
	 * This method returns the line number of which the attribute
	 * this object represents is on in the CompilationUnit
	 * @return Integer value Line number of attribute
	 */
	public int getLineNum() {
		return lineNum;
	}

	/**
	 * This method returns an Integer value of the number of 
	 * links between Attribute objects. 
	 * @return Integer value number of links
	 */
	public int getLinks() {
		return links;
	}

	/**
	 * This method returns an ArrayList of String object representing the 
	 * modifiers of the attribute in the CompilationUnit
	 * @return ArrayList of String objects
	 */
	public ArrayList<String> getModifier() {
		return modifier;
	}

	/**
	 * Returns the value of the attribute's usage within the CompilationUnit
	 * @return Integer value of the attribute's usage
	 */
	public int getUsage() {
		return usage;
	}

	/**
	 * Sets the finalized attribute of the object by determining if the modifiers contains
	 * the "final" modifier
	 * @return (boolean) finalized attribute
	 */
	public boolean isFinalized() {
		// Checks if the modifier contains the word final
		if(this.modifier.contains("final")) {
			// If so sets the variable to true
			this.finalized = true;
		}
		// Returns if finalized or not
		return finalized;
	}

	/**
	 * Sets the finalizaed attribute within the Object. To be used in method isFinalized()
	 * @deprecated
	 * @param finalized Boolan value (true/false) 
	 */
	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}

	/**
	 * Sets the identifer attribute of the object. This is often used in the AttributeVisitor 
	 * class when creating new Attribute objects. 
	 * @param identifier String representation of a ASTNode's SimpleName
	 */
	public void setIdentifier(String identifier) {
		Identifier = identifier;
	}

	/**
	 * This method is used in the Visitor class to set the Attribute's line number.
	 * @param lineNum Integer value of the line number in the CompilationUnit
	 */
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	/**
	 * This method set the number of links between Attribute objects. 
	 * @param links Integer value of the number of links. 
	 */
	public void setLinks(int links) {
		this.links = links;
	}

	/**
	 * This method is used to set the modifier in most cases. The Integer i is used in the method 
	 * getModifier in class CAMValues. This returns an ArrayList of Strings that represent the modifiers
	 * of the method.
	 * @param i Integer representing an ASTNode's modifiers.
	 */
	public void setModifier(int i) {
		this.modifier = CAMValues.getModifier(i);
		
	}
	
	/**
	 * This method is used to set the modifier in special cases, such as enumeration objects where the modifier is 
	 * not automatically set by the system and must be set to "public static final"
	 * @param modifier an ArrayList of String values
	 */
	public void setModifier(ArrayList<String> modifier) {
		this.modifier = modifier;
	}
	
	/**
	 * This method allows the usage of an Attribute object to be hard reset. 
	 * @param usage Integer value based on the number of times the attribute was used in the program. 
	 */
	public void setUsage(int usage) {
		this.usage = usage;
	}

	/**
	 * ToString method for helping with the development of the Attribute object. 
	 */
	@Override
	public String toString() {
		return "Attribute [Identifier=" + Identifier + ", modifier=" + modifier.toString() + ", usage=" + usage + ", finalized="
				+ finalized + ", compilationUnit=" + ", links=" + links + ", lineNum=" + lineNum
				+ "]";
	}

}
