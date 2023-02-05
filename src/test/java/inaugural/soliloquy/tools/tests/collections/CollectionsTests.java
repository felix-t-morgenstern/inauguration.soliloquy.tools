package inaugural.soliloquy.tools.tests.collections;

import org.junit.jupiter.api.Test;
import soliloquy.specs.common.valueobjects.Pair;

import java.util.ArrayList;
import java.util.HashMap;

import static inaugural.soliloquy.tools.collections.Collections.*;
import static inaugural.soliloquy.tools.testing.Assertions.assertEqualsAndNotSame;
import static org.junit.jupiter.api.Assertions.*;

class CollectionsTests {
    @Test
    void testArrayOf() {
        var array = arrayOf(123, 456, 789);

        assertNotNull(array);
        assertEquals(3, array.length);
        assertEquals(123, array[0]);
        assertEquals(456, array[1]);
        assertEquals(789, array[2]);
    }

    @Test
    void testListOfFromArray() {
        var list = listOf(1, 2, 3);

        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testListOfFromList() {
        var originalList = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};

        var list = listOf(originalList);

        assertNotNull(list);
        assertEqualsAndNotSame(originalList, list);
    }

    @Test
    void testMapOfFromPairs() {
        var map = mapOf(Pair.of("A", 1), Pair.of("B", 2), Pair.of("C", 3));

        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(1, map.get("A"));
        assertEquals(2, map.get("B"));
        assertEquals(3, map.get("C"));
    }

    @Test
    void testMapOfFromMap() {
        var originalMap = new HashMap<String, Integer>() {{
            put("A", 1);
            put("B", 2);
            put("C", 3);
        }};

        var map = mapOf(originalMap);

        assertNotNull(map);
        assertEqualsAndNotSame(originalMap, map);
    }
}
