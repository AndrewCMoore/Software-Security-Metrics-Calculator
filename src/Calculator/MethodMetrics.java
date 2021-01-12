package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import ssmc.Class;
import ssmc.Method;
import tree.JDTree;

public class MethodMetrics {

	public MethodMetrics(JDTree[] classes) {
		this.numNonFinalPrivateProtectedMethods(classes);
		this.numClassifiedMethods(classes);
		this.numPublicMethods(classes);
		this.numMethods(classes);
	}

	private HashMap<String, Integer> numNonFinalPrivateProtectedMethods(JDTree[] classes) {
		System.out.println("======================================================");
		System.out.println("Non-Final Private or Protected Methods");
		System.out.println("======================================================");
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
						if(method.getIdentifier().equals(classNode.getIdentifier())) {
							count++;
						}
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
			//ArrayList<Method> methods = (Method) classes[i]
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);
		//printMap(calcMap);
		return calcMap;
	}
	
	private HashMap<String, Integer> numClassifiedMethods(JDTree[] classes) {
		System.out.println("======================================================");
		System.out.println("Classified Methods");
		System.out.println("======================================================");
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
					if(!method.getModifiers().contains("public")) {
						if(method.getIdentifier().equals(classNode.getIdentifier())) {
							count++;
						}
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
			//ArrayList<Method> methods = (Method) classes[i]
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);
		//printMap(calcMap);
		return calcMap;
	}
	
	private HashMap<String, Integer> numPublicMethods(JDTree[] classes){
		System.out.println("======================================================");
		System.out.println("Public methods");
		System.out.println("======================================================");
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
						if(method.getIdentifier().equals(classNode.getIdentifier())) {
							count++;
						}
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
			//ArrayList<Method> methods = (Method) classes[i]
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);
		//printMap(calcMap);
		return calcMap;
	}
	
	private HashMap<String, Integer> numMethods(JDTree[] classes){
		System.out.println("======================================================");
		System.out.println("Total Methods");
		System.out.println("======================================================");
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;

		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode();
			if(o instanceof Class) {
				Class classNode = (Class) o;
				ArrayList<Method> methodList = classNode.getMethods();
				for(Method method : methodList) {
					if(method.getIdentifier().equals(classNode.getIdentifier())) {
						count++;
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
			//ArrayList<Method> methods = (Method) classes[i]
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);
		//printMap(calcMap);
		return calcMap;
	}
	
	private void printMap(HashMap<String, Integer> map) {
		for(String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key) + ", ");
		}
	}
}
