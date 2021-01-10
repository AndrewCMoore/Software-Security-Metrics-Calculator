package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import tree.JDTree;
import ssmc.*;
import ssmc.Class;

public class AttributeMetrics {
	
	public AttributeMetrics(JDTree[] classes) {
		this.numPrivateProtectedClassAttributes(classes);
		this.numPrivateProtectedInstanceAttributes(classes);
		this.numPublicClassAttributes(classes);
		this.numPublicInstanceAttributes(classes);
	}
	
	private HashMap<String, Integer> numPublicInstanceAttributes(JDTree[] classes) {
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 						
					//System.out.println(attribute.toString());
					if(attribute.getModifier().contains("public") && !attribute.getModifier().contains("static")) { 					
						count++; 												
					}
				}
				//System.out.println("Public Instance Attributes in " + classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
		}
		//System.out.println("program total: " + total);
		calcMap.put("total", total);
		System.out.println("** numPublicInstanceAttributes **");
		printMap(calcMap);
		return calcMap;
	}
	
	private HashMap<String, Integer> numPrivateProtectedInstanceAttributes(JDTree[] classes) { 
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 						
					//System.out.println(attribute.toString());
					if(attribute.getModifier().contains("private") || attribute.getModifier().contains("protected") || attribute.getModifier().contains("")) {
						if(!classNode.isAttributeInMethod(attribute) && !attribute.getModifier().contains("static")) {
							count++;
						} 												
					}
				}
				//System.out.println("Private Protected Instance Attributes in " + classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
		}
		//System.out.println("program total: " + total);
		calcMap.put("total", total);
		System.out.println("** numPrivateProtectedInstanceAttributes **");
		printMap(calcMap);
		return calcMap;
	}
	
	private HashMap<String, Integer> numPublicClassAttributes(JDTree[] classes) {
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				//System.out.println(attributeList);
				for(Attribute attribute : attributeList) {
					//System.out.println(attribute.toString());
					if(attribute.getModifier().contains("static") && attribute.getModifier().contains("public")) {
						count++;											
					}
				}
				//this will be changed to insert into a data structure instead of printing
				//System.out.println("Public Class Attributes in " + classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
		}
		//System.out.println("program total: " + total);
		calcMap.put("total", total);
		System.out.println("** numPublicClassAttributes **");
		printMap(calcMap);
		return calcMap;
	}
	
	private HashMap<String, Integer> numPrivateProtectedClassAttributes(JDTree[] classes) {
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 						
					//System.out.println(attribute.toString());
					if(attribute.getModifier().contains("static") && (attribute.getModifier().contains("protected ") || attribute.getModifier().contains("private"))) {
						count++;											
					}
				}
				//this will be changed to insert into a data structure instead of printing
				//System.out.println("Private Protected Class Attributes in " + classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
		}
		//System.out.println("program total: " + total);
		calcMap.put("total", total);
		System.out.println("** numPrivateProtectedClassAttributes **");
		printMap(calcMap);
		return calcMap;
	}
	
	private void printMap(HashMap<String, Integer> map) {
		for(String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key) + ", ");
		}
		
	}
}
