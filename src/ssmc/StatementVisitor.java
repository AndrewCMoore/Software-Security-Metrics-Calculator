package ssmc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
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
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.SwitchCase;

public class StatementVisitor extends ASTVisitor {

	private CompilationUnit compilationUnit;
	private ArrayList<ASTNode> nodes;
	private ArrayList<Statement> statementList;
	
	public StatementVisitor(CompilationUnit compilationUnit) {
		super();
		this.statementList = new ArrayList<Statement>();
		this.compilationUnit = compilationUnit;
		this.nodes = new ArrayList<ASTNode>();
	}
	
	public ArrayList<Statement> getArrayList() {
		return this.statementList;
	}

	public Object[] getChildren(ASTNode node) {
		Object child;
	    List list= node.structuralPropertiesForType();
	    for (Object element : list) {
	        StructuralPropertyDescriptor curr= (StructuralPropertyDescriptor) element;
	            child= node.getStructuralProperty(curr);
	        if (child instanceof List) {
	                return ((List) child).toArray();
	        } else if (child instanceof ASTNode) {
	            return new Object[] { child };
	            }
	        return new Object[0];
	    }
		return null;
	}
	public void callNode(ASTNode node) {
	    if (node != null) {
	        List<ASTNode> children = new ArrayList<ASTNode>();
	        List<?> list = node.structuralPropertiesForType();
	        for (int i = 0; i < list.size(); i++) {
	            Object child = node.getStructuralProperty((StructuralPropertyDescriptor) list.get(i));
	            if (child instanceof ASTNode) {
	                children.add((ASTNode) child);
	            }               
	        }
	        
	        for(ASTNode node1 : children){
	            if (node1 != null) {
	            	itterateNode(node1);
	                //callNode(node1);
	            } 
	            
	         }
	    } 
	}
	public ArrayList<ASTNode> getNodes(){
		return this.nodes;
	}
	public void itterateNode(ASTNode node) {
		if(this.getChildren(node).length != 0) {
			for(int i = 0; i < this.getChildren(node).length; i++) {
				ASTNode node1 = (ASTNode) this.getChildren(node)[i];
				switch(node1.getNodeType()) {
				case 12: 
					visit((CatchClause) node1);
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
					break;		
				}
			}
		}
	}
		
	public boolean visit(ContinueStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		callNode(node);
		return false;		
	}
	public boolean visit(DoStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		callNode(node);
		return false;
	}
	
	public boolean visit(ForStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		callNode(node);
		return false;
	}
	
	public int getOperators(Expression e) {
		if(e instanceof InfixExpression) {
			InfixExpression i = (InfixExpression) e;
			if(i.getOperator()!=null) {
				return 1 + getOperators(i.getLeftOperand()) + getOperators(i.getRightOperand());
			}
		}
		return 0;
	}
	
	public boolean visit(IfStatement node) {
		this.nodes.add(node);
		
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		List<?> list = node.structuralPropertiesForType();
		for(Object o : list) {
			StructuralPropertyDescriptor property = (StructuralPropertyDescriptor) o;
			Object child = node.getStructuralProperty(property);
            if (child instanceof Expression) {
                if(child instanceof InfixExpression) {
                	InfixExpression infix = (InfixExpression) child;
                	statement.addComplexity(getOperators(infix)-1);
                }
                
            } 
			
			
		}
		if(node.getElseStatement() != null) {
			if(node.getElseStatement() instanceof Block) {}
			else {
				//System.out.println(node.getElseStatement());
				visit((IfStatement) node.getElseStatement());
				
			}			
		}
		callNode(node);
		//System.out.println("We are in the IfStatement node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		
		return false;
	}

	public boolean visit(SwitchStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		
		// Node.getStatements give us the list of cases, logic, and breaks.
		for(int i = 0; i < node.statements().size(); i++) {
			// For each switch case, null or full:
			if(node.statements().get(i) instanceof SwitchCase) {
				statement.addComplexity(1);
			}
		}
		
		statementList.add(statement);

		//System.out.println("The number of cases are " + switchCount);
		callNode(node);
		//System.out.println("We are in the SwitchStatement node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		return true;
	}
	
	public boolean visit(WhileStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		callNode(node);
		//System.out.println("We are in the WhileStatement node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		return false;
	}
	
	public boolean visit(ConditionalExpression node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		callNode(node);
		return true;
	}
	
	public boolean visit(EnhancedForStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		callNode(node);
		return false;
	}
	
	public boolean visit(ThrowStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		callNode(node);
		return true;
	}
	
	public boolean visit(TryStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		callNode(node);
		return true;
	}
	
	public boolean visit(CatchClause node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		callNode(node);
		return true;
	}
	
	public boolean visit(ReturnStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		//statement.addComplexity(1);
		statementList.add(statement);
		//callNode(node);
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

