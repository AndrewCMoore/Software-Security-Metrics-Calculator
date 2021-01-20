package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import tree.JDTree;
import ssmc.*;
import ssmc.Class;

/**
* The AttributeMetrics class contains all of the metrics that are related 
* to attributes
*
* @author  Paul Hewson
*/
public class AttributeMetrics {
	
	/**
	* The constructor currently calls each method in the class for testing purposes
	*/
	public AttributeMetrics(JDTree[] classes) {
		this.numPrivateProtectedClassAttributes(classes);
		this.numPrivateProtectedInstanceAttributes(classes);
		this.numPublicClassAttributes(classes);
		this.numPublicInstanceAttributes(classes);
		this.numPrivateProtectedAttributes(classes);
		this.numTotalAttributes(classes);
	}
	
	/**
	* Method numPublicInstanceAttributes determines the number of 
	* public instance attributes in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	*/
	private HashMap<String, Integer> numPublicInstanceAttributes(JDTree[] classes) {
		System.out.println("======================================================");
		System.out.println("Public Instance Attributes");
		System.out.println("======================================================");
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>(); 
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) { //iterate over classes
			Object o = classes[i].getNode(); 	//get current class node as an object						
			if(o instanceof Class) { 			//ensure that object is of type class
				Class classNode = (Class) o; 	//cast object to type class
				ArrayList<Attribute> attributeList = classNode.getAttributes(); //get attributes for current class
				for(Attribute attribute : attributeList) { 	
					if(attribute.getModifier().contains("public") && !attribute.getModifier().contains("static")) { 				
						count++; 			//increment counter only if the attribute is public and not static									
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count); //add class, count pair to hashmap
				total += count; 			//add count for class to program total
				count = 0;					//reset counter for next class
			}
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);		//add program total to hashmap
		//printMap(calcMap);
		return calcMap;
	}
	
	/**
	* Method numPrivateProtectedInstanceAttributes determines the number of 
	* private or protected instance attributes in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	*/
	private HashMap<String, Integer> numPrivateProtectedInstanceAttributes(JDTree[] classes) { 
		System.out.println("======================================================");
		System.out.println("Private or Protected Instance Attributes");
		System.out.println("======================================================");
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 		
					if(!attribute.getModifier().contains("public") && !attribute.getModifier().contains("static")) { 
						if(!classNode.isAttributeInMethod(attribute)) { //check if it is an attribute with default modifier and not a variable defined within a method
							count++;		//Increment counter only if the attribute is not private or protected, and is not static
						} 												
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);
		//printMap(calcMap);
		return calcMap;
	}
	
	/**
	* Method numPublicClassAttributes determines the number of 
	* public class attributes in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	*/
	private HashMap<String, Integer> numPublicClassAttributes(JDTree[] classes) {
		System.out.println("======================================================");
		System.out.println("Public Class Attributes");
		System.out.println("======================================================");
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) {
					if(attribute.getModifier().contains("static") && attribute.getModifier().contains("public")) {
						count++;			//Increment counter only if the attribute is public or static								
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);
		//printMap(calcMap);
		return calcMap;
	}
	
	/**
	* Method numPrivateProtectedClassAttributes determines the number of 
	* private or protected class attributes in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	*/
	private HashMap<String, Integer> numPrivateProtectedClassAttributes(JDTree[] classes) {
		System.out.println("======================================================");
		System.out.println("Private or Protected Class Attributes");
		System.out.println("======================================================");
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 		
					if(attribute.getModifier().contains("static") && (attribute.getModifier().contains("protected ") || attribute.getModifier().contains("private"))) {
						count++;				//Increment counter only if the attribute is private or protected, and is static							
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);
		//printMap(calcMap);
		return calcMap;
	}
	
	/**
	* Method numPrivateProtectedAttributes determines the number of 
	* private or protected instance and class attributes in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	*/
	private HashMap<String, Integer> numPrivateProtectedAttributes(JDTree[] classes) { 
		System.out.println("======================================================");
		System.out.println("Total Private or Protected Attributes");
		System.out.println("======================================================");
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 	
					if(!attribute.getModifier().contains("public")) {
						if(!classNode.isAttributeInMethod(attribute)) {
							count++;		//Increment counter only if the attribute is private or protected
						} 												
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);
		//printMap(calcMap);
		return calcMap;
	}
	
	/**
	* Method numTotalAttributes determines the total number 
	* of attributes in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	*/
	private HashMap<String, Integer> numTotalAttributes(JDTree[] classes){
		System.out.println("======================================================");
		System.out.println("Total Attributes");
		System.out.println("======================================================");
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 		
					if(!classNode.isAttributeInMethod(attribute)) { //exclude all variable declarations within methods
						count++;		//Increment counter for each attribute									
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);
		//printMap(calcMap);
		return calcMap;
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
}
