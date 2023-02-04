package inaugural.soliloquy.tools.testing;

import soliloquy.specs.common.persistence.PersistentValuesHandler;
import soliloquy.specs.common.persistence.TypeHandler;
import soliloquy.specs.common.shared.HasId;
import soliloquy.specs.common.valueobjects.Pair;

import java.util.*;

import static inaugural.soliloquy.tools.collections.Collections.listOf;
import static inaugural.soliloquy.tools.collections.Collections.mapOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class Mock {
    public static <T extends HasId> T generateMockWithId(Class<T> clazz, String id) {
        var mock = mock(clazz);
        when(mock.id()).thenReturn(id);
        return mock;
    }

    @SafeVarargs
    public static <T> List<T> generateMockList(T... values) {
        //noinspection unchecked
        var mockList = (List<T>) mock(List.class);
        when(mockList.size()).thenReturn(values.length);
        when(mockList.iterator()).thenReturn(listOf(values).iterator());
        doCallRealMethod().when(mockList).forEach(any());

        return mockList;
    }

    @SafeVarargs
    public static <K, V> Map<K, V> generateMockMap(Pair<K, V>... keyValuePairs) {
        //noinspection unchecked
        var mockMap = (Map<K, V>) mock(Map.class);
        when(mockMap.size()).thenReturn(keyValuePairs.length);
        when(mockMap.entrySet()).thenReturn(mapOf(keyValuePairs).entrySet());
        doCallRealMethod().when(mockMap).forEach(any());

        return mockMap;
    }

    public static <T> TypeHandler<T> generateSimpleMockTypeHandler(T[] values) {
        //noinspection unchecked
        var mockHandler = (TypeHandler<T>) mock(TypeHandler.class);

        for (var value : values) {
            when(mockHandler.write(value)).thenReturn(value.toString());
            when(mockHandler.read(value.toString())).thenReturn(value);
        }

        return mockHandler;
    }

    @SafeVarargs
    public static <T> TypeHandler<T> generateSimpleMockTypeHandler(Pair<String, T>... values) {
        //noinspection unchecked
        var mockHandler = (TypeHandler<T>) mock(TypeHandler.class);

        for (var value : values) {
            when(mockHandler.read(value.getItem1())).thenReturn(value.getItem2());
            when(mockHandler.write(value.getItem2())).thenReturn(value.getItem1());
        }

        return mockHandler;
    }

    /** @noinspection rawtypes */
    public static Pair<PersistentValuesHandler, Map<String, TypeHandler>> generateMockPersistentValuesHandlerWithSimpleHandlers(
            Object[]... valueSets) {
        var mockPersistentValuesHandler = mock(PersistentValuesHandler.class);
        var handlers = new HashMap<String, TypeHandler>();

        for (var valueSet : valueSets) {
            var type = valueSet[0].getClass().getCanonicalName();

            var typeHandler = generateSimpleMockTypeHandler(valueSet);

            when(mockPersistentValuesHandler.getTypeHandler(type)).thenReturn(typeHandler);
            handlers.put(type, typeHandler);
        }

        return Pair.of(mockPersistentValuesHandler, handlers);
    }

    public static <T> HandlerAndEntity<T> generateMockEntityAndHandler(Class<T> clazz,
                                                                       String writtenValue) {
        var mockEntity = mock(clazz);
        //noinspection unchecked
        var mockHandler = (TypeHandler<T>) mock(TypeHandler.class);

        when(mockHandler.read(anyString())).thenReturn(mockEntity);
        when(mockHandler.read(null)).thenReturn(mockEntity);
        when(mockHandler.write(any())).thenReturn(writtenValue);

        return new HandlerAndEntity<>(mockEntity, mockHandler);
    }

    public static class HandlerAndEntity<T> {
        public T entity;
        public TypeHandler<T> handler;

        public HandlerAndEntity(T entity, TypeHandler<T> handler) {
            this.entity = entity;
            this.handler = handler;
        }
    }

    public static <T> HandlerAndEntities<T> generateMockHandlerAndEntities(Class<T> clazz,
                                                                           String[] writtenValues) {
        //noinspection unchecked
        var mockHandler = (TypeHandler<T>) mock(TypeHandler.class);
        var mockEntities = new HashMap<String, T>();

        for (var writtenValue : writtenValues) {
            var mockEntity = mock(clazz);
            when(mockHandler.read(writtenValue)).thenReturn(mockEntity);
            when(mockHandler.write(mockEntity)).thenReturn(writtenValue);
            mockEntities.put(writtenValue, mockEntity);
        }

        return new HandlerAndEntities<>(mockHandler, mockEntities);
    }

    public static class HandlerAndEntities<T> {
        public TypeHandler<T> handler;
        public Map<String, T> entities;

        public HandlerAndEntities(TypeHandler<T> handler, Map<String, T> entities) {
            this.handler = handler;
            this.entities = entities;
        }
    }
}
