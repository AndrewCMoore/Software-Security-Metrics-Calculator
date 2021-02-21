package Report_Generation;

import java.util.HashMap;

public class calculateValues {
	
	private HashMap<String, ?> hashmap;
	private float sum;
	private float mean;
	private float std;
	private float highestValue;
	private float lowestValue;
	private float count;
	
	public calculateValues(HashMap<String, ?> hashmap) {
		this.hashmap = hashmap;
		this.count = hashmap.size();
		mean();
		sum();
		standardDeviation();
		highLowValues();

	}
	
	private void highLowValues() {
		float highestValue = Integer.MIN_VALUE;
		float lowestValue = Integer.MAX_VALUE;
		
		for(String key : hashmap.keySet()) {
			
			if((float) hashmap.get(key) > highestValue) {
				highestValue = (float) hashmap.get(key);
			}
			
			if((float) hashmap.get(key) < lowestValue) {
				lowestValue = (float) hashmap.get(key);
			}
			
		}
		
		this.highestValue = highestValue;
		this.lowestValue = lowestValue;
	}
	private void mean() {
		// Set the mean to the sum divide by the number of classes
		this.mean = sum / count;
	}
	
	public void sum() {
		// Initialize the local variable
		float sum = 0;
		// For each Class in the project
		for(String key : hashmap.keySet()) {
			// Add the value to the sum
			sum += (float) hashmap.get(key);
		}
		// Set the global sum variable
		this.sum = sum;
	}
	
	public void standardDeviation() {
		// Initialize the local variable
		float standardDeviation = 0;
		// For each Class in the project
		for(String key : hashmap.keySet()) {
			// Return the value for the class
			standardDeviation += Math.pow(((float) hashmap.get(key) - mean), 2);
		}
		// Set the global standard deviation variable
		this.std = standardDeviation;
	}

	
}
