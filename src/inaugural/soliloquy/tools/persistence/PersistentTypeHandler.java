package inaugural.soliloquy.tools.persistence;

import inaugural.soliloquy.tools.generic.CanGetInterfaceName;
import soliloquy.specs.common.persistence.PersistentValueTypeHandler;

// TODO: Consider having tools.PersistentTypeHandler extend tools.HasOneGenericParam
public abstract class PersistentTypeHandler<T> extends CanGetInterfaceName
        implements PersistentValueTypeHandler<T> {
    @Override
    public String getInterfaceName() {
        return PersistentValueTypeHandler.class.getCanonicalName() + "<" +
                getProperTypeName(getArchetype()) + ">";
    }

    @Override
    public T getArchetype() {
        throw new UnsupportedOperationException();
    }
}
