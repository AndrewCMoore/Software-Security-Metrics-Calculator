package Calculator_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Calculator.ClassMetrics;
import tree.JDTree;

class ClassMetricsTest {

	static ClassMetrics classMetrics;
	static JDTree[] classes;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Initialize Variables
		JDTree testTree = null;
		IProject project = null;
		IPath path = null;

		// Get the workspace in the plugin
		IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		
		// If the plugin window is open
		if (window != null) {
			// Get the test project
			project = fileRoot.getProject("Software-Security-Metrics-Calculator-Test-Project");
			// Check the nature of said project
			if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
				// got the IJavaProject which is the format we need
				IJavaProject javaProject = JavaCore.create(project);
				String kind = project.getClass().getName();
				//Build a tree out of the project and we will use this for tests. 
				testTree = new JDTree(javaProject, null);
				classes = testTree.getLeefs();
				classMetrics = new ClassMetrics(classes);
				
			}
		}		
	}

	@Test
	void testGetMethodsInClass() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 3);
		expectedValues.put("FactoryObject_InheritanceLevel2", 2);
		expectedValues.put("FactoryObject", 13);
		expectedValues.put("Inspectors", 9);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 9);
		// pointless package
		expectedValues.put("pointlessClass", 1);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 6);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 4);
		// simpleThreading package 
		expectedValues.put("AgentThread", 10);
		expectedValues.put("ChefThread", 3);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("CriticalClassInheritance", 0);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 2);
		
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), classMetrics.getMethodsInClass(classes).get(key));
		}
	}

}
