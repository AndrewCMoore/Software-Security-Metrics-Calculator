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
		//AttributeMetrics attributes = new AttributeMetrics();
		//attributes.numPublicInstanceAttributes(tree);
		
		ClasseMetrics cm = new ClasseMetrics(classes);
		//InheritanceMetrics im = new InheritanceMetrics(classes);
	}

}
