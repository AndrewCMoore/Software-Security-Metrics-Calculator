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
	private Attribute attributeA, attributeB, attributeC;
	private ArrayList<Attribute> attributes;
	private ICompilationUnit iCompilationUnit;
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
	void testAddUsage() {
		assertEquals(1, attributeA.getUsage());
		attributeA.addUsage();
		assertEquals(2, attributeA.getUsage());
	}
	
	@Test
	void testGetCompilationUnit() {
		CompilationUnit cu = CAMValues.parse(this.iCompilationUnit);
		assertEquals(cu.getFlags(), attributeA.getCompilationUnit().getFlags());
		assertEquals(cu.getCommentList(), attributeA.getCompilationUnit().getCommentList());
		assertEquals(cu.getLength(), attributeA.getCompilationUnit().getLength());
		assertEquals(cu.getParent(), attributeA.getCompilationUnit().getParent());
	}
	
	@Test 
	void testGetIdentifier() {
		assertEquals("AttributeA", attributeA.getIdentifier());
	}
	
	@Test
	void testGetLineNum() {
		assertEquals(3, attributeA.getLineNum());
	}
	
	@Test
	void testGetLinks() {
		assertEquals(0, attributeA.getLinks());
	}
	
	@Test
	void testGetModifier() {
		assertEquals("[public]", attributeA.getModifier().toString());
		assertEquals("[private, static, final]", attributeB.getModifier().toString());
		assertEquals("[protected, volatile, transient]", attributeC.getModifier().toString());
	}
	
	@Test 
	void testGetUsage() {
		assertEquals(1, attributeA.getUsage());
	}
	
	@Test
	void testIsFinalized() {
		assertEquals(false, attributeA.isFinalized());
		assertEquals(true, attributeB.isFinalized());
	}
	
	@Test
	void testSetFinalized() {
		attributeA.setFinalized(true);
		assertEquals(true, attributeA.isFinalized());
		attributeA.setFinalized(false);
	}

	@Test
	void testSetIdentifier() {
		attributeA.setIdentifier("AttributeZ");
		assertEquals("AttributeZ", attributeA.getIdentifier());
		attributeA.setIdentifier("AttributeA");
	}
	
	@Test
	void testSetLineNum() {
		attributeA.setLineNum(-1);
		assertEquals(-1, attributeA.getLineNum());
		attributeA.setLineNum(3);
	}
	
	@Test
	void testSetLinks() {
		attributeA.setLinks(1);
		assertEquals(1, attributeA.getLinks());
		attributeA.setLinks(0);
	}
	
	@Test
	void testSetModifierInt() {
		attributeA.setModifier(2);
		assertEquals("[private]", attributeA.getModifier().toString());
		attributeA.setModifier(1);
	}
	@Test
	void testSetModifierArrayList() {
		ArrayList<String> arrayList = new ArrayList<String>();
		
		arrayList.add("private");
		
		attributeA.setModifier(arrayList);
		assertEquals("[private]", attributeA.getModifier().toString());
		
		arrayList.remove(0);
		arrayList.add("public");
		attributeA.setModifier(arrayList);
	}
	
	@Test
	void testSetUsage() {
		attributeA.setUsage(-1);
		assertEquals(-1, attributeA.getUsage());
		attributeA.setUsage(1);
	}
	
	@Test
	void testToString() {
		attributeA.toString();
	}
	
	
	
	

	
}
