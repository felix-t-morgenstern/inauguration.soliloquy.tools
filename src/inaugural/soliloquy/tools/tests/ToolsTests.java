package inaugural.soliloquy.tools.tests;

import inaugural.soliloquy.tools.Tools;
import inaugural.soliloquy.tools.tests.fakes.PassthroughRunnable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToolsTests {
    private static String _callingClassName;

    @Test
    void testEmptyIfNull() {
        String input = "input";

        assertEquals(input, Tools.emptyIfNull(input));

        assertEquals("", Tools.emptyIfNull(null));
    }

    @Test
    void testNullIfEmpty() {
        String input = "input";

        assertEquals(input, Tools.nullIfEmpty(input));

        assertNull(Tools.nullIfEmpty(""));
    }

    @Test
    void testRound() {
        float value = 1.23456f;
        int places = 3;

        assertEquals(1.235f, Tools.round(value, places));
        assertEquals(-1.235f, Tools.round(-value, places));

        assertThrows(IllegalArgumentException.class, () -> Tools.round(value, -1));
    }

    @Test
    void testCallingClassName() {
        _callingClassName = Tools.callingClassName();

        assertEquals(this.getClass().getCanonicalName(), _callingClassName);
    }

    @Test
    void testCallingClassNameWithStepsToMoveUp() {
        PassthroughRunnable setCallingClassName = new PassthroughRunnable(() ->
                _callingClassName = Tools.callingClassName(6));
        PassthroughRunnable level1 = new PassthroughRunnable(setCallingClassName::call);
        PassthroughRunnable level2 = new PassthroughRunnable(level1::call);

        level2.call();

        assertEquals(this.getClass().getCanonicalName(), _callingClassName);
    }
}
