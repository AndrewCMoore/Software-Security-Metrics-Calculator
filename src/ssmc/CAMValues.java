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
	private static ArrayList<Class> classes;
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
	public static void generateMethodAST(ICompilationUnit unit){
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		MethodVisitor mv = new MethodVisitor(cu);
		cu.accept(mv);
	}
	
	public static void generateBodyDeclarationAst(ICompilationUnit unit) {
		classes = new ArrayList<Class>();
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		DeclarationVisitor dv= new DeclarationVisitor(cu);
		cu.accept(dv);
		
		
	}
	public static void addToClassList(Class c) {
		classes.add(c);
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
	
	protected static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS13);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); //parse		
	}
	
	public static Class[] getClasses(ICompilationUnit unit) {
		generateBodyDeclarationAst(unit);
		generateAttributeAST(unit);
		Class[] classList = new Class[classes.size()];
		for(int i =0;i<classes.size();i++) {
			classList[i]=classes.get(i);
		}
		
		return classList;
	}
}
