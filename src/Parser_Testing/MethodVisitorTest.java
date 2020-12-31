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
		
		ICompilationUnit iCompilationUnit = mt.AccessTestClass().getCompilationUnit("");
		this.cu = CAMValues.parse(iCompilationUnit);
		mv = new MethodVisitor(cu);
		
		cu.accept(mv);
		
		ArrayList<ASTNode> nodes = mv.getNodes();
		
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
		testMV.visit(methods.get(0));
		// Ensure there is only one node in the MethodVisitor
		assertEquals(1, testMV.getNodes().size());
		// Ensure that the node is AttributeA
		assertEquals(mv.getNodes().get(2), testMV.getNodes().get(0));	
		
		fail("Not yet implemented");
	}

	@Test
	void testIsClassified() {
		// Create a new MethodVisitor
		MethodVisitor testMV = new MethodVisitor(cu);
		// Assert a public method is false
		assertEquals(false, "False Method");
		// Assert a private method is true
		assertEquals(true, "True Method");
		
		fail("Not yet implemented");
	}

	@Test
	void testGetMethods() {
		assertEquals("", mv.getMethods().toString());
		fail("Not yet implemented");
	}

	@Test
	void testGetNodes() {
		assertEquals("", mv.getNodes().toString());
		fail("Not yet implemented");
	}

}
