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
import static org.junit.jupiter.api.Assertions.*;

public class ClassTest {

	@Test
	public void testClassNumber() {
		MainTest mt = new MainTest();

		try {
			mt.AccessTestClass();
			ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			assertEquals(classes.size() , 2);
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
			assertEquals (methods.size() , 4);
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
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			assertEquals (classes.size() , 2);
			assertEquals (classes.get(0).getIdentifier(),"Test_Class");
			assertEquals (classes.get(1).getIdentifier(),"InnerLevel");
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
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			assertEquals ( 1,classes.size() );
			assertEquals (classes.get(0).getIdentifier(),"Singleton");
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
			ArrayList<Class> classes = CAMValues.generateBodyDeclarationAst(cu);
			assertEquals (classes.size() , 4);
			assertEquals (classes.get(0).getIdentifier(),"Many_Classes");
			assertEquals (classes.get(1).getIdentifier(),"Inside");
			assertEquals (classes.get(2).getIdentifier(),"twoClasses");
			assertEquals (classes.get(3).getIdentifier(),"threeClasses");
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
			assertEquals (CAMValues.getBelonging(methods.get(0), classes),classes.get(0));
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
			assertEquals (CAMValues.getBelonging(methods.get(1), classes), classes.get(0));
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
			assertEquals(4,classes.size());
			assertEquals(5,methods.size());
			assertEquals (CAMValues.getBelonging(methods.get(4), classes) , classes.get(3));
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
			assertEquals ("public",classes.get(0).getModifier().split(" ")[0]);
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
			assertEquals (modifiers.contains("private"),true );
			assertEquals(modifiers.contains("final"),true);
			assertEquals(modifiers.contains("static"),true);
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
			String modifiers = classes.get(1).getModifier();
			assertEquals (3,modifiers.split(" ").length);
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
			
			assertEquals(classes.get(0).getStartLine(),2);
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
			
			assertEquals(24,classes.get(0).getEndLine());
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
		assertEquals(c.getMethods().get(0),m);
	}
	@Test
	public void tesAddMethod2() {
		
		Class c = new Class(null,null);
		Method m = new Method("Method 1",null);
		Method m2 = new Method("Method 2",null);
		c.addMethod(m);
		c.addMethod(m2);
		assertEquals(c.getMethods().size(),2);
		assertEquals(c.getMethods().get(0),m);
		assertEquals(c.getMethods().get(1),m2);
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
		assertEquals(c.getMethods().size(),15);
		for(int i=0;i<15;i++) {
			assertEquals(c.getMethods().get(i),methods.get(i));
		}
	}
	@Test
	public void tesAddAttribute() {
		
		Class c = new Class(null,null);
		Attribute a = new Attribute(null,null);
		c.addAttribute(a);
		assertEquals(c.getAttributes().get(0),a);
	}
	@Test
	public void tesAddAttribute2() {
		
		Class c = new Class(null,null);
		Attribute a = new Attribute("Attribute 1",null);
		Attribute a2 = new Attribute("Attribute 2",null);
		c.addAttribute(a);
		c.addAttribute(a2);
		assertEquals(c.getAttributes().size(),2);
		assertEquals(c.getAttributes().get(0),a);
		assertEquals(c.getAttributes().get(1),a2);
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
		assertEquals(c.getAttributes().size(),15);
		for(int i=0;i<15;i++) {
			assertEquals(c.getAttributes().get(i),Attributes.get(i));
		}
	}

}
