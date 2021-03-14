package Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
	private static final double INT_TO_DOUBLE = 1.0;


	
	
	//all of these are project level ratio that can't be reduced to the class level :(
	private Double criticalClassesExtensibility;
	private Double criticalClassesCoupling;
	private Double compositePartCriticalClasses;
	private Double unusedCriticalAccessorClass;
	private Double criticalDesignProportion;
	private Double criticalSerializedClassesProportion;
	private Double criticalSuperclassesProportion;
	private Double criticalSuperclassesInheritance;
	private Double reflectionPackageBoolean;
	//private HashMap<String,Double> reflectionPackageBoolean= new HashMap<String,Double>();
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
		criticalClassesExtensibility(pv,mpv);
		criticalClassesTotal(pv,mpv);
		criticalClassesCoupling(pv,mpv);
		compositePartCriticalClasses(pv,mpv);
		unusedCriticalAccessorClass(pv,mpv);
		criticalDesignProportion(pv,mpv);
		criticalSerializedClassesProportion(pv,mpv);
		criticalSuperclassesProportion(pv,mpv);
		criticalSuperclassesInheritance(pv,mpv);
		reflectionPackageBoolean(pv,mpv);
		ClassifiedMethodsInheritance(pv,mpv);
		
		
		
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

	//Project Level Ratio
	public void ClassifiedMethodsInheritance(PulledValues pv, MurgePulledValues mpv)  {
		Set<String> baseClassesInProject = mpv.getBaseClassNames();
		HashMap<String, Double> numberOfProtectedMethodsInClass = mpv.getNumberOfProtectedMethodsInClass();
		HashMap<String, Double> numberOfPrivateMethodsInClass = mpv.getNumberOfPrivateMethodsInClass();
		
		for (String className: numberOfProtectedMethodsInClass.keySet()) {			
			classifiedMethodsInheritance.put(className, (baseClassesInProject.contains(className) ? (numberOfProtectedMethodsInClass.get(className)/ (numberOfPrivateMethodsInClass.get(className) + numberOfProtectedMethodsInClass.get(className)))   : 0.0 ));	
		}
		
		
	}		
	
	//total # CC , this is a Double.
	private void criticalClassesTotal(PulledValues pv, MurgePulledValues mpv) {
		criticalClassesTotal = mpv.getNumberOfCriticalClassesInProgram();
	}
	
	//CCE project ratio thus Double.
	private void criticalClassesExtensibility(PulledValues pv, MurgePulledValues mpv) {
		System.out.println("it is divividing it by"+ mpv.getNumberOfCriticalClassesInProgram());
		criticalClassesExtensibility=(double) (mpv.getNonFinalizedCriticalClasses().size() / mpv.getNumberOfCriticalClassesInProgram());
	}
	
	//CCC // this is a Double.
	private void criticalClassesCoupling(PulledValues pv, MurgePulledValues mpv) {
		Set<String> criticalClassNames = mpv.getCriticalClasses();
		
		HashMap<String, Double> numberOfcriticalClassAttributes = getClassifiedClassDataAccessibility();
		HashMap<String, Double> numberOfcriticalInstanceAttributes = getClassifiedInstanceDataAccessibility();
		double sumOfPrivateAndProtectedMethodsInClass=0,sumOfCriticalCriticalCouplingAttributes=0;
		
		
		HashMap<String, HashSet<String>> numberOfCoupledClasses = mpv.getClassesCoupledToBaseClass();
		
		for (String className: numberOfcriticalInstanceAttributes.keySet()) {
			sumOfPrivateAndProtectedMethodsInClass += (numberOfcriticalClassAttributes.get(className) + numberOfcriticalInstanceAttributes.get(className));
			for (String criticalClassName: criticalClassNames) {
				if (numberOfCoupledClasses.containsKey(criticalClassName)) sumOfCriticalCriticalCouplingAttributes+=1;
			}
		}
		criticalClassesCoupling=(sumOfCriticalCriticalCouplingAttributes/sumOfPrivateAndProtectedMethodsInClass);
	}
	
	//CPCC // this is a Double.
	private void compositePartCriticalClasses(PulledValues pv, MurgePulledValues mpv) {
		Set<String> criticalClassNames = mpv.getCriticalBaseClasses();
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		HashMap<String, HashSet<String>> compositePartClasses = mpv.getClassesCoupledToBaseClass();
		double compositePartClassesCounter=0;
		
		for (String className:classNames) {
			
			if (compositePartClasses.containsKey(className)) {
				if (compositePartClasses.get(className).size()>1) compositePartClassesCounter+=1;
			}
		}
		compositePartCriticalClasses= (double) (compositePartClassesCounter/criticalClassNames.size());
	}
	
	//UCAC //!
	private void unusedCriticalAccessorClass(PulledValues pv, MurgePulledValues mpv) {
		unusedCriticalAccessorClass=(double) (mpv.getUnusedClassifiedMethods().size() / mpv.getNumberOfCriticalClassesInProgram());
	}
	
	//CDP
	private void criticalDesignProportion(PulledValues pv, MurgePulledValues mpv) {
		criticalDesignProportion= (double) (mpv.getNumberOfCriticalClassesInProgram()*INT_TO_DOUBLE) / (double) (mpv.getNumberOfClassesInProject().size()*INT_TO_DOUBLE);
	}
	
	//CSCP
	private void criticalSerializedClassesProportion(PulledValues pv, MurgePulledValues mpv) {
		criticalSerializedClassesProportion = (double) (mpv.getNumberOfSerializableClassesInProject().size()*INT_TO_DOUBLE) / (double) (mpv.getNumberOfCriticalClassesInProgram()*INT_TO_DOUBLE);
	}
	
	//CSP
	private void criticalSuperclassesProportion(PulledValues pv, MurgePulledValues mpv) {
		criticalSuperclassesProportion = (double) (mpv.getCriticalBaseClasses().size()*INT_TO_DOUBLE) / (double) (mpv.getNumberOfCriticalClassesInProgramHeirchy().size()*INT_TO_DOUBLE);
	}
	
	//CSI 
	private void criticalSuperclassesInheritance(PulledValues pv, MurgePulledValues mpv) {
		criticalSuperclassesInheritance =  (double) (mpv.getSumOfNumberOfClassesInheritingFromCriticalBaseClasses()/mpv.getNumberOfCriticalClassesInProgramHeirchy().size()*1.0);		
	}
	
	//RPB - this is actually a boolean not an int sum. fix it in murge pulled values.
	private void reflectionPackageBoolean(PulledValues pv, MurgePulledValues mpv) {
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		ArrayList<String> criticalSerializedClasses = mpv.getNumberOfSerializableClassesInProject();
		/*for (String className: classNames) {
			reflectionPackageBoolean.put(className, (criticalSerializedClasses.contains(className)) ? (Double) (1.0) : (Double) (0.0)  );
		}*/
		
		reflectionPackageBoolean = (criticalSerializedClasses.size()>0*INT_TO_DOUBLE) ? (Double) (1.0) : (Double) (0.0);
		
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

	public Double getCriticalClassesExtensibility() {
		return criticalClassesExtensibility;
	}

	public Double getCriticalClassesCoupling() {
		return criticalClassesCoupling;
	}

	public Double getCompositePartCriticalClasses() {
		return compositePartCriticalClasses;
	}

	public Double getUnusedCriticalAccessorClass() {
		return unusedCriticalAccessorClass;
	}

	public Double getCriticalDesignProportion() {
		return criticalDesignProportion;
	}

	public Double getCriticalSerializedClassesProportion() {
		return criticalSerializedClassesProportion;
	}

	public Double getCriticalSuperclassesProportion() {
		return criticalSuperclassesProportion;
	}

	public Double getCriticalSuperclassesInheritance() {
		return criticalSuperclassesInheritance;
	}
	
	public Double getReflectionPackageBoolean() {
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
