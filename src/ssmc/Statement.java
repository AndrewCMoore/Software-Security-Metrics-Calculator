package ssmc;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

public class Statement {
	private CompilationUnit compilationUnit;
	private int complexityValue;
	private int endLine; 
	private ASTNode node;
	private int startLine;
	
	/**
	 * Constructor for class
	 * @param node ASTNode
	 * @param compilationUnit CompilationUnit
	 */
	public Statement(ASTNode node, CompilationUnit compilationUnit) {
		this.node = node;
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
		return this.node;
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
		return "Statement [nodeType=" + node + ", compilationUnit=" + ", complexityValue="
				+ complexityValue + ", startLine=" + startLine + ", endLine=" + endLine + "]";
	}
	
	public void checkExpressionForOperators() {
		List<?> list = node.structuralPropertiesForType();
		for (Object o : list) {
			StructuralPropertyDescriptor property = (StructuralPropertyDescriptor) o;
			Object child = node.getStructuralProperty(property);
			if (child instanceof Expression) {
				//System.out.println(child.getClass().getName());
				//System.out.println(child);
				if (child instanceof InfixExpression) {
					InfixExpression infix = (InfixExpression) child;
					//System.out.println(getOperators(infix));
					this.addComplexity(getOperators(infix));
				}
			}
		}
	}
	
	private int getOperators(Expression e) {
		if (e instanceof InfixExpression) {
			InfixExpression i = (InfixExpression) e;
			if (i.getOperator() != null) {
				if (checkLogic(i.getOperator())) {
					return 1 + getOperators(i.getLeftOperand()) + getOperators(i.getRightOperand());
				} else {
					return getOperators(i.getLeftOperand()) + getOperators(i.getRightOperand());
				}

			}
		}
		return 0;
	}
	
	private boolean checkLogic(InfixExpression.Operator o) {

		if (o == InfixExpression.Operator.CONDITIONAL_OR || o == InfixExpression.Operator.CONDITIONAL_AND) {
			return true;
		}

		return false;
	}
}
