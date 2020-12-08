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
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.viewers.IStructuredSelection;

<<<<<<< HEAD:src/ssmc/handlers/ButtonHandler.java
=======

import ssmc.Attribute;
import ssmc.AttributeVisitor;
import ssmc.Class;
import ssmc.CommentVisitor;
import ssmc.MethodVisitor;
import ssmc.StatementVisitor;

>>>>>>> master:src/ssmc/handlers/SampleHandler.java
/**
 * 
 * @author Andrew
 *
 */
public class ButtonHandler extends AbstractHandler {

	/**
	 * Upon button press, execute this code
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		try {
			addToTree();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	
	public void addToTree() throws JavaModelException, CoreException {
		IProject project = null;
		IPath path = null;
		
		IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
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
		}
		
	    path = project.getFullPath();
	    project = fileRoot.getProject(path.toOSString());
	    
	    
	    // The JDTree class takes over from here
	    
	    /*
	     * Depreciated
	     * Leave for potential further use
	     
	    if(project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
			IJavaProject javaProject = JavaCore.create(project);
			IPackageFragment[] packages = javaProject.getPackageFragments();
			for(IPackageFragment aPackage : packages) {
				if(aPackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
					for(ICompilationUnit aClass : aPackage.getCompilationUnits()) {
						
						// aClass iterates through all of the ICompilation Units
						//generateAttributeAST(aClass);
						//generateStatementAST(aClass);
						/*
						generateMethodAST(aClass);
						generateAttributeAST(aClass);
						generateCommentAST(aClass);	
						generateStatementAST(aClass);
						//getClasses(aClass);
					}
				}
			}
	    }
	    */
	    
	}

	public static Class[] getClasses(ICompilationUnit comp) {
		// TODO Auto-generated method stub
		return null;
	}
}