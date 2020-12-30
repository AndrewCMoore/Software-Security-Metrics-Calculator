package ssmc;

import java.util.ArrayList;

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
	private static ArrayList<Class> classes;
	
	public DeclarationVisitor(CompilationUnit cu) {
		 super();
		 this.cu = cu;
		 classes = new ArrayList<Class>();
	}
	
	
	 public boolean visit(TypeDeclaration node){
		 	int startLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition());
		 	int endLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()+node.getLength() - 1);
	        SimpleName name = node.getName();
	        int modifiers = node.getModifiers();
	        String modify = CAMValues.getModifier(modifiers);
	        String id = name.toString();
	        Class c = new Class(id,cu);
	        c.setStartLine(startLineNum);
	        c.setEndLine(endLineNum);
	        c.setModifier(modify);
	        
	        classes.add(c);
	        return true;
	    }
	 
	


	public ArrayList<Class> getClasses(){
			return classes;
		}
	

}
