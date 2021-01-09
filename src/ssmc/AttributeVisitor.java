package ssmc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class AttributeVisitor extends ASTVisitor{

	private ArrayList<Attribute> attributes;
	private CompilationUnit compliationUnit;
	private Set<String> names;
	private ArrayList<ASTNode> nodes;
	
	public AttributeVisitor(CompilationUnit compilationUnit) {
		super();
		this.compliationUnit = compilationUnit;
		names = new HashSet<String>();
		attributes = new ArrayList<Attribute>();
		nodes = new ArrayList<ASTNode>();
	}
	
	public ArrayList<Attribute> getArrayList(){
		return this.attributes;
	}
	
	public ArrayList<ASTNode> getNodes(){
		return this.nodes;
	}
	
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
	
	public boolean visit(SimpleName node) {
		
		if (this.names.contains(node.getIdentifier())) {
			for(int i = 0; i < this.attributes.size(); i++) {
				Attribute attribute = this.attributes.get(i);
				if(attribute.getIdentifier().equals(node.getIdentifier().toString())) {
					attribute.addUsage();
				}
			}
		}
		
		this.nodes.add(node);
		
		return true;
	}
	
	public boolean visit(VariableDeclarationFragment node) {
		
		SimpleName name = node.getName();											// Get the String ID of the node (variable)
		Attribute a = new Attribute(name.getIdentifier(), this.compliationUnit); 	// Create a new Attribute object
		
		IVariableBinding type = node.resolveBinding(); 								// Get the variable type
		
		int modifiers = type.getModifiers(); 										// Get the modifier value 
		ArrayList<String> modifier = CAMValues.getModifier(modifiers);
		
		a.setModifier(modifier); 	 												// Set the Attribute's variables
		a.setLineNum(this.compliationUnit.getLineNumber(node.getStartPosition()));  // Sets the line number for the variable
		
		this.names.add(name.getIdentifier());										// Add the node name to the set of names
		this.attributes.add(a);	
		nodes.add(node);
		
		return true;
	}
	
}
