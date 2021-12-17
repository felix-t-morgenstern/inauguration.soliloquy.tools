package inaugural.soliloquy.tools.tests.abstractimplementations.persistence;

import inaugural.soliloquy.tools.persistence.AbstractTypeHandler;

public class TypeHandlerImpl<T> extends AbstractTypeHandler<T> {
    public TypeHandlerImpl(T archetype) {
        super(archetype);
    }

    // NB: To be handled by implementation
    @Override
    public T read(String s) throws IllegalArgumentException {
        return null;
    }

    // NB: To be handled by implementation
    @Override
    public String write(T t) {
        return null;
    }
}
