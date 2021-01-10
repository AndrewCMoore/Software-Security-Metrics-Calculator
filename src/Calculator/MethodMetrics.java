package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import ssmc.Class;
import ssmc.Method;
import tree.JDTree;

public class MethodMetrics {

	public MethodMetrics(JDTree[] classes) {
		//this.numNonFinalPrivateProtectedMethods(classes);
		this.numPublicMethods(classes);
	}

	private HashMap<String, Integer> numNonFinalPrivateProtectedMethods(JDTree[] classes) {
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;

		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode();
			if(o instanceof Class) {
				Class classNode = (Class) o;
				ArrayList<Method> methodList = classNode.getMethods();
				for(Method method : methodList) {
					//System.out.println(method.toString() + ": modifiers: " + method.getModifiers().toString() + ", finalized: " + method.getFinalized());
					if((method.getModifiers().contains("private") || method.getModifiers().contains("protected")) && !method.getFinalized()) {
						count++;
					}
				}
				//System.out.println("Non-Final Private Protected Methods in " + classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
			//ArrayList<Method> methods = (Method) classes[i]
		}
		//System.out.println("program total: " + total);
		calcMap.put("total", total);
		printMap(calcMap);
		return calcMap;
	}
	
	private HashMap<String, Integer> numPublicMethods(JDTree[] classes){
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;

		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode();
			if(o instanceof Class) {
				Class classNode = (Class) o;
				ArrayList<Method> methodList = classNode.getMethods();
				for(Method method : methodList) {
					//System.out.println(method.toString() + ": modifiers: " + method.getModifiers().toString() + ", finalized: " + method.getFinalized());
					if(method.getModifiers().contains("public")) {
						count++;
					}
				}
				//System.out.println("Non-Final Private Protected Methods in " + classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
			//ArrayList<Method> methods = (Method) classes[i]
		}
		//System.out.println("program total: " + total);
		calcMap.put("total", total);
		printMap(calcMap);
		return calcMap;
	}
	
	private void printMap(HashMap<String, Integer> map) {
		for(String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key) + ", ");
		}
	}
}
