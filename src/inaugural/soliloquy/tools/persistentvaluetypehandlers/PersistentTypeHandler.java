package inaugural.soliloquy.tools.persistentvaluetypehandlers;

import inaugural.soliloquy.tools.generic.HasOneGenericParam;
import soliloquy.specs.common.infrastructure.PersistentValueTypeHandler;

public abstract class PersistentTypeHandler<T> extends HasOneGenericParam<T>
        implements PersistentValueTypeHandler<T> {

    @Override
    public abstract T getArchetype();

    @Override
    public String getUnparameterizedInterfaceName() {
        return PersistentValueTypeHandler.class.getCanonicalName();
    }
}
