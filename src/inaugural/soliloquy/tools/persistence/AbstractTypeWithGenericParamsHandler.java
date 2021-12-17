package inaugural.soliloquy.tools.persistence;

import inaugural.soliloquy.tools.Check;
import soliloquy.specs.common.persistence.PersistentValuesHandler;
import soliloquy.specs.common.persistence.TypeHandler;

public abstract class AbstractTypeWithGenericParamsHandler<T>
        extends AbstractTypeHandler<T> implements TypeHandler<T> {
    protected final PersistentValuesHandler PERSISTENT_VALUES_HANDLER;

    protected AbstractTypeWithGenericParamsHandler(T archetype,
                                                   PersistentValuesHandler persistentValuesHandler)
    {
        super(archetype);
        PERSISTENT_VALUES_HANDLER =
                Check.ifNull(persistentValuesHandler, "persistentValuesHandler");
    }

    @Override
    public String getUnparameterizedInterfaceName() {
        return TypeHandler.class.getCanonicalName();
    }
}
