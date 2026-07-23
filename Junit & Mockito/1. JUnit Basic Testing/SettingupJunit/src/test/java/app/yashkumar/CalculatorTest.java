package app.yashkumar;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void add() {
        assertEquals(20, calculator.add(10, 10));
    }

    @Test
    public void sub() {
        assertEquals(3, calculator.sub(10, 7));
    }

    @Test
    public void multiply() {
        assertEquals(15, calculator.multiply(3, 5));
    }
}
