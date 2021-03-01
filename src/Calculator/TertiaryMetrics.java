package Calculator;

import java.util.HashMap;

public class TertiaryMetrics {
	private HashMap<String, Double> classifiedInstanceDataAccessibility  = new HashMap<String, Double>();
	private HashMap<String, Double> classifiedClassDataAccessibility = new HashMap<String, Double>();
	private HashMap<String, Double> classifiedOperationAccessibility = new HashMap<String, Double>();
	private HashMap<String, Double> classifiedMethodsExtensibility = new HashMap<String, Double>();
	private HashMap<String, Double> classifiedAccessorAttributeInteractions = new HashMap<String, Double>();
	private HashMap<String, Double> classifiedMutatorAttributeInteractions = new HashMap<String, Double>();
	private HashMap<String, Double> classifiedAttributesInteractionWeight = new HashMap<String, Double>();
	private HashMap<String, Double> classifiedMethodsWeight = new HashMap<String, Double>();
	private HashMap<String, Double> classifiedWritingMethodsProportion = new HashMap<String, Double>();
	private HashMap<String, Double> uncalledClassifiedAccessorMethod = new HashMap<String, Double>();
	private HashMap<String, Double> classifiedMethodsInheritance = new HashMap<String, Double>(); //CMI metric.
	private HashMap<String, Double> classifiedAttributesInheritance = new HashMap<String, Double>();
	private HashMap<String, Double> unaccessedAssignedClassifiedAttribute = new HashMap<String, Double>();
	private int classifiedAttributesTotal = 0;
	private int classifiedMethodsTotal = 0;
	private int criticalClassesTotal = 0;
	
	//all of these are project level ratio that can't be reduced to the class level :(
	private float criticalClassesExtensibility = 0;
	private float criticalClassesCoupling = 0;
	private float compositePartCriticalClasses = 0;
	private float unusedCriticalAccessorClass=0;
	private float criticalDesignProportion=0;
	private float criticalSerializedClassesProportion=0;
	private float criticalSuperclassesProportion=0;
	private float criticalSuperclassesInheritance=0;
	private float reflectionPackageBoolean=0;
	//private float unusedCriticalAccessorClass = 0;

	

	public TertiaryMetrics(PulledValues pv, MurgePulledValues mpv) { 
		classifiedInstanceDataAccessibility(pv,mpv);
		classifiedClassDataAccessibility(pv,mpv);
		classifiedOperationAccessibility(pv,mpv);
		classifiedMethodsExtensibility(pv,mpv);
		classifiedAttributesTotal(pv,mpv);
		classifiedMethodsTotal(pv,mpv);
		classifiedAccessorAttributeInteractions(pv,mpv);
		classifiedMutatorAttributeInteractions(pv,mpv);
		classifiedAttributesInteractionWeight(pv,mpv);
		classifiedMethodsWeight(pv,mpv);
		classifiedWritingMethodsProportion(pv,mpv);
		uncalledClassifiedAccessorMethod(pv,mpv);
		//unaccessedAssignedClassifiedAttribute(pv,mpv);
	}
	
