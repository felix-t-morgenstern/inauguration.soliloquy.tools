package inaugural.soliloquy.tools.persistence;

import com.google.gson.Gson;
import inaugural.soliloquy.tools.generic.AbstractHasOneGenericParam;
import soliloquy.specs.common.persistence.TypeHandler;

public abstract class AbstractTypeHandler<T> extends AbstractHasOneGenericParam<T>
        implements TypeHandler<T> {
    protected static final Gson GSON = new Gson();

    protected AbstractTypeHandler(T archetype) {
        super(archetype);
    }

    @Override
    protected String getUnparameterizedInterfaceName() {
        return TypeHandler.class.getCanonicalName();
    }
}
