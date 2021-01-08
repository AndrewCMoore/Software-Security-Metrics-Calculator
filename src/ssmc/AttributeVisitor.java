package ssmc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class AttributeVisitor extends ASTVisitor{

	private CompilationUnit compliationUnit;
	private Set<String> names;
	private ArrayList<Attribute> attributes;
	private ArrayList<ASTNode> nodes;
	
	public AttributeVisitor(CompilationUnit compilationUnit) {
		super();
		this.compliationUnit = compilationUnit;
		names = new HashSet<String>();
		attributes = new ArrayList<Attribute>();
		nodes = new ArrayList<ASTNode>();
	}
	
	public boolean visit(VariableDeclarationFragment node) {
		nodes.add(node);
		SimpleName name = node.getName();											// Get the String ID of the node (variable)
		Attribute a = new Attribute(name.getIdentifier(), this.compliationUnit); 	// Create a new Attribute object
		IVariableBinding type = node.resolveBinding(); 								// Get the variable type
		int modifier = type.getModifiers(); 										// Get the modifier value 
		a.setModifier(modifier); 													// Set the Attribute's variables
		a.setLineNum(this.compliationUnit.getLineNumber(node.getStartPosition()));  // Sets the line number for the variable
		//System.out.println(a.toString());											// Print out the Attribute's Structure
		this.names.add(name.getIdentifier());										// Add the node name to the set of names
		this.attributes.add(a);														// Add the Attribute to the ArrayList of Attributes
		return true;
	}
	
	public boolean visit(SimpleName node) {
		this.nodes.add(node);
		if (this.names.contains(node.getIdentifier())) {
			for(int i = 0; i < this.attributes.size(); i++) {
				Attribute attribute = this.attributes.get(i);
				if(attribute.getIdentifier().equals(node.getIdentifier().toString())) {
					//System.out.println("Node: " + node.getIdentifier() + " is used at line " + this.compliationUnit.getLineNumber(node.getStartPosition()));
					attribute.addUsage();
					//System.out.println("The variable " + attribute.getIdentifier() + " has a usage of: " + attribute.getUsage());
				}
			}
		}
		
		return true;
	}
	
	public boolean visit(EnumDeclaration node) {
		this.nodes.add(node);
		SimpleName name = node.getName();											// Get the String ID of the node (variable)
		Attribute a = new Attribute(name.getIdentifier(), this.compliationUnit); 	// Create a new Attribute object
		a.setModifier("Public Static Final "); 										// Set the Attribute's variables
		a.setLineNum(this.compliationUnit.getLineNumber(node.getStartPosition()));  // Sets the line number for the variable
		//System.out.println(a.toString());											// Print out the Attribute's Structure
		this.names.add(name.getIdentifier());										// Add the node name to the set of names
		this.attributes.add(a);														// Add the Attribute to the ArrayList of Attributes
		return true;
	}
	public ArrayList<Attribute> getArrayList(){
		return this.attributes;
	}
	
	public ArrayList<ASTNode> getNodes(){
		return this.nodes;
	}
	
}
