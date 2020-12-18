package ssmc;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ReturnStatement;

public class MethodVisitor extends ASTVisitor{

	private CompilationUnit cu;
	
    public MethodVisitor(CompilationUnit cu){
        super();
        this.cu = cu;
    }

    public boolean visit(MethodDeclaration node){
       // System.out.println("method " + node.getName() + " is " + node.modifiers() + " and length: " + node.getLength());
      //  System.out.println("from parent: " + node.getParent());
      //  System.out.println("has " + node.parameters().size()  + " parameters: " + node.parameters());
      //  System.out.println("returns: " + node.getReturnType2());
      //  System.out.println("isClassified? " + isClassified(node));
      //  System.out.println("////////////////////////////");
    		System.out.println("The modifier for"+node.getName()+" is "+node.getModifiers());
        return true;
    }
    
    /*public boolean visit(ReturnStatement node) {
    	System.out.println("this is a return statement");
    	System.out.println(node);
    	System.out.println(node.getExpression());
    	System.out.println("?????????????????????");
    	return true;
    }*/
    
    private boolean isClassified(MethodDeclaration node) {
    	for(Object modifier : node.modifiers()) {
    		if(modifier.toString().contains("private")) {
    			return true;
    		}
    	}
    	/*if(node.modifiers().get(0).toString().contains("private")) {
    		return true;
    	}*/
    	return false;
    }
    
}
    
