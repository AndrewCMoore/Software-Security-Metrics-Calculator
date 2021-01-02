package Parser_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.junit.jupiter.api.Test;

import ssmc.CAMValues;
import ssmc.StatementVisitor;

class StatementVisitorTest {

	private CompilationUnit cu;
	private StatementVisitor sv;
	private ArrayList<DoStatement> doStatements;  
	private ArrayList<ForStatement> forStatements;
	private ArrayList<IfStatement> ifStatements;
	private ArrayList<SwitchStatement> switchStatements;
	private ArrayList<WhileStatement> whileStatements;
	
	public StatementVisitorTest() throws CoreException {
		MainTest mt = new MainTest();
		
		this.doStatements = new ArrayList<DoStatement>();
		this.forStatements = new ArrayList<ForStatement>();
		this.ifStatements = new ArrayList<IfStatement>();
		this.switchStatements = new ArrayList<SwitchStatement>();
		this.whileStatements = new ArrayList<WhileStatement>();
		
		ICompilationUnit iCompilationUnit = mt.AccessTestClass().getCompilationUnit("Statement_Test.java");
		this.cu = CAMValues.parse(iCompilationUnit);
		this.sv = new StatementVisitor(cu);
		cu.accept(sv);
		
		ArrayList<ASTNode> nodes = sv.getNodes();
		
		for(Object o: nodes) {
			if(o instanceof DoStatement) {
				doStatements.add((DoStatement) o);
			} 
			if(o instanceof ForStatement) {
				forStatements.add((ForStatement) o);
			}
			if(o instanceof IfStatement) {
				ifStatements.add((IfStatement) o);
			}
		    if(o instanceof SwitchStatement) {
				switchStatements.add((SwitchStatement) o);
			}
			if(o instanceof WhileStatement) {
				whileStatements.add((WhileStatement) o);
			}
		}
		
		
	}
	@Test
	void testGetArrayList() {
		assertEquals(
				"[Statement [nodeType=if (x == 0) {\n"
				+ "  do {\n"
				+ "  }\n"
				+ " while (x == 1);\n"
				+ "  for (int i=0; i < 1; i++) {\n"
				+ "  }\n"
				+ "switch (x) {\n"
				+ "  }\n"
				+ "  while (x == 1) {\n"
				+ "  }\n"
				+ "}\n"
				+ ", compilationUnit=public class Statement_Test {\n"
				+ "  Statement_Test(){\n"
				+ "    int x=0;\n"
				+ "    if (x == 0) {\n"
				+ "      do {\n"
				+ "      }\n"
				+ " while (x == 1);\n"
				+ "      for (int i=0; i < 1; i++) {\n"
				+ "      }\n"
				+ "switch (x) {\n"
				+ "      }\n"
				+ "      while (x == 1) {\n"
				+ "      }\n"
				+ "    }\n"
				+ "  }\n"
				+ "}\n"
				+ ", complexityValue=1, startLine=5, endLine=10], Statement [nodeType=do {\n"
				+ "}\n"
				+ " while (x == 1);\n"
				+ ", compilationUnit=public class Statement_Test {\n"
				+ "  Statement_Test(){\n"
				+ "    int x=0;\n"
				+ "    if (x == 0) {\n"
				+ "      do {\n"
				+ "      }\n"
				+ " while (x == 1);\n"
				+ "      for (int i=0; i < 1; i++) {\n"
				+ "      }\n"
				+ "switch (x) {\n"
				+ "      }\n"
				+ "      while (x == 1) {\n"
				+ "      }\n"
				+ "    }\n"
				+ "  }\n"
				+ "}\n"
				+ ", complexityValue=1, startLine=6, endLine=6], Statement [nodeType=for (int i=0; i < 1; i++) {\n"
				+ "}\n"
				+ ", compilationUnit=public class Statement_Test {\n"
				+ "  Statement_Test(){\n"
				+ "    int x=0;\n"
				+ "    if (x == 0) {\n"
				+ "      do {\n"
				+ "      }\n"
				+ " while (x == 1);\n"
				+ "      for (int i=0; i < 1; i++) {\n"
				+ "      }\n"
				+ "switch (x) {\n"
				+ "      }\n"
				+ "      while (x == 1) {\n"
				+ "      }\n"
				+ "    }\n"
				+ "  }\n"
				+ "}\n"
				+ ", complexityValue=1, startLine=7, endLine=7], Statement [nodeType=switch (x) {\n"
				+ "}\n"
				+ ", compilationUnit=public class Statement_Test {\n"
				+ "  Statement_Test(){\n"
				+ "    int x=0;\n"
				+ "    if (x == 0) {\n"
				+ "      do {\n"
				+ "      }\n"
				+ " while (x == 1);\n"
				+ "      for (int i=0; i < 1; i++) {\n"
				+ "      }\n"
				+ "switch (x) {\n"
				+ "      }\n"
				+ "      while (x == 1) {\n"
				+ "      }\n"
				+ "    }\n"
				+ "  }\n"
				+ "}\n"
				+ ", complexityValue=0, startLine=8, endLine=8], Statement [nodeType=while (x == 1) {\n"
				+ "}\n"
				+ ", compilationUnit=public class Statement_Test {\n"
				+ "  Statement_Test(){\n"
				+ "    int x=0;\n"
				+ "    if (x == 0) {\n"
				+ "      do {\n"
				+ "      }\n"
				+ " while (x == 1);\n"
				+ "      for (int i=0; i < 1; i++) {\n"
				+ "      }\n"
				+ "switch (x) {\n"
				+ "      }\n"
				+ "      while (x == 1) {\n"
				+ "      }\n"
				+ "    }\n"
				+ "  }\n"
				+ "}\n"
				+ ", complexityValue=1, startLine=9, endLine=9]]"
				, sv.getArrayList().toString());
		
	}

	@Test
	void testGetChildren() {
		// Create a new StatementVisitor
		StatementVisitor testSV = new StatementVisitor(cu);
		// Get the parent node of the ifStatement in StatementVisitor
		ASTNode node = ifStatements.get(0).getParent();
		// Assert the two children
		assertEquals("int x=0;\n" , testSV.getChildren(node)[0].toString());
		assertEquals(
				"if (x == 0) {\n"
				+ "  do {\n"
				+ "  }\n"
				+ " while (x == 1);\n"
				+ "  for (int i=0; i < 1; i++) {\n"
				+ "  }\n"
				+ "switch (x) {\n"
				+ "  }\n"
				+ "  while (x == 1) {\n"
				+ "  }\n"
				+ "}\n"
				+ ""
				, testSV.getChildren(node)[1].toString());
		
	}

	@Test
	void testGetChildren1() {		
	}

	@Test 
	void testGetNode(){
		assertEquals(
				"[if (x == 0) {\n"
				+ "  do {\n"
				+ "  }\n"
				+ " while (x == 1);\n"
				+ "  for (int i=0; i < 1; i++) {\n"
				+ "  }\n"
				+ "switch (x) {\n"
				+ "  }\n"
				+ "  while (x == 1) {\n"
				+ "  }\n"
				+ "}\n"
				+ ", do {\n"
				+ "}\n"
				+ " while (x == 1);\n"
				+ ", for (int i=0; i < 1; i++) {\n"
				+ "}\n"
				+ ", switch (x) {\n"
				+ "}\n"
				+ ", while (x == 1) {\n"
				+ "}\n"
				+ "]"
				, sv.getNodes().toString());
	}
	
	@Test
	void testItterateNode() {
		
	}

	@Test
	void testVisitDoStatement() {
		// Create a new StatementVisitor
		StatementVisitor testSV = new StatementVisitor(cu);
		// Visit a DoStatment ASTNode in StatementVisitor
		testSV.visit(doStatements.get(0));
		// Ensure there is only one node in StatementVisitor
		assertEquals(1, testSV.getNodes().size());
		// Ensure that the node is doStatement
		assertEquals(sv.getNodes().get(1), testSV.getNodes().get(0));
	}

	@Test
	void testVisitForStatement() {
		// Create a new StatementVisitor
		StatementVisitor testSV = new StatementVisitor(cu);
		// Visit a DoStatment ASTNode in StatementVisitor
		testSV.visit(forStatements.get(0));
		// Ensure there is only one node in StatementVisitor
		assertEquals(1, testSV.getNodes().size());
		// Ensure that the node is doStatement
		assertEquals(sv.getNodes().get(2), testSV.getNodes().get(0));
	}

	@Test
	void testVisitIfStatement() {
		// Create a new StatementVisitor
		StatementVisitor testSV = new StatementVisitor(cu);
		// Visit a DoStatment ASTNode in StatementVisitor
		testSV.visit(ifStatements.get(0));
		// Ensure there is only one node in StatementVisitor
		assertEquals(5, testSV.getNodes().size());
		// Ensure that the node is doStatement
		assertEquals(sv.getNodes().get(0), testSV.getNodes().get(0));
	}

	@Test
	void testVisitSwitchStatement() {
		// Create a new StatementVisitor
		StatementVisitor testSV = new StatementVisitor(cu);
		// Visit a DoStatment ASTNode in StatementVisitor
		testSV.visit(switchStatements.get(0));
		// Ensure there is only one node in StatementVisitor
		assertEquals(1, testSV.getNodes().size());
		// Ensure that the node is doStatement
		assertEquals(sv.getNodes().get(3), testSV.getNodes().get(0));
	}

	@Test
	void testVisitWhileStatement() {
		// Create a new StatementVisitor
		StatementVisitor testSV = new StatementVisitor(cu);
		// Visit a DoStatment ASTNode in StatementVisitor
		testSV.visit(whileStatements.get(0));
		// Ensure there is only one node in StatementVisitor
		assertEquals(1, testSV.getNodes().size());
		// Ensure that the node is doStatement
		assertEquals(sv.getNodes().get(4), testSV.getNodes().get(0));
	}

}
