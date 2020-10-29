package hr.fer.zemris.java.hw01;

import static org.junit.jupiter.api.Assertions.*;
import hr.fer.zemris.java.hw01.UniqueNumbers.TreeNode;
import static hr.fer.zemris.java.hw01.UniqueNumbers.*;


import org.junit.jupiter.api.Test;

public class UniqueNumbersTest {

	@Test
	public void dodajCvorNullPointeru() {
		TreeNode korijen = null;
		korijen = addNode(korijen, 7);
		assertTrue(containsValue(korijen,7));
	}
	
	@Test
	public void dodajCvorPostojecemStablu() {
		TreeNode korijen = null;
		korijen = addNode(korijen, 7);
		korijen = addNode(korijen, 6);
		assertTrue(containsValue(korijen, 6));
	}

	@Test
	public void velicinaStablaCijiJeKorijenNullPointer() {
		TreeNode cvor = null;
		assertEquals(0, treeSize(cvor));
	}
	
	@Test
	public void velicinaStablaDubineDva() {
		TreeNode korijen = null;
		korijen = addNode(korijen, 7);
		korijen = addNode(korijen, 5);
		korijen = addNode(korijen, 6);
		assertEquals(3, treeSize(korijen));
	}
	
	@Test
	public void neSadrziNitiJedanCvor() {
		TreeNode korijen = null;
		assertFalse(containsValue(korijen, 7));
	}
	
	@Test
	public void sadrziCvor() {
		TreeNode korijen = null;
		korijen = addNode(korijen, 7);
		assertTrue(containsValue(korijen, 7));
	}
	
	@Test
	public void neSadrziCvor() {
		TreeNode korijen = null;
		korijen = addNode(korijen, 7);
		assertFalse(containsValue(korijen, 5));
	}
	

}
