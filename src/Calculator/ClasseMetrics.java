package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import tree.JDTree;
import ssmc.*;
import ssmc.Class;

public class ClasseMetrics extends MetricClasses {
	
	HashMap<String, Integer> MethodsInaClass = new HashMap<String, Integer>();
	int NumberOfClasses;
	boolean CalculatedMethodsInaClass;
	
	public ClasseMetrics(JDTree[] classes) {
		this.MethodsInaClass = new HashMap<String, Integer>();
		this.NumberOfClasses = 0;
		this.CalculatedMethodsInaClass = false;
		this.getMethodsInClass(classes);
		
	}
	
	public HashMap<String, Integer> getMethodsInClass(JDTree[] classes) {
		System.out.println("======================================================");
		System.out.println("Methods In A Class");
		System.out.println("======================================================");
		int MethodCounter;
		if (CalculatedMethodsInaClass) {
			return MethodsInaClass;
		} else {		
	
			for(int i = 0; i < classes.length; i++) {
				Object o = classes[i].getNode(); 				
				MethodCounter=0;
				if(o instanceof Class) {
					Class classNode = (Class) o;
					ArrayList<Method> methodList = classNode.getMethods();						
					System.out.println("In class: "+classNode.getIdentifier());
					for(Method method : methodList) {
						MethodCounter++;		                                                                             //method.getStatements().toString() works but you need to do alot of trimming.        
						//System.out.println("For method name: "+ method.getIdentifier()+"\n it has the following modifiers: " +method.getFirstStatement()+"\n It is This many lines of code long: "+ (method.getEndLine()-method.getStartLine())+"\n Or This Many Lines of Code: "+method.getStatements().size());
						
					}
					
					System.out.println(classNode.getIdentifier() + " : " + MethodCounter);
					MethodsInaClass.put(classNode.getIdentifier(), MethodCounter);
					System.out.println("======================================================");
					System.out.println("======================================================");
					System.out.println("======================================================");
					System.out.println("======================================================");
					System.out.println("======================================================");
				}				
			}
			CalculatedMethodsInaClass=true;
		}
		
		//printMap(MethodsInaClass);
		return MethodsInaClass;
	}
	
		

}
