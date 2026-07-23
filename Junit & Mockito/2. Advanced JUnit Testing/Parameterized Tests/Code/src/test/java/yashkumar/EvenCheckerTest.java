package yashkumar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EvenCheckerTest {
    EvenChecker checker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, -8, 2000, 4, 100})
    public void testEven(int number) {
        assertTrue(checker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, -5, 7, 1, 5})
    public void testOdd(int number) {
        assertFalse(checker.isEven(number));
    }
}
