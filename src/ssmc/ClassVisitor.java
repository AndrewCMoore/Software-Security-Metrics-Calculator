package ssmc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;

public class ClassVisitor extends ASTVisitor {

	private CompilationUnit compliationUnit;
	private Set<String> names;
	private List<Attribute> attributes;
	
	public ClassVisitor(CompilationUnit compilationUnit) {
		super();
		this.compliationUnit = compilationUnit;
		names = new HashSet<String>();
		attributes = new ArrayList<Attribute>();
	}
	
	public boolean visit(CompilationUnit node) {
		//System.out.println(node.types());
		getChildren1(node);
		return false;
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
	            	//itterateNode(node1);
	            	
	                String c = children.toString();
	                System.out.println(("Children Node: " + c + "\n"));
	                //getChildren1(node1);
	            } 
	            
	         }
	    }else {
	    	
	        return; 
	    }       
	}
}
