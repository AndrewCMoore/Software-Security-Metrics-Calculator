package OldCalculator_Testing;

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

import Calculator.MethodMetrics;
import tree.JDTree;

class MethodMetricsTest {

	static MethodMetrics methodMetrics;
	
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
				JDTree[] classes = testTree.getLeefs();
				methodMetrics = new MethodMetrics(classes);
				
			}
		}
	}

	@Test
	void testGetNonFinalPrivateProtected() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 1);
		expectedValues.put("FactoryObject_InheritanceLevel2", 1);
		expectedValues.put("FactoryObject", 0);
		expectedValues.put("Inspectors", 4);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 2);
		// pointless package
		expectedValues.put("pointlessClass", 0);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 0);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 3);
		// simpleThreading package 
		expectedValues.put("AgentThread", 0);
		expectedValues.put("ChefThread", 0);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("criticalClassInheritance", 0);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
		
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), methodMetrics.getMapNonFinalPrivateProtected().get(key));
		}
	}

	@Test
	void testGetMapClassified() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 1);
		expectedValues.put("FactoryObject_InheritanceLevel2", 1);
		expectedValues.put("FactoryObject", 0);
		expectedValues.put("Inspectors", 4);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 2);
		// pointless package
		expectedValues.put("pointlessClass", 0);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 0);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 3);
		// simpleThreading package 
		expectedValues.put("AgentThread", 0);
		expectedValues.put("ChefThread", 0);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("criticalClassInheritance", 0);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
				
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), methodMetrics.getMapClassified().get(key));
		}
	}

	@Test
	void testGetMapPublic() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 2);
		expectedValues.put("FactoryObject_InheritanceLevel2", 1);
		expectedValues.put("FactoryObject", 13);
		expectedValues.put("Inspectors", 5);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 7);
		// pointless package
		expectedValues.put("pointlessClass", 1);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 6);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 1);
		// simpleThreading package 
		expectedValues.put("AgentThread", 10);
		expectedValues.put("ChefThread", 3);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("criticalClassInheritance", 0);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 2);
		
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), methodMetrics.getMapPublic().get(key));
		}
	}

	@Test
	void testGetMapTotal() {
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
		expectedValues.put("criticalClassInheritance", 0);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 2);
		
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), methodMetrics.getMapTotal().get(key));
		}
	}

	@Test
	void testGetMapMutatorInteractions() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 1);
		expectedValues.put("FactoryObject", 2);
		expectedValues.put("Inspectors", 6);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 1);
		// pointless package
		expectedValues.put("pointlessClass", 0);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 0);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 0);
		// simpleThreading package 
		expectedValues.put("AgentThread", 1);
		expectedValues.put("ChefThread", 0);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("criticalClassInheritance", 0);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
		
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), methodMetrics.getMapMutatorInteractions().get(key));
		}
	}

	@Test
	void testGetMapAccessorInteractions() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 0);
		expectedValues.put("FactoryObject", 4);
		expectedValues.put("Inspectors", 0);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 1);
		// pointless package
		expectedValues.put("pointlessClass", 0);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 0);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 1);
		// simpleThreading package 
		expectedValues.put("AgentThread", 1);
		expectedValues.put("ChefThread", 0);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("criticalClassInheritance", 0);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
		
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), methodMetrics.getMapAccessorInteractions().get(key));
		}
	}

}
