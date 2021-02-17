package ssmc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.SwitchCase;

public class StatementVisitor extends ASTVisitor {

	private CompilationUnit compilationUnit;
	private ArrayList<Integer> ids;
	private ArrayList<ASTNode> nodes;
	private ArrayList<Statement> statementList;
	
	/**
	 * Constructor for the class
	 * @param compilationUnit CompilationUnit
	 */
	public StatementVisitor(CompilationUnit compilationUnit) {
		// Calls the super method
		super();
		
		// Set class variables from parameters
		this.compilationUnit = compilationUnit;
			
		// Initialize ArrayLists 
		this.statementList = new ArrayList<Statement>();
		this.nodes = new ArrayList<ASTNode>();
		this.ids = new ArrayList<Integer>();
	}
	
	/**
	 * This method is called in nodes to get all of the children. 
	 * @param node ASTNode
	 * @return List of ASTNode objects
	 */
	public List<ASTNode> callNode(ASTNode node) {
		List<ASTNode> children = new ArrayList<ASTNode>();
	    if (node != null) {
	        List<?> list = node.structuralPropertiesForType();
	        for (int i = 0; i < list.size(); i++) {
	            Object child = node.getStructuralProperty((StructuralPropertyDescriptor) list.get(i));
	            if (child instanceof ASTNode) {
	                children.add((ASTNode) child);
	            }               
	        }
	        
	        for(ASTNode childNode : children){
	            if (childNode != null) {
	            	determineNodeChildType(childNode);
	            } 
	            
	         }
	    } 
	    return children;
	}

	
	/**
	 * Method confirms the ASTNode is not called twice for efficiency.
	 * @param node ASTNode
	 * @return boolean
	 */
	public boolean confirm(ASTNode node) {
		if(!ids.contains(System.identityHashCode(node))) {
			this.ids.add(System.identityHashCode(node));
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the Statement objects this Visitor has visited
	 * @return ArrayList of Statement objects
	 */
	public ArrayList<Statement> getArrayList() {
		return this.statementList;
	}
	
	/**
	 * This method gets the children of the ASTNode
	 * @param node ASTNode
	 * @return Array of objects
	 */
	public Object[] getChildren(ASTNode node) {

	    List<ASTNode> list= node.structuralPropertiesForType();
	    for (Object element : list) {
	        StructuralPropertyDescriptor curr= (StructuralPropertyDescriptor) element;
	            Object child= node.getStructuralProperty(curr);
	        if (child instanceof List) {
	                return ((List) child).toArray();
	        } else if (child instanceof ASTNode) {
	        	confirm((ASTNode) child);
	            return new Object[] { child };
	            }
	        return new Object[0];
	    }
		return null;
	}
	
	
	/**
	 * Returns all the ASTNodes the class has visited
	 * @return ArrayList of ASTNode objects
	 */
	public ArrayList<ASTNode> getNodes(){
		return this.nodes;
	}
		
	/**
	 * Determines which type of ASTNode child to visit
	 * @param node ASTNode
	 */
	public void determineNodeChildType(ASTNode node){
		try {
			if(this.getChildren(node).length != 0) {
				for(int i = 0; i < this.getChildren(node).length; i++) {
					ASTNode node1 = (ASTNode) this.getChildren(node)[i];
					
					//System.out.println("Node " + node1.toString() + System.identityHashCode(node1));
					switch(node1.getNodeType()) {
					case 10:
						visit((BreakStatement) node1);
						break;
					case 16:
						visit((ConditionalExpression) node1);
						break;
					case 18: 
						visit((ContinueStatement) node1);
						break;
					case 19: 
						visit((DoStatement) node1);
						break;
					case 24:
						visit((ForStatement) node1);
						break;
					case 25:
						visit((IfStatement) node1);
						break;
					case 41: 
						visit((ReturnStatement) node1);
						break;
					case 49:
						visit((SwitchCase) node1);
						break;
					case 50:
						visit((SwitchStatement) node1);
						break;
					case 53: 
						visit((ThrowStatement) node1);
						break;
					case 54: 
						visit((TryStatement) node1);
						break;
					case 61: 
						visit((WhileStatement) node1);
						break;
					case 70:
						visit((EnhancedForStatement) node1);
						break;
					default:
						callNode(node1);
						break;		
					}
				}
			}
		} catch (NullPointerException e) {
			//e.printStackTrace();
		}
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the BreakStatement ASTNodes within the CompilationUnit. 
	 * BreakStatement ASTNodes follow the format of: 
	 * 		
	 *	BreakStatement:
     *		break [ Identifier ] ;
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(BreakStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statement.addCyclomaticComplexity(1);
		statement.addModifiedComplexity(1);
		statement.addStrictComplexity(1);
		statementList.add(statement);
		callNode(node);
		return true;
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the ConditionalExpression ASTNodes within the CompilationUnit. 
	 * ConditionalExpression ASTNodes follow the format of: 
	 * 		
	 *	ConditionalExpression:
     *		Expression ? Expression : Expression
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(ConditionalExpression node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statement.addCyclomaticComplexity(1);
		statement.addModifiedComplexity(1);
		statement.addStrictComplexity(1);
		statement.addCountPath(1);
		statementList.add(statement);
		callNode(node);
		return true;
	}

	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the ContinueStatement ASTNodes within the CompilationUnit. 
	 * ContinueStatment ASTNodes follow the format of: 
	 * 		
	 *	ContinueStatement:
     * 	continue [ Identifier ] ;
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(ContinueStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statement.addCyclomaticComplexity(1);
		statement.addModifiedComplexity(1);
		statement.addStrictComplexity(1);
		statementList.add(statement);
		callNode(node);
		return true;		
	}

	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the DoStatement ASTNodes within the CompilationUnit. 
	 * DoStatment ASTNodes follow the format of: 
	 * 		
	 *	 DoStatement:
     *	 	do Statement while ( Expression ) ;
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(DoStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statement.addCyclomaticComplexity(1);
		statement.addModifiedComplexity(1);
		statement.addStrictComplexity(1);
		statement.addCountPath(1);
		statement.checkExpressionForOperators();
		statementList.add(statement);

		callNode(node);
		return true;
	}

	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the EnhancedForStatement ASTNodes within the CompilationUnit. 
	 * EnhancedForStatment ASTNodes follow the format of: 
	 * 		
	 *	 EnhancedForStatement:
     *	 	for ( FormalParameter : Expression )
     *	 		Statement
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(EnhancedForStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statement.addCyclomaticComplexity(1);
		statement.addModifiedComplexity(1);
		statement.addStrictComplexity(1);
		statement.addCountPath(1);
		statement.checkExpressionForOperators();
		statementList.add(statement);
		
		callNode(node);
		return true;
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the ForStatement ASTNodes within the CompilationUnit. 
	 * ForStatment ASTNodes follow the format of: 
	 * 		
	 *	ForStatement:
     *  	for (
     *			[ ForInit ];
     *			[ Expression ] ;
     *			[ ForUpdate ] )
     *			Statement
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(ForStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statement.addCyclomaticComplexity(1);
		statement.addModifiedComplexity(1);
		statement.addStrictComplexity(1);
		statement.addCountPath(1);
		statement.checkExpressionForOperators();
		statementList.add(statement);
		
		callNode(node);
		return true;
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the IfStatement ASTNodes within the CompilationUnit. 
	 * IfStatment ASTNodes follow the format of: 
	 * 		
	 *	IfStatement:
     *  	if ( Expression ) Statement [ else Statement]
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(IfStatement node) {
		
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statement.addCyclomaticComplexity(1);
		statement.addModifiedComplexity(1);
		statement.addStrictComplexity(1);
		statement.addCountPath(1);
		statement.checkExpressionForOperators();
		statementList.add(statement);
		
		if(node.getElseStatement() != null) {
			if(node.getElseStatement() instanceof Block) {
				statement.addComplexity(1);
				statement.addCyclomaticComplexity(1);
				statement.addModifiedComplexity(1);
				statement.addStrictComplexity(1);
				statement.addCountPath(1);
			}
			else {
				if(node.getElseStatement() instanceof IfStatement) {
					visit((IfStatement) node.getElseStatement());
				}
			}
		}
		
		callNode(node);
		return true;
	}
		
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the ReturnStatement ASTNodes within the CompilationUnit. 
	 * ReturnStatment ASTNodes follow the format of: 
	 * 		
	 *	ReturnStatement:
     *  	return [ Expression ] ;
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(ReturnStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statementList.add(statement);
		return true;
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the SwitchStatement ASTNodes within the CompilationUnit. 
	 * SwitchStatment ASTNodes follow the format of: 
	 * 		
	 *	SwitchStatement:
     *          switch ( Expression )
     *                  { { SwitchCase | Statement } }
     *  SwitchCase:
     *          case Expression  :
     *          default :
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(SwitchStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addModifiedComplexity(1);
		
		// Node.getStatements give us the list of cases, logic, and breaks.
		for(int i = 0; i < node.statements().size(); i++) {
			// For each switch case, null or full:
			if(node.statements().get(i) instanceof SwitchCase) {
				statement.checkExpressionForOperators();
				statement.addComplexity(1);
				statement.addCyclomaticComplexity(1);
				statement.addStrictComplexity(1);
				statement.addCountPath(1);
			}
		}
		
		statementList.add(statement);

		callNode(node);
		return true;
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the ThrowStatement ASTNodes within the CompilationUnit. 
	 * ThrowStatment ASTNodes follow the format of: 
	 * 		
	 *	ThrowStatement:
     *		throw Expression ;
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(ThrowStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		//statement.addComplexity(1);
		statementList.add(statement);
		callNode(node);
		return true;
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the TryStatement ASTNodes within the CompilationUnit. 
	 * TryStatment ASTNodes follow the format of: 
	 * 		
	 *	TryStatement:
     *		try [ ( Resources ) ]
     *    		Block
     *    		[ { CatchClause } ]
     *    		[ finally Block ]
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(TryStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statement.addCyclomaticComplexity(1);
		statement.addModifiedComplexity(1);
		statement.addStrictComplexity(1);
		statementList.add(statement);
		callNode(node);
		return true;
	}
	
	/**
	 * This method is an overwritten method from the super class ASTVisitor. 
	 * 
	 * This method visits all the While	Statement ASTNodes within the CompilationUnit. 
	 * WhileStatment ASTNodes follow the format of: 
	 * 		
	 *	WhileStatement:
     *		while ( Expression ) Statement
	 *
	 * This method determines key information of the Statement in the 
	 * CompilationUnt. It then creates a new Statement object and sets 
	 * these values within the class.
	 * 
	 * Returns true to search for a child ASTNode.
	 */
	public boolean visit(WhileStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statement.addCyclomaticComplexity(1);
		statement.addModifiedComplexity(1);
		statement.addStrictComplexity(1);
		statement.addCountPath(1);
		statement.checkExpressionForOperators();
		System.out.println("+" + statement.getComplexity() +" complexity in: " + statement.getClass() + "from " + statement.toString());
		statementList.add(statement);
		
		
		callNode(node);
		return true;

	}	
}

