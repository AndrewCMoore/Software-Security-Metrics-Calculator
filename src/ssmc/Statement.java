package ssmc;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class Statement {
	private CompilationUnit compilationUnit;
	private int complexityValue;
	private int endLine; 
	private ASTNode nodeType;
	private int startLine;
	
	public Statement(ASTNode node, CompilationUnit compilationUnit) {
		this.nodeType = node;
		this.compilationUnit = compilationUnit; 
		this.complexityValue = 0;
		try {
			this.startLine = this.compilationUnit.getLineNumber(node.getStartPosition());
			this.endLine = this.compilationUnit.getLineNumber(node.getStartPosition() + node.getLength() - 1);
		} catch (NullPointerException e) {
			
		}
		
	}
	
	public void addComplexity(int value) {
		this.complexityValue = this.complexityValue + value;
	}
	public CompilationUnit getCompilationUnit() {
		return this.compilationUnit;
	}
	public int getComplexity() {
		return this.complexityValue;
	}
	public int getEndLine() {
		return this.endLine;
	}
	public ASTNode getNode() {
		return this.nodeType;
	}
	public int getStartLine() {
		return this.startLine;
	}

	public String toString() {
		return this.nodeType.toString();
	}
	
/*
	@Override
	public String toString() {
		return "Statement [nodeType=" + nodeType + "| | | | | |" +  this.compilationUnit.getParent();
				//+  ", compilationUnit=" +  ", complexityValue="
				//+ complexityValue + ", startLine=" + startLine + ", endLine=" + endLine + "]";
	}
	*/
}
