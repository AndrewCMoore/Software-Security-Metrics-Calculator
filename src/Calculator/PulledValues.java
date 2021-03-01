package Calculator;

import java.util.ArrayList;
import java.util.HashMap;

import ssmc.Attribute;
import ssmc.Class;
import ssmc.Method;
import tree.JDTree;


public class PulledValues {
	private HashMap<String, Integer> mapNonFinalPrivateProtectedMethods = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapClassifiedMethods = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapPublicMethods = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapTotalMethods = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapMutatorInteractions = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapAccessorInteractions = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapMethodInvocations = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapWritesClassifiedAttributes = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapAccessClassifiedNeverCalled = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapPublicInstance = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapPublicClass = new HashMap<String, Integer>(); 
	private HashMap<String, Integer> mapPrivateProtectedInstance = new HashMap<String, Integer>(); 
	private HashMap<String, Integer> mapPrivateProtectedClass = new HashMap<String, Integer>(); 
	private HashMap<String, Integer> mapPrivateProtectedTotal = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapTotalAttributes = new HashMap<String, Integer>(); 
	private HashMap<String, Integer> mapCriticalElements = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapCriticalNotUsed = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapAttributeInteractions = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapClassifiedAttributeInteractions = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapClassifiedInstanceAttributeNotPrivate = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapClassifiedClassAttributeNotPrivate = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapClassifiedMethodsNotPrivate = new HashMap<String, Integer>();
	private HashMap<String, Double> mapStrictComplexity = new HashMap<String, Double>();
	private HashMap<String, Integer> mapCyclomaticComplexity = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapModifiedComplexity = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapMcCabesComplexity = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapCountPath = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapMethodInputs = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapMethodOutputs = new HashMap<String, Integer>();
	private HashMap<String, Integer> mapHenryKafura = new HashMap<String, Integer>();
	
