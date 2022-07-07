package inaugural.soliloquy.tools.persistence;

import com.google.gson.Gson;
import inaugural.soliloquy.tools.generic.AbstractHasOneGenericParam;
import soliloquy.specs.common.persistence.TypeHandler;

public abstract class AbstractTypeHandler<T> extends AbstractHasOneGenericParam<T>
        implements TypeHandler<T> {
    @SuppressWarnings("unused")
    protected static final Gson JSON = new Gson();

    protected AbstractTypeHandler(T archetype) {
        super(archetype);
    }

    @Override
    protected String getUnparameterizedInterfaceName() {
        return TypeHandler.class.getCanonicalName();
    }

    @Override
    public String toString() {
        return getInterfaceName();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AbstractTypeHandler && obj.hashCode() == hashCode();
    }
}
