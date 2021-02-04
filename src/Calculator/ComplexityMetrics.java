package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jdt.core.dom.Statement;

import ssmc.Method;
import ssmc.StatementVisitor;
import ssmc.Class;
import tree.JDTree;

/**
* The AttributeMetrics class contains all of the metrics that are related 
* to method complexity
*
* @author  Paul Hewson
*/
public class ComplexityMetrics {
	private HashMap<String, Integer> mapMethodComplexity = new HashMap<String, Integer>();
	
	/**
	* The constructor currently calls each method in the class for testing purposes
	*/
	public ComplexityMetrics(JDTree[] classes) {
		calculateComplexity(classes);
		printResults();
		//this.methodComplexity(classes);
	}
	
	public HashMap<String, Integer> getCyclomaticComplexity() {
		return mapMethodComplexity;
	}

	private void calculateComplexity(JDTree[] classes) {
		for(int i = 0; i < classes.length; i++) {
			int methodComplexity = 0;
			
			Object o = classes[i].getNode();
			if(o instanceof Class) {		
				Class classNode = (Class) o;	
				ArrayList<Method> methodList = classNode.getMethods(); 
				for(Method method : methodList) { 						
					methodComplexity += countComplexity(method); 
				}
				mapMethodComplexity.put(classNode.getIdentifier(), methodComplexity);
			}
		}
	}

	private void printResults() {
		System.out.println("==========================================");
		System.out.println("Cyclomatic Complexity");
		System.out.println("==========================================");
		printMap(mapMethodComplexity);	
	}
	
	private int countComplexity(Method method) {
		System.out.println(method.getIdentifier() + " has complexity: " + method.getMethodComplexity());
		return method.getMethodComplexity();
	}

	/**
	* Method printMap iterates over each key value pair in a 
	* hashmap and prints them out.
	* @param HashMap<String, Integer> The hashmap to me printed
	*/
	private void printMap(HashMap<String, Integer> map) {
		for(String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key) + ", ");
		}
	}
	
	/**
	* Method methodComplexity determines the cyclomatic complexity 
	* of each method and counts the total complexity in each class
	* 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	* @deprecated
	*/
	private HashMap<String, Integer> methodComplexity(JDTree[] classes) {
		System.out.println("======================================================");
		System.out.println("Method Complexity");
		System.out.println("======================================================");
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) { //iterate over classes
			Object o = classes[i].getNode();	//get current class node as an object
			if(o instanceof Class) {			//ensure that object is of type class
				Class classNode = (Class) o;	//cast object to type class
				ArrayList<Method> methodList = classNode.getMethods(); //get each method in the class
				for(Method method : methodList) { 	//iterate over method
					System.out.println("     " + method.getIdentifier() + " : " + method.getMethodComplexity());						
					count += method.getMethodComplexity(); //add method complexity to class total
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count); //add class, count pair to hashmap
				total += count;					//add count for class to program total
				count = 0;						//reset counter for next class
			}
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);			//add program total to hashmap
		//printMap(calcMap);
		return calcMap;
	}
}
