package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import tree.JDTree;
import ssmc.*;
import ssmc.Class;

public class ClassMetrics extends MetricClasses {
	
	public static final boolean DEBUG = false;
	
	HashMap<String, Integer> MethodsInaClass = new HashMap<String, Integer>();
	int numberOfClasses;
	boolean extractedInformation;
	JDTree[] classes;
	
	public ClassMetrics(JDTree[] classes) {
		this.classes = classes;
		this.MethodsInaClass = new HashMap<String, Integer>();
		this.numberOfClasses = 0;
		this.extractedInformation = false;
		this.extractInformation();
		//this.getNumberOfClasses();		
	}
	
	/**
	*Get the JDT Tree
	 * @return 
	**/
	public JDTree[] getJDTClasses() {
		return this.classes;
	}	
	
	/**
	* @return number of classes in the code Artifact
	**/		
	public int getNumberOfClasses() {
		if (DEBUG) System.out.println("numberOfClasses");
		if (!extractedInformation) {
			extractInformation();			
		}
		return numberOfClasses;
	}
	
	/**
	* @return number of methods in each class in the code Artifact
	**/		
	public HashMap<String, Integer> getNumberOfMethods() {
		if (DEBUG) {
			System.out.println("methodsInClass");
			printMap(MethodsInaClass);
		}
		if (!extractedInformation) {
			extractInformation();			
		}
		return MethodsInaClass;
	}
	
	//#################
	//Notes: getLengthOfMethod working but untested AND you may need to use a hashmap of hashmaps here. <class, <method, size>>. 	
	//#################
	
	/**
	* @return length of each method in code Artifact 
	**/		
	public int getLengthOfMethod() {
		if (DEBUG) System.out.println("LengthOfMethod");
		if (!extractedInformation) {
			extractInformation();			
		}
		return -1;
	}
	
	/**
	*Get all the required information for the metrics
	 * @return 
	**/
	public void extractInformation() {		
		JDTree[] classes = getJDTClasses();
		
		if (DEBUG) {
			System.out.println("======================================================");
			System.out.println("Methods In A Class");
			System.out.println("======================================================");
		}
		
		int MethodCounter; //record the number of methods per class
				
		/**
		*extract all the information we need for Metrics and 
		*else
		*populate and return the Hashmap
		 * @return 
		**/
		if (!extractedInformation) {
			for(int i = 0; i < classes.length; i++) { //for each class node				
				Object o = classes[i].getNode(); //get JDT tree class node			
				MethodCounter=0;				 //Set method counter to 0				
				if(o instanceof Class) {		 //check if object is a class 					
					Class classNode = (Class) o;
					numberOfClasses++;           //count number of classes;
					ArrayList<Method> methodList = classNode.getMethods();	//get ArrayList of Methods in the class;				
					if (DEBUG) System.out.println("In class: "+classNode.getIdentifier());					
					for(Method method : methodList) {						
						MethodCounter++;		                                                                             //method.getStatements().toString() works but you need to do alot of trimming.        
						//System.out.println("For method name: "+ method.getIdentifier()+"\n it has the following modifiers: " +method.getFirstStatement()+"\n It is This many lines of code long: "+ (method.getEndLine()-method.getStartLine())+"\n Or This Many Lines of Code: "+method.getStatements().size());
					}						
					if (DEBUG) System.out.println(classNode.getIdentifier() + " : " + MethodCounter);
					MethodsInaClass.put(classNode.getIdentifier(), MethodCounter);
				}					
			}			
			extractedInformation=true;			
		 }		
	}	
}
