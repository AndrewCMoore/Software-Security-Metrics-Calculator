package PerformanceTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.Test;

import Calculator.Calculator;
import ssmc.CAMValues;
import tree.JDTree;

/**
 * @author AndrewCMoore
 */
public class PerformanceTest {

	HashMap<String, Long> timeValues;
	HashMap<String, Integer> lengthValues;
	HashMap<String, Long> startValues;
	HashMap<String, Long> parserValues;
	HashMap<String, Long> calcValues;
	HashMap<String, Long> memoryValues;
	
	/*
	 * This class is a test class that runs when the file is run as a JUnit-plugin 
	 * test in order to utilize the functionality of the headless JDT library.
	 */
	public PerformanceTest() {
		// Initialize HashMap values
		timeValues = new HashMap<String, Long>();
		startValues = new HashMap<String, Long>();
		lengthValues = new HashMap<String, Integer>();
		parserValues = new HashMap<String, Long>();
		calcValues = new HashMap<String, Long>();
		memoryValues = new HashMap<String, Long>();
		
		// Intialize variables
		float systemAverage = 0;
		int projectsRun = 0;
		//IProject[] projects = null;
		IProject[] projects = new IProject[1];
		IPath path = null;
		
		
		// Try catch to remove errors in projects
		try {
			//Gets the root directory of the workspace
			IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
			// Get the array of projects within the workspace
			//projects[0] = fileRoot.getProject("SSMC");
			//projects[0] = fileRoot.getProject("ReactiveX");
			projects[0] = fileRoot.getProject("facebook-android-sdk");
			//projects[2] = fileRoot.getProject("twitter-kit-android");
			
			// For each project in the workspace
			for(IProject project : projects) {
				System.out.println("PROJECT: " + project.getName());
				
				try { 
					// Get the number of lines of code in the project 
					int linesOfCode = getNumberOfLinesInProject(project);
					// Start the timer for performace
					long startUpTime = 0;
					long projectStartTime = 0;
					long parserStopTime = 0;
					long calculatorStopTime = 0;
					long memory = 0;
					// Run the SSMC for the project
				
					if(project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
						projectStartTime = System.nanoTime();
						IJavaProject javaProject = JavaCore.create(project);
						String kind = project.getClass().getName();
						
						//Build a tree out of the project
						JDTree myTree = new JDTree(javaProject, null);
						// Stop timer -> parser timing 
						parserStopTime = System.nanoTime();
						// Calculator timer 
						//pass the tree to the calculator
						Calculator calc = new Calculator(myTree);
						//start calculating metrics
						calc.calculate();
						calculatorStopTime = System.nanoTime();
						// Check memory for Calculator module 
						memory = 1;
						startUpTime = System.nanoTime() - calculatorStopTime;
					}
					// End the timer for performace
					long projectEndTime = System.nanoTime();
					// Calculate performace time
					long time = projectEndTime - projectStartTime;
			
					// Put values into Hashmap data structures
					timeValues.put(project.getName(), time);
					lengthValues.put(project.getName(), linesOfCode);
					startValues.put(project.getName(), startUpTime);
					parserValues.put(project.getName(), parserStopTime - projectStartTime);
					calcValues.put(project.getName(), calculatorStopTime - parserStopTime);
					memoryValues.put(project.getName(), memory);
					
					
					// Just incase a program is empty
					if(time > 0) {
						float average = (float) ((float) linesOfCode / (float) time * 60000000000.00);
						//System.out.println("The project: " + project.getName() + "had a runtime of " + time + "\n With " + linesOfCode + " lines of code for an average of: " + "\n " + average + " lines of code per minute");
					}
				}	catch (Exception e) {
					e.printStackTrace();
				}
			} 
			Runtime.getRuntime().gc();
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}
		
   		try {
   			// Generate the CSV file to observe the data
			generateCSV();
		} catch (IOException e) {
			e.printStackTrace();
		}   
   		
   		//System.out.println("LENGTH: " + Arrays.toString(projects));
	}
	
