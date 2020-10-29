package hr.fer.zemris.java.custom.collections;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListIndexedCollectionTest {

	@Test
	void addNullReference() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertThrows(NullPointerException.class, ()-> {
			list.add(null);
		});
	}
	
	@Test
	void defaultConstruct() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertEquals(true, list.isEmpty());
	}
	
	@Test
	void constructWithCollection() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("prvi");
		list.add("drugi");
		LinkedListIndexedCollection list2 = new LinkedListIndexedCollection(list);
		assertEquals(true, list2.contains("prvi"));
		assertEquals(true, list2.contains("drugi"));
	}
	
	@Test
	void addAnyObject() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("new york");
		assertEquals("new york", list.get(0));
	}
	
	@Test
	void clearTest() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("new york");
		list.clear();
		assertEquals(0, list.size());
	}
	
	@Test
	void clearEmpty() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.clear();
		assertEquals(0, list.size());
	}
	
	@Test
	void getFirstTest() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("new york");
		assertEquals("new york", list.get(0));
	}
	
	@Test
	void getMiddle() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("new york");
		list.add("la");
		list.add("new york");
		assertEquals("la", list.get(1));
	}
	
	@Test
	void getLast() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("new york");
		assertEquals("new york", list.get(0));
	}
	
	@Test
	void getSizeIndex() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			list.get(16);
		});
	}
	
	@Test
	void getNegativeIndex() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			list.get(-1);
		});
	}
	
	@Test
	void insertsTo0() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("la");
		list.insert("new york", 0);
		assertEquals("new york", list.get(0));
		assertEquals("la", list.get(1));
	}
	
	@Test
	void insertsMiddle() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("la");
		list.add("vegas");
		list.add("boston");
		list.insert("new york", 1);
		assertEquals("new york", list.get(1));
		assertEquals("vegas", list.get(2));
	}
	
	@Test
	void insertsToSize() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("la");
		list.insert("new york", 0);
		assertEquals("new york", list.get(0));
		assertEquals("la", list.get(1));
	}
	
	@Test
	void insertsNull() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertThrows(NullPointerException.class, ()-> {
			list.insert(null, 0);
		});
	}
	
	@Test
	void insertsToNegativeIndex() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			list.insert("new york", -1);
		});
	}
	
	@Test
	void insertsToGreaterThanSize() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			list.insert("new york", 17);
		});
	}
	
	
	@Test
	void indexOfAny() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("la");
		list.add("new york");
		assertEquals(0, list.indexOf("la"));
		assertEquals(1, list.indexOf("new york"));
	}
	
	@Test
	void indexOfNonExisting() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("la");
		assertEquals(-1, list.indexOf("las vegas"));
	}
	
	@Test
	void indexOfNull() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertEquals(-1, list.indexOf(null));
	}
	
	@Test
	void removesFirst() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("la");
		list.remove(0);
		assertEquals(-1, list.indexOf("la"));
	}
	
	@Test
	void removesLast() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("la");
		list.add("vegas");
		list.remove(1);
		assertEquals("la", list.get(0));
		assertEquals(-1, list.indexOf("vegas"));
	}
	
	@Test
	void removesMiddle() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("la");
		list.add("vegas");
		list.add("boston");
		list.add("new york");
		list.remove(1);
		assertEquals(0, list.indexOf("la"));
		assertEquals(-1, list.indexOf("vegas"));
		assertEquals(3, list.size());
		assertEquals(1, list.indexOf("boston"));
	}
	
	@Test
	void removesMiddleWhileThreeElements() { //jer ostaju samo first i last
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("la");
		list.add("boston");
		list.add("new york");
		assertEquals(3, list.size());
		list.remove(1);
		assertEquals(0, list.indexOf("la"));
		assertEquals(-1, list.indexOf("boston"));
		assertEquals(2, list.size());
		assertEquals(1, list.indexOf("new york"));
	}
	
	
	@Test
	void removesIndexGreaterThanSize() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			list.remove(16);
		});
	}
	
	@Test
	void removesNegativeIndex() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			list.remove(-1);
		});
	}
	
	@Test
	void sizeEmpty() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertEquals(0, list.size());
	}
	
	@Test
	void sizeOneElement() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("kraj");
		assertEquals(1, list.size());
	}
	
	@Test
	void isEmptyTestEmpty() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertEquals(true, list.isEmpty());
	}
	
	@Test
	void isEmptyTestNotEmpty() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("kraj");
		assertEquals(false, list.isEmpty());
	}
	
	@Test
	void containsTrue() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("kraj");
		assertEquals(true, list.contains("kraj"));
	}
	
	@Test
	void containsFalse() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertEquals(false, list.contains("vegas"));	
	}
	
	@Test
	void containsNull() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		assertEquals(false, list.contains(null));
	}
	
	@Test
	void removesObjectTrue() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("la");
		assertEquals(true, list.remove("la"));
		assertEquals(false, list.contains("la"));
	}
	
	@Test
	void removesObjectFalse() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("vegas");
		assertEquals(false, list.contains("la"));
		assertEquals(false, list.remove("la"));
	}
	
	@Test
	void objectToArrayTestTrue() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("new york");
		list.add("la");
		LinkedListIndexedCollection list2 = new LinkedListIndexedCollection();
		list2.add("new york");
		list2.add("la");
		Object[] array = list.toArray();
		Object[] array2 = list2.toArray();
		assertEquals(true, Arrays.equals(array, array2));
	}
	
	@Test
	void objectToArrayTestFalse() {
		LinkedListIndexedCollection list = new LinkedListIndexedCollection();
		list.add("new york");
		list.add("la");
		LinkedListIndexedCollection list2 = new LinkedListIndexedCollection();
		list2.add("new york");
		Object[] array = list.toArray();
		Object[] array2 = list2.toArray();
		assertEquals(false, Arrays.equals(array, array2));
	}
	
	
	
}