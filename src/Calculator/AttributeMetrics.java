package Calculator;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;
import tree.JDTree;
import ssmc.*;
import ssmc.Class;

public class AttributeMetrics {
	
	
	
	protected int numPublicInstanceAttributes(JDTree tree) {
		JDTree[] classes = tree.getLeefs(); //returns JDTree array containing all classes
		int total = 0;
		int count = 0;
		
		//iterate over each class node
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									//get the node as an object
			if(o instanceof Class) { 											//make sure that the node is actually a class
				Class classNode = (Class) o; 									//cast object to type class
				ArrayList<Attribute> attributeList = classNode.getAttributes(); //get arraylist of attributes for current class
				for(Attribute attribute : attributeList) { 						//iterate over attributes
					//System.out.println(attribute.toString());
					if(attribute.getModifier().equals("Public ")) { 					//check if public
						count++; 												//update count
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
}
