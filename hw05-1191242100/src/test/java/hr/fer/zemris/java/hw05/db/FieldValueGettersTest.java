package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FieldValueGettersTest {

    StudentRecord record;

    @BeforeEach
    public void setUpBefore() throws Exception {
        record = new StudentRecord("1191242100", "Karlić", "Luka", "5");
    }

    @Test
    public void testFirstName() {
        assertEquals("Luka", FieldValueGetters.FIRST_NAME.get(record));
    }

    @Test
    public void testLastName() {
        assertEquals("Karlić", FieldValueGetters.LAST_NAME.get(record));
    }

    @Test
    public void testJMBAG() {
        assertEquals("1191242100", FieldValueGetters.JMBAG.get(record));
    }
}
