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

	private StatementVisitor sv;
	private CompilationUnit cu;
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
		
		ICompilationUnit iCompilationUnit = mt.AccessTestClass().getCompilationUnit("");
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
	void testVisitDoStatement() {
		fail("Not yet implemented");
	}

	@Test
	void testVisitForStatement() {
		fail("Not yet implemented");
	}

	@Test
	void testVisitIfStatement() {
		fail("Not yet implemented");
	}

	@Test
	void testVisitSwitchStatement() {
		fail("Not yet implemented");
	}

	@Test
	void testVisitWhileStatement() {
		fail("Not yet implemented");
	}

	@Test
	void testGetChildren() {
		fail("Not yet implemented");
	}

	@Test
	void testItterateNode() {
		fail("Not yet implemented");
	}

	@Test
	void testGetChildren1() {
		fail("Not yet implemented");
	}

	@Test
	void testGetArrayList() {
		fail("Not yet implemented");
	}

}
