package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ObjectMultistackTest {

	public ObjectMultistack multiStack = new ObjectMultistack();
    @Test
    public void push() {
        multiStack.push("Messi", new ValueWrapper(10));
        multiStack.push("Ronaldo", new ValueWrapper(7));

        assertEquals(10, multiStack.peek("Messi").getValue());
        assertEquals(7, multiStack.peek("Ronaldo").getValue());
    }

    @Test
    public void pop() {
        multiStack.push("Messi", new ValueWrapper(10));
        multiStack.push("Messi", new ValueWrapper("GOAT"));
        multiStack.push("Ronaldo", new ValueWrapper(7));

        assertEquals("GOAT", multiStack.pop("Messi").getValue());
        assertEquals(10, multiStack.pop("Messi").getValue());
        assertEquals(7, multiStack.pop("Ronaldo").getValue());
    }

    @Test
    public void peek() {
        multiStack.push("Messi", new ValueWrapper(10));
        multiStack.push("Messi", new ValueWrapper("GOAT"));
        multiStack.push("Ronaldo", new ValueWrapper(7));

        assertEquals("GOAT", multiStack.peek("Messi").getValue());
        assertEquals(7, multiStack.peek("Ronaldo").getValue());
    }

    @Test
    public void isEmpty() {
        assertTrue(multiStack.isEmpty("Messi"));
        assertTrue(multiStack.isEmpty("Ronaldo"));
        multiStack.push("Messi", new ValueWrapper(10));
        multiStack.push("Ronaldo", new ValueWrapper(7));
        assertFalse(multiStack.isEmpty("Messi"));
        assertFalse(multiStack.isEmpty("Ronaldo"));
 
    }

}
