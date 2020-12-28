package tree;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import ssmc.*;
import ssmc.Class;

public class JDTree {

	private JDTree parent;
	private JDTree[] children;
	private NodeType type;
	private Object node;

	public JDTree(Object node, JDTree parent) throws JavaModelException {

		this.node = node;
		this.parent = parent;
		String kind = node.getClass().getName();
		if (kind.equals("org.eclipse.jdt.internal.core.JavaProject")) {
			IJavaProject project = (IJavaProject) node;
			type = NodeType.PACKAGE;
			IPackageFragment[] packages = project.getPackageFragments();
			children = new JDTree[packages.length];
			for (int i = 0; i < packages.length; i++) {
				children[i] = new JDTree(packages[i], this);
			}
		}
		if (kind.equals("org.eclipse.jdt.internal.core.PackageFragment")) {
			type = NodeType.PACKAGE;
			IPackageFragment pack = (IPackageFragment) node;
			ICompilationUnit[] units = pack.getCompilationUnits();
			children = new JDTree[units.length];
			for (int i = 0; i < units.length; i++) {
				children[i] = new JDTree(units[i], this);
			}
			// generateAST();
		}
		if (kind.equals("org.eclipse.jdt.internal.core.CompilationUnit")) {
			type = NodeType.COMPILATIONUNIT;
			ICompilationUnit comp = (ICompilationUnit) node;
			Class[] classes = CAMValues.getClasses(comp);
			if (classes != null) {
				children = new JDTree[classes.length];
				for (int i = 0; i < classes.length; i++) {
					children[i] = new JDTree(classes[i], this);
					System.out.println("the Class is "+classes[i].getIdentifier());
				}
			}
		}
		if (kind.equals("Class")) {
			type = NodeType.CLASS;

		}

	}

	public JDTree[] getLeefs() {
		if (type == NodeType.CLASS) {
			return children;
		} else {
			ArrayList<JDTree> leefs = new ArrayList<JDTree>();
			for (JDTree child : children) {
				JDTree[] childLeefs = child.getLeefs();
				for (JDTree leef : childLeefs) {
					leefs.add(leef);
				}
			}
			JDTree[] leefArray = new JDTree[leefs.size()];
			for (int i = 0; i < leefs.size(); i++) {
				leefArray[i] = leefs.get(i);
			}
			return leefArray;
		}

	}

	public JDTree getParent() {
		return parent;
	}

	public JDTree[] getChildren() {
		return children;
	}

	public Object getNode() {
		return node;
	}

	public NodeType getType() {
		return type;
	}

}

// get parent and children getNode
