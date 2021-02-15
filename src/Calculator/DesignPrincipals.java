package Calculator;

import java.util.HashMap;

public class DesignPrincipals {
	private HashMap<String, Float> abstraction = new HashMap<String, Float>();
	private HashMap<String, Float> cohesion = new HashMap<String, Float>();
	private HashMap<String, Float> complexity = new HashMap<String, Float>();
	private HashMap<String, Float> composition = new HashMap<String, Float>();
	private HashMap<String, Float> coupling = new HashMap<String, Float>();
	private HashMap<String, Float> designSize = new HashMap<String, Float>();
	private HashMap<String, Float> encapsulation = new HashMap<String, Float>();
	private HashMap<String, Float> inheritance = new HashMap<String, Float>();
	private HashMap<String, Float> messaging = new HashMap<String, Float>();
	private HashMap<String, Float> polymorphism = new HashMap<String, Float>();
	private HashMap<String, Float> hierarchies = new HashMap<String, Float>();

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
	}
	
	//none of these are correct, just placeholders
	private void hierarchies(PrimaryMetrics pm) {
		for (String key: pm.getReduceAttackSurface().keySet()) {
			hierarchies.put(key, (float) 1);
		}
		
	}

	private void polymorphism(PrimaryMetrics pm) {
		// TODO Auto-generated method stub
		
	}

	private void messaging(PrimaryMetrics pm) {
		// TODO Auto-generated method stub
		
	}

	private void inheritance(PrimaryMetrics pm) {
		// TODO Auto-generated method stub
		
	}

	private void encapsulation(PrimaryMetrics pm) {
		// TODO Auto-generated method stub
		
	}

	private void designSize(PrimaryMetrics pm) {
		// TODO Auto-generated method stub
		
	}

	private void coupling(PrimaryMetrics pm) {
		// TODO Auto-generated method stub
		
	}

	private void composition(PrimaryMetrics pm) {
		// TODO Auto-generated method stub
		
	}
	
	private void complexity(PrimaryMetrics pm) {
		// TODO Auto-generated method stub
		
	}

	private void cohesion(PrimaryMetrics pm) {
		// TODO Auto-generated method stub
		
	}

	private void abstraction(PrimaryMetrics pm) {
		// TODO Auto-generated method stub
		
	}

	public HashMap<String, Float> getAbstraction() {
		//return abstraction;
		return hierarchies;
	}

	public HashMap<String, Float> getCohesion() {
		//return cohesion;
		return hierarchies;
	}
	
	public HashMap<String, Float> getComplexity() {
		//return complexity;
		return hierarchies;
	}

	public HashMap<String, Float> getComposition() {
		//return composition;
		return hierarchies;
	}

	public HashMap<String, Float> getCoupling() {
		//return coupling;
		return hierarchies;
	}

	public HashMap<String, Float> getDesignSize() {
		//return designSize;
		return hierarchies;
	}

	public HashMap<String, Float> getEncapsulation() {
		//return encapsulation;
		return hierarchies;
	}

	public HashMap<String, Float> getInheritance() {
		//return inheritance;
		return hierarchies;
	}

	public HashMap<String, Float> getMessaging() {
		//return messaging;
		return hierarchies;
	}

	public HashMap<String, Float> getPolymorphism() {
		//return polymorphism;
		return hierarchies;
	}

	public HashMap<String, Float> getHierarchies() {
		//return hierarchies;
		return hierarchies;
	}
	
	

}
