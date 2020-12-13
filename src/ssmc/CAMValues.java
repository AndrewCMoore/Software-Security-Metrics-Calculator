package ssmc;

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
	public static void generateMethodAST(ICompilationUnit unit){
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		MethodVisitor mv = new MethodVisitor(cu);
		cu.accept(mv);
	}
	
	public static void generateBodyDeclarationAst(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		DeclarationVisitor dv= new DeclarationVisitor(cu);
		cu.accept(dv);
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
		generateBodyDeclarationAst(unit);
		
		
		return null;
	}
}
