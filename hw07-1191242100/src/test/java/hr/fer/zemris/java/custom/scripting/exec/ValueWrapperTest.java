package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValueWrapperTest {

    @Test
    public void getValue() {
        ValueWrapper wrapper = new ValueWrapper(1);
        ValueWrapper wrapper2 = new ValueWrapper(null);
        ValueWrapper wrapper3 = new ValueWrapper(Boolean.valueOf(true));
        assertEquals(1 ,wrapper.getValue());
        assertNull(wrapper2.getValue());
        assertEquals(true, wrapper3.getValue());
    }

    @Test
    public void setValue() {
        ValueWrapper wrapper = new ValueWrapper(1);
        wrapper.setValue(1.0);
        assertEquals(1.0,wrapper.getValue());
    }

    @Test
    public void addJustOneNull() {
    	ValueWrapper wrapper1 = new ValueWrapper(null);
    	ValueWrapper wrapper2 = new ValueWrapper(500);
    	wrapper1.add(wrapper2.getValue());
    	assertEquals(500, wrapper1.getValue());
    	assertTrue(wrapper1.getValue() instanceof Integer);
    	assertEquals(500, wrapper2.getValue());
    }
    
    @Test
    public void addWhileBothAreNull() {
        ValueWrapper wrapper1 = new ValueWrapper(null);
        ValueWrapper wrapper2 = new ValueWrapper(null);
        wrapper1.add(wrapper2.getValue());
        assertEquals(0, wrapper1.getValue());
        assertTrue(wrapper1.getValue() instanceof Integer);
        assertEquals(null, wrapper2.getValue());
    }


    @Test
    public void addIntInt(){
        ValueWrapper wrapper1 = new ValueWrapper(5);
        ValueWrapper wrapper2 = new ValueWrapper(5);
        wrapper1.add(wrapper2.getValue());
        assertEquals(10,wrapper1.getValue());
        assertTrue(wrapper1.getValue() instanceof Integer);
        assertEquals(5,wrapper2.getValue());
    }
    
    @Test
    public void addIntDouble(){
        ValueWrapper wrapper1 = new ValueWrapper(6);
        ValueWrapper wrapper2 = new ValueWrapper(9);
        wrapper1.add(wrapper2.getValue());
        assertEquals(15, wrapper1.getValue());
        assertTrue(wrapper1.getValue() instanceof Integer);
        assertEquals(9, wrapper2.getValue());
    }

    @Test
    public void addStringDouble(){
        ValueWrapper wrapper1 = new ValueWrapper("1.2E1");
        ValueWrapper wrapper2 = new ValueWrapper(1);
        wrapper1.add(wrapper2.getValue());
        assertEquals(13.0,wrapper1.getValue());
        assertTrue(wrapper1.getValue() instanceof Double);
        assertEquals(1,wrapper2.getValue());
    }

    @Test
    public void addStringInt(){
        ValueWrapper wrapper1 = new ValueWrapper("12");
        ValueWrapper wrapper2 = new ValueWrapper(1);
        wrapper1.add(wrapper2.getValue());
        assertEquals(13,wrapper1.getValue());
        assertTrue(wrapper1.getValue() instanceof Integer);
        assertEquals(1,wrapper2.getValue());
    }

    @Test
    public void addStringThrowsException(){
        ValueWrapper wrapper1 = new ValueWrapper("Dragi korisnice");
        ValueWrapper wrapper2 = new ValueWrapper(1);
        assertThrows(RuntimeException.class, () -> wrapper1.add(wrapper2.getValue()));
    }

    @Test
    public void subtract() {
        ValueWrapper wrapper1 = new ValueWrapper(69);
        ValueWrapper wrapper2 = new ValueWrapper(1);
        wrapper1.subtract(wrapper2.getValue());
        assertEquals(68, wrapper1.getValue());
        assertTrue(wrapper1.getValue() instanceof Integer);
        assertEquals(1, wrapper2.getValue());
    }

    @Test
    public void multiply() {
        ValueWrapper wrapper1 = new ValueWrapper("1.2E1");
        ValueWrapper wrapper2 = new ValueWrapper("1.2E1");
        wrapper1.multiply(wrapper2.getValue());
        assertEquals(144.0,wrapper1.getValue());
        assertTrue(wrapper1.getValue() instanceof Double);
        assertEquals("1.2E1", wrapper2.getValue());
    }
    
    @Test
    public void numCompare0() {
        ValueWrapper wrapper1 = new ValueWrapper("1.5E1");
        ValueWrapper wrapper2 = new ValueWrapper(15);
        assertEquals(0, wrapper1.numCompare(wrapper2.getValue()));
    }
    @Test
    public void numCompare1() {
        ValueWrapper wrapper1 = new ValueWrapper(2);
        ValueWrapper wrapper2 = new ValueWrapper(1);
        assertEquals(1, wrapper1.numCompare(wrapper2));
    }

    @Test
    public void numCompareMinus1() {
        ValueWrapper wrapper1 = new ValueWrapper(2);
        ValueWrapper wrapper2 = new ValueWrapper(1);
        assertEquals(-1, wrapper2.numCompare(wrapper1));
    }
}
