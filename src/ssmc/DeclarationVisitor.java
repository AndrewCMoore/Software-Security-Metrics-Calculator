package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class DeclarationVisitor extends ASTVisitor {
	
	
	private CompilationUnit cu;
	private ArrayList<Class> classes;
	private ArrayList<ASTNode> nodes;
	
	public DeclarationVisitor(CompilationUnit cu) {
		 super();
		 this.cu = cu;
		 classes = new ArrayList<Class>();
		 nodes = new ArrayList<ASTNode>();
	}
	
	
	public boolean visit(TypeDeclaration node){
		nodes.add(node);
		int startLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition());
		int endLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()+node.getLength() - 1);
		SimpleName name = node.getName();
		int modifiers = node.getModifiers();
		ArrayList<String> modify = CAMValues.getModifier(modifiers);
		String id = name.toString();
		//System.out.println(id);
		Class c = new Class(id,cu);
		c.setStartLine(startLineNum);
		c.setEndLine(endLineNum);
		c.setModifier(modify);
		
		classes.add(c);
		//System.out.print(c.toString());
		return true;
    }

	public boolean visit(EnumDeclaration node) {
		String id = node.getName().toString();
		Class c = new Class(id, cu);
		int startLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition());
		int endLineNum = ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition()+node.getLength() - 1);
		c.setStartLine(startLineNum);
		c.setEndLine(endLineNum);
		c.setModifier(CAMValues.getModifier(node.getModifiers()));
		c.setEnum(true);
		classes.add(c);
		
		return true;
	}
	public ArrayList<Class> getClasses(){
		return classes;
	}
	 
	public ArrayList<ASTNode> getNodes(){
		return nodes;
	}
	

}
