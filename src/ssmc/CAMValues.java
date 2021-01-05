package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

/**
 * This class generates values for the Class, Attribute, and Method objects
 */
public class CAMValues {
	
	/**
	 * Uses unit to create a CompilationUnit to use in AttributeVisitor. 
	 * Runs the visitor class to get all Attribute values for the 
	 * ICompilationUnit
	 * @param unit ICompilationUnit input
	 */
	
	public static ArrayList<Attribute> generateAttributeAST(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		AttributeVisitor av = new AttributeVisitor(cu);
		cu.accept(av);
		
		return av.getArrayList();
	}

	/**
	 * Uses unit to create a CompilationUnit to use in MethodVisitor. 
	 * Runs the visitor class to get all Method values for the 
	 * ICompilationUnit
	 * @param unit ICompilationUnit input
	 */
	public static ArrayList<Method> generateMethodAST(ICompilationUnit unit){
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		MethodVisitor mv = new MethodVisitor(cu);
		cu.accept(mv);
		System.out.println("\n======================================");
		for(Method m :mv.getMethods()) {
			System.out.println(m.getIdentifier());
		}
		System.out.println("======================================\n");
		return mv.getMethods();
	}
	
	public static ArrayList<Class> generateBodyDeclarationAst(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		DeclarationVisitor dv = new DeclarationVisitor(cu);
		cu.accept(dv);
		
		return dv.getClasses();
	}
	
	/**
	 * Uses unit to create a CompilationUnit to use in CommentVisitor. 
	 * Runs the visitor class to get all comment related values for the 
	 * ICompilationUnit
	 * @param unit ICompilationUnit input
	 */
	public static void generateCommentAST(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		CommentVisitor cv = new CommentVisitor(cu);
		cu.accept(cv);
		System.out.println("The total number of commented lines is: " + cv.getLineCount());
	}
	
	/**
	 * Uses unit to create a CompilationUnit to use in MethodVisitor. 
	 * Runs the visitor class to get all statement values related
	 * to complexity for the ICompilationUnit
	 * @param unit ICompilationUnit input
	 */
	public static ArrayList<Statement> generateStatementAST(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		StatementVisitor sv = new StatementVisitor(cu);
		cu.accept(sv);
		System.out.println("In the Compilation Unit: " + unit.getElementName()
						  +" the values returned are: \n"  + sv.toString());
		
		return sv.getArrayList();
	}
	
