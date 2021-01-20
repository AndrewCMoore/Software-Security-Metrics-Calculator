package Calculator;

import tree.JDTree;
import Calculator.*;

public class Calculator {
	
	private JDTree tree;
	private JDTree[] classes;
	
	public Calculator(JDTree tree) {
		this.tree = tree;
		this.classes = tree.getLeefs();
	}

	public void calculate() {
		AttributeMetrics attributes = new AttributeMetrics(classes);
		MethodMetrics methods = new MethodMetrics(classes);
		ComplexityMetrics statements = new ComplexityMetrics(classes);
		ClasseMetrics cm = new ClasseMetrics(classes);
		InheritanceMetrics im = new InheritanceMetrics(classes);

	}
}
