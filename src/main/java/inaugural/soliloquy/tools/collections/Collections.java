package inaugural.soliloquy.tools.collections;

import soliloquy.specs.common.valueobjects.Pair;

import java.util.*;

public class Collections {
    @SafeVarargs
    public static <T> List<T> listOf(T... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    @SafeVarargs
    public static <K, V> Map<K, V> mapOf(Pair<K, V>... items) {
        var map = new HashMap<K, V>();
        for (var item : items) {
            map.put(item.getItem1(), item.getItem2());
        }
        return map;
    }
}
