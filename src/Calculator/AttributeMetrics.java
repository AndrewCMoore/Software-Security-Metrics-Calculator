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
					if(attribute.getModifier().contains("public") && !attribute.getModifier().contains("static")) { 					
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
					if(attribute.getModifier().contains("private") || attribute.getModifier().contains("protected")) {
						if(!classNode.isAttributeInMethod(attribute) && !attribute.getModifier().contains("static")) {
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
					System.out.println(attribute.toString());
					if(attribute.getModifier().contains("static") && attribute.getModifier().contains("public")) {
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
					//System.out.println(attribute.toString());
					if(attribute.getModifier().contains("static") && (attribute.getModifier().contains("protected ") || attribute.getModifier().contains("private"))) {
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
}
