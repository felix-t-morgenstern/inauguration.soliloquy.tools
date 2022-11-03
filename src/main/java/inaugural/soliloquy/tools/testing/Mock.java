package inaugural.soliloquy.tools.testing;

import soliloquy.specs.common.persistence.TypeHandler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Mock {
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
