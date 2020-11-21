package ssmc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.IfStatement;

public class JDT_Parser {
	
	private int numIfElseStatement;
	/**
	 * TO DO 
	 * 
	 * Implement the methods for specific values, use the parameters IProject, IPackageFrament, ICompilationUnit, or IMethod as an input for each
	 */
	
	/**
	 *
	 * @throws JavaModelException 
	 */
	private int getMethodInputs(IMethod method) throws JavaModelException {
		return method.getParameters().length;
	}
	
	private int getMethodOutputs(IMethod method) throws JavaModelException {
		if(method.getReturnType() == "Void") {
			return 0;
		} 
		return 1;
	}
	
	
	/**
	 * @throws JavaModelException 
	 * 
	 */
	private boolean isClassifiedMethod(IMethod method) throws JavaModelException {
		if(Flags.isPrivate(method.getFlags()) || Flags.isProtected(method.getFlags())){
			return true;
		}
		return false;
	}
	
	/*
	 * CAMC = a / kl
	 * a = summation of the number of distinct parameter types of each method in the class
	 * l = number of distinct parameter types
	 * k = number of methods
	 */
	private int numParamTypes(IMethod method) throws JavaModelException {
		int num = 0;
		
		for(int i = 0; i < method.getParameters().length; i++) {
			//switch(method.getParameters()[i].getTypeRoot().get) {
			//method.getParameters()[i].
			
			//}
		}
		return 0;
	}
	private boolean reflectionPackageBoolean(IProject project) {
		//if(project.contains(arg0))
		return false;
	}
	// I think we can use getBinary() to determine if the member is from a class file or if from a compilation unit (imported)
	//private boolean 
	
	private void ASTParserAttempt1(ICompilationUnit icomp) {
		//icomp.getChildren()[0].get
	}
	
	public boolean visit(IfStatement node) {
			this.numIfElseStatement++;
			return true;
	}
	
	
	public boolean isInheritedMethod(IMethod method) {
		if(method.getParent().exists() &&
		   !method.getParent().getClass().isLocalClass()) {
			return true; 
		}
		return false;
	}
	
	public int numDistinctParameterTypes(IMethod method) {
		int numPT = 0;
		
		return numPT;
	}
	
	public int numLines(ICompilationUnit file) throws IOException {
		// Simple Implementation
		// Could also use a buffered reader: see numCommentedLines
		int lineCount; 
		try (Stream<String> stream = Files.lines((Path) file.getPath(), StandardCharsets.UTF_8)){
			lineCount = (int) stream.count();
		}
		return lineCount;
		
	}
	
	public int numCommentedLines(ICompilationUnit file) {
		int numCommentedLines;
		String a = "/**";
		String b = "*/";
		String c = "//";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file.getPath().toFile()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	
	public int getNumOfMethods(ICompilationUnit file) throws JavaModelException {
		int numOfMethods = 0;
		IType[] allTypes = file.getAllTypes();
		for(IType type : allTypes) {
			numOfMethods += type.getMethods().length;
		}
		
		return numOfMethods;
	}
	
	public int getNumOfClasses(IPackageFragment pkg) throws JavaModelException {
		return pkg.getCompilationUnits().length;
	}
	
	
	
	
	
	
	
}
