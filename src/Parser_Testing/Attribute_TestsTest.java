package Parser_Testing;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ssmc.CAMValues;
import ssmc.Attribute;

class Attribute_TestsTest {
	private ArrayList<Attribute> attributes;
	public Attribute_TestsTest() throws CoreException{
		MainTest mt = new MainTest();
		ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
		//ICompilationUnit cu = mt.getUnitByName(mt.AccessTestClass(),"Test_Class");
		// Now we can run CAM values
		System.out.println(cu);
		attributes = CAMValues.generateAttributeAST(cu);
		System.out.println();
	}
	
	
	@Test
	void testName() {
		if(attributes.size()>0) {
			assertEquals("attributeA", attributes.get(0).getIdentifier());
		}else {
			fail();
		}
	}
	
	@Test
	void testModifier() {
		assertEquals("Static ", attributes.get(0).getModifier());
	}

	
}
