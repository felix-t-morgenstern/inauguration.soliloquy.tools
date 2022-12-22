package inaugural.soliloquy.tools.tests.testing;

import inaugural.soliloquy.tools.random.Random;
import inaugural.soliloquy.tools.testing.Mock;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.persistence.PersistentValuesHandler;
import soliloquy.specs.common.persistence.TypeHandler;
import soliloquy.specs.common.valueobjects.Pair;
import soliloquy.specs.gamestate.entities.Item;

import java.util.Map;

import static inaugural.soliloquy.tools.testing.Mock.*;
import static org.junit.jupiter.api.Assertions.*;

class MockTests {
    @Test
    void testGenerateSimpleMockTypeHandler() {
        Integer[] values =
                new Integer[]{Random.randomInt(), Random.randomInt(), Random.randomInt()};

        TypeHandler<Integer> mockIntegerHandler = generateSimpleMockTypeHandler(values);

        assertNotNull(mockIntegerHandler);
        for (Integer value : values) {
            assertEquals(value, mockIntegerHandler.read(value.toString()));
            assertEquals(value.toString(), mockIntegerHandler.write(value));
        }
    }

    @Test
    void testGenerateMockPersistentValuesHandlerWithSimpleHandlers() {
        Integer[] ints =
                new Integer[]{Random.randomInt(), Random.randomInt(), Random.randomInt()};
        Double[] doubles =
                new Double[]{Random.randomDouble(), Random.randomDouble(), Random.randomDouble()};

        //noinspection rawtypes
        Pair<PersistentValuesHandler, Map<String, TypeHandler>> persistentValuesHandlerAndHandlers = generateMockPersistentValuesHandlerWithSimpleHandlers(ints, doubles);

        assertNotNull(persistentValuesHandlerAndHandlers);
        assertNotNull(persistentValuesHandlerAndHandlers.getItem1());
        assertNotNull(persistentValuesHandlerAndHandlers.getItem2());
        //noinspection rawtypes
        TypeHandler integerHandler = persistentValuesHandlerAndHandlers.getItem2().get(Integer.class.getCanonicalName());
        for (Integer value : ints) {
            assertEquals(value, integerHandler.read(value.toString()));
            //noinspection unchecked
            assertEquals(value.toString(), integerHandler.write(value));
        }
        //noinspection rawtypes
        TypeHandler doubleHandler = persistentValuesHandlerAndHandlers.getItem2().get(Double.class.getCanonicalName());
        for (Double value : doubles) {
            assertEquals(value, doubleHandler.read(value.toString()));
            //noinspection unchecked
            assertEquals(value.toString(), doubleHandler.write(value));
        }

    }

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
