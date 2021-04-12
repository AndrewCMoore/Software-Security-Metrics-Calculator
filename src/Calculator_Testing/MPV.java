package Calculator_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getAllHierarchySize());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getBaseClassNames());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getClassCouplingRelationship());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getClassesCoupledToBaseClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getClassMethodsInheritedBySubClassm());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getComplexityDepthInClassMethods());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getCriticalBaseClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getCriticalClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getCriticalSerializedClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getDepthOfInheritanceTreeAtCurrentSuperClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getImidiateChildren());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getImportBooleanReflectionClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getLngthOfEachMethodInEachClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getLoopSizeInClassMethods());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getMethodNamesInClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getMethodsInClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getMethodsInheritedBySubClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNonFinalizedCriticalClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfAccesibleMethods());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfAttributesInheritedByAClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfBaseClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfBaseClassMethodsInheritedBySubClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfClassesInProject());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getnumberOfClassesThatInheritFromEachCriticalSuperClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfCriticalBaseClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfCriticalClassesInProgram());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfCriticalClassesInProgramHeirchy());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfCriticalSuperClassesInHierarchy());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfHierarchys());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfMethodsInheritedByAClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfNonFinalizedCriticalClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfPrivateMethodsInClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfProtectedMethodsInClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfSerializableClassesInProject());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfTopLevelCriticalSuperClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfUniqueAttributesTypesInClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getNumberOfUniqueParametersInClassForAllMethods());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getParametersInEachMethodInEachClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getProtectedMethodsInClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getReflectionPackageClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getSumOfAllInstanceMethodsInClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getSumOfAllInstanceVaribles());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getSumOfClassesWhichMayInheritFromEachCriticalSuperClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getSumOfNumberOfClassesInheritingFromCriticalBaseClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getTotalNumberOfAttributesInheritedInAClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getTotalNumberOfCommentedLinesInEachClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getTotalNumberOfCriticalSubClasses());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getTotalNumberOfLinesInEachClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getTotalNumberOfMethodsAccesible());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getTotalNumberOfMethodsInheritedInAClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getUniqueParamatersInEachMethodInEachClass());
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
        expectedValues.put("criticalClassInheritance", 0);
        // weibullGenerator package 
        expectedValues.put("GenerateWeibullDistributionData", 0);

        for(String key : expectedValues.keySet()) {
            assertEquals(expectedValues.get(key), mt.calc.getMurgePulledValues().getUnusedClassifiedMethods());
        }
    }

}
