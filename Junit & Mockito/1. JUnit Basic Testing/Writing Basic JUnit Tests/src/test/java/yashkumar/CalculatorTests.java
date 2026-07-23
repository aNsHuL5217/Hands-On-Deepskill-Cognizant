package yashkumar;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorTests {

    Calculator calculator = new Calculator();

    @Test
    public void greet() {
        assertEquals(12, calculator.add(5, 7));
    }
}
