package inaugural.soliloquy.tools.persistence;

import inaugural.soliloquy.tools.generic.AbstractHasOneGenericParam;
import soliloquy.specs.common.persistence.TypeHandler;

public abstract class AbstractTypeHandler<T> extends AbstractHasOneGenericParam<T>
        implements TypeHandler<T> {
    protected AbstractTypeHandler(T archetype) {
        super(archetype);
    }

    @Override
    protected String getUnparameterizedInterfaceName() {
        return TypeHandler.class.getCanonicalName();
    }
}
