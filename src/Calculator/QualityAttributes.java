package Calculator;

import java.util.HashMap;

public class QualityAttributes {
	private HashMap<String, Double> reusability = new HashMap<String, Double>();
	private HashMap<String, Double> flexibility = new HashMap<String, Double>();
	private HashMap<String, Double> understandability = new HashMap<String, Double>();
	private HashMap<String, Double> functionality = new HashMap<String, Double>();
	private HashMap<String, Double> extendability = new HashMap<String, Double>();
	private HashMap<String, Double> effectiveness = new HashMap<String, Double>();

	public QualityAttributes(DesignPrincipals dp) {
		reusability(dp);
		flexibility(dp);
		understandability(dp);
		functionality(dp);
		extendability(dp);
		effectiveness(dp);
	}

	private void reusability(DesignPrincipals dp) {
		for(String key : dp.getAbstraction().keySet()) {
			reusability.put(key, (-0.25 * dp.getCoupling().get(key)) + (0.25 * dp.getCohesion().get(key)) + (0.5 * dp.getMessaging().get(key)) + (0.5 * dp.getDesignSize().get(key)));
		}
	}

	private void extendability(DesignPrincipals dp) {
		for(String key : dp.getAbstraction().keySet()) {
			extendability.put(key, (0.5 * dp.getAbstraction().get(key)) - (0.5 * dp.getCoupling().get(key)) + (0.5 * dp.getInheritance().get(key)) + (0.5 * dp.getPolymorphism().get(key)));
		}
	}

	private void functionality(DesignPrincipals dp) {
		for(String key : dp.getAbstraction().keySet()) {
			functionality.put(key, (0.12 * dp.getCohesion().get(key)) + (0.22 * dp.getPolymorphism().get(key)) + (0.22 * dp.getMessaging().get(key)) + (0.22 * dp.getDesignSize().get(key)) + (0.22 * dp.getHierarchies().get(key)));
		}
	}

	private void understandability(DesignPrincipals dp) {
		for(String key : dp.getAbstraction().keySet()) {
			understandability.put(key, (-0.33 * dp.getAbstraction().get(key)) + (0.33 * dp.getEncapsulation().get(key)) - (0.33 * dp.getCoupling().get(key)) + (0.33 * dp.getCohesion().get(key)) - (0.33 * dp.getPolymorphism().get(key)) - (0.33 * dp.getComplexity().get(key)) - (0.33 * dp.getDesignSize().get(key)));
		}
	}

	private void flexibility(DesignPrincipals dp) {
		for(String key : dp.getAbstraction().keySet()) {
			flexibility.put(key, (0.25 * dp.getEncapsulation().get(key)) - (0.25 * dp.getCoupling().get(key)) + (0.5 * dp.getComposition().get(key)) + (0.5 * dp.getPolymorphism().get(key)));
		}
	}

	private void effectiveness(DesignPrincipals dp) {
		for(String key : dp.getAbstraction().keySet()) {
			effectiveness.put(key, (0.2 * dp.getAbstraction().get(key)) + (0.2 * dp.getEncapsulation().get(key)) + (0.2 * dp.getComposition().get(key)) + (0.2 * dp.getInheritance().get(key)) + (0.2 * dp.getPolymorphism().get(key)));
		}
	}

	public HashMap<String, Double> getReusability() {
		return reusability;
	}

	public HashMap<String, Double> getFlexibility() {
		return flexibility;
	}

	public HashMap<String, Double> getUnderstandability() {
		return understandability;
	}

	public HashMap<String, Double> getFunctionality() {
		return functionality;
	}

	public HashMap<String, Double> getExtendability() {
		return extendability;
	}

	public HashMap<String, Double> getEffectiveness() {
		return effectiveness;
	}
	
	public Double getAverageReusability() {
		return average(reusability);
	}

	public Double getAverageFlexibility() {
		return average(flexibility);
	}

	public Double getAverageUnderstandability() {
		return average(understandability);
	}

	public Double getAverageFunctionality() {
		return average(functionality);
	}

	public Double getAverageExtendability() {
		return average(extendability);
	}

	public Double getAverageEffectiveness() {
		return average(effectiveness);
	}
	
	public Double average(HashMap<String, Double> qualityAttribute) {
		double sum = 0;
		int count = 0;
		
		for(String key : qualityAttribute.keySet()) {
			count ++;
			sum += qualityAttribute.get(key);
		}
		return sum/count;
	}
	

}
