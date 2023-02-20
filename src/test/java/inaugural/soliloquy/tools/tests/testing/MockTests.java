package inaugural.soliloquy.tools.tests.testing;

import org.junit.jupiter.api.Test;
import soliloquy.specs.common.valueobjects.Pair;
import soliloquy.specs.gamestate.entities.Item;
import soliloquy.specs.ruleset.entities.ItemType;

import java.util.ArrayList;

import static inaugural.soliloquy.tools.collections.Collections.mapOf;
import static inaugural.soliloquy.tools.random.Random.*;
import static inaugural.soliloquy.tools.testing.Mock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class MockTests {
    @Test
    void testGenerateMockWithId() {
        var id = "Id";

        var mockItem = generateMockWithId(ItemType.class, id);

        assertNotNull(mockItem);
        assertEquals(id, mockItem.id());
    }

    @Test
    void testGenerateMockList() {
        var mockList = generateMockList(1, 2, 3);

        assertNotNull(mockList);
        assertEquals(3, mockList.size());
        assertTrue(mockList.contains(1));
        assertTrue(mockList.contains(2));
        assertTrue(mockList.contains(3));
        assertFalse(mockList.contains(4));
        var collector = new ArrayList<Integer>();
        // NB: Calling forEach twice here to ensure that multiple iteration is possible
        mockList.forEach(i -> {});
        //noinspection UseBulkOperation
        mockList.forEach(collector::add);
        assertEquals(new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
        }}, collector);
    }

    @Test
    void testGenerateMockMap() {
        var mockMap = generateMockMap(Pair.of(3, "C"), Pair.of(2, "B"), Pair.of(1, "A"));

        assertNotNull(mockMap);
        assertEquals(3, mockMap.size());
        assertEquals("A", mockMap.get(1));
        assertEquals("B", mockMap.get(2));
        assertEquals("C", mockMap.get(3));
        // NB: Calling forEach twice here to ensure that multiple iteration is possible
        mockMap.forEach((k,v) -> {});
        var keysInOrder = new ArrayList<Integer>();
        var valuesInOrder = new ArrayList<String>();
        mockMap.forEach((k, v) -> {
            keysInOrder.add(k);
            valuesInOrder.add(v);
        });
        assertEquals(new ArrayList<Integer>() {{
            add(3);
            add(2);
            add(1);
        }}, keysInOrder);
        assertEquals(new ArrayList<String>() {{
            add("C");
            add("B");
            add("A");
        }}, valuesInOrder);
    }

    @Test
    void testGenerateMockLookupFunction() {
        var id1 = "id1";
        var id2 = "id2";
        var id3 = "id3";
        var invalidId = "invalidId";
        var value1 = 1;
        var value2 = 2;
        var value3 = 3;

        var lookupFunction = generateMockLookupFunction(
                Pair.of(id1, value1),
                Pair.of(id2, value2),
                Pair.of(id3, value3));

        assertNull(lookupFunction.apply(invalidId));
        assertEquals(value1, lookupFunction.apply(id1));
        assertEquals(value2, lookupFunction.apply(id2));
        assertEquals(value3, lookupFunction.apply(id3));
        verify(lookupFunction).apply(invalidId);
        verify(lookupFunction).apply(id1);
        verify(lookupFunction).apply(id2);
        verify(lookupFunction).apply(id3);
    }

    @Test
    void testGenerateSimpleMockTypeHandler() {
        var values = new Integer[]{randomInt(), randomInt(), randomInt()};

        var mockIntegerHandler = generateSimpleMockTypeHandler(values);

        assertNotNull(mockIntegerHandler);
        for (var value : values) {
            assertEquals(value, mockIntegerHandler.read(value.toString()));
            assertEquals(value.toString(), mockIntegerHandler.write(value));
        }
    }

    @Test
    void testGenerateSimpleMockTypeHandlerWithWrittenValues() {
        var values = new Integer[]{randomInt(), randomInt(), randomInt()};
        var writtenValues = new String[]{randomString(), randomString(), randomString()};

        var mockIntegerHandler = generateSimpleMockTypeHandler(
                Pair.of(writtenValues[0], values[0]),
                Pair.of(writtenValues[1], values[1]),
                Pair.of(writtenValues[2], values[2]));

        assertNotNull(mockIntegerHandler);
        for (var i = 0; i < 3; i++) {
            assertEquals(values[i], mockIntegerHandler.read(writtenValues[i]));
            assertEquals(writtenValues[i], mockIntegerHandler.write(values[i]));
        }
    }

    @Test
    void testGenerateMockPersistentValuesHandlerWithSimpleHandlers() {
        var ints = new Integer[]{randomInt(), randomInt(), randomInt()};
        var doubles =
                new Double[]{randomDouble(), randomDouble(), randomDouble()};

        var persistentValuesHandlerAndHandlers =
                generateMockPersistentValuesHandlerWithSimpleHandlers(ints, doubles);

        assertNotNull(persistentValuesHandlerAndHandlers);
        assertNotNull(persistentValuesHandlerAndHandlers.getItem1());
        assertNotNull(persistentValuesHandlerAndHandlers.getItem2());
        var integerHandler =
                persistentValuesHandlerAndHandlers.getItem2().get(Integer.class.getCanonicalName());
        for (var value : ints) {
            assertEquals(value, integerHandler.read(value.toString()));
            //noinspection unchecked
            assertEquals(value.toString(), integerHandler.write(value));
        }
        var doubleHandler =
                persistentValuesHandlerAndHandlers.getItem2().get(Double.class.getCanonicalName());
        for (var value : doubles) {
            assertEquals(value, doubleHandler.read(value.toString()));
            //noinspection unchecked
            assertEquals(value.toString(), doubleHandler.write(value));
        }

    }

    @Test
    void testGenerateMockEntityAndHandler() {
        var writtenValue = "writtenValue";

        var handlerAndEntity = generateMockEntityAndHandler(Item.class, writtenValue);

        assertNotNull(handlerAndEntity);
        var entity = handlerAndEntity.entity;
        assertNotNull(entity);
        var handler = handlerAndEntity.handler;
        assertNotNull(handler);
        assertEquals(writtenValue, handler.write(null));
        assertSame(entity, handler.read(null));
        assertSame(entity, handler.read(""));
    }

    @Test
    void testGenerateMockEntitiesAndHandler() {
        var writtenValue1 = "writtenValue1";
        var writtenValue2 = "writtenValue2";

        var handlerAndEntities = generateMockHandlerAndEntities(Item.class,
                new String[]{writtenValue1, writtenValue2});

        assertNotNull(handlerAndEntities);
        assertNotNull(handlerAndEntities.entities);
        assertEquals(2, handlerAndEntities.entities.size());
        assertTrue(handlerAndEntities.entities.containsKey(writtenValue1));
        assertTrue(handlerAndEntities.entities.containsKey(writtenValue2));
        assertNotNull(handlerAndEntities.handler);
        assertEquals(writtenValue1,
                handlerAndEntities.handler.write(handlerAndEntities.entities.get(writtenValue1)));
        assertEquals(writtenValue2,
                handlerAndEntities.handler.write(handlerAndEntities.entities.get(writtenValue2)));
        assertSame(handlerAndEntities.entities.get(writtenValue1),
                handlerAndEntities.handler.read(writtenValue1));
        assertSame(handlerAndEntities.entities.get(writtenValue2),
                handlerAndEntities.handler.read(writtenValue2));
    }
}
