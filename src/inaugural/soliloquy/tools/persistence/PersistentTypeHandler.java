package inaugural.soliloquy.tools.persistence;

import inaugural.soliloquy.tools.generic.HasOneGenericParam;
import soliloquy.specs.common.persistence.PersistentValueTypeHandler;

// TODO: Consider having tools.PersistentTypeHandler extend tools.HasOneGenericParam
public abstract class PersistentTypeHandler<T> extends HasOneGenericParam<T>
        implements PersistentValueTypeHandler<T> {
    @Override
    protected String getUnparameterizedInterfaceName() {
        return PersistentValueTypeHandler.class.getCanonicalName();
    }
}
