package Calculator;

import tree.JDTree;

public class Calculator {
	
	private JDTree tree;
	private JDTree[] classes;
	
	public Calculator(JDTree tree) {
		this.tree = tree;
		this.classes = tree.getLeefs();
		
	}

	public void calculate() {
		AttributeMetrics attributes = new AttributeMetrics();
		MethodMetrics methods = new MethodMetrics();
		//attributes.numPublicInstanceAttributes(classes);
		attributes.numPrivateProtectedInstanceAttributes(classes);
		attributes.numPublicClassAttributes(classes);
		//attributes.numPrivateProtectedClassAttributes(classes);
		methods.numNonFinalPrivateProtectedMethods(classes);
	}

}
