package inaugural.soliloquy.tools.persistence;

import inaugural.soliloquy.tools.generic.HasOneGenericParam;
import soliloquy.specs.common.persistence.PersistentValueTypeHandler;

public abstract class PersistentDataStructureWithGenericParams<T>
        extends HasOneGenericParam<T> implements PersistentValueTypeHandler<T> {
    @Override
    protected String getUnparameterizedInterfaceName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getInterfaceName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getArchetype() {
        throw new UnsupportedOperationException();
    }
}