	private void uncalledClassifiedAccessorMethod(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> classifiedNeverUsed = pv.getMapAccessClassifiedNeverCalled();
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			uncalledClassifiedAccessorMethod.put(key, (double) classifiedNeverUsed.get(key)/classifiedMethods.get(key));
		}
	}

	private void classifiedWritingMethodsProportion(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> methodsWriteClassifiedAttributes = pv.getMapWritesClassifiedAttributes();
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			classifiedWritingMethodsProportion.put(key, (double) methodsWriteClassifiedAttributes.get(key)/classifiedMethods.get(key));
		}
	}

	private void classifiedMethodsWeight(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		HashMap<String, Integer> totalMethods = pv.getMapTotalMethods();
		
		for(String key : totalMethods.keySet()) {
			classifiedMethodsWeight.put(key, (double) classifiedMethods.get(key)/totalMethods.get(key));
		}
	}

	private void classifiedAttributesInteractionWeight(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> classifiedAttributeInteractions = pv.getMapClassifiedAttributeInteractions();
		HashMap<String, Integer> attributeInteractions = pv.getMapAccessorInteractions();
		
		for(String key : attributeInteractions.keySet()) {
			classifiedAttributesInteractionWeight.put(key, (double) classifiedAttributeInteractions.get(key)/attributeInteractions.get(key));
		}
	}

	private void classifiedMutatorAttributeInteractions(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> mutatorInteractions = pv.getMapMutatorInteractions();
		HashMap<String, Integer> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : mutatorInteractions.keySet()) {
			classifiedMutatorAttributeInteractions.put(key, (double) mutatorInteractions.get(key) / (mutatorInteractions.get(key) * classifiedAttributes.get(key)));
		}
	}

	private void classifiedAccessorAttributeInteractions(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> accessorInteractions = pv.getMapAccessorInteractions();
		HashMap<String, Integer> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : accessorInteractions.keySet()) {
			classifiedAccessorAttributeInteractions.put(key, (double) accessorInteractions.get(key) / (accessorInteractions.get(key) * classifiedAttributes.get(key)));
		}
	}

	private void classifiedMethodsTotal(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			classifiedMethodsTotal += pv.getMapClassifiedMethods().get(key);
		}
	}

	private void classifiedAttributesTotal(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : classifiedAttributes.keySet()) {
			classifiedAttributesTotal += pv.getMapTotalAttributes().get(key);
		}
	}

	private void classifiedMethodsExtensibility(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> nonFinalClassifiedMethods = pv.getMapNonFinalPrivateProtectedMethods();
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			if(classifiedMethods.get(key) != 0) {
				classifiedMethodsExtensibility.put(key, (double) (nonFinalClassifiedMethods.get(key)/classifiedMethods.get(key)));
			}
			else {
				classifiedMethodsExtensibility.put(key, (double) 0);
			}
		}
	}

	private void classifiedOperationAccessibility(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> classifiedMethodsNotPrivate = pv.getMapClassifiedMethodsNotPrivate();
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			if(classifiedMethods.get(key) != 0) {
				classifiedOperationAccessibility.put(key, (double) (classifiedMethodsNotPrivate.get(key)/classifiedMethods.get(key)));
			}
			else {
				classifiedOperationAccessibility.put(key, (double) 0);
			}
		}
	}

	private void classifiedClassDataAccessibility(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> classifiedClassNotPrivate = pv.getMapClassifiedClassAttributeNotPrivate();
		HashMap<String, Integer> privateProtectedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : privateProtectedAttributes.keySet()) {
			if(privateProtectedAttributes.get(key) != 0) {
				classifiedClassDataAccessibility.put(key, (double) (classifiedClassNotPrivate.get(key)/privateProtectedAttributes.get(key)));
			}
			else {
				classifiedClassDataAccessibility.put(key, (double) 0);
			}
		}	
	}

	private void classifiedInstanceDataAccessibility(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> classifiedInstanceNotPrivate = pv.getMapPublicInstance();
		HashMap<String, Integer> privateProtectedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : privateProtectedAttributes.keySet()) {
			if(privateProtectedAttributes.get(key) != 0) {
				classifiedInstanceDataAccessibility.put(key, (double) (classifiedInstanceNotPrivate.get(key)/privateProtectedAttributes.get(key)));		
			}
			else {
				classifiedInstanceDataAccessibility.put(key, (double) 0);
			}
		}
	}
	
	
	/**this was incorrect in the 100% diagram, it used TWO pulled values, not one. number of clasifed attributed that can be inherited in a hiarchy
	total # ot classifgied attributes in a programs inheritance hyarchy*/

	public void ClassifiedMethodsInheritance() {}		
	
	//total # CC
	private void criticalClassesTotal(PulledValues pv, MurgePulledValues mpv) {
		criticalClassesTotal = mpv.getNumberOfCriticalClassesInProgram();
	}
	
	//CCE
	private void criticalClassesExtensibility(PulledValues pv, MurgePulledValues mpv) {
		criticalClassesExtensibility=0;
	}
	
	//CCC
	private void criticalClassesCoupling(PulledValues pv, MurgePulledValues mpv) {
		criticalClassesCoupling=1;
	}
	
	//CPCC
	private void compositePartCriticalClasses(PulledValues pv, MurgePulledValues mpv) {
		compositePartCriticalClasses=1;
	}
	//UCAC
	private void unusedCriticalAccessorClass() {
		unusedCriticalAccessorClass=0;
	}
	
	//CDP
	private void criticalDesignProportion() {
		criticalDesignProportion=0;
	}
	
	//CSCP
	private void criticalSerializedClassesProportion() {
		criticalSerializedClassesProportion=0;
	}
	
	//CSP
	private void criticalSuperclassesProportion() {}
	
	//CSI
	private void criticalSuperclassesInheritance() {}
	
	//RPB - this is actually a boolean not an int sum. fix it in murge pulled values.
	private void reflectionPackageBoolean() {
		reflectionPackageBoolean=0;
	}
	
	//getters
	

	public HashMap<String, Double> getClassifiedInstanceDataAccessibility() {
		return classifiedInstanceDataAccessibility;
	}

	public HashMap<String, Double> getClassifiedClassDataAccessibility() {
		return classifiedClassDataAccessibility;
	}

	public HashMap<String, Double> getClassifiedOperationAccessibility() {
		return classifiedOperationAccessibility;
	}

	public HashMap<String, Double> getClassifiedMethodsExtensibility() {
		return classifiedMethodsExtensibility;
	}

	public HashMap<String, Double> getClassifiedAccessorAttributeInteractions() {
		return classifiedAccessorAttributeInteractions;
	}

	public HashMap<String, Double> getClassifiedMutatorAttributeInteractions() {
		return classifiedMutatorAttributeInteractions;
	}

	public HashMap<String, Double> getClassifiedAttributesInteractionWeight() {
		return classifiedAttributesInteractionWeight;
	}

	public HashMap<String, Double> getClassifiedMethodsWeight() {
		return classifiedMethodsWeight;
	}

	public HashMap<String, Double> getClassifiedWritingMethodsProportion() {
		return classifiedWritingMethodsProportion;
	}

	public HashMap<String, Double> getUncalledClassifiedAccessorMethod() {
		return uncalledClassifiedAccessorMethod;
	}

	public int getClassifiedAttributesTotal() {
		return classifiedAttributesTotal;
	}

	public int getClassifiedMethodsTotal() {
		return classifiedMethodsTotal;
	}

	public int getCriticalClassesTotal() {
		return criticalClassesTotal;
	}

	public float getCriticalClassesExtensibility() {
		return criticalClassesExtensibility;
	}

	public float getCriticalClassesCoupling() {
		return criticalClassesCoupling;
	}

	public float getCompositePartCriticalClasses() {
		return compositePartCriticalClasses;
	}

	public float getUnusedCriticalAccessorClass() {
		return unusedCriticalAccessorClass;
	}

	public float getCriticalDesignProportion() {
		return criticalDesignProportion;
	}

	public float getCriticalSerializedClassesProportion() {
		return criticalSerializedClassesProportion;
	}

	public float getCriticalSuperclassesProportion() {
		return criticalSuperclassesProportion;
	}

	public float getCriticalSuperclassesInheritance() {
		return criticalSuperclassesInheritance;
	}
	
	public float getReflectionPackageBoolean() {
		return reflectionPackageBoolean;
	}

	public HashMap<String, Double> getClassifiedAttributesInheritance() {
		//return classifiedAttributesInheritance;
		return classifiedClassDataAccessibility;
	}

	public HashMap<String, Double> getUnaccessedAssignedClassifiedAttribute() {
		//return unaccessedAssignedClassifiedAttribute;
		return classifiedMethodsWeight;
	}
	public HashMap<String, Double> getClassifiedMethodsInheritance () {
		//classifiedMethodsInheritance.put("Temperary Placeholder", (float) 1.00);
		return classifiedOperationAccessibility;
	}
	

	

}
