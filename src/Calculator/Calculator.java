package Calculator;

import tree.JDTree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import Calculator.*;

/**
* The Calculator class is responsible for creating
* each necessary metrics object as well as calling 
* the appropriate methods for the operation of the 
* calculator module.
*
* @author  Paul Hewson, Anthony Maevski-Popov
*/
public class Calculator {
	
	private JDTree tree;
	private JDTree[] classes;
	
	/**
	* The constructor takes the JDT tree, gets the 
	* leaf nodes of the tree which contains the class objects
	* and saves both as private attributes to be used in the 
	* metrics calculations
	*/
	public Calculator(JDTree tree) {
		this.tree = tree;
		this.classes = tree.getLeefs();
	}

	/**
	* The calculate function creates the necessary objects and 
	* calls the appropriate methods for the calculator module
	*/
	public void calculate() {
		//AttributeMetrics attributes = new AttributeMetrics(classes);
		//MethodMetrics methods = new MethodMetrics(classes);
		//ClasseMetrics cm = new ClasseMetrics(classes);
		//ComplexityMetrics statements = new ComplexityMetrics(classes);
		//ClassMetrics cm = new ClasseMetrics(classes);
		//InheritanceMetrics im = new InheritanceMetrics(classes);
		MurgePulledValues mpv = new MurgePulledValues(classes);
		PulledValues pv = new PulledValues(classes);
		TertiaryMetrics tm = new TertiaryMetrics(pv, mpv);
		SecondaryMetrics sm = new SecondaryMetrics(tm);
		PrimaryMetrics pm = new PrimaryMetrics(pv,sm,mpv);
		DesignPrincipals dp = new DesignPrincipals(pm);
		QualityAttributes qa = new QualityAttributes(dp);
	}
}
