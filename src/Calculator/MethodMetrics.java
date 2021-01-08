package Calculator;

import java.util.ArrayList;

import ssmc.Class;
import ssmc.Method;
import tree.JDTree;

public class MethodMetrics {

	protected int numNonFinalPrivateProtectedMethods(JDTree[] classes) {
		int total = 0;
		int count = 0;

		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode();
			if(o instanceof Class) {
				Class classNode = (Class) o;
				ArrayList<Method> methodList = classNode.getMethods();
				for(Method method : methodList) {
					System.out.println(method.toString() + ": modifiers: " + method.getModifiers().toString() + ", finalized: " + method.getFinalized());
					if((method.getModifiers().contains("private") || method.getModifiers().contains("protected")) && !method.getFinalized()) {
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
