package inaugural.soliloquy.tools.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class Assertions {
    public static <T> void assertEqualsAndNotSame(T expected, T actual) {
        assertEquals(expected, actual);
        assertNotSame(expected, actual);
    }
}
