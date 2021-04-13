package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import ssmc.Attribute;
import ssmc.Class;
import ssmc.Method;
import tree.JDTree;


public class PulledValues {
	private HashMap<String, Double> mapNonFinalPrivateProtectedMethods = new HashMap<String, Double>();
	private HashMap<String, Double> mapClassifiedMethods = new HashMap<String, Double>();
	private HashMap<String, Double> mapPublicMethods = new HashMap<String, Double>();
	private HashMap<String, Double> mapTotalMethods = new HashMap<String, Double>();
	private HashMap<String, Double> mapMutatorInteractions = new HashMap<String, Double>();
	private HashMap<String, Double> mapAccessorInteractions = new HashMap<String, Double>();
	private HashMap<String, Double> mapMethodInvocations = new HashMap<String, Double>();
	private HashMap<String, Double> mapWritesClassifiedAttributes = new HashMap<String, Double>();
	private HashMap<String, Double> mapAccessClassifiedNeverCalled = new HashMap<String, Double>();
	private HashMap<String, Double> mapPublicInstance = new HashMap<String, Double>();
	private HashMap<String, Double> mapPublicClass = new HashMap<String, Double>();
	private HashMap<String, Double> mapPrivateProtectedInstance = new HashMap<String, Double>();
	private HashMap<String, Double> mapPrivateProtectedClass = new HashMap<String, Double>();
	private HashMap<String, Double> mapPrivateProtectedTotal = new HashMap<String, Double>();
	private HashMap<String, Double> mapTotalAttributes = new HashMap<String, Double>();
	private HashMap<String, Double> mapCriticalElements = new HashMap<String, Double>();
	private HashMap<String, Double> mapCriticalNotUsed = new HashMap<String, Double>();
	private HashMap<String, Double> mapAttributeInteractions = new HashMap<String, Double>();
	private HashMap<String, Double> mapClassifiedAttributeInteractions = new HashMap<String, Double>();
	private HashMap<String, Double> mapClassifiedInstanceAttributeNotPrivate = new HashMap<String, Double>();;
	private HashMap<String, Double> mapClassifiedClassAttributeNotPrivate = new HashMap<String, Double>();
	private HashMap<String, Double> mapClassifiedMethodsNotPrivate = new HashMap<String, Double>();
	private HashMap<String, Double> mapStrictComplexity = new HashMap<String, Double>();
	private HashMap<String, Double> mapCyclomaticComplexity = new HashMap<String, Double>();
	private HashMap<String, Double> mapModifiedComplexity = new HashMap<String, Double>();
	private HashMap<String, Double> mapMcCabesComplexity = new HashMap<String, Double>();
	private HashMap<String, Double> mapCountPath = new HashMap<String, Double>();
	private HashMap<String, Double> mapMethodInputs = new HashMap<String, Double>();
	private HashMap<String, Double> mapMethodOutputs = new HashMap<String, Double>();
	private HashMap<String, Double> mapHenryKafura = new HashMap<String, Double>();
	
	public PulledValues(JDTree[] classes) {
		calculate(classes);
		//printResults();
		System.out.println("AXX39: "+mapTotalAttributes);
		
			
	}

