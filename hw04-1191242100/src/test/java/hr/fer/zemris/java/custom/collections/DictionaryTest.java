package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DictionaryTest {
	
	@Test
	void sizeTest(){
		Dictionary<String, Integer> defaultDictionary = new Dictionary<String, Integer>();
		defaultDictionary.put("Ivana", 1);
		defaultDictionary.put("Ante", 2);
		defaultDictionary.put("Jasna", 3);
		defaultDictionary.put("Kristina", 4);
		assertEquals(4, defaultDictionary.size());
	}
	
	@Test
	void putTest(){
		Dictionary<String, Integer> defaultDictionary = new Dictionary<String, Integer>();
		defaultDictionary.put("Ivana", 1);
		defaultDictionary.put("Ivana", 2);
		assertEquals(2, defaultDictionary.get("Ivana"));
		assertEquals(1, defaultDictionary.size());
		assertThrows(NullPointerException.class, ()-> defaultDictionary.put(null, 2));
	}
	
	@Test
	void clearTest(){
		Dictionary<String, Integer> defaultDictionary = new Dictionary<String, Integer>();
		defaultDictionary.clear();
		assertEquals(0, defaultDictionary.size());
	}
	
	@Test
	void isEmptyTest(){
		Dictionary<String, Integer> defaultDictionary = new Dictionary<String, Integer>();
		defaultDictionary.put("bla", 7);
		assertFalse(defaultDictionary.isEmpty());
		defaultDictionary.clear();
		assertTrue(defaultDictionary.isEmpty());
	}
	
	@Test
	void getValueTest() {
		Dictionary<String, Integer> defaultDictionary = new Dictionary<String, Integer>();
		defaultDictionary.put("bla", 7);
		assertEquals(7, defaultDictionary.get("bla"));
		assertEquals(null, defaultDictionary.get("zrinka"));
		assertEquals(null, defaultDictionary.get(2.17));
	}

}
