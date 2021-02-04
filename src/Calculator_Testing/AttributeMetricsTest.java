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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Calculator.AttributeMetrics;
import tree.JDTree;

class AttributeMetricsTest {

	static AttributeMetrics attributeMetrics; 
	  String[] classNames; 
	
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
				attributeMetrics = new AttributeMetrics(classes);
			}
		}		
	}

	@AfterEach
	void tearDown() throws Exception {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 0);
		expectedValues.put("FactoryObject", 0);
		expectedValues.put("Inspectors", 0);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 0);
		// pointless package
		expectedValues.put("pointlessClass", 0);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 0);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 0);
		// simpleThreading package 
		expectedValues.put("AgentThread", 0);
		expectedValues.put("ChefThread", 0);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("CriticalClassInheritance", 0);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
	}

	@Test
	void testGetPublicInstanceAttributes() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 0);
		expectedValues.put("FactoryObject", 0);
		expectedValues.put("Inspectors", 0);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 0);
		// pointless package
		expectedValues.put("pointlessClass", 1);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 1);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 0);
		// simpleThreading package 
		expectedValues.put("AgentThread", 0);
		expectedValues.put("ChefThread", 0);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("CriticalClassInheritance", null);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
	
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), attributeMetrics.getPublicInstanceAttributes().get(key));
			
		}
		
	}

	@Test
	void testGetPublicClassAttributes() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 3);
		expectedValues.put("FactoryObject_InheritanceLevel1", 0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 0);
		expectedValues.put("FactoryObject", 0);
		expectedValues.put("Inspectors", 0);
		expectedValues.put("ObjectStates", 4);
		expectedValues.put("Products", 3);
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
		expectedValues.put("Ingredients", 3);
		expectedValues.put("CriticalClassInheritance", null);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
		
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), attributeMetrics.getPublicClassAttributes().get(key));
		}
	}

	@Test
	void testGetPrivateProtectedInstanceAttributes() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 1);
		expectedValues.put("FactoryObject_InheritanceLevel2", 3);
		expectedValues.put("FactoryObject", 5);
		expectedValues.put("Inspectors", 5);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 4);
		// pointless package
		expectedValues.put("pointlessClass", 0);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 1);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 1);
		// simpleThreading package 
		expectedValues.put("AgentThread", 4);
		expectedValues.put("ChefThread", 3);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("CriticalClassInheritance", null);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
		
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), attributeMetrics.getPrivateProtectedInstanceAttributes().get(key));
		}
		
	}

	@Test
	void testGetPrivateProtectedClassAttributes() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 0);
		expectedValues.put("FactoryObject", 0);
		expectedValues.put("Inspectors", 0);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 0);
		// pointless package
		expectedValues.put("pointlessClass", 0);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 2);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 4);
		// simpleThreading package 
		expectedValues.put("AgentThread", 2);
		expectedValues.put("ChefThread", 0);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("CriticalClassInheritance", null);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
		
		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), attributeMetrics.getPrivateProtectedClassAttributes().get(key));
		}
	}

	@Test
	void testGetPrivateProtectedTotal() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 1);
		expectedValues.put("FactoryObject_InheritanceLevel2", 3);
		expectedValues.put("FactoryObject", 5);
		expectedValues.put("Inspectors", 5);
		expectedValues.put("ObjectStates", 0);
		expectedValues.put("Products", 0);
		expectedValues.put("WorkStations", 0);
		// pointless package
		expectedValues.put("pointlessClass", 4);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 3);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 5);
		// simpleThreading package 
		expectedValues.put("AgentThread", 6);
		expectedValues.put("ChefThread", 3);
		expectedValues.put("Ingredients", 0);
		expectedValues.put("CriticalClassInheritance", null);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
		
		for(String key : expectedValues.keySet()) {
			System.out.println("ERROR: " + key);
			System.out.println("ERROR: " + expectedValues.get(key));
			System.out.println("ERROR: " + attributeMetrics.getPrivateProtectedTotal().get(key));
			assertEquals(expectedValues.get(key), attributeMetrics.getPrivateProtectedTotal().get(key));
		}
	}

	@Test
	void testGetTotalAttributes() {
		HashMap<String, Integer> expectedValues = new HashMap<String, Integer>();
		//factoryClasses package
		expectedValues.put("Components", 3);
		expectedValues.put("FactoryObject_InheritanceLevel1", 1);
		expectedValues.put("FactoryObject_InheritanceLevel2", 3);
		expectedValues.put("FactoryObject", 5);
		expectedValues.put("Inspectors", 5);
		expectedValues.put("ObjectStates", 4);
		expectedValues.put("Products", 3);
		expectedValues.put("WorkStations", 5);
		// pointless package
		expectedValues.put("pointlessClass", 1);
		expectedValues.put("pointlessInterface", 0);
		expectedValues.put("pointlessLoops", 4);
		// sfm package 
		expectedValues.put("SimulateFactoryModel", 5);
		// simpleThreading package 
		expectedValues.put("AgentThread", 7);
		expectedValues.put("ChefThread", 3);
		expectedValues.put("Ingredients", 3);
		expectedValues.put("CriticalClassInheritance", null);
		// weibullGenerator package 
		expectedValues.put("GenerateWeibullDistributionData", 0);
	
	
		for(String key : expectedValues.keySet()) {
			System.out.println("ERROR: " + key);
			System.out.println("ERROR: " + expectedValues.get(key));
			System.out.println("ERROR: " + attributeMetrics.getPrivateProtectedTotal().get(key));
			assertEquals(expectedValues.get(key), attributeMetrics.getPrivateProtectedTotal().get(key));
		}
	}

}