	private void calculate(JDTree[] classes) {
		for(int i = 0; i < classes.length; i++) { 
			int nonFinalPrivateProtected = 0;
			int classifiedMethods = 0;
			int publicMethods = 0;
			int mutatorInteractions = 0;
			int accessorInteractions = 0;
			int methodInvocations = 0;
			int writesClassifiedAttributes = 0;
			int accessClassifiedNeverCalled = 0;
			int publicInstance = 0;
			int publicClass = 0;
			int privateProtectedInstance = 0;
			int privateProtectedClass = 0;
			int criticalElements = 0;
			int criticalNotUsed = 0;
			int attributeInteractions = 0;
			int classifiedAttributeInteractions = 0;
			int classifiedInstanceAttributeNotPrivate = 0;
			int classifiedClassAttributeNotPrivate = 0;
			int classifiedMethodsNotPrivate = 0;
			int strictComplexity = 0;
			int cyclomaticComplexity = 0;
			int modifiedComplexity = 0;
			int mcCabesComplexity = 0;
			int countPath = 0;
			int methodInputs = 0;
			int methodOutputs = 0;
			int henryKafura = 0;
			
			Object o = classes[i].getNode();	
			System.out.println(o.getClass());
			if(o instanceof Class) {	
				System.out.println("This is true");
				Class classNode = (Class) o;
				ArrayList<Method> methodList = classNode.getMethods(); 
				ArrayList<Attribute> attributeList = classNode.getAttributes();
				System.out.println(classNode.getIdentifier());
				System.out.println(attributeList);

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
						classifiedMethodsNotPrivate += isClassifiedMethodNotPrivate(method);
						strictComplexity += strictComplexity(method);
						cyclomaticComplexity += cyclomaticComplexity(method);
						modifiedComplexity += modifiedComplexity(method);
						mcCabesComplexity += mcCabesComplexity(method, methodList.size());
						countPath += countPath(method);
						methodInputs += methodInputs(method);
						methodOutputs += methodOutputs(method);
						henryKafura += henryKafura(method);
					}
				} 
				for(Attribute attribute : attributeList) { 
					if(!classNode.isAttributeInMethod(attribute)) {
						//call methods to increment counters
						publicInstance += isPublicInstance(attribute);
						publicClass += isPublicClass(attribute);
						privateProtectedInstance += isPrivateProtectedInstance(attribute);
						privateProtectedClass += isPrivateProtectedClass(attribute);	
						criticalElements += isCriticalElement(attribute);
						criticalNotUsed += isCriticalNotUsed(attribute);
						attributeInteractions += countInteractions(attribute);
						classifiedAttributeInteractions += countClassifiedInteractions(attribute);
						classifiedInstanceAttributeNotPrivate += isCriticalInstanceNotPrivate(attribute);
						classifiedClassAttributeNotPrivate += isCriticalClassNotPrivate(attribute);
					}
				}
				mapNonFinalPrivateProtectedMethods.put(classNode.getIdentifier(), (double) nonFinalPrivateProtected);
				mapClassifiedMethods.put(classNode.getIdentifier(), (double) classifiedMethods);
				mapPublicMethods.put(classNode.getIdentifier(), (double) publicMethods);
				mapTotalMethods.put(classNode.getIdentifier(), (double) (classifiedMethods + publicMethods));
				mapMutatorInteractions.put(classNode.getIdentifier(), (double) mutatorInteractions);
				mapAccessorInteractions.put(classNode.getIdentifier(), (double) accessorInteractions);
				mapMethodInvocations.put(classNode.getIdentifier(), (double) methodInvocations);
				mapWritesClassifiedAttributes.put(classNode.getIdentifier(), (double) writesClassifiedAttributes);
				mapAccessClassifiedNeverCalled.put(classNode.getIdentifier(), (double) accessClassifiedNeverCalled);
				mapPublicInstance.put(classNode.getIdentifier(), (double) publicInstance); 	
				mapPublicClass.put(classNode.getIdentifier(), (double) publicClass);
				mapPrivateProtectedInstance.put(classNode.getIdentifier(), (double) privateProtectedInstance);
				mapPrivateProtectedClass.put(classNode.getIdentifier(), (double) privateProtectedClass);
				mapPrivateProtectedTotal.put(classNode.getIdentifier(), (double) (privateProtectedInstance + privateProtectedClass));
				mapTotalAttributes.put(classNode.getIdentifier(), (double) (publicInstance + publicClass + privateProtectedInstance + privateProtectedClass));
				mapCriticalElements.put(classNode.getIdentifier(), (double) criticalElements);
				mapCriticalNotUsed.put(classNode.getIdentifier(), (double) criticalNotUsed);
				mapAttributeInteractions.put(classNode.getIdentifier(), (double) attributeInteractions);
				mapClassifiedAttributeInteractions.put(classNode.getIdentifier(), (double) classifiedAttributeInteractions);
				mapClassifiedInstanceAttributeNotPrivate.put(classNode.getIdentifier(), (double) classifiedInstanceAttributeNotPrivate);
				mapClassifiedClassAttributeNotPrivate.put(classNode.getIdentifier(), (double) classifiedClassAttributeNotPrivate);
				mapClassifiedMethodsNotPrivate.put(classNode.getIdentifier(), (double) classifiedMethodsNotPrivate);
				mapStrictComplexity.put(classNode.getIdentifier(), (double) strictComplexity);
				mapCyclomaticComplexity.put(classNode.getIdentifier(), (double) cyclomaticComplexity);
				mapModifiedComplexity.put(classNode.getIdentifier(), (double) modifiedComplexity);
				mapMcCabesComplexity.put(classNode.getIdentifier(), (double) mcCabesComplexity);
				mapCountPath.put(classNode.getIdentifier(), (double) countPath);
				mapMethodInputs.put(classNode.getIdentifier(), (double) methodInputs);
				mapMethodOutputs.put(classNode.getIdentifier(), (double) methodOutputs);
				mapHenryKafura.put(classNode.getIdentifier(), (double) henryKafura);
				
			}
		}
	}

	private int henryKafura(Method method) {
		return method.getMethodLength() * (methodOutputs(method) * methodInputs(method)) * (methodOutputs(method) * methodInputs(method));
	}

	private int methodOutputs(Method method) {
		return method.getNumberOfOutputs();
	}

	private int methodInputs(Method method) {
		return method.getNumberOfInputs();
	}

	private int countPath(Method method) {
		return method.getMethodComplexity().get("countpath");
	}

	private int isClassifiedMethodNotPrivate(Method method) {
		try {
		if(!method.getModifiers().contains("public") && !method.getModifiers().contains("private")) {
			return 1;
		}
		}  catch (Exception e) {}	
		return 0;
	}

	private int isCriticalInstanceNotPrivate(Attribute attribute) {
		try {
		if(attribute.isCritical() && !attribute.getModifier().contains("private") && !attribute.getModifier().contains("static")) {
			return 1;
		}
		}  catch (Exception e) {}	
		return 0;
	}
	
	private int isCriticalClassNotPrivate(Attribute attribute) {
		try {
		if(attribute.isCritical() && !attribute.getModifier().contains("private") && attribute.getModifier().contains("static")) {
			return 1;
		}
		}  catch (Exception e) {}	
		return 0;
	}

	private int countClassifiedInteractions(Attribute attribute) {
		try {
		if(!attribute.getModifier().contains("public")) {
			return attribute.getUsage();
		}
		}  catch (Exception e) {}	
		return 0;
	}

	private int countInteractions(Attribute attribute) {
		return attribute.getUsage();
	}

	private int isNonFinalPrivateProtected(Method method) {
		try {
		if((method.getModifiers().contains("private") || method.getModifiers().contains("protected")) && !method.getFinalized()) {
			return 1;
		}
		}  catch (Exception e) {}	
		return 0;
	}
	
	private int isClassified(Method method) {
		try {
		if(!method.getModifiers().contains("public")) {
			return 1; 
		}
		}  catch (Exception e) {}	
		return 0;
	}
	
	private int isPublic(Method method) {
		try {
		if(method.getModifiers().contains("public")) {
			return 1; 
		}
		}  catch (Exception e) {}	
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
	
	private int countMethodInvocations(Method method) {
		return method.getNumberOfInvocations();
	}
	
	private int writesClassifiedAttributes(Method method) {
		if(method.getMutator() > 0) {
			return 1;
		}
		return 0;
	}
	
	private int accessClassifiedNeverCalled(Method method) {
		if(method.getAccessor() && method.getUsage() == 0) {
			return 1;
		}
		return 0;
	}
	
	private int isPublicInstance(Attribute attribute) {
		try {
		if(attribute.getModifier().contains("public") && !attribute.getModifier().contains("static")) {
			return 1; 								
		}
		}  catch (Exception e) {}	
		return 0;
		
	}
	
	private int isPublicClass(Attribute attribute) {
		try {
		if(attribute.getModifier().contains("static") && attribute.getModifier().contains("public")) {
			return 1;								
		}
		}  catch (Exception e) {}	
		return 0;
	}
	
	private int isPrivateProtectedInstance(Attribute attribute) {
		try {
		if(!attribute.getModifier().contains("public") && !attribute.getModifier().contains("static")) { 
			return 1;											
		}
		}  catch (Exception e) {}	
		return 0;
	}
	
	private int isPrivateProtectedClass(Attribute attribute) {
		try {
		if(!attribute.getModifier().contains("public") && attribute.getModifier().contains("static")) {
			return 1;			
		}
		}  catch (Exception e) {}	
		return 0;
	}
	
	private int isCriticalElement(Attribute attribute) {
		if(attribute.isCritical()) {
			return 1;
		}
		return 0;
	}
	
	private int isCriticalNotUsed(Attribute attribute) {
		if(attribute.isCritical()) {
			if(attribute.getUsage() == 0) {
				return 1;
			}
		}
		return 0;
	}
	
	private int strictComplexity(Method method) {
		return method.getMethodComplexity().get("strict");
	}
	
	private float mcCabesComplexity(Method method, int methodsInClass) {
		return method.getMethodComplexity().get("cyclomatic") / methodsInClass;
	}

	private int modifiedComplexity(Method method) {
		return method.getMethodComplexity().get("modified");
	}

	private int cyclomaticComplexity(Method method) {
		return method.getMethodComplexity().get("cyclomatic");
	}
	//////////////////////////////////////
	//Getters
	//////////////////////////////////////
	
	public HashMap<String, Double> getMapClassifiedMethodsNotPrivate() {
		return mapClassifiedMethodsNotPrivate;
	}
	
	public HashMap<String, Double> getMapNonFinalPrivateProtectedMethods() {
		return mapNonFinalPrivateProtectedMethods;
	}

	public HashMap<String, Double> getMapClassifiedMethods() {
		return mapClassifiedMethods;
	}

	public HashMap<String, Double> getMapPublicMethods() {
		return mapPublicMethods;
	}

	public HashMap<String, Double> getMapTotalMethods() {
		return mapTotalMethods;
	}

	public HashMap<String, Double> getMapMutatorInteractions() {
		return mapMutatorInteractions;
	}

	public HashMap<String, Double> getMapAccessorInteractions() {
		return mapAccessorInteractions;
	}

	public HashMap<String, Double> getMapMethodInvocations() {
		return mapMethodInvocations;
	}

	public HashMap<String, Double> getMapWritesClassifiedAttributes() {
		return mapWritesClassifiedAttributes;
	}

	public HashMap<String, Double> getMapAccessClassifiedNeverCalled() {
		return mapAccessClassifiedNeverCalled;
	}

	public HashMap<String, Double> getMapPublicInstance() {
		return mapPublicInstance;
	}

	public HashMap<String, Double> getMapPublicClass() {
		return mapPublicClass;
	}

	public HashMap<String, Double> getMapPrivateProtectedInstance() {
		return mapPrivateProtectedInstance;
	}

	public HashMap<String, Double> getMapPrivateProtectedClass() {
		return mapPrivateProtectedClass;
	}

	public HashMap<String, Double> getMapPrivateProtectedTotal() {
		return mapPrivateProtectedTotal;
	}

	public HashMap<String, Double> getMapTotalAttributes() {
		return mapTotalAttributes;
	}

	public HashMap<String, Double> getMapCriticalElements() {
		return mapCriticalElements;
	}

	public HashMap<String, Double> getMapCriticalNotUsed() {
		return mapCriticalNotUsed;
	}

	public HashMap<String, Double> getMapAttributeInteractions() {
		return mapAttributeInteractions;
	}

	public HashMap<String, Double> getMapClassifiedAttributeInteractions() {
		return mapClassifiedAttributeInteractions;
	}

	public HashMap<String, Double> getMapClassifiedInstanceAttributeNotPrivate() {
		return mapClassifiedInstanceAttributeNotPrivate;
	}

	public HashMap<String, Double> getMapClassifiedClassAttributeNotPrivate() {
		return mapClassifiedClassAttributeNotPrivate;
	}
	
	public HashMap<String, Double> getMapStrictComplexity() {
		return mapStrictComplexity;
	}

	public HashMap<String, Double> getMapCyclomaticComplexity() {
		return mapCyclomaticComplexity;
	}

	public HashMap<String, Double> getMapModifiedComplexity() {
		return mapModifiedComplexity;
	}

	public HashMap<String, Double> getMapMcCabesComplexity() {
		return mapMcCabesComplexity;
	}
	
	public HashMap<String, Double> getMapCountPath() {
		return mapCountPath;
	}

	public HashMap<String, Double> getMapMethodInputs() {
		return mapMethodInputs;
	}

	public HashMap<String, Double> getMapMethodOutputs() {
		return mapMethodOutputs;
	}

	public HashMap<String, Double> getMapHenryKafura() {
		return mapHenryKafura;
	}

	private void printResults() {
		System.out.println("mapNonFinalPrivateProtectedMethods :");
		testPrint(this.getMapNonFinalPrivateProtectedMethods());
		System.out.println("mapClassifiedMethods :");
		testPrint(this.getMapClassifiedMethods());
		System.out.println("mapPublicMethods :");
		testPrint(this.getMapPublicMethods());
		System.out.println("mapTotalMethods :");
		testPrint(this.getMapTotalMethods());
		System.out.println("mapMutatorInteractions :");
		testPrint(this.getMapMutatorInteractions());
		System.out.println("mapAccessorInteractions :");
		testPrint(this.getMapAccessorInteractions());
		System.out.println("mapMethodInvocations :");
		testPrint(this.getMapMethodInvocations());
		System.out.println("mapWritesClassifiedAttributes :"); 
		testPrint(this.getMapWritesClassifiedAttributes());
		System.out.println("mapAccessClassifiedNeverCalled :"); 
		testPrint(this.getMapAccessClassifiedNeverCalled());
		System.out.println("mapPublicInstance :");
		testPrint(this.getMapPublicInstance());
		System.out.println("mapPublicClass :");
		testPrint(this.getMapPublicClass());
		System.out.println("mapPrivateProtectedInstance :");
		testPrint(this.getMapPrivateProtectedInstance());
		System.out.println("mapPrivateProtectedClass :");
		testPrint(this.getMapPrivateProtectedClass());
		System.out.println("mapPrivateProtectedTotal :");
		testPrint(this.getMapPrivateProtectedTotal());
		System.out.println("mapTotalAttributes :");
		testPrint(this.getMapTotalAttributes());
		System.out.println("mapCriticalElements :");
		testPrint(this.getMapCriticalElements());
		System.out.println("mapCriticalNotUsed :"); 
		testPrint(this.getMapCriticalNotUsed());
		System.out.println("mapAttributeInteractions :");
		testPrint(this.getMapAttributeInteractions());
		System.out.println("mapClassifiedAttributeInteractions :");
		testPrint(this.getMapClassifiedAttributeInteractions());
		System.out.println("mapClassifiedInstanceAttributeNotPrivate :");
		testPrint(this.getMapClassifiedInstanceAttributeNotPrivate());
		System.out.println("mapClassifiedClassAttributeNotPrivate :"); 
		testPrint(this.getMapClassifiedClassAttributeNotPrivate());
		System.out.println("mapClassifiedMethodsNotPrivate :");
		testPrint(this.getMapClassifiedMethodsNotPrivate());
		System.out.println("mapStrictComplexity :");
		testPrint1(this.getMapStrictComplexity());
		System.out.println("mapCyclomaticComplexity :");
		testPrint(this.getMapCyclomaticComplexity());
		System.out.println("mapModifiedComplexity :");
		testPrint(this.getMapModifiedComplexity());
		System.out.println("mapMcCabesComplexity :");
		testPrint(this.getMapMcCabesComplexity());
		System.out.println("mapCountPath :");
		testPrint(this.getMapCountPath());
		System.out.println("mapMethodInputs :"); //0 :(
		testPrint(this.getMapMethodInputs());
		System.out.println("mapMethodOutputs :"); //0 :(
		testPrint(this.getMapMethodOutputs());
		System.out.println("mapHenryKafura :"); //0 :(
		testPrint(this.getMapHenryKafura());
	}
	
	private void testPrint(HashMap<String, Double> hashMap){
		System.out.println(hashMap.get("Components"));
		System.out.println(hashMap.get("FactoryObject_InheritanceLevel1"));
		System.out.println(hashMap.get("FactoryObject_InheritanceLevel2"));
		System.out.println(hashMap.get("FactoryObject"));
		System.out.println(hashMap.get("Inspectors"));
		System.out.println(hashMap.get("ObjectStates"));
		System.out.println(hashMap.get("Products"));
		System.out.println(hashMap.get("WorkStations")); 
		System.out.println("///////////");
		System.out.println(hashMap.get("pointlessClass"));
		System.out.println(hashMap.get("pointlessInterface"));
		System.out.println(hashMap.get("pointlessLoops"));
		System.out.println("///////////");
		System.out.println(hashMap.get("SimulateFactoryModel"));
		System.out.println(hashMap.get("AgentThread"));
		System.out.println(hashMap.get("ChefThread"));
		System.out.println(hashMap.get("Ingredients"));
		System.out.println(hashMap.get("criticalClassInheritance"));
		System.out.println("///////////");
		System.out.println(hashMap.get("GenerateWeibullDistributionData"));
		
	}
	
	private void testPrint1(HashMap<String, Double> hashMap){
		System.out.println(hashMap.get("Components"));
		System.out.println(hashMap.get("FactoryObject_InheritanceLevel1"));
		System.out.println(hashMap.get("FactoryObject_InheritanceLevel2"));
		System.out.println(hashMap.get("FactoryObject"));
		System.out.println(hashMap.get("Inspectors"));
		System.out.println(hashMap.get("ObjectStates"));
		System.out.println(hashMap.get("Products"));
		System.out.println(hashMap.get("WorkStations"));
		System.out.println(hashMap.get("pointlessClass"));
		System.out.println(hashMap.get("pointlessInterface"));
		System.out.println(hashMap.get("pointlessLoops"));
		System.out.println(hashMap.get("SimulateFactoryModel"));
		System.out.println(hashMap.get("AgentThread"));
		System.out.println(hashMap.get("ChefThread"));
		System.out.println(hashMap.get("Ingredients"));
		System.out.println(hashMap.get("criticalClassInheritance"));
		System.out.println(hashMap.get("GenerateWeibullDistributionData"));
		
	}
	
	private void printMap(HashMap<String, Double> map) {
		for(String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key) + ", ");
		}
	}
}
