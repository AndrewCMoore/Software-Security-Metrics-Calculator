package factoryClasses;

import pointless.pointlessClass;
import pointless.pointlessInterface;

public class FactoryObject_InheritanceLevel2 extends FactoryObject_InheritanceLevel1 implements pointlessInterface {

	private int privateClassified;
	protected int protectedClassified;
	pointlessClass pc = new pointlessClass();
	
	public FactoryObject_InheritanceLevel2(String ObjectName) {
		super(ObjectName);
		pointlessProtectedMethod();
		// TODO Auto-generated constructor stub
	}

	
	public void pointlessMethod() {
		System.out.println("I'm Also Pointless");
		pc.pointlessMethod();
		
	}
	
	

}
