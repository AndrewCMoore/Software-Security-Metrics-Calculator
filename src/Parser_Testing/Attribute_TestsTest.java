package Parser_Testing;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.junit.jupiter.api.Test;

class Attribute_TestsTest {

	public Attribute_TestsTest() throws CoreException{
		MainTest mt = new MainTest();
		ICompilationUnit cu = mt.AccessTestClass().getCompilationUnit("Attribute_Test");
		// Now we can run CAM values
	}
	
	
	@Test
	void test() {
		
	}

	
}
