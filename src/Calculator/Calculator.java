package Calculator;

import tree.JDTree;

public class Calculator {
	
	private JDTree tree;
	
	public Calculator(JDTree myTree) {
		// TODO Auto-generated constructor stub
	}

	public void calculate() {
		AttributeMetrics attributes = new AttributeMetrics();
		attributes.numPublicInstanceAttributes(tree);
	}

}