	public PulledValues(JDTree[] classes) {
		calculate(classes);
		printResults();
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
			if(o instanceof Class) {			
				Class classNode = (Class) o;	
				ArrayList<Method> methodList = classNode.getMethods(); 
				ArrayList<Attribute> attributeList = classNode.getAttributes(); 
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
				mapNonFinalPrivateProtectedMethods.put(classNode.getIdentifier(), nonFinalPrivateProtected);
				mapClassifiedMethods.put(classNode.getIdentifier(), classifiedMethods);
				mapPublicMethods.put(classNode.getIdentifier(), publicMethods);
				mapTotalMethods.put(classNode.getIdentifier(), classifiedMethods + publicMethods);
				mapMutatorInteractions.put(classNode.getIdentifier(), mutatorInteractions);
				mapAccessorInteractions.put(classNode.getIdentifier(), accessorInteractions);
				mapMethodInvocations.put(classNode.getIdentifier(), methodInvocations);
				mapWritesClassifiedAttributes.put(classNode.getIdentifier(), writesClassifiedAttributes);
				mapAccessClassifiedNeverCalled.put(classNode.getIdentifier(), accessClassifiedNeverCalled);
				mapPublicInstance.put(classNode.getIdentifier(), publicInstance); 	
				mapPublicClass.put(classNode.getIdentifier(), publicClass);
				mapPrivateProtectedInstance.put(classNode.getIdentifier(), privateProtectedInstance);
				mapPrivateProtectedClass.put(classNode.getIdentifier(), privateProtectedClass);
				mapPrivateProtectedTotal.put(classNode.getIdentifier(), privateProtectedInstance + privateProtectedInstance);
				mapTotalAttributes.put(classNode.getIdentifier(), publicInstance + publicClass + privateProtectedInstance + privateProtectedClass);
				mapCriticalElements.put(classNode.getIdentifier(), criticalElements);
				mapCriticalNotUsed.put(classNode.getIdentifier(), criticalNotUsed);
				mapAttributeInteractions.put(classNode.getIdentifier(), attributeInteractions);
				mapClassifiedAttributeInteractions.put(classNode.getIdentifier(), classifiedAttributeInteractions);
				mapClassifiedInstanceAttributeNotPrivate.put(classNode.getIdentifier(), classifiedInstanceAttributeNotPrivate);
				mapClassifiedClassAttributeNotPrivate.put(classNode.getIdentifier(), classifiedClassAttributeNotPrivate);
				mapClassifiedMethodsNotPrivate.put(classNode.getIdentifier(), classifiedMethodsNotPrivate);
				mapStrictComplexity.put(classNode.getIdentifier(), (double) strictComplexity);
				mapCyclomaticComplexity.put(classNode.getIdentifier(), cyclomaticComplexity);
				mapModifiedComplexity.put(classNode.getIdentifier(), modifiedComplexity);
				mapMcCabesComplexity.put(classNode.getIdentifier(), mcCabesComplexity);
				mapCountPath.put(classNode.getIdentifier(), countPath);
				mapMethodInputs.put(classNode.getIdentifier(), methodInputs);
				mapMethodOutputs.put(classNode.getIdentifier(), methodOutputs);
				mapHenryKafura.put(classNode.getIdentifier(), henryKafura);
				
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
		if(!method.getModifiers().contains("public") && !method.getModifiers().contains("private")) {
			return 1;
		}
		return 0;
	}

	private int isCriticalInstanceNotPrivate(Attribute attribute) {
		if(!attribute.getModifier().contains("public") && !attribute.getModifier().contains("private") && !attribute.getModifier().contains("static")) {
			return 1;
		}
		return 0;
	}
	
	private int isCriticalClassNotPrivate(Attribute attribute) {
		if(!attribute.getModifier().contains("public") && !attribute.getModifier().contains("private") && attribute.getModifier().contains("static")) {
			return 1;
		}
		return 0;
	}

	private int countClassifiedInteractions(Attribute attribute) {
		if(!attribute.getModifier().contains("public")) {
			return attribute.getUsage();
		}
		return 0;
	}

	private int countInteractions(Attribute attribute) {
		return attribute.getUsage();
	}

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
	
	private int isPublicInstance(Attribute attribute) {
		if(attribute.getModifier().contains("public") && !attribute.getModifier().contains("static")) {
			return 1; 								
		}
		return 0;
	}
	
	private int isPublicClass(Attribute attribute) {
		if(attribute.getModifier().contains("static") && attribute.getModifier().contains("public")) {
			return 1;								
		}
		return 0;
	}
	
	private int isPrivateProtectedInstance(Attribute attribute) {
		if(!attribute.getModifier().contains("public") && !attribute.getModifier().contains("static")) { 
			return 1;											
		}
		return 0;
	}
	
	private int isPrivateProtectedClass(Attribute attribute) {
		if(!attribute.getModifier().contains("public") && attribute.getModifier().contains("static")) {
			return 1;			
		}
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
	
	public HashMap<String, Integer> getMapClassifiedMethodsNotPrivate() {
		return mapClassifiedMethodsNotPrivate;
	}
	
	public HashMap<String, Integer> getMapNonFinalPrivateProtectedMethods() {
		return mapNonFinalPrivateProtectedMethods;
	}

	public HashMap<String, Integer> getMapClassifiedMethods() {
		return mapClassifiedMethods;
	}

	public HashMap<String, Integer> getMapPublicMethods() {
		return mapPublicMethods;
	}

	public HashMap<String, Integer> getMapTotalMethods() {
		return mapTotalMethods;
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

	public HashMap<String, Integer> getMapWritesClassifiedAttributes() {
		return mapWritesClassifiedAttributes;
	}

	public HashMap<String, Integer> getMapAccessClassifiedNeverCalled() {
		return mapAccessClassifiedNeverCalled;
	}

	public HashMap<String, Integer> getMapPublicInstance() {
		return mapPublicInstance;
	}

	public HashMap<String, Integer> getMapPublicClass() {
		return mapPublicClass;
	}

	public HashMap<String, Integer> getMapPrivateProtectedInstance() {
		return mapPrivateProtectedInstance;
	}

	public HashMap<String, Integer> getMapPrivateProtectedClass() {
		return mapPrivateProtectedClass;
	}

	public HashMap<String, Integer> getMapPrivateProtectedTotal() {
		return mapPrivateProtectedTotal;
	}

	public HashMap<String, Integer> getMapTotalAttributes() {
		return mapTotalAttributes;
	}

	public HashMap<String, Integer> getMapCriticalElements() {
		return mapCriticalElements;
	}

	public HashMap<String, Integer> getMapCriticalNotUsed() {
		return mapCriticalNotUsed;
	}

	public HashMap<String, Integer> getMapAttributeInteractions() {
		return mapAttributeInteractions;
	}

	public HashMap<String, Integer> getMapClassifiedAttributeInteractions() {
		return mapClassifiedAttributeInteractions;
	}

	public HashMap<String, Integer> getMapClassifiedInstanceAttributeNotPrivate() {
		return mapClassifiedInstanceAttributeNotPrivate;
	}

	public HashMap<String, Integer> getMapClassifiedClassAttributeNotPrivate() {
		return mapClassifiedClassAttributeNotPrivate;
	}
	
	public HashMap<String, Double> getMapStrictComplexity() {
		return mapStrictComplexity;
	}

	public HashMap<String, Integer> getMapCyclomaticComplexity() {
		return mapCyclomaticComplexity;
	}

	public HashMap<String, Integer> getMapModifiedComplexity() {
		return mapModifiedComplexity;
	}

	public HashMap<String, Integer> getMapMcCabesComplexity() {
		return mapMcCabesComplexity;
	}
	
	public HashMap<String, Integer> getMapCountPath() {
		return mapCountPath;
	}

	public HashMap<String, Integer> getMapMethodInputs() {
		return mapMethodInputs;
	}

	public HashMap<String, Integer> getMapMethodOutputs() {
		return mapMethodOutputs;
	}

	public HashMap<String, Integer> getMapHenryKafura() {
		return mapHenryKafura;
	}

	private void printResults() {
	}
	
	private void printMap(HashMap<String, Integer> map) {
		for(String key : map.keySet()) {
			System.out.println(key + ": " + map.get(key) + ", ");
		}
	}
}
