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
	Set<String> baseClassesNames;
	//ArrayList<CustomPair> depthOfInhertance;
	HashMap<String,String> superClassofChild;
	HashMap<String, Integer> methodsInaClass = new HashMap<String, Integer>();
	int NumberOfBaseClasses,maxDepthOfInheritance;
	JDTree[] classes;
	
	public static final boolean DEBUG = false;
	
	boolean extractInheritanceInformation;
	
	public InheritanceMetrics(JDTree[] classes) {
		this.classes = classes;
		this.methodsInaClass = new HashMap<String, Integer>();
		this.classNames = new HashSet<String>(); 
		this.baseClassesNames = new HashSet<String>(); 
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
						baseClassesNames.add(classNode.getSuperClass()); //populate a hashSet of superclass names, (can't include duplicates)
						//depthOfInhertance.add(new CustomPair(classNode.getSuperClass(),classNode.getIdentifier()));
						superClassofChild.put(classNode.getIdentifier(),classNode.getSuperClass()); //get hashMet with the superclass of each child class.
					}
					//For Temporary Testing
					if (DEBUG ) {
						System.out.println("The superclass for: "+classNode.getIdentifier()+"\nis: "+classNode.getSuperClass());	
						System.out.println("The interfaces for: "+classNode.getIdentifier()+"\nis: "+classNode.getInterfaces().toString()); 
						System.out.println("is the superclass of this class a Thread?: "+classNode.getIdentifier()+"\nThen: "+classNode.isCritical()); 
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
	
	/**
	*Returns {@link #BaseClassesNames} 
	 * @return 
	**/
	public int getNumberofBaseClasses() {
		//if (DEBUG) { }
		return baseClassesNames.size();	
		
	}
	
	/**
	*Use a reverse HashMap  a Hashmap containing a superClassofChilds to reverse and map the associations while linking all children to a superclass
	 * @return 
	**/
	
	public Map<String, ArrayList<String>> getImidiateChildren() {  //<string,int> when fully tested. 
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
	
	//soon, needs to be redone. this is simple as getImidiateChildren() gives you the superclass with all children.
	/*
	     public int maxDeapthOfInheritance() {
		//given the child strings fromn the super strings in your
		 remove all keys
	}*/
	}
