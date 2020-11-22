package ssmc;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

public class StatementVisitor extends ASTVisitor {

	private CompilationUnit compilationUnit;
	private double complexity;
	
	public StatementVisitor(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		this.complexity = 0.0;
	}
	
	public boolean visit(DoStatement node) {
		return false;
	}
	public boolean visit(ForStatement node) {
		return false;
	}
	public boolean visit(IfStatement node) {
		return false;
	}
	public boolean visit(SwitchStatement node) {
		return false;
	}
	public boolean visit(WhileStatement node) {
		return false;
	}
	public String toString() {
		return "";
	}
	

}
