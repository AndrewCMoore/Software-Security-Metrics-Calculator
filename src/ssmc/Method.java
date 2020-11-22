package ssmc;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Method {
	private int length;
	private int numberOfOutputs;
	private int numberOfInputs;
	private double methodComplexity;
	private boolean isClassified;
	private boolean isWriteClassified;
	private boolean isInherited;
	private boolean isAccessible;
	private String identifier;
	private String modified;
	private int usage;
	private boolean finalized;
	private CompilationUnit originFile;
	private int links;
	
	public Method(String identifier, CompilationUnit originFile) {
		this.originFile = originFile;
		this.identifier = identifier;
	}
	
}
