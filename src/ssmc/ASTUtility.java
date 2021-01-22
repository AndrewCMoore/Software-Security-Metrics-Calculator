package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclaration;

public class ASTUtility {

	public static final int getStartLine(final ASTNode node) {
		return ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition());
	}
	
	public static final int getEndLine(final ASTNode node) {
		return ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition() + node.getLength() - 1);
	}
	
	public static final ArrayList<String> getModifers(final ASTNode node) {
		if(node instanceof BodyDeclaration) {
			BodyDeclaration bdNode = (BodyDeclaration) node;
			return getModifier(bdNode.getModifiers());
		} 
		if(node instanceof VariableDeclaration){
			VariableDeclaration vdNode = (VariableDeclaration) node;
			IVariableBinding type = vdNode.resolveBinding();
			return getModifier(type.getModifiers());
		}
		
		return null; 
	}
	
	public static final String getNodeName(ASTNode node) {
		if(node instanceof AbstractTypeDeclaration) {
			AbstractTypeDeclaration ATDNode = (AbstractTypeDeclaration) node;
			return ATDNode.getName().getIdentifier();
		}
		return null;
		
	}
	
	/**
	 * This method takes an input of the modifier from the ASTNode's method 
	 * call getModifiers() and determines what the modifier is by using a
	 * bitwise AND. Adds all modifiers to an ArrayList to return. 
	 * 
	 * @param modifiers int return value of ASTNode's method getModifiers()
	 * @return ArrayList of String object each representing a modifier
	 */
	private static ArrayList<String> getModifier(int modifiers) {
		final ArrayList<String> arrayList = new ArrayList<String>();
		
		if( (modifiers & 1)!=0) {
			arrayList.add("public");
		}
		if( (modifiers & 2)!=0) {
			arrayList.add("private");
		}
		if( (modifiers & 4)!=0) {
			arrayList.add("protected");
		}
		if( (modifiers & 8)!=0) {
			arrayList.add("static");
		}
		if( (modifiers & 16)!=0) {
			arrayList.add("final");
		}
		if( (modifiers & 32)!=0) {
			arrayList.add("synchronized");
		}
		if( (modifiers & 64)!=0) {
			arrayList.add("volatile");
		}
		if( (modifiers & 128)!=0) {
			arrayList.add("transient");
		}
		if( (modifiers & 256)!=0) {
			arrayList.add("native");
		}
		if( (modifiers & 512)!=0) {
			arrayList.add("DEFAULT");
		}
		if( (modifiers & 1024)!=0) {
			arrayList.add("abstract");
		}
		if( (modifiers & 2048)!=0) {
			arrayList.add("strictfp");
		}
		return arrayList;
	}
}
