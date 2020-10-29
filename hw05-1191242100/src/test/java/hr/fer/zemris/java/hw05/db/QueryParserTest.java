package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueryParserTest {

	@Test
	void testIsDirectQuery() {
		QueryParser qp2 = new QueryParser("jmbag=\"0123456789\" and lastName>\"J\" and firstName=\"a\"");
		assertFalse(qp2.isDirectQuery()); // false
		assertEquals(3, qp2.getQuery().size()); // 2
	}
	
	
	@Test
	void testGetQueriedThrow() {
		QueryParser qp2 = new QueryParser("jmbag=\"0123456789\" and lastName>\"J\"");
		assertThrows(IllegalStateException.class, () -> qp2.getQueriedJMBAG());
	}
	
	@Test
	void testGetQueriedJMBAG() {
		QueryParser qp1 = new QueryParser(" jmbag =\"0123456789\" ");
		assertTrue(qp1.isDirectQuery());
		assertEquals("0123456789", qp1.getQueriedJMBAG());
	}
	
	@Test
	void invalidField() {
		assertThrows(QueryParserException.class, () -> new QueryParser("JMBAG=\"0123456789\" and lastName>\"J\""));
		assertThrows(QueryParserException.class, () -> new QueryParser("jmbag=\"0123456789\" and firName>\"J\""));
		assertThrows(QueryParserException.class, () -> new QueryParser("jmbag=<\"0123456789\""));
		assertThrows(QueryParserException.class, () -> new QueryParser("LIKE"));
		assertThrows(QueryParserException.class, () -> new QueryParser("jmbag=<\"0123456789\" ab"));
	}
	
}
