package ssmc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LineComment;


/**
 * This class generates values for the Class, Attribute, Method, and Statement objects
 */
public class CAMValues extends Thread {
	
	Class[] classArray;
	ICompilationUnit unit;
	boolean running = true;
	
	// For Threading 
	final int MAX_NO_OF_THREADS = 5;
	final Semaphore semaphore = new Semaphore(MAX_NO_OF_THREADS);
	
	public CAMValues(ICompilationUnit unit) {
		this.unit = unit;
	}
	
	
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
	 * Uses unit to create a CompilationUnit to use in DeclarationVisitor. 
	 * Runs the visitor class to get all Class values for the 
	 * ICompilationUnit
	 * @param unit ICompilationUnit input
	 */
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
	 * @deprecated
	 * @param unit ICompilationUnit input
	 */
	public static void generateCommentAST(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		CommentVisitor cv = new CommentVisitor(cu);
		cu.accept(cv);
		//System.out.println("The total number of commented lines is: " + cv.getLineCount());
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
	
	/**
	 * Uses unit to create a CompilationUnit to use in MethodVisitor. 
	 * Runs the visitor class to get all Statement values related
	 * to complexity for the ICompilationUnit
	 * @param unit ICompilationUnit input
	 */
	public static ArrayList<Statement> generateStatementAST(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		//System.out.println("WABABA " + cu.getLineNumber(cu.getLength()-1));
		StatementVisitor sv = new StatementVisitor(cu);
		cu.accept(sv);
		//System.out.println("\n\n\n " + sv.ids + "\n\n\n");
		//System.out.println("In the Compilation Unit: " + unit.getElementName()
		//				  +" the values returned are: \n"  + sv.toString());
		
		return sv.getArrayList();
	}
	
	/**
	 * This method determines which Class object an Attribute object belongs to 
	 * within the CompilationUnit. This is done by comparing the line on which 
	 * the Attribute object reside on to the starting and ending line of the 
	 * Class object. If the Attribute object is within the Class object, it 
	 * sets variable c1 to that Class. It does this itteratively to ensure that 
	 * inner classes are accounted for in the process. 
	 * 
	 * @param a Attribute object to be determine which Class object it belongs to
	 * @param classes ArrayList of Class objects to be evaluated
	 * @return The Class object the Attribute belongs to
	 */
	private static Class getBelonging(Attribute a, ArrayList<Class> classes) {
		Class cl = null;
		int min=Integer.MAX_VALUE;
		for(Class c:classes) {
			if(c.getStartLine() < a.getLineNum() && c.getEndLine() > a.getLineNum()) {
				int size = c.getEndLine()-c.getStartLine();				
				if(size<min) {
					
					cl =c;
					min = size;
					
				}
			}
		}
		return cl;
	}
	/**
	 * This method determines which Class object a Method object belongs to 
	 * within the CompilationUnit. This is done by comparing the starting and
	 * ending line on which the Method object reside on to the starting and ending 
	 * line of the Class object. If the Method object is within the Class object, 
	 * it sets variable c1 to that Class. It does this itteratively to ensure that 
	 * inner classes are accounted for in the process. 
	 * 
	 * @param m Method object to be determine which Class object it belongs to
	 * @param classes ArrayList of Class objects to be evaluated
	 * @return The Class object the Method belongs to
	 */
	public static Class getBelonging(Method m,ArrayList<Class> classes) {
		Class cl = null;
		int min=Integer.MAX_VALUE;
		for(Class c:classes) {
			if(c.getStartLine()<m.getStartLine()&&c.getEndLine()>m.getEndLine()) {
				int size = c.getEndLine()-c.getStartLine();				
				if(size<min) {
					
					cl =c;
					min = size;
					
				}
			}
		}
		return cl;
	}
	
	/**
	 * This method determines which Method object a Statmenet object belongs to 
	 * within the CompilationUnit. This is done by comparing the starting and
	 * ending line on which the Statmenet object reside on to the starting and ending 
	 * line of the Method object. If the Statement object is within the Method object, 
	 * it sets variable m1 to that Method. 
	 * 
	 * @param s Statement objects to be determined which Class object it belongs to 
	 * @param methods ArrayList of Method objects to be evaluated
	 * @return The Method object the Statement belongs to
	 */
	private static Method getBelonging(Statement s, ArrayList<Method> methods) {
		Method m1 = null;
		int min=Integer.MAX_VALUE;
		for(Method m:methods) {
			if(m.getStartLine()<=s.getStartLine() && m.getEndLine()>=s.getEndLine()) {
				int size = m.getEndLine()-m.getStartLine();				
				if(size<min) {
					
					m1 =m;
					min = size;
					
					return m1;
					
				}
			}
		}
		return m1;
	}
	
	/**
	 * This method gathers all the information in the CompilationUnit and 
	 * aggregates the objects into Class objects. 
	 * 
	 * Calls methods generateBodyDeclarationAST(unit), generateMethodAST(unit),
	 * generateStatementAST(unit),  and generateAttributeAST(unit) to gather information 
	 * about the CompilationUnit. Each returns an ArrayList of the type object type: 
	 * (Class, Method, Statement, Attribute)
	 * 
	 * From theses ArrayLists, it uses the getBelonging() methods to organize and aggregate
	 * the objects from Statment into Method, Attribute into Class, and Method into 
	 * Class.
	 * 
	 * 
	 * @param unit CompilationUnit 
	 * @return Array of Class objects with aggregated Statement, Attribute, and Method objects
	 */
	public static Class[] getClasses(ICompilationUnit unit) {
		
		ArrayList<Attribute> attributes = generateAttributeAST(unit);
		ArrayList<Statement> statements = generateStatementAST(unit);
		ArrayList<Method> methods = generateMethodAST(unit);
		ArrayList<Class> classes = generateBodyDeclarationAst(unit);
		
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		
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
			}
		}
				
		for(Method m :methods) {
			Class c = getBelonging(m,classes);
			if(c  != null) {
				c.addMethod(m);
			}
			m.isAccessor();
			m.isMutator();
		}
		
		
		
		
		
		Class[] classList = new Class[classes.size()];
		for(int i =0;i<classes.size();i++) {
			classList[i]=classes.get(i);
		}
			
		return classList;
	}
		
	/**
	 * This method gets the String representation of a Statement object. 
	 * 
	 * @param s Statement object 
	 * @return String representation of the Statment s's ASTNode
	 */
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
	
	/**
	 * Method to parse the ICompilationUnit to a CompilationUnit
	 * Sets the type, source and resolve bindings
	 * @param unit ICompilationUnit input
	 * @return CompilationUnit
	 */
	public static CompilationUnit parse(ICompilationUnit unit) {
		@SuppressWarnings("deprecation")
		ASTParser parser = ASTParser.newParser(AST.JLS14);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); //parse		
	}
	
	public void run() {
		while(running) {
			try {
				
				System.out.println("Thread " + Thread.currentThread().getId()+ " has now started");
				System.out.println("There are " + Thread.currentThread().activeCount() + " threads running");
				this.classArray = getClasses(unit);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// Stop the thread for system resource management
				running = false;
				System.out.println("Thread " + Thread.currentThread().getId()+ " is now closed");
				return; 
			}
		}	
	}

	public Class[] getClassArray() {
		return classArray;
	}
}