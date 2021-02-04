package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import ssmc.Class;
import ssmc.Method;
import tree.JDTree;

/**
* The MethodMetrics class contains all of the metrics that are related 
* to methods
*
* @author  Paul Hewson
*/
public class MethodMetrics {
	private HashMap<String, Integer> mapNonFinalPrivateProtected = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapClassified = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapPublic = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapTotal = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapMutatorInteractions = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapAccessorInteractions = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapMethodInvocations = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapWritesClassifiedAttributes = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapAccessClassifiedNeverCalled = new HashMap<String, Integer>();

	/**
	* The constructor currently calls each method in the class for testing purposes
	*/
	public MethodMetrics(JDTree[] classes) {
		calculateMethods(classes);
		printResults();
		//this.numNonFinalPrivateProtectedMethods(classes);
		//this.numClassifiedMethods(classes);
		//this.numPublicMethods(classes);
		//this.numMethods(classes);
		//this.numAccessorInteractions(classes);
		//this.numMutatorInteractions(classes);
	}

	private void calculateMethods(JDTree[] classes) {
		for(int i = 0; i < classes.length; i++) { 
			int nonFinalPrivateProtected = 0;
			int classifiedMethods = 0;
			int publicMethods = 0;
			int mutatorInteractions = 0;
			int accessorInteractions = 0;
			int methodInvocations = 0;
			int writesClassifiedAttributes = 0;
			int accessClassifiedNeverCalled = 0;
			
			Object o = classes[i].getNode();	
			if(o instanceof Class) {			
				Class classNode = (Class) o;	
				ArrayList<Method> methodList = classNode.getMethods(); 
				for(Method method : methodList) {	
					if(!method.getIdentifier().equals(classNode.getIdentifier())) { 
						nonFinalPrivateProtected += isNonFinalPrivateProtected(method);
						classifiedMethods += isClassified(method);
						publicMethods += isPublic(method);
						mutatorInteractions += countMutatorInteractions(method);
						accessorInteractions += countAccessorInteractions(method);
						methodInvocations += countMethodInvocations(method);
						writesClassifiedAttributes += writesClassifiedAttributes(method);
						accessClassifiedNeverCalled += accessClassifiedNeverCalled(method);
					}
				} 
				mapNonFinalPrivateProtected.put(classNode.getIdentifier(), nonFinalPrivateProtected);
				mapClassified.put(classNode.getIdentifier(), classifiedMethods);
				mapPublic.put(classNode.getIdentifier(), publicMethods);
				mapTotal.put(classNode.getIdentifier(), classifiedMethods + publicMethods);
				mapMutatorInteractions.put(classNode.getIdentifier(), mutatorInteractions);
				mapAccessorInteractions.put(classNode.getIdentifier(), accessorInteractions);
				mapMethodInvocations.put(classNode.getIdentifier(), methodInvocations);
				mapWritesClassifiedAttributes.put(classNode.getIdentifier(), writesClassifiedAttributes);
				mapAccessClassifiedNeverCalled.put(classNode.getIdentifier(), accessClassifiedNeverCalled);
			}
		}
	}

	private void printResults() {
		System.out.println("==========================================");
		System.out.println("Non-Final Private or Protected Methods");
		System.out.println("==========================================");
		printMap(mapNonFinalPrivateProtected);

		System.out.println("==========================================");
		System.out.println("Classified Methods");
		System.out.println("==========================================");
		printMap(mapClassified);

		System.out.println("==========================================");
		System.out.println("Public Methods");
		System.out.println("==========================================");
		printMap(mapPublic);

		System.out.println("==========================================");
		System.out.println("Total Methods");
		System.out.println("==========================================");
		printMap(mapTotal);

		System.out.println("==========================================");
		System.out.println("Interactions Between Mutators and Attributes");
		System.out.println("==========================================");
		printMap(mapMutatorInteractions);

		System.out.println("==========================================");
		System.out.println("Interactions Between Accessors and Attributes");
		System.out.println("==========================================");
		printMap(mapAccessorInteractions);
	}
	
	////////////////////////////////////////////////////////////////
	//calculator methods
	////////////////////////////////////////////////////////////////
	
	private int isNonFinalPrivateProtected(Method method) {
		if((method.getModifiers().contains("private") || method.getModifiers().contains("protected")) && !method.getFinalized()) {
			return 1;
		}
		return 0;
	}
	
	private int isClassified(Method method) {
		if(!method.getModifiers().contains("public")) {
			return 1; 
		}
		return 0;
	}
	
	private int isPublic(Method method) {
		if(method.getModifiers().contains("public")) {
			return 1; 
		}
		return 0;
	}
	
	private int countAccessorInteractions(Method method) {
		if(method.getAccessor()) {
			return 1; 
		}
		return 0;
	}

