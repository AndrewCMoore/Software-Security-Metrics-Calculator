package Calculator;

import java.util.HashMap;

public abstract class MetricClasses {
	
	public void printMap(HashMap<String, String> depthOfInhertance) {
		for(String key : depthOfInhertance.keySet()) {
			System.out.println(key + ": " + depthOfInhertance.get(key) + ", ");
			
		}
	}
	
	public void printStringMap(HashMap<String, String> depthOfInhertance) {
		for(String key : depthOfInhertance.keySet()) {
			System.out.println(key + ": " + depthOfInhertance.get(key) + ", ");
			
		}
	}

}
