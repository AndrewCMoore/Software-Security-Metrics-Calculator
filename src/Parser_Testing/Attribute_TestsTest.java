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
		ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Attribute_Test.java");
		// Now we can run CAM values
		attributes = CAMValues.generateAttributeAST(cu);
		
	}
	
	
	@Test
	void testName() {
		assertEquals("AttributeA", attributes.get(0).getIdentifier());
	}
	
	@Test
	void testModifier() {
		assertEquals("Public ", attributes.get(0).getModifier());
	}

	
}
