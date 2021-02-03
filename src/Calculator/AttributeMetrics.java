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
	private HashMap<String, Integer> mapPublicInstance = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapPublicClass = new HashMap<String, Integer>(); 
	private HashMap<String, Integer> mapPrivateProtectedInstance = new HashMap<String, Integer>(); 
	private HashMap<String, Integer> mapPrivateProtectedClass = new HashMap<String, Integer>(); 
	private HashMap<String, Integer> mapPrivateProtectedTotal = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapTotalAttributes = new HashMap<String, Integer>(); 
	
	/**
	* The constructor currently calls each method in the class for testing purposes
	*/
	public AttributeMetrics(JDTree[] classes) {
		this.calculateAttributes(classes);
		this.printResults();
		//this.numPrivateProtectedClassAttributes(classes);
		//this.numPrivateProtectedInstanceAttributes(classes);
		//this.numPublicClassAttributes(classes);
		//this.numPrivateProtectedAttributes(classes);
		//this.numTotalAttributes(classes);
	}
	
	/**
	* Method numPublicInstanceAttributes determines the number of 
	* public instance attributes in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	*/
	private void calculateAttributes(JDTree[] classes) {
		for(int i = 0; i < classes.length; i++) { 
			//initiate/reset counters for each metric  
			int publicInstance = 0;
			int publicClass = 0;
			int privateProtectedInstance = 0;
			int privateProtectedClass = 0;
			
			Object o = classes[i].getNode(); 							
			if(o instanceof Class) { 			
				Class classNode = (Class) o; 	
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 
					if(!classNode.isAttributeInMethod(attribute)) {
						//call methods to increment counters
						publicInstance += isPublicInstance(attribute);
						publicClass += isPublicClass(attribute);
						privateProtectedInstance += isPrivateProtectedInstance(attribute);
						privateProtectedClass += isPrivateProtectedClass(attribute);	
					}
				}
				//add method counter pair to related hashmap
				this.mapPublicInstance.put(classNode.getIdentifier(), publicInstance); 	
				this.mapPublicClass.put(classNode.getIdentifier(), publicClass);
				this.mapPrivateProtectedInstance.put(classNode.getIdentifier(), privateProtectedInstance);
				this.mapPrivateProtectedClass.put(classNode.getIdentifier(), privateProtectedClass);
				this.mapPrivateProtectedTotal.put(classNode.getIdentifier(), privateProtectedInstance + privateProtectedInstance);
				this.mapTotalAttributes.put(classNode.getIdentifier(), publicInstance + publicClass + privateProtectedInstance + privateProtectedClass);
			}
		}
	}

	private void printResults() {
		System.out.println("==========================================");
		System.out.println("Public Instance Attributes");
		System.out.println("==========================================");
		printMap(mapPublicInstance);
		
		System.out.println("==========================================");
		System.out.println("Public Class Attributes");
		System.out.println("==========================================");
		printMap(mapPublicClass);
		
		System.out.println("==========================================");
		System.out.println("Private & Protected Instance Attributes");
		System.out.println("==========================================");
		printMap(mapPrivateProtectedInstance);
		
		System.out.println("==========================================");
		System.out.println("Private & Protected Class Attributes");
		System.out.println("==========================================");
		printMap(mapPrivateProtectedClass);
		
		System.out.println("==========================================");
		System.out.println("Private & Protected Total");
		System.out.println("==========================================");
		printMap(mapPrivateProtectedTotal);
		
		System.out.println("==========================================");
		System.out.println("Total Attributes");
		System.out.println("==========================================");
		printMap(mapTotalAttributes);
	}

	private int isPublicInstance(Attribute attribute) {
		if(attribute.getModifier().contains("public") && !attribute.getModifier().contains("static")) {
			return 1; 								
		}
		return 0;
	}
	
	private int isPublicClass(Attribute attribute) {
		if(attribute.getModifier().contains("static") && attribute.getModifier().contains("public")) {
			return 1;								
		}
		return 0;
	}
	
	private int isPrivateProtectedInstance(Attribute attribute) {
		if(!attribute.getModifier().contains("public") && !attribute.getModifier().contains("static")) { 
			return 1;											
		}
		return 0;
	}
	
	private int isPrivateProtectedClass(Attribute attribute) {
		if(!attribute.getModifier().contains("public") && attribute.getModifier().contains("static")) {
			return 1;			
		}
		return 0;
	}
	
	public HashMap<String, Integer> getPublicInstanceAttributes(){
		return this.mapPublicInstance;
	}
	
	public HashMap<String, Integer> getPublicClassAttributes(){
		return this.mapPublicClass;
	}
	
	public HashMap<String, Integer> getPrivateProtectedInstanceAttributes(){
		return this.mapPrivateProtectedInstance;
	}
	
	public HashMap<String, Integer> getPrivateProtectedClassAttributes(){
		return this.mapPrivateProtectedClass;
	}
	
	public HashMap<String, Integer> getPrivateProtectedTotal(){
		return this.mapPrivateProtectedTotal;
	}
	
	public HashMap<String, Integer> getTotalAttributes(){
		return this.mapTotalAttributes;
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
	* Method numPrivateProtectedInstanceAttributes determines the number of 
	* private or protected instance attributes in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	* @deprecated
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
	* @deprecated
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
	* @deprecated
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
	* @deprecated
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
	* @deprecated
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
}
