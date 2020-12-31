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
		
		ICompilationUnit iCompilationUnit = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
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
		// Ensure that the node is TypeDeclaration Test_Class
		assertEquals(dv.getNodes().get(0), testDV.getNodes().get(0));
	}

	@Test
	void testGetClasses() {
		// The classes won't return normally.
		assertEquals("", dv.getClasses());
		fail("Test doesn't work as intended");	
	}

	@Test
	void testGetNodes() {
		assertEquals("[public class Test_Class {\n"
				+ "  static String attributeA;\n"
				+ "  public Test_Class(){\n"
				+ "    attributeA=\"00\";\n"
				+ "  }\n"
				+ "  public void methodA(){\n"
				+ "    attributeA=\"02\";\n"
				+ "    InnerLevel il=new InnerLevel();\n"
				+ "  }\n"
				+ "private final static class InnerLevel {\n"
				+ "    String attributeB;\n"
				+ "    public InnerLevel(){\n"
				+ "      attributeB=\"01\";\n"
				+ "    }\n"
				+ "  }\n"
				+ "}\n"
				+ ", private final static class InnerLevel {\n"
				+ "  String attributeB;\n"
				+ "  public InnerLevel(){\n"
				+ "    attributeB=\"01\";\n"
				+ "  }\n"
				+ "}\n"
				+ "]"
				, dv.getNodes().toString());
	}
}
