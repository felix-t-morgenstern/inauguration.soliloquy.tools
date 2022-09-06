package inaugural.soliloquy.tools.tests.abstractimplementations.persistence;

import inaugural.soliloquy.tools.persistence.AbstractSoliloquyTypeHandler;
import soliloquy.specs.common.shared.SoliloquyClass;

public class SoliloquyTypeHandlerImpl<T extends SoliloquyClass>
        extends AbstractSoliloquyTypeHandler<T> {
    public SoliloquyTypeHandlerImpl(Class<T> type) {
        super(type);
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
