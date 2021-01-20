package calculator;

import java.util.HashMap;

public abstract class MetricClasses {
	
	public void printMap(HashMap<String, Integer> methodsInaClass) {
		for(String key : methodsInaClass.keySet()) {
			System.out.println(key + ": " + methodsInaClass.get(key) + ", ");
			
		}
	}
	
	public void printStringMap(HashMap<String, String> depthOfInhertance) {
		for(String key : depthOfInhertance.keySet()) {
			System.out.println(key + ": " + depthOfInhertance.get(key) + ", ");
			
		}
	}

}
