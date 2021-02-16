package Report_Generation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import Calculator.Calculator;

public class generateCSV {

	private Calculator calc; 
	private Set<String> classNames;
	private FileWriter csvWriter;
	public generateCSV(IProject project, Calculator calc) throws IOException {
		this.calc = calc;
		classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();

		createCSV(project);
		csvWriter.append("Quality Attribute, Design Principle,Primary Metric,Secondary Metric, Teriary Metric,");
		for(String s : 	classNames) {
			csvWriter.append(s + ",");
		}
		csvWriter.append("\n\n");
		classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();
		
		generateEffectiveness();
		generateExtendability();
		generateFlexibility();
		generateFunctionality();
		generateReusability();
		generateUnderstandability();

		csvWriter.flush();
		csvWriter.close();
	}

	private void createCSV(IProject project) {
		File file = new File(project.getLocation().toString(), project.getName() + "Report.csv");
		try {
			csvWriter = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//########################################################################################################### 
	// QUALITY ATTRIBUTES
	//###########################################################################################################
	
	private void generateEffectiveness() {
		try {
			// Quality Attribute Effectiveness
			csvWriter.append("Effectiveness,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getEffectiveness().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateAbstraction();
			generateComposition();
			generateEncapsulation();			
			generateInheritance();
			generatePolymorphism();
			
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateExtendability() {
		try {
			// Quality Attribute Extendability
			csvWriter.append("Extendability,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getExtendability().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateAbstraction();
			generateCoupling();
			generateInheritance();
			generatePolymorphism();
			
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateFlexibility() {
		try {
			// Quality Attribute Flexbility
			csvWriter.append("Flexbility,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getFlexibility().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateComposition();
			generateCoupling();
			generateEncapsulation();
			generatePolymorphism();
			
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateFunctionality() {
		try {
			// Quality Attribute Functionality
			csvWriter.append("Functionality,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getFunctionality().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateCohesion();	
			generateDesignSize();
			generateHierarchies();
			generateMessaging();
			generatePolymorphism();
			
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateReusability() {
		try {
			// Quality Attribute Reusability
			csvWriter.append("Reusability,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getReusability().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateCohesion();	
			generateCoupling();
			generateDesignSize();
			generateMessaging();
			
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateUnderstandability() {
		try {
			// Quality Attribute Understandability
			csvWriter.append("Understandability,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getUnderstandability().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateAbstraction();
			generateCohesion();	
			generateComplexity();
			generateCoupling();
			generateDesignSize();
			generateEncapsulation();
			generatePolymorphism();
			
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//########################################################################################################### 
	// DESIGN PRINCIPLES
	//###########################################################################################################
	
	private void generateAbstraction() {
		try {
			// Design Principle Abstraction
			csvWriter.append(",Abstraction,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getAbstraction().get(key)) + ",");
			}
			csvWriter.append("\n");
			
			generateANA();
			generateFSD();
			generateRAS();
			
			csvWriter.append("\n");

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateComplexity() {
		try {
			// Design Principle Complexity
			csvWriter.append(",Complexity,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getComplexity().get(key)) + ",");
			}
			csvWriter.append("\n");
			generateEOM();
			generateSWL();
			generateSLOC();
			generateWMC();
			generateNOM();
			generateNC();
			generateMC();
			generateDIT();
			generateCC();
			generateDCC();
			generateCR();
			generateCP();
			generateECC();
			generateNOC();
			generateSCC();

			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}	
	private void generateCohesion() {
		try {
			// Design Principle Cohesion
			csvWriter.append(",Cohesion,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getCohesion().get(key)) + ",");
			}
			csvWriter.append("\n");
			
			generateLCOM();
			generateCAM();

			csvWriter.append("\n");

		} catch(IOException e) {
			e.printStackTrace();
		}
	}	
	private void generateComposition() {
		try {
			// Design Principle Compoisiton
			csvWriter.append(",Compoisiton,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getComposition().get(key)) + ",");
			}
			csvWriter.append("\n");
			generateMOA();

			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateCoupling() {
		try {
			// Design Principle Coupling
			csvWriter.append(",Coupling,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getCoupling().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateCBC();
			generateCBO();
			generateCCP();
			generateDIT();
			generateDCC();
			generateFI();
			generateFO();
			generateHK();
			generateNOC();
			generateRFC();

			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateDesignSize() {
		try {
			// Design Principle Design Size
			csvWriter.append(",Design Size,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getDesignSize().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateDSC();

			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateEncapsulation() {
		try {
			// Design Principle Encapsulation
			csvWriter.append(",Encapsulation,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getEncapsulation().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateCER();
			generateDAM();
			generateGLP();
			generateISO();
			generateLCM();			

			csvWriter.append("\n");

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateHierarchies() {
		try {
			// Design Principle Hierarchies
			csvWriter.append(",Hierarchies,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getEncapsulation().get(key)) + ",");
			}
			csvWriter.append("\n");		

			generateNOH(); 

			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateInheritance() {
		try {
			// Design Principle Inheritance
			csvWriter.append(",Inheritance,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getInheritance().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateMFA();

			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generateMessaging() {
		try {
			// Design Principle Messaging
			csvWriter.append(",Messaging,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getMessaging().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateCIS();

			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	private void generatePolymorphism() {
		try {
			// Design Principle Polymorphism
			csvWriter.append(",Polymorphism,,,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getPolymorphism().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateNOP();

			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//########################################################################################################### 
	// PRIMARY METRICS
	//###########################################################################################################
	
	private void generateANA() {
		try {
			// Primary Metric Average Number of Ancestors
			csvWriter.append(",,Average Number of Ancestors (ANA),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateCAM() {
		try {
			// Primary Metric Cohesion Among Methods in Class (CAM)
			csvWriter.append(",,Cohesion Among Methods in Class (CAM),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getCohesionAmongMethodsInClass().get(key)) + ",");
			}
			csvWriter.append("\n\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateCBC() {
		try {
			// Primary Metric Count Of Base Classes (CBC)
			csvWriter.append(",,Count Of Base Classes (CBC),,,");
			for(String key: classNames) {
				// Int?
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getCountOfBaseClasses().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateCBO() {
		try {
			// Primary Metric Coupling Between Objects (CBO)
			csvWriter.append(",,Coupling Between Objects (CBO),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getCouplingBetweenObjects().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateCC() {
		try {
			// Primary Metric Cyclomatic Complexity (CC)
			csvWriter.append(",,Cyclomatic Complexity (CC),,,");
			for(String key: classNames) {
				// Not yet Implemeneted
				// csvWriter.append(String.valueOf(calc.getPrimaryMetrics().cyclomaticComplexity().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateCCP() {
		try {
			// Primary Metric Coupling Corruption Propagation (CCP)
			csvWriter.append(",,Coupling Corruption Propagation (CCP),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getCouplingCorruptionPropagation().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateCER() {
		try {
			// Primary Metric Critical Element Ratio (CER)
			csvWriter.append(",,Critical Element Ratio (CER),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().criticalElementRatio().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateCIS() {
		try {
			// Primary Metric Class Interface Size (CIS)
			csvWriter.append(",,Class Interface Size (CIS),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().classInterfaceSize().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateCP() {
		try {
			// Primary Metric Count Path (CP)
			csvWriter.append(",,Count Path (CP),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().countPath(null, null, null);.get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateCR() {
		try {
			// Primary Metric Comment Ratio (CR)
			csvWriter.append(",,Comment Ratio (CR),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getCommentRatio().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateDAM() {
		try {
			// Primary Metric Data Access Metric (DAM)
			csvWriter.append(",,Data Access Metric (DAM),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().dataAccessMetric().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	private void generateDCC() {
		try {
			// Primary Metric Direct Class Coupling (DCC)
			csvWriter.append(",,Direct Class Coupling (DCC),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().directClassCoupling().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateDIT() {
		try {
			// Primary Metric Depth Of Inheritance Tree (DIT)
			csvWriter.append(",,Depth Of Inheritance Tree (DIT),,,");
			for(String key: classNames) {
				// Not yet Implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().depthOfInheritanceTree().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateDSC() {
		try {
			// Primary Metric Design Size In Classes (DSC)
			csvWriter.append(",,Design Size In Classes (DSC),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().designSizeInClasses().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateECC() {
		try {
			// Primary Metric Essential Cyclomatic Complexity (ECC)
			csvWriter.append(",,Essential Cyclomatic Complexity (ECC),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().essentialCyclomaticComplexity().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateEOM() {
		try {
			// Primary Metric Economoy of Mechanism (EOM)
			csvWriter.append(",,Economoy of Mechanism (EOM),,,");
			for(String key: classNames) {
				// Int value?
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getEconomyOfMechanism().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateSAM();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateFI() {
		try {
			// Primary Metric Fan In (FI)
			csvWriter.append(",,Fan In (FI),,,");
			for(String key: classNames) {
				// Not yet imeplemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().fanIn().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateFO() {
		try {
			// Primary Metric Fan Out (FO)
			csvWriter.append(",,Fan Out (FO),,,");
			for(String key: classNames) {
				// Not yet imeplemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().fanOut().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateFSD() {
		try {
			// Primary Metric Fail Safe Defaults
			csvWriter.append(",,Fail Safe Defaults (FSD),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getFailSafeDefaults().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateRCA();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateGLP() {
		try {
			// Primary Metric Grant Least Privilege (GLP)
			csvWriter.append(",,Data Access Metric (DAM),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().grantLeastPrivilege().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateWCM();
			generateWCC();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateHK() {
		try {
			// Primary Metric Henry Kafura (HK)
			csvWriter.append(",,Henry Kafura (HK),,,");
			for(String key: classNames) {
				// Not yet implemented
				// csvWriter.append(String.valueOf(calc.getPrimaryMetrics().henryKafura().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateISO() {
		try {
			// Primary Metric Isolation (ISO)
			csvWriter.append(",,Isolation (ISO),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().isolation().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateWCM();
			generateWCC();
			generateRCC();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateLCM() {
		try {
			// Primary Metric Least Commom Mechanism (LCM)
			csvWriter.append(",,Least Commom Mechanism (LCM),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().leastCommomMechanism().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateWCC();
			generateRCC();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateLCOM() {
		try {
			// Primary Metric Lack of Cohesion of Methods (LCOM)
			csvWriter.append(",,Lack of Cohesion of Methods (LCOM),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getLackOfCohesionOfMethods().get(key)) + ",");
			}
			csvWriter.append("\n\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateMC() {
		try {
			// Primary Metric Modified Cyclomatic Complexity (MC)
			csvWriter.append(",,Modified Cyclomatic Complexity (MC),,,");
			for(String key: classNames) {
				// Not implemented yet
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics()().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateMFA() {
		try {
			// Primary Metric Measure Of Functional Abtraction (MFA)
			csvWriter.append(",,Measure Of Functional Abtraction (MFA),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getMeasureOfFunctionalAbtraction().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateMOA() {
		try {
			// Primary Metric Measure Of Aggregation (MOA)
			csvWriter.append(",,Measure Of Aggregation (MOA),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().measureOfAggregation().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private void generateNC() {
		try {
			// Primary Metric Nesting Complexity (NC)
			csvWriter.append(",,Nesting Complexity (NC),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getNestingComplexity().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateNOC() {
		try {
			// Primary Metric Number Of Children (NOC)
			csvWriter.append(",,Number Of Children (NOC),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getNumberOfChildren().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateNOH() {
		try {
			// Primary Metric Number of Hierarchies (NOH)
			csvWriter.append(",,Number of Hierarchies (NOH),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getNumberOfHierarchies().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateNOM() {
		try {
			// Primary Metric Number Of Methods (NOM)
			csvWriter.append(",,Number Of Methods (NOM),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getNumberOfMethods().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateNOP() {
		try {
			// Primary Metric Number Of Polymorphic Methods (NOP)
			csvWriter.append(",,Number Of Polymorphic Methods (NOP),,,");
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().numberOfPolymorphicMethods().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateRAS() {
		try {
			// Primary Metric Reduce Attack Surface (RAS)
			csvWriter.append(",,Reduce Attack Surface (RAS),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getReduceAttackSurface().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateRCA();
			generateRCM();
			generateRCC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateRFC() {
		try {
			// Primary Metric Responce Set For a Class (RFC)
			csvWriter.append(",,Responce Set For a Class (RFC),,,");
			for(String key: classNames) {
				// Not yet implemented
				// csvWriter.append(String.valueOf(calc.getPrimaryMetrics().responceSetForaClass().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateSWL() {
		try {
			// Primary Metric Secure Weakest Link (SWL)
			csvWriter.append(",,Secure Weakest Link (SWL),,,");
			for(String key: classNames) {
				// Not implemented yet
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateSAM();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateSLOC() {
		try {
			// Primary Metric Source Lines Of Code (SLOC)
			csvWriter.append(",,Source Lines Of Code (SLOC),,,");
			for(String key: classNames) {
				// Int value?
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getSourceLinesOfCode.get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateSCC() {
		try {
			// Primary Metric Strict Cyclomatic Complexity (SCC)
			csvWriter.append(",,Strict Cyclomatic Complexity (SCC),,,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getStrictCyclomaticComplexity().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void generateWMC() {
		try {
			// Primary Metric Weighted Methods per Class (WMC)
			csvWriter.append(",,Weighted Methods per Class (WMC),,,");
			for(String key: classNames) {
				// Not implemented yet
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//########################################################################################################### 
	// SECONDARY METRICS
	//###########################################################################################################
	
	private void generateRCA() {
		try {
			// Seconday Metric Readability of Classified Attributes (RCA)
			csvWriter.append(",,,Readability of Classified Attributes (RCA),,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getReadabilityOfClassifiedAttributes().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateCIDA();
			generateCCDA();
			generateCAI();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateRCC() {
		try {
			// Seconday Metric Readability of Critical Classes (RCC)
			csvWriter.append(",,,Readability of Critical Classes (RCC),,");
			for(String key: classNames) {
				//Float value???
				//csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getReadabilityOfCriticalClasses().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateRPB();
			generateCPCC();
			generateCCE();
			generateCDP();
			generateCSP();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateRCM() {
		try {
			// Seconday Metric Readability of Classifed Methods (RCM)
			csvWriter.append(",,,Readability of Classifed Methods (RCM),,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getReadabilityOfClassifiedMethods().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateCOA();
			generateCME();
			generateCMI();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateSAM() {
		try {
			// Seconday Metric Security Absolute Measurements (SAM)
			csvWriter.append(",,,Security Absolute Measurements (SAM),,");
			for(String key: classNames) {
				//int value???
				//csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getSecurityAbsoluteMeasurements().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateCMT();
			generateCCT();
			generateCAT();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	private void generateWCA() {
		try {
			// Seconday Metric Writability Of Classified Attributes (WCA)
			csvWriter.append(",,,Writability Of Classified Attributes (WCA),,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getWritabilityOfClassifiedAttributes().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateUACA();
			generateCAAI();
			generateCMAI();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateWCC() {
		try {
			// Seconday Metric Writability via Critical Classes (WCC)
			csvWriter.append(",,,Writability via Critical Classes (WCC),,");
			for(String key: classNames) {
				//float value???
				//csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getWritabilityOfCriticalClasses().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateCCC();
			generateCSCP();
			generateCSI();
			generateUCAC();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateWCM() {
		try {
			// Seconday Metric Writability Of Classified Methods (WCM)
			csvWriter.append(",,,Writability Of Classified Methods (WCM),,");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getWritabilityOfClassifiedMethods().get(key)) + ",");
			}
			csvWriter.append("\n");

			generateCAIW();
			generateCMW();
			generateCWMP();
			generateUCAM();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	//########################################################################################################### 
	// TERTIARY METRICS
	//###########################################################################################################
	
	private void generateCAAI() {
		try {
			// Tertiary Metric Classified Accessor Attribute Interactions (CAAI)
			csvWriter.append(",,,,Classified Accessor Attribute Interactions (CAAI),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedAccessorAttributeInteractions().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCAI() {
		try {
			// Tertiary Metric Classified Attributes Inheritance (CAI)
			csvWriter.append(",,,,Classified Attributes Inheritance (CAI),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedAttributesInheritance().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCAIW() {
		try {
			// Tertiary Metric Classified Attributes Interaction Weight (CAIW)
			csvWriter.append(",,,,Classified Attributes Interaction Weight (CAIW),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedAttributesInteractionWeight().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCAT() {
		try {
			// Tertiary Metric Classified Attributes Total (CAT)
			csvWriter.append(",,,,Classified Attributes Total (CAT),");
			for(String key: classNames) {
				//int??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedAttributesTotal().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCCC() {
		try {
			// Tertiary Metric Critical Classes Coupling (CCC)
			csvWriter.append(",,,,Critical Classes Coupling (CCC),");
			for(String key: classNames) {
				//float??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getCriticalClassesCoupling().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCCDA() {
		try {
			// Tertiary Metric Classified Class Data Accessibility (CCDA)
			csvWriter.append(",,,,Classified Class Data Accessibility (CCDA),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedClassDataAccessibility().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCCE() {
		try {
			// Tertiary Metric Critical Classes Extensibility (CCE)
			csvWriter.append(",,,,Critical Classes Extensibility (CCE),");
			for(String key: classNames) {
				//Float??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getCriticalClassesExtensibility().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCCT() {
		try {
			// Tertiary Metric Critical Classes Total (CCT)
			csvWriter.append(",,,,Critical Classes Total (CCT),");
			for(String key: classNames) {
				//int??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getCriticalClassesTotal().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCDP() {
		try {
			// Tertiary Metric Critical Design Proportion (CDP)
			csvWriter.append(",,,,Critical Design Proportion (CDP),");
			for(String key: classNames) {
				//Float??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getCriticalDesignProportion().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCIDA() {
		try {
			// Tertiary Metric Classified Instance Data Accessbility (CIDA)
			csvWriter.append(",,,Classified Instance Data Accessbility (CIDA),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedInstanceDataAccessibility().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCMAI() {
		try {
			// Tertiary Metric Classified Mutator Attribute Interactions (CMAI)
			csvWriter.append(",,,,Classified Mutator Attribute Interactions (CMAI),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedMutatorAttributeInteractions().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCME() {
		try {
			// Tertiary Metric Classified Methods Extensiblity (CME)
			csvWriter.append(",,,,Classified Methods Extensiblity (CME),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedMethodsExtensibility().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCMI() {
		try {
			// Tertiary Metric Classified Methods Inheritance  (CMI)
			csvWriter.append(",,,,Classified Methods Inheritance  (CMI),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedMethodsInheritance().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCMT() {
		try {
			// Tertiary Metric Classified Methods Total (CMT)
			csvWriter.append(",,.,Classified Methods Total (CMT),");
			for(String key: classNames) {
				//int??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedMethodsTotal().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCMW() {
		try {
			// Tertiary Metric Classified Methods Weight (CMW)
			csvWriter.append(",,,,Classified Methods Weight (CMW),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedMethodsWeight().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCOA() {
		try {
			// Tertiary Metric Classified Operation Accessiblity (COA)
			csvWriter.append(",,,,Classified Operation Accessiblity (COA),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedOperationAccessibility().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCPCC() {
		try {
			// Tertiary Metric Composite-Part Critical Classes (CPCC)
			csvWriter.append(",,,,Composite-Part Critical Classes (CPCC),");
			for(String key: classNames) {
				//Float??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getCompositePartCriticalClasses().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCSCP() {
		try {
			// Tertiary Metric Critical Serialized Classes Proportion (CSCP)
			csvWriter.append(",,,,Critical Serialized Classes Proportion (CSCP),");
			for(String key: classNames) {
				//float??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getCriticalSerializedClassesProportion().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCSI() {
		try {
			// Tertiary Metric Critical Superclasses Inheritance (CSI)
			csvWriter.append(",,,,Critical Superclasses Inheritance (CSI),");
			for(String key: classNames) {
				//float??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getCriticalSuperclassesInheritance().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCSP() {
		try {
			// Tertiary Metric Critical Superclasses Proportion (CSP)
			csvWriter.append(",,,,Critical Superclasses Proportion (CSP),");
			for(String key: classNames) {
				//Float??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getCriticalSuperclassesProportion().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateCWMP() {
		try {
			// Tertiary Metric Classified Writing Methods Proportion (CWMP)
			csvWriter.append(",,,,Classified Writing Methods Proportion (CWMP),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedWritingMethodsProportion().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateRPB() {
		try {
			// Tertiary Metric Reflection Package Boolean (RPB)
			csvWriter.append(",,,,Reflection Package Boolean (RPB),");
			for(String key: classNames) {
				// float???
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getReflectionPackageBoolean().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateUACA() {
		try {
			// Tertiary Metric Unaccessed Assigned Classified Attribute (UACA)
			csvWriter.append(",,,,Unaccessed Assigned Classified Attribute (UACA),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getUnaccessedAssignedClassifiedAttribute().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateUCAC() {
		try {
			// Tertiary Metric Unused Critical Accessor Class (UCAC)
			csvWriter.append(",,,,Unused Critical Accessor Class (UCAC),");
			for(String key: classNames) {
				//float??
				//csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getUnusedCriticalAccessorClass().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void generateUCAM() {
		try {
			// Tertiary Metric Uncalled Classified Accessor Method (UCAM)
			csvWriter.append(",,,,Uncalled Classified Accessor Method (UCAM),");
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getUncalledClassifiedAccessorMethod().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	

	
	



	

	
	


	
	
}

