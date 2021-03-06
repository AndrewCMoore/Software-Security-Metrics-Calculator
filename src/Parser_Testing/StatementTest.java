package Parser_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IfStatement;
import org.junit.jupiter.api.Test;

import ssmc.CAMValues;
import ssmc.Statement;

class StatementTest {
	private ICompilationUnit iCompilationUnit;
	private Statement ifStatement;
	private ArrayList<Statement> statements;
	public StatementTest() throws CoreException {
		MainTest mt = new MainTest();
		this.iCompilationUnit = mt.AccessTestClass().getCompilationUnit("Statement_Test.java");
		
		statements = CAMValues.generateStatementAST(iCompilationUnit);
		this.ifStatement = statements.get(0);
		
		System.out.println(statements.toString());
	}
	
	@Test
	void testAddComplexity() {
		this.ifStatement.addComplexity(2);
		assertEquals(3, this.ifStatement.getComplexity());
	}
	
	@Test
	void testGetCompilationUnit() {
		CompilationUnit cu = CAMValues.parse(this.iCompilationUnit);
		assertEquals(cu.getFlags(), this.ifStatement.getCompilationUnit().getFlags());
		assertEquals(cu.getCommentList().toString(), this.ifStatement.getCompilationUnit().getCommentList().toString());
		assertEquals(cu.getLength(), this.ifStatement.getCompilationUnit().getLength());
		assertEquals(cu.getParent(), this.ifStatement.getCompilationUnit().getParent());
	}
	
	@Test
	void testGetComplexity() {
		assertEquals(1, this.ifStatement.getComplexity());
	}
	 
	@Test
	void testGetEndLine() {
		assertEquals(10, this.ifStatement.getEndLine());
	}

	@Test
	void testGetNode() {
		ArrayList<Statement> statement = CAMValues.generateStatementAST(this.iCompilationUnit);
		assertEquals(statement.get(0).getNode().properties() ,ifStatement.getNode().properties());
	}
	
	@Test
	void testGetStartLine() {
		assertEquals(5, this.ifStatement.getStartLine());
	}
	
	@Test 
	void testToString() {
		this.ifStatement.toString();
	}
}
