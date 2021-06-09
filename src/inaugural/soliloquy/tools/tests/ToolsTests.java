package inaugural.soliloquy.tools.tests;

import inaugural.soliloquy.tools.Tools;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToolsTests {
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

        //noinspection ConstantConditions
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
}
