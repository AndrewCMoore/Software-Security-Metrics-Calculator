package Calculator;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;
import tree.JDTree;
import ssmc.*;
import ssmc.Class;

public class AttributeMetrics {
	
	
	
	protected int numPublicInstanceAttributes(JDTree tree) {
		JDTree[] classes = tree.getLeefs(); //returns JDTree array containing all classes
		int count = 0;
		
		//iterate over each class node
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 									//get the node as an object
			if(o instanceof Class) { 											//make sure that the node is actually a class
				Class classNode = (Class) o; 									//cast object to type class
				ArrayList<Attribute> attributeList = classNode.getAttributes(); //get arraylist of attributes for current class
				for(Attribute attribute : attributeList) { 						//iterate over attributes
					if(attribute.getModifier() == "Public ") { 					//check if public
						count++; 												//update count
					}
				}
			}
		}
		System.out.println(count);
		return count;
	}
	
}
