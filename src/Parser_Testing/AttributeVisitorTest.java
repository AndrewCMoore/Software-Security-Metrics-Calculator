package Parser_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.junit.jupiter.api.Test;

import ssmc.AttributeVisitor;
import ssmc.CAMValues;

class AttributeVisitorTest {
	
	private AttributeVisitor av;
	private CompilationUnit cu;
	private ArrayList<SimpleName> simplenames;
	private ArrayList<VariableDeclarationFragment> variables;
	
	public AttributeVisitorTest() throws CoreException {
		MainTest mt = new MainTest();
		this.simplenames = new ArrayList<SimpleName>();
		this.variables = new ArrayList<VariableDeclarationFragment>();
		ICompilationUnit iCompilationUnit = mt.AccessTestClass().getCompilationUnit("Attribute_Test.java");
		cu = CAMValues.parse(iCompilationUnit);
		av = new AttributeVisitor(cu);

		cu.accept(av);
		
		ArrayList<ASTNode> nodes = av.getNodes();
		
		for(Object o: nodes) {
			if(o instanceof SimpleName) {
				this.simplenames.add((SimpleName) o);
			}
			if(o instanceof VariableDeclarationFragment) {
				this.variables.add((VariableDeclarationFragment) o);
			}
		}
	}
	
	
	@Test
	void testGetArrayList() {
		//System.out.println(av.getArrayList());
		assertEquals("[Attribute [Identifier=AttributeA, modifier=Public , usage=1, finalized=false, compilationUnit=public class Attribute_Test {\n"
				+ "  public String AttributeA;\n"
				+ "  private static final int AttributeB=0;\n"
				+ "  protected transient volatile int AttributeC;\n"
				+ "  public Attribute_Test(){\n"
				+ "    int x=AttributeB;\n"
				+ "  }\n"
				+ "}\n"
				+ ", links=0, lineNum=3], Attribute [Identifier=AttributeB, modifier=Static Private , usage=2, finalized=true, compilationUnit=public class Attribute_Test {\n"
				+ "  public String AttributeA;\n"
				+ "  private static final int AttributeB=0;\n"
				+ "  protected transient volatile int AttributeC;\n"
				+ "  public Attribute_Test(){\n"
				+ "    int x=AttributeB;\n"
				+ "  }\n"
				+ "}\n"
				+ ", links=0, lineNum=4], Attribute [Identifier=AttributeC, modifier=Transient Volatile Protected , usage=1, finalized=false, compilationUnit=public class Attribute_Test {\n"
				+ "  public String AttributeA;\n"
				+ "  private static final int AttributeB=0;\n"
				+ "  protected transient volatile int AttributeC;\n"
				+ "  public Attribute_Test(){\n"
				+ "    int x=AttributeB;\n"
				+ "  }\n"
				+ "}\n"
				+ ", links=0, lineNum=5], Attribute [Identifier=x, modifier=, usage=1, finalized=false, compilationUnit=public class Attribute_Test {\n"
				+ "  public String AttributeA;\n"
				+ "  private static final int AttributeB=0;\n"
				+ "  protected transient volatile int AttributeC;\n"
				+ "  public Attribute_Test(){\n"
				+ "    int x=AttributeB;\n"
				+ "  }\n"
				+ "}\n"
				+ ", links=0, lineNum=8]]"
				, av.getArrayList().toString());
	}

	@Test 
	void testGetNodes() {
		assertEquals("[Attribute_Test, String, AttributeA, AttributeA, AttributeB=0, AttributeB,"
				   + " AttributeC, AttributeC, Attribute_Test, x=AttributeB, x, AttributeB]"
				   , av.getNodes().toString());
	}
	
	@Test
	void testToString() {
		av.toString();
	}

	@Test
	void testVisitSimpleName() {
		// Assert the usage of AttributeB is 2
		assertEquals(2, av.getArrayList().get(1).getUsage());
		// Get the SimpleName of AttributeB 
		SimpleName simpleName = this.simplenames.get(3);
		// Call the Visit function, which increments the usage
		av.visit(simpleName);
		// Assert the usage of AttributeB is 3
		assertEquals(3, av.getArrayList().get(1).getUsage());
		// Reset back to normal
		av.getArrayList().get(1).setUsage(2);
	}

	@Test
	void testVisitVariableDeclarationFragment() {
		// Create a new AttributeVisitor
		AttributeVisitor testAV = new AttributeVisitor(this.cu);
		// Visit a VariableDeclarationNode on VaraibleA
		testAV.visit(this.variables.get(0));
		// Ensure is only node in the Attribute Visitor
		assertEquals(1, testAV.getNodes().size());
		// Ensure that the node is AttributeA
		assertEquals(av.getNodes().get(2), testAV.getNodes().get(0));		
	}

}
