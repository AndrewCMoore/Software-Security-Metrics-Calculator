package Calculator;

import java.util.HashMap;

public class TertiaryMetrics {
	private HashMap<String, Float> classifiedInstanceDataAccessibility  = new HashMap<String, Float>();
	private HashMap<String, Float> classifiedClassDataAccessibility = new HashMap<String, Float>();
	private HashMap<String, Float> classifiedOperationAccessibility = new HashMap<String, Float>();
	private HashMap<String, Float> classifiedMethodsExtensibility = new HashMap<String, Float>();
	private HashMap<String, Float> classifiedAccessorAttributeInteractions = new HashMap<String, Float>();
	private HashMap<String, Float> classifiedMutatorAttributeInteractions = new HashMap<String, Float>();
	private HashMap<String, Float> classifiedAttributesInteractionWeight = new HashMap<String, Float>();
	private HashMap<String, Float> classifiedMethodsWeight = new HashMap<String, Float>();
	private HashMap<String, Float> classifiedWritingMethodsProportion = new HashMap<String, Float>();
	private HashMap<String, Float> uncalledClassifiedAccessorMethod = new HashMap<String, Float>();
	//private HashMap<String, Float> unaccessedAssignedClassifiedAttribute = new HashMap<String, Float>();
	private int classifiedAttributesTotal = 0;
	private int classifiedMethodsTotal = 0;

	public TertiaryMetrics(PulledValues pv) { 
		classifiedInstanceDataAccessibility(pv);
		classifiedClassDataAccessibility(pv);
		classifiedOperationAccessibility(pv);
		classifiedMethodsExtensibility(pv);
		classifiedAttributesTotal(pv);
		classifiedMethodsTotal(pv);
		classifiedAccessorAttributeInteractions(pv);
		classifiedMutatorAttributeInteractions(pv);
		classifiedAttributesInteractionWeight(pv);
		classifiedMethodsWeight(pv);
		classifiedWritingMethodsProportion(pv);
		uncalledClassifiedAccessorMethod(pv);
		//unaccessedAssignedClassifiedAttribute(pv);
	}
	
	private void uncalledClassifiedAccessorMethod(PulledValues pv) {
		HashMap<String, Integer> classifiedNeverUsed = pv.getMapAccessClassifiedNeverCalled();
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			uncalledClassifiedAccessorMethod.put(key, (float) classifiedNeverUsed.get(key)/classifiedMethods.get(key));
		}
	}

	private void classifiedWritingMethodsProportion(PulledValues pv) {
		HashMap<String, Integer> methodsWriteClassifiedAttributes = pv.getMapWritesClassifiedAttributes();
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			classifiedWritingMethodsProportion.put(key, (float) methodsWriteClassifiedAttributes.get(key)/classifiedMethods.get(key));
		}
	}

	private void classifiedMethodsWeight(PulledValues pv) {
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		HashMap<String, Integer> totalMethods = pv.getMapTotalMethods();
		
		for(String key : totalMethods.keySet()) {
			classifiedMethodsWeight.put(key, (float) classifiedMethods.get(key)/totalMethods.get(key));
		}
	}

	private void classifiedAttributesInteractionWeight(PulledValues pv) {
		HashMap<String, Integer> classifiedAttributeInteractions = pv.getMapClassifiedAttributeInteractions();
		HashMap<String, Integer> attributeInteractions = pv.getMapAccessorInteractions();
		
		for(String key : attributeInteractions.keySet()) {
			classifiedAttributesInteractionWeight.put(key, (float) classifiedAttributeInteractions.get(key)/attributeInteractions.get(key));
		}
	}

	private void classifiedMutatorAttributeInteractions(PulledValues pv) {
		HashMap<String, Integer> mutatorInteractions = pv.getMapMutatorInteractions();
		HashMap<String, Integer> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : mutatorInteractions.keySet()) {
			classifiedMutatorAttributeInteractions.put(key, (float) mutatorInteractions.get(key) / (mutatorInteractions.get(key) * classifiedAttributes.get(key)));
		}
	}

	private void classifiedAccessorAttributeInteractions(PulledValues pv) {
		HashMap<String, Integer> accessorInteractions = pv.getMapAccessorInteractions();
		HashMap<String, Integer> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : accessorInteractions.keySet()) {
			classifiedAccessorAttributeInteractions.put(key, (float) accessorInteractions.get(key) / (accessorInteractions.get(key) * classifiedAttributes.get(key)));
		}
	}

	private void classifiedMethodsTotal(PulledValues pv) {
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			classifiedMethodsTotal += pv.getMapClassifiedMethods().get(key);
		}
	}

	private void classifiedAttributesTotal(PulledValues pv) {
		HashMap<String, Integer> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : classifiedAttributes.keySet()) {
			classifiedAttributesTotal += pv.getMapTotalAttributes().get(key);
		}
	}

	private void classifiedMethodsExtensibility(PulledValues pv) {
		HashMap<String, Integer> nonFinalClassifiedMethods = pv.getMapNonFinalPrivateProtectedMethods();
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			if(classifiedMethods.get(key) != 0) {
				classifiedMethodsExtensibility.put(key, (float) (nonFinalClassifiedMethods.get(key)/classifiedMethods.get(key)));
			}
			else {
				classifiedMethodsExtensibility.put(key, (float) 0);
			}
		}
	}

	private void classifiedOperationAccessibility(PulledValues pv) {
		HashMap<String, Integer> classifiedMethodsNotPrivate = pv.getMapClassifiedMethodsNotPrivate();
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			if(classifiedMethods.get(key) != 0) {
				classifiedOperationAccessibility.put(key, (float) (classifiedMethodsNotPrivate.get(key)/classifiedMethods.get(key)));
			}
			else {
				classifiedOperationAccessibility.put(key, (float) 0);
			}
		}
	}

	private void classifiedClassDataAccessibility(PulledValues pv) {
		HashMap<String, Integer> classifiedClassNotPrivate = pv.getMapClassifiedClassAttributeNotPrivate();
		HashMap<String, Integer> privateProtectedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : privateProtectedAttributes.keySet()) {
			if(privateProtectedAttributes.get(key) != 0) {
				classifiedClassDataAccessibility.put(key, (float) (classifiedClassNotPrivate.get(key)/privateProtectedAttributes.get(key)));
			}
			else {
				classifiedClassDataAccessibility.put(key, (float) 0);
			}
		}	
	}

	private void classifiedInstanceDataAccessibility(PulledValues pv) {
		HashMap<String, Integer> classifiedInstanceNotPrivate = pv.getMapPublicInstance();
		HashMap<String, Integer> privateProtectedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : privateProtectedAttributes.keySet()) {
			if(privateProtectedAttributes.get(key) != 0) {
				classifiedInstanceDataAccessibility.put(key, (float) (classifiedInstanceNotPrivate.get(key)/privateProtectedAttributes.get(key)));		
			}
			else {
				classifiedInstanceDataAccessibility.put(key, (float) 0);
			}
		}
	}
	
	

}
