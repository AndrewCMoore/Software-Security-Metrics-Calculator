package Parser_Testing;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import ssmc.CAMValues;

public class MainTest {

	public IPackageFragment AccessTestClass() throws CoreException {
		IPackageFragment IPF = null;
		IProject project = null;
		IPath path = null;
		IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

		if (window != null) {
			project = fileRoot.getProject("Parser-Testing");

		}
		path = project.getFullPath();
		project = fileRoot.getProject(path.toOSString());
		if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
			IJavaProject javaProject = JavaCore.create(project);
			IPackageFragment[] packages = javaProject.getPackageFragments(); 
			for(IPackageFragment aPackage : packages) { 
				if(aPackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
					IPF = aPackage;
				}
			}		 
		}
		return IPF;
	}
	public ICompilationUnit getUnitByName(IPackageFragment f,String s) throws JavaModelException {
		ICompilationUnit[] units = f.getCompilationUnits();
		for(ICompilationUnit unit:units) {
			System.out.println(unit.getElementName());
			if(unit.getElementName().equals(s)) {
				return unit;
			}
		}
		return null;
		
	}
	
}