	private int countMutatorInteractions(Method method) {
		return method.getMutator(); 
	}

	private void printMap(HashMap<String, Integer> map) {
		for(String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key) + ", ");
		}
	}
	
	private int countMethodInvocations(Method method) {
		return method.getNumberOfInvocations();
	}
	
	private int writesClassifiedAttributes(Method method) {
		if(method.getWriteClassified()) {
			return 1;
		}
		return 0;
	}
	
	private int accessClassifiedNeverCalled(Method method) {
		if(method.getClassified() && method.getUsage() == 0) {
			//check if accesses classified attribute
		}
		return 0;
	}
	
	////////////////////////////////////////////////////////////////
	//GETTERS
	////////////////////////////////////////////////////////////////
	
	public HashMap<String, Integer> getMapNonFinalPrivateProtected() {
		return mapNonFinalPrivateProtected;
	}

	public HashMap<String, Integer> getMapClassified() {
		return mapClassified;
	}

	public HashMap<String, Integer> getMapPublic() {
		return mapPublic;
	}

	public HashMap<String, Integer> getMapTotal() {
		return mapTotal;
	}

	public HashMap<String, Integer> getMapMutatorInteractions() {
		return mapMutatorInteractions;
	}

	public HashMap<String, Integer> getMapAccessorInteractions() {
		return mapAccessorInteractions;
	}

	public HashMap<String, Integer> getMapMethodInvocations() {
		return mapMethodInvocations;
	}
	
	/**
	* Method numNonFinalPrivateProtectedMethods determines the number of 
	* public instance attributes in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	* @deprecated
	*/
	private HashMap<String, Integer> numNonFinalPrivateProtectedMethods(JDTree[] classes) {
		System.out.println("======================================================");
		System.out.println("Non-Final Private or Protected Methods");
		System.out.println("======================================================");
		HashMap<String, Integer> calcMap = new HashMap<String, Integer>();
		int total = 0;
		int count = 0;

		for(int i = 0; i < classes.length; i++) { //iterate over classes
			Object o = classes[i].getNode();	//get current class node as an object
			if(o instanceof Class) {			//ensure that object is of type class
				Class classNode = (Class) o;	//cast object to type class
				ArrayList<Method> methodList = classNode.getMethods(); //get methods for current class
				for(Method method : methodList) {	//iterate over methods in selected class
					if((method.getModifiers().contains("private") || method.getModifiers().contains("protected")) && !method.getFinalized()) {
						if(!method.getIdentifier().equals(classNode.getIdentifier())) { //exclude constructors
							count++; //increment counter only if method is private or protected and is not finalized and not the constructor
						}
					}
				}
				System.out.println(classNode.getIdentifier() + " : " + count); 
				calcMap.put(classNode.getIdentifier(), count); //add class, count pair to hashmap
				total += count;						//add count for class to program total
				count = 0;							//reset counter for next class
			}
		}
		System.out.println("program total: " + total);
		calcMap.put("total", total);			//add program total to hashmap
		//printMap(calcMap);
		return calcMap;
	}
	
	/**
	* Method numClassifiedMethods determines the number of 
	* classified (non-public) attributes in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	* @deprecated
	*/
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
					if(!method.getModifiers().contains("public")) {
						if(!method.getIdentifier().equals(classNode.getIdentifier())) {
							count++; 	//increment counter only if the method is not public and not a constructor
						}
					}
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
	
	/**
	* Method numPublicMethods determines the number of
	* public methods in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	* @deprecated
	*/
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
					if(method.getModifiers().contains("public")) {
						if(!method.getIdentifier().equals(classNode.getIdentifier())) {
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
	
	/**
	* Method numMethods determines the total number 
	* of methods in each class 
	* @param classes An array of JDTree nodes containing classes
	* @return HashMap<String, Integer> Returns the class name, total pair for each class
	* @deprecated
	*/
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
					if(!method.getIdentifier().equals(classNode.getIdentifier())) {
						count++; //increment counter only if method is not a constructor
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
	
	/**
	 * 
	 * @param classes
	 * @return
	 * @deprecated
	 */
	private HashMap<String, Integer> numAccessorInteractions(JDTree[] classes){
		System.out.println("======================================================");
		System.out.println("Interactions Between Accessors and Attributes");
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
					System.out.println(method.getIdentifier() + " is accessor ??  " + method.getAccessor());
					if(method.getAccessor()) {
						count++; //increment counter only if method is an accessor
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
	
	/**
	 * 
	 * @param classes
	 * @return
	 * @deprecated
	 */
	private HashMap<String, Integer> numMutatorInteractions(JDTree[] classes){
		System.out.println("======================================================");
		System.out.println("Interactions Between Mutators and Attributes");
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
					count += method.getMutator(); //increment counter only if method is an accessor
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
}
