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
	private HashMap<String, Float> classifiedMethodsInheritance = new HashMap<String, Float>(); //CMI metric.
	private HashMap<String, Float> classifiedAttributesInheritance = new HashMap<String, Float>();
	private HashMap<String, Float> unaccessedAssignedClassifiedAttribute = new HashMap<String, Float>();
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
			uncalledClassifiedAccessorMethod.put(key, (float) classifiedNeverUsed.get(key)/classifiedMethods.get(key));
		}
	}

	private void classifiedWritingMethodsProportion(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> methodsWriteClassifiedAttributes = pv.getMapWritesClassifiedAttributes();
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			classifiedWritingMethodsProportion.put(key, (float) methodsWriteClassifiedAttributes.get(key)/classifiedMethods.get(key));
		}
	}

	private void classifiedMethodsWeight(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> classifiedMethods = pv.getMapClassifiedMethods();
		HashMap<String, Integer> totalMethods = pv.getMapTotalMethods();
		
		for(String key : totalMethods.keySet()) {
			classifiedMethodsWeight.put(key, (float) classifiedMethods.get(key)/totalMethods.get(key));
		}
	}

	private void classifiedAttributesInteractionWeight(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> classifiedAttributeInteractions = pv.getMapClassifiedAttributeInteractions();
		HashMap<String, Integer> attributeInteractions = pv.getMapAccessorInteractions();
		
		for(String key : attributeInteractions.keySet()) {
			classifiedAttributesInteractionWeight.put(key, (float) classifiedAttributeInteractions.get(key)/attributeInteractions.get(key));
		}
	}

	private void classifiedMutatorAttributeInteractions(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> mutatorInteractions = pv.getMapMutatorInteractions();
		HashMap<String, Integer> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : mutatorInteractions.keySet()) {
			classifiedMutatorAttributeInteractions.put(key, (float) mutatorInteractions.get(key) / (mutatorInteractions.get(key) * classifiedAttributes.get(key)));
		}
	}

	private void classifiedAccessorAttributeInteractions(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Integer> accessorInteractions = pv.getMapAccessorInteractions();
		HashMap<String, Integer> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : accessorInteractions.keySet()) {
			classifiedAccessorAttributeInteractions.put(key, (float) accessorInteractions.get(key) / (accessorInteractions.get(key) * classifiedAttributes.get(key)));
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
				classifiedMethodsExtensibility.put(key, (float) (nonFinalClassifiedMethods.get(key)/classifiedMethods.get(key)));
			}
			else {
				classifiedMethodsExtensibility.put(key, (float) 0);
			}
		}
	}

	private void classifiedOperationAccessibility(PulledValues pv, MurgePulledValues mpv) {
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

	private void classifiedClassDataAccessibility(PulledValues pv, MurgePulledValues mpv) {
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

	private void classifiedInstanceDataAccessibility(PulledValues pv, MurgePulledValues mpv) {
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
	

	public HashMap<String, Float> getClassifiedInstanceDataAccessibility() {
		return classifiedInstanceDataAccessibility;
	}

	public HashMap<String, Float> getClassifiedClassDataAccessibility() {
		return classifiedClassDataAccessibility;
	}

	public HashMap<String, Float> getClassifiedOperationAccessibility() {
		return classifiedOperationAccessibility;
	}

	public HashMap<String, Float> getClassifiedMethodsExtensibility() {
		return classifiedMethodsExtensibility;
	}

	public HashMap<String, Float> getClassifiedAccessorAttributeInteractions() {
		return classifiedAccessorAttributeInteractions;
	}

	public HashMap<String, Float> getClassifiedMutatorAttributeInteractions() {
		return classifiedMutatorAttributeInteractions;
	}

	public HashMap<String, Float> getClassifiedAttributesInteractionWeight() {
		return classifiedAttributesInteractionWeight;
	}

	public HashMap<String, Float> getClassifiedMethodsWeight() {
		return classifiedMethodsWeight;
	}

	public HashMap<String, Float> getClassifiedWritingMethodsProportion() {
		return classifiedWritingMethodsProportion;
	}

	public HashMap<String, Float> getUncalledClassifiedAccessorMethod() {
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

	public HashMap<String, Float> getClassifiedAttributesInheritance() {
		//return classifiedAttributesInheritance;
		return classifiedClassDataAccessibility;
	}

	public HashMap<String, Float> getUnaccessedAssignedClassifiedAttribute() {
		//return unaccessedAssignedClassifiedAttribute;
		return classifiedMethodsWeight;
	}
	public HashMap<String, Float> getClassifiedMethodsInheritance () {
		//classifiedMethodsInheritance.put("Temperary Placeholder", (float) 1.00);
		return classifiedOperationAccessibility;
	}
	

	

}
