package ssmc;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ModuleDeclaration;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;

public class DeclarationVisitor extends ASTVisitor {
	
	private CompilationUnit cu;
	
	
	public DeclarationVisitor(CompilationUnit cu) {
		 super();
		 this.cu = cu;
		 System.out.println("created a vistor");
	}
	
	public boolean visit(ReturnStatement v) {
		System.out.println("got to a a variable declaration");
		return true;
	}
	
	 public boolean visit(TypeDeclaration node){
		 	System.out.println("got here?");
	       System.out.println(node);
	        return true;
	    }
	

}
