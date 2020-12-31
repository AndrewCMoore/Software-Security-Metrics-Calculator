package Parser_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.jupiter.api.Test;

import ssmc.CAMValues;
import ssmc.DeclarationVisitor;

class DeclarationVisitorTest {
	
	private DeclarationVisitor dv;
	private CompilationUnit cu;
	private ArrayList<TypeDeclaration> types; 
	
	public DeclarationVisitorTest() throws CoreException {
		MainTest mt = new MainTest();
		
		types = new ArrayList<TypeDeclaration>();
		
		ICompilationUnit iCompilationUnit = mt.AccessTestClass().getCompilationUnit("Statement_Test.java");
		this.cu = CAMValues.parse(iCompilationUnit);
		this.dv = new DeclarationVisitor(cu);
		cu.accept(dv);
		
		ArrayList<ASTNode> nodes = dv.getNodes();
		
		for(Object o: nodes) {
			if(o instanceof TypeDeclaration) {
				types.add((TypeDeclaration) o);
			} 
		}
	}

	@Test
	void testVisitTypeDeclaration() {
		// Create a new DeclarationVisitor
		DeclarationVisitor testDV = new DeclarationVisitor(cu);
		// Visit a TypeDeclaration in the DeclarationVisitor
		testDV.visit(types.get(0));
		// Ensure there is only one node in DeclarationVisitor
		assertEquals(1, testDV.getNodes().size());
		// Ensure that the node is TypeDeclaration
		assertEquals(dv.getNodes().get(0), testDV.getNodes().get(0));
		
		fail("Not yet implemented");
	}

	@Test
	void testGetClasses() {
		assertEquals("", dv.getClasses().toString());
		fail("Not yet implemented");
	}

	@Test
	void testGetNodes() {
		assertEquals("", dv.getNodes().toString());
		fail("Not yet implemented");
	}

}
