package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SimpleHashtableTest {

	private static SimpleHashtable<String, Integer> defaultHashtable;
	private static SimpleHashtable customHashtable;
	
	@BeforeAll
	public static void setUpBeforeClass(){
		defaultHashtable = new SimpleHashtable<String, Integer>();
		customHashtable = new SimpleHashtable<String, Integer>(30);
		defaultHashtable.put("Ivana", 1);
		defaultHashtable.put("Ante", 2);
		defaultHashtable.put("Jasna", 3);
		defaultHashtable.put("Kristina", 4);
	}
	
	@Test
	void customAndDefaultSize() {
		assertEquals(32, customHashtable.tableCapacity());
		assertEquals(16, defaultHashtable.tableCapacity());
		
	}

	@Test
	void containsKeyTest(){
		assertTrue(defaultHashtable.containsKey("Ivana"));
		assertTrue(defaultHashtable.containsKey("Ante"));
		assertTrue(defaultHashtable.containsKey("Jasna"));
		assertTrue(defaultHashtable.containsKey("Kristina"));
		assertFalse(defaultHashtable.containsKey("Luka"));
		assertFalse(defaultHashtable.containsKey(null));	
	}
	
	@Test
	void containsValueTest(){
		defaultHashtable.put("Ante", 2);
		assertTrue(defaultHashtable.containsValue(2)); 
		assertFalse(defaultHashtable.containsValue(null));
		assertFalse(defaultHashtable.containsValue("kraj"));
	}
	
	@Test
	void sizeTest(){
		assertEquals(4, defaultHashtable.size());
		//Jasna already exists - size will not change
		defaultHashtable.put("Jasna", 4);
		assertEquals(4, defaultHashtable.size());
		defaultHashtable.remove("Jasna");
		assertEquals(3, defaultHashtable.size());
		defaultHashtable.remove("Ante");
		assertEquals(2, defaultHashtable.size());
	}
	
	@Test
	void removeTest(){
		//ante and jasna were removed earlier
		defaultHashtable.put("Ante", 2);
		defaultHashtable.put("Jasna", 3);
		assertEquals(4, defaultHashtable.size());
		defaultHashtable.remove("Jasna");
		assertEquals(3, defaultHashtable.size());
		defaultHashtable.remove("Ante");
		assertEquals(2, defaultHashtable.size());
	}
	
	@Test
	void isEmptyTest(){
		assertFalse(defaultHashtable.isEmpty());
		assertTrue(customHashtable.isEmpty());
	}
	
	@Test
	void getValueTest() {
		assertEquals(2, defaultHashtable.get("Ante"));
		assertEquals(null, defaultHashtable.get("zrinka"));
		assertEquals(null, defaultHashtable.get(2.17));
	}
}
