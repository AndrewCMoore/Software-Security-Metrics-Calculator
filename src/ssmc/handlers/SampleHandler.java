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
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;

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
			for(int i =  0; i < getPackages().length; i++) {
				
				if(getPackages()[i].getKind() == IPackageFragmentRoot.K_SOURCE){
					MessageDialog.openInformation(window.getShell(),"Package: " + i, getPackages()[i].getElementName());
				}
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				for(ICompilationUnit aClass : aPackage.getCompilationUnits()) {
					aClass.getElementName();
					// Add to tree
				}
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
	private IMethod[] getMethods() throws CoreException {
		IMethod[] methods = null;
		for(IPackageFragment aPackage : getPackages()) {
			if(aPackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				for(ICompilationUnit aClass : aPackage.getCompilationUnits()) {
					IType[] allTypes = aClass.getAllTypes();
					
					for(IType Type : allTypes) {
						methods = Type.getMethods();
						for(IMethod method : methods) {
							// Add to tree
							
						}
					}
				}
			}
		}	
		return methods;
	}
	
	/**
	 * TO DO 
	 * 
	 * Implement the methods for specific values, use the parameters IProject, IPackageFrament, ICompilationUnit, or IMethod as an input for each
	 */
	
	//oop
	
}
