package ssmc.handlers;

import org.eclipse.core.commands.AbstractHandler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import Calculator.Calculator;
import tree.JDTree;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * 
 * @author Andrew
 *
 */
public class ButtonHandler extends AbstractHandler {

	/**
	 * responds to the button being pushed
	 * Isn't meant to be called other than by the button push
	 * 
	 * @param event this is the event that is created by pushing a button   
	 * @return returns null
	 * @throws ExecutionException
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		try {
			addToTree();
		} catch (Exception e) {
			System.out.println("Select a Project, Package or Class");
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Takes the active Workspace and a selection in the workspace 
	 * if the selection is or is part of a java project then it will 
	 * create a tree out of the project then call the calculator module
	 * 
	 * @throws JavaModelException
	 * @throws CoreException
	 */
	public void addToTree() throws JavaModelException, CoreException {
		IProject project = null;
		IPath path = null;
		//Gets the root directory of the workspace
   		IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
		// Gets the active window
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window != null) {
			//gets the selection using the window
			IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
			if (selection instanceof IStructuredSelection) {
				Object element = ((IStructuredSelection) selection).getFirstElement();
				
				if (element instanceof IResource) {
					//gets the IProject that the selection is part of
					project = ((IResource) element).getProject();
				} else if (element instanceof IJavaElement) {
					//Converts it to a better format
					IJavaProject jProject = ((IJavaElement) element).getJavaProject();
					project = jProject.getProject();			
				}
			}
		}
		
		path = project.getFullPath();
		project = fileRoot.getProject(path.toOSString());
		if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
			// got the IJavaProject which is the format we need
			IJavaProject javaProject = JavaCore.create(project);
			String kind = project.getClass().getName();
			//Build a tree out of the project
			JDTree myTree = new JDTree(javaProject, null);
			//pass the tree to the calculator
			Calculator calc = new Calculator(myTree);
			//start calculating metrics
			calc.calculate();
		}
		
		// The JDTree class takes over from here

		/*
		 * Depreciated Leave for potential further use
		 * 
		 * if(project.isNatureEnabled("org.eclipse.jdt.core.javanature")) { IJavaProject
		 * javaProject = JavaCore.create(project); IPackageFragment[] packages =
		 * javaProject.getPackageFragments(); for(IPackageFragment aPackage : packages)
		 * { if(aPackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
		 * for(ICompilationUnit aClass : aPackage.getCompilationUnits()) {
		 * 
		 * // aClass iterates through all of the ICompilation Units
		 * //generateAttributeAST(aClass); //generateStatementAST(aClass); /*
		 * generateMethodAST(aClass); generateAttributeAST(aClass);
		 * generateCommentAST(aClass); generateStatementAST(aClass);
		 * //getClasses(aClass); } } } }
		 */

	}
}