	/**
	 * Method to parse the ICompilationUnit to a CompilationUnit
	 * Sets the type, source and resolve bindings
	 * @param unit ICompilationUnit input
	 * @return CompilationUnit
	 */
	
	
	
	
	public static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS14);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); //parse		
	}
	
	public static Class[] getClasses(ICompilationUnit unit) {
		ArrayList<Class> classes = generateBodyDeclarationAst(unit);
		ArrayList<Method> methods = generateMethodAST(unit);
		ArrayList<Statement> statements = generateStatementAST(unit);
		ArrayList<Attribute> attributes = generateAttributeAST(unit);
		
		for(Method m :methods) {
			Class c = getBelonging(m,classes);
			if(c  != null) {
				c.addMethod(m);
			}
		}
		
		for(Statement s: statements) {
			Method m = getBelonging(s, methods);
			if(m != null) {
				m.addStatement(s);
			}
		}
		
		for(Attribute a: attributes) {
			Class c = getBelonging(a, classes);
			if(c != null) {
				c.addAttribute(a);
				//System.out.println("The usage of attribute " + a.getIdentifier() + "defined on line " + a.getLineNum() + "is: " + a.getUsage());
			}
		}
		
		
		Class[] classList = new Class[classes.size()];
		for(int i =0;i<classes.size();i++) {
			classList[i]=classes.get(i);
			classList[i].isEnum();
		}
			
		return classList;
	}
	
	
	public static Class getBelonging(Method m,ArrayList<Class> classes) {
		Class cl = null;
		int min=Integer.MAX_VALUE;
		for(Class c:classes) {
			//System.out.println("C's start and end line are: " + c.getStartLine() + " " + c.getEndLine() + "M's start and end line are: " + m.getStartLine() + " " + m.getEndLine());
			if(c.getStartLine()<m.getStartLine()&&c.getEndLine()>m.getEndLine()) {
				int size = c.getEndLine()-c.getStartLine();				
				if(size<min) {
					
					cl =c;
					min = size;
					
				}
			}
		}
		System.out.println("The method is "+m.getIdentifier()+" and it belongs to "+cl.getIdentifier());
		return cl;
	}
	
	private static Class getBelonging(Attribute a, ArrayList<Class> classes) {
		Class cl = null;
		int min=Integer.MAX_VALUE;
		for(Class c:classes) {
			//System.out.println("C's start and end line are: " + c.getStartLine() + " " + c.getEndLine() + "M's start and end line are: " + m.getStartLine() + " " + m.getEndLine());
			if(c.getStartLine() < a.getLineNum() && c.getEndLine() > a.getLineNum()) {
				int size = c.getEndLine()-c.getStartLine();				
				if(size<min) {
					
					cl =c;
					min = size;
					
				}
			}
		}
		System.out.println("The method is "+a.getIdentifier()+" and it belongs to "+cl.getIdentifier());
		return cl;
	}
	
	private static Method getBelonging(Statement s, ArrayList<Method> methods) {
		Method m1 = null;
		int min=Integer.MAX_VALUE;
		for(Method m:methods) {
			//System.out.println("C's start and end line are: " + c.getStartLine() + " " + c.getEndLine() + "M's start and end line are: " + m.getStartLine() + " " + m.getEndLine());
			if(m.getStartLine()<s.getStartLine()&&m.getEndLine()>s.getEndLine()) {
				int size = m.getEndLine()-m.getStartLine();				
				if(size<min) {
					
					m1 =m;
					min = size;
					
				}
			}
		}
		System.out.println("The statement is " + getStatementNodeSimpleName(s) + " and it belongs to "+ m1.getIdentifier());
		return m1;
	}
	
	
		// none is 0
		// public is 1
		// private is 2
		// protected is 4
		// static is 8
		// final is 16
		// synchronized 32
		
		//Volatile 64
		//Transient 128
		// native 256
		
		// abstract 1024
		// strictfp 2048
	
	//This method simply runs through the flags and ads the string if the flag is on it
	public static String getModifier(int modifiers) {
		String modify = "";
		if( (modifiers & 1)!=0) {
			modify+="public ";
		}
		if( (modifiers & 2)!=0) {
			modify+="private ";
		}
		if( (modifiers & 4)!=0) {
			modify+="protected ";
		}
		if( (modifiers & 8)!=0) {
			modify+="static ";
		}
		if( (modifiers & 16)!=0) {
			modify+="final ";
		}
		if( (modifiers & 32)!=0) {
			modify+="synchronized ";
		}
		if( (modifiers & 64)!=0) {
			modify+="volatile ";
		}
		if( (modifiers & 128)!=0) {
			modify+="transient ";
		}
		if( (modifiers & 256)!=0) {
			modify+="native ";
		}
		if( (modifiers & 512)!=0) {
			modify+="";
		}
		if( (modifiers & 1024)!=0) {
			modify+="abstract ";
		}
		if( (modifiers & 2048)!=0) {
			modify+="strictfp ";
		}
		return modify;
	}
	
	public static String getStatementNodeSimpleName(Statement s) {
		switch(s.getNode().getNodeType()) {
		case 19: 
			return "DoStatement";
		case 24:
			return "ForStatement";
		case 25:
			return "IfStatement";
		case 49:
			return "SwitchCase";
		case 50:
			return "SwitchStatement";
		case 61: 
			return "WhileStatement";
		default:
			break;		
		}
		return "";
	}
}
