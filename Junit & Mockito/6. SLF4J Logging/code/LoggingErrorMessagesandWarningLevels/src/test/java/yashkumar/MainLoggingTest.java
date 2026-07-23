package yashkumar;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainLoggingTest {

    @Test
    void testErrorAndWarnLogging() {
        PrintStream originalErr = System.err;
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {
            Main.main(new String[0]);
        } finally {
            System.setErr(originalErr);
        }

        String output = errContent.toString();
        assertTrue(output.contains("This is an error"), "Expected error log message");
        assertTrue(output.contains("This is a warn"), "Expected warn log message");
    }
}
