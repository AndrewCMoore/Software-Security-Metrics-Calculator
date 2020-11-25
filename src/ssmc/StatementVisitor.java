package ssmc;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.SwitchCase;

public class StatementVisitor extends ASTVisitor {

	private CompilationUnit compilationUnit;
	private double complexity;
	private ChildListPropertyDescriptor childList;
	
	
	public StatementVisitor(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		this.complexity = 0.0;
	}
	
	public boolean visit(DoStatement node) {
		getChildren1(node);	
		System.out.println("We are in the DoStatement node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		return false;
	}
	public boolean visit(ForStatement node) {
		getChildren1(node);
		System.out.println("We are in the ForStatement node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		return false;
	}
	public boolean visit(IfStatement node) {
		getChildren1(node);
		System.out.println("We are in the IfStatement node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		return false;
	}
	public boolean visit(SwitchCase node) {
		
		System.out.println("We are in the SwitchCase node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		getChildren1(node);
		return false;
	}
	public boolean visit(SwitchStatement node) {
		getChildren1(node);
		System.out.println("We are in the SwitchStatement node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		return false;
	}
	public boolean visit(WhileStatement node) {
		getChildren1(node);
		System.out.println("We are in the WhileStatement node on line " + compilationUnit.getLineNumber(node.getStartPosition()));
		return false;
	}
	public String toString() {
		return "";
	}
	
	//public subNodes()
	
	public Object[] getChildren(ASTNode node) {
	    List list= node.structuralPropertiesForType();
	    for (int i= 0; i < list.size(); i++) {
	        StructuralPropertyDescriptor curr= (StructuralPropertyDescriptor) list.get(i);
	            Object child= node.getStructuralProperty(curr);
	        if (child instanceof List) {
	                return ((List) child).toArray();
	        } else if (child instanceof ASTNode) {
	            return new Object[] { child };
	            }
	        return new Object[0];
	    }
		return null;
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
	
	public void getChildren1(ASTNode node) {
	    if (node != null) {
	        List<ASTNode> children = new ArrayList<ASTNode>();
	        List list = node.structuralPropertiesForType();
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
	                System.out.println(("Children Node: " + c + "\n"));
	                getChildren1(node1);
	            } 
	        }
	    }else {
	        return; 
	    }       
	}
}

