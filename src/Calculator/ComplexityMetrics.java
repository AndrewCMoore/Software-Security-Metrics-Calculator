package calculator;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jdt.core.dom.Statement;

import ssmc.Method;
import ssmc.StatementVisitor;
import ssmc.Class;
import tree.JDTree;

public class ComplexityMetrics {
	
	public ComplexityMetrics(JDTree[] classes) {
		this.methodComplexity(classes);
	}
	//shouldn't count try, only catch
	//check for interface
	//add check for abstract class
	private HashMap<String, Integer> methodComplexity(JDTree[] classes) {
		System.out.println("======================================================");
		System.out.println("Method Complexity");
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
					//System.out.println(method.getIdentifier() + " complexity: " + method.getMethodComplexity());
					System.out.println("     " + method.getIdentifier() + " : " + method.getMethodComplexity());
												
					count += method.getMethodComplexity();
				}
				System.out.println(classNode.getIdentifier() + " : " + count);
				calcMap.put(classNode.getIdentifier(), count);
				total += count;
				count = 0;
			}
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
