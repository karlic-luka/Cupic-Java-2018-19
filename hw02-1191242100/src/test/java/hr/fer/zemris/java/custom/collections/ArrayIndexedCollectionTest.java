package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ArrayIndexedCollectionTest {
	
	@Test
	void defaultConstructorTest() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertEquals(0, array.size());
	}
	
	@Test
	void constructorInitial() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(5);
		assertEquals(0, array.size());
	}
	
	@Test
	void constructsCollection() {
		ArrayIndexedCollection col = new ArrayIndexedCollection();
		col.add("new york");
		col.add("las vegas");
		ArrayIndexedCollection array = new ArrayIndexedCollection(col);
		assertEquals(true, array.contains("new york"));
		assertEquals(true, array.contains("las vegas"));
	}
	
	@Test
	void constructsCollectionAndCapacity() {
		ArrayIndexedCollection col = new ArrayIndexedCollection();
		col.add("new york");
		col.add("las vegas");
		ArrayIndexedCollection array = new ArrayIndexedCollection(col, 1); 
		assertEquals(true, array.contains("new york"));
		assertEquals(true, array.contains("las vegas"));
	}
	
	@Test
	void constructsNullCollection() {
		assertThrows(NullPointerException.class, ()-> {
			ArrayIndexedCollection array = new ArrayIndexedCollection(null);
		});
	}


	@Test
	void addNullReference() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertThrows(NullPointerException.class, ()-> {
			array.add(null);
		});
	}
	
	@Test
	void addAnyObject() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("new york");
		assertEquals(true, array.contains("new york"));
	}
	
	@Test
	void clearTest() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("new york");
		array.clear();
		assertEquals(0, array.size());
	}
	
	@Test
	void clearEmpty() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.clear();
		assertEquals(0, array.size());
	}
	
	@Test
	void getFirstTest() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("new york");
		assertEquals("new york", array.get(0));
	}
	
	@Test
	void getLast() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(1);
		array.add("new york");
		assertEquals("new york", array.get(0));
	}
	
	@Test
	void getMiddle() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("new york");
		array.add("la");
		array.add("new york");
		assertEquals("la", array.get(1));
	}
	
	@Test
	void getSizeIndex() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			array.get(16);
		});
	}
	
	@Test
	void getNegativeIndex() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			array.get(-1);
		});
	}
	
	@Test
	void insertsTo0() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(2);
		array.insert("new york", 0);
		array.insert("la", 0);
		assertEquals("la", array.get(0));
		assertEquals("new york", array.get(1));
	}
	
	@Test
	void insertsMiddle() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("la");
		array.add("vegas");
		array.add("boston");
		array.insert("new york", 1);
		assertEquals("new york", array.get(1));
		assertEquals("vegas", array.get(2));
	}
	
	
	@Test
	void insertsToSize() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(1);
		array.add("la");
		array.insert("new york", 0);
		assertEquals("new york", array.get(0));
		assertEquals("la", array.get(1));
	}
	
	@Test
	void insertsNull() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertThrows(NullPointerException.class, ()-> {
			array.insert(null, 0);
		});
	}
	
	@Test
	void insertsToNegativeIndex() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			array.insert("new york", -1);
		});
	}
	
	@Test
	void insertsToGreaterThanSize() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			array.insert("new york", 17);
		});
	}
	
	@Test
	void indexOfAny() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("la");
		assertEquals(0, array.indexOf("la"));
	}
	
	@Test
	void indexOfNonExisting() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("la");
		assertEquals(-1, array.indexOf("las vegas"));
	}
	
	@Test
	void indexOfNull() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertEquals(-1, array.indexOf(null));
	}
	
	@Test
	void removesFirst() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(1);
		array.add("la");
		array.remove(0);
		assertEquals(-1, array.indexOf("la"));
	}
	
	@Test
	void removesLast() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(2);
		array.add("la");
		array.add("vegas");
		array.remove(1);
		assertEquals(-1, array.indexOf("vegas"));
		assertEquals(0, array.indexOf("la"));
	}
	
	@Test
	void removesMiddle() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(1);
		array.add("la");
		array.add("vegas");
		array.add("boston");
		array.remove(1);
		assertEquals(-1, array.indexOf("vegas"));
		assertEquals(0, array.indexOf("la"));
		assertEquals(1, array.indexOf("boston"));
	}
	
	@Test
	void removesIndexGreaterThanSize() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			array.remove(16);
		});
	}
	
	@Test
	void removesNegativeIndex() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			array.remove(-1);
		});
	}
	
	@Test
	void sizeEmpty() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertEquals(0, array.size());
	}
	
	@Test
	void sizeOneElement() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("kraj");
		assertEquals(1, array.size());
	}
	
	@Test
	void isEmptyTestEmpty() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertEquals(true, array.isEmpty());
	}
	
	@Test
	void isEmptyTestNotEmpty() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("kraj");
		assertEquals(false, array.isEmpty());
	}
	
	@Test
	void containsTrue() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("kraj");
		assertEquals(true, array.contains("kraj"));
	}
	
	@Test
	void containsFalse() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		array.add("kraj");
		assertEquals(false, array.contains("vegas"));	
	}
	
	@Test
	void containsNull() {
		ArrayIndexedCollection array = new ArrayIndexedCollection();
		assertEquals(false, array.contains(null));
	}
	
	@Test
	void removesObjectTrue() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(1);
		array.add("la");
		assertEquals(true, array.remove("la"));
		assertEquals(false, array.contains("la"));
	}
	
	@Test
	void removesObjectFalse() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(1);
		array.add("vegas");
		assertEquals(false, array.remove("la"));
		assertEquals(false, array.contains("la"));
	}
	
	@Test
	void objectToArrayTestTrue() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(2);
		array.add("new york");
		array.add("la");
		ArrayIndexedCollection array2 = new ArrayIndexedCollection(2);
		array2.add("new york");
		array2.add("la");
		Object[] firstArray = array.toArray();
		Object[] secondArray = array2.toArray();
		assertEquals(true, Arrays.equals(firstArray, secondArray));
	}
	
	@Test
	void objectToArrayTestFalse() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(2);
		array.add("new york");
		array.add("la");
		ArrayIndexedCollection array2 = new ArrayIndexedCollection(2);
		array2.add("new york");
		Object[] firstArray = array.toArray();
		Object[] secondArray = array2.toArray();
		assertEquals(false, Arrays.equals(firstArray, secondArray));
	}
}