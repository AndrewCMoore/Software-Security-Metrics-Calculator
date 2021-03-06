package Parser_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.junit.jupiter.api.Test;

import ssmc.CAMValues;
import ssmc.StatementVisitor;

class StatementVisitorTest {

	private CompilationUnit cu;
	private StatementVisitor sv;
	private ArrayList<DoStatement> doStatements;  
	private ArrayList<Statement> forStatements;
	private ArrayList<IfStatement> ifStatements;
	private ArrayList<SwitchStatement> switchStatements;
	private ArrayList<WhileStatement> whileStatements;
	private ArrayList<ConditionalExpression> conditionals;
	private ArrayList<TryStatement> trys;
	private ArrayList<ThrowStatement> Throws;
	private ArrayList<CatchClause> catches;
	private ArrayList<ReturnStatement> returns;
	
	public StatementVisitorTest() throws CoreException {
		MainTest mt = new MainTest();
		
		this.doStatements = new ArrayList<DoStatement>();
		this.forStatements = new ArrayList<Statement>();
		this.ifStatements = new ArrayList<IfStatement>();
		this.switchStatements = new ArrayList<SwitchStatement>();
		this.whileStatements = new ArrayList<WhileStatement>();
		this.conditionals = new ArrayList<ConditionalExpression>();
		this.trys = new ArrayList<TryStatement>();
		this.Throws = new ArrayList<ThrowStatement>();
		this.catches = new ArrayList<CatchClause>();
		this.returns = new ArrayList<ReturnStatement>();
		
		ICompilationUnit iCompilationUnit = mt.AccessTestClass().getCompilationUnit("Statement_Test.java");
		this.cu = CAMValues.parse(iCompilationUnit);
		this.sv = new StatementVisitor(cu);
		cu.accept(sv);
		
		ArrayList<ASTNode> nodes = sv.getNodes();
		
		for(ASTNode node : nodes) {
	
			switch(node.getNodeType()){
				case 12: 
					this.catches.add((CatchClause) node);
					break;
				case 16:
					this.conditionals.add((ConditionalExpression) node);
					break;
				case 19: 
					this.doStatements.add((DoStatement) node);
					break;
				case 24:
					this.forStatements.add((ForStatement) node);
					break;
				case 25:
					this.ifStatements.add((IfStatement) node);
					break;
				case 41: 
					this.returns.add((ReturnStatement) node);
					break;
				case 50:
					this.switchStatements.add((SwitchStatement) node);
					break;
				case 53: 
					this.Throws.add((ThrowStatement) node);
					break;
				case 54: 
					this.trys.add((TryStatement) node);
					break;
				case 61: 
					this.whileStatements.add((WhileStatement) node);
					break;
				case 70:
					this.forStatements.add((EnhancedForStatement) node);
					break;
				default:
					break;		
			}
		}		
	}

	@Test
	void testGetArrayList() {
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
				+ ", for (int i : numbers) {\n"
				+ "  if (i > maxValue) {\n"
				+ "    maxValue=i;\n"
				+ "  }\n"
				+ "}\n"
				+ ", if (i > maxValue) {\n"
				+ "  maxValue=i;\n"
				+ "}\n"
				+ ", isTrue ? 5 : 4, if (i < 10) {\n"
				+ "  return true;\n"
				+ "}\n"
				+ ", return true;\n"
				+ ", return false;\n"
				+ ", throw new NullPointerException(\"demo\");\n"
				+ ", try {\n"
				+ "}\n"
				+ " catch (Exception e) {\n"
				+ "}\n"
				+ "]"
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
				+ ", for (int i : numbers) {\n"
				+ "  if (i > maxValue) {\n"
				+ "    maxValue=i;\n"
				+ "  }\n"
				+ "}\n"
				+ ", if (i > maxValue) {\n"
				+ "  maxValue=i;\n"
				+ "}\n"
				+ ", isTrue ? 5 : 4, if (i < 10) {\n"
				+ "  return true;\n"
				+ "}\n"
				+ ", return true;\n"
				+ ", return false;\n"
				+ ", throw new NullPointerException(\"demo\");\n"
				+ ", try {\n"
				+ "}\n"
				+ " catch (Exception e) {\n"
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
		testSV.visit((ForStatement) forStatements.get(0));
		// Ensure there is only one node in StatementVisitor
		assertEquals(1, testSV.getNodes().size());
		// Ensure that the node is doStatement
		assertEquals(sv.getNodes().get(2), testSV.getNodes().get(0));
	}

	@Test
	void testForEachStatement() {
		// Create a new StatementVisitor
		StatementVisitor testSV = new StatementVisitor(cu);
		// Visit a DoStatment ASTNode in StatementVisitor
		testSV.visit((EnhancedForStatement) forStatements.get(1));
		// Ensure there is only one node in StatementVisitor
		assertEquals(2, testSV.getNodes().size());
		// Ensure that the node is doStatement
		assertEquals(sv.getNodes().get(5), testSV.getNodes().get(0));
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
		System.out.println(sv.getNodes().size());
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
	
	@Test
	void testConditionalExpression() {
		// Create a new StatementVisitor
		StatementVisitor testSV = new StatementVisitor(cu);
		// Visit a DoStatment ASTNode in StatementVisitor
		testSV.visit(conditionals.get(0));
		// Ensure there is only one node in StatementVisitor
		assertEquals(1, testSV.getNodes().size());
		// Ensure that the node is doStatement
		assertEquals(sv.getNodes().get(7), testSV.getNodes().get(0));
	}
	
	@Test
	void testTryStatement() {
		StatementVisitor testSV = new StatementVisitor(cu);
		testSV.visit(trys.get(0));
		assertEquals(1, testSV.getNodes().size());
		assertEquals(sv.getNodes().get(18), testSV.getNodes().get(0));
	}
	
	// Need to fix this implementation: Gets all of the returns statemetns, we only want them if they aren't the last in the method.
	@Test
	void testReturnStatement() {
		StatementVisitor testSV = new StatementVisitor(cu);
		System.out.print("SAD " + returns.toString());
	}
	
	@Test
	void testThrowStatement() {
		StatementVisitor testSV = new StatementVisitor(cu);
		testSV.visit(Throws.get(0));
		assertEquals(1, testSV.getNodes().size());
		assertEquals(sv.getNodes().get(17), testSV.getNodes().get(0));
	}

}
