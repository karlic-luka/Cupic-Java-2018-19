package hr.fer.zemris.java.hw01;
import static org.junit.jupiter.api.Assertions.*;
import static hr.fer.zemris.java.hw01.Factorial.*;
import org.junit.jupiter.api.Test;

public class FactorialTest {

	@Test
	public void nulaFaktorijela() {
		assertEquals(1, izracunajFaktorijele(0));
	}
	

	@Test
	public void negativniFaktorijeli() {
		assertThrows(IllegalArgumentException.class, ()-> {
			izracunajFaktorijele(-1);
		});
	}
	
	@Test
	public void prevelikiFaktorijeli() {
		assertThrows(IllegalArgumentException.class, ()-> {
			izracunajFaktorijele(21);
		});
	}
	
	@Test
	public void faktorijeliIzRaspona() {
		assertEquals(24, izracunajFaktorijele(4));
	}
	
	@Test
	public void dvadesetFaktorijela() {
		assertEquals(2432902008176640000L, izracunajFaktorijele(20));
	}
}
