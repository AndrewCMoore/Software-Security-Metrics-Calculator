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
		
		return false;
	}
	
	
	
	
}
