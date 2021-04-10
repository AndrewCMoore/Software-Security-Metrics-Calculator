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
	private MurgePulledValues mpv;
	private PulledValues pv;
	private TertiaryMetrics tm;
	private SecondaryMetrics sm;
	private PrimaryMetrics pm;
	private DesignPrincipals dp;
	private QualityAttributes qa;
	
	/**
	* The constructor takes the JDT tree, gets the 
	* leaf nodes of the tree which contains the class objects
	* and saves both as private attributes to be used in the 
	* metrics calculations
	*/
	public Calculator(JDTree tree) {
		this.tree = tree;
		this.classes = tree.getLeefs();
		System.out.println("LLLLLLLLLLLLLLLL" + tree + " : " + classes[0].getNode().toString() + classes[0].getClass());
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
		mpv = new MurgePulledValues(classes);
		pv = new PulledValues(classes);
		tm = new TertiaryMetrics(pv, mpv);
		sm = new SecondaryMetrics(tm);
		pm = new PrimaryMetrics(pv,sm,mpv);
		dp = new DesignPrincipals(pm);
		qa = new QualityAttributes(dp);
	}

	public MurgePulledValues getMurgePulledValues() {
		return mpv;
	}

	public PulledValues getPulledValues() {
		return pv;
	}

	public TertiaryMetrics getTertiaryMetrics() {
		return tm;
	}

	public SecondaryMetrics getSecondaryMetrics() {
		return sm;
	}

	public PrimaryMetrics getPrimaryMetrics() {
		return pm;
	}

	public DesignPrincipals getDesignPrincipals() {
		return dp;
	}

	public QualityAttributes getQualityAttributes() {
		return qa;
	}
	
	
	
	
}
