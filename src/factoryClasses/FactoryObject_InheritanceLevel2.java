package factoryClasses;

import pointless.pointlessClass;
import java.lang.reflect.Modifier;
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
		
		//https://www.youtube.com/watch?v=agnblS47F18
		Class reflectThisClass = FactoryObject_InheritanceLevel2.class;
		String className = reflectThisClass.getName();
		int classModifier = reflectThisClass.getModifiers(); //is this class public, is it abstract, final, interface, private, protected, static. strict, synchronized, volatile, etc.
		Class classSuper = reflectThisClass.getSuperclass();
		System.out.println("The name of this class is: "+className+"\nIs the Class modifier public?"+Modifier.isPublic(classModifier)+"\nDoes This Class Have a Super Class, What is it?"+classSuper);
		
		
	}
	
	private void setProtectedClassified(int p) {
		this.protectedClassified=p;
				
	}
	
	

}
