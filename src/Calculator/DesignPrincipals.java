package Calculator;

import java.util.HashMap;

public class DesignPrincipals {
	private HashMap<String, Double> abstraction = new HashMap<String, Double>();
	private HashMap<String, Double> cohesion = new HashMap<String, Double>();
	private HashMap<String, Double> complexity = new HashMap<String, Double>();
	private HashMap<String, Double> composition = new HashMap<String, Double>();
	private HashMap<String, Double> coupling = new HashMap<String, Double>();
	private HashMap<String, Double> designSize = new HashMap<String, Double>();
	private HashMap<String, Double> encapsulation = new HashMap<String, Double>();
	private HashMap<String, Double> inheritance = new HashMap<String, Double>();
	private HashMap<String, Double> messaging = new HashMap<String, Double>();
	private HashMap<String, Double> polymorphism = new HashMap<String, Double>();
	private HashMap<String, Double> hierarchies = new HashMap<String, Double>();

	public DesignPrincipals(PrimaryMetrics pm) {
		abstraction(pm);
		cohesion(pm);
		complexity(pm);
		composition(pm);
		coupling(pm);
		designSize(pm);
		encapsulation(pm);
		inheritance(pm);
		messaging(pm);
		polymorphism(pm);
		hierarchies(pm);
		
		System.out.println("HELP ME");
		System.out.println("abstraction: "+abstraction);
		System.out.println("cohesion" +cohesion);
		System.out.println("complexity"+complexity);
		System.out.println("composition"+composition);
		System.out.println("coupling"+coupling);
		System.out.println("designSize"+designSize);
		System.out.println("encapsulation"+encapsulation);
		System.out.println("inheritance"+inheritance);
		System.out.println("messaging"+messaging);
		System.out.println("polymorphism"+polymorphism);
		System.out.println("abstraction"+abstraction);
		System.out.println("hierarchies"+hierarchies);	
	}
	
	//none of these are correct, just placeholders
	private void hierarchies(PrimaryMetrics pm) {
		for (String key: pm.getReduceAttackSurface().keySet()) {
			hierarchies.put(key, (double) 1);
		}
	}

	private void polymorphism(PrimaryMetrics pm) {
		for (String key: pm.getNumberOfPolymorphicMethods().keySet()) {
			polymorphism.put(key, (double) pm.getNumberOfPolymorphicMethods().get(key));
		}
		
	}

	private void messaging(PrimaryMetrics pm) {
		for (String key: pm.getClassInterfaceSize().keySet()) {
			messaging.put(key, (double) pm.getClassInterfaceSize().get(key));
		}
		
	}

	private void inheritance(PrimaryMetrics pm) {
		for (String key: pm.getMeasureOfFunctionalAbtraction().keySet()) {
			inheritance.put(key, (double) pm.getMeasureOfFunctionalAbtraction().get(key));
		}
		
	}

	private void encapsulation(PrimaryMetrics pm) {
		for (String key: pm.getClassesInProject()) {
			encapsulation.put(key, (double) pm.getCriticalElementRatio().get(key) +
				//	pm.getDataAccessMetric().get(key) +
				//	pm.getGrantLeastPrivelage().get(key) +
				//	pm.getIsolation()/(pm.getClassesInProject().size()) + 
				//	pm.getLeastCommonMechanism()/(pm.getClassesInProject().size()) +
					pm.getNumberOfHierarchies()
					);
		}
		
	}
	//!
	private void designSize(PrimaryMetrics pm) {
		for (String key: pm.getClassesInProject()) {
			designSize.put(key, pm.getDesignSizeInClasses()/pm.getClassesInProject().size()*pm.getClassesInProject().size());
		}
		
	}

	private void coupling(PrimaryMetrics pm) {
		for (String key: pm.getClassesInProject()) {
			coupling.put(key, (double) (pm.getCountOfBaseClasses()) / ((double)pm.getCouplingBetweenObjects().size()) +
					(pm.getCouplingBetweenObjects().get(key) )+
					//(pm.getCouplingCorruptionPropagation().get(key) )+
					(pm.getDepthOfInheritanceTree().get(key) )+
					(pm.getDirectClassCoupling().get(key) )+
					(pm.getFanIn().get(key) )+
					(pm.getFanOut().get(key) )+
					(pm.getHenryKafura().get(key) )+
					(pm.getNumberOfChildren().get(key) )+
					(pm.getResponseSetForAClass().get(key)));
		}
		
	}

	private void composition(PrimaryMetrics pm) {
		for (String key: pm.getMeasureOfAggregation().keySet()) {
			composition.put(key, (double) pm.getMeasureOfAggregation().get(key));
		}
		
	}
	
	//not sure how to deal with nesting complexity being a hashmap inside a hashmap
	private void complexity(PrimaryMetrics pm) {
		for (String key: pm.getClassesInProject()) {
			
			complexity.put(key, (double) pm.getCommentRatio().get(key) + 
					pm.getCountOfBaseClasses() +
					pm.getCyclomaticComplexity().get(key) + 
					pm.getDepthOfInheritanceTree().get(key) + 
					pm.getCountPath().get(key)+
					pm.getEconomyOfMechanism() +
					pm.getMcCabesCyclomaticComplexity().get(key) +
					pm.getModifiedCyclomaticComplexity().get(key) +
					
					//pm.getNestingComplexity().get(key) + !
					
					pm.getNumberOfChildren().get(key) +
					pm.getStrictCyclomaticComplexity().get(key) 
					//(double)pm.getSecureWeakestLink() / (double)pm.getClassesInProject().size() + !
					//(double)pm.getSourceLinesOfCode()  / (double)pm.getClassesInProject().size()//+ !
				//	pm.getWeightedMethodsPerClass().get(key)
					);
			
		}
	}

	private void cohesion(PrimaryMetrics pm) {
		for (String key: pm.getReduceAttackSurface().keySet()) {
			cohesion.put(key, (double) pm.getLackOfCohesionOfMethods().get(key) + 
					pm.getCohesionAmongMethodsInClass().get(key));
		}
	}

	private void abstraction(PrimaryMetrics pm) {
		for (String key: pm.getFailSafeDefaults().keySet()) {
			abstraction.put(key, (double) pm.getFailSafeDefaults().get(key) + 
					pm.getReduceAttackSurface().get(key) + 
					pm.getAverageNumberOfAncestors().get(key));
		}
	}

	public HashMap<String, Double> getAbstraction() {
		//return abstraction;
		return hierarchies;
	}

	public HashMap<String, Double> getCohesion() {
		//return cohesion;
		return hierarchies;
	}
	
	public HashMap<String, Double> getComplexity() {
		//return complexity;
		return hierarchies;
	}

	public HashMap<String, Double> getComposition() {
		//return composition;
		return hierarchies;
	}

	public HashMap<String, Double> getCoupling() {
		//return coupling;
		return hierarchies;
	}

	public HashMap<String, Double> getDesignSize() {
		//return designSize;
		return hierarchies;
	}

	public HashMap<String, Double> getEncapsulation() {
		//return encapsulation;
		return hierarchies;
	}

	public HashMap<String, Double> getInheritance() {
		//return inheritance;
		return hierarchies;
	}

	public HashMap<String, Double> getMessaging() {
		//return messaging;
		return hierarchies;
	}

	public HashMap<String, Double> getPolymorphism() {
		//return polymorphism;
		return hierarchies;
	}

	public HashMap<String, Double> getHierarchies() {
		//return hierarchies;
		return hierarchies;
	}
	
	

}
