package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

public class MethodVisitor extends ASTVisitor{
	
	private CompilationUnit cu;
	private static ArrayList<Method> methods;
	private ArrayList<ASTNode> nodes;
	
    public MethodVisitor(CompilationUnit cu){
        super();
        this.cu = cu;
        methods = new ArrayList<Method>();
        nodes = new ArrayList<ASTNode>();
    }

    public boolean visit(MethodDeclaration node){  	
    	nodes.add(node);
    	int startLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition());
	 	int endLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()+node.getLength() - 1);
	 	
        SimpleName name = node.getName();
        String id = name.toString();
        
        int modifiers = node.getModifiers();
        ArrayList<String> modify = CAMValues.getModifier(modifiers);
    	
        Method m = new Method(id,cu);
        m.setModifiers(modify);
        m.setMethodLength(node.getLength());
    	
        m.setStartLine(startLineNum);
        m.setEndLine(endLineNum);
        
        methods.add(m);
        
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
    
    public ArrayList<Method> getMethods() {
		return methods;
	}
    
    public ArrayList<ASTNode> getNodes() {
    	return nodes;
    }
    
}
    
