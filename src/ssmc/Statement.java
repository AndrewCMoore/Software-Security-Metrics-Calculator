package ssmc;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class Statement {
	private CompilationUnit compilationUnit;
	private int complexityValue;
	private int endLine; 
	private ASTNode nodeType;
	private int startLine;
	
	/**
	 * Constructor for class
	 * @param node ASTNode
	 * @param compilationUnit CompilationUnit
	 */
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
	
	/**
	 * Adds a specified amount of complexity to the Statement.
	 * @param value int
	 */
	public void addComplexity(int value) {
		this.complexityValue = this.complexityValue + value;
	}
	
	/**
	 * Returns the CompilationUnit of this Statement
	 * @return CompilationUnit
	 */
	public CompilationUnit getCompilationUnit() {
		return this.compilationUnit;
	}
	
	/**
	 * Returns the complexity of this Statement
	 * @return int
	 */
	public int getComplexity() {
		return this.complexityValue;
	}
	
	/**
	 * Returns the end line of this Statement
	 * @return int
	 */
	public int getEndLine() {
		return this.endLine;
	}
	
	/**
	 * Returns the ASTNode type for this Statement
	 * @return ASTNode
	 */
	public ASTNode getNode() {
		return this.nodeType;
	}
	
	/**
	 * Returns the start line of this Statement
	 * @return int
	 */
	public int getStartLine() {
		return this.startLine;
	}


	/**
	 * ToString method for development purposes
	 */
	public String toString() {
		return "Statement [nodeType=" + nodeType + ", compilationUnit=" + ", complexityValue="
				+ complexityValue + ", startLine=" + startLine + ", endLine=" + endLine + "]";
	}
	
}
