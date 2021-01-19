package ssmc;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import java.util.List;

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
		
		
	   /**
	    * get super class, try catch required since program will crash due
	    to null pointer exceptions when a class does not have a superclass"
	    **/
       try {
			  
			c.setSuperClass(node.getSuperclassType().toString());
			if (node.getSuperclassType().toString().equals("Thread")) c.setCritical(); // if superclass is Thread then this is a critical class.
			}
			catch(Exception e) {
			 
				c.setSuperClass("none");
		}
       
       /**
	    * get a List[] of all interfaces in each class, try catch required since program will crash due
	    to null pointer exceptions when a class does not have a superclass"
	    **/
       
       try {
			  
			c.addInterfaces(node.superInterfaceTypes());
			}
			catch(Exception e) {
				List<String> emptylst = Collections.emptyList();
				c.addInterfaces(emptylst);
		}
       
       /**
	    * get super class, try catch required since program will crash due
	    to null pointer exceptions when a class does not have a superclass"
	    **/
      
       
       
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
	
	/*private String getExtends(TypeDeclaration node) {
		String superClass = "";
		System.out.println("===========================");
		if(node.getSuperclassType()!=null) {
			System.out.println(node.getSuperclassType().toString());
			superClass = node.getSuperclassType().toString();
		}else {
			System.out.println("null");
			superClass = "Object";
		}
		System.out.println("===========================");
		return superClass;
	}*/

}
