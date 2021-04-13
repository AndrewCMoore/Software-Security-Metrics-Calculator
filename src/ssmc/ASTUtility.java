package ssmc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class ASTUtility {

	public static final int getStartLine(final ASTNode node) {
		
		return ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition());
	}
	
	public static final int getEndLine(final ASTNode node) {
		return ((CompilationUnit) node.getRoot()).getLineNumber(node.getStartPosition() + node.getLength() - 1);
	}
	
	public static final ArrayList<String> getModifers(ASTNode node) {	
		if(node instanceof SingleVariableDeclaration){
			SingleVariableDeclaration sVDNode = (SingleVariableDeclaration) node;
			//System.out.println(sVDNode.getModifiers());
			
			return getModifier(sVDNode.getModifiers());
		}
		if(node instanceof BodyDeclaration) {
			BodyDeclaration bdNode = (BodyDeclaration) node;
			return getModifier(bdNode.getModifiers());
		} 
		if(node instanceof VariableDeclarationFragment){
            VariableDeclarationFragment vdNode = (VariableDeclarationFragment) node;
            System.out.println("|||" + vdNode.getParent().getClass());
                if(vdNode.getParent() instanceof FieldDeclaration) {
                    FieldDeclaration fd = (FieldDeclaration) vdNode.getParent();
                    System.out.println(" MODIFIER: " + fd.getModifiers());
                    return getModifier(fd.getModifiers());
                }
                if(vdNode.getParent() instanceof VariableDeclarationStatement) {
                    VariableDeclarationStatement vds = (VariableDeclarationStatement) vdNode.getParent();
                    System.out.println(" MODIFIER: " + vds.getModifiers());
                    return getModifier(vds.getModifiers());
                }
                if(vdNode.getParent() instanceof VariableDeclarationExpression) {
                    VariableDeclarationExpression vde = (VariableDeclarationExpression) vdNode.getParent();
                    System.out.println(" MODIFIER: " + vde.getModifiers());
                    return getModifier(vde.getModifiers());
                }
                if(vdNode.getParent() instanceof LambdaExpression) {
                    LambdaExpression le = (LambdaExpression) vdNode.getParent();
                    ITypeBinding type = le.resolveTypeBinding();

                    if(type!=null) {
                        System.out.println(type.getModifiers());
                        return getModifier(type.getModifiers());
                    }
                } 
        }
		
		
		return null; 
	}
	
	public static final String getNodeName(ASTNode node) {
		if(node instanceof AbstractTypeDeclaration) {
			AbstractTypeDeclaration ATDNode = (AbstractTypeDeclaration) node;
			return ATDNode.getName().getIdentifier();
		}
		return "";
		
	}
	
	/**
	    * get a List[] of all interfaces in each class, try catch required since program will crash due
	    to null pointer exceptions when a class does not have interfaces"
	    **/
	public static final void checkInterfaces(Class c, ASTNode node) {
		if(node instanceof TypeDeclaration) {
			TypeDeclaration TDNode = (TypeDeclaration) node;
			try {
				 
				c.addInterfaces(TDNode.superInterfaceTypes().toString());
			} catch (Exception e) {
				//List<String> emptylst = Collections.emptyList();
				c.addInterfaces("");
			}
		}
		if(node instanceof EnumDeclaration) {
			EnumDeclaration EDNode = (EnumDeclaration) node;
			try {
				c.addInterfaces(EDNode.superInterfaceTypes().toString());
			} catch (Exception e) {
				//List<String> emptylst = Collections.emptyList();
				c.addInterfaces("");
			}
		}
		
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
