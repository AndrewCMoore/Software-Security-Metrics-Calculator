package Parser_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.junit.jupiter.api.Test;

import ssmc.CAMValues;
import ssmc.MethodVisitor;

class MethodVisitorTest {

	private MethodVisitor mv;
	private CompilationUnit cu;
	private ArrayList<MethodDeclaration> methods; 
	
	public MethodVisitorTest() throws CoreException {
		MainTest mt = new MainTest();
		
		this.methods = new ArrayList<MethodDeclaration>();
		
		ICompilationUnit iCompilationUnit = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
		this.cu = CAMValues.parse(iCompilationUnit);
		mv = new MethodVisitor(cu);
		
		cu.accept(mv);
		
		ArrayList<ASTNode> nodes = mv.getNodes();
		
		System.out.println(nodes.size());
		
		for(Object o: nodes) {
			if(o instanceof MethodDeclaration) {
				methods.add((MethodDeclaration) o);
			}
		}
	}
	
	@Test
	void testVisitMethodDeclaration() {
		// Create a new MethodVisitor
		MethodVisitor testMV = new MethodVisitor(cu);
		// Visit a MethodDeclaration node in MethodVisitor
		testMV.visit(methods.get(1));
		// Ensure there is only one node in the MethodVisitor
		assertEquals(1, testMV.getNodes().size());
		// Ensure that the node is MethodA()
		assertEquals(mv.getNodes().get(1), testMV.getNodes().get(0));	
	}

	@Test
	void testGetMethods() {
		assertEquals("", mv.getMethods().toString());
		fail("Test doesn't work as intended");
	}

	@Test
	void testGetNodes() {
		assertEquals("[public Test_Class(){\n"
				+ "  attributeA=\"00\";\n"
				+ "}\n"
				+ ", public void methodA(){\n"
				+ "  attributeA=\"02\";\n"
				+ "  InnerLevel il=new InnerLevel();\n"
				+ "}\n"
				+ ", public InnerLevel(){\n"
				+ "  attributeB=\"01\";\n"
				+ "}\n"
				+ "]"
				, mv.getNodes().toString());
	}

}
