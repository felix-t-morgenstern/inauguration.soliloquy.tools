package inaugural.soliloquy.tools.tests;

import inaugural.soliloquy.tools.Tools;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToolsTests {
    @Test
    void testEmptyIfNull() {
        String input = "input";

        assertEquals(input, Tools.emptyIfNull(input));

        assertEquals("", Tools.emptyIfNull(null));
    }
}
