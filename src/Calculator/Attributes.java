package Calculator;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;
import tree.JDTree;
import ssmc.*;
import ssmc.Class;

public class Attributes {



    public int numPublicInstanceAttributes(JDTree myTree) {
        JDTree[] classes = myTree.getLeefs(); //returns JDTree array containing all classes
        Object o = classes[0].getNode();
        int count = 0;
        if(o instanceof Class) {
            Class c = (Class) o;
            ArrayList<Attribute> a = c.getAttributes();
            for(Attribute attribute : a) {
                if(attribute.getModifier() == "Public ") {
                    count++;
                }
            }
            System.out.println("+++++++++++++++++++++++++++++" + count);
        }
        return count;
    }
}