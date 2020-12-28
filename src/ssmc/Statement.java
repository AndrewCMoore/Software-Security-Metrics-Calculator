package ssmc;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class Statement {
	private ASTNode nodeType;
	private CompilationUnit compilationUnit;
	private int complexityValue; 
	private int startLine;
	private int endLine;
	
	public Statement(ASTNode node, CompilationUnit compilationUnit) {
		this.nodeType = node;
		this.compilationUnit = compilationUnit; 
		this.complexityValue = 0;
		this.startLine = this.compilationUnit.getLineNumber(node.getStartPosition());
		this.endLine = this.compilationUnit.getColumnNumber(node.getStartPosition() + node.getLength());
	}
	
	public void addComplexity(int value) {
		this.complexityValue = this.complexityValue + value;
	}
	public ASTNode getNode() {
		return this.nodeType;
	}
	public CompilationUnit getCompilationUnit() {
		return this.compilationUnit;
	}
	public int getComplexity() {
		return this.complexityValue;
	}
	public int getStartLine() {
		return this.startLine;
	}
	public int getEndLine() {
		return this.endLine;
	}
}
