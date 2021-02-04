package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotatableType;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;

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
    
    	// If the Return type is primitive
    	if(node.getReturnType2() instanceof PrimitiveType) {
    		// Cast to primitive and check if the primitive is void
    		if(((PrimitiveType) node.getReturnType2()).getPrimitiveTypeCode() == PrimitiveType.VOID) {
    			// Set the method to void type
    			m.setVoid(true);
    		}
    	}

        m.setStartLine(ASTUtility.getStartLine(node));
        m.setEndLine(ASTUtility.getEndLine(node));
        
        m.setMethodLength(m.getEndLine() - m.getStartLine());
        methods.add(m);
        
        return true;
    }
    
    
    public boolean visit(MethodInvocation node) {
    	// Gets the parameter
    	nodes.add(node);
    	
    	try {
    		// For the last method defined
    		Method m = methods.get(methods.size() - 1);	
    		// Add to the number of methods invoked in said method
    		m.addInvocation();
    		//System.out.println("Method : " + m.getIdentifier() + " invoked method: " + node.getName().getIdentifier() + " on line: " + ASTUtility.getStartLine(node));
    	
    		// Catch if methods is empty
    	} catch (IndexOutOfBoundsException e) {}
    	
    	return true;
    }
    
    public boolean visit(SingleVariableDeclaration node) {
    	// Gets the parameter
    	nodes.add(node);
    	// For each Method visited
    	for(Method m: methods) {
    		// Check if the object in the () is on the same line
    		if(m.getStartLine() == ASTUtility.getStartLine(node)) {
    			// Gets the String representation of the parameters name
    			final String name = node.getName().getIdentifier();
    			// Gets the String representation of the parameters t
    	    	final String type = node.getType().toString();
    	    	// Set the parameter for the Method
    	    	m.setParameters(name, type);
    	    	m.setOnlyparameterTypes(type);//anthony
    	    	//System.out.println("Method " + m.toString() + " has parameter" + m.getParameters().toString());
    		}
    	}
    	return false;
    }

}
    
