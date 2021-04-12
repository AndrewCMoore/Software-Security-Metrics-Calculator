package Calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PrimaryMetrics {

	private Double sourceLinesOfCode=(double) 0; //Project Level Metric
	private HashMap<String, Double> commentRatio = new HashMap<String, Double>(); //class level metric
	private HashMap<String,  Double> nestingComplexity = new HashMap<String, Double>(); //method level metric (max loop complexity of each method)
	private HashMap<String, Double> numberOfChildren = new HashMap<String, Double>(); //class level metric
	private HashMap<String, Double> numberOfMethods = new HashMap<String, Double>(); //classlevelmetric
	private double numberOfHierarchies=0;  //project level
	private double countOfBaseClasses=0; //project level
	private double numberofClasses=0; //Project level
	private HashMap<String,Double> lackOfCohesionOfMethods = new HashMap<String,Double>();
	private HashMap<String,Double> cohesionAmongMethodsInClass = new HashMap<String,Double>();
	private HashMap<String,Double> measureOfFunctionalAbtraction = new HashMap<String,Double>();
	private HashMap<String, HashMap<String,Double>> lengthOfMethod = new HashMap<String, HashMap<String,Double>>();
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
	private Set<String> classesInProject = new HashSet<String>();
	private HashMap<String, Double> averageNumberOfAncestors = new HashMap<String, Double>();


	
	
	
	private static final double INT_TO_DOUBLE = 1.0;
	

	
	public PrimaryMetrics(PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		classesInProject=  mpv.getNumberOfClassesInProject();
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
		//classesInProject=  mpv.getNumberOfClassesInProject();
		System.out.println("OverHere");
		printTrash();
	}
	
	
	//###########################################################################################################################################################	
	//Abstraction Metrics
	//###########################################################################################################################################################

	public void averageNumberOfAncestor(PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		int averageNumberOfChildren=0;
		Map<String, ArrayList<String>> imidiateChildren = mpv.getImidiateChildren();
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		Set<String> inheritanceClasses = new HashSet<String>();
		for (String className: classNames) {
			if (imidiateChildren.containsKey(className)) {
				averageNumberOfChildren+=imidiateChildren.get(className).size();
				inheritanceClasses.add(className);
				for (String childClassName : imidiateChildren.get(className)) {
					inheritanceClasses.add(childClassName);
				}
				averageNumberOfAncestors.put(className, ((double)averageNumberOfChildren/(double)inheritanceClasses.size()));
			} else {
				averageNumberOfAncestors.put(className, 0.0);
			}
			
		}
		

		
	}
	
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
		
		HashMap<String,Double> EC = mpv.getmapNumberOfUniqueAttributesInMethodsInClass();		
		HashMap<String,Double> VC = new HashMap<String,Double>();
		HashMap<String,Double> MC = mpv.getSumOfAllInstanceMethodsInClass();
		
		for (String key: pv.getMapPublicInstance().keySet()) {
			VC.put(key, (pv.getMapPrivateProtectedInstance().get(key)+pv.getMapPrivateProtectedInstance().get(key)));
			lackOfCohesionOfMethods.put(key, (double) (1-(EC.get(key) /(double)(VC.get(key)*(double)MC.get(key))))*100);
		}
		
	}
	
	//need requirment method names from each classes.
	public void cohesionAmongMethodsInClass (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String,Double> k = mpv.getMethodsInClass();
		HashMap<String, HashMap<String,Double>> l = mpv.getUniqueParamatersInEachMethodInEachClass();
		HashMap<String,Double> a = mpv.getNumberOfUniqueParametersInClassForAllMethods();
		//HashMap<String,Double> methodClassL = mpv.getMethodNamesInClass();
		HashMap<String,Double> newL = new HashMap<String,Double>();
		
		//sum all L values per class
		for (String key: l.keySet()) {
			int counter = 0;			
			for (String innerKey: l.get(key).keySet()) {
				counter+=l.get(key).get(innerKey);
			}
			newL.put(key, (double) counter);
		}
		
		//CAMC = a / kl
		for (String key :newL.keySet()) {
			cohesionAmongMethodsInClass.put(key, (double) ((double)(a.get(key))/((k.get(key)*(double)newL.get(key)))));
		}		
	}
	
	//###########################################################################################################################################################	
	//Complexity Metrics
	//###########################################################################################################################################################
	
	public void commentRatio (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
			
			HashMap<String,Double> totalLinesOfCode = mpv.getTotalNumberOfLinesInEachClass();
			HashMap<String,Double> totalCommentsInCode = mpv.getTotalNumberOfCommentedLinesInEachClass();
			
			for (String key: totalCommentsInCode.keySet()) {
				commentRatio.put(key, (double) totalCommentsInCode.get(key)/((double)totalLinesOfCode.get(key)-(double)totalCommentsInCode.get(key)));
			}
	}
	
	public void countBaseClasses (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {}
	
	public void cyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapCyclomaticComplexity().keySet()) {
			cyclomaticComplexity.put(key, (double) pv.getMapCyclomaticComplexity().get(key));
		}
	}
	
	
	public void countPath (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		
		/*for (String key: pv.getMapCountPath().keySet()) {
			countPath.put(key, (double) pv.getMapCountPath().get(key));
		}*/		
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		HashMap<String, Double> classCountPath  = pv.getMapCountPath();
		for (String key: classNames) {
			countPath.put(key, (classCountPath.containsKey(key)) ?  (double) classCountPath.get(key) :  (double) 0.0);
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
		double complexityAverage;
		HashMap<String, HashMap<String,Double>> nestingComplexityInEachClass = mpv.getComplexityDepthInClassMethods();
		for (String className: nestingComplexityInEachClass.keySet() ){
			complexityAverage=0;
			for (String methodName: nestingComplexityInEachClass.get(className).keySet()) {
				complexityAverage+=nestingComplexityInEachClass.get(className).get(methodName);
			}
			if(nestingComplexityInEachClass.get(className).size() != 0) {
				complexityAverage=complexityAverage/nestingComplexityInEachClass.get(className).size();
			} else {
				complexityAverage = 0;
			}
			nestingComplexity.put(className,complexityAverage);
		}	
	}
	
	public void numberOfChildren (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		Map<String, ArrayList<String>> immidiateChildrenInClass = mpv.getImidiateChildren();
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		//for each class in project, ? operator, is the class name part of a inheritance hearchy ? yes-> put classname,#children : no-> put classname,0 children.
		for (String key: classNames) {
			numberOfChildren.put(key, (immidiateChildrenInClass.containsKey(key)) ?  (double) mpv.getImidiateChildren().get(key).size() :  (double) 0.0);
		}
	}
	
	public void numberOfMethods (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		numberOfMethods = mpv.getMethodsInClass();
	}
	
	public void strictCyclomaticComplexity (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		HashMap<String,Double> strictCC  = pv.getMapStrictComplexity();
		for (String key: classNames) {
			strictCyclomaticComplexity.put(key, (strictCC.containsKey(key)) ?  (double) strictCC.get(key) :  (double) 0.0);
		}
	}
	
	public void secureWeakestLink (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: sm.getWritabilityOfClassifiedAttributes().keySet()) {
	        secureWeakestLink += sm.getWritabilityOfClassifiedAttributes().get(key) + sm.getWritabilityOfClassifiedMethods().get(key);
	    }	
	}
	
	public void weightedMethodsPerClass (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String, HashMap<String,Double>> complexityDepthInClassMethods = mpv.getComplexityDepthInClassMethods();
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
			if(classMethodAverageMultiplier*classMethodAverageMultiplier != 0) {
				weightedMethodsPerClass.put(key, (double)(classMethodComplexityTotal / (double)(classMethodAverageMultiplier*classMethodAverageMultiplier)));
			} else {
				weightedMethodsPerClass.put(key, 0.0);
			}
		}
		
		
		
	}
	
	
	public void SourceLinesOfCode (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String,Double> projectTotalLinesOfCode = mpv.getTotalNumberOfLinesInEachClass();
        for (String key: projectTotalLinesOfCode.keySet()) {
        	sourceLinesOfCode+=(double)projectTotalLinesOfCode.get(key);
        }		
	}
	
	//###########################################################################################################################################################	
	//Composition Metrics
	//###########################################################################################################################################################

	//Attributes that are classes vs # attribuutes/ this was missed, temp soln for now. :(
	public void measureOfAggregation (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for(String key : pv.getMapTotalMethods().keySet()) {
			measureOfAggregation.put(key, (double) pv.getMapTotalMethods().get(key) + (double)pv.getMapMethodInvocations().get(key));
		}
	}
	
	//###########################################################################################################################################################	
	//Coupling Metrics
	//###########################################################################################################################################################
	
	//CBC
	public void countOfBaseClasses (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		countOfBaseClasses=(double)mpv.getNumberOfBaseClasses();
	}
	
	//CBO
	public void couplingBetweenObjects (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		
		HashMap<String, Double> classCoupling = mpv.getClassCouplingRelationship();
		for (String className: getClassesInProject()) {
			couplingBetweenObjects.put(className, (classCoupling.containsKey(className) ? (double) classCoupling.get(className): (double) 0.0 ));
		}
		
	}
	
	//CCP //!
	public void couplingCorruptionPropagation (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String, Double> methodsInherited=mpv.getNumberOfMethodsInheritedByAClass();
		for (String className: getClassesInProject()) {
			couplingBetweenObjects.put(className, (methodsInherited.containsKey(className) ? (double) methodsInherited.get(className) : (double) 0.0 ));
		}
	}
	
	public void depthOfInheritanceTree (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String className: mpv.getNumberOfClassesInProject()) {
			depthOfInheritace.put(className,(mpv.getDepthOfInheritanceTreeAtCurrentSuperClass().containsKey(className)==true) ? mpv.getDepthOfInheritanceTreeAtCurrentSuperClass().get(className) : (double) 0.0) ;
		}
	}
	
	public void directClassCoupling (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: mpv.getClassCouplingRelationship().keySet()) {
			directClassCoupling.put(key, (double) mpv.getClassCouplingRelationship().get(key));
		}

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
			responceSetForaClass.put(key, (double) pv.getMapTotalMethods().get(key) + (double)pv.getMapMethodInvocations().get(key));
		}
	}
	

	
	//###########################################################################################################################################################	
	//Design Size Metrics
	//###########################################################################################################################################################

	public void designSizeInClasses (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		numberofClasses =(double) mpv.getNumberOfClassesInProject().size();
	}
	
	public void stallRatio (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		//This metric will no longer be used.
	}
	
	//###########################################################################################################################################################	
	//Encapsulation Metrics
	//###########################################################################################################################################################

	public void criticalElementRatio (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		for (String key: pv.getMapCriticalElements().keySet()) {
			if(pv.getMapTotalAttributes().get(key) != 0) {
				criticalElementRatio.put(key, (double) pv.getMapCriticalElements().get(key) /(double) pv.getMapTotalAttributes().get(key));
			} else {
				criticalElementRatio.put(key, 0.0);
			}
        }
	}
	
	public void dataAccessMetric (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		/*for (String key: pv.getMapCriticalElements().keySet()) {
        	dataAccessMetric.put(key, (double) pv.getMapCriticalElements().get(key) / (double)pv.getMapTotalAttributes().get(key));
        }*/
		
		HashMap<String, Double> criticalMapElements = pv.getMapCriticalElements();
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		for (String key: classNames) {
			if(pv.getMapTotalAttributes().get(key) != 0) {
				dataAccessMetric.put(key, (criticalMapElements.containsKey(key)) ? (double) pv.getMapCriticalElements().get(key) / (double)pv.getMapTotalAttributes().get(key) :  (double) 0.0);
			} else {
				dataAccessMetric.put(key, 0.0);
			}
		}
		
	}
	
	// sm.getWritabilityOfCriticalClasses()  is wrong. need to devide
	public void grantLeastPrivilege (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		/*for (String key: sm.getWritabilityOfClassifiedAttributes().keySet()) {
        	grantLeastPrivelage.put(key, sm.getReadabilityOfClassifiedAttributes().get(key) + sm.getWritabilityOfClassifiedMethods().get(key) + sm.getWritabilityOfCriticalClasses());
        }*/
		
		
		Set<String> classNames = mpv.getNumberOfClassesInProject();
		HashMap<String,Double> writabilityOfClasses=sm.getWritabilityOfClassifiedAttributes();
		for (String key: classNames) {
			grantLeastPrivelage.put(key, (writabilityOfClasses.containsKey(key)) ?  sm.getReadabilityOfClassifiedAttributes().get(key) + sm.getWritabilityOfClassifiedMethods().get(key) + sm.getWritabilityOfCriticalClasses() :  (double) 0.0);
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
		HashMap<String,Double> numberOfMethodsInherited = mpv.getClassMethodsInheritedBySubClassm();
		HashMap<String,Double> totalNumberOfMethodsAccessible =  mpv.getClassMethodsInheritedBySubClassm(); //temp
		
		for (String key: numberOfMethodsInherited.keySet()) {
			measureOfFunctionalAbtraction.put(key, (double) (numberOfMethodsInherited.get(key)/(double)totalNumberOfMethodsAccessible.get(key)));
		}
	}
	
	//###########################################################################################################################################################	
	//Messaging  Metrics
	//###########################################################################################################################################################
	
	public void classInterfaceSize (PulledValues pv, SecondaryMetrics sm, MurgePulledValues mpv) {
		HashMap<String, Double> mapPublicInstance = pv.getMapPublicInstance();
		for (String key: mapPublicInstance.keySet()) {
			classInterfaceSize.put(key, (double) (mapPublicInstance.get(key)+ (double)pv.getMapPublicClass().get(key)));
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


	public HashMap<String, Double> getNestingComplexity() {
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


	public HashMap<String, HashMap<String,Double>> getLengthOfMethod() {
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


	public HashMap<String, Double> getAverageNumberOfAncestors() {
	
		return strictCyclomaticComplexity;
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
	
	public Set<String> getClassesInProject() {
		return classesInProject;
	}
	
	public void printTrash() {
		System.out.println("Primary Metrics");
		System.out.println("sourceLinesOfCode: " + this.getSourceLinesOfCode());
		System.out.println("commentRatio : " + this.getCommentRatio());
		System.out.println("nestingComplexity : " + this.getNestingComplexity());
		System.out.println("numberOfChildren : " + this.getNumberOfChildren());
		System.out.println("numberOfMethods : " + this.getNumberOfMethods());
		System.out.println("numberOfHierarchies: " + this.getNumberOfHierarchies());
		System.out.println("countOfBaseClasses: " + this.getCountOfBaseClasses());
		System.out.println("numberofClasses: " + this.getNumberofClasses());
		System.out.println("lackOfCohesionOfMethods : " + this.getLackOfCohesionOfMethods());
		System.out.println("cohesionAmongMethodsInClass : " + this.getCohesionAmongMethodsInClass());
		System.out.println("measureOfFunctionalAbtraction : " + this.getMeasureOfFunctionalAbtraction());
		System.out.println("lengthOfMethod : " + this.lengthOfMethod);
		System.out.println("stallRatio: " + this.getStallRatio());
		System.out.println("couplingCorruptionPropagation : " + this.getCouplingCorruptionPropagation());
		System.out.println("couplingBetweenObjects : " + this.getCouplingBetweenObjects());
		System.out.println("failSafeDefaults : " + this.getFailSafeDefaults());
		System.out.println("reduceAttackSurface : " + this.getReduceAttackSurface());
		System.out.println("economyOfMechanism : " + this.getEconomyOfMechanism());
		System.out.println("strictCyclomaticComplexity : " + this.getStrictCyclomaticComplexity());
		System.out.println("cyclomaticComplexity : " + this.getCyclomaticComplexity());
		System.out.println("modifiedCyclomaticComplexity : " + this.getModifiedCyclomaticComplexity());
		System.out.println("mcCabesCyclomaticComplexity : " + this.getMcCabesCyclomaticComplexity());
		System.out.println("countPath : " + this.getCountPath());
		System.out.println("secureWeakestLink: " + this.getSecureWeakestLink());
		System.out.println("isolation: " + this.getIsolation());
		System.out.println("leastCommomMechanism: " + this.getLeastCommonMechanism());
		System.out.println("fanIn : " + this.getFanIn());
		System.out.println("fanOut : " + this.getFanOut());
		System.out.println("henryKafura : " + this.getHenryKafura());
		System.out.println("criticalElementRatio : " + this.getCriticalElementRatio());
		System.out.println("dataAccessMetric : " + this.getDataAccessMetric());
		System.out.println("grantLeastPrivelage : " + this.getGrantLeastPrivelage());
		System.out.println("responceSetForaClass : " + this.getResponseSetForAClass());
		System.out.println("numberOfPolymorphicMethods : " + this.getNumberOfPolymorphicMethods());
		System.out.println("classInterfaceSize : " + this.getClassInterfaceSize());
		System.out.println("depthOfInheritace : " + this.getDepthOfInheritanceTree());
		System.out.println("weightedMethodsPerClass : " + this.getWeightedMethodsPerClass());
		System.out.println("measureOfAggregation : " + this.getMeasureOfAggregation());
		System.out.println("directClassCoupling : " + this.getDirectClassCoupling());
		System.out.println("classesInProject : " + this.getClassesInProject());
		System.out.println("averageNumberOfAncestors : " + this.getAverageNumberOfAncestors());
		
	

		
		/*double commentRatioSum=0,nestingComplexitySum=0,numberOfChildrenSum=0,numberOfMethodsSum=0,
				lackOfCohesionOfMethodsSum=0,cohesionAmongMethodsInClassSum=0,measureOfFunctionalAbtractionSum=0,
				lengthOfMethodSum=0,couplingCorruptionPropagationSum=0,couplingBetweenObjectsSum=0,
				failSafeDefaultsSum=0,reduceAttackSurfaceSum=0,strictCyclomaticComplexitySum=0,cyclomaticComplexitySum=0,
				modifiedCyclomaticComplexitySum=0,mcCabesCyclomaticComplexitySum=0,countPathSum=0,
				fanInSum=0,fanOutSum=0,henryKafuraSum=0,criticalElementRatioSum=0,dataAccessMetricSum=0,grantLeastPrivelageSum=0,
				responceSetForaClassSum=0
				,numberOfPolymorphicMethodsSum=0,classInterfaceSizeSum=0,depthOfInheritaceSum=0,
				weightedMethodsPerClassSum=0,measureOfAggregationSum=0,directClassCouplingSum=0,averageNumberOfAncestorsSum=0;
		
		//for (String key:classesInProject) {
			
			
				

			for (String key :nestingComplexity.keySet()) {
				nestingComplexitySum += nestingComplexity.get(key);
			}
			

			for (String key :numberOfChildren.keySet()) {
				numberOfChildrenSum += numberOfChildren.get(key);
			}

			for (String key :numberOfMethods.keySet()) {
				numberOfMethodsSum += numberOfMethods.get(key);
			}

			for (String key :lackOfCohesionOfMethods.keySet()) {
				lackOfCohesionOfMethodsSum += lackOfCohesionOfMethods.get(key);
			}

			for (String key :cohesionAmongMethodsInClass.keySet()) {
				cohesionAmongMethodsInClassSum += cohesionAmongMethodsInClass.get(key);
			}

			for (String key :measureOfFunctionalAbtraction.keySet()) {
				measureOfFunctionalAbtractionSum += measureOfFunctionalAbtraction.get(key);
			}

			
				lengthOfMethodSum += 0;
			
			for (String key :couplingCorruptionPropagation.keySet()) {
				couplingCorruptionPropagationSum += couplingCorruptionPropagation.get(key);
			}

			for (String key :couplingBetweenObjects.keySet()) {
				couplingBetweenObjectsSum += couplingBetweenObjects.get(key);
			}

			for (String key :failSafeDefaults.keySet()) {
				failSafeDefaultsSum += failSafeDefaults.get(key);
			}

			for (String key :reduceAttackSurface.keySet()) {
				reduceAttackSurfaceSum += reduceAttackSurface.get(key);
			}

			for (String key :strictCyclomaticComplexity.keySet()) {
				strictCyclomaticComplexitySum += strictCyclomaticComplexity.get(key);
			}

			for (String key :cyclomaticComplexity.keySet()) {
				cyclomaticComplexitySum += cyclomaticComplexity.get(key);
			}

			for (String key :modifiedCyclomaticComplexity.keySet()) {
				modifiedCyclomaticComplexitySum += modifiedCyclomaticComplexity.get(key);
			}

			for (String key :mcCabesCyclomaticComplexity.keySet()) {
				mcCabesCyclomaticComplexitySum += mcCabesCyclomaticComplexity.get(key);
			}

			for (String key :countPath.keySet()) {
				countPathSum += countPath.get(key);
			}

			for (String key :fanIn.keySet()) {
				fanInSum += fanIn.get(key);
			}

			for (String key :fanOut.keySet()) {
				fanOutSum += fanOut.get(key);
			}

			for (String key :henryKafura.keySet()) {
				henryKafuraSum += henryKafura.get(key);
			}

			for (String key :criticalElementRatio.keySet()) {
				criticalElementRatioSum += criticalElementRatio.get(key);
			}
			
			

			for (String key :dataAccessMetric.keySet()) {
				dataAccessMetricSum += dataAccessMetric.get(key);
			}

			for (String key :grantLeastPrivelage.keySet()) {
				grantLeastPrivelageSum += grantLeastPrivelage.get(key);
			}

			for (String key :responceSetForaClass.keySet()) {
				responceSetForaClassSum += responceSetForaClass.get(key);
			} 

			for (String key :numberOfPolymorphicMethods.keySet()) {
				numberOfPolymorphicMethodsSum += numberOfPolymorphicMethods.get(key);
			}

			for (String key :classInterfaceSize.keySet()) {
				classInterfaceSizeSum += classInterfaceSize.get(key);
			}

			for (String key :depthOfInheritace.keySet()) {
				depthOfInheritaceSum += depthOfInheritace.get(key);
			}
			

			for (String key :weightedMethodsPerClass.keySet()) {
				weightedMethodsPerClassSum += weightedMethodsPerClass.get(key);
			}

			for (String key :measureOfAggregation.keySet()) {
				measureOfAggregationSum += measureOfAggregation.get(key);
			}

			for (String key :directClassCoupling.keySet()) {
				directClassCouplingSum += directClassCoupling.get(key);
			}

			for (String key :averageNumberOfAncestors.keySet()) {
				averageNumberOfAncestorsSum += averageNumberOfAncestors.get(key);
			}
		
			
		
		System.out.println("sourceLinesOfCode");
		System.out.println(sourceLinesOfCode);
		System.out.println("leastCommomMechanism");
		System.out.println(leastCommomMechanism);		
		System.out.println("isolation");
		System.out.println(isolation);
		System.out.println("secureWeakestLink");
		System.out.println(secureWeakestLink);
		System.out.println("economyOfMechanism");
		System.out.println(economyOfMechanism);
		System.out.println("numberofClasses");
		System.out.println(numberofClasses);
		System.out.println("countOfBaseClasses");
		System.out.println(countOfBaseClasses);
		System.out.println("numberOfHierarchies");
		System.out.println(numberOfHierarchies);	
		
		System.out.println("nestingComplexitySum");
		System.out.println(nestingComplexitySum);
		System.out.println("numberOfChildrenSum");
		System.out.println(numberOfChildrenSum);
		System.out.println("numberOfMethodsSum");
		System.out.println(numberOfMethodsSum);		
		System.out.println("lackOfCohesionOfMethodsSum");
		System.out.println(lackOfCohesionOfMethodsSum);		
		System.out.println("cohesionAmongMethodsInClassSum");		
		System.out.println(cohesionAmongMethodsInClassSum);		
		System.out.println("measureOfFunctionalAbtractionSum");
		System.out.println(measureOfFunctionalAbtractionSum);
		System.out.println("lengthOfMethodSum");
		System.out.println(lengthOfMethodSum);
		System.out.println("couplingCorruptionPropagationSum");
		System.out.println(couplingCorruptionPropagationSum);		
		System.out.println("couplingBetweenObjectsSum");
		System.out.println(couplingBetweenObjectsSum);
		System.out.println("failSafeDefaultsSum");
		System.out.println(failSafeDefaultsSum);
		System.out.println("reduceAttackSurfaceSum");
		System.out.println(reduceAttackSurfaceSum);		
		System.out.println("strictCyclomaticComplexitySum");
		System.out.println(strictCyclomaticComplexitySum);
		System.out.println("cyclomaticComplexitySum");
		System.out.println(cyclomaticComplexitySum);		
		System.out.println("modifiedCyclomaticComplexitySum");
		System.out.println(modifiedCyclomaticComplexitySum);
		System.out.println("mcCabesCyclomaticComplexitySum");
		System.out.println(mcCabesCyclomaticComplexitySum);
		System.out.println("countPathSum");
		System.out.println(countPathSum);
		System.out.println("fanInSum");
		System.out.println(fanInSum);
		System.out.println("fanOutSum");
		System.out.println(fanOutSum);		
		System.out.println("henryKafuraSum");
		System.out.println(henryKafuraSum);
		System.out.println("criticalElementRatioSum");
		System.out.println(criticalElementRatioSum);
		System.out.println("dataAccessMetricSum");
		System.out.println(dataAccessMetricSum);
		System.out.println("grantLeastPrivelageSum");
		System.out.println(grantLeastPrivelageSum);
		System.out.println("responceSetForaClassSum");
		System.out.println(responceSetForaClassSum);
		System.out.println("numberOfPolymorphicMethodsSum");
		System.out.println(numberOfPolymorphicMethodsSum);
		System.out.println("classInterfaceSizeSum");
		System.out.println(classInterfaceSizeSum);
		System.out.println("depthOfInheritaceSum");
		System.out.println(depthOfInheritaceSum);
		System.out.println("weightedMethodsPerClassSum");
		System.out.println(weightedMethodsPerClassSum);
		System.out.println("measureOfAggregationSum");
		System.out.println(measureOfAggregationSum);
		System.out.println("averageNumberOfAncestorsSum");
		System.out.println(averageNumberOfAncestorsSum);
		System.out.println("directClassCouplingSum");
		System.out.println(directClassCouplingSum); */
	}	  
}