package inaugural.soliloquy.tools.persistence;

import inaugural.soliloquy.tools.generic.HasOneGenericParam;
import soliloquy.specs.common.persistence.PersistentValueTypeHandler;

public abstract class PersistentTypeHandler<T> extends HasOneGenericParam<T>
        implements PersistentValueTypeHandler<T> {

    @Override
    public abstract T getArchetype();

    @Override
    public String getUnparameterizedInterfaceName() {
        return PersistentValueTypeHandler.class.getCanonicalName();
    }
}
