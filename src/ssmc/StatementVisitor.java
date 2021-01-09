package ssmc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.SwitchStatement;
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
	@SuppressWarnings("unchecked")
	public Object[] getChildren(ASTNode node) {
	    List<ASTNode> list= node.structuralPropertiesForType();
	    for (Object element : list) {
	        StructuralPropertyDescriptor curr= (StructuralPropertyDescriptor) element;
	            Object child= node.getStructuralProperty(curr);
	        if (child instanceof List) {
	                return ((List<ASTNode>) child).toArray();
	        } else if (child instanceof ASTNode) {
	            return new Object[] { child };
	            }
	        return new Object[0];
	    }
		return null;
	}
	public void getChildren1(ASTNode node) {
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
	            	
	                String c = children.toString();
	                //System.out.println(("Children Node: " + c + "\n"));
	                //getChildren1(node1);
	            } 
	            
	         }
	    }else {
	    	
	        return; 
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
				case 19: 
					visit((DoStatement) node1);
					break;
				case 24:
					visit((ForStatement) node1);
					break;
				case 25:
					visit((IfStatement) node1);
					break;
				case 49:
					visit((SwitchCase) node1);
					break;
				case 50:
					visit((SwitchStatement) node1);
					break;
				case 61: 
					visit((WhileStatement) node1);
					break;
				default:
					break;		
				}
			}
		}
	}
		
	public boolean visit(DoStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		getChildren1(node);
		return false;
	}
	
	public boolean visit(ForStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		getChildren1(node);
		return false;
	}
	
	public boolean visit(IfStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		if(node.getElseStatement() != null) {
			if(node.getElseStatement() instanceof Block) {}
			else {
				//System.out.println(node.getElseStatement());
				visit((IfStatement) node.getElseStatement());
			}			
		}
		getChildren1(node);
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
		getChildren1(node);
		//System.out.println("We are in the SwitchStatement node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		return true;
	}
	
	public boolean visit(WhileStatement node) {
		this.nodes.add(node);
		Statement statement = new Statement(node, this.compilationUnit);
		statement.addComplexity(1);
		statementList.add(statement);
		getChildren1(node);
		//System.out.println("We are in the WhileStatement node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		return false;
	}
}

