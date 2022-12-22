package inaugural.soliloquy.tools.testing;

import soliloquy.specs.common.persistence.PersistentValuesHandler;
import soliloquy.specs.common.persistence.TypeHandler;
import soliloquy.specs.common.valueobjects.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Mock {
    public static <T> TypeHandler<T> generateSimpleMockTypeHandler(T[] values) {
        //noinspection unchecked
        TypeHandler<T> mockHandler = mock(TypeHandler.class);

        for (T value : values) {
            when(mockHandler.write(value)).thenReturn(value.toString());
            when(mockHandler.read(value.toString())).thenReturn(value);
        }

        return mockHandler;
    }

    /** @noinspection rawtypes*/
    public static Pair<PersistentValuesHandler, Map<String, TypeHandler>> generateMockPersistentValuesHandlerWithSimpleHandlers(Object[]... valueSets) {
        PersistentValuesHandler mockPersistentValuesHandler = mock(PersistentValuesHandler.class);
        HashMap<String, TypeHandler> handlers = new HashMap<>();

        for (Object[] valueSet : valueSets) {
            String type = valueSet[0].getClass().getCanonicalName();

            TypeHandler typeHandler = generateSimpleMockTypeHandler(valueSet);

            //noinspection unchecked
            when(mockPersistentValuesHandler.getTypeHandler(type)).thenReturn(typeHandler);
            handlers.put(type, typeHandler);
        }

        return Pair.of(mockPersistentValuesHandler, handlers);
    }

    public static <T> TypeAndHandler<T> generateMockEntityAndHandler(Class<T> clazz,
                                                                     String writtenValue) {
        T mockEntity = mock(clazz);
        //noinspection unchecked
        TypeHandler<T> mockHandler = mock(TypeHandler.class);

        when(mockHandler.read(anyString())).thenReturn(mockEntity);
        when(mockHandler.read(null)).thenReturn(mockEntity);
        when(mockHandler.write(any())).thenReturn(writtenValue);

        return new TypeAndHandler<>(mockEntity, mockHandler);
    }

    public static class TypeAndHandler<T> {
        public T entity;
        public TypeHandler<T> handler;

        public TypeAndHandler(T entity, TypeHandler<T> handler) {
            this.entity = entity;
            this.handler = handler;
        }
    }
}
