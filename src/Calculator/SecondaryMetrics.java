package Calculator;

import java.util.HashMap;

public class SecondaryMetrics {
	
	private HashMap<String, Double> readabilityOfClassifiedAttributes = new HashMap<String, Double>();
	private HashMap<String, Double> readabilityOfClassifiedMethods = new HashMap<String, Double>();
	private HashMap<String, Double> writabilityOfClassifiedAttributes = new HashMap<String, Double>();
	private HashMap<String, Double> writabilityOfClassifiedMethods = new HashMap<String, Double>();
	private HashMap<String, Double> writabilityOfClassifiedClasses = new HashMap<String, Double>();
	private Double readabilityOfCriticalClasses=(double) 0;
	private Double writabilityOfCriticalClasses=(double) 0;
	private Double securityAbsoluteMeasurements = (double) 0;
	private static final int CLASSIFIEDATTRIBUTESWEIGHT = 4;
	private static final int  CLASSIFIEDMETHODSWEIGHT = 3;
	private static final int  CRITCALCLASSESWEIGHT = 2;
	private static final int ABSOLUTEMEASURMENTSWEIGHT = 1;
	
	public SecondaryMetrics(TertiaryMetrics tm) {
		this.readabilityOfClassifiedAttributes(tm);
		this.readabilityOfClassifiedMethods(tm);
		this.readabilityOfCriticalClasses(tm);
		this.securityAbsoluteMeasurments(tm);
		this.writabilityOfClassifiedAttributes(tm);
		this.writabilityofClassifiedClasses(tm);
		this.writabilityOfClassifiedMethods(tm);
		this.writabilityOfCriticalClasses(tm);
		System.out.println("OverHere");
		printSM();
	}
	
	//###########################################################################################################################################################	
	//Abstraction Metrics
	//###########################################################################################################################################################
	

	
	public void readabilityOfClassifiedAttributes (TertiaryMetrics tm) {
		for(String key : tm.getClassifiedAttributesInheritance().keySet()) {
			readabilityOfClassifiedAttributes.put(key, (double) ((tm.getClassifiedInstanceDataAccessibility().get(key) + tm.getClassifiedClassDataAccessibility().get(key) + tm.getClassifiedAttributesInheritance().get(key)))*CLASSIFIEDATTRIBUTESWEIGHT);
		}
	}

	public void readabilityOfClassifiedMethods (TertiaryMetrics tm) {
		for(String key : tm.getClassifiedOperationAccessibility().keySet()) {
			readabilityOfClassifiedMethods.put(key, (double) (tm.getClassifiedOperationAccessibility().get(key) + tm.getClassifiedMethodsExtensibility().get(key) + tm.getClassifiedMethodsInheritance().get(key))*CLASSIFIEDMETHODSWEIGHT);
		}
	}

	public void readabilityOfCriticalClasses (TertiaryMetrics tm) {		
		readabilityOfCriticalClasses = 
		(double) ((tm.getCriticalClassesExtensibility()+tm.getCriticalSuperclassesProportion()
			+tm.getCriticalDesignProportion()+tm.getCompositePartCriticalClasses()
			+tm.getReflectionPackageBoolean())*CRITCALCLASSESWEIGHT);
	}
	//###########################################################################################################################################################	
	//Complexity Metrics
	//###########################################################################################################################################################
	
	public void securityAbsoluteMeasurments (TertiaryMetrics tm) {
		securityAbsoluteMeasurements  = (double) ((tm.getClassifiedAttributesTotal() + tm.getClassifiedMethodsTotal() + tm.getCriticalClassesTotal())*ABSOLUTEMEASURMENTSWEIGHT);
	}
	
	//###########################################################################################################################################################	
	//Encapsulation Metrics
	//###########################################################################################################################################################

	public void writabilityOfClassifiedAttributes (TertiaryMetrics tm) {
		for(String key : tm.getClassifiedMutatorAttributeInteractions().keySet()) {
			writabilityOfClassifiedAttributes.put(key, (double) (tm.getClassifiedMutatorAttributeInteractions().get(key) + tm.getClassifiedAccessorAttributeInteractions().get(key) + tm.getUnaccessedAssignedClassifiedAttribute().get(key))*CLASSIFIEDATTRIBUTESWEIGHT);
		}
	}

	public void writabilityOfClassifiedMethods (TertiaryMetrics tm) {
		for(String key : tm.getClassifiedAttributesInteractionWeight().keySet()) {
			writabilityOfClassifiedMethods.put(key, (double) tm.getClassifiedAttributesInteractionWeight().get(key) + tm.getClassifiedMethodsWeight().get(key) + tm.getClassifiedWritingMethodsProportion().get(key) + tm.getUncalledClassifiedAccessorMethod().get(key));
		}
	}

	public void writabilityofClassifiedClasses (TertiaryMetrics tm) {
		for(String key : tm.getClassifiedAttributesInteractionWeight().keySet()) {
			writabilityOfClassifiedClasses.put(key, (double) (tm.getClassifiedAttributesInteractionWeight().get(key) + tm.getClassifiedMethodsWeight().get(key) + tm.getClassifiedWritingMethodsProportion().get(key) + tm.getUncalledClassifiedAccessorMethod().get(key))*CRITCALCLASSESWEIGHT);
		}
	}
	
