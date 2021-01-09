package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

public class MethodVisitor extends ASTVisitor{
	
	private static ArrayList<Method> methods;
	private CompilationUnit cu;
	private ArrayList<ASTNode> nodes;
	
    public MethodVisitor(CompilationUnit cu){
        super();
        this.cu = cu;
        methods = new ArrayList<Method>();
        nodes = new ArrayList<ASTNode>();
    }

    public ArrayList<Method> getMethods() {
		return methods;
	}
        
    public ArrayList<ASTNode> getNodes() {
    	return nodes;
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
    
}
    
