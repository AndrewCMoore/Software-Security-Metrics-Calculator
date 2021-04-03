package ssmc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class AttributeVisitor extends ASTVisitor{

	private ArrayList<Attribute> attributes;
	private CompilationUnit compliationUnit;
	private Set<String> names;
	private ArrayList<ASTNode> nodes;
	
	/**
	 * Constructor, calls the superclass ASTVisitor initializes ArrayList objects of Attribute, 
	 * String, and ASTNode objects in order to track progress through the CompilationUnit.
	 * @param compilationUnit The CompilationUnit the Visitor class will visit. 
	 */
	public AttributeVisitor(CompilationUnit compilationUnit) {
		super();
		this.compliationUnit = compilationUnit;
		names = new HashSet<String>();
		attributes = new ArrayList<Attribute>();
		nodes = new ArrayList<ASTNode>();
	}
	
	/**
	 * Returns an ArratyList of Attribute objects. These are all the Attribute objects created
	 * when visiting the ASTNodes of this CompilationUnit.
	 * @return ArrayList of Attribute objects
	 */
	public ArrayList<Attribute> getArrayList(){
		return this.attributes;
	}
	
	/**
	 * Returns an ArrayList of ASTNode objects. These are all the ASTNode objects
	 * created when visiting the ASTNodes of this CompilationUnit.
	 * @return ArrayList of ASTNode objects
	 */
	public ArrayList<ASTNode> getNodes(){
		return this.nodes;
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all nodes within the CompilationUnit that are of the Enum declaration
	 * EnumConstantDeclaration follows the format:
     * 		[ Javadoc ] { ExtendedModifier } Identifier
     *  		[ ( [ Expression { , Expression } ] ) ]
     *    		[ AnonymousClassDeclaration ]
     *    
     * Upon visiting these types, creates a new Attribute object. Sets the modifiers, 
     * and add this ASTNode and Attribute object to their ArrayLists. This also adds the SimpleName 
     * object of the ASTNode to an ArrayList. This is used in visit(SimpleName) to access the Attribute
     * object that this SimpleName/ASTNode represents. 
     * 
     * Returns true to search for a child ASTNode.
	 */
	public boolean visit(EnumConstantDeclaration node) {
		
		SimpleName name = node.getName();											// Get the String ID of the node (variable)
		Attribute a = new Attribute(name.getIdentifier(), this.compliationUnit); 	// Create a new Attribute object
		ArrayList<String> modifier = new ArrayList<String>();
		
		modifier.add("public");
		modifier.add("static");
		modifier.add("final");
		
		a.setModifier(modifier); 										// Set the Attribute's variables
		a.setLineNum(this.compliationUnit.getLineNumber(node.getStartPosition()));  // Sets the line number for the variable
		
		this.nodes.add(node);
		this.names.add(name.getIdentifier());										// Add the node name to the set of names
		this.attributes.add(a);														// Add the Attribute to the ArrayList of Attributes
		return true;
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the SimpleName ASTNodes. These are Attribute usage statements
	 * within the CompilationUnit. 
	 * SimpleName ASTNodes follow the format of: 
	 * 		
	 * 		Identifier
	 *
	 * This class determines if the SimpleName is within the 
	 * is in the ArrayList of SimpleNames. This indicates the varaible has been defined, and
	 * is now checking for usage. 
	 * 
	 * Adds usage to the Attribute object. 
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(SimpleName node) {
		
		if (this.names.contains(node.getIdentifier())) {
			for(int i = 0; i < this.attributes.size(); i++) {
				Attribute attribute = this.attributes.get(i);
				if(attribute.getIdentifier().equals(node.getIdentifier().toString())) {
					attribute.addUsage();
					attribute.setCritical();
				}
			}
		}
		
		this.nodes.add(node);
		
		return true;
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the VaraibleDeclarationFragment ASTNodes within 
	 * the CompilationUnit. 
	 * VaraibleDeclarationFragment ASTNodes follow the format of: 
	 * 
	 * 		Identifier { Dimension } [ = Expression ]
	 * 
	 * Upon visiting these types, it creates a new Attribute object. Sets the modifiers, 
     * and add this ASTNode and Attribute object to their ArrayLists. This also adds the SimpleName 
     * object of the ASTNode to an ArrayList. This is used in visit(SimpleName) to access the Attribute
     * object that this SimpleName/ASTNode represents. 
	 * 
	 * Returns true to search for a child ASTNode.
	 */	
	public boolean visit(VariableDeclarationFragment node) {
		
		SimpleName name = node.getName();											// Get the String ID of the node (variable)
		Attribute a = new Attribute(name.getIdentifier(), this.compliationUnit); 	// Create a new Attribute object

		
		
		a.setModifier(ASTUtility.getModifers(node)); 	 												// Set the Attribute's variables
		a.setLineNum(this.compliationUnit.getLineNumber(node.getStartPosition()));  // Sets the line number for the variable
		
		// Get the type of the variable
		//System.out.println(node.getParent().getClass());
		if(node.getParent() instanceof FieldDeclaration) {
			FieldDeclaration typeCast = (FieldDeclaration) node.getParent();
			a.setType(typeCast.getType().toString());
		}
		
		this.names.add(name.getIdentifier());										// Add the node name to the set of names
		this.attributes.add(a);	
		nodes.add(node);
		return true;

	}
}
