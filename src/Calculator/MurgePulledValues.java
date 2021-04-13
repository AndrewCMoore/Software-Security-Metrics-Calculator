package Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import tree.JDTree;
import ssmc.*;
import ssmc.Class;

/**
*
* @author  Anthony MP
*/
public class MurgePulledValues {

	//my vars
	private Set<String> classNames = new HashSet <String>(); //used to contain  a set of all classNames in a project.
	Set<String> baseClassesNames = new HashSet <String>();; //used to contain a set of all classes that have a superclass.
	private ArrayList<String> topLevelSuperClassesInHierarchy = new ArrayList<String>(); //used to contain the highest level classes of a Hierarchy
	private ArrayList<String> topLevelCriticalSuperClassesInHierarchy = new ArrayList<String>(); // used to contain the highest level Critical classes of a heiarchy
	private HashMap<String,String> parentChildRelationships = new HashMap<String,String>();
	private ArrayList<String> criticalSerializableClasses = new ArrayList<String>();
	private ArrayList <String> nonFinalizedCriticalClass = new ArrayList<String>();
	private HashMap<String,Double> reflectionPackageClasses = new HashMap<String,Double>();
	private HashMap<String,HashMap<String,Double>> lengthOfEachMethodInEachClass = new HashMap<String,HashMap<String,Double>>();
	private HashMap<String,HashMap<String,Double>> complexityDepthInClassMethods = new HashMap<String,HashMap<String,Double>>();
	private HashMap<String,HashMap<String,Double>> loopSizeInClassMethods = new HashMap<String,HashMap<String,Double>>();
	private HashMap<String,Double> mapProtectedMethodsInClass = new  HashMap<String,Double>();
	private HashMap<String, Double> methodsInClass = new HashMap<String,Double>();
	private HashMap<String,HashMap<String,Double>> mapUniqueParamatersInClassEachMethod = new HashMap<String,HashMap<String,Double>>();
	private HashMap<String,Double> mapuniqueParametersInClassAllMethods = new HashMap<String,Double>();
	private Map<String, ArrayList<String>> mapImidiateChildren; //no = new req.
	private HashMap<String,Double> mapHierarchySize = new HashMap<String,Double>();
	private HashMap<String,Double> mapCriticalClassHierarchySize = new HashMap<String,Double>();
	
	private Set<String> mapCriticalClasses = new HashSet<String>();
	//private HashMap<String,Double> mapTotalCriticalClassInheritance = new HashMap<String,Double>(); 
	private Map<String,Double> mapCriticalClassHierarchy = new HashMap<String,Double>();
	private HashMap<String,Double> mapCriticalBaseClassesInProgram =new HashMap<String,Double>();
	private HashMap<String,Double> mapCriticalChildClassessInProgram =new HashMap<String,Double>();
	private Set<String> mapTotalCriticalClassInheritance = new HashSet<String>(); 	
	private HashMap<String,Double> sumOfAllInstanceMethodsInClass = new HashMap<String,Double>();
	private HashMap<String,HashMap<String,Double>>  mapParamatersInClassEachMethod = new HashMap<String,HashMap<String,Double>>();
	private HashMap<String,Double> mapTotalLinesInClass = new HashMap<String,Double>();
	private HashMap<String,Double> mapTotalCommentsInClass = new HashMap<String,Double>();
	private HashMap<String,Double> mapAccesibleMethods = new HashMap<String,Double>();
	private HashMap<String, Double> classesCoupledToBaseClass = new HashMap<String, Double>();
	private HashMap<String, Double> classCouplingRelationship = new HashMap<String, Double>();
	
	private HashMap<String,Double> mapbaseClassMethodsInheritedBySubClass = new HashMap<String,Double>();
	
	private HashMap<String,Double> unusedClassifiedMethods = new HashMap<String,Double>();
	private HashSet<String> criticalClassesInProgramsHeirchy = new HashSet<String>();
	
	//may replace #methods in class.
	private HashMap<String, HashSet<String>> methodNamesInClass = new HashMap<String, HashSet<String>>();
	
	private HashMap<String, HashSet<String>> validMethodNamesInClassThatCanBeInherited = new HashMap<String, HashSet<String>>();
	private HashMap<String, HashSet<String>> validAttributeNamesInClassThatCanBeInherited = new HashMap<String, HashSet<String>>();
	
	private HashMap<String, ArrayList<String>> totalNumberOfMethodsAccesible = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<String>> totalNumberOfAttributesAccesible = new HashMap<String, ArrayList<String>>();
	
	private LinkedHashSet<String> topToBottomClassHiarchy =  new  LinkedHashSet<String>();
	
	private HashMap<String,Double> numberOfClassesThatInheritFromEachCriticalSuperClass = new HashMap<String,Double>();
	private HashMap<String,Double> criticalSerializedClasses = new HashMap<String,Double>();
	private HashMap<String,Double> nonFinalizedCriticalClasses = new HashMap<String,Double>();
	private HashMap<String,Double> importBooleanReflectionClasses = new HashMap<String,Double>();
	private  HashMap<String, Double> numberOfProtectedMethodsInClass = new  HashMap<String,Double>();
	private  HashMap<String, Double> numberOfPrivateMethodsInClass = new  HashMap<String,Double>();
	
	private  HashMap<String,ArrayList<String>> numberOfClassesThatCanInheritFromEachSuperClass = new  HashMap<String,ArrayList<String>>(); 
	private HashMap<String,Double> depthOfInheritanceTreeAtCurrentSuperClass = new HashMap<String,Double>();
	private Set<String> criticalBaseClasses = new HashSet<String>();
	
	private HashMap<String,Double> sumaztionOfuniqueParametersInEachMethodForAClass = new  HashMap<String,Double>();
	private HashMap<String,Double> numberOfUniqueParametersInAClass = new HashMap<String,Double> ();
	
	private HashMap<String, Double> mapNumberOfUniqueAttributesTypesInClass = new HashMap<String, Double>();
	
	//refactor into setting all such PV with 0.0 and then replace new exploting hashmaps .put operation.
	private HashMap<String,Double> mapNumberOfMethodsInheritedByAClass = new HashMap<String,Double>();
	private HashMap<String,Double> mapTotalNumberOfMethodsInheritedInAClass = new HashMap<String,Double>();
	private HashMap<String,Double> mapNumberOfAttributesInheritedByAClass = new HashMap<String,Double>();
	private HashMap<String,Double> mapTotalNumberOfAttributesInheritedInAClass = new HashMap<String,Double>();
	
	
	
	Set<String>  isCriticalListInHyarchy = new HashSet<String>();	

	private HashMap <String,Double> mapNumberOfUniqueAttributesInMethodsInClass = new HashMap<String,Double>();
	
	
	