	/**
	 * This method returns the total number of lines 
	 * in the project by getting the number of lines
	 * for each ICompilationUnit in the IProject
	 * @param project IProject
	 * @return int Number of lines of code in the Project
	 * @throws JavaModelException For when a Project is not Java-based, ignore
	 */
	public static int getNumberOfLinesInProject(IProject project) throws JavaModelException {
		// Intialize variable to return
		int linesOfCode = 0;
		// Get the JavaProject object
		IJavaProject javaProject = JavaCore.create(project); 
		
		IPackageFragmentRoot[] folders = javaProject.getPackageFragmentRoots();
		
		for (int i = 0; i < folders.length; i++) {
			// Get the type of folder
			String childKind = folders[i].getClass().getName();
			
			// Remove JAR folders by only getting PackageFragementRoots
			if(childKind.equals("org.eclipse.jdt.internal.core.PackageFragmentRoot")) {
				// Get all of the package within the root 
				IJavaElement[] packages = folders[i].getChildren();
				for(IJavaElement aPackage : packages) {
					if(aPackage.getClass().getName().equals("org.eclipse.jdt.internal.core.PackageFragment")) {
						IPackageFragment pf = (IPackageFragment) aPackage;
						for(ICompilationUnit aClass : pf.getCompilationUnits()) {
							// Parse into a compilationUnit to access values
							CompilationUnit unit = CAMValues.parse(aClass);
							// Add to the total number of lines of code by getting the 
							// line number last line of code 
							linesOfCode += unit.getLineNumber(unit.getLength() - 1);
						} 
					}
				}
			}
		}
					/*
					for(ICompilationUnit aClass : aPackage.getCompilationUnits()) {
						// Parse into a compilationUnit to access values
						CompilationUnit unit = CAMValues.parse(aClass);
						// Add to the total number of lines of code by getting the 
						// line number last line of code 
						linesOfCode += unit.getLineNumber(unit.getLength() - 1);
					} 
					*/
		
		/*
		// Parse by packages
		IPackageFragment[] packages = javaProject.getPackageFragments(); 
		// For each package in the Java project
		for(IPackageFragment aPackage : packages){ 
			// If the folder is a source folder
			//if(aPackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				// Get the ICompilationUnit (class)
				for(ICompilationUnit aClass : aPackage.getCompilationUnits()) {
					// Parse into a compilationUnit to access values
					CompilationUnit unit = CAMValues.parse(aClass);
					// Add to the total number of lines of code by getting the 
					// line number last line of code 
					linesOfCode += unit.getLineNumber(unit.getLength() - 1);
				}
			//}
		}*/
		// Return value
		return linesOfCode;
	}

	
	void generateCSV() throws IOException {
	
		// Intitialize local variables
		Long compileTotal = (long) 0;
		Integer linesTotal = 0;
		
		// Create a CSV file 
		FileWriter csvWriter = new FileWriter("Performance.csv");
		
		// Title Lines: Project, CompileTime, Lines of Code
		csvWriter.append("Project");
		csvWriter.append(",");
		csvWriter.append("Compile Time");
		csvWriter.append(",");
		csvWriter.append("Compile Time: StartUp");
		csvWriter.append(",");
		csvWriter.append("Compile Time: Parser");
		csvWriter.append(",");
		csvWriter.append("Compile Time: Calculator");
		csvWriter.append(",");
		csvWriter.append("Lines of Code");
		csvWriter.append(",");
		csvWriter.append("Lines of Code per Minute");
		csvWriter.append(",");
		csvWriter.append("Memory Usage");
		csvWriter.append("\n");

		// For each Project visited
		for(String key : timeValues.keySet()) {

			// Project name
			csvWriter.append(key);
			csvWriter.append(",");
			// Compile Time
			csvWriter.append(timeValues.get(key).toString());
			csvWriter.append(",");
			// Startup Compile Time
			csvWriter.append(startValues.get(key).toString());
			csvWriter.append(",");
			// Parser Compile Time
			csvWriter.append(parserValues.get(key).toString());
			csvWriter.append(",");
			// Calculator Compile Time
			csvWriter.append(calcValues.get(key).toString());
			csvWriter.append(",");
			// Lines of Code
			csvWriter.append(lengthValues.get(key).toString());
			csvWriter.append(",");
			// Lines of Code per Minute
			float average = (float) (lengthValues.get(key) / timeValues.get(key).floatValue() * 60000000000.00);
			csvWriter.append("" + average);
			csvWriter.append(",");
			// Memory Usage 
			csvWriter.append(memoryValues.get(key).toString());
			csvWriter.append(",");
	
			csvWriter.append("\n");
			
			// Add to values
			compileTotal += timeValues.get(key);
			linesTotal += lengthValues.get(key);
			
		}
		
		float average =  (float) (linesTotal / compileTotal.floatValue()  * 60000000000.00);

		//System.out.print(average + " " + compileTotal + " " + linesTotal);
		// Data Consolidation
		csvWriter.append("Totals: ");
		csvWriter.append(",");
		csvWriter.append(compileTotal.toString());
		csvWriter.append(",");
		csvWriter.append(linesTotal.toString());
		csvWriter.append(",,,");
		csvWriter.append("Average Lines/Minute: " + average);
		csvWriter.append("\n");
		
		

		csvWriter.flush();
		csvWriter.close();
	}
	
	public static long memoryUsage() {
		final long MEGABYTE = 1024L * 1024L;
		Runtime runtime = Runtime.getRuntime();
		// Run Garbage Collector
		runtime.gc();
		// Calculate used memory
		long memory = (runtime.totalMemory() - runtime.freeMemory()) / MEGABYTE;
		//System.out.println("bytes: " + memory/MEGABYTE);
		
		return memory;
	}
	
	@Test
	void test() {}
	

}