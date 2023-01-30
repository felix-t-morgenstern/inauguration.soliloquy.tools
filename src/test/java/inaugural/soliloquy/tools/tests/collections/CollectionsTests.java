package inaugural.soliloquy.tools.tests.collections;

import org.junit.jupiter.api.Test;
import soliloquy.specs.common.valueobjects.Pair;

import static inaugural.soliloquy.tools.collections.Collections.*;
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
    void testListOf() {
        var list = listOf(1, 2, 3);

        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testMapOf() {
        var map = mapOf(Pair.of("A", 1), Pair.of("B", 2), Pair.of("C", 3));

        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(1, map.get("A"));
        assertEquals(2, map.get("B"));
        assertEquals(3, map.get("C"));
    }
}
