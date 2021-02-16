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
		// Set variables
		this.calc = calc;
		classNames = calc.getMurgePulledValues().getNumberOfClassesInProject();

		// Create the csv file 
		createCSV(project);
		
		// Add headers
		csvWriter.append("Quality Attribute, Design Principle,Primary Metric,Secondary Metric, Teriary Metric,");
		for(String s : 	classNames) {
			// Add class names next to headers
			csvWriter.append(s + ",");
		}
		// Create a space between the values and the headers
		csvWriter.append("\n\n");
		
		// Generate Quality Attributes and all the subceeded values
		generateEffectiveness();
		generateExtendability();
		generateFlexibility();
		generateFunctionality();
		generateReusability();
		generateUnderstandability();

		// Flush and close to improve OS performance.
		csvWriter.flush();
		csvWriter.close();
	}

	/**
	 * Creates the csv file and associates it to the csvWriter
	 * @param project The IProject being analyzed
	 */
	private void createCSV(IProject project) {
		// Create a file in the project folder, with the name "Project Name Report.csv"
		File file = new File(project.getLocation().toString(), project.getName() + " Report.csv");
		
		// Try to remove program stopping errors
		try {
			// Create a FileWriter for the file
			csvWriter = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//########################################################################################################### 
	// QUALITY ATTRIBUTES
	//###########################################################################################################
	
	/**
	 * Generates the Effectiveness Quality Attribute and its subceeded values in the csv file.  
	 */
	private void generateEffectiveness() {
		try {
			// Quality Attribute Effectiveness
			csvWriter.append("Effectiveness,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getEffectiveness().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Design Principles
			generateAbstraction();
			generateComposition();
			generateEncapsulation();			
			generateInheritance();
			generatePolymorphism();
			// Create a space after the Quality Attribute in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Extendabiliy Quality Attribute and its subceeded values in the csv file.  
	 */
	private void generateExtendability() {
		try {
			// Quality Attribute Extendability
			csvWriter.append("Extendability,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getExtendability().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Design Principles
			generateAbstraction();
			generateCoupling();
			generateInheritance();
			generatePolymorphism();
			
			// Create a space after the Quality Attribute in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Flexibility Quality Attribute and its subceeded values in the csv file.  
	 */
	private void generateFlexibility() {
		try {
			// Quality Attribute Flexbility
			csvWriter.append("Flexbility,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getFlexibility().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Design Principles
			generateComposition();
			generateCoupling();
			generateEncapsulation();
			generatePolymorphism();
			
			// Create a space after the Quality Attribute in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Functionality Quality Attribute and its subceeded values in the csv file.  
	 */
	private void generateFunctionality() {
		try {
			// Quality Attribute Functionality
			csvWriter.append("Functionality,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getFunctionality().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Design Principles
			generateCohesion();	
			generateDesignSize();
			generateHierarchies();
			generateMessaging();
			generatePolymorphism();
			
			// Create a space after the Quality Attribute in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Reusability Quality Attribute and its subceeded values in the csv file.  
	 */
	private void generateReusability() {
		try {
			// Quality Attribute Reusability
			csvWriter.append("Reusability,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getReusability().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Design Principles
			generateCohesion();	
			generateCoupling();
			generateDesignSize();
			generateMessaging();
			
			// Create a space after the Quality Attribute in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Understandability Quality Attribute and its subceeded values in the csv file.  
	 */
	private void generateUnderstandability() {
		try {
			// Quality Attribute Understandability
			csvWriter.append("Understandability,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getQualityAttributes().getUnderstandability().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Design Principles
			generateAbstraction();
			generateCohesion();	
			generateComplexity();
			generateCoupling();
			generateDesignSize();
			generateEncapsulation();
			generatePolymorphism();
			
			// Create a space after the Quality Attribute in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//########################################################################################################### 
	// DESIGN PRINCIPLES
	//###########################################################################################################
	
	/**
	 * Generates the Abstraction Design Princple and its subceeded values in the csv file.
	 */
	private void generateAbstraction() {
		try {
			// Design Principle Abstraction
			csvWriter.append(",Abstraction,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getAbstraction().get(key)) + ",");
			}
			csvWriter.append("\n");
			
			// Generate the associated Primary Metrics
			generateANA();
			generateFSD();
			generateRAS();
			
			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Complexity Design Princple and its subceeded values in the csv file.
	 */
	private void generateComplexity() {
		try {
			// Design Principle Complexity
			csvWriter.append(",Complexity,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getComplexity().get(key)) + ",");
			}
			csvWriter.append("\n");
			
			// Generate the associated Primary Metrics
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

			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * Generates the Cohesion Design Princple and its subceeded values in the csv file.
	 */
	private void generateCohesion() {
		try {
			// Design Principle Cohesion
			csvWriter.append(",Cohesion,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getCohesion().get(key)) + ",");
			}
			csvWriter.append("\n");
			
			// Generate the associated Primary Metrics
			generateLCOM();
			generateCAM();

			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");

		} catch(IOException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * Generates the Composition Design Princple and its subceeded values in the csv file.
	 */
	private void generateComposition() {
		try {
			// Design Principle Compoisiton
			csvWriter.append(",Compoisiton,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getComposition().get(key)) + ",");
			}
			csvWriter.append("\n");
			
			// Generate the associated Primary Metrics
			generateMOA();

			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Coupling Design Princple and its subceeded values in the csv file.
	 */
	private void generateCoupling() {
		try {
			// Design Principle Coupling
			csvWriter.append(",Coupling,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getCoupling().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Primary Metrics
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
			
			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Design Size Design Princple and its subceeded values in the csv file.
	 */
	private void generateDesignSize() {
		try {
			// Design Principle Design Size
			csvWriter.append(",Design Size,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getDesignSize().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Primary Metrics
			generateDSC();

			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Encapsulation Design Princple and its subceeded values in the csv file.
	 */
	private void generateEncapsulation() {
		try {
			// Design Principle Encapsulation
			csvWriter.append(",Encapsulation,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getEncapsulation().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Primary Metrics
			generateCER();
			generateDAM();
			generateGLP();
			generateISO();
			generateLCM();			

			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Hierarchies Design Princple and its subceeded values in the csv file.
	 */
	private void generateHierarchies() {
		try {
			// Design Principle Hierarchies
			csvWriter.append(",Hierarchies,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getEncapsulation().get(key)) + ",");
			}
			csvWriter.append("\n");		

			// Generate the associated Primary Metrics
			generateNOH(); 

			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Inheritance Design Princple and its subceeded values in the csv file.
	 */
	private void generateInheritance() {
		try {
			// Design Principle Inheritance
			csvWriter.append(",Inheritance,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getInheritance().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Primary Metrics
			generateMFA();

			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Messaging Design Princple and its subceeded values in the csv file.
	 */
	private void generateMessaging() {
		try {
			// Design Principle Messaging
			csvWriter.append(",Messaging,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getMessaging().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Primary Metrics
			generateCIS();

			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Polymorphism Design Princple and its subceeded values in the csv file.
	 */
	private void generatePolymorphism() {
		try {
			// Design Principle Polymorphism
			csvWriter.append(",Polymorphism,,,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getDesignPrincipals().getPolymorphism().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Primary Metric
			generateNOP();

			// Create a space after the Design Principle in the csv
			csvWriter.append("\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//########################################################################################################### 
	// PRIMARY METRICS
	//###########################################################################################################
	
	/**
	 * Generates the Average Number of Ancestors (ANA) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateANA() {
		try {
			// Primary Metric Average Number of Ancestors (ANA)
			csvWriter.append(",,Average Number of Ancestors (ANA),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Cohesion Among Methods in Class (CAM) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateCAM() {
		try {
			// Primary Metric Cohesion Among Methods in Class (CAM)
			csvWriter.append(",,Cohesion Among Methods in Class (CAM),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getCohesionAmongMethodsInClass().get(key)) + ",");
			}
			csvWriter.append("\n\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Count Of Base Classes (CBC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateCBC() {
		try {
			// Primary Metric Count Of Base Classes (CBC)
			csvWriter.append(",,Count Of Base Classes (CBC),,,");
			// For each class in the project
			for(String key: classNames) {
				// Int?
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getCountOfBaseClasses().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Coupling Between Objects (CBO) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateCBO() {
		try {
			// Primary Metric Coupling Between Objects (CBO)
			csvWriter.append(",,Coupling Between Objects (CBO),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getCouplingBetweenObjects().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Cyclomatic Complexity (CC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateCC() {
		try {
			// Primary Metric Cyclomatic Complexity (CC)
			csvWriter.append(",,Cyclomatic Complexity (CC),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet Implemeneted
				// csvWriter.append(String.valueOf(calc.getPrimaryMetrics().cyclomaticComplexity().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Coupling Corruption Propagation (CCP) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateCCP() {
		try {
			// Primary Metric Coupling Corruption Propagation (CCP)
			csvWriter.append(",,Coupling Corruption Propagation (CCP),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getCouplingCorruptionPropagation().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Critical Element Ratio (CER) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateCER() {
		try {
			// Primary Metric Critical Element Ratio (CER)
			csvWriter.append(",,Critical Element Ratio (CER),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().criticalElementRatio().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Class Interface Size (CIS) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateCIS() {
		try {
			// Primary Metric Class Interface Size (CIS)
			csvWriter.append(",,Class Interface Size (CIS),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().classInterfaceSize().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Count Path (CP) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateCP() {
		try {
			// Primary Metric Count Path (CP)
			csvWriter.append(",,Count Path (CP),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().countPath(null, null, null);.get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Comment Ratio (CR) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateCR() {
		try {
			// Primary Metric Comment Ratio (CR)
			csvWriter.append(",,Comment Ratio (CR),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getCommentRatio().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Data Access Metric (DAM) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateDAM() {
		try {
			// Primary Metric Data Access Metric (DAM)
			csvWriter.append(",,Data Access Metric (DAM),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().dataAccessMetric().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * Generates the Direct Class Coupling (DCC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateDCC() {
		try {
			// Primary Metric Direct Class Coupling (DCC)
			csvWriter.append(",,Direct Class Coupling (DCC),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().directClassCoupling().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Depth Of Inheritance Tree (DIT) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateDIT() {
		try {
			// Primary Metric Depth Of Inheritance Tree (DIT)
			csvWriter.append(",,Depth Of Inheritance Tree (DIT),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet Implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().depthOfInheritanceTree().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Design Size In Classes (DSC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateDSC() {
		try {
			// Primary Metric Design Size In Classes (DSC)
			csvWriter.append(",,Design Size In Classes (DSC),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().designSizeInClasses().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Essential Cyclomatic Complexity (ECC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateECC() {
		try {
			// Primary Metric Essential Cyclomatic Complexity (ECC)
			csvWriter.append(",,Essential Cyclomatic Complexity (ECC),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().essentialCyclomaticComplexity().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Economoy of Mechanism (EOM) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateEOM() {
		try {
			// Primary Metric Economoy of Mechanism (EOM)
			csvWriter.append(",,Economoy of Mechanism (EOM),,,");
			// For each class in the project
			for(String key: classNames) {
				// Int value?
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getEconomyOfMechanism().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Secondary Metric
			generateSAM();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Fan In (FI) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateFI() {
		try {
			// Primary Metric Fan In (FI)
			csvWriter.append(",,Fan In (FI),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet imeplemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().fanIn().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Fan Out (FO) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateFO() {
		try {
			// Primary Metric Fan Out (FO)
			csvWriter.append(",,Fan Out (FO),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet imeplemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().fanOut().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Fail Safe Defaults (FSD) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateFSD() {
		try {
			// Primary Metric Fail Safe Defaults (FSD)
			csvWriter.append(",,Fail Safe Defaults (FSD),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getFailSafeDefaults().get(key)) + ",");
			}
			csvWriter.append("\n");
			
			// Generate the associated Secondary Metric
			generateRCA();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Grant Least Privilege (GLP) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateGLP() {
		try {
			// Primary Metric Grant Least Privilege (GLP)
			csvWriter.append(",,Data Access Metric (DAM),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().grantLeastPrivilege().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Secondary Metric
			generateWCM();
			generateWCC();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Henry Kafura (HK) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateHK() {
		try {
			// Primary Metric Henry Kafura (HK)
			csvWriter.append(",,Henry Kafura (HK),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				// csvWriter.append(String.valueOf(calc.getPrimaryMetrics().henryKafura().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Isolation (ISO) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateISO() {
		try {
			// Primary Metric Isolation (ISO)
			csvWriter.append(",,Isolation (ISO),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().isolation().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Secondary Metric
			generateWCM();
			generateWCC();
			generateRCC();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Least Commom Mechanism (LCM) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateLCM() {
		try {
			// Primary Metric Least Commom Mechanism (LCM)
			csvWriter.append(",,Least Commom Mechanism (LCM),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().leastCommomMechanism().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Secondary Metric
			generateWCC();
			generateRCC();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Lack of Cohesion of Methods (LCOM) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateLCOM() {
		try {
			// Primary Metric Lack of Cohesion of Methods (LCOM)
			csvWriter.append(",,Lack of Cohesion of Methods (LCOM),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getLackOfCohesionOfMethods().get(key)) + ",");
			}
			csvWriter.append("\n\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Modified Cyclomatic Complexity (MC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateMC() {
		try {
			// Primary Metric Modified Cyclomatic Complexity (MC)
			csvWriter.append(",,Modified Cyclomatic Complexity (MC),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not implemented yet
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics()().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Measure Of Functional Abtraction (MFA) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateMFA() {
		try {
			// Primary Metric Measure Of Functional Abtraction (MFA)
			csvWriter.append(",,Measure Of Functional Abtraction (MFA),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getMeasureOfFunctionalAbtraction().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Measure Of Aggregation (MOA) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateMOA() {
		try {
			// Primary Metric Measure Of Aggregation (MOA)
			csvWriter.append(",,Measure Of Aggregation (MOA),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().measureOfAggregation().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Generates the Nesting Complexity (NC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateNC() {
		try {
			// Primary Metric Nesting Complexity (NC)
			csvWriter.append(",,Nesting Complexity (NC),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getNestingComplexity().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Number Of Children (NOC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateNOC() {
		try {
			// Primary Metric Number Of Children (NOC)
			csvWriter.append(",,Number Of Children (NOC),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getNumberOfChildren().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Number of Hierarchies (NOH) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateNOH() {
		try {
			// Primary Metric Number of Hierarchies (NOH)
			csvWriter.append(",,Number of Hierarchies (NOH),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getNumberOfHierarchies().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Number Of Methods (NOM) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateNOM() {
		try {
			// Primary Metric Number Of Methods (NOM)
			csvWriter.append(",,Number Of Methods (NOM),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getNumberOfMethods().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Number Of Polymorphic Methods (NOP) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateNOP() {
		try {
			// Primary Metric Number Of Polymorphic Methods (NOP)
			csvWriter.append(",,Number Of Polymorphic Methods (NOP),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().numberOfPolymorphicMethods().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Reduce Attack Surface (RAS) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateRAS() {
		try {
			// Primary Metric Reduce Attack Surface (RAS)
			csvWriter.append(",,Reduce Attack Surface (RAS),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getReduceAttackSurface().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Secondary Metric
			generateRCA();
			generateRCM();
			generateRCC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Responce Set For a Class (RFC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateRFC() {
		try {
			// Primary Metric Responce Set For a Class (RFC)
			csvWriter.append(",,Responce Set For a Class (RFC),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not yet implemented
				// csvWriter.append(String.valueOf(calc.getPrimaryMetrics().responceSetForaClass().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Secure Weakest Link (SWL) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateSWL() {
		try {
			// Primary Metric Secure Weakest Link (SWL)
			csvWriter.append(",,Secure Weakest Link (SWL),,,");
			// For each class in the project
			for(String key: classNames) {
				// Not implemented yet
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Secondary Metric
			generateSAM();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Source Lines Of Code (SLOC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateSLOC() {
		try {
			// Primary Metric Source Lines Of Code (SLOC)
			csvWriter.append(",,Source Lines Of Code (SLOC),,,");
			// For each class in the project
			for(String key: classNames) {
				// Int value?
				//csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getSourceLinesOfCode.get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Strict Cyclomatic Complexity (SCC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateSCC() {
		try {
			// Primary Metric Strict Cyclomatic Complexity (SCC)
			csvWriter.append(",,Strict Cyclomatic Complexity (SCC),,,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getPrimaryMetrics().getStrictCyclomaticComplexity().get(key)) + ",");
			}
			csvWriter.append("\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Weighted Methods per Class (WMC) Primary Metric and its subceeded values in the csv file.
	 */
	private void generateWMC() {
		try {
			// Primary Metric Weighted Methods per Class (WMC)
			csvWriter.append(",,Weighted Methods per Class (WMC),,,");
			// For each class in the project
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
	
	/**
	 * Generates the Readability of Classified Attributes (RCA) Secondary Metric and its subceeded values in the csv file.
	 */
	private void generateRCA() {
		try {
			// Seconday Metric Readability of Classified Attributes (RCA)
			csvWriter.append(",,,Readability of Classified Attributes (RCA),,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getReadabilityOfClassifiedAttributes().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Tertiary Metrics
			generateCIDA();
			generateCCDA();
			generateCAI();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Readability of Critical Classes (RCC) Secondary Metric and its subceeded values in the csv file.
	 */
	private void generateRCC() {
		try {
			// Seconday Metric Readability of Critical Classes (RCC)
			csvWriter.append(",,,Readability of Critical Classes (RCC),,");
			// For each class in the project
			for(String key: classNames) {
				//Float value???
				//csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getReadabilityOfCriticalClasses().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Tertiary Metrics
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
	/**
	 * Generates the Readability of Classifed Methods (RCM) Secondary Metric and its subceeded values in the csv file.
	 */
	private void generateRCM() {
		try {
			// Seconday Metric Readability of Classifed Methods (RCM)
			csvWriter.append(",,,Readability of Classifed Methods (RCM),,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getReadabilityOfClassifiedMethods().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Tertiary Metrics
			generateCOA();
			generateCME();
			generateCMI();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Security Absolute Measurements (SAM) Secondary Metric and its subceeded values in the csv file.
	 */
	private void generateSAM() {
		try {
			// Seconday Metric Security Absolute Measurements (SAM)
			csvWriter.append(",,,Security Absolute Measurements (SAM),,");
			// For each class in the project
			for(String key: classNames) {
				//int value???
				//csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getSecurityAbsoluteMeasurements().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Tertiary Metrics
			generateCMT();
			generateCCT();
			generateCAT();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	/**
	 * Generates the Writability Of Classified Attributes (WCA) Secondary Metric and its subceeded values in the csv file.
	 */
	private void generateWCA() {
		try {
			// Seconday Metric Writability Of Classified Attributes (WCA)
			csvWriter.append(",,,Writability Of Classified Attributes (WCA),,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getWritabilityOfClassifiedAttributes().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Tertiary Metrics
			generateUACA();
			generateCAAI();
			generateCMAI();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Writability via Critical Classes (WCC) Secondary Metric and its subceeded values in the csv file.
	 */
	private void generateWCC() {
		try {
			// Seconday Metric Writability via Critical Classes (WCC)
			csvWriter.append(",,,Writability via Critical Classes (WCC),,");
			// For each class in the project
			for(String key: classNames) {
				//float value???
				//csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getWritabilityOfCriticalClasses().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Tertiary Metrics
			generateCCC();
			generateCSCP();
			generateCSI();
			generateUCAC();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Writability Of Classified Methods (WCM) Secondary Metric and its subceeded values in the csv file.
	 */
	private void generateWCM() {
		try {
			// Seconday Metric Writability Of Classified Methods (WCM)
			csvWriter.append(",,,Writability Of Classified Methods (WCM),,");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getSecondaryMetrics().getWritabilityOfClassifiedMethods().get(key)) + ",");
			}
			csvWriter.append("\n");

			// Generate the associated Tertiary Metrics
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
	
	/**
	 * Generates the Classified Accessor Attribute Interactions (CAAI) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCAAI() {
		try {
			// Tertiary Metric Classified Accessor Attribute Interactions (CAAI)
			csvWriter.append(",,,,Classified Accessor Attribute Interactions (CAAI),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedAccessorAttributeInteractions().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Classified Attributes Inheritance (CAI) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCAI() {
		try {
			// Tertiary Metric Classified Attributes Inheritance (CAI)
			csvWriter.append(",,,,Classified Attributes Inheritance (CAI),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedAttributesInheritance().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Classified Attributes Interaction Weight (CAIW) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCAIW() {
		try {
			// Tertiary Metric Classified Attributes Interaction Weight (CAIW)
			csvWriter.append(",,,,Classified Attributes Interaction Weight (CAIW),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedAttributesInteractionWeight().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Classified Attributes Total (CAT) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCAT() {
		try {
			// Tertiary Metric Classified Attributes Total (CAT)
			csvWriter.append(",,,,Classified Attributes Total (CAT),");
			// For each class in the project
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
	/**
	 * Generates the Critical Classes Coupling (CCC) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCCC() {
		try {
			// Tertiary Metric Critical Classes Coupling (CCC)
			csvWriter.append(",,,,Critical Classes Coupling (CCC),");
			// For each class in the project
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
	/**
	 * Generates the Classified Class Data Accessibility (CCDA) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCCDA() {
		try {
			// Tertiary Metric Classified Class Data Accessibility (CCDA)
			csvWriter.append(",,,,Classified Class Data Accessibility (CCDA),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedClassDataAccessibility().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Critical Classes Extensibility (CCE) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCCE() {
		try {
			// Tertiary Metric Critical Classes Extensibility (CCE)
			csvWriter.append(",,,,Critical Classes Extensibility (CCE),");
			// For each class in the project
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
	/**
	 * Generates the Critical Classes Total (CCT) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCCT() {
		try {
			// Tertiary Metric Critical Classes Total (CCT)
			csvWriter.append(",,,,Critical Classes Total (CCT),");
			// For each class in the project
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
	/**
	 * Generates the Critical Design Proportion (CDP) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCDP() {
		try {
			// Tertiary Metric Critical Design Proportion (CDP)
			csvWriter.append(",,,,Critical Design Proportion (CDP),");
			// For each class in the project
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
	/**
	 * Generates the Classified Instance Data Accessbility (CIDA) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCIDA() {
		try {
			// Tertiary Metric Classified Instance Data Accessbility (CIDA)
			csvWriter.append(",,,Classified Instance Data Accessbility (CIDA),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedInstanceDataAccessibility().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Classified Mutator Attribute Interactions (CMAI) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCMAI() {
		try {
			// Tertiary Metric Classified Mutator Attribute Interactions (CMAI)
			csvWriter.append(",,,,Classified Mutator Attribute Interactions (CMAI),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedMutatorAttributeInteractions().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Classified Methods Extensiblity (CME) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCME() {
		try {
			// Tertiary Metric Classified Methods Extensiblity (CME)
			csvWriter.append(",,,,Classified Methods Extensiblity (CME),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedMethodsExtensibility().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Classified Methods Inheritance  (CMI) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCMI() {
		try {
			// Tertiary Metric Classified Methods Inheritance  (CMI)
			csvWriter.append(",,,,Classified Methods Inheritance  (CMI),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedMethodsInheritance().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Classified Methods Total (CMT) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCMT() {
		try {
			// Tertiary Metric Classified Methods Total (CMT)
			csvWriter.append(",,.,Classified Methods Total (CMT),");
			// For each class in the project
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
	/**
	 * Generates the Classified Methods Weight (CMW) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCMW() {
		try {
			// Tertiary Metric Classified Methods Weight (CMW)
			csvWriter.append(",,,,Classified Methods Weight (CMW),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedMethodsWeight().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Classified Operation Accessiblity (COA) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCOA() {
		try {
			// Tertiary Metric Classified Operation Accessiblity (COA)
			csvWriter.append(",,,,Classified Operation Accessiblity (COA),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedOperationAccessibility().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Composite-Part Critical Classes (CPCC) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCPCC() {
		try {
			// Tertiary Metric Composite-Part Critical Classes (CPCC)
			csvWriter.append(",,,,Composite-Part Critical Classes (CPCC),");
			// For each class in the project
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
	/**
	 * Generates the Critical Serialized Classes Proportion (CSCP) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCSCP() {
		try {
			// Tertiary Metric Critical Serialized Classes Proportion (CSCP)
			csvWriter.append(",,,,Critical Serialized Classes Proportion (CSCP),");
			// For each class in the project
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
	/**
	 * Generates the Critical Superclasses Inheritance (CSI) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCSI() {
		try {
			// Tertiary Metric Critical Superclasses Inheritance (CSI)
			csvWriter.append(",,,,Critical Superclasses Inheritance (CSI),");
			// For each class in the project
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
	/**
	 * Generates the Critical Superclasses Proportion (CSP) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCSP() {
		try {
			// Tertiary Metric Critical Superclasses Proportion (CSP)
			csvWriter.append(",,,,Critical Superclasses Proportion (CSP),");
			// For each class in the project
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
	/**
	 * Generates the Classified Writing Methods Proportion (CWMP) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateCWMP() {
		try {
			// Tertiary Metric Classified Writing Methods Proportion (CWMP)
			csvWriter.append(",,,,Classified Writing Methods Proportion (CWMP),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getClassifiedWritingMethodsProportion().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Reflection Package Boolean (RPB) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateRPB() {
		try {
			// Tertiary Metric Reflection Package Boolean (RPB)
			csvWriter.append(",,,,Reflection Package Boolean (RPB),");
			// For each class in the project
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
	/**
	 * Generates the Unaccessed Assigned Classified Attribute (UACA) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateUACA() {
		try {
			// Tertiary Metric Unaccessed Assigned Classified Attribute (UACA)
			csvWriter.append(",,,,Unaccessed Assigned Classified Attribute (UACA),");
			// For each class in the project
			for(String key: classNames) {
				csvWriter.append(String.valueOf(calc.getTertiaryMetrics().getUnaccessedAssignedClassifiedAttribute().get(key)) + ",");
			}
			csvWriter.append("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Generates the Unused Critical Accessor Class (UCAC) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateUCAC() {
		try {
			// Tertiary Metric Unused Critical Accessor Class (UCAC)
			csvWriter.append(",,,,Unused Critical Accessor Class (UCAC),");
			// For each class in the project
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
	/**
	 * Generates the Uncalled Classified Accessor Method (UCAM) Tertiary Metric and its subceeded values in the csv file.
	 */
	private void generateUCAM() {
		try {
			// Tertiary Metric Uncalled Classified Accessor Method (UCAM)
			csvWriter.append(",,,,Uncalled Classified Accessor Method (UCAM),");
			// For each class in the project
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

