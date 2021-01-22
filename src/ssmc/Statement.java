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
		// Set the variables from parameter values
		this.node = node;
		this.compilationUnit = compilationUnit; 
		
		// Set the variables to base values
		this.complexityValue = 0;
		try {
			// Not all nodes have a .getLength method(), thus a try/catch is needed
			this.startLine = this.compilationUnit.getLineNumber(node.getStartPosition());
			this.endLine = this.compilationUnit.getLineNumber(node.getStartPosition() + node.getLength() - 1);
		} catch (NullPointerException e) {
			e.printStackTrace();
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
		// Create a list of the ASTNode structure 
		List<?> list = node.structuralPropertiesForType();
		// For each object in this list
		for (Object o : list) {
			// Get the properties of the object
			StructuralPropertyDescriptor property = (StructuralPropertyDescriptor) o;
			// Get the ASTNode's child from the property.
			Object child = node.getStructuralProperty(property);
			// If the ASTNode's child is a () 
			if (child instanceof Expression) {
				// If the ASTNode's child is a statement inside the ()
				if (child instanceof InfixExpression) {
					InfixExpression infix = (InfixExpression) child;
					// Add complexity to the Statement based on the operators inside the ()
					this.addComplexity(getOperators(infix));
				}
			}
		}
	}
	
	private int getOperators(Expression e) {
		// If the type of expression is an InfixExpression 
		if (e instanceof InfixExpression) {
			// Cast the expression to an object that represents the expression within the () of the method
			InfixExpression i = (InfixExpression) e;
			if (i.getOperator() != null) {
				// Ensure that the operators are ones that add complexity (|| or &&)
				if (checkLogic(i.getOperator())) {
					// Add complexity and check for more operators within the expression
					return 1 + getOperators(i.getLeftOperand()) + getOperators(i.getRightOperand());
				} else {
					// Check for more operators within the expression
					return getOperators(i.getLeftOperand()) + getOperators(i.getRightOperand());
				}
			}
		}
		return 0;
	}
	
	private boolean checkLogic(InfixExpression.Operator o) {
		// If the operator within the () of a statement is a '||' or '&&' 
		if (o == InfixExpression.Operator.CONDITIONAL_OR || o == InfixExpression.Operator.CONDITIONAL_AND) {
			return true;
		}

		return false;
	}
}
