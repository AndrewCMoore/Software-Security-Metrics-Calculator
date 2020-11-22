package ssmc.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.Document;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;


import ssmc.Attribute;
import ssmc.AttributeVisitor;
import ssmc.CommentVisitor;
import ssmc.MethodVisitor;

/**
 * 
 * @author Andrew
 *
 */
public class SampleHandler extends AbstractHandler {

	/**
	 * Upon button press, execute this code
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"SSMC",
				"Hello, Eclipse world");
		
		MessageDialog.openInformation(window.getShell(),"Root", getRoot().getLocation().toString());
		MessageDialog.openInformation(window.getShell(),"Path", getProjectName().toOSString());
		
		try {
			MessageDialog.openInformation(window.getShell(), "Methods", getMethods());
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		try {
			for(int i =  0; i < getPackages().length; i++) {
				if(getPackages()[i].getKind() == IPackageFragmentRoot.K_SOURCE){
					MessageDialog.openInformation(window.getShell(),"Package: " + i, getPackages()[i].getElementName());
				}
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//AST 
		try {
<<<<<<< HEAD
			//generateCommentAST(getClasses()[0]);
			generateMethodAST(getClasses()[0]);
			//generateAttributeAST(getClasses()[0]);
=======
			generateCommentAST(getClasses()[0]);
			generateMethodAST(getClasses()[0]);
			generateAttributeAST(getClasses()[0]);
>>>>>>> cd86c06e13702ba62928b2fff754e041723d0148
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
	/**
	 * Get the root directory of the Eclipse IDE folder. 
	 * @return The IWorkspaceRoot representation of the root folder. 
	 */
	private IWorkspaceRoot getRoot() {
		IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
		//TreeNode<IWorkspaceRoot> root = new TreeNode<IWorkspaceRoot>(fileRoot);
		return fileRoot;
	}
	
	/**
	 * Gets the current active project. Uses IStrucuted Selection and IWorkbenchWindow to identify the 
	 * current active Eclipse IDE window as well as the selected project upon button press in order 
	 * to correctly identify which project the JDT will be grabbing from. 
	 * @return The IPath representation of the currently active project.
	 */
	private IPath getProjectName() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IProject project = null;
		IPath path = null;
		
	    if(window != null) { 
	    	IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
	    	if(selection instanceof IStructuredSelection) {
				Object element = ((IStructuredSelection) selection).getFirstElement();
				
				if(element instanceof IResource) {
					project = ((IResource) element).getProject();
				} else if (element instanceof IJavaElement) {
					IJavaProject jProject = ((IJavaElement) element).getJavaProject();
					project = jProject.getProject();
				}
			}
	    path = project.getFullPath();
	    }
	    return path;
	}
	
	/**
	 * Uses the function getProjectName and gets the directory for the project
	 * @return The IProject representation of the Project
	 */
	private IProject getProject() {
		IProject project = getRoot().getProject(getProjectName().toOSString());
		// Add to tree
		return project;
	}
	
	/**
	 * Uses the IJavaPRoject representation of getProject and splits the project
	 * into packages.
	 * @return IPackageFragment representation of the project's packages 
	 * @throws CoreException
	 */
	private IPackageFragment[] getPackages() throws CoreException {
		if(getProject().isNatureEnabled("org.eclipse.jdt.core.javanature")) {
			IJavaProject javaProject = JavaCore.create(getProject());
			IPackageFragment[] packages = javaProject.getPackageFragments();
			// Add to tree
			return packages;
		}
		return null;
	}
	/**
	 * Gets all compilable units in the project from each of the packages
	 * @return ICompilationUnit representation of the classes in the project
	 * @throws CoreException
	 */
	private ICompilationUnit[] getClasses() throws CoreException {
		for(IPackageFragment aPackage : getPackages()) {
			if(aPackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				
				return aPackage.getCompilationUnits();
				/*
				for(ICompilationUnit aClass : aPackage.getCompilationUnits()) {
					aClass.getElementName();
					// Add to tree
				}*/
			}
		}	
		
		return null;		
	}
	
	/**
	 * Returns all the methods in the class
	 * *NOTE* This is a little buggy rn so let me fix it later
	 * @return The methods in the project
	 * @throws CoreException
	 */
	private String getMethods() throws CoreException {
		String s = new String();
		IMethod[] methodArray = new IMethod[1];
		for(IPackageFragment aPackage : getPackages()) {
			if(aPackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				for(ICompilationUnit aClass : aPackage.getCompilationUnits()) {
					IType[] allTypes = aClass.getAllTypes();
					
					for(IType Type : allTypes) {
						IMethod[] methods = Type.getMethods();
						for(IMethod method : methods) {
							// Add to tree
							s += method.getElementName() + "\t";
							//methodArray[methodArray.length] = method;
						}
					}
				}
			}
		}	
		return s;
	}	
	
	
	/**
	 * Statement to call AttributeVisitor
	 */
	private void generateAttributeAST(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		AttributeVisitor av = new AttributeVisitor(cu);
		cu.accept(av);
	}

	private void generateMethodAST(ICompilationUnit unit){
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		MethodVisitor mv = new MethodVisitor(cu);
		cu.accept(mv);
	}
	
	private void generateCommentAST(ICompilationUnit unit) {
		final CompilationUnit cu = (CompilationUnit) parse(unit);
		CommentVisitor cv = new CommentVisitor(cu);
		cu.accept(cv);
		System.out.println("The total number of commented lines is: " + cv.getLineCount());
	}
	
	protected CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS13);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); //parse		
	}
}
