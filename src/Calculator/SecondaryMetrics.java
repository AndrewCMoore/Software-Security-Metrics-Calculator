package Calculator;

import java.util.HashMap;

public class SecondaryMetrics {
	
	private HashMap<String, Double> readabilityOfClassifiedAttributes = new HashMap<String, Double>();
	private HashMap<String, Double> readabilityOfClassifiedMethods = new HashMap<String, Double>();
	private HashMap<String, Double> writabilityOfClassifiedAttributes = new HashMap<String, Double>();
	private HashMap<String, Double> writabilityOfClassifiedMethods = new HashMap<String, Double>();
	private HashMap<String, Double> writabilityOfClassifiedClasses = new HashMap<String, Double>();
	private float readabilityOfCriticalClasses=0;
	private float writabilityOfCriticalClasses=0;
	private int securityAbsoluteMeasurements = 0;
	
	public SecondaryMetrics(TertiaryMetrics tm) {
		this.readabilityOfClassifiedAttributes(tm);
		this.readabilityOfClassifiedMethods(tm);
		this.readabilityOfCriticalClasses(tm);
		this.securityAbsoluteMeasurments(tm);
		this.writabilityOfClassifiedAttributes(tm);
		this.writabilityofClassifiedClasses(tm);
		this.writabilityOfClassifiedMethods(tm);
		this.writabilityOfCriticalClasses(tm);
	}
	
	//###########################################################################################################################################################	
	//Abstraction Metrics
	//###########################################################################################################################################################
	

	
	public void readabilityOfClassifiedAttributes (TertiaryMetrics tm) {
		for(String key : tm.getClassifiedAttributesInheritance().keySet()) {
			readabilityOfClassifiedAttributes.put(key, (double) (tm.getClassifiedInstanceDataAccessibility().get(key) + tm.getClassifiedClassDataAccessibility().get(key) + tm.getClassifiedAttributesInheritance().get(key)));
		}
	}

	public void readabilityOfClassifiedMethods (TertiaryMetrics tm) {
		for(String key : tm.getClassifiedOperationAccessibility().keySet()) {
			readabilityOfClassifiedMethods.put(key, (double) tm.getClassifiedOperationAccessibility().get(key) + tm.getClassifiedMethodsExtensibility().get(key) + tm.getClassifiedMethodsInheritance().get(key));
		}
	}

	public void readabilityOfCriticalClasses (TertiaryMetrics tm) {		
		readabilityOfCriticalClasses = 
		tm.getCriticalClassesExtensibility()+tm.getCriticalSuperclassesProportion()
			+tm.getCriticalDesignProportion()+tm.getCompositePartCriticalClasses()
			+tm.getReflectionPackageBoolean();
	}
	//###########################################################################################################################################################	
	//Complexity Metrics
	//###########################################################################################################################################################
	
	public void securityAbsoluteMeasurments (TertiaryMetrics tm) {
		securityAbsoluteMeasurements  = tm.getClassifiedAttributesTotal() + tm.getClassifiedMethodsTotal() + tm.getCriticalClassesTotal();
	}
	
	//###########################################################################################################################################################	
	//Encapsulation Metrics
	//###########################################################################################################################################################

	public void writabilityOfClassifiedAttributes (TertiaryMetrics tm) {
		for(String key : tm.getClassifiedMutatorAttributeInteractions().keySet()) {
			writabilityOfClassifiedAttributes.put(key, (double) tm.getClassifiedMutatorAttributeInteractions().get(key) + tm.getClassifiedAccessorAttributeInteractions().get(key) + tm.getUnaccessedAssignedClassifiedAttribute().get(key));
		}
	}

	public void writabilityOfClassifiedMethods (TertiaryMetrics tm) {
		for(String key : tm.getClassifiedAttributesInteractionWeight().keySet()) {
			writabilityOfClassifiedMethods.put(key, (double) tm.getClassifiedAttributesInteractionWeight().get(key) + tm.getClassifiedMethodsWeight().get(key) + tm.getClassifiedWritingMethodsProportion().get(key) + tm.getUncalledClassifiedAccessorMethod().get(key));
		}
	}

	public void writabilityofClassifiedClasses (TertiaryMetrics tm) {
		for(String key : tm.getClassifiedAttributesInteractionWeight().keySet()) {
			writabilityOfClassifiedClasses.put(key, (double) tm.getClassifiedAttributesInteractionWeight().get(key) + tm.getClassifiedMethodsWeight().get(key) + tm.getClassifiedWritingMethodsProportion().get(key) + tm.getUncalledClassifiedAccessorMethod().get(key));
		}
	}
	
	public void writabilityOfCriticalClasses(TertiaryMetrics tm) {
		writabilityOfCriticalClasses = 
		tm.getCriticalClassesCoupling()+tm.getCriticalSuperclassesInheritance()
			+tm.getCriticalSerializedClassesProportion()+tm.getUnusedCriticalAccessorClass();
	}

	public HashMap<String, Double> getReadabilityOfClassifiedAttributes() {
		return readabilityOfClassifiedAttributes;
	}

	public HashMap<String, Double> getReadabilityOfClassifiedMethods() {
		return readabilityOfClassifiedMethods;
	}

	public HashMap<String, Double> getWritabilityOfClassifiedAttributes() {
		return writabilityOfClassifiedAttributes;
	}

	public HashMap<String, Double> getWritabilityOfClassifiedMethods() {
		return writabilityOfClassifiedMethods;
	}

	public HashMap<String, Double> getWritabilityOfClassifiedClasses() {
		return writabilityOfClassifiedClasses;
	}

	public float getReadabilityOfCriticalClasses() {
		return readabilityOfCriticalClasses;
	}

	public float getWritabilityOfCriticalClasses() {
		return writabilityOfCriticalClasses;
	}

	public int getSecurityAbsoluteMeasurements() {
		return securityAbsoluteMeasurements;
	}
		
}
