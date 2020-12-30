package Parser_Testing;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ssmc.CAMValues;
import ssmc.Attribute;

class AttributeTest {
	private ICompilationUnit iCompilationUnit;
	private ArrayList<Attribute> attributes;
	private Attribute attributeA, attributeB, attributeC;
	public AttributeTest() throws CoreException{
		MainTest mt = new MainTest();
		iCompilationUnit = mt.AccessTestClass().getCompilationUnit("Attribute_Test.java");
		// Now we can run CAM values
		attributes = CAMValues.generateAttributeAST(iCompilationUnit);
		attributeA = attributes.get(0);
		attributeB = attributes.get(1);
		attributeC = attributes.get(2);
	}
	
	
	@Test
	void testName() {
		assertEquals("AttributeA", attributeA.getIdentifier());
	}
	
	@Test
	void testModifier() {
		assertEquals("Public ", attributeA.getModifier());
	}
	
	@Test 
	void testGetUsage() {
		assertEquals(1, attributeA.getUsage());
	}
	
	@Test
	void testAddUsage() {
		attributeA.addUsage();
		assertEquals(2, attributeA.getUsage());
	}
	
	@Test 
	void testGetIdentifier() {
		assertEquals("AttributeA", attributeA.getIdentifier());
	}
	
	@Test
	void testSetIdentifier() {
		attributeA.setIdentifier("Changed Name");
		assertEquals("Changed Name", attributeA.getIdentifier());
		attributeA.setIdentifier("AttributeA");
	}
	
	@Test
	void testGetModifier() {
		assertEquals("Public ", attributeA.getModifier());
		assertEquals("Static Private ", attributeB.getModifier());
		assertEquals("Transient Volatile Protected ", attributeC.getModifier());
	}
	
	@Test
	void testSetModifierInt() {
		attributeA.setModifier(2);
		assertEquals("Private ", attributeA.getModifier());
		attributeA.setModifier(1);
	}
	
	@Test
	void testSetModifierString() {
		attributeA.setModifier("Private ");
		assertEquals("Private ", attributeA.getModifier());
		attributeA.setModifier("Public ");
	}
	
	@Test
	void testIsFinalized() {
		assertEquals(false, attributeA.isFinalized());
	}
	
	@Test
	void testSetFinalized() {
		attributeA.setFinalized(true);
		assertEquals(true, attributeA.isFinalized());
		attributeA.setFinalized(false);
	}

	@Test
	void testCompilationUnit() {
		CompilationUnit cu = attributeA.getCompilationUnit();
		CompilationUnit cuNew = CAMValues.parse(iCompilationUnit);
		attributeA.setCompilationUnit(cuNew);
		assertEquals(cuNew, attributeA.getCompilationUnit());
		attributeA.setCompilationUnit(cu);
	}
	
	@Test
	void testGetLinks() {
		assertEquals(0, attributeA.getLinks());
	}
	
	@Test
	void testSetLinks() {
		attributeA.setLinks(1);
		assertEquals(1, attributeA.getLinks());
		attributeA.setLinks(0);
	}
	
	@Test
	void testGetLineNum() {
		assertEquals(3, attributeA.getLineNum());
	}
	
	@Test
	void testSetLineNum() {
		attributeA.setLineNum(-1);
		assertEquals(-1, attributeA.getLineNum());
		attributeA.setLineNum(3);
	}
	
	@Test
	void testToString() {
		attributeA.toString();
	}
	
	
	
	

	
}
