package inaugural.soliloquy.tools.tests;

import inaugural.soliloquy.tools.Tools;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        assertEquals(input, Tools.emptyIfNull(input));

        assertNull(Tools.emptyIfNull(""));
    }
}
