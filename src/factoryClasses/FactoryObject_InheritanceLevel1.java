package factoryClasses;

import java.io.Serializable;

import pointless.pointlessClass;

public class FactoryObject_InheritanceLevel1  extends FactoryObject {
	
	pointlessClass pc = new pointlessClass();

	public FactoryObject_InheritanceLevel1(String ObjectName) {
		super(ObjectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String printSimulationResults() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateServiceTimeDuration(Double interval) {
		// TODO Auto-generated method stub
		
	}

	protected void pointlessProtectedMethod() {
		System.out.println("I'm Also Pointless but protected");
		pc.pointlessMethod();
	}
}
