package inaugural.soliloquy.tools.tests.testing;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;

import static inaugural.soliloquy.tools.testing.Assertions.assertEqualsAndNotSame;
import static inaugural.soliloquy.tools.testing.Assertions.assertOnlyContains;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertionsTests {
    @Test
    void testAssertEqualsAndNotSame() {
        ArrayList<Integer> list1 = new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
        }};
        ArrayList<Integer> list2 = new ArrayList<>(list1);

        assertEqualsAndNotSame(list1, list2);
        assertThrows(AssertionFailedError.class, () -> assertEqualsAndNotSame(list1, list1));
    }

    @Test
    void testAssertOnlyContains() {
        assertThrows(AssertionFailedError.class, () -> assertOnlyContains(new ArrayList<>() {{
            add(0);
            add(1);
        }}, 0));
        assertThrows(AssertionFailedError.class, () -> assertOnlyContains(new ArrayList<>() {{
            add(1);
        }}, 0));
        assertThrows(AssertionFailedError.class, () -> assertOnlyContains(new ArrayList<>(), 0));
        assertThrows(AssertionFailedError.class, () -> assertOnlyContains(new ArrayList<>() {{
            add(new Object());
        }}, new Object()));
    }
}
