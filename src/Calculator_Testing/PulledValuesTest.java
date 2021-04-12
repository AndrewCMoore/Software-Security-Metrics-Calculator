package Calculator_Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



public class PulledValuesTest {

	
	private static MainTest mt; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mt = new MainTest();
		
	}
	
	@Test
	void testGetPublicClassAttributes() {
		HashMap<String, Double> expectedValues = new HashMap<String, Double>();
		//factoryClasses package
		expectedValues.put("Components", 3.0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
		expectedValues.put("FactoryObject", 0.0);
		expectedValues.put("Inspectors", 0.0);
		expectedValues.put("ObjectStates", 4.0);
		expectedValues.put("Products", 3.0);
		expectedValues.put("WorkStations", 1.0);
		// pointless package
		expectedValues.put("pointlessClass", 0.0);
		expectedValues.put("pointlessInterface", 0.0);
		expectedValues.put("pointlessLoops", 0.0);
		// sfm package
		expectedValues.put("SimulateFactoryModel", 0.0);
		// simpleThreading package
		expectedValues.put("AgentThread", 1.0);
		expectedValues.put("ChefThread", 0.0);
		expectedValues.put("Ingredients", 3.0);
		expectedValues.put("criticalClassInheritance", 0.0);
		// weibullGenerator package
		expectedValues.put("GenerateWeibullDistributionData", 0.0);

		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapPublicClass().get(key));
		}
	}

  @Test
  public void testGetPublicInstanceAttributes() {
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
		expectedValues.put("pointlessClass", 1.0);
		expectedValues.put("pointlessInterface", 0.0);
		expectedValues.put("pointlessLoops", 1.0);
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
			assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapPublicInstance().get(key));
		}

	}
  @Test
  public void testGetMapNonFinalPrivateProtectedMethods() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 1.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 1.0);
    expectedValues.put("FactoryObject", 0.0);
    expectedValues.put("Inspectors", 4.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 2.0);
    // pointless package
    expectedValues.put("pointlessClass", 0.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 0.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 3.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 0.0);
    expectedValues.put("ChefThread", 0.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapNonFinalPrivateProtectedMethods().get(key));
    }

  }
  @Test
  public void testGetMapClassifiedMethods() {
		HashMap<String, Double> expectedValues = new HashMap<String, Double>();
		//factoryClasses package
		expectedValues.put("Components", 0.0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 1.0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 1.0);
		expectedValues.put("FactoryObject", 0.0);
		expectedValues.put("Inspectors", 4.0);
		expectedValues.put("ObjectStates", 0.0);
		expectedValues.put("Products", 0.0);
		expectedValues.put("WorkStations", 2.0);
		// pointless package
		expectedValues.put("pointlessClass", 0.0);
		expectedValues.put("pointlessInterface", 0.0);
		expectedValues.put("pointlessLoops", 0.0);
		// sfm package
		expectedValues.put("SimulateFactoryModel", 3.0);
		// simpleThreading package
		expectedValues.put("AgentThread", 0.0);
		expectedValues.put("ChefThread", 0.0);
		expectedValues.put("Ingredients", 0.0);
		expectedValues.put("criticalClassInheritance", 0.0);
		// weibullGenerator package
		expectedValues.put("GenerateWeibullDistributionData", 0.0);

		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapClassifiedMethods().get(key));
		}

	}
  @Test
  public void testGetMapPublicMethods() {
		HashMap<String, Double> expectedValues = new HashMap<String, Double>();
		//factoryClasses package
		expectedValues.put("Components", 0.0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 2.0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 1.0);
		expectedValues.put("FactoryObject", 13.0);
		expectedValues.put("Inspectors", 5.0);
		expectedValues.put("ObjectStates", 0.0);
		expectedValues.put("Products", 0.0);
		expectedValues.put("WorkStations", 7.0);
		// pointless package
		expectedValues.put("pointlessClass", 1.0);
		expectedValues.put("pointlessInterface", 1.0);
		expectedValues.put("pointlessLoops", 6.0);
		// sfm package
		expectedValues.put("SimulateFactoryModel", 1.0);
		// simpleThreading package
		expectedValues.put("AgentThread", 10.0);
		expectedValues.put("ChefThread", 3.0);
		expectedValues.put("Ingredients", 0.0);
		expectedValues.put("criticalClassInheritance", 0.0);
		// weibullGenerator package
		expectedValues.put("GenerateWeibullDistributionData", 2.0);

		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapPublicMethods().get(key));
		}

	}
  @Test
  public void testGetMapTotalMethods() {
		HashMap<String, Double> expectedValues = new HashMap<String, Double>();
		//factoryClasses package
		expectedValues.put("Components", 0.0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 3.0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 2.0);
		expectedValues.put("FactoryObject", 13.0);
		expectedValues.put("Inspectors", 9.0);
		expectedValues.put("ObjectStates", 0.0);
		expectedValues.put("Products", 0.0);
		expectedValues.put("WorkStations", 9.0);
		// pointless package
		expectedValues.put("pointlessClass", 1.0);
		expectedValues.put("pointlessInterface", 1.0);
		expectedValues.put("pointlessLoops", 6.0);
		// sfm package
		expectedValues.put("SimulateFactoryModel", 4.0);
		// simpleThreading package
		expectedValues.put("AgentThread", 10.0);
		expectedValues.put("ChefThread", 3.0);
		expectedValues.put("Ingredients", 0.0);
		expectedValues.put("criticalClassInheritance", 0.0);
		// weibullGenerator package
		expectedValues.put("GenerateWeibullDistributionData", 2.0);

		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapTotalMethods().get(key));
		}

	}
  @Test
  public void testGetMapMutatorInteractions() {
		HashMap<String, Double> expectedValues = new HashMap<String, Double>();
		//factoryClasses package
		expectedValues.put("Components", 0.0);
		expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
		expectedValues.put("FactoryObject_InheritanceLevel2", 1.0);
		expectedValues.put("FactoryObject", 2.0);
		expectedValues.put("Inspectors", 6.0);
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
		expectedValues.put("AgentThread", 1.0);
		expectedValues.put("ChefThread", 0.0);
		expectedValues.put("Ingredients", 0.0);
		expectedValues.put("criticalClassInheritance", 0.0);
		// weibullGenerator package
		expectedValues.put("GenerateWeibullDistributionData", 0.0);

		for(String key : expectedValues.keySet()) {
			assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapMutatorInteractions().get(key));
		}

	}
  @Test
  public void testGetMapAccessorInteractions() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
    expectedValues.put("FactoryObject", 4.0);
    expectedValues.put("Inspectors", 0.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 1.0);
    // pointless package
    expectedValues.put("pointlessClass", 0.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 0.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 1.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 1.0);
    expectedValues.put("ChefThread", 0.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapAccessorInteractions().get(key));
    }

  }
  @Test
  public void testGetMapMethodInvocations() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 2.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 7.0);
    expectedValues.put("FactoryObject", 7.0);
    expectedValues.put("Inspectors", 50.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 29.0);
    // pointless package
    expectedValues.put("pointlessClass", 1.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 1.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 55.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 17.0);
    expectedValues.put("ChefThread", 17.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 32.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapMethodInvocations().get(key));
    }

  }
  @Test
  public void testGetMapWritesClassifiedAttributes() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 1.0);
    expectedValues.put("FactoryObject", 2.0);
    expectedValues.put("Inspectors", 3.0);
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
    expectedValues.put("AgentThread", 1.0);
    expectedValues.put("ChefThread", 0.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapWritesClassifiedAttributes().get(key));
    }

  }
  @Test
  public void testGetMapAccessClassifiedNeverCalled() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
    expectedValues.put("FactoryObject", 4.0);
    expectedValues.put("Inspectors", 0.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 1.0);
    // pointless package
    expectedValues.put("pointlessClass", 0.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 0.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 1.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 1.0);
    expectedValues.put("ChefThread", 0.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapAccessClassifiedNeverCalled().get(key));
    }

  }
  @Test
  public void testGetMapPrivateProtectedInstance() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 1.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 3.0);
    expectedValues.put("FactoryObject", 5.0);
    expectedValues.put("Inspectors", 5.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 4.0);
    // pointless package
    expectedValues.put("pointlessClass", 0.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 1.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 1.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 4.0);
    expectedValues.put("ChefThread", 3.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapPrivateProtectedInstance().get(key));
    }

  }
  @Test
  public void testGetMapPrivateProtectedClass() {
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
    expectedValues.put("pointlessLoops", 2.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 4.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 2.0);
    expectedValues.put("ChefThread", 0.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapPrivateProtectedClass().get(key));
    }

  }
  @Test
  public void testGetMapPrivateProtectedTotal() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 1.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 3.0);
    expectedValues.put("FactoryObject", 5.0);
    expectedValues.put("Inspectors", 5.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 4.0);
    // pointless package
    expectedValues.put("pointlessClass", 0.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 3.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 5.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 6.0);
    expectedValues.put("ChefThread", 3.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapPrivateProtectedTotal().get(key));
    }

  }
  @Test
  public void testGetMapTotalAttributes() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 3.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 1.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 3.0);
    expectedValues.put("FactoryObject", 5.0);
    expectedValues.put("Inspectors", 5.0);
    expectedValues.put("ObjectStates", 4.0);
    expectedValues.put("Products", 3.0);
    expectedValues.put("WorkStations", 5.0);
    // pointless package
    expectedValues.put("pointlessClass", 1.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 4.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 5.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 7.0);
    expectedValues.put("ChefThread", 3.0);
    expectedValues.put("Ingredients", 3.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapTotalAttributes().get(key));
    }

  }
  @Test
  public void testGetMapCriticalElements() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 3.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 1.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 3.0);
    expectedValues.put("FactoryObject", 5.0);
    expectedValues.put("Inspectors", 5.0);
    expectedValues.put("ObjectStates", 4.0);
    expectedValues.put("Products", 3.0);
    expectedValues.put("WorkStations", 5.0);
    // pointless package
    expectedValues.put("pointlessClass", 1.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 4.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 5.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 7.0);
    expectedValues.put("ChefThread", 3.0);
    expectedValues.put("Ingredients", 3.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapCriticalElements().get(key));
    }

  }
  @Test
  public void testGetMapCriticalNotUsed() {
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
    expectedValues.put("ChefThread", 0.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapCriticalNotUsed().get(key));
    }

  }
  @Test
  public void testGetMapAttributeInteractions() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 3.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 2.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 5.0);
    expectedValues.put("FactoryObject", 29.0);
    expectedValues.put("Inspectors", 31.0);
    expectedValues.put("ObjectStates", 4.0);
    expectedValues.put("Products", 3.0);
    expectedValues.put("WorkStations", 28.0);
    // pointless package
    expectedValues.put("pointlessClass", 2.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 26.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 18.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 28.0);
    expectedValues.put("ChefThread", 12.0);
    expectedValues.put("Ingredients", 3.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapAttributeInteractions().get(key));
    }

  }
  @Test
  public void testGetMapClassifiedAttributeInteractions() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 2.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 5.0);
    expectedValues.put("FactoryObject", 29.0);
    expectedValues.put("Inspectors", 31.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 26.0);
    // pointless package
    expectedValues.put("pointlessClass", 0.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 12.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 18.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 25.0);
    expectedValues.put("ChefThread", 12.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapClassifiedAttributeInteractions().get(key));
    }

  }
  @Test
  public void testGetMapClassifiedInstanceAttributeNotPrivate() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 1.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 2.0);
    expectedValues.put("FactoryObject", 0.0);
    expectedValues.put("Inspectors", 0.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 0.0);
    // pointless package
    expectedValues.put("pointlessClass", 1.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 1.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 1.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 0.0);
    expectedValues.put("ChefThread", 1.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapClassifiedInstanceAttributeNotPrivate().get(key));
    }

  }
  @Test
  public void testGetMapClassifiedClassAttributeNotPrivate() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 3.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
    expectedValues.put("FactoryObject", 0.0);
    expectedValues.put("Inspectors", 0.0);
    expectedValues.put("ObjectStates", 4.0);
    expectedValues.put("Products", 3.0);
    expectedValues.put("WorkStations", 1.0);
    // pointless package
    expectedValues.put("pointlessClass", 0.0);
    expectedValues.put("pointlessInterface", 0.0);
    expectedValues.put("pointlessLoops", 0.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 0.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 1.0);
    expectedValues.put("ChefThread", 0.0);
    expectedValues.put("Ingredients", 3.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapClassifiedClassAttributeNotPrivate().get(key));
    }

  }
  @Test
  public void testGetMapClassifiedMethodsNotPrivate() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 1.0);
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
    expectedValues.put("ChefThread", 0.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 0.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapClassifiedMethodsNotPrivate().get(key));
    }

  }
  @Test
  public void testGetMapStrictComplexity() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 3.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 2.0);
    expectedValues.put("FactoryObject", 13.0);
    expectedValues.put("Inspectors", 11.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 13.0);
    // pointless package
    expectedValues.put("pointlessClass", 1.0);
    expectedValues.put("pointlessInterface", 1.0);
    expectedValues.put("pointlessLoops", 6.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 4.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 10.0);
    expectedValues.put("ChefThread", 4.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 2.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapStrictComplexity().get(key));
    }

  }
  @Test
  public void testGetMapCyclomaticComplexity() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 3.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 2.0);
    expectedValues.put("FactoryObject", 13.0);
    expectedValues.put("Inspectors", 9.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 10.0);
    // pointless package
    expectedValues.put("pointlessClass", 1.0);
    expectedValues.put("pointlessInterface", 1.0);
    expectedValues.put("pointlessLoops", 6.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 4.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 10.0);
    expectedValues.put("ChefThread", 3.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 2.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapCyclomaticComplexity().get(key));
    }

  }
  @Test
  public void testGetMapModifiedComplexity() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 3.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 2.0);
    expectedValues.put("FactoryObject", 13.0);
    expectedValues.put("Inspectors", 9.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 10.0);
    // pointless package
    expectedValues.put("pointlessClass", 1.0);
    expectedValues.put("pointlessInterface", 1.0);
    expectedValues.put("pointlessLoops", 6.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 4.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 10.0);
    expectedValues.put("ChefThread", 3.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 2.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapModifiedComplexity().get(key));
    }

  }
  @Test
  public void testGetMapMcCabesComplexity() {
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
    expectedValues.put("pointlessInterface", 1.0);
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
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapMcCabesComplexity().get(key));
    }

  }
  @Test
  public void testGetMapCountPath() {
    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
    //factoryClasses package
    expectedValues.put("Components", 0.0);
    expectedValues.put("FactoryObject_InheritanceLevel1", 3.0);
    expectedValues.put("FactoryObject_InheritanceLevel2", 2.0);
    expectedValues.put("FactoryObject", 13.0);
    expectedValues.put("Inspectors", 9.0);
    expectedValues.put("ObjectStates", 0.0);
    expectedValues.put("Products", 0.0);
    expectedValues.put("WorkStations", 9.0);
    // pointless package
    expectedValues.put("pointlessClass", 1.0);
    expectedValues.put("pointlessInterface", 1.0);
    expectedValues.put("pointlessLoops", 6.0);
    // sfm package
    expectedValues.put("SimulateFactoryModel", 4.0);
    // simpleThreading package
    expectedValues.put("AgentThread", 10.0);
    expectedValues.put("ChefThread", 3.0);
    expectedValues.put("Ingredients", 0.0);
    expectedValues.put("criticalClassInheritance", 0.0);
    // weibullGenerator package
    expectedValues.put("GenerateWeibullDistributionData", 2.0);

    for(String key : expectedValues.keySet()) {
      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapCountPath().get(key));
    }

  }
  @Test
  public void testGetMapMethodInputs() {
	    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
	    //factoryClasses package
	    expectedValues.put("Components", 0.0);
	    expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
	    expectedValues.put("FactoryObject_InheritanceLevel2", 1.0);
	    expectedValues.put("FactoryObject", 7.0);
	    expectedValues.put("Inspectors", 7.0);
	    expectedValues.put("ObjectStates", 0.0);
	    expectedValues.put("Products", 0.0);
	    expectedValues.put("WorkStations", 6.0);
	    // pointless package
	    expectedValues.put("pointlessClass", 0.0);
	    expectedValues.put("pointlessInterface", 0.0);
	    expectedValues.put("pointlessLoops", 0.0);
	    // sfm package
	    expectedValues.put("SimulateFactoryModel", 2.0);
	    // simpleThreading package
	    expectedValues.put("AgentThread", 2.0);
	    expectedValues.put("ChefThread", 0.0);
	    expectedValues.put("Ingredients", 0.0);
	    expectedValues.put("criticalClassInheritance", 0.0);
	    // weibullGenerator package
	    expectedValues.put("GenerateWeibullDistributionData", 3.0);

	    for(String key : expectedValues.keySet()) {
	      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapMethodInputs().get(key));
	    }

	  }
  		@Test
	  public void testGetMapMethodOutputs() {
	    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
	    //factoryClasses package
	    expectedValues.put("Components", 0.0);
	    expectedValues.put("FactoryObject_InheritanceLevel1", 1.0);
	    expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
	    expectedValues.put("FactoryObject", 7.0);
	    expectedValues.put("Inspectors", 2.0);
	    expectedValues.put("ObjectStates", 0.0);
	    expectedValues.put("Products", 0.0);
	    expectedValues.put("WorkStations", 1.0);
	    // pointless package
	    expectedValues.put("pointlessClass", 0.0);
	    expectedValues.put("pointlessInterface", 0.0);
	    expectedValues.put("pointlessLoops", 0.0);
	    // sfm package
	    expectedValues.put("SimulateFactoryModel", 3.0);
	    // simpleThreading package
	    expectedValues.put("AgentThread", 1.0);
	    expectedValues.put("ChefThread", 0.0);
	    expectedValues.put("Ingredients", 0.0);
	    expectedValues.put("criticalClassInheritance", 0.0);
	    // weibullGenerator package
	    expectedValues.put("GenerateWeibullDistributionData", 0.0);

	    for(String key : expectedValues.keySet()) {
	      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapMethodOutputs().get(key));
	    }

	  }
  		 @Test	
	  public void testGetMapHenryKafura() {
	    HashMap<String, Double> expectedValues = new HashMap<String, Double>();
	    //factoryClasses package
	    expectedValues.put("Components", 0.0);
	    expectedValues.put("FactoryObject_InheritanceLevel1", 0.0);
	    expectedValues.put("FactoryObject_InheritanceLevel2", 0.0);
	    expectedValues.put("FactoryObject", 5.0);
	    expectedValues.put("Inspectors", 0.0);
	    expectedValues.put("ObjectStates", 0.0);
	    expectedValues.put("Products", 0.0);
	    expectedValues.put("WorkStations", 0.0);
	    // pointless package
	    expectedValues.put("pointlessClass", 0.0);
	    expectedValues.put("pointlessInterface", 0.0);
	    expectedValues.put("pointlessLoops", 0.0);
	    // sfm package
	    expectedValues.put("SimulateFactoryModel", 26.0);
	    // simpleThreading package
	    expectedValues.put("AgentThread", 0.0);
	    expectedValues.put("ChefThread", 0.0);
	    expectedValues.put("Ingredients", 0.0);
	    expectedValues.put("criticalClassInheritance", 0.0);
	    // weibullGenerator package
	    expectedValues.put("GenerateWeibullDistributionData", 0.0);

	    for(String key : expectedValues.keySet()) {
	      assertEquals(expectedValues.get(key), mt.calc.getPulledValues().getMapHenryKafura().get(key));
	    }

	  }

}
