package inaugural.soliloquy.tools.testing;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class Assertions {
    public static <T> void assertEqualsAndNotSame(T expected, T actual) {
        assertEquals(expected, actual);
        assertNotSame(expected, actual);
    }

    public static <T> void assertOnlyContains(List<T> list, T item) {
        assertEquals(1, list.size());
        if (item instanceof Integer ||
                item instanceof Long ||
                item instanceof Float ||
                item instanceof Double ||
                item instanceof UUID) {
            assertEquals(item, list.get(0));
        }
        else {
            assertSame(item, list.get(0));
        }
    }
}
