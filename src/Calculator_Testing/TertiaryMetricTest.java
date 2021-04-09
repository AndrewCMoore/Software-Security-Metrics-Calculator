package Calculator_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TertiaryMetricTest {

	private static MainTest mt; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mt = new MainTest();
		
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
            assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapPublicClass().get(key));
        }
    }
}
