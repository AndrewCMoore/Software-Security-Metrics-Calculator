package tree;

/**
 * This enum represents the type of node
 * All names are self explanitory
 * 
 * Method and Attribute and File are not used 
**/
public enum NodeType {
	//JavaProject
	PROJECT, 
	//IPackageFragment
	PACKAGE,
	//Not used
	FILE,
	//ICompilationUnit
	COMPILATIONUNIT,
	//Class
	CLASS,
	//Not used
	METHOD,
	ATTRIBUTE
}
