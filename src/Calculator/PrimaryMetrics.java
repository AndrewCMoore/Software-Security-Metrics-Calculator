package Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrimaryMetrics {

	private Double sourceLinesOfCode=(double) 0; //Project Level Metric
	private HashMap<String, Double> commentRatio = new HashMap<String, Double>(); //class level metric
	private HashMap<String, HashMap<String, Integer>> nestingComplexity = new HashMap<String, HashMap<String, Integer>>(); //method level metric (max loop complexity of each method)
	private HashMap<String, Double> numberOfChildren = new HashMap<String, Double>(); //class level metric
	private HashMap<String, Double> numberOfMethods = new HashMap<String, Double>(); //classlevelmetric
	private double numberOfHierarchies=0;  //project level
	private double countOfBaseClasses=0; //project level
	private double numberofClasses=0; //Project level
	private HashMap<String,Double> lackOfCohesionOfMethods = new HashMap<String,Double>();
	private HashMap<String,Double> cohesionAmongMethodsInClass = new HashMap<String,Double>();
	private HashMap<String,Double> measureOfFunctionalAbtraction = new HashMap<String,Double>();
	private HashMap<String, HashMap<String, Integer>> lengthOfMethod = new HashMap<String, HashMap<String, Integer>>();
	private double stallRatio;
	private HashMap<String,Double> couplingCorruptionPropagation = new HashMap<String,Double>();
	private HashMap<String,Double> couplingBetweenObjects = new HashMap<String,Double>();
	private HashMap<String, Double> failSafeDefaults = new HashMap<String, Double>();
	private HashMap<String, Double> reduceAttackSurface = new HashMap<String, Double>();
	private double economyOfMechanism = (double) 0;
	private HashMap<String, Double> strictCyclomaticComplexity = new HashMap<String, Double>();
	private HashMap<String, Double> cyclomaticComplexity = new HashMap<String, Double>();
	private HashMap<String, Double> modifiedCyclomaticComplexity = new HashMap<String, Double>();
	private HashMap<String, Double> mcCabesCyclomaticComplexity = new HashMap<String, Double>();
	private HashMap<String, Double> countPath = new HashMap<String, Double>();
	private double secureWeakestLink;
	private double isolation;
	private double leastCommomMechanism;
	private HashMap<String, Double> fanIn = new HashMap<String, Double>();
	private HashMap<String, Double> fanOut = new HashMap<String, Double>();
	private HashMap<String, Double> henryKafura = new HashMap<String, Double>();
	private HashMap<String, Double> criticalElementRatio = new HashMap<String, Double>();
	private HashMap<String, Double> dataAccessMetric = new HashMap<String, Double>();
	private HashMap<String, Double> grantLeastPrivelage = new HashMap<String, Double>();
	private HashMap<String, Double> responceSetForaClass = new HashMap<String, Double>();
	private HashMap<String, Double> numberOfPolymorphicMethods = new HashMap<String, Double>();
	private HashMap<String, Double> classInterfaceSize = new HashMap<String, Double>();
	private HashMap<String, Double> depthOfInheritace = new HashMap<String, Double>();
	private HashMap<String, Double> weightedMethodsPerClass = new HashMap<String, Double>();
	private HashMap<String, Double> measureOfAggregation = new HashMap<String, Double>();
	private HashMap<String, Double> directClassCoupling = new HashMap<String, Double>();
	private ArrayList<String> classesInProject = new ArrayList<String>();


	
	
	
	private static final double INT_TO_DOUBLE = 1.0;
	

	
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
		//depthOfInheritace(pv, sm, mpv); duplicate of depthOfInheritanceTree
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
		classesInProject= (ArrayList<String>) mpv.getNumberOfClassesInProject();
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
			lackOfCohesionOfMethods.put(key, (double) (1-(VC.get(key) /(VC.get(key)*MC.get(key))))*100);
		}
		
	}
	
	//need requirment method names from each classes.
	public void cohesionAmongMethodsInClass (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String,Double> k = mpv.getMethodsInClass();
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
			cohesionAmongMethodsInClass.put(key, (double) ((a.get(key))/((k.get(key)*newL.get(key)))));
		}		
	}
	
	//###########################################################################################################################################################	
	//Complexity Metrics
	//###########################################################################################################################################################
	
	public void commentRatio (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
			
			HashMap<String,Integer> totalLinesOfCode = mpv.getTotalNumberOfLinesInEachClass();
			HashMap<String,Integer> totalCommentsInCode = mpv.getTotalNumberOfCommentedLinesInEachClass();
			
			for (String key: totalCommentsInCode.keySet()) {
				commentRatio.put(key, (double) totalCommentsInCode.get(key)/(totalLinesOfCode.get(key)-totalCommentsInCode.get(key)));
			}
	}
	
	public void countBaseClasses (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void cyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapCyclomaticComplexity().keySet()) {
			cyclomaticComplexity.put(key, (double) pv.getMapCyclomaticComplexity().get(key));
		}
	}
	
	
	public void countPath (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapCountPath().keySet()) {
			countPath.put(key, (double) pv.getMapCountPath().get(key));
		}
	}
	
	//CAT + CMT + CCT
	public void economyOfMechanism (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		economyOfMechanism  = sm.getSecurityAbsoluteMeasurements();
	}
	
	public void modifiedCyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapModifiedComplexity().keySet()) {
			modifiedCyclomaticComplexity.put(key, (double) pv.getMapModifiedComplexity().get(key));
		}
	}
	
	public void mcCabesCyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapMcCabesComplexity().keySet()) {
			mcCabesCyclomaticComplexity .put(key, (double) pv.getMapMcCabesComplexity().get(key));
		}
	}
	
	//#proposal info lacking, refer to original paper.
	public void nestingComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		nestingComplexity = mpv.getComplexityDepthInClassMethods();
	}
	
	public void numberOfChildren (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		Map<String, ArrayList<String>> immidiateChildrenInClass = mpv.getImidiateChildren();
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		//for each class in project, ? operator, is the class name part of a inheritance hearchy ? yes-> put classname,#children : no-> put classname,0 children.
		for (String key: classNames) {
			numberOfChildren.put(key, (immidiateChildrenInClass.get(key)!=null) ? numberOfChildren.put(key, (double) mpv.getImidiateChildren().get(key).size()) : numberOfChildren.put(key, (double) 0));
		}
	}
	
	public void numberOfMethods (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		numberOfMethods = mpv.getMethodsInClass();
	}
	
	public void strictCyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		strictCyclomaticComplexity  = pv.getMapStrictComplexity();
	}
	
	public void secureWeakestLink (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: sm.getWritabilityOfClassifiedAttributes().keySet()) {
	        secureWeakestLink += sm.getWritabilityOfClassifiedAttributes().get(key) + sm.getWritabilityOfClassifiedMethods().get(key);
	    }	
	}
	
	public void weightedMethodsPerClass (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String, HashMap<String,Integer>> complexityDepthInClassMethods = mpv.getComplexityDepthInClassMethods();
		HashMap<String, Double> complexityDepthInClassMethodsInClass = new HashMap<String, Double>();
		double classMethodComplexityTotal;
		double classMethodAverageMultiplier; 
		for (String key:mpv.getMethodsInClass().keySet()) {
			classMethodComplexityTotal=0.0;
			classMethodAverageMultiplier=0.0;
			for (String methodName: complexityDepthInClassMethodsInClass.keySet()){
				classMethodComplexityTotal+=complexityDepthInClassMethods.get(key).get(methodName);
				classMethodAverageMultiplier+=1;
			}
			weightedMethodsPerClass.put(key, (classMethodComplexityTotal / (classMethodAverageMultiplier*classMethodAverageMultiplier)));
		}
		
		
		
	}
	
	
	public void SourceLinesOfCode (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String, Integer> projectTotalLinesOfCode = mpv.getTotalNumberOfLinesInEachClass();
        for (String key: projectTotalLinesOfCode.keySet()) {
        	sourceLinesOfCode+=projectTotalLinesOfCode.get(key);
        }		
	}
	
	//###########################################################################################################################################################	
	//Composition Metrics
	//###########################################################################################################################################################

	//Attributes that are classes vs # attribuutes/ this was missed, temp soln for now. :(
	public void measureOfAggregation (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for(String key : pv.getMapTotalMethods().keySet()) {
			measureOfAggregation.put(key, (double) pv.getMapTotalMethods().get(key) + pv.getMapMethodInvocations().get(key));
		}
	}
	
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
	
	//CCP //:(
	public void couplingCorruptionPropagation (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		couplingCorruptionPropagation=mpv.getDepthOfInheritanceTreeAtCurrentSuperClass();
	}
	
	public void depthOfInheritanceTree (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		depthOfInheritace=mpv.getDepthOfInheritanceTreeAtCurrentSuperClass();
	}
	
	public void directClassCoupling (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		directClassCoupling=mpv.getClassesCoupledToBaseClass();
	}
	
	public void fanIn(PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapMethodInputs().keySet()) {
        	fanIn.put(key, (double) pv.getMapMethodInputs().get(key));
        }
	}
	
	public void fanOut(PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapMethodOutputs().keySet()) {
        	fanOut.put(key, (double) pv.getMapMethodOutputs().get(key));
        }
	}
	
	public void henryKafura(PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapHenryKafura().keySet()) {
        	henryKafura.put(key, (double) pv.getMapHenryKafura().get(key));
        }
	}
	
	//doublecheckthis
	public void responceSetForaClass (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for(String key : pv.getMapTotalMethods().keySet()) {
			responceSetForaClass.put(key, (double) pv.getMapTotalMethods().get(key) + pv.getMapMethodInvocations().get(key));
		}
	}
	

	
	//###########################################################################################################################################################	
	//Design Size Metrics
	//###########################################################################################################################################################

	public void designSizeInClasses (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		numberofClasses = mpv.getNumberOfClassesInProject().size();
	}
	
	public void stallRatio (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		//This metric will no longer be used.
	}
	
	//###########################################################################################################################################################	
	//Encapsulation Metrics
	//###########################################################################################################################################################

	public void criticalElementRatio (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapCriticalElements().keySet()) {
        	criticalElementRatio.put(key, (double) pv.getMapCriticalElements().get(key) / pv.getMapTotalAttributes().get(key));
        }
	}
	
	public void dataAccessMetric (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapCriticalElements().keySet()) {
        	dataAccessMetric.put(key, (double) pv.getMapCriticalElements().get(key) / pv.getMapTotalAttributes().get(key));
        }
	}
	
	public void grantLeastPrivilege (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: sm.getWritabilityOfClassifiedAttributes().keySet()) {
        	grantLeastPrivelage.put(key, sm.getReadabilityOfClassifiedAttributes().get(key) + sm.getWritabilityOfClassifiedMethods().get(key) + sm.getWritabilityOfCriticalClasses());
        }
	}
		
	public void isolation (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		isolation = sm.getWritabilityOfCriticalClasses();
	}
	
	public void leastCommomMechanism (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		leastCommomMechanism=sm.getReadabilityOfCriticalClasses()+sm.getWritabilityOfCriticalClasses();
	}
	
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
			measureOfFunctionalAbtraction.put(key, (double) (numberOfMethodsInherited.get(key)/totalNumberOfMethodsAccessible.get(key)));
		}
	}
	
	//###########################################################################################################################################################	
	//Messaging  Metrics
	//###########################################################################################################################################################
	
	public void classInterfaceSize (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String,Integer> mapPublicInstance = pv.getMapPublicInstance();
		for (String key: mapPublicInstance.keySet()) {
			classInterfaceSize.put(key, (Double) (mapPublicInstance.get(key)+pv.getMapPublicClass().get(key)*INT_TO_DOUBLE));
		}
	}

	//###########################################################################################################################################################	
	//Polymorphism Metrics
	//###########################################################################################################################################################

	public void numberOfPolymorphicMethods (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		numberOfPolymorphicMethods=mpv.getMethodsInClass();
	}

	//###########################################################################################################################################################	
	//Getters 
	//###########################################################################################################################################################

	
	public Double getSourceLinesOfCode() {
		return this.sourceLinesOfCode;
	}


	public HashMap<String, Double> getCommentRatio() {
		return commentRatio;
	}


	public HashMap<String, HashMap<String, Integer>> getNestingComplexity() {
		return nestingComplexity;
	}


	public HashMap<String, Double> getNumberOfChildren() {
		return numberOfChildren;
	}


	public HashMap<String, Double> getNumberOfMethods() {
		return numberOfMethods;
	}


	public double getNumberOfHierarchies() {
		return numberOfHierarchies;
	}


	public double getCountOfBaseClasses() {
		return countOfBaseClasses;
	}


	public double getNumberofClasses() {
		return numberofClasses;
	}


	public HashMap<String, Double> getLackOfCohesionOfMethods() {
		//return lackOfCohesionOfMethods;
		return numberOfMethods;
	}


	public HashMap<String, Double> getCohesionAmongMethodsInClass() {
		//return cohesionAmongMethodsInClass;
		return numberOfMethods;
	}


	public HashMap<String, Double> getMeasureOfFunctionalAbtraction() {
		return measureOfFunctionalAbtraction;
	}


	public HashMap<String, HashMap<String, Integer>> getLengthOfMethod() {
		return lengthOfMethod;
	}


	public double getStallRatio() {
		return stallRatio;
	}


	public HashMap<String, Double> getCouplingCorruptionPropagation() {
		return couplingCorruptionPropagation;
	}


	public HashMap<String, Double> getCouplingBetweenObjects() {
		return couplingBetweenObjects;
	}


	public HashMap<String, Double> getFailSafeDefaults() {
		return failSafeDefaults;
	}


	public HashMap<String, Double> getReduceAttackSurface() {
		return reduceAttackSurface;
	}


	public Double getEconomyOfMechanism() {
		return economyOfMechanism;
	}


	public HashMap<String, Double> getStrictCyclomaticComplexity() {
		return strictCyclomaticComplexity;
	}

	// :(
	public HashMap<String, Double> getAverageNumberOfAncestors() {
	
		return new HashMap<String, Double>();
	}


	public HashMap<String, Double> getCyclomaticComplexity() {
		return cyclomaticComplexity;
	}


	public HashMap<String, Double> getModifiedCyclomaticComplexity() {
		return modifiedCyclomaticComplexity;
	}


	public HashMap<String, Double> getMcCabesCyclomaticComplexity() {
		return mcCabesCyclomaticComplexity;
	}


	public HashMap<String, Double> getCountPath() {
		return countPath;
	}


	public double getSecureWeakestLink() {
		return secureWeakestLink;
	}


	public HashMap<String, Double> getFanIn() {
		return fanIn;
	}


	public HashMap<String, Double> getFanOut() {
		return fanOut;
	}


	public HashMap<String, Double> getHenryKafura() {
		return henryKafura;
	}


	public HashMap<String, Double> getCriticalElementRatio() {
		return criticalElementRatio;
	}


	public HashMap<String, Double> getDataAccessMetric() {
		return dataAccessMetric;
	}


	public HashMap<String, Double> getGrantLeastPrivelage() {
		return grantLeastPrivelage;
	}


	public HashMap<String, Double> getDepthOfInheritanceTree() {
		return depthOfInheritace;
	}


	public HashMap<String, Double> getWeightedMethodsPerClass() {
		return weightedMethodsPerClass;
	}


	public HashMap<String, Double> getMeasureOfAggregation() {
		return measureOfAggregation;
	}


	public HashMap<String, Double> getDirectClassCoupling() {
		return directClassCoupling;
	}


	public HashMap<String, Double> getResponseSetForAClass() {
		return responceSetForaClass;
	}


	public double getDesignSizeInClasses() {
		return numberofClasses;
	}


	public double getIsolation() {
		return isolation;
	}


	public double getLeastCommonMechanism() {
		return leastCommomMechanism;
	}


	public HashMap<String, Double> getClassInterfaceSize() {
		return classInterfaceSize;
	}


	public HashMap<String, Double> getNumberOfPolymorphicMethods() {
		return numberOfPolymorphicMethods;
	}
	
	public ArrayList<String> getClassesInProject() {
		return classesInProject;
	}
	  
}