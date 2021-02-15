package Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrimaryMetrics {

	private int sourceLinesOfCode=0; //Project Level Metric
	private HashMap<String, Float> commentRatio = new HashMap<String, Float>(); //class level metric
	private HashMap<String, HashMap<String, Integer>> nestingComplexity = new HashMap<String, HashMap<String, Integer>>(); //method level metric (max loop complexity of each method)
	private HashMap<String, Integer> numberOfChildren = new HashMap<String, Integer>(); //class level metric
	private HashMap<String, Integer> numberOfMethods = new HashMap<String, Integer>(); //classlevelmetric
	private int numberOfHierarchies=0;  //project level
	private int countOfBaseClasses=0; //project level
	private int numberofClasses=0; //Project level
	private HashMap<String,Float> lackOfCohesionOfMethods = new HashMap<String,Float>();
	private HashMap<String,Float> cohesionAmongMethodsInClass = new HashMap<String,Float>();
	private HashMap<String,Float> measureOfFunctionalAbtraction = new HashMap<String,Float>();
	private HashMap<String, HashMap<String, Integer>> lengthOfMethod = new HashMap<String, HashMap<String, Integer>>();
	private int stallRatio;
	private HashMap<String,Float> couplingCorruptionPropagation = new HashMap<String,Float>();
	private HashMap<String,Float> couplingBetweenObjects = new HashMap<String,Float>();
	private HashMap<String, Double> failSafeDefaults = new HashMap<String, Double>();
	private HashMap<String, Double> reduceAttackSurface = new HashMap<String, Double>();
	private int economyOfMechanism = 0;
	private HashMap<String, Integer> strictCyclomaticComplexity = new HashMap<String, Integer>();
	
	public PrimaryMetrics(PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		averageNumberOfAncestor(pv, sm, mpv);
		failSafeDefaults(pv, sm, mpv);
		reduceAttackSurface(pv, sm, mpv);
		//lackOfCohesionOfMethods(pv, sm, mpv);
		//cohesionAmongMethodsInClass(pv, sm, mpv);
		commentRatio(pv, sm, mpv);
		countBaseClasses(pv, sm, mpv);
		cyclomaticComplexity(pv, sm, mpv);
		depthOfInheritanceTree(pv, sm, mpv);
		countPath(pv, sm, mpv);
		economyOfMechanism(pv, sm, mpv);
		essentialCyclomaticComplexity(pv, sm, mpv);
		mcCabesCyclomaticComplexity(pv, sm, mpv);
		modifiedCyclomaticComplexity(pv, sm, mpv);
		nestingComplexity(pv, sm, mpv);
		numberOfChildren(pv, sm, mpv);
		numberOfMethods(pv, sm, mpv);
		strictCyclomaticComplexity(pv, sm, mpv);
		secureWeakestLink(pv, sm, mpv);
		weightedMethodsPerClass(pv, sm, mpv);
		SourceLinesOfCode(pv, sm, mpv);
		measureOfAggregation(pv, sm, mpv);
		countOfBaseClasses(pv, sm, mpv);
		couplingBetweenObjects(pv, sm, mpv);
		couplingCorruptionPropagation(pv, sm, mpv);
		depthOfInheritace(pv, sm, mpv);
		directClassCoupling(pv, sm, mpv);
		fanIn(pv, sm, mpv);
		fanOut(pv, sm, mpv);
		henryKafura(pv, sm, mpv);
		responceSetForaClass(pv, sm, mpv);
		designSizeInClasses(pv, sm, mpv);
		stallRatio(pv, sm, mpv);
		criticalElementRatio(pv, sm, mpv);
		dataAccessMetric(pv, sm, mpv);
		grantLeastPrivilege(pv, sm, mpv);
		isolation(pv, sm, mpv);
		leastCommomMechanism(pv, sm, mpv);
		numberOfHierarchies(pv, sm, mpv);
		measureOfFunctionalAbtraction(pv, sm, mpv);
		classInterfaceSize(pv, sm, mpv);
		numberOfPolymorphicMethods(pv, sm, mpv);
	}
	
	
	//###########################################################################################################################################################	
	//Abstraction Metrics
	//###########################################################################################################################################################

	public void averageNumberOfAncestor(PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void failSafeDefaults (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: sm.getReadabilityOfClassifiedAttributes().keySet()) {
			failSafeDefaults.put(key, sm.getReadabilityOfClassifiedAttributes().get(key) + sm.getReadabilityOfClassifiedMethods().get(key));
		}
	}
	
	public void reduceAttackSurface (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: sm.getReadabilityOfClassifiedAttributes().keySet()) {
			reduceAttackSurface .put(key, sm.getReadabilityOfClassifiedAttributes().get(key) + sm.getReadabilityOfClassifiedMethods().get(key) + sm.getReadabilityOfCriticalClasses());
		}
	}
	
	//###########################################################################################################################################################	
	//Cohesion Metrics
	//###########################################################################################################################################################

	//LCOM(C) = (1- |E(C) / V(C)| * |M(C)|) * 100%
	public void lackOfCohesionOfMethods (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		
		HashMap<String,Integer> EC = mpv.EC();
		HashMap<String,Integer> VC = mpv.getSumOfAllInstanceVaribles();
		HashMap<String,Integer> MC = mpv.getSumOfAllInstanceMethodsInClass();
		
		for (String key: VC.keySet()) {
			lackOfCohesionOfMethods.put(key, (float) (1-(VC.get(key) /(VC.get(key)*MC.get(key))))*100);
		}
		
	}
	
	//need requirment method names from each classes.
	public void cohesionAmongMethodsInClass (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String,Integer> k = mpv.getMethodsInClass();
		HashMap<String, HashMap<String, Integer>> l = mpv.getUniqueParamatersInEachMethodInEachClass();
		HashMap<String,Integer> a = mpv.getNumberOfUniqueParametersInClassForAllMethods();
		//HashMap<String, Integer> methodClassL = mpv.getMethodNamesInClass();
		HashMap<String,Integer> newL = new HashMap<String,Integer>();
		
		//sum all L values per class
		for (String key: l.keySet()) {
			int counter = 0;			
			for (String innerKey: l.get(key).keySet()) {
				counter+=l.get(key).get(innerKey);
			}
			newL.put(key, counter);
		}
		
		//CAMC = a / kl
		for (String key :newL.keySet()) {
			cohesionAmongMethodsInClass.put(key, (float) ((a.get(key))/((k.get(key)*newL.get(key)))));
		}		
	}
	
	//###########################################################################################################################################################	
	//Complexity Metrics
	//###########################################################################################################################################################
	
	public void commentRatio (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
			
			HashMap<String,Integer> totalLinesOfCode = mpv.getTotalNumberOfLinesInEachClass();
			HashMap<String,Integer> totalCommentsInCode = mpv.getTotalNumberOfCommentedLinesInEachClass();
			
			for (String key: totalCommentsInCode.keySet()) {
				commentRatio.put(key, (float) totalCommentsInCode.get(key)/(totalLinesOfCode.get(key)-totalCommentsInCode.get(key)));
			}
	}
	
	public void countBaseClasses (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void cyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	//Incorrect Pulled Value. This is actually a class level metric, each class in the hiarchy returns it's relitive depth in its Hierarchy
	public void depthOfInheritanceTree (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		//another ? #:0;
	}
	
	public void countPath (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	//CAT + CMT + CCT
	public void economyOfMechanism (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		economyOfMechanism  = sm.getSecurityAbsoluteMeasurements();
	}
	
	public void essentialCyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void mcCabesCyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void modifiedCyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	//#proposal info lacking, refer to origional paper.
	public void nestingComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		nestingComplexity = mpv.getComplexityDepthInClassMethods();
	}
	
	
	public void numberOfChildren (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		Map<String, ArrayList<String>> immidiateChildrenInClass = mpv.getImidiateChildren();
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		//for each class in project, ? operator, is the class name part of a inheritance hearchy ? yes-> put classname,#children : no-> put classname,0 children.
		for (String key: classNames) {
			numberOfChildren.put(key, (immidiateChildrenInClass.get(key)!=null) ? numberOfChildren.put(key, mpv.getImidiateChildren().get(key).size()) : numberOfChildren.put(key, 0));
		}
	}
	
	public void numberOfMethods (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		numberOfMethods = mpv.getMethodsInClass();
	}
	
	public void strictCyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		strictCyclomaticComplexity  = pv.getMapStrictComplexity();
	}
	
	public void secureWeakestLink (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void weightedMethodsPerClass (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	
	public void SourceLinesOfCode (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String, Integer> projectTotalLinesOfCode = mpv.getTotalNumberOfLinesInEachClass();
        for (String key: projectTotalLinesOfCode.keySet()) {
        	sourceLinesOfCode+=projectTotalLinesOfCode.get(key);
        }		
	}
	
	//###########################################################################################################################################################	
	//Composition Metrics
	//###########################################################################################################################################################

	public void measureOfAggregation (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	//###########################################################################################################################################################	
	//Coupling Metrics
	//###########################################################################################################################################################
	
	//CBC
	public void countOfBaseClasses (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		countOfBaseClasses=mpv.getNumberOfBaseClasses();
	}
	
	//CBO
	public void couplingBetweenObjects (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		mpv.getClassesCoupledToBaseClass();
	}
	
	//CCP
	public void couplingCorruptionPropagation (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		
	}
	
	public void depthOfInheritace (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void directClassCoupling (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void fanIn (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		
	}
	
	public void fanOut (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void henryKafura (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		//HK(F) = 	Length of Function*(FanIn*FanOut)²
		 lengthOfMethod = mpv.getLngthOfEachMethodInEachClass();
		
	}
	
	//doublecheckthis
	public void responceSetForaClass (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	

	
	//###########################################################################################################################################################	
	//Design Size Metrics
	//###########################################################################################################################################################

	public void designSizeInClasses (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		numberofClasses = mpv.getNumberOfClassesInProject().size();
	}
	
	public void stallRatio (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	//###########################################################################################################################################################	
	//Encapsulation Metrics
	//###########################################################################################################################################################

	public void criticalElementRatio (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void dataAccessMetric (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void grantLeastPrivilege (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
		
	public void isolation (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void leastCommomMechanism (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	//
	public void numberOfHierarchies (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		numberOfHierarchies=mpv.getNumberOfHierarchys();
	}
	
	//###########################################################################################################################################################	
	//Inheritance Metrics
	//###########################################################################################################################################################
	
	//MFA(C) =# of Methods Inherited by a Class / # of Total Methods Accessible
	public void measureOfFunctionalAbtraction (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String,Integer> numberOfMethodsInherited = mpv.getClassMethodsInheritedBySubClassm();
		HashMap<String,Integer> totalNumberOfMethodsAccessible =  mpv.getClassMethodsInheritedBySubClassm(); //temp
		
		for (String key: numberOfMethodsInherited.keySet()) {
			measureOfFunctionalAbtraction.put(key, (float) (numberOfMethodsInherited.get(key)/totalNumberOfMethodsAccessible.get(key)));
		}
	}
	
	//###########################################################################################################################################################	
	//Messaging  Metrics
	//###########################################################################################################################################################
	
	public void classInterfaceSize (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}

	//###########################################################################################################################################################	
	//Polymorphism Metrics
	//###########################################################################################################################################################

	public void numberOfPolymorphicMethods (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}

	//###########################################################################################################################################################	
	//Getters 
	//###########################################################################################################################################################

	
	public int getSourceLinesOfCode() {
		return this.sourceLinesOfCode;
	}


	public HashMap<String, Float> getCommentRatio() {
		return commentRatio;
	}


	public HashMap<String, HashMap<String, Integer>> getNestingComplexity() {
		return nestingComplexity;
	}


	public HashMap<String, Integer> getNumberOfChildren() {
		return numberOfChildren;
	}


	public HashMap<String, Integer> getNumberOfMethods() {
		return numberOfMethods;
	}


	public int getNumberOfHierarchies() {
		return numberOfHierarchies;
	}


	public int getCountOfBaseClasses() {
		return countOfBaseClasses;
	}


	public int getNumberofClasses() {
		return numberofClasses;
	}


	public HashMap<String, Integer> getLackOfCohesionOfMethods() {
		//return lackOfCohesionOfMethods;
		return numberOfMethods;
	}


	public HashMap<String, Integer> getCohesionAmongMethodsInClass() {
		//return cohesionAmongMethodsInClass;
		return numberOfMethods;
	}


	public HashMap<String, Float> getMeasureOfFunctionalAbtraction() {
		return measureOfFunctionalAbtraction;
	}


	public HashMap<String, HashMap<String, Integer>> getLengthOfMethod() {
		return lengthOfMethod;
	}


	public int getStallRatio() {
		return stallRatio;
	}


	public HashMap<String, Float> getCouplingCorruptionPropagation() {
		return couplingCorruptionPropagation;
	}


	public HashMap<String, Float> getCouplingBetweenObjects() {
		return couplingBetweenObjects;
	}


	public HashMap<String, Double> getFailSafeDefaults() {
		return failSafeDefaults;
	}


	public HashMap<String, Double> getReduceAttackSurface() {
		return reduceAttackSurface;
	}


	public int getEconomyOfMechanism() {
		return economyOfMechanism;
	}


	public HashMap<String, Integer> getStrictCyclomaticComplexity() {
		return strictCyclomaticComplexity;
	}
	
}
