package Calculator_Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Calculator.CustomPair;

class CustomPairTest {

	static CustomPair testPair;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testPair = new CustomPair("Key", "Value");
	}

	@Test
	void testGetK() {
		assertEquals(testPair.getK(), "Key");
	}

	@Test
	void testGetV() {
		assertEquals(testPair.getV(), "Value");
	}

}
