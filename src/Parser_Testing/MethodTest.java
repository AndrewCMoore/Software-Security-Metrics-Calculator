package Parser_Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.Test;

import ssmc.CAMValues;
import ssmc.DeclarationVisitor;
import ssmc.Method;
import ssmc.Statement;

class MethodTest {
	private Method method;
	private CompilationUnit cu;
	MethodTest() throws CoreException{
		MainTest mt = new MainTest();
		ICompilationUnit unit = mt.AccessTestClass().getCompilationUnit("Test_Class.java");
		this.cu = CAMValues.parse(unit);
		
	}
	@Test
	void testMethod() {
		try {
			Method m = new Method(null, null);
		} catch (Exception e) {
		}
	}

	@Test
	void testAddStatement() {
		Method m = new Method(null, null);
		Statement s = new Statement(null, null);
		m.addStatement(s);
		
		assert(m.getStatements().contains(s));
	}

	@Test
	void testAddUsage() {
		Method m = new Method(null, null);
		assertEquals(0, m.getUsage());
	}

	@Test
	void testGetAccessible() {
		Method m = new Method(null, null);
		assertEquals(false, m.getAccessible());
	}

	@Test
	void testGetClassified() {
		Method m = new Method(null, null);
		assertEquals(false, m.getClassified());
	}

	@Test
	void testGetCompilationUnit() {
		Method m = new Method(null, null);
		assertEquals(null, m.getCompilationUnit());
	}

	@Test
	void testGetEndLine() {
		Method m = new Method(null, null);
		assertEquals(0, m.getEndLine());
	}

	@Test
	void testGetFinalized() {
		Method m = new Method(null, null);
		assertEquals(false, m.getFinalized());
	}

	@Test
	void testGetIdentifier() {
		Method m = new Method(null, null);
		assertEquals(null, m.getIdentifier());
	}

	@Test
	void testGetInherated() {
		Method m = new Method(null, null);
		assertEquals(false, m.getInherated());
	}

	@Test
	void testGetLinks() {
		Method m = new Method(null, null);
		assertEquals(0, m.getLinks());
	}

	@Test
	void testGetMethodLength() {
		Method m = new Method(null, null);
		assertEquals(0, m.getMethodLength());
	}

	@Test
	void testGetModifiers() {
		Method m = new Method(null, null);
		assertEquals("[]", m.getModifiers().toString());
	}

	@Test
	void testGetNumbeOfInputs() {
		Method m = new Method(null, null);
		assertEquals(0, m.getNumbeOfInputs());
	}

	@Test
	void testGetNumberOfOutputs() {
		Method m = new Method(null, null);
		assertEquals(0, m.getNumberOfOutputs());
	}

	@Test
	void testGetStartLine() {
		Method m = new Method(null, null);
		assertEquals(0, m.getStartLine());
	}

	@Test
	void testGetStatements() {
		Method m = new Method(null, null);
		Statement s = new Statement(null, null);
		m.addStatement(s);
		
		assert(m.getStatements().contains(s));
	}

	@Test
	void testGetUsage() {
		Method m = new Method(null, null);
		assertEquals(0, m.getUsage());
	}

	@Test
	void testGetWriteClassified() {
		Method m = new Method(null, null);
		assertEquals(false, m.getWriteClassified());
	}

	@Test
	void testSetAccessible() {
		Method m = new Method(null, null);
		assertEquals(m.getAccessible(), false);
		m.setAccessible(true);
		assertEquals(m.getAccessible(), true);
	}

	@Test
	void testSetClassified() {
		Method m = new Method(null, null);
		assertEquals(m.getClassified(), false);
		m.setClassified(true);
		assertEquals(m.getClassified(), true);
	}

	@Test
	void testSetEndLine() {
		Method m = new Method(null, null);
		assertEquals(m.getEndLine(), 0);
		m.setEndLine(12);
		assertEquals(m.getEndLine(), 12);
	}

	@Test
	void testSetFinalized() {
		Method m = new Method(null, null);
		assertEquals(m.getFinalized(), false);
		m.setFinalized(true);
		assertEquals(m.getFinalized(), true);
	}

	@Test
	void testSetInherated() {
		Method m = new Method(null, null);
		assertEquals(m.getInherated(), false);
		m.setInherated(true);
		assertEquals(m.getInherated(), true);
	}

	@Test
	void testSetLinks() {
		Method m = new Method(null, null);
		assertEquals(m.getLinks(), 0);
		m.setLinks(4);
		assertEquals(m.getLinks(), 4);
	}

	@Test
	void testSetMethodLength() {
		Method m = new Method(null, null);
		assertEquals(m.getMethodLength(), 0);
		m.setMethodLength(13);
		assertEquals(m.getMethodLength(), 13);
	}

	@Test
	void testSetModifiers() {
		Method m = new Method(null, null);
		assertEquals(m.getAccessible(), false);
		m.setAccessible(true);
		assertEquals(m.getAccessible(), true);
	}

	@Test
	void testSetNumbeOfInputs() {
		Method m = new Method(null, null);
		assertEquals(m.getNumbeOfInputs(), 0);
		m.setNumbeOfInputs(6);
		assertEquals(m.getNumbeOfInputs(), 6);
	}

	@Test
	void testSetNumberOfOutputs() {
		Method m = new Method(null, null);
		assertEquals(m.getNumberOfOutputs(), 0);
		m.setNumberOfOutputs(9);
		assertEquals(m.getNumberOfOutputs(), 9);
	}

	@Test
	void testSetStartLine() {
		Method m = new Method(null, null);
		assertEquals(m.getStartLine(), 0);
		m.setStartLine(1);
		assertEquals(m.getStartLine(), 1);
	}

	@Test
	void testSetWriteClassified() {
		Method m = new Method(null, null);
		assertEquals(m.getWriteClassified(), false);
		m.setWriteClassified(true);
		assertEquals(m.getWriteClassified(), true);
	}

	@Test
	void testGetNumOfStatements() {
		Method m = new Method(null, null);
		assertEquals(m.getNumOfStatements().toString(), "{Switch=0, Catch=0, For=0, Do=0, While=0, If=0}");
		Statement s = new Statement(null, null);
		Statement s1 = new Statement(null, null);
		Statement s2 = new Statement(null, null);
		
		m.addStatement(s);
		m.addStatement(s1);
		m.addStatement(s2);
	
		assertEquals(m.getNumOfStatements().toString(), "{Switch=0, Catch=0, For=0, Do=0, While=0, If=0}");
	}

	@Test
	void testGetMethodComplexity() {
		Method m = new Method(null, null);
		assertEquals(m.getMethodComplexity(), 1);
	}

}
