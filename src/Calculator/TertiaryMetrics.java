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
	private Double classifiedAttributesTotal = 0.0;
	private Double classifiedMethodsTotal = 0.0;
	private Double criticalClassesTotal = 0.0;


	
	
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
		
		classifiedAttributesInheritance(pv,mpv);
		unaccessedAssignedClassifiedAttribute(pv,mpv);
		
}
	
	private void uncalledClassifiedAccessorMethod(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> classifiedNeverUsed = pv.getMapAccessClassifiedNeverCalled();
		HashMap<String, Double> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			if(classifiedMethods.get(key) != 0) {
				uncalledClassifiedAccessorMethod.put(key, (double) classifiedNeverUsed.get(key)/(double)classifiedMethods.get(key));
			} else {
				uncalledClassifiedAccessorMethod.put(key, 0.0);
			}
		}
	}

	private void classifiedWritingMethodsProportion(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> methodsWriteClassifiedAttributes = pv.getMapWritesClassifiedAttributes();
		HashMap<String, Double> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			if(classifiedMethods.get(key) != 0) {
				classifiedWritingMethodsProportion.put(key, (double) methodsWriteClassifiedAttributes.get(key)/(double)classifiedMethods.get(key));
			} else {
				classifiedWritingMethodsProportion.put(key, 0.0);
			}
		}
	}

	private void classifiedMethodsWeight(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> classifiedMethods = pv.getMapClassifiedMethods();
		HashMap<String, Double> totalMethods = pv.getMapTotalMethods();
		
		for(String key : totalMethods.keySet()) {
			if((double)totalMethods.get(key) != 0) {
				classifiedMethodsWeight.put(key, (double) classifiedMethods.get(key)/(double)totalMethods.get(key));
			} else {
				classifiedMethodsWeight.put(key, 0.0);
			}
		}
	}

	private void classifiedAttributesInteractionWeight(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> classifiedAttributeInteractions = pv.getMapClassifiedAttributeInteractions();
		HashMap<String, Double> attributeInteractions = pv.getMapAccessorInteractions();
		
		for(String key : attributeInteractions.keySet()) {
			if(attributeInteractions.get(key) != 0) {
				classifiedAttributesInteractionWeight.put(key, (double) classifiedAttributeInteractions.get(key)/(double)attributeInteractions.get(key));
			} else {
				classifiedAttributesInteractionWeight.put(key, 0.0);
			}
		}
	}

	private void classifiedMutatorAttributeInteractions(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> mutatorInteractions = pv.getMapMutatorInteractions();
		HashMap<String, Double> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : mutatorInteractions.keySet()) {
			if((double)(mutatorInteractions.get(key) * classifiedAttributes.get(key)) != 0.0) {
				classifiedMutatorAttributeInteractions.put(key, (double) mutatorInteractions.get(key) / (double)(mutatorInteractions.get(key) * (double)classifiedAttributes.get(key)));
			} else {
				classifiedMutatorAttributeInteractions.put(key, 0.0);
			}
		}
	}

	private void classifiedAccessorAttributeInteractions(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> accessorInteractions = pv.getMapAccessorInteractions();
		HashMap<String, Double> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : accessorInteractions.keySet()) {
			if((double)(accessorInteractions.get(key) * classifiedAttributes.get(key)) != 0.0) {
				classifiedAccessorAttributeInteractions.put(key, (double) accessorInteractions.get(key) / (double)(accessorInteractions.get(key) * (double)classifiedAttributes.get(key)));
			} else {
				classifiedAccessorAttributeInteractions.put(key, 0.0);
			}
		}
	}

	private void classifiedMethodsTotal(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			classifiedMethodsTotal +=(double) pv.getMapClassifiedMethods().get(key);
		}
	}

	private void classifiedAttributesTotal(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> classifiedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : classifiedAttributes.keySet()) {
			classifiedAttributesTotal += (double) pv.getMapTotalAttributes().get(key);
		}
	}

	private void classifiedMethodsExtensibility(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> nonFinalClassifiedMethods = pv.getMapNonFinalPrivateProtectedMethods();
		HashMap<String, Double> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			if(classifiedMethods.get(key) != 0) {
				classifiedMethodsExtensibility.put(key, (double) (nonFinalClassifiedMethods.get(key)/(double)classifiedMethods.get(key)));
			}
			else {
				classifiedMethodsExtensibility.put(key, (double) 0);
			}
		}
	}

	private void classifiedOperationAccessibility(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> classifiedMethodsNotPrivate = pv.getMapClassifiedMethodsNotPrivate();
		HashMap<String, Double> classifiedMethods = pv.getMapClassifiedMethods();
		
		for(String key : classifiedMethods.keySet()) {
			if(classifiedMethods.get(key) != 0) {
				classifiedOperationAccessibility.put(key, (double) (classifiedMethodsNotPrivate.get(key)/(double)classifiedMethods.get(key)));
			}
			else {
				classifiedOperationAccessibility.put(key, (double) 0);
			}
		}
	}

	private void classifiedClassDataAccessibility(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> classifiedClassNotPrivate = pv.getMapClassifiedClassAttributeNotPrivate();
		HashMap<String, Double> privateProtectedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : privateProtectedAttributes.keySet()) {
			if(privateProtectedAttributes.get(key) != 0) {
				classifiedClassDataAccessibility.put(key, (double) (classifiedClassNotPrivate.get(key)/(double)privateProtectedAttributes.get(key)));
			}
			else {
				classifiedClassDataAccessibility.put(key, (double) 0);
			}
		}	
	}

	private void classifiedInstanceDataAccessibility(PulledValues pv, MurgePulledValues mpv) {
		HashMap<String, Double> classifiedInstanceNotPrivate = pv.getMapClassifiedInstanceAttributeNotPrivate();
		HashMap<String, Double> privateProtectedAttributes = pv.getMapPrivateProtectedTotal();
	
		for(String key : privateProtectedAttributes.keySet()) {
			if(privateProtectedAttributes.get(key) != (double)0.0) {
				classifiedInstanceDataAccessibility.put(key, ((double) (classifiedInstanceNotPrivate.get(key)/(double)privateProtectedAttributes.get(key))));		
			}else {
				classifiedInstanceDataAccessibility.put(key, (double) 0);
			}
			System.out.println(classifiedInstanceDataAccessibility);
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
			classifiedMethodsInheritance.put(className, (baseClassesInProject.contains(className) ? ((double)numberOfProtectedMethodsInClass.get(className)/ ((double)numberOfPrivateMethodsInClass.get(className) + (double)numberOfProtectedMethodsInClass.get(className)))   : (double)0.0 ));	
		}
		
		
	}		
	
	//total # CC , this is a Double.
	private void criticalClassesTotal(PulledValues pv, MurgePulledValues mpv) {
		criticalClassesTotal = (double) mpv.getNumberOfCriticalClassesInProgram();
	}
	
	//CCE project ratio thus Double.
	private void criticalClassesExtensibility(PulledValues pv, MurgePulledValues mpv) {
		System.out.println("it is divividing it by"+ mpv.getNumberOfCriticalClassesInProgram());
		if (mpv.getNumberOfCriticalClassesInProgram()!=0) {
			criticalClassesExtensibility=(double) (mpv.getNonFinalizedCriticalClasses().size() /(double) mpv.getNumberOfCriticalClassesInProgram());
		} else {
			criticalClassesExtensibility = (double) 0;
		}
	}
	
	//CCC // this is a Double.
	private void criticalClassesCoupling(PulledValues pv, MurgePulledValues mpv) {
		Set<String> criticalClassNames = mpv.getCriticalClasses();
		
		HashMap<String, Double> numberOfcriticalClassAttributes = getClassifiedClassDataAccessibility();
		HashMap<String, Double> numberOfcriticalInstanceAttributes = getClassifiedInstanceDataAccessibility();
		double sumOfPrivateAndProtectedMethodsInClass=0,sumOfCriticalCriticalCouplingAttributes=0;
		
		
		HashMap<String, Double> numberOfCoupledClasses = mpv.getClassesCoupledToBaseClass();
		
		for (String className: numberOfcriticalInstanceAttributes.keySet()) {
			sumOfPrivateAndProtectedMethodsInClass += (numberOfcriticalClassAttributes.get(className) + numberOfcriticalInstanceAttributes.get(className));
			for (String criticalClassName: criticalClassNames) {
				if (numberOfCoupledClasses.containsKey(criticalClassName)) sumOfCriticalCriticalCouplingAttributes+=1;
			}
		}
		if (sumOfPrivateAndProtectedMethodsInClass!=0) {
			criticalClassesCoupling=(sumOfCriticalCriticalCouplingAttributes/sumOfPrivateAndProtectedMethodsInClass);

		} else {
			criticalClassesCoupling=(double) 0;
		}
	}
	
	//CPCC // this is a Double.
	private void compositePartCriticalClasses(PulledValues pv, MurgePulledValues mpv) {
		Set<String> criticalClassNames = mpv.getCriticalBaseClasses();
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		HashMap<String, Double> compositePartClasses = mpv.getClassesCoupledToBaseClass();
		double compositePartClassesCounter=0;
		
		for (String className:classNames) {
			
			if (compositePartClasses.containsKey(className)) {
				if (compositePartClasses.get(className)>1) compositePartClassesCounter+=1;
			}
		}
		compositePartCriticalClasses= (double) (compositePartClassesCounter/criticalClassNames.size());
		if (criticalClassNames.size()==0) compositePartCriticalClasses= (double) 0;
	}
	
	//UCAC 
	private void unusedCriticalAccessorClass(PulledValues pv, MurgePulledValues mpv) {
		unusedCriticalAccessorClass=(double) (mpv.getUnusedClassifiedMethods().size() / (double)mpv.getNumberOfCriticalClassesInProgram());
		if (mpv.getNumberOfCriticalClassesInProgram()==0) unusedCriticalAccessorClass=(double) 0;
	}
	
	//CDP
	private void criticalDesignProportion(PulledValues pv, MurgePulledValues mpv) {
		criticalDesignProportion= (double) (mpv.getNumberOfCriticalClassesInProgram()) / (double) (mpv.getNumberOfClassesInProject().size());
		if ((mpv.getNumberOfClassesInProject().size()==0)) criticalDesignProportion= (double) 0;
	}
	
	//CSCP
	private void criticalSerializedClassesProportion(PulledValues pv, MurgePulledValues mpv) {
		criticalSerializedClassesProportion = (double) (mpv.getNumberOfSerializableClassesInProject()) / (double) (mpv.getNumberOfCriticalClassesInProgram());
		if (mpv.getNumberOfCriticalClassesInProgram()==0) criticalSerializedClassesProportion = (double)0;
	}
	
	//CSP
	private void criticalSuperclassesProportion(PulledValues pv, MurgePulledValues mpv) {
		criticalSuperclassesProportion = (double) (mpv.getCriticalBaseClasses().size()) / (double) (mpv.getNumberOfCriticalClassesInProgramHeirchy().size());
		if (mpv.getNumberOfCriticalClassesInProgramHeirchy().size()==0) criticalSuperclassesProportion = (double) 0;
	}
	
	//CSI 
	private void criticalSuperclassesInheritance(PulledValues pv, MurgePulledValues mpv) {
		criticalSuperclassesInheritance =  (double) (mpv.getSumOfNumberOfClassesInheritingFromCriticalBaseClasses()/(double)mpv.getNumberOfCriticalClassesInProgramHeirchy().size());		
		if (mpv.getNumberOfCriticalClassesInProgramHeirchy().size()==0) criticalSuperclassesInheritance =  (double)0;
	}
	
	//RPB - this is actually a boolean not an int sum. fix it in murge pulled values.
	private void reflectionPackageBoolean(PulledValues pv, MurgePulledValues mpv) {
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		int criticalSerializedClasses = mpv.getNumberOfSerializableClassesInProject();
		/*for (String className: classNames) {
			reflectionPackageBoolean.put(className, (criticalSerializedClasses.contains(className)) ? (Double) (1.0) : (Double) (0.0)  );
		}*/
		
		reflectionPackageBoolean = (criticalSerializedClasses>0) ? (double) (1.0) : (double) (0.0);
		
	}
	
	private void classifiedAttributesInheritance(PulledValues pv, MurgePulledValues mpv) {
		for (String className: mpv.getNumberOfClassesInProject()) {
			classifiedAttributesInheritance.put(className, ((double) mpv.getValidAttributeNamesInClassThatCanBeInherited().get(className).size()/mpv.getNumberOfProtectedAttributesInClass().get(className)));
		}		 
	}
	
	private void unaccessedAssignedClassifiedAttribute(PulledValues pv, MurgePulledValues mpv) {
			for (String className: mpv.getNumberOfClassesInProject()) {
				unaccessedAssignedClassifiedAttribute.put(className, ((double) mpv.getUnusedClassifiedAttributes().get(className)/(pv.getMapPrivateProtectedClass().get(className)+pv.getMapPrivateProtectedInstance().get(className))));
			}	
			
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

	public Double getClassifiedAttributesTotal() {
		return classifiedAttributesTotal;
	}

	public Double getClassifiedMethodsTotal() {
		return classifiedMethodsTotal;
	}

	public Double getCriticalClassesTotal() {
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
		return classifiedAttributesInheritance ;
	}

	public HashMap<String, Double> getUnaccessedAssignedClassifiedAttribute() {
		return unaccessedAssignedClassifiedAttribute;
	}
	public HashMap<String, Double> getClassifiedMethodsInheritance () {
		return classifiedMethodsInheritance;
	}
	

	

}
