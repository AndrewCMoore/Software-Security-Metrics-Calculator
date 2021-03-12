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

public class JDTree extends Thread{
	//the parent node for the tree, if it is null it is the root node
	private JDTree parent;
	//List of Children, if it is null there are no children and it is the leaf nodes
	private JDTree[] children;
	//Type of node that it is
	private NodeType type;
	// Actual node that we are organizing
	private Object node;
	
	private boolean Threading = false;
	
	/**
	 * Constructor for JDTree
	 * Finds out node type, and sets children and node type for the node
	 * 
	 * 
	 * @param parent The parent node of the tree, null if it is the root node
	 * @param Object node The node it represents ie: JavaProject,IPackageFragment ect.
	 * @throws JavaModelException
	**/
	public JDTree(Object node, JDTree parent) throws JavaModelException {
		
		if (Threading) {
			this.node = node;
			this.parent = parent;
			//we get a string of the kind of node that we are dealing with
			String kind = node.getClass().getName();
			//Check the kind of node
			if (kind.equals("org.eclipse.jdt.internal.core.JavaProject")) {
				//if it is a project
				IJavaProject project = (IJavaProject) node;
				//set the type
				type = NodeType.PROJECT;
				//get the packages in the project
				IPackageFragment[] packages = project.getPackageFragments();
				//Create a list of children
				ArrayList<JDTree> aChildren = new ArrayList<JDTree>();
				//loop through the packages and create a new node for each one
				for (int i = 0; i < packages.length; i++) {
					String childKind = packages[i].getClass().getName();
					// we have to do it this way because we want to exclude all of the Jar files
					//only do this if it is actually a PackageFragment 
					if(childKind.equals("org.eclipse.jdt.internal.core.PackageFragment")) {
						//add it to an array list first because we don't know how many there are goin to be
						 aChildren.add(new JDTree(packages[i], this));
					}
				}
				//Then add it to an array later when we know the size
				//TODO make children an arraylist so we don't have to do this twice
				children = new JDTree[aChildren.size()];
				for(int j=0; j<aChildren.size(); j++) {
					children[j] = aChildren.get(j);
				}
			}
			//repeat of the packages
			if (kind.equals("org.eclipse.jdt.internal.core.PackageFragment")) {
				type = NodeType.PACKAGE;
				IPackageFragment pack = (IPackageFragment) node;
				//get the list of compilation units
				ICompilationUnit[] units = pack.getCompilationUnits();
				
				for(int j = 0; j < units.length; j++) {
					type = NodeType.COMPILATIONUNIT;
					CAMValues cv = new CAMValues(units[j]);
					cv.start();
					Class[] classes = cv.getClassArray();
					//Class[] classes = new CAMValues().run(comp);
					//Class[] classes = CAMValues.getClasses(comp);
					//if the there are classes in here then we add them to the list of children
					if (classes != null) {
						children = new JDTree[classes.length];
						for (int i = 0; i < classes.length; i++) {
							children[i] = new JDTree(classes[i], this);
							System.out.println("the Class is "+classes[i].getIdentifier());
							
						}
					}
				}
				children = new JDTree[units.length];
				//add children to the array
				for (int i = 0; i < units.length; i++) {
					children[i] = new JDTree(units[i], this);
				}
				// generateAST();
			}
			/*
			//If it is a Compilation unit
			if (kind.equals("org.eclipse.jdt.internal.core.CompilationUnit")) {
				//set type
				type = NodeType.COMPILATIONUNIT;
			
				ICompilationUnit comp = (ICompilationUnit) node;
				CAMValues cv = new CAMValues(comp);
				cv.run();
				Class[] classes = cv.getClassArray();
				//Class[] classes = new CAMValues().run(comp);
				//Class[] classes = CAMValues.getClasses(comp);
				//if the there are classes in here then we add them to the list of children
				if (classes != null) {
					children = new JDTree[classes.length];
					for (int i = 0; i < classes.length; i++) {
						children[i] = new JDTree(classes[i], this);
						System.out.println("the Class is "+classes[i].getIdentifier());
						
					}
				}
			}
			*/
			//if it is a class there arne't any more steps
			if (kind.equals("ssmc.Class")) {
				type = NodeType.CLASS;
			}
		}
		else {
			this.node = node;
			this.parent = parent;
			//we get a string of the kind of node that we are dealing with
			String kind = node.getClass().getName();
			//Check the kind of node
			if (kind.equals("org.eclipse.jdt.internal.core.JavaProject")) {
				//if it is a project
				IJavaProject project = (IJavaProject) node;
				//set the type
				type = NodeType.PROJECT;
				//get the packages in the project
				IPackageFragment[] packages = project.getPackageFragments();
				//Create a list of children
				ArrayList<JDTree> aChildren = new ArrayList<JDTree>();
				//loop through the packages and create a new node for each one
				for (int i = 0; i < packages.length; i++) {
					String childKind = packages[i].getClass().getName();
					// we have to do it this way because we want to exclude all of the Jar files
					//only do this if it is actually a PackageFragment 
					if(childKind.equals("org.eclipse.jdt.internal.core.PackageFragment")) {
						//add it to an array list first because we don't know how many there are goin to be
						 aChildren.add(new JDTree(packages[i], this));
					}
				}
				//Then add it to an array later when we know the size
				//TODO make children an arraylist so we don't have to do this twice
				children = new JDTree[aChildren.size()];
				for(int j=0; j<aChildren.size(); j++) {
					children[j] = aChildren.get(j);
				}
			}
			//repeat of the packages
			if (kind.equals("org.eclipse.jdt.internal.core.PackageFragment")) {
				type = NodeType.PACKAGE;
				IPackageFragment pack = (IPackageFragment) node;
				//get the list of compilation units
				ICompilationUnit[] units = pack.getCompilationUnits();
				children = new JDTree[units.length];
				//add children to the array
				for (int i = 0; i < units.length; i++) {
					children[i] = new JDTree(units[i], this);
				}
				// generateAST();
			}
			//If it is a Compilation unit
			if (kind.equals("org.eclipse.jdt.internal.core.CompilationUnit")) {
				//set type
				type = NodeType.COMPILATIONUNIT;
			
				ICompilationUnit comp = (ICompilationUnit) node;
				Class[] classes = CAMValues.getClasses(comp);
				//if the there are classes in here then we add them to the list of children
				if (classes != null) {
					children = new JDTree[classes.length];
					for (int i = 0; i < classes.length; i++) {
						children[i] = new JDTree(classes[i], this);
						System.out.println("the Class is "+classes[i].getIdentifier());
						
					}
				}
			}
			//if it is a class there arne't any more steps
			if (kind.equals("ssmc.Class")) {
				type = NodeType.CLASS;
				
			}
		}
		

	}
	
	/**
	 * Gets the leafs at the bottom of the tree
	 * All returned nodes are of type CLASS
	 * @return array of CLASS JDT nodes
	**/
	
	public JDTree[] getLeefs() {
		if (type == NodeType.COMPILATIONUNIT) {
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
	/**
	 * 
	 * @return this nodes parent
	 */
	public JDTree getParent() {
		return parent;
	}
	/**
	 * 
	 * @return array of children
	 */
	public JDTree[] getChildren() {
		return children;
	}
	/**
	 * 
	 * @return the represented object
	 */
	public Object getNode() {
		return node;
	}
	/**
	 * 
	 * @return enum type
	 */
	public NodeType getType() {
		return type;
	}

}

// get parent and children getNode
