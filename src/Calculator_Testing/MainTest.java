package Calculator_Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Calculator.Calculator;
import tree.JDTree;

class MainTest {

	Calculator calc; 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

	public MainTest() throws CoreException {
		IPackageFragment IPF = null;
		IProject project = null;
		IPath path = null;
		IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

		if (window != null) {
			// Insert Project Name here
			project = fileRoot.getProject("Software-Security-Metrics-Calculator-Test-Project");

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
			calc = new Calculator(myTree);
			//start calculating metrics
			calc.calculate();
		}
	}

}

