package ssmc;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import java.util.List;

public class DeclarationVisitor extends ASTVisitor {
	
	
	private CompilationUnit cu;
	private ArrayList<Class> classes;
	private ArrayList<ASTNode> nodes;
	
	/**
	 * Constructor class 
	 * @param cu CompilationUnit
	 */
	public DeclarationVisitor(CompilationUnit cu) {
		 super();
		 this.cu = cu;
		 classes = new ArrayList<Class>();
		 nodes = new ArrayList<ASTNode>();
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the TypeDeclaration ASTNodes within the CompilationUnit. 
	 * TypeDeclaration ASTNodes is a combination of interfaces and classes which follow 
	 * the format of: 
	 * 		
	 * 		ClassDeclaration:
     * 			[ Javadoc ] { ExtendedModifier } class Identifier
     *                   [ < TypeParameter { , TypeParameter } > ]
     *                   [ extends Type ]
     *                   [ implements Type { , Type } ]
     *                   { { ClassBodyDeclaration | ; } }
 	 *		InterfaceDeclaration:
     * 			[ Javadoc ] { ExtendedModifier } interface Identifier
     *                   [ < TypeParameter { , TypeParameter } > ]
     *                   [ extends Type { , Type } ]
     *                   { { InterfaceBodyDeclaration | ; } }
	 *
	 * This method determines key information of the Class in the 
	 * CompilationUnt. It then creates a new Class object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(TypeDeclaration node){
		
		nodes.add(node);
		
		Class c = new Class(ASTUtility.getNodeName(node), cu);
		c.setStartLine(ASTUtility.getStartLine(node));
		c.setEndLine(ASTUtility.getEndLine(node));
		c.setModifier(ASTUtility.getModifers(node));
		ASTUtility.checkInterfaces(c, node);
		
	    /**
	    * get super class, try catch required since program will crash due
	    to null pointer exceptions when a class does not have a superclass"
	    **/
        try {			  
			c.setSuperClass(node.getSuperclassType().toString());
			if (node.getSuperclassType().toString().equals("Thread")) c.setCritical(); // if superclass is Thread then this is a critical class.
			} catch (Exception e) {			 
				c.setSuperClass("null");
		}
       
        
       

        classes.add(c);
		return true;
    }
	
	

	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the EnumDeclaration ASTNodes within the CompilationUnit. 
	 * EnumDeclaration ASTNodes follow the format of: 
	 * 		
	 * 		EnumDeclaration:
     *			[ Javadoc ] { ExtendedModifier } enum Identifier
     *   			[ implements Type { , Type } ]
     *   			{
     *    			[ EnumConstantDeclaration { , EnumConstantDeclaration } ] [ , ]
     *    			[ ; { ClassBodyDeclaration | ; } ]
     *    			}
	 *
	 * This method determines key information of the Class in the 
	 * CompilationUnt. It then creates a new Class object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(EnumDeclaration node) {
		Class c = new Class(ASTUtility.getNodeName(node), cu);
		c.setStartLine(ASTUtility.getStartLine(node));
		c.setEndLine(ASTUtility.getEndLine(node));
		c.setModifier(ASTUtility.getModifers(node));
		ASTUtility.checkInterfaces(c, node);
		c.setEnum(true);
		classes.add(c);
		
		return true;
	}
	
	
	/**
	 * Returns the ArrayList of Class objects this Vistor has visited. 
	 * @return ArrayList of Class objects
	 */
	public ArrayList<Class> getClasses(){
		return classes;
	}
	 
	/**
	 * Returns the ArrayList of ASTNode objects this Visitor has visited.
	 * @return ArrayList of ASTNode objects. 
	 */
	public ArrayList<ASTNode> getNodes(){
		return nodes;
	}
	
}
