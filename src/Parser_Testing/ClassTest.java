package Parser_Testing;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.junit.Test;
import ssmc.Class;
import ssmc.Method;
import ssmc.Attribute;
import ssmc.CAMValues;

public class ClassTest {

	@Test
	public void testClassNumber() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			assert (classes.size() == 2);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void testMethodNumber() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
			ArrayList<Method> methods = CAMValues.generateMethodAST(cu);
			assert (methods.size() == 2);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	public void testClassName() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
			ArrayList<Method> classes = CAMValues.generateMethodAST(cu);
			assert (classes.size() == 2);
			assert (classes.get(0).getIdentifier().equals("Test_Class"));
			assert (classes.get(0).getIdentifier().equals("InnerLevel"));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		
	}
	@Test
	public void testClassName2() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Singleton.java");
			ArrayList<Method> classes = CAMValues.generateMethodAST(cu);
			assert (classes.size() == 1);
			assert (classes.get(0).getIdentifier().equals("Singleton"));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		
	}
	@Test
	public void testClassName3() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Many_Classes.java");
			ArrayList<Method> classes = CAMValues.generateMethodAST(cu);
			assert (classes.size() == 4);
			assert (classes.get(0).getIdentifier().equals("Mainy_Classes"));
			assert (classes.get(1).getIdentifier().equals("Inside"));
			assert (classes.get(2).getIdentifier().equals("twoClasses"));
			assert (classes.get(3).getIdentifier().equals("threeClasses"));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		
	}

	@Test
	public void testgetBelonging1() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Singleton.java");
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			ArrayList<Method> methods = CAMValues.generateMethodAST(cu);
			assert (CAMValues.getBelonging(methods.get(0), classes) == classes.get(0));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
	
	
	@Test
	public void testgetBelonging2() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			ArrayList<Method> methods = CAMValues.generateMethodAST(cu);
			assert (CAMValues.getBelonging(methods.get(1), classes) == classes.get(1));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	public void testgetBelonging3() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Many_Classes.java");
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			ArrayList<Method> methods = CAMValues.generateMethodAST(cu);
			assert(classes.size()==4);
			assert(methods.size()==1);
			assert (CAMValues.getBelonging(methods.get(0), classes) == classes.get(3));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}

	}
	
	@Test
	public void testGetModifiers1() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			assert (classes.get(0).getModifier().equals("public"));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void testGetModifiers2() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			String modifiers =classes.get(1).getModifier();
			assert (modifiers.contains("private")&&modifiers.contains("final")&&modifiers.contains("static"));
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	@Test
	public void testGetModifiers3() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			String modifiers =classes.get(1).getModifier();
			assert (modifiers.split(" ").length==3);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testStartLine() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			
			assert(classes.get(0).getStartLine()==2);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testEndLine() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			
			assert(classes.get(0).getEndLine()==22);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	@Test
	public void tesAddMethod() {
		
		Class c = new Class(null,null);
		Method m = new Method(null,null);
		c.addMethod(m);
		assert(c.getMethods().get(0)==m);
	}
	@Test
	public void tesAddMethod2() {
		
		Class c = new Class(null,null);
		Method m = new Method("Method 1",null);
		Method m2 = new Method("Method 2",null);
		c.addMethod(m);
		c.addMethod(m2);
		assert(c.getMethods().size()==2);
		assert(c.getMethods().get(0)==m);
		assert(c.getMethods().get(1)==m2);
	}
	@Test
	public void tesAddMethod3() {
		ArrayList<Method> methods = new ArrayList<Method>();
		Class c = new Class(null,null);
		for(int i=0;i<15;i++) {
			Method m = new Method("Method 1",null);
			methods.add(m);
			c.addMethod(m);
		}
		assert(c.getMethods().size()==15);
		for(int i=0;i<15;i++) {
			assert(c.getMethods().get(i)==methods.get(i));
		}
	}
	@Test
	public void tesAddAttribute() {
		
		Class c = new Class(null,null);
		Attribute a = new Attribute(null,null);
		c.addAttribute(a);
		assert(c.getAttributes().get(0)==a);
	}
	@Test
	public void tesAddAttribute2() {
		
		Class c = new Class(null,null);
		Attribute a = new Attribute("Attribute 1",null);
		Attribute a2 = new Attribute("Attribute 2",null);
		c.addAttribute(a);
		c.addAttribute(a2);
		assert(c.getAttributes().size()==2);
		assert(c.getAttributes().get(0)==a);
		assert(c.getAttributes().get(1)==a2);
	}
	@Test
	public void tesAddAttribute3() {
		ArrayList<Attribute> Attributes = new ArrayList<Attribute>();
		Class c = new Class(null,null);
		for(int i=0;i<15;i++) {
			Attribute a = new Attribute("Attribute 1",null);
			Attributes.add(a);
			c.addAttribute(a);
		}
		assert(c.getMethods().size()==15);
		for(int i=0;i<15;i++) {
			assert(c.getAttributes().get(i)==Attributes.get(i));
		}
	}

}
