package Calculator;

import java.util.HashMap;

public class TertiaryMetrics {
	private HashMap<String, Float> classifiedInstanceDataAccessibility  = new HashMap<String, Float>();
	private HashMap<String, Float> classifiedClassDataAccessibility = new HashMap<String, Float>();

	public TertiaryMetrics(PulledValues pv) { 
		classifiedInstanceDataAccessibility(pv);
		classifiedClassDataAccessibility(pv);
	}

	private void classifiedClassDataAccessibility(PulledValues pv) {
		HashMap<String, Integer> publicInstanceAttributes = pv.getMapPublicInstance();
		HashMap<String, Integer> privateProtectedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : publicInstanceAttributes.keySet()) {
			
		}
		
	}

	private void classifiedInstanceDataAccessibility(PulledValues pv) {
		HashMap<String, Integer> publicInstanceAttributes = pv.getMapPublicInstance();
		HashMap<String, Integer> privateProtectedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : publicInstanceAttributes.keySet()) {
			if(privateProtectedAttributes.get(key) != 0) {
				classifiedInstanceDataAccessibility.put(key, (float) (publicInstanceAttributes.get(key)/privateProtectedAttributes.get(key)));		
			}
			else {
				classifiedInstanceDataAccessibility.put(key, (float) (publicInstanceAttributes.get(key)));
			}
		}
	}
	
	

}
