package ssmc;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Class {
	private String Identifier;
	private String modifier;
	private boolean serialized;
	private boolean critical;
	private CompilationUnit originFile;
	private Method[] methods;
	private Attribute[] attribute;
	
	public Class(String identifier, CompilationUnit originFile) {
		this.Identifier = identifier;
		this.originFile = originFile;
		this.serialized = false;
		this.critical = false;
	}
	
	
}
