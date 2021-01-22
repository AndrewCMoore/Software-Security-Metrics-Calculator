package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

import ssmc.ASTUtility;

public class MethodVisitor extends ASTVisitor{
	
	private ArrayList<Method> methods;
	private final CompilationUnit cu;
	private ArrayList<ASTNode> nodes;
	
	/**
	 * Consutructor for class
	 * @param cu CompilationUnit
	 */
    public MethodVisitor(CompilationUnit cu){
        super();
        this.cu = cu;
        methods = new ArrayList<Method>();
        nodes = new ArrayList<ASTNode>();
    }

    /**
     * Returns the Methods this class visits
     * @return ArrayList of Method objects
     */
    public ArrayList<Method> getMethods() {
		return methods;
	}
        
    /**
     * Returns the ASTNodes this class visits
     * @return ArrayList of Method objects
     */
    public ArrayList<ASTNode> getNodes() {
    	return nodes;
    }
    
    /**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the MethodDeclaration ASTNodes within the CompilationUnit. 
	 * TypeDeclaration ASTNodes is a combination of constructors and methods which follow 
	 * the format of: 
	 * 		
	 *	MethodDeclaration:
     *		[ Javadoc ] { ExtendedModifier } [ < TypeParameter { , TypeParameter } > ] ( Type | void )
     *   		Identifier (
     *       		[ ReceiverParameter , ] [ FormalParameter { , FormalParameter } ]
     *   		) { Dimension }
     *   		[ throws Type { , Type } ]
     *   		( Block | ; )
 	 *
 	 *	ConstructorDeclaration:
     *		[ Javadoc ] { ExtendedModifier } [ < TypeParameter { , TypeParameter } > ]
     *  		Identifier (
     *       		[ ReceiverParameter , ] [ FormalParameter { , FormalParameter } ]
     *   		) { Dimension }
     *   		[ throws Type { , Type } ]
     * 			( Block | ; )
	 *
	 * This method determines key information of the Method in the 
	 * CompilationUnt. It then creates a new Method object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
    public boolean visit(MethodDeclaration node){  	

    	nodes.add(node);	 	
        SimpleName name = node.getName();
        String id = name.toString();
    	
        Method m = new Method(id,cu);
        m.setModifiers(ASTUtility.getModifers(node));
        m.setMethodLength(node.getLength());
 
        m.setStartLine(ASTUtility.getStartLine(node));
        m.setEndLine(ASTUtility.getEndLine(node));
        
        methods.add(m);
        
        return true;
    }

}
    
