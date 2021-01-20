package calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JTree;
import ssmc.Class;
import ssmc.Method;
import tree.JDTree;

public class InheritanceMetrics extends MetricClasses {
	//Multimap<String, Integer> multiMap;
	Set<String> classNames;
	Set<String> BaseClassesNames;
	//ArrayList<CustomPair> depthOfInhertance;
	HashMap<String,String> superClassofChild;
	HashMap<String, Integer> MethodsInaClass = new HashMap<String, Integer>();
	int NumberOfBaseClasses,maxDepthOfInheritance;
	JDTree[] classes;
	
	public static final boolean DEBUG = false;
	
	boolean extractInheritanceInformation;
	
	public InheritanceMetrics(JDTree[] classes) {
		this.classes = classes;
		this.MethodsInaClass = new HashMap<String, Integer>();
		this.classNames = new HashSet<String>(); 
		this.BaseClassesNames = new HashSet<String>(); 
		//this.depthOfInhertance = new ArrayList<CustomPair>(); 
		this.superClassofChild= new HashMap<String,String>();
		this.maxDepthOfInheritance=0;
		this.NumberOfBaseClasses = 0;
		this.extractInheritanceInformation = false;
		this.extractInheritanceInformation();
		//this.getMethodsInClass(classes);
		//Set<String> depthOfInhertance;
		
	}
	
	/**
	*Get the JDT Tree
	 * @return 
	**/
	public JDTree[] getJDTClasses() {
		return this.classes;
	}
	
	/**
	*extract the Inheritance information from the JDT tree for the Code Artifact.
	*@return
	**/
	public void extractInheritanceInformation() {
		JDTree[] classes = getJDTClasses();
		//int NumberOfBaseClasses=0;
		if (!extractInheritanceInformation) {
			for(int i = 0; i < classes.length; i++) {
				Object o = classes[i].getNode();				
				if(o instanceof Class) {
					Class classNode = (Class) o;
					if (DEBUG )System.out.println("======================================================");
					if (classNames.contains(classNode.getSuperClass()))  { 
						BaseClassesNames.add(classNode.getSuperClass()); //populate a hashSet of superclass names, (can't include duplicates)
						//depthOfInhertance.add(new CustomPair(classNode.getSuperClass(),classNode.getIdentifier()));
						 superClassofChild.put(classNode.getIdentifier(),classNode.getSuperClass()); //get hashMet with the superclass of each child class.
					}
					if (DEBUG ) {
						System.out.println("The superclass for: "+classNode.getIdentifier()+"\nis: "+classNode.getSuperClass());	
						System.out.println("The interfaces for: "+classNode.getIdentifier()+"\nis: "+classNode.getInterfaces().toString()); //add if statement for serilizable
						System.out.println("is the superclass of this class a Thread?: "+classNode.getIdentifier()+"\nThen: "+classNode.isCritical()); //add if statment for Thread.
						System.out.println("======================================================");
					}					
					//System.out.println(classNode.getIdentifier() + " : " + MethodCounter);
					//MethodsInaClass.put(classNode.getIdentifier(), MethodCounter);
				}				
			}
			//System.out.println("======================================================");
			//System.out.println("Max Depth of Inheritance: ");
		}
	}
	
	public int getNumberofBaseClasses() {
		//if (DEBUG) { }
		return BaseClassesNames.size();	
		
	}
	
	public Map<String, ArrayList<String>> getImidiateChildren() {
		Map<String, ArrayList<String>> reverseMap = new HashMap<>(
				superClassofChild.entrySet().stream()
			        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
			        .collect(Collectors.toMap(
			                item -> item.get(0).getValue(),
			                item -> new ArrayList<>(
			                    item.stream()
			                        .map(Map.Entry::getKey)
			                        .collect(Collectors.toList())
			                ))
			        ));
		//reverseMap.keySet();
		if (DEBUG) {
			for (String key :reverseMap.keySet()) {
				System.out.println("The Base class: "+key + "\nhas the following children: "+ reverseMap.get(key));		
			}
		}		 
		return reverseMap;	
	}
	//soon
	/*
	     public int maxDeapthOfInheritance() {
		//given the child strings fromn the super strings in your
		 remove all keys
	}*/
	
	
	
	/*public void setupInheritanceClass(JDTree[] classes) {
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 				
			NumberOfBaseClasses=0;
			if(o instanceof Class) {
				Class classNode = (Class) o;
				classNames.add(classNode.getIdentifier());
				
	}
		}
	}*/
}
