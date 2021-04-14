package Calculator_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MPV {

	private static MainTest mt; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mt = new MainTest();
		
	}

	@Test
    void testGetAllHierarchySize() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package

        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
        expectedValues.put("FactoryObject", 3.0);
        expectedValues.put("Inspectors", 0.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 0.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 0.0);
        expectedValues.put("ChefThread", 1.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 0.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getAllHierarchySize());
        }
    }
	
	@Test
    void testGetBaseClassNames() {
		Set<String> expectedValues = new HashSet<String>();
		expectedValues.add("FactoryObject_InheritanceLevel2");
		expectedValues.add("FactoryObject_InheritanceLevel1");
		expectedValues.add("FactoryObject");
		expectedValues.add("ChefThread");
		
		for (String ev: expectedValues) {
			assertTrue(mt.calc.getMurgePulledValues().getBaseClassNames().contains(ev));			
		}
		assertEquals(4, mt.calc.getMurgePulledValues().getBaseClassNames().size());        
    }
	
	@Test
    void testGetClassCouplingRelationship() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 2.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 4.0);
        expectedValues.put("FactoryObject", 1.0);
        expectedValues.put("Inspectors", 5.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 4.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 1.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 7.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 6.0);
        expectedValues.put("ChefThread", 3.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 2.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getClassCouplingRelationship());
        }
    }
	
	@Test
    void testGetClassesCoupledToBaseClass() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 2.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 4.0);
        expectedValues.put("FactoryObject", 1.0);
        expectedValues.put("Inspectors", 0.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 0.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 0.0);
        expectedValues.put("ChefThread", 3.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 0.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getClassesCoupledToBaseClass());
        }
    }
	
	@Test
    void testGetClassMethodsInheritedBySubClass() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 11.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 14.0);
        expectedValues.put("FactoryObject", 0.0);
        expectedValues.put("Inspectors", 14.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 11.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 0.0);
        expectedValues.put("ChefThread", 0.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 2.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getClassMethodsInheritedBySubClassm());
        }
    }
	

	@Test
    void testCriticalBaseClasses() {
        String expectedValues = "ChefThread";
        for(String value : mt.calc.getMurgePulledValues().getCriticalBaseClasses()) {
            assertEquals(expectedValues, mt.calc.getMurgePulledValues().getCriticalBaseClasses());
        }
    }
	
	//@Test
    void testCriticalClasses() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        
        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getCriticalClasses());
        }
    }
	
	@Test
    void testCriticalSerializedClasses() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
        expectedValues.put("FactoryObject", 0.0);
        expectedValues.put("Inspectors", 0.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 0.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 1.0);
        expectedValues.put("ChefThread", 0.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 0.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getCriticalSerializedClasses());
        }
    }
	
	@Test
    void testGetDepthOfInheritanceTreeAtCurrentSuperClass() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 1.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 2.0);
        expectedValues.put("FactoryObject", 0.0);
        expectedValues.put("Inspectors", 3.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 0.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 0.0);
        expectedValues.put("ChefThread", 0.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 0.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getDepthOfInheritanceTreeAtCurrentSuperClass());
        }
    }
	
	
	@Test
    void testImportBooleanReflectionClasses() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 1.0);
        expectedValues.put("FactoryObject", 0.0);
        expectedValues.put("Inspectors", 0.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 0.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 0.0);
        expectedValues.put("ChefThread", 0.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 0.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getImportBooleanReflectionClasses());
        }
    }
		
	@Test
    void testMethodsInClass() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 3.0);
        expectedValues.put("FactoryObject", 14.0);
        expectedValues.put("Inspectors", 10.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 0.0);
        // pointless package
        expectedValues.put("pointlessClass", 3.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 10.0);
        expectedValues.put("ChefThread", 4.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 1.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 2.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getMethodsInClass());
        }
    }
	
	@Test
    void testMethodsInheritedBySubClass() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 11.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 14.0);
        expectedValues.put("FactoryObject", 0.0);
        expectedValues.put("Inspectors", 14.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 11.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread",0.0);
        expectedValues.put("ChefThread", 0.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 2.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getMethodsInheritedBySubClass());
        }
    }
	
	@Test
    void testNonFinalizedCriticalClasses() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
        expectedValues.put("FactoryObject", 0.0);
        expectedValues.put("Inspectors", 0.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 0.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 1.0);
        expectedValues.put("ChefThread", 1.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 1.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNonFinalizedCriticalClasses());
        }
    }
	
	@Test
    void testNumberOfAccesibleMethods() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 3.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 2.0);
        expectedValues.put("FactoryObject", 14.0);
        expectedValues.put("Inspectors", 6.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 8.0);
        // pointless package
        expectedValues.put("pointlessClass", 3.0);
        expectedValues.put("pointlessInterface", 1.0);
        expectedValues.put("pointlessLoops", 7.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 2.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 10.0);
        expectedValues.put("ChefThread", 4.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 1.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 2.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfAccesibleMethods());
        }
    }
	
	@Test
    void testNumberOfAttributesInheritedByAClass() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
        expectedValues.put("FactoryObject", 0.0);
        expectedValues.put("Inspectors", 0.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 1.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 0.0);
        expectedValues.put("ChefThread", 0.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 0.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);
        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfAttributesInheritedByAClass());
        }
    }
	
	@Test
    void testNumberOfBaseClasses() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();

            assertEquals(4, mt.calc.getMurgePulledValues().getNumberOfBaseClasses());
  
    }
	
	@Test
    void testNumberOfBaseClassMethodsInheritedBySubClass() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package

        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 14.0);
        expectedValues.put("FactoryObject", 0.0);
        expectedValues.put("Inspectors", 14.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 11.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 0.0);
        expectedValues.put("ChefThread", 0.0);
        expectedValues.put("Ingredients", 11.0);
        expectedValues.put("criticalClassInheritance", 2.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfBaseClassMethodsInheritedBySubClass());
        }
    }
	

	@Test
    void testNumberOfClassesThatInheritFromEachCriticalSuperClass() {
        HashMap<String, Double> expectedValues = new HashMap<String, Double>();
        //factoryClasses package
        expectedValues.put("Components", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
        expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
        expectedValues.put("FactoryObject", 0.0);
        expectedValues.put("Inspectors", 0.0);
        expectedValues.put("ObjectStates", 0.0);
        expectedValues.put("Products", 0.0);
        expectedValues.put("WorkStations", 0.0);
        // pointless package
        expectedValues.put("pointlessClass", 0.0);
        expectedValues.put("pointlessInterface", 0.0);
        expectedValues.put("pointlessLoops", 0.0);
        // sfm package 
        expectedValues.put("SimulateFactoryModel", 0.0);
        // simpleThreading package 
        expectedValues.put("AgentThread", 0.0);
        expectedValues.put("ChefThread", 1.0);
        expectedValues.put("Ingredients", 0.0);
        expectedValues.put("criticalClassInheritance", 0.0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0.0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getnumberOfClassesThatInheritFromEachCriticalSuperClass());
        }
    }
	

	
	@Test
    void testNumberOfCriticalClassesInProgram() {
        double  expectedValues = 3.0;
        assertEquals(expectedValues, mt.calc.getMurgePulledValues().getNumberOfCriticalClassesInProgram());
    }
	
	
	@Test
    void testNumberOfHierarchys() {
            assertEquals(2.0, mt.calc.getMurgePulledValues().getNumberOfHierarchys());
	}

	@Test
    void testNumberOfSerializableClassesInProject() {
            assertEquals(1.0, mt.calc.getMurgePulledValues().getNumberOfSerializableClassesInProject());
	}

	
	@Test
    void testGetPublicClassAttributes() {
            assertEquals(1.0, mt.calc.getMurgePulledValues().getNumberOfTopLevelCriticalSuperClasses());
    }
}
