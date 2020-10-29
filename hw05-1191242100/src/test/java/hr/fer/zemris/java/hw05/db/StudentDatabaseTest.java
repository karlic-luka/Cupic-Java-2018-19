package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentDatabaseTest {
	
	StudentDatabase database;
	
	@BeforeEach
	public void setUp() throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("database.txt"), StandardCharsets.UTF_8);
        database = new StudentDatabase(lines);
	}

	@Test
	void testForJMBAG() {
		StudentRecord prvi = new StudentRecord("0000000001", "Akšamović", "Marin", "2");
		assertEquals(prvi, database.forJMBAG("0000000001"));
	}

	@Test
    public void filterAlwaysFalse() {
        List<StudentRecord> filter = database.filter(record -> false);
        assertEquals(0, filter.size());
    }

    @Test
    public void filterAlwaysTrue() {
        List<StudentRecord> filter = database.filter(record -> true);
        assertEquals(63, filter.size());
    }
}
