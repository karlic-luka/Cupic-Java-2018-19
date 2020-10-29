package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueryFilterTest {

	@Test
    public void testTrueGetQuery() {
        QueryParser qp = new QueryParser("jmbag=\"1191242100\" and lastName LIKE \"K*ć\"");
        StudentRecord record = new StudentRecord("1191242100", "Karlić", "Luka", "5");
        QueryFilter filter = new QueryFilter(qp.getQuery());
        assertTrue(filter.accepts(record));

    }

    @Test
    public void testFalseGetQuery() {
        QueryParser qp = new QueryParser("jmbag=\"1191242100\" and lastName > \"k\"");
        StudentRecord record = new StudentRecord("1191242100", "Karlić", "Luka", "5");
        QueryFilter filter = new QueryFilter(qp.getQuery());
        assertFalse(filter.accepts(record));

    }

}
