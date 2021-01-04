package Calculator;

import tree.JDTree;

public class Calculator {
	
	private JDTree tree;
	
	public Calculator(JDTree tree) {
		this.tree = tree;
	}

	public void calculate() {
		AttributeMetrics attributes = new AttributeMetrics();
		attributes.numPublicInstanceAttributes(tree);
	}

}
