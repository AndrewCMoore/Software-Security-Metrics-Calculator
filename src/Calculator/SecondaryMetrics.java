package Calculator;

public class SecondaryMetrics {
	
	private float readabilityOfCriticalClasses=0;
	private float writabilityOfCriticalClasses=0;

	public SecondaryMetrics(TertiaryMetrics tm) {
		// TODO Auto-generated constructor stub
	}
	
	//###########################################################################################################################################################	
	//Abstraction Metrics
	//###########################################################################################################################################################
	

	
	public void readabilityOfClassifiedAttributes (TertiaryMetrics tm) {}

	public void readabilityOfClassifiedMethods (TertiaryMetrics tm) {}

	public void readabilityOfCriticalClasses (TertiaryMetrics tm) {		
		readabilityOfCriticalClasses = 
		tm.getCriticalClassesExtensibility()+tm.getCriticalSuperclassesProportion()
			+tm.getCriticalDesignProportion()+tm.getCompositePartCriticalClasses()
			+tm.getReflectionPackageBoolean();
	}
	//###########################################################################################################################################################	
	//Complexity Metrics
	//###########################################################################################################################################################

	
	public void securityAbsoluteMeasurments (TertiaryMetrics tm) {}
	
	//###########################################################################################################################################################	
	//Encapsulation Metrics
	//###########################################################################################################################################################

	public void writabilityOfClassifiedAttributes (TertiaryMetrics tm) {}

	public void writabilityOfClassifiedMethods (TertiaryMetrics tm) {}

	public void writabilityofClassifiedClasses (TertiaryMetrics tm) {}
	
	public void readabilityOfClassifiedClasses (TertiaryMetrics tm) {}
	
	public void writabilityOfCriticalClasses(TertiaryMetrics tm) {
		writabilityOfCriticalClasses = 
		tm.getCriticalClassesCoupling()+tm.getCriticalSuperclassesInheritance()
			+tm.getCriticalSerializedClassesProportion()+tm.getUnusedCriticalAccessorClass();
	}
		
}
