package inaugural.soliloquy.tools.tests.testing;

import inaugural.soliloquy.tools.testing.Mock;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.persistence.TypeHandler;
import soliloquy.specs.gamestate.entities.Item;

import static inaugural.soliloquy.tools.testing.Mock.generateMockEntityAndHandler;
import static org.junit.jupiter.api.Assertions.*;

class MockTests {
    @Test
    void testGenerateMockEntityAndHandler() {
        String writtenValue = "writtenValue";

        Mock.TypeAndHandler<Item> mockEntityAndHandler =
                generateMockEntityAndHandler(Item.class, writtenValue);

        assertNotNull(mockEntityAndHandler);
        Item entity = mockEntityAndHandler.entity;
        assertNotNull(entity);
        TypeHandler<Item> handler = mockEntityAndHandler.handler;
        assertNotNull(handler);
        assertEquals(writtenValue, handler.write(null));
        assertSame(entity, handler.read(null));
        assertSame(entity, handler.read(""));
    }
}
