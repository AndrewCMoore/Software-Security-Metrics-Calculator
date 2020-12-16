package ssmc;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ModuleDeclaration;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;

public class DeclarationVisitor extends ASTVisitor {
	
	private CompilationUnit cu;
	
	
	public DeclarationVisitor(CompilationUnit cu) {
		 super();
		 this.cu = cu;
	}
	
	
	 public boolean visit(TypeDeclaration node){
		 	System.out.println("got here?");
		 	int startLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition());
		 	int endLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()+node.getLength());
	        SimpleName name = node.getName();
	        String id = name.toString();
	        Class c = new Class(id,cu);
	        c.setStartLine(startLineNum);
	        c.setEndLine(endLineNum);
	        CAMValues.addToClassList(c);
	        return true;
	    }
	

}
