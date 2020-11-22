package ssmc;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodVisitor extends ASTVisitor{

	private CompilationUnit cu;
	
    public MethodVisitor(CompilationUnit cu){
        super();
        this.cu = cu;
    }

    public boolean visit(MethodDeclaration node){
        System.out.println("We are inside a method declaration node");
        System.out.println(node.getName());

        return true;
    }
}
