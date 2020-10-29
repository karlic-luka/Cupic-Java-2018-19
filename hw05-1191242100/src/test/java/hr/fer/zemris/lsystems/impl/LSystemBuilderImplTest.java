package hr.fer.zemris.lsystems.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import hr.fer.zemris.lsystems.LSystem;

class LSystemBuilderImplTest {

	@Test
	void testGenerate() {
		LSystemBuilderImpl builder = new LSystemBuilderImpl();
		
		builder.setAxiom("F");
		builder.registerProduction('F', "F+F--F+F");
		assertEquals("F", builder.build().generate(0));
		assertEquals("F+F--F+F", builder.build().generate(1));
		assertEquals("F+F--F+F+F+F--F+F--F+F--F+F+F+F--F+F", builder.build().generate(2));
	}

}
