package tree;

import java.lang.management.ManagementFactory;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Processor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import ssmc.*;
import ssmc.Class;

public class JDTree {
	//the parent node for the tree, if it is null it is the root node
	private JDTree parent;
	//List of Children, if it is null there are no children and it is the leaf nodes
	private JDTree[] children;
	//Type of node that it is
	private NodeType type;
	// Actual node that we are organizing
	
	private Object node;
	

	private boolean Threading = false;
	private static int MAX_NO_THREADS = 50;
	public static int startThreads;
	public static Semaphore semaphore = new Semaphore(5);

	
	/**
	 * Constructor for JDTree.
	 * Finds out node type, and sets children and node type for the node.
	 * 
	 * @param parent The parent node of the tree, null if it is the root node
	 * @param Object node The node it represents ie: JavaProject,IPackageFragment ect.
	 * @throws JavaModelException
	**/
	public JDTree(Object node, JDTree parent) throws JavaModelException {
		
		System.out.println("Node: " + node.getClass());
		System.out.println("Starting Thread Count: " + startThreads);
		
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
				System.out.println("This is the project");
				//checkFolders(project);
				
				// Get the package fragments roots
				IPackageFragmentRoot[] folders = project.getPackageFragmentRoots();
				
				//Create a list of children
				ArrayList<JDTree> aChildren = new ArrayList<JDTree>();
				
				//loop through the packages and create a new node for each one
				for (int i = 0; i < folders.length; i++) {
					// Get the type of folder
					String childKind = folders[i].getClass().getName();
					
					// Remove JAR folders by only getting PackageFragementRoots
					if(childKind.equals("org.eclipse.jdt.internal.core.PackageFragmentRoot")) {
						// Get all of the package within the root 
						IJavaElement[] packages = folders[i].getChildren();
						for(IJavaElement aPackage : packages) {
							aChildren.add(new JDTree(aPackage, this));
						}
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
				int startThreadCount = Thread.activeCount();
				System.out.println("|||||||| " + startThreadCount + " |||||||||");
				type = NodeType.PACKAGE;
				type = NodeType.COMPILATIONUNIT;
				IPackageFragment pack = (IPackageFragment) node;
				System.out.println("PACKAGE: " + pack.toString());
				//get the list of compilation units
				ICompilationUnit[] units = pack.getCompilationUnits();
				
				// # of Children = number of compilation units within the package 
				children = new JDTree[units.length];
				ArrayList<JDTree> tempChildren = new ArrayList<JDTree>();
				ArrayList<CAMValues> listCAMV = new ArrayList<CAMValues>();
				//add children to the array
				for (int i = 0; i < units.length; i++) {
					while(Thread.activeCount() - startThreadCount > MAX_NO_THREADS) {
						
					}
					CAMValues cv = new CAMValues(units[i]);
					
					//if(Thread.activeCount() - startThreads < MAX_NO_THREADS) {
					cv.start();
					listCAMV.add(cv);
					//} else {
						//cv.run();
					//}
				}
				while(Thread.activeCount() > startThreadCount) {
					System.out.println(MAX_NO_THREADS + " MAX THREADS");
					System.out.println("Threads:" + Thread.activeCount() + "\t Goal: " + startThreadCount);
				}
				for(int i = 0; i < listCAMV.size(); i++) {
					Class[] classes = listCAMV.get(i).getClassArray();
					System.out.println(classes);
					//if the there are classes in here then we add them to the list of children
					if (classes != null) {
						for (int j = 0; j < classes.length; j++) {
							//children[j] = new JDTree(classes[j], this);
							tempChildren.add(new JDTree(classes[j], this));
							System.out.println("the Class is "+classes[j].getIdentifier());
						}
					}
				}
				
				children = new JDTree[tempChildren.size()];
				
				for(int k = 0; k < tempChildren.size(); k++) {
					System.out.println("Size:" + tempChildren.size() + "\t index:" + k);
					children[k] = tempChildren.get(k);
				}
				// generateAST();
			}

			if (kind.equals("org.eclipse.jdt.internal.core.CompilationUnit")) {}
			/*
			//If it is a Compilation unit
			if (kind.equals("org.eclipse.jdt.internal.core.CompilationUnit")) {
				//set type
				type = NodeType.COMPILATIONUNIT;
			
				ICompilationUnit comp = (ICompilationUnit) node;
				//Class[] classes = CAMValues.getClasses(comp);
				CAMValues cv = new CAMValues(comp);
				
				try {
					cv.start();
					TimeUnit.MICROSECONDS.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Class[] classes = cv.getClassArray();
				//if the there are classes in here then we add them to the list of children
				if (classes != null) {
					children = new JDTree[classes.length];
					for (int i = 0; i < classes.length; i++) {
						children[i] = new JDTree(classes[i], this);
						//System.out.println("the Class is "+classes[i].getIdentifier());
						
					}
				}
			}
			/*
			//repeat of the packages
			if (kind.equals("org.eclipse.jdt.internal.core.PackageFragment")) {
				type = NodeType.PACKAGE;
				IPackageFragment pack = (IPackageFragment) node;
				//get the list of compilation units
				ICompilationUnit[] units = pack.getCompilationUnits();
				type = NodeType.COMPILATIONUNIT;
				for(int k = 0; k < units.length; k++) {
					
				}
				
				for(int j = 0; j < units.length; j++) {
					
					
					System.out.println("Active: " + Thread.activeCount() + "\n Start Threads: " + startThreads + "\n Max Threads: " + MAX_NO_THREADS);
					while(Thread.activeCount() - startThreads > MAX_NO_THREADS) {
						//System.out.println("Too many threads active");
					}
					
					System.out.println("Threads active");
					
					CAMValues cv = new CAMValues(units[j]);
					try {
						cv.start();
						while(cv.running) {}
						Class[] classes = cv.getClassArray();
						System.out.println(classes);
						
						if (classes != null) {
							System.out.println("We got here");
							children = new JDTree[classes.length];
							for (int i = 0; i < classes.length; i++) {
								children[i] = new JDTree(classes[i], this);
								System.out.println("the Class is "+classes[i].getIdentifier());
								
							}
						}
					} catch (Exception e) {
						
					} 
					
					
					//if the there are classes in here then we add them to the list of children
										
				}
				/*
				children = new JDTree[units.length];
				//add children to the array
				for (int i = 0; i < units.length; i++) {
					children[i] = new JDTree(units[i], this);
				}
				
				// generateAST();

			}*/
			
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
				// Check the folders 
				checkFolders(project);
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
				System.out.println(pack.toString());
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
						//System.out.println("the Class is "+classes[i].getIdentifier());
						
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
		
		System.out.println(this.node.getClass());
		if (type == NodeType.COMPILATIONUNIT) {
			return children;
		} 
		else {
			ArrayList<JDTree> leefs = new ArrayList<JDTree>();
			
			for (JDTree child : children) {
				System.out.println("Child: " + child.node.getClass());
				JDTree[] childLeefs = child.getLeefs();
				for (JDTree leef : childLeefs) {
					//System.out.println("+++" + leef.node.getClass());
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
	
	public void checkFolders(IJavaProject project) {
		try {
			Object[] array1 = project.getNonJavaResources();
			IPackageFragmentRoot[] array= project.getAllPackageFragmentRoots();
			for(IPackageFragmentRoot a : array) {
				for(Object o : a.getChildren()) {
					System.out.println(o.toString());
				}
				
				System.out.println(a.toString());
			}
			
			for(Object a : array1) {
				System.out.println("Method 1: " + a.toString());
				if(a instanceof IFolder) {
					System.out.println("The folder is: " + (IFolder) a);
					IFolder folder = (IFolder) a;
				}
				
			}
			
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

// get parent and children getNode
