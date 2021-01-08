package Calculator;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;
import tree.JDTree;
import ssmc.*;
import ssmc.Class;

public class AttributeMetrics {
	
	protected int numPublicInstanceAttributes(JDTree[] classes) {
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 						
					//System.out.println(attribute.toString());
					if(attribute.getModifier().equals("Public ")) { 					
						count++; 												
					}
				}
				//this will be changed to insert into a data structure instead of printing
				System.out.println("Public Instance Attributes in " + classNode.getIdentifier() + " : " + count);
				total += count;
				count = 0;
			}
		}
		System.out.println("program total: " + total);
		return total;
	}
	
	protected int numPrivateProtectedInstanceAttributes(JDTree[] classes) { 
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 						
					//System.out.println(attribute.toString());
					if(attribute.getModifier().equals("Private ") || attribute.getModifier().equals("Protected ") || attribute.getModifier().equals("")) {
						if(!classNode.isAttributeInMethod(attribute)) {
							count++;
						} 												
					}
				}
				//this will be changed to insert into a data structure instead of printing
				System.out.println("Private Protected Instance Attributes in " + classNode.getIdentifier() + " : " + count);
				total += count;
				count = 0;
			}
		}
		System.out.println("program total: " + total);
		return total;
	}
	
	protected int numPublicClassAttributes(JDTree[] classes) {
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				//System.out.println(attributeList);
				for(Attribute attribute : attributeList) {
					//System.out.println("       " + attribute.toString());
					if(attribute.getModifier().equals("Static Public ") || classNode.getEnum()) {
						count++;											
					}
				}
				//this will be changed to insert into a data structure instead of printing
				System.out.println("Public Class Attributes in " + classNode.getIdentifier() + " : " + count);
				total += count;
				count = 0;
			}
		}
		System.out.println("program total: " + total);
		return total;
	}
	
	protected int numPrivateProtectedClassAttributes(JDTree[] classes) {
		int total = 0;
		int count = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									
			if(o instanceof Class) { 											
				Class classNode = (Class) o; 									
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
				for(Attribute attribute : attributeList) { 						
					System.out.println(attribute.toString());
					if(attribute.getModifier().equals("Static Private ") || attribute.getModifier().equals("Static Protected ") || attribute.getModifier().equals("Static ")) {
						count++;											
					}
				}
				//this will be changed to insert into a data structure instead of printing
				System.out.println("Private Protected Class Attributes in " + classNode.getIdentifier() + " : " + count);
				total += count;
				count = 0;
			}
		}
		System.out.println("program total: " + total);
		return total;
	}
	
	
	protected int numNonFinalPrivateProtectedMethods(JDTree[] classes) {
		int total = 0;
		int count = 0;

		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode();
			if(o instanceof Class) {
				Class classNode = (Class) o;
				ArrayList<Method> methodList = classNode.getMethods();
				for(Method method : methodList) {
					System.out.println(method.toString() + ": classified: " + method.getClassified() + ", finalized: " + method.getFinalized());
					if(method.getClassified() && !method.getFinalized()) {
						count++;
					}
				}
				System.out.println("Non-Final Private Protected Methods in " + classNode.getIdentifier() + " : " + count);
				total += count;
				count = 0;
			}
			//ArrayList<Method> methods = (Method) classes[i]
		}
		System.out.println("program total: " + total);
		return total;
	}
}
