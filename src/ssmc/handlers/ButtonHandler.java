package ssmc.handlers;

import java.io.IOException;
import java.util.Scanner;

import org.eclipse.core.commands.AbstractHandler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import Calculator.Calculator;
import Report_Generation.GenerateHTML;
import Report_Generation.GenerateStyles;
import Report_Generation.generateCSV;
import tree.JDTree;
import PerformanceTesting.PerformanceTest;

import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * 
 * @author Andrew
 *
 */  
public class ButtonHandler extends AbstractHandler {
	private int LOC;
	long parserTime = 0;
	long calcTime = 0;
	long reportTime = 0;
	private String projectName;
	
	public static void main(String[] args) throws JavaModelException, CoreException {
		// Create scanner item for user input into the kernal 
		Scanner scanner = new Scanner(System.in);
		// Get Project name, parse from there
		System.out.println("Please enter the name of your Java Project");
		IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = fileRoot.getProject(scanner.nextLine());
		if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
			// got the IJavaProject which is the format we need
			IJavaProject javaProject = JavaCore.create(project);
			String kind = project.getClass().getName();
			//Build a tree out of the project
			JDTree myTree = new JDTree(javaProject, null);
			//pass the tree to the calculator
			Calculator calc = new Calculator(myTree);
			//start calculating metrics
			calc.calculate();
		}
	}
	/**
	 * responds to the button being pushed
	 * Isn't meant to be called other than by the button push
	 * 
	 * @param event this is the event that is created by pushing a button   
	 * @return returns null
	 * @throws ExecutionException
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		long startTime = 0;
		long memBefore = 0;
		
		try {
			memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
			startTime = System.nanoTime();
			addToTree();
		} catch (Exception e) {
			System.out.println("Select a Project, Package or Class");
			e.printStackTrace();
		}
		long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endTime = System.nanoTime();
		System.out.println("Project Analysis Complete");
		System.out.println(endTime - startTime);
		System.out.println("LOC: " + LOC);
		float LOCPM = (float) (((float) LOC) * 60000000000.00 / ((float)(endTime - startTime)));
		System.out.println("Lines per Minute: " + LOCPM);
		//long memory = PerformanceTest.memoryUsage();
		
		long MEGABYTE = 1024L * 1024L;
		long memory = (memAfter - memBefore) / MEGABYTE;
		
		System.out.println("Memory: " + memory);
		
		System.out.println((endTime-startTime) + "\t" + LOCPM + "\t" + memory);
		System.out.println((long) ((parserTime-startTime)*100/(endTime-startTime)) + "\t" + ((long)(calcTime-parserTime)*100/(endTime-startTime)) + "\t" + ((long) (reportTime-calcTime)*100/(endTime-startTime)));
		return null;
	}
	
	/**
	 * Takes the active Workspace and a selection in the workspace 
	 * if the selection is or is part of a java project then it will 
	 * create a tree out of the project then call the calculator module
	 * 
	 * @throws JavaModelException
	 * @throws CoreException
	 * @throws IOException 
	 */
	public void addToTree() throws JavaModelException, CoreException, IOException {
		JDTree.startThreads = Thread.activeCount();
		IProject project = null;
		IPath path = null;
		//Gets the root directory of the workspace
   		IWorkspaceRoot fileRoot = ResourcesPlugin.getWorkspace().getRoot();
		// Gets the active window
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window != null) {
			//gets the selection using the window
			IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
			if (selection instanceof IStructuredSelection) {
				Object element = ((IStructuredSelection) selection).getFirstElement();
				
				if (element instanceof IResource) {
					//gets the IProject that the selection is part of
					project = ((IResource) element).getProject();
				} else if (element instanceof IJavaElement) {
					//Converts it to a better format
					IJavaProject jProject = ((IJavaElement) element).getJavaProject();
					project = jProject.getProject();			
				}
			}
		}
		
		path = project.getFullPath();
		project = fileRoot.getProject(path.toOSString());
		if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
			// got the IJavaProject which is the format we need
			projectName = project.getName();
			IJavaProject javaProject = JavaCore.create(project);
			String kind = project.getClass().getName();
			//Build a tree out of the project
			JDTree myTree = new JDTree(javaProject, null);
			parserTime = System.nanoTime();
			//pass the tree to the calculator
			Calculator calc = new Calculator(myTree);
			calcTime = System.nanoTime();
			//start calculating metrics
			calc.calculate();
			
			try {			
				generateCSV CSV = new generateCSV(project, calc);
				GenerateHTML generator = new GenerateHTML(calc);
				GenerateStyles css = new GenerateStyles();
				reportTime = System.nanoTime();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.LOC = PerformanceTest.getNumberOfLinesInProject(project);
		
		
		// The JDTree class takes over from here

		/*
		 * Depreciated Leave for potential further use
		 * 
		 * if(project.isNatureEnabled("org.eclipse.jdt.core.javanature")) { IJavaProject
		 * javaProject = JavaCore.create(project); IPackageFragment[] packages =
		 * javaProject.getPackageFragments(); for(IPackageFragment aPackage : packages)
		 * { if(aPackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
		 * for(ICompilationUnit aClass : aPackage.getCompilationUnits()) {
		 * 
		 * // aClass iterates through all of the ICompilation Units
		 * //generateAttributeAST(aClass); //generateStatementAST(aClass); /*
		 * generateMethodAST(aClass); generateAttributeAST(aClass);
		 * generateCommentAST(aClass); generateStatementAST(aClass);
		 * //getClasses(aClass); } } } }
		 */

	}
	public String getProjectName() {
		return projectName;
	}
}
