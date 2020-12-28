package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;

public class MethodVisitor extends ASTVisitor{
	
	private CompilationUnit cu;
	private static ArrayList<Method> methods;
	
	
    public MethodVisitor(CompilationUnit cu){
        super();
        this.cu = cu;
        methods = new ArrayList<Method>();
    }

    public boolean visit(MethodDeclaration node){  	
    	int startLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition());
	 	int endLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()+node.getLength());
	 	
        SimpleName name = node.getName();
        String id = name.toString();
        
        int modifiers = node.getModifiers();
        String modify = CAMValues.getModifier(modifiers);
    	
        Method m = new Method(id,cu);
        methods.add(m);
        m.setMethodLength(node.getLength());
    	
        
        
    	
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
    
    public ArrayList<Method> getMethods(){
		return methods;
	}
    
}
    
