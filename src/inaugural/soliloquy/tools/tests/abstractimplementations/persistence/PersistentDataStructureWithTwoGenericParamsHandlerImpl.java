package inaugural.soliloquy.tools.tests.abstractimplementations.persistence;

import inaugural.soliloquy.tools.persistence.PersistentDataStructureWithTwoGenericParamsHandler;
import inaugural.soliloquy.tools.tests.fakes.FakeHasTwoGenericParams;
import soliloquy.specs.common.persistence.PersistentValuesHandler;

public class PersistentDataStructureWithTwoGenericParamsHandlerImpl<T>
        extends PersistentDataStructureWithTwoGenericParamsHandler<T> {
    public PersistentDataStructureWithTwoGenericParamsHandlerImpl(
            PersistentValuesHandler persistentValuesHandler) {
        super(persistentValuesHandler);
    }

    @Override
    protected Object generateTypeFromFactory(Object archetype1, Object archetype2) {
        return new FakeHasTwoGenericParams<>(archetype1, archetype2);
    }

    @Override
    public T read(String s) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String write(T t) {
        throw new UnsupportedOperationException();
    }
}
