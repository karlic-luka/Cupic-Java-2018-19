package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ComparisonOperatorsTest {

    @Test
    public void lessTrueTest() {
        assertTrue(ComparisonOperators.LESS.satisfied("A", "B"));
    }

    @Test
    public void lessFalseTest() {
        assertFalse(ComparisonOperators.LESS.satisfied("c", "b"));
    }

    @Test
    public void lessOrEqualsTrueTest() {
        assertTrue(ComparisonOperators.LESS_OR_EQUALS.satisfied("Luka", "Luka"));
    }

    @Test
    public void lessOrEqualsFalseTest() {
        assertFalse(ComparisonOperators.LESS_OR_EQUALS.satisfied("Luka", "Juka"));
    }

    @Test
    public void greaterTrueTest() {
        assertTrue(ComparisonOperators.GREATER.satisfied("Muka", "Luka"));
    }

    @Test
    public void greaterFalseTest() {
        assertFalse(ComparisonOperators.GREATER.satisfied("Luka", "Lvka"));
    }

    @Test
    public void greaterEqualsTrueTest() {
        assertTrue(ComparisonOperators.GREATER_OR_EQUALS.satisfied("Anb", "Ana"));
    }

    @Test
    public void greaterEqualsFalseTest() {
        assertFalse(ComparisonOperators.GREATER_OR_EQUALS.satisfied("Anb", "Anc"));
    }

    @Test
    public void equalsTrueTest() {
        assertTrue(ComparisonOperators.EQUALS.satisfied("Luka", "Luka"));
    }

    @Test
    public void equalsFalseTest() {
        assertFalse(ComparisonOperators.EQUALS.satisfied("Luka", "Karlić"));
    }

    @Test
    public void notEqualsTrueTest() {
        assertTrue(ComparisonOperators.NOT_EQUALS.satisfied("Luka", "Karlić"));
    }

    @Test
    public void notEqualsFalseTest() {
        assertFalse(ComparisonOperators.NOT_EQUALS.satisfied("Luka", "Luka"));
    }

    @Test
    public void likeTest() {
        assertFalse(ComparisonOperators.LIKE.satisfied("Zagreb", "Aba*"));
        assertFalse(ComparisonOperators.LIKE.satisfied("AAA", "AA*AA"));
        assertTrue(ComparisonOperators.LIKE.satisfied("AAAA", "AA*AA"));
    }

}
