package hr.fer.zemris.java.hw06.crypto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilTest {

	@Test
	void hexToByteTest() {
		assertEquals("01ae22", Util.bytetohex(new byte[] {1, -82, 34}));
	}

	@Test
	void byteToHexTest() {
		byte[] expected = Util.hextobyte("01ae22");
		assertEquals(1, expected[0]);
		assertEquals(-82, expected[1]);
		assertEquals(34, expected[2]);
	}
	
	@Test
	void hexToByteOdd() {
		assertThrows(IllegalArgumentException.class, ()-> Util.hextobyte("01ae222"));
	}

}