package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void testAdd1() {
        assertEquals(calculator.add(6,5), 11);
    }
    @Test
    public void testAdd2() {
        assertEquals(calculator.add(2,-3), -1);
    }
    @Test
    public void testMultiply1() {
        assertEquals(calculator.multiply(6,5), 30);
    }
    @Test
    public void testMultiply2() {
        assertEquals(calculator.multiply(2,-3), -6);
    }
    @Test
    void testAddPositiveNumbers() {
        assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(-5, 5));
    }

}