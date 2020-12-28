package ssmc;

import java.util.ArrayList;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

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
	
	public static void generateAttributeAST(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		AttributeVisitor av = new AttributeVisitor(cu);
		cu.accept(av);
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
	public static void generateStatementAST(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		StatementVisitor sv = new StatementVisitor(cu);
		cu.accept(sv);
		System.out.println("In the Compilation Unit: " + unit.getElementName()
						  +" the values returned are: \n"  + sv.toString());
	}
	
	/**
	 * Method to parse the ICompilationUnit to a CompilationUnit
	 * Sets the type, source and resolve bindings
	 * @param unit ICompilationUnit input
	 * @return CompilationUnit
	 */
	
	
	
	
	protected static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS13);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); //parse		
	}
	
	public static Class[] getClasses(ICompilationUnit unit) {
		ArrayList<Class> classes = generateBodyDeclarationAst(unit);
		ArrayList<Method> methods = generateMethodAST(unit);
		
		for(Method m :methods) {
			Class c = getBelonging(m,classes);
			if(c  != null) {
				c.addMethod(m);
			}
		}
		
		
		
		Class[] classList = new Class[classes.size()];
		for(int i =0;i<classes.size();i++) {
			classList[i]=classes.get(i);
		}
		
		return classList;
	}
	
	
	private static Class getBelonging(Method m,ArrayList<Class> classes) {
		Class cl = null;
		int min=Integer.MAX_VALUE;
		for(Class c:classes) {
			System.out.println("Class start line "+c.getStartLine()+" Method Start line "+m.getStartLine());
			System.out.println("Class end line "+c.getEndLine()+" Method Stendart line "+m.getEndLine());
			if(c.getStartLine()<m.getStartLine()&&c.getEndLine()>m.getEndLine()) {
				int size = c.getEndLine()-c.getStartLine();
				System.out.println("Size is "+size);
				if(size<min) {
					
					cl =c;
					min = size;
					
				}
			}
		}
		return cl;
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
			modify+=" public";
		}
		if( (modifiers & 2)!=0) {
			modify+=" private";
		}
		if( (modifiers & 4)!=0) {
			modify+=" protected";
		}
		if( (modifiers & 8)!=0) {
			modify+=" static";
		}
		if( (modifiers & 16)!=0) {
			modify+=" final";
		}
		if( (modifiers & 32)!=0) {
			modify+=" synchronized";
		}
		if( (modifiers & 64)!=0) {
			modify+=" volatile";
		}
		if( (modifiers & 128)!=0) {
			modify+=" transient";
		}
		if( (modifiers & 256)!=0) {
			modify+=" native";
		}
		if( (modifiers & 512)!=0) {
			modify+=" ";
		}
		if( (modifiers & 1024)!=0) {
			modify+=" abstract";
		}
		if( (modifiers & 2048)!=0) {
			modify+=" strictfp";
		}
		return modify;
	}
	
}
