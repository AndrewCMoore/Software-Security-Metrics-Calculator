package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import ssmc.Method;
import ssmc.Statement;
import ssmc.Class;
import tree.JDTree;

public class StatementMetrics {
	
	public StatementMetrics(JDTree[] classes) {
		this.methodComplexity(classes);
	}
	
	private HashMap<String, Integer> methodComplexity(JDTree[] classes) {
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int count = 0;
		int total = 0;
		
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode();
			if(o instanceof Class) {
				Class classNode = (Class) o;
				ArrayList<Method> methodList = classNode.getMethods();
				for(Method method : methodList) {
					//HashMap<String, Integer> statements = method.getNumOfStatements();
					System.out.println(classNode.getIdentifier());
					System.out.println(method.getIdentifier() + ": " + method.getMethodComplexity());
					//printMap(statements);
					
				}
			}
		}
		
		return calcMap;
	}
	
	private void printMap(HashMap<String, Integer> map) {
		for(String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key) + ", ");
		}
	}
}