	public void writabilityOfCriticalClasses(TertiaryMetrics tm) {
		writabilityOfCriticalClasses = 
		(double) (tm.getCriticalClassesCoupling()+tm.getCriticalSuperclassesInheritance()
			+tm.getCriticalSerializedClassesProportion()+tm.getUnusedCriticalAccessorClass());
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

	public Double getReadabilityOfCriticalClasses() {
		return readabilityOfCriticalClasses;
	}

	public Double getWritabilityOfCriticalClasses() {
		return writabilityOfCriticalClasses;
	}

	public Double getSecurityAbsoluteMeasurements() {
		return securityAbsoluteMeasurements;
	}
	
	public void printSM() {
		System.out.println("Secondary Metrics");
		System.out.println("getReadabilityOfClassifiedAttributes: " + this.getReadabilityOfClassifiedAttributes());
		System.out.println("getReadabilityOfClassifiedMethods: " + this.getReadabilityOfClassifiedMethods());
		System.out.println("getWritabilityOfClassifiedAttributes: " + this.getWritabilityOfClassifiedAttributes());
		System.out.println("getWritabilityOfClassifiedMethods: " + this.getWritabilityOfClassifiedMethods());
		System.out.println("getWritabilityOfClassifiedClasses: " + this.getWritabilityOfClassifiedClasses());
		System.out.println("getReadabilityOfCriticalClasses: " + this.getReadabilityOfCriticalClasses());
		System.out.println("writabilityOfCriticalClasses: " + this.getWritabilityOfCriticalClasses());
		System.out.println("securityAbsoluteMeasurements: " + this.getSecurityAbsoluteMeasurements());
		
		
		/*double ReadabilityOfClassifiedAttributes = 0;
		double ReadabilityOfClassifiedMethods = 0;
		double WritabilityOfClassifiedAttributes = 0;
		double WritabilityOfClassifiedMethods = 0;
		double WritabilityOfClassifiedClasses = 0;
		for(String key : this.readabilityOfClassifiedAttributes.keySet()) {
			if (readabilityOfClassifiedAttributes.get(key).isNaN() || readabilityOfClassifiedAttributes.get(key).isInfinite()|| (readabilityOfClassifiedAttributes.get(key)==null) ) {
				ReadabilityOfClassifiedAttributes += 0;
			}else {try {
				ReadabilityOfClassifiedAttributes += readabilityOfClassifiedAttributes.get(key);
			}catch (Exception e) {}}
			if (readabilityOfClassifiedMethods.get(key).isNaN() || readabilityOfClassifiedMethods.get(key).isInfinite()|| (readabilityOfClassifiedMethods.get(key)==null) ) {
				ReadabilityOfClassifiedMethods += 0;
			}else {try {
				ReadabilityOfClassifiedMethods += readabilityOfClassifiedMethods.get(key);
			}catch (Exception e) {}}
			if (writabilityOfClassifiedAttributes.get(key).isNaN() || writabilityOfClassifiedAttributes.get(key).isInfinite()|| (writabilityOfClassifiedAttributes.get(key)==null) ) {
				WritabilityOfClassifiedAttributes += 0;
			}else {try {
				WritabilityOfClassifiedAttributes += writabilityOfClassifiedAttributes.get(key);
			}catch (Exception e) {}}
			if (writabilityOfClassifiedMethods.get(key).isNaN() || writabilityOfClassifiedMethods.get(key).isInfinite() || (writabilityOfClassifiedMethods.get(key)==null) ) {
				WritabilityOfClassifiedMethods += 0;
			}else {try {
				WritabilityOfClassifiedMethods += writabilityOfClassifiedMethods.get(key);
			}catch (Exception e) {}}
			if (writabilityOfClassifiedClasses.get(key).isNaN() || writabilityOfClassifiedClasses.get(key).isInfinite() || (writabilityOfClassifiedClasses.get(key)==null) ) {
				WritabilityOfClassifiedClasses += 0;
			}else { try {
				WritabilityOfClassifiedClasses += writabilityOfClassifiedClasses.get(key);
			}catch (Exception e) {}}
		}
		System.out.println("ReadabilityOfClassifiedAttributes: " + ReadabilityOfClassifiedAttributes);
		System.out.println("ReadabilityOfClassifiedMethods: " + ReadabilityOfClassifiedMethods);
		System.out.println("WritabilityOfClassifiedAttributes: " + WritabilityOfClassifiedAttributes);
		System.out.println("WritabilityOfClassifiedMethods: " + WritabilityOfClassifiedMethods);
		System.out.println("WritabilityOfClassifiedClasses: " + WritabilityOfClassifiedClasses);
		System.out.println("readabilityOfCriticalClasses: " + readabilityOfCriticalClasses);
		System.out.println("writabilityOfCriticalClasses: " + writabilityOfCriticalClasses);
		System.out.println("securityAbsoluteMeasurements: " + securityAbsoluteMeasurements);*/
	}
	
	
		
}
