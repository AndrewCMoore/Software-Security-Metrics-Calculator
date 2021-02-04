package Calculator;

import java.util.HashMap;

public class TertiaryMetrics {
	private HashMap<String, Float> CIDA = new HashMap<String, Float>();
	private HashMap<String, Float> CCDA = new HashMap<String, Float>();

	public TertiaryMetrics(PulledValues pv) {
		classifiedInstanceDataAccessibility(pv);
		classifiedClassDataAccessibility(pv);
	}

	private void classifiedClassDataAccessibility(PulledValues pv) {
		// TODO Auto-generated method stub
		
	}

	private void classifiedInstanceDataAccessibility(PulledValues pv) {
		HashMap<String, Integer> publicInstanceAttributes = pv.getMapPublicInstance();
		HashMap<String, Integer> privateProtectedAttributes = pv.getMapPrivateProtectedTotal();
		
		for(String key : publicInstanceAttributes.keySet()) {
			CIDA.put(key, (float) (publicInstanceAttributes.get(key)/privateProtectedAttributes.get(key)));
		}
	}
	
	

}