	/**
	* The constructor currently is smelly
	*/
	public MurgePulledValues(JDTree[] classes) {		
		
		
		this.setupDataExtraction(classes); // extract some key information before proceeding to the main extraction in extractInformation(classes);
		this.extractInheritanceInformation(classes); //main extraction method. Extracts almost all of the data.
		this.aquireTopLevelSuperClasses(classes);
		this.setUpImidiateSuperChildRelationship();
		//System.out.println("\n\n\n");
		this.getTotalNumberOfCriticalSubClasses();
		this.buildInheritanceDependencies(classes);
		//System.out.println(mapImidiateChildren);
		//System.out.println(sumaztionOfuniqueParametersInEachMethodForAClass);
		//System.out.println(mapUniqueParamatersInClassEachMethod);
		System.out.println("AXX78");
		System.out.println(getmapNumberOfUniqueAttributesInMethodsInClass());
		//System.out.println(getAllHierarchySize());

		//System.out.println(mapNumberOfMethodsInheritedByAClass);
		
		System.out.println("AXY22");
		correctHashMap(mapHierarchySize);
		correctHashMap(mapbaseClassMethodsInheritedBySubClass);
		correctHashMap(criticalSerializedClasses);
		correctHashMap(importBooleanReflectionClasses);
		correctHashMap(mapbaseClassMethodsInheritedBySubClass);
		correctHashMap(nonFinalizedCriticalClasses);
		correctHashMap(mapAccesibleMethods);
		correctHashMap(mapNumberOfAttributesInheritedByAClass);
		correctHashMap(mapbaseClassMethodsInheritedBySubClass);
		correctHashMap(nonFinalizedCriticalClasses);
		correctHashMap(numberOfClassesThatInheritFromEachCriticalSuperClass);
		correctHashMap(mapNumberOfMethodsInheritedByAClass);
		
		//correctHashMap(mapProtectedMethodsInClass);
		

		correctHashMap(mapHierarchySize);
		correctHashMap(mapHierarchySize);

		correctHashMap(classesCoupledToBaseClass);
		
		correctHashMap(depthOfInheritanceTreeAtCurrentSuperClass);

		correctHashMap(mapHierarchySize);
		correctHashMap(mapHierarchySize);
		correctHashMap(mapHierarchySize);
		correctHashMap(mapHierarchySize);
		correctHashMap(mapHierarchySize);
		correctHashMap(mapHierarchySize);
		correctHashMap(mapHierarchySize);
		correctHashMap(mapHierarchySize);
		
		System.out.println( "getmapNumberOfUniqueAttributesInMethodsInClass()" + " :" + getmapNumberOfUniqueAttributesInMethodsInClass() );
		System.out.println( "getAllHierarchySize()" + " :" + getAllHierarchySize() );
		System.out.println( "getBaseClassNames()" + " :" + getBaseClassNames() );
		System.out.println( "getClassCouplingRelationship()" + " :" + getClassCouplingRelationship() );
		System.out.println( "getClassesCoupledToBaseClass()" + " :" + getClassesCoupledToBaseClass() );
		System.out.println( "getClassMethodsInheritedBySubClassm()" + " :" + getClassMethodsInheritedBySubClassm() );
		System.out.println( "getComplexityDepthInClassMethods()" + " :" + getComplexityDepthInClassMethods() );
		System.out.println( "getCriticalSerializedClasses()" + " :" + getCriticalSerializedClasses() );
		System.out.println( "getDepthOfInheritanceTreeAtCurrentSuperClass()" + " :" + getDepthOfInheritanceTreeAtCurrentSuperClass() );
		//System.out.println( "getImidiateChildren()" + " :" + getImidiateChildren() );
		System.out.println( "getImportBooleanReflectionClasses()" + " :" + getImportBooleanReflectionClasses() );
		System.out.println( "getLngthOfEachMethodInEachClass()" + " :" + getLengthOfEachMethodInEachClass() );
		System.out.println( "getLoopSizeInClassMethods()" + " :" + getLoopSizeInClassMethods() );
		System.out.println( "getMethodNamesInClass()" + " :" + getMethodNamesInClass() );
		System.out.println( "getMethodsInClass()" + " :" + getMethodsInClass() );
		System.out.println( "getMethodsInheritedBySubClass()" + " :" + getMethodsInheritedBySubClass() );
		System.out.println( "getNonFinalizedCriticalClasses()" + " :" + getNonFinalizedCriticalClasses() );
		System.out.println( "getNumberOfAccesibleMethods()" + " :" + getNumberOfAccesibleMethods() );
		System.out.println( "getNumberOfAttributesInheritedByAClass()" + " :" + getNumberOfAttributesInheritedByAClass() );
		System.out.println( "getNumberOfBaseClasses()" + " :" + getNumberOfBaseClasses() );
		System.out.println( "getNumberOfBaseClassMethodsInheritedBySubClass()" + " :" + getNumberOfBaseClassMethodsInheritedBySubClass() );
		System.out.println( "getNumberOfClassesInProject()" + " :" + getNumberOfClassesInProject() );
		//loopSizeInClassMethods
		System.out.println( "getnumberOfClassesThatInheritFromEachCriticalSuperClass()" + " :" + getnumberOfClassesThatInheritFromEachCriticalSuperClass() );
		//System.out.println( "getNumberOfCriticalBaseClasses()" + " :" + getNumberOfCriticalBaseClasses() ); useless?
		System.out.println( "getNumberOfCriticalClassesInProgram()" + " :" + getNumberOfCriticalClassesInProgram() );
		System.out.println( "getNumberOfCriticalClassesInProgramHeirchy()" + " :" + getNumberOfCriticalClassesInProgramHeirchy() );
		System.out.println( "getNumberOfCriticalSuperClassesInHierarchy()" + " :" + getNumberOfCriticalSuperClassesInHierarchy() );
		System.out.println( "getNumberOfHierarchys()" + " :" + getNumberOfHierarchys() );
		System.out.println( "getNumberOfMethodsInheritedByAClass()" + " :" + getNumberOfMethodsInheritedByAClass() );
		System.out.println( "getNumberOfNonFinalizedCriticalClasses()" + " :" + getNumberOfNonFinalizedCriticalClasses() );
		System.out.println( "getNumberOfPrivateMethodsInClass()" + " :" + getNumberOfPrivateMethodsInClass() );
		System.out.println( "getNumberOfProtectedMethodsInClass()" + " :" + getNumberOfProtectedMethodsInClass() );
		System.out.println( "getNumberOfSerializableClassesInProject()" + " :" + getNumberOfSerializableClassesInProject() );
		System.out.println( "getNumberOfTopLevelCriticalSuperClasses()" + " :" + getNumberOfTopLevelCriticalSuperClasses() );
		System.out.println( "getNumberOfUniqueAttributesTypesInClass()" + " :" + getNumberOfUniqueAttributesTypesInClass() );
		System.out.println( "getNumberOfUniqueParametersInClassForAllMethods()" + " :" + getNumberOfUniqueParametersInClassForAllMethods() );
		System.out.println( "getParametersInEachMethodInEachClass()" + " :" + getParametersInEachMethodInEachClass() );
		System.out.println( "getProtectedMethodsInClass()" + " :" + getProtectedMethodsInClass() );
		System.out.println( "getReflectionPackageClass()" + " :" + getReflectionPackageClass() );
		System.out.println( "getSumOfAllInstanceMethodsInClass()" + " :" + getSumOfAllInstanceMethodsInClass() );
		System.out.println( "getSumOfClassesWhichMayInheritFromEachCriticalSuperClass" + " :" + getSumOfClassesWhichMayInheritFromEachCriticalSuperClass() );
		System.out.println( "getSumOfNumberOfClassesInheritingFromCriticalBaseClasses" + " :" + getSumOfNumberOfClassesInheritingFromCriticalBaseClasses() );
		System.out.println( "getTotalNumberOfAttributesInheritedInAClass()" + " :" + getTotalNumberOfAttributesInheritedInAClass() );
		System.out.println( "getTotalNumberOfCommentedLinesInEachClass()" + " :" + getTotalNumberOfCommentedLinesInEachClass() );
		System.out.println( "getTotalNumberOfCriticalSubClasses()" + " :" + getTotalNumberOfCriticalSubClasses() );
		System.out.println( "getTotalNumberOfLinesInEachClass()" + " :" + getTotalNumberOfLinesInEachClass() );
		System.out.println( "getTotalNumberOfMethodsAccesible()" + " :" + getTotalNumberOfMethodsAccesible() );
		System.out.println( "getTotalNumberOfMethodsInheritedInAClass" + " :" + getTotalNumberOfMethodsInheritedInAClass() );
		System.out.println( "getUniqueParamatersInEachMethodInEachClass()" + " :" + getUniqueParamatersInEachMethodInEachClass() );
		System.out.println( "getUnusedClassifiedMethods()" + " :" + getUnusedClassifiedMethods() );

		
		
		
		
	}
	
	
	//###########################################################################################################################################################	
	//Sub Extractors Methods 
	//###########################################################################################################################################################

	
	/**
	 * This is required to calculate inheritance PV in the  extractInheritanceInformation method.
	 */	
	public void setupDataExtraction(JDTree[] classes) {
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 				
			if(o instanceof Class) {
				Class classNode = (Class) o;
				classNames.add(classNode.getIdentifier());
				if (classNode.getInterfaces().contains("Runnable")) classNode.setCritical();
				if (classNode.isCritical()) {
					mapCriticalClasses.add(classNode.getIdentifier());
					if (classNode.getInterfaces().contains("Serializable")) criticalSerializedClasses.put(classNode.getIdentifier(), (double) 1); 
				}
				
			}
		}
	}	
	
	/**
	 * This is required to calculate inheritance PV in the  extractInheritanceInformation method but must be called after it.
	 */	
	public void aquireTopLevelSuperClasses(JDTree[] classes) {
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 			
			if(o instanceof Class) {
				Class classNode = (Class) o;
				//if the classNode we are examining is a BaseClass AND the BaseClass has no superClass above it
				if (baseClassesNames.contains(classNode.getIdentifier()) && (!(classNames.contains(classNode.getSuperClass())))) { 
					topLevelSuperClassesInHierarchy.add(classNode.getIdentifier());
					if (classNode.isCritical()) topLevelCriticalSuperClassesInHierarchy.add(classNode.getIdentifier());	//sepret List for Critical SuperClasses
				}
			}
		}
	}
	//#methods inherited by a class.
	//Baseclasses must first be discovered before this can be calculated
	//used to set # classes coupled to base classes PV 
	//used to get number of methods that can be inherited and total # in class including inherited
	//used to set #nonfinalizedCriticalClasses.
	public void buildInheritanceDependencies(JDTree[] classes) {
		

		HashSet<String> numberOfCoupledBaseClasses;
		HashSet<String> numberOfCoupledClasses;
		
		HashSet<String> classDeclarationLine;

		//System.out.println("\n\n\n\n");
		for(int i = 0; i < classes.length; i++) {
			Object o = classes[i].getNode(); 			
			if(o instanceof Class) {
				Class classNode = (Class) o;
				
				//set nonfinalizedCriticalClasses
				
				if (mapCriticalClasses.contains(classNode.getIdentifier())) {
					try {
						if (!(classNode.getModifier().contains("final"))) nonFinalizedCriticalClasses.put(classNode.getIdentifier(), (double)1);
					}catch (Exception e) {}
				}
				
				
				
				//if current class is base class, get Attributes.
				numberOfCoupledBaseClasses =  new HashSet<String>();
				numberOfCoupledClasses  =  new HashSet<String>();
				classDeclarationLine = new HashSet<String>();
				
				
				if (baseClassesNames.contains(classNode.getIdentifier())) {
					ArrayList<Attribute> attributeList = classNode.getAttributes(); //get current class attributes.
					try {
					//for(Attribute attribute : attributeList) { 
						//for each class in the project check if className CONTAINS attribute type. Note that Queue<Workstations> is still coupled to workstations. thus check if contains.
						for (String className: classNames) {
							//System.out.println(classNode.getIdentifier()+"::"+attribute.getType()+"::"+attribute.getType());

							if ((classNode.getCompilationUnit().toString().contains(className)) && !(className.equals(classNode.getIdentifier()))) numberOfCoupledBaseClasses.add(className);
					//}

					}}catch (Exception e) {}
					classesCoupledToBaseClass.put(classNode.getIdentifier(), (double) numberOfCoupledBaseClasses.size());
				}
				for (String className: classNames) {
					if  ((classNode.getCompilationUnit().toString().contains(className)) && !(className.equals(classNode.getIdentifier())))  numberOfCoupledClasses.add(className);
				}
				classCouplingRelationship.put(classNode.getIdentifier(), (double)numberOfCoupledClasses.size());
				
			}
			
		}
		
				//System.out.println(topToBottomClassHiarchy);
			//	System.out.println("topToBottomClassHiarchy");
			//	System.out.println("classesCoupledToBaseClass");
				//System.out.println(classesCoupledToBaseClass);
				
				//methodNamesInClass
				//for each superclass
				for (String key: topToBottomClassHiarchy) {
					//check if the key is the name of a class
					if (classNames.contains(key)) {
						try {
						ArrayList<String> childrenClasses = mapImidiateChildren.get(key);
						//for each childClass
						for (String childClass: childrenClasses) {
							HashSet<String> SuperClassSet = validMethodNamesInClassThatCanBeInherited.get(key);
							HashSet<String> ChildClassSet = validMethodNamesInClassThatCanBeInherited.get(childClass);
							HashSet<String> SuperAttributeSet = validAttributeNamesInClassThatCanBeInherited.get(key);
							HashSet<String> ChildAttributeSet = validAttributeNamesInClassThatCanBeInherited.get(childClass);
							
							//used to solve method not visable in subclass problem
							HashSet<String> aggricationSet = SuperClassSet;
							aggricationSet.addAll(ChildClassSet);
							System.out.println(aggricationSet);
							ArrayList<String> test = new ArrayList<String>();
							test.addAll(aggricationSet);
							System.out.println(test.size());
							mapNumberOfMethodsInheritedByAClass.put(childClass, test.size()*1.0);
							
							aggricationSet = new HashSet<String>();
							aggricationSet.addAll(test);
							
							SuperClassSet.removeAll(ChildClassSet);
							System.out.println("The ChildClass: "+childClass+"\nHas Inherited these methods: "+(SuperClassSet)+"\nTotaling: "+SuperClassSet.size());
							mapbaseClassMethodsInheritedBySubClass.put(childClass,(double) SuperClassSet.size());
							mapTotalNumberOfMethodsInheritedInAClass.put(childClass, (double)SuperClassSet.size());
							//mapbaseClassMethodsInheritedBySubClass.put(childClass, SuperClassSet) since you need to work with the superclass set at some point.
							
							validMethodNamesInClassThatCanBeInherited.remove(childClass);
							//computation using subclass is done. replace old subclass methodList with a new stringset containing all inherited methods.
							System.out.println(validMethodNamesInClassThatCanBeInherited.put(childClass, aggricationSet));
							System.out.println("In Class X new # methods are:"+ childClass+"::"+aggricationSet.size());
							totalNumberOfMethodsAccesible.put(childClass, test);
							validMethodNamesInClassThatCanBeInherited.put(childClass, aggricationSet);
							
							//refactor into setting all such PV with 0.0 and then replace new exploting hashmaps .put operation.
	
							
							
							
							//Prob Fine.
							//used to solve method not visable in subclass problem
							HashSet<String> aggricationSetAttributes = SuperAttributeSet;
							aggricationSetAttributes.addAll(ChildAttributeSet);
							//System.out.println(aggricationSet);
							ArrayList<String> testAttributes = new ArrayList<String>();
							testAttributes.addAll(aggricationSetAttributes);
							//System.out.println(test.size());
							mapNumberOfAttributesInheritedByAClass.put(childClass, testAttributes.size()*1.0);
							
							aggricationSetAttributes = new HashSet<String>();
							aggricationSetAttributes.addAll(testAttributes);
							
							SuperAttributeSet.removeAll(ChildAttributeSet);
							System.out.println("The ChildClass: "+childClass+"\nHas Inherited these attributes: "+(SuperAttributeSet)+"\nTotaling: "+SuperAttributeSet.size());
							mapTotalNumberOfAttributesInheritedInAClass.put(childClass, SuperClassSet.size()*1.0);
							//mapbaseClassMethodsInheritedBySubClass.put(childClass, SuperClassSet) since you need to work with the superclass set at some point.
							
							validAttributeNamesInClassThatCanBeInherited.remove(childClass);
							//computation using subclass is done. replace old subclass methodList with a new stringset containing all inherited methods.
							System.out.println(validAttributeNamesInClassThatCanBeInherited.put(childClass, aggricationSetAttributes));
							System.out.println("In Class X new # attributes are:"+ childClass+"::"+aggricationSetAttributes.size());
							totalNumberOfAttributesAccesible.put(childClass, testAttributes);
							validAttributeNamesInClassThatCanBeInherited.put(childClass, aggricationSetAttributes);
							
							
						}} catch (Exception e) {}
						
					}
			
		}
		//System.out.println("mapbaseClassMethodsInheritedBySubClass");
		//System.out.println(mapbaseClassMethodsInheritedBySubClass);
		//System.out.println("totalNumberOfMethodsAccesible");
		//System.out.println(totalNumberOfMethodsAccesible);
		/*for(String key: totalNumberOfMethodsAccesible.keySet()) {
			System.out.println("ClassName and #: "+key+"::"+totalNumberOfMethodsAccesible.get(key).size());
		}*/
		
	}
	
	
	//###########################################################################################################################################################	
	//Mutator Methods 
	//###########################################################################################################################################################

	//	
	public void buildInheritanceRelationships(Class classNode) {
		if (classNames.contains(classNode.getSuperClass()))  { 
			baseClassesNames.add(classNode.getSuperClass()); //populate a hashSet of superclass names, (can't include duplicates)
			parentChildRelationships.put(classNode.getIdentifier(),classNode.getSuperClass()); //get hashMet with the superclass of each child class.
		}
	}
	
	public void isCriticalSerializableClass (Class classNode) {
		if (classNode.isCritical()) {
			if (classNode.getInterfaces().contains("Serializable")) 
				criticalSerializableClasses.add(classNode.getIdentifier());			
		}		
	}
	
	public void isNonFinalizedCriticalClass (Class classNode) {
		if (classNode.isCritical()) {
			try {
			if (!(classNode.getModifier().contains("final"))) nonFinalizedCriticalClass.add(classNode.getIdentifier());			
		}  catch (Exception e) {}	
		}
	}
	
	public void isReflectionPackageClass(Class classNode) {
		reflectionPackageClasses.put(classNode.getIdentifier(),classNode.getCompilationUnit().toString().contains("java.lang.reflect") ? (double)1:(double)0);
	}
	
	
	public void recordMethodLength (Class classNode) {
		
		ArrayList<Method> methodList = classNode.getMethods();
		HashMap<String,Double> methodLength = new HashMap <String,Double>();
		HashMap<Integer,Integer> startEndPoints = new HashMap <Integer,Integer>();
		HashMap <String,HashMap<Integer,Integer>> methodStartEndPoints = new HashMap <String,HashMap<Integer,Integer>>();
		for(Method method : methodList) {	
			try {
			///if the method is not a constructor, main, run, or abstract, then count it.
			if (!(method.getIdentifier().equals(classNode.getIdentifier()) ||(method.getIdentifier().equals("main")) || (method.getIdentifier().equals("run") || method.getModifiers().contains("abstract")))) {	
				//int counter = (method.getEndLine()-method.getStartLine()) >1 ? ((method.getEndLine()-method.getStartLine())-1):1; //lengthofmethod ;
				//System.out.println("In Class:" +classNode.getIdentifier()+" Method:"+method.getIdentifier()+":length is:"+(counter)); //lengthofmethod
				methodLength.put(method.getIdentifier(), (method.getEndLine()-method.getStartLine()) >1 ? (double)((method.getEndLine()-method.getStartLine())-1):(double)1);
			
				//method.getCompilationUnit().
				
				//Class/method/start-end
				startEndPoints.put(method.getStartLine(),method.getEndLine());

							
			}	
			}  catch (Exception e) {}	
		}
		System.out.println("AXER");
		System.out.println(classNode.getCompilationUnit().toString());
		ArrayList<Attribute> attributeList = classNode.getAttributes();
		String classCU = classNode.getCompilationUnit().toString();
		String lines[] = classCU.split("\\n");
		int classSize = lines.length;
		int lineCounter=0;
		int totalUniqueAttributesInMethods=0;
		HashSet<String> mapUniqueAttributesInMethods;
		int methodCounter=0;
		
		HashMap <String,Integer> numberOfMethodUses = new HashMap<String,Integer>();
		
		
		for (Method  method:methodList) {
			methodCounter=0;
			for (String line: lines) {	
				if (line.contains(method.getIdentifier())) methodCounter++;
				
			}	
			numberOfMethodUses.put(method.getIdentifier(), methodCounter);
		}
		
		methodCounter=0;
		for (String key : numberOfMethodUses.keySet()) {
			if (numberOfMethodUses.get(key)==1) methodCounter++;
		}
		unusedClassifiedMethods.put(classNode.getIdentifier(),(double) methodCounter);
		
		//EC CALCULATION
		//For each line in a class
		for (String line: lines) {
			mapUniqueAttributesInMethods = new HashSet<String>();
			//for each method's start end point
			for (int sp: startEndPoints.keySet()) {			
				int startPoint=sp;
				int endPoint=startEndPoints.get(sp);
				//if the current line is in a method, check if a class or instence attribute is here and
				//add it to a HashSet since attributes must be unique
				if (lineCounter>=startPoint && lineCounter<=endPoint) {
					for (Attribute attribute: attributeList) {
						if (line.contains(attribute.getIdentifier())) mapUniqueAttributesInMethods.add(attribute.getIdentifier());
					}					
				}					
			}
			lineCounter++;		
			totalUniqueAttributesInMethods+=mapUniqueAttributesInMethods.size();
		}

		
		mapNumberOfUniqueAttributesInMethodsInClass.put(classNode.getIdentifier(), (double) totalUniqueAttributesInMethods);
		
		lengthOfEachMethodInEachClass.put(classNode.getIdentifier(), methodLength);
	}

	
	
	/**
	 * This method does two things at once.
	 * 1) It gets the DepthOfCompexity PV (how deep a loop goes)
	 * 2) it records the number of lines in a loop in the method.
	 * @param classNode
	 */
	public void buildComplexityPV (Class classNode) {
		
		ArrayList<Method> methodList = classNode.getMethods();
		HashMap<String,Double> complexityDepthInMethods = new HashMap<String,Double>();
		HashMap<String,Double> loopSizeInMethods = new HashMap<String,Double>();
		//complexityDepthInClassMethods,loopSizeInClassMethods

		
		for(Method method : methodList) {	
			int methodLoopComplexity=0;
			ArrayList<Statement> statmentList = method.getStatements(); //#lines in loop
			
			int totalStatmentSizePerMethod=0,LastStartLine=0,LastEndLine=0; //#lines in loop
			for (Statement s :statmentList) { //#lines in loop
				
				if (( s.getNode().getNodeType() == 19) ||  (s.getNode().getNodeType() == 24) ||   s.getNode().getNodeType()==61 ||   s.getNode().getNodeType()==70 ) { //#lines in loop
					if (s.getStartLine()<LastEndLine || (LastEndLine==0 && LastStartLine ==0)) methodLoopComplexity++; ////totalmethodLoopCompletiy
					LastStartLine=s.getStartLine();//#lines in loop
					LastEndLine = s.getEndLine();	//#lines in loop
					totalStatmentSizePerMethod+= ((LastEndLine-LastStartLine)-1); //#lines in loop
				}
			}
			
			complexityDepthInMethods.put(method.getIdentifier(),(double)methodLoopComplexity); 
			loopSizeInMethods.put(method.getIdentifier(),(double) totalStatmentSizePerMethod );
			//if (methodLoopComplexity!=0) System.out.println("In Class::Method: "+classNode.getIdentifier() +"::"+method.getIdentifier()+"\nMethodLoopComplexity:"+methodLoopComplexity); //totalmethodLoopCompletiy
			
			
			//if (totalStatmentSizePerMethod!=0) {System.out.println("In Class::Method: "+classNode.getIdentifier() +"::"+method.getIdentifier()+"\nSumOfAllLoopSizes:"+totalStatmentSizePerMethod); //#lines in loop
			//totalLinesInLoop+=totalStatmentSizePerMethod; //#lines in loop
		
		}
		
		complexityDepthInClassMethods.put(classNode.getIdentifier(), complexityDepthInMethods );
		loopSizeInClassMethods.put(classNode.getIdentifier(), loopSizeInMethods );
		
	}
	
	//AMP 
	
	// log the number of methods that can be inherited are protected in superclasses only (Base Classes)
	/*public void setnumberOfProtectedMethodsThatCanBeInherited(Class classNode) {
		if (baseClassesNames.contains(classNode.getIdentifier())) {
			//totalNumberOfPrivateandPritectedmethods+= (method.getModifiers().contains("protected")) ? 1:0; //protected methods that CAN be inherited.
			mapProtectedMethodsInClass.put(classNode.getIdentifier(), value)
		}
		
	}*/
	
	/**Due to the unfortunatle fact that JDT tree can only get a .
	 * superclasses, I'm reversing the direction to get all childs of a BaseClass. 
	 * (can also be solved via a tree structure)
	 */
	public void setUpImidiateSuperChildRelationship() {
		mapImidiateChildren = new HashMap<>(
		parentChildRelationships.entrySet().stream()
	        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
	        .collect(Collectors.toMap(
	                item -> item.get(0).getValue(),
	                item -> new ArrayList<>(
	                    item.stream()
	                        .map(Map.Entry::getKey)
	                        .collect(Collectors.toList())
	                ))
	        ));		
		determineDepthOfInheritance();
		//System.out.println("\n\n\n\n\n");
		setUpCriticalClassInformation();
		
	}
	
	//might need to be renamed if multivalued 
	//
	//does top level is 0!!! thus for example: depth is 3 instead of 4. if this is false, solution is "int DepthOfInheriitanceCounter=1; //to include the TopSuperClass level.
	public void determineDepthOfInheritance() {
		//start point of depth of inheritance tree
		ArrayList<String> childrenClasses = new ArrayList<String>();
		ArrayList<String> nextChildrenClasses = new ArrayList<String>();				
		int DepthOfInheriitanceCounter=0;
		
		
		for (String topBaseClass :topLevelSuperClassesInHierarchy) { //for each topSuperClass
			try {
			childrenClasses=mapImidiateChildren.get(topBaseClass); //aquire current children of topBaseClass
			topToBottomClassHiarchy.add(topBaseClass);
			depthOfInheritanceTreeAtCurrentSuperClass.put(topBaseClass, (double) DepthOfInheriitanceCounter);

			while (!(childrenClasses.isEmpty())) {//while atleast one key-value pair exists
				
			//	System.out.println("Current Children Classes: " +childrenClasses); //should be level1+workstations.
				for (int i=0;i<childrenClasses.size();i++) {
					//System.out.println("I want to add: " +mapImidiateChildren.get(childrenClasses.get(i)));
					
					topToBottomClassHiarchy.addAll(childrenClasses);
					nextChildrenClasses.addAll(mapImidiateChildren.get(childrenClasses.get(i)));
					DepthOfInheriitanceCounter+=1;
					
					
					
					depthOfInheritanceTreeAtCurrentSuperClass.put(childrenClasses.get(i), (double) (DepthOfInheriitanceCounter));
					for (String nextChildClass:nextChildrenClasses) {
						depthOfInheritanceTreeAtCurrentSuperClass.put(nextChildClass, (double) ((DepthOfInheriitanceCounter+1)));
					}
					
					
					
					
					
					
					//System.out.println("next children classes Inner: "+nextChildrenClasses);
				}
				//System.out.println("next children classes: "+nextChildrenClasses);
				childrenClasses=nextChildrenClasses;
				nextChildrenClasses=  new ArrayList<String>();
				
			
			}
			} catch (Exception e) {}
			
			
		
			mapHierarchySize.put(topBaseClass, (double)(DepthOfInheriitanceCounter+1)); //+1 due to null condition at the last children. This can be solved via a do while, do this and make if better 
			//System.out.println("In :" +topBaseClass +":DepthOnInhIs:"+ (DepthOfInheriitanceCounter+1));
			DepthOfInheriitanceCounter=0;
			
		} //Endpoint of depth of inheritance tree		
	}
	
	
	public void setUpCriticalClassInformation() {
		ArrayList<String> listOfCriticalClassNames = new ArrayList<String>();
		//Set<String>  isCriticalListInHyarchy = new HashSet<String>();		
		ArrayList<String> childrenClasses = new ArrayList<String>();
		ArrayList<String> nextChildrenClasses = new ArrayList<String>();		
		 Set<String> criticalClasses = new HashSet<String>(); 			
		 int DepthOfInheriitanceCounter=0;
			
			for (String topLevelCriticalSuperClass :topLevelCriticalSuperClassesInHierarchy) { //for each topSuperClass
				depthOfInheritanceTreeAtCurrentSuperClass.put(topLevelCriticalSuperClass, (double) DepthOfInheriitanceCounter);
				childrenClasses=mapImidiateChildren.get(topLevelCriticalSuperClass);
				mapTotalCriticalClassInheritance.addAll(mapImidiateChildren.get(topLevelCriticalSuperClass)); //add direct  childclasses.
				mapCriticalClassHierarchy.put(topLevelCriticalSuperClass, (double)mapImidiateChildren.get(topLevelCriticalSuperClass).size());				
				numberOfClassesThatInheritFromEachCriticalSuperClass.put(topLevelCriticalSuperClass, (double)childrenClasses.size());
				numberOfClassesThatCanInheritFromEachSuperClass.put(topLevelCriticalSuperClass, childrenClasses);
				
				criticalBaseClasses.add(topLevelCriticalSuperClass);
				//mapCriticalChildClassessInProgram
				while (!(childrenClasses.isEmpty())) {//while atleast one key-value pair exists
					
					
					
					//System.out.println("Current Children Classes: " +childrenClasses); //should be level1+workstations.
					for (int i=0;i<childrenClasses.size();i++) {
						//System.out.println("I want to add: " +mapImidiateChildren.get(childrenClasses.get(i)));
						
						
						
						try {
							criticalBaseClasses.addAll(childrenClasses);
							nextChildrenClasses.addAll(mapImidiateChildren.get(childrenClasses.get(i)));
							DepthOfInheriitanceCounter+=1;
							
							
							
							depthOfInheritanceTreeAtCurrentSuperClass.put(childrenClasses.get(i), (double) (DepthOfInheriitanceCounter));
							for (String nextChildClass:nextChildrenClasses) {
								depthOfInheritanceTreeAtCurrentSuperClass.put(nextChildClass, (double) ((DepthOfInheriitanceCounter+1)));
							}
							
							
							
							} catch (Exception e) {}
						
						
						
						
						try {
							
							criticalBaseClasses.add(childrenClasses.get(i));
							
							/**THIS IS VERY CRITICAL PLEASE COMMENT ON WHY THIS IS DONE AND WHY IT WORKS
							 *  //since .isCritical() ONLY WORKS  if the cclassNode..super is thread or interface 
							 *  includes serilizable,  the .isCritical boolean for SUBCLASSES IS FALSE 
							 *   tin here we tranverse all CriticalBaseClassHarchys and all all children
							 *    classes to tmapCriticalClasses to make sure they are included!!!!!!!
							 */
							for (String cn :classNames) {							
								if (childrenClasses.contains(cn)) mapCriticalClasses.add(childrenClasses.get(i)); 	
								
							}
							

							
						nextChildrenClasses.addAll(mapImidiateChildren.get(childrenClasses.get(i)));
						numberOfClassesThatInheritFromEachCriticalSuperClass.put(childrenClasses.get(i), (double)mapImidiateChildren.get(childrenClasses.get(i)).size());
						
						//mapCriticalClassHierarchy.put(mapImidiateChildren.get(childrenClasses.get(i))),nextChildrenClasses.size());
						mapTotalCriticalClassInheritance.addAll(nextChildrenClasses);
						criticalClasses.addAll(childrenClasses);
						
						
						//# children from each SuperClass, anthony your problem solving is insane.
						numberOfClassesThatCanInheritFromEachSuperClass.put(childrenClasses.get(i), nextChildrenClasses);
						for (String key: numberOfClassesThatCanInheritFromEachSuperClass.keySet()) {
							// if the currentClass has a superclass
							if (numberOfClassesThatCanInheritFromEachSuperClass.get(key).contains(childrenClasses.get(i)))  {
								ArrayList<String> addSubClassesToCurrentSuperClass = new ArrayList<String>();
								addSubClassesToCurrentSuperClass.addAll((numberOfClassesThatCanInheritFromEachSuperClass.get(key)));
								//if (nextChildrenClasses!=null) 
								addSubClassesToCurrentSuperClass.addAll(nextChildrenClasses);
								numberOfClassesThatCanInheritFromEachSuperClass.put(key,addSubClassesToCurrentSuperClass );							
							}							
						}
						
						
						} catch (Exception e) {}
						
						//used to get a list of all sub critical classes used to calculate the #total critical classes.
						
			


						//System.out.println("next children classes Inner: "+nextChildrenClasses);
					}
					
					
					//System.out.println("next children classes: "+nextChildrenClasses);
					childrenClasses=nextChildrenClasses;
					nextChildrenClasses=  new ArrayList<String>();

				
				}
				
				mapCriticalBaseClassesInProgram.put(topLevelCriticalSuperClass,(double)criticalClasses.size());
				//mapCriticalChildClassessInProgram.put(topLevelCriticalSuperClass,criticalClasses.size()-1);
				//System.out.println("totalNumberOfCriticalSUPERClassesInProgramIs: "+(criticalClasses.size()));
				
				//System.out.println("TRUEtotalNumberOfCriticalClassesInProgramIs: "+(mapCriticalClasses.size()+1)+mapCriticalClasses);
				isCriticalListInHyarchy=mapCriticalClasses;
				for (String baseClass :baseClassesNames) {
					if (!(mapCriticalClasses.contains(baseClass))) isCriticalListInHyarchy.remove(baseClass);
					
				}
				
				DepthOfInheriitanceCounter=0;
				//System.out.println("True Total Number of Critical Classes In Hyarchy: "+isCriticalListInHyarchy.size());
				//mapTotalCriticalClassInheritance.put(topLevelCriticalSuperClass, isCriticalListInHyarchy.size());
				//System.out.println("True Total Number of Possible Inheritance From a TopLevelCritical Classes In Hyarchy: "+(isCriticalListInHyarchy.size()-1)); //# possible inheritances from all critical classes
				//mapCriticalClassHierarchy=mapImidiateChildren;
				
				//for loop to set up critical class Hierarchy
				//System.out.println("Critical Class Hierarchy: \n" + mapCriticalClassHierarchy.keySet());
				
			} //Endpoint of depth of NumberOfCriticalSuperClasses		
			//printMap(mapCriticalClassHierarchy);
	}

	
	
	//###########################################################################################################################################################	
	//main Extractor Method -make this and all upper private
	//###########################################################################################################################################################

	
	/**
	*extract information from the JDT tree for the Code Artifact.
	*@return
	**/
	public void extractInheritanceInformation(JDTree[] classes) {
		
		int totalNumberOfPrivateandPritectedmethods=0;	//do better. Decide if to spam attributes here or in the mutators 
		int totalNumberOfMethodsPerClass=0;
		int numberOfUniqueMethodParameters=0;
		int numberOfUniqueClassParameters=0;
		int numberOfInstanceMethods=0;
		int numberOfAccesibleMethods=0;
		HashMap<String,Double> parametersInMethod = new HashMap<String,Double>();
		HashSet<String> uniqueParametersInMethod = new HashSet<String>();
		HashMap<String,Double> UniqueparametersPerMethod = new HashMap<String,Double>();
		HashSet<String> UniqueParamaterTypesInClass = new HashSet <String>();
		ArrayList<String> paramaterTypesInClass = new ArrayList<String>();
		HashSet<String> methodNames = new HashSet<String>();
		HashSet<String> validMethodNamesThatCanBeInherited = new HashSet<String>();
		HashSet<String> validAttributesNamesThatCanBeInherited = new HashSet<String>();
		
		ArrayList<String> protectedMethods;
		ArrayList<String> privateMethods;

		for(int i = 0; i < classes.length; i++) {
			totalNumberOfMethodsPerClass=0; //reset counter
			totalNumberOfPrivateandPritectedmethods=0; // reset counter - will fix soon
			numberOfUniqueClassParameters=0;
			numberOfInstanceMethods=0;
			UniqueParamaterTypesInClass = new HashSet <String>();
			paramaterTypesInClass = new ArrayList<String>();
			numberOfAccesibleMethods=0;
			methodNames = new HashSet<String>();
			validMethodNamesThatCanBeInherited = new HashSet<String>();
			validAttributesNamesThatCanBeInherited = new HashSet<String>();
			protectedMethods = new ArrayList<String>();
			privateMethods = new ArrayList<String>();
			int uniqueParametersInEachMethodCounter;
			HashSet<String> classAttrubuteTypes;
			
			HashSet<String> uniqueParametersInEachMethod;
			
			Object o = classes[i].getNode();				
			if(o instanceof Class) {
				Class classNode = (Class) o;
				
				ArrayList<Method> methodList = classNode.getMethods();	//get ArrayList of Methods in the class;	
				ArrayList<Attribute> attributeList = classNode.getAttributes(); //get arraylist of attributes in class;
				
				if (classNode.getCompilationUnit().toString().contains("java.lang.reflect")) importBooleanReflectionClasses.put(classNode.getIdentifier(),(double)1);
				
				
				buildInheritanceRelationships(classNode); //store information about all super and sub classes.
				isCriticalSerializableClass(classNode);
				isNonFinalizedCriticalClass(classNode);
				isReflectionPackageClass(classNode);
				buildComplexityPV(classNode);
				recordMethodLength(classNode);
				
				
				uniqueParametersInEachMethodCounter=0;
				
				
				classAttrubuteTypes = new HashSet<String>();
				for(Attribute a : attributeList) {
					try {
					classAttrubuteTypes.add(a.getType());
						if (a.getModifier().contains("public")) validAttributesNamesThatCanBeInherited.add(a.getIdentifier());
					}catch(Exception e) {}
					
				}
				validAttributeNamesInClassThatCanBeInherited.put(classNode.getIdentifier(),validAttributesNamesThatCanBeInherited);
				mapNumberOfUniqueAttributesTypesInClass.put(classNode.getIdentifier(), (double)classAttrubuteTypes.size()-1);
				
				
				for(Method method : methodList) {
					methodNames.add(method.getIdentifier());
					try {
					if (! ((method.getModifiers().contains("abstract")) || (method.getModifiers().contains("static")) || (method.getModifiers().contains("final")))) numberOfInstanceMethods++; //will be moved soon.
					
					//#methods inherited PV
					if (!(method.getIdentifier().equals(classNode.getIdentifier()) ||(method.getIdentifier().equals("main")) || (method.getIdentifier().equals("run") || method.getModifiers().contains("abstract")))) {
						validMethodNamesThatCanBeInherited.add(method.getIdentifier()); 
					}
					
					if ((method.getModifiers().contains("protected"))) protectedMethods.add(method.getIdentifier());
					if ((method.getModifiers().contains("private"))) privateMethods.add(method.getIdentifier());
					
					if (method.getModifiers().contains("public")) numberOfAccesibleMethods++;
					parametersInMethod = new HashMap<String,Double>(); //improve
					uniqueParametersInEachMethod = new HashSet <String>();
					if (!(method.getIdentifier().equals(classNode.getIdentifier()))) uniqueParametersInEachMethod.addAll(method.getOnlyParameterTypes());
					uniqueParametersInEachMethodCounter+=uniqueParametersInEachMethod.size();
					parametersInMethod.put(method.getIdentifier(), (double)method.getOnlyParameterTypes().size());
					UniqueparametersPerMethod.put(method.getIdentifier(),(double) uniqueParametersInMethod.size());
					UniqueParamaterTypesInClass.addAll(method.getOnlyParameterTypes());
					paramaterTypesInClass.addAll(method.getOnlyParameterTypes());
					
					
					totalNumberOfMethodsPerClass+=1;
					ArrayList<Statement> statmentList = method.getStatements();
					//totalNumberOfPrivateandPritectedmethods+= (method.getModifiers().contains("protected")) ? 1:0; //protected methods that CAN be inherited.
					}  catch (Exception e) {}	
				}
				sumaztionOfuniqueParametersInEachMethodForAClass.put(classNode.getIdentifier(),(double)uniqueParametersInEachMethodCounter);
				
				//dependend on that attribute, type thing.
				//numberOfUniqueParametersInAClass.put(classNode.getIdentifier(),uniqueParametersInClass)
				
				numberOfProtectedMethodsInClass.put(classNode.getIdentifier(),protectedMethods.size()*1.0);
				numberOfPrivateMethodsInClass.put(classNode.getIdentifier(),privateMethods.size()*1.0);
				validMethodNamesInClassThatCanBeInherited.put(classNode.getIdentifier(),validMethodNamesThatCanBeInherited);
				methodNamesInClass.put(classNode.getIdentifier(), methodNames);
				mapAccesibleMethods.put(classNode.getIdentifier(),(double)numberOfAccesibleMethods);
				mapTotalLinesInClass.put(classNode.getIdentifier(), (double)classNode.getTotalNumberOfLines());
				mapTotalCommentsInClass.put(classNode.getIdentifier(), (double)classNode.getTotalNumberOfCommentedLines());
				mapParamatersInClassEachMethod.put(classNode.getIdentifier(), parametersInMethod);
				mapUniqueParamatersInClassEachMethod.put(classNode.getIdentifier(), parametersInMethod);
				mapuniqueParametersInClassAllMethods.put(classNode.getIdentifier(), (double)UniqueParamaterTypesInClass.size());
				methodsInClass.put(classNode.getIdentifier(), (double) totalNumberOfMethodsPerClass);
				mapProtectedMethodsInClass.put(classNode.getIdentifier(),(double) totalNumberOfPrivateandPritectedmethods); //
				sumOfAllInstanceMethodsInClass.put(classNode.getIdentifier(), (double)numberOfInstanceMethods);
				
				classNode.getInterfaces();
				
			}	
			//System.out.println("validMethodNamesThatCanBeInherited");
			//System.out.println(validMethodNamesThatCanBeInherited);
		}
	}
	
	
	
	//###########################################################################################################################################################	
	//Accesors Extractors Methods 
	//###########################################################################################################################################################

	//Let PV = pulled value
	
	// #classes PV
	public Set<String> getNumberOfClassesInProject() {
		return classNames;
	}
	
	// #Serializable classes PV
	public int getNumberOfSerializableClassesInProject() {
		return criticalSerializableClasses.size();		
	}
	
	// #Non-Finalized Critical classes PV
	public int getNumberOfNonFinalizedCriticalClasses() {
		return nonFinalizedCriticalClass.size();		
	}
	
	//#if a class uses "java.lang.reflect" , the class has a value of 1, else 0. PV
	public  HashMap<String,Double> getReflectionPackageClass() {
		return this.reflectionPackageClasses;
	}
	
	//#BaseClass PV (how many classes can act as superclasses)
	public int getNumberOfBaseClasses() {
		return baseClassesNames.size();
	}
	
	public Set<String> getBaseClassNames() {
		return baseClassesNames;
	}
	
	public HashMap<String, Double> getmapNumberOfUniqueAttributesInMethodsInClass() {
		return mapNumberOfUniqueAttributesInMethodsInClass;
	}
	
	//#ComplexityDepth of each method in a class PV
	public HashMap<String, HashMap<String,Double>> getComplexityDepthInClassMethods() {
		return this.complexityDepthInClassMethods;
	}
	
	//#ComplexityDepth of each method in a class PV
	public HashMap<String, HashMap<String,Double>> getLoopSizeInClassMethods() {
		return this.loopSizeInClassMethods;
	}
	
	//#protected methods that can be inherited from a BaseClass PV
	public HashMap<String,Double> getProtectedMethodsInClass() {
		return this.mapProtectedMethodsInClass;
	}
	
	//#methods for each class PV //may be murged to <String>, Arraylist<String>
	public HashMap<String, Double> getMethodsInClass() {
		return this.methodsInClass;
	}
	
	//#numberOfHierarchyInProject PV
	public int getNumberOfHierarchys() {
		return topLevelSuperClassesInHierarchy.size();
	}
	
	//#number Of CriticalClassHierarchys In Project PV
	public int getNumberOfCriticalSuperClassesInHierarchy() {
		return topLevelCriticalSuperClassesInHierarchy.size();
	}
	//a=sum of # of distinct parameter types of each method in class PV
	public HashMap<String, HashMap<String,Double>> getUniqueParamatersInEachMethodInEachClass() {
		return this.mapUniqueParamatersInClassEachMethod;
	}
	
	//# number of parameters in each method per class PV
	public HashMap<String, HashMap<String,Double>> getParametersInEachMethodInEachClass() {
		return this.mapParamatersInClassEachMethod;
	}
	
	//l=#distinct parameter types (in class) for all methods
	public HashMap<String,Double> getNumberOfUniqueParametersInClassForAllMethods() {
		return this.mapuniqueParametersInClassAllMethods;
	}
	
	//# immidiateChildren for each BaseClass (SuperClass) PV
	public Map<String, ArrayList<String>> getImidiateChildren() {
		return this.mapImidiateChildren;
	}
	
	/**
	 * High Value Map 
	 * Get size of all program Hierarchy size PV  (max depth  of inheritance)
	 * # confirms program Hierarchys (number of Hierarchys) PV
	 * Can be further used to get depth of inheritance for each Hierarchy
	 * @return
	 */
	public HashMap<String,Double> getAllHierarchySize() {
		return this.mapHierarchySize;
	}
	
	/*public HashMap<String,Double> getPossibleNumberofCriticalClassInheritance() {
		return this.mapCriticalClassHierarchySize;
	}*/
	
	
	//get the length of method PV
	public HashMap<String, HashMap<String,Double>> getLengthOfEachMethodInEachClass() {
		return this.lengthOfEachMethodInEachClass;
	}
	
	//# critical superclasses PV
	public HashMap<String,Double> getNumberOfCriticalBaseClasses() {
		return this.mapCriticalBaseClassesInProgram;
	}
	
	//return total# of criticalClasses (including all children of critical classes) in this program.
	public int getNumberOfCriticalClassesInProgram() {
		return mapCriticalClasses.size();
	}
	
	//possible inheritances from all critical classes PV
	public int getTotalNumberOfCriticalSubClasses() {
		//System.out.println(mapTotalCriticalClassInheritance.size());
		return mapTotalCriticalClassInheritance.size();
		
	}
	
	

	//M( C)= SUM of all  INSTANCE methods (non static / final methods, abstract methods) PV
	public HashMap<String,Double> getSumOfAllInstanceMethodsInClass() {
		return this.sumOfAllInstanceMethodsInClass;
	}
	
	//this did not work, i need to redo it.
	//E( C) = SET (no duplicates)  # pairs of (v,m) for each instance var v used by method m PV
	public HashMap<String,Double> EC() {
		return null;
	}

	//total # of lines PV
	public HashMap<String,Double> getTotalNumberOfLinesInEachClass() {
		return this.mapTotalLinesInClass;
	}
	
	//# commented lines PV
	public HashMap<String,Double> getTotalNumberOfCommentedLinesInEachClass( ) {
		return this.mapTotalCommentsInClass;
	}
	
	//total # of methods accessible (class level) PV
	public HashMap<String,Double> getNumberOfAccesibleMethods() {
		return this.mapAccesibleMethods;
	}
	
	// # classes coupled to base classes PV 
	public HashMap<String, Double> getClassesCoupledToBaseClass() {
		return this.classesCoupledToBaseClass;
	}
	
	public HashMap<String,Double> getNumberOfBaseClassMethodsInheritedBySubClass() {
		return this.mapbaseClassMethodsInheritedBySubClass;
	}
	
	public HashMap<String, HashSet<String>> getMethodNamesInClass() {
		return methodNamesInClass;
	}
	
	public HashMap<String,Double> getClassMethodsInheritedBySubClassm() {
		return mapbaseClassMethodsInheritedBySubClass;
	}
	
	
	public HashMap<String, ArrayList<String>> getTotalNumberOfMethodsAccesible() {
		return totalNumberOfMethodsAccesible;
	}
	
	public HashMap<String,Double> getMethodsInheritedBySubClass() {
		return mapbaseClassMethodsInheritedBySubClass;
	}
	
	public HashMap<String,Double> getnumberOfClassesThatInheritFromEachCriticalSuperClass() {
		return numberOfClassesThatInheritFromEachCriticalSuperClass;
	}
	
	public HashMap<String,Double> getNonFinalizedCriticalClasses() {
		return nonFinalizedCriticalClasses;
	}
	
	public HashMap<String,Double> getCriticalSerializedClasses() {
		return criticalSerializedClasses;
	}
	
	public HashMap<String,Double> getImportBooleanReflectionClasses() {
		return importBooleanReflectionClasses;
	}
	
	public HashMap<String, Double> getNumberOfProtectedMethodsInClass() {
		return numberOfProtectedMethodsInClass;
	}
	
	public HashMap<String, Double> getNumberOfPrivateMethodsInClass() {
		return numberOfPrivateMethodsInClass;
	}
	
	public Double getSumOfClassesWhichMayInheritFromEachCriticalSuperClass() {
		return (double) (isCriticalListInHyarchy.size());
	}
	
	public Set<String> getCriticalBaseClasses() {
		return criticalBaseClasses;
	}
	
	public Set<String> getNumberOfCriticalClassesInProgramHeirchy(){
		return isCriticalListInHyarchy;
	}
	
	
	public HashMap<String, Double> getDepthOfInheritanceTreeAtCurrentSuperClass() {
		return depthOfInheritanceTreeAtCurrentSuperClass;
	}
	
	//Number Of Unique Attributes Per Class
	public HashMap<String, Double> getNumberOfUniqueAttributesTypesInClass() {
		return mapNumberOfUniqueAttributesTypesInClass;
	}
	
	
	public HashMap<String, Double> getNumberOfMethodsInheritedByAClass() {
		return mapNumberOfMethodsInheritedByAClass;
	}
	public HashMap<String, Double> getTotalNumberOfMethodsInheritedInAClass() {
		return mapTotalNumberOfMethodsInheritedInAClass;
	}
	public HashMap<String, Double> getNumberOfAttributesInheritedByAClass() {
		return mapNumberOfAttributesInheritedByAClass;
	}
	public HashMap<String, Double> getTotalNumberOfAttributesInheritedInAClass() {
		return mapTotalNumberOfAttributesInheritedInAClass;
	}

	public Set<String> getCriticalClasses() {
		return mapCriticalClasses;
	}
	
	
	public HashMap<String, Double>  getUnusedClassifiedMethods() {
		return unusedClassifiedMethods;
		
	}
	
	public int getNumberOfTopLevelCriticalSuperClasses() {
		return topLevelCriticalSuperClassesInHierarchy.size();
	}
	
	
	
	public int getSumOfNumberOfClassesInheritingFromCriticalBaseClasses() {
		int sum=0;
		for (String c :numberOfClassesThatCanInheritFromEachSuperClass.keySet()) {
			sum+=numberOfClassesThatCanInheritFromEachSuperClass.get(c).size();
		}
		return sum;
	}
	
	public HashMap<String, Double> getClassCouplingRelationship() {
		return classCouplingRelationship;
	}
	

	
	//###########################################################################################################################################################	
	//Correct HashMap
	//###########################################################################################################################################################

	private void correctHashMap(HashMap<String, Double> input) {
		
		for (String className: getNumberOfClassesInProject()) {			
			if (!(input.containsKey(className)))  input.put(className, 0.0);			
		}
	}
	
	
}
