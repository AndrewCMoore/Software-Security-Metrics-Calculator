package Calculator;

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
	HashMap<String,String> immediateChildren;
	HashMap<String, Integer> MethodsInaClass = new HashMap<String, Integer>();
	int NumberOfBaseClasses,maxDepthOfInheritance;
	
	boolean CalculatedMethodsInaClass;
	
	public InheritanceMetrics(JDTree[] classes) {
		this.MethodsInaClass = new HashMap<String, Integer>();
		this.classNames = new HashSet<String>(); 
		this.BaseClassesNames = new HashSet<String>(); 
		//this.depthOfInhertance = new ArrayList<CustomPair>(); 
		this.immediateChildren= new HashMap<String,String>();
		this.maxDepthOfInheritance=0;
		this.NumberOfBaseClasses = 0;
		this.CalculatedMethodsInaClass = false;
		this.setupInheritanceClass(classes);
		this.getMethodsInClass(classes);
		Set<String> depthOfInhertance;
		
	}
	
	public HashMap<String, Integer> getMethodsInClass(JDTree[] classes) {
		System.out.println("======================================================");
		System.out.println("SuperClass of A Class");
		System.out.println("======================================================");
		int NumberOfBaseClasses=0;
		if (CalculatedMethodsInaClass) {
			return MethodsInaClass;
		} else { 		
			
			for(int i = 0; i < classes.length; i++) {
				Object o = classes[i].getNode();				
				if(o instanceof Class) {
					Class classNode = (Class) o;
					System.out.println("======================================================");
					if (classNames.contains(classNode.getSuperClass()))  { 
						BaseClassesNames.add(classNode.getSuperClass()); 
						//depthOfInhertance.add(new CustomPair(classNode.getSuperClass(),classNode.getIdentifier()));
						immediateChildren.put(classNode.getIdentifier(),classNode.getSuperClass());
					}
					System.out.println("The superclass for: "+classNode.getIdentifier()+"\nis: "+classNode.getSuperClass());	
					System.out.println("The interfaces for: "+classNode.getIdentifier()+"\nis: "+classNode.getInterfaces().toString());
					System.out.println("is the superclass of this class a Thread?: "+classNode.getIdentifier()+"\nThen: "+classNode.isCritical());
					
					System.out.println("======================================================");
					//System.out.println(classNode.getIdentifier() + " : " + MethodCounter);
					//MethodsInaClass.put(classNode.getIdentifier(), MethodCounter);
				}				
				
			}
			System.out.println("======================================================");
			System.out.println("======================================================");
			System.out.println("======================================================");
			System.out.println("# base Classes :"+BaseClassesNames.size());		// the bottom most class do not count.	
			System.out.println("Max Depth of Inheritance: ");
			Map<String, ArrayList<String>> reverseMap = new HashMap<>(
					immediateChildren.entrySet().stream()
				        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
				        .collect(Collectors.toMap(
				                item -> item.get(0).getValue(),
				                item -> new ArrayList<>(
				                    item.stream()
				                        .map(Map.Entry::getKey)
				                        .collect(Collectors.toList())
				                ))
				        ));
			for (String key :reverseMap.keySet())
			System.out.println("The Base class: "+key + "\nhas the following children: "+ reverseMap.get(key));
			//reverseMap.keySet();

			//cp:depthOfInhertance can be solved using creating a List of Bi Directional Nodes.
			
		}
		
		/*for (CustomPair cp:depthOfInhertance) { 
			System.out.println(cp.getV() +" : "+ cp.getK());
		}*/
		return null;
	
	}
	

	//soon
	/*public int maxDeapthOfInheritance() {
		//given the child strings fromn the super strings in your
		 remove all keys
	}*/
	
	
	
	public void setupInheritanceClass(JDTree[] classes) {
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 				
			NumberOfBaseClasses=0;
			if(o instanceof Class) {
				Class classNode = (Class) o;
				classNames.add(classNode.getIdentifier());
				
	}
		}
	}
	
	
	
	
}
