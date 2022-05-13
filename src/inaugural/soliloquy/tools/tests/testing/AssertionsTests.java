package inaugural.soliloquy.tools.tests.testing;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;

import static inaugural.soliloquy.tools.testing.Assertions.assertEqualsAndNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertionsTests {
    @Test
    void testAssertEqualsAndNotSame() {
        ArrayList<Integer> list1 = new ArrayList<>() {{ add(1); add(2); add(3); }};
        ArrayList<Integer> list2 = new ArrayList<>(list1);

        assertEqualsAndNotSame(list1, list2);
        assertThrows(AssertionFailedError.class, () -> assertEqualsAndNotSame(list1, list1));
    }
}
