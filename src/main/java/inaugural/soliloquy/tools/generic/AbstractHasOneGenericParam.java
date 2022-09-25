package inaugural.soliloquy.tools.generic;

public abstract class AbstractHasOneGenericParam<T> extends AbstractHasGenericParams
        implements soliloquy.specs.common.shared.HasOneGenericParam<T> {
    protected final T ARCHETYPE;

    protected AbstractHasOneGenericParam(T archetype) {
        ARCHETYPE = archetypeCheck(archetype, "archetype");
    }

    @Override
    public T getArchetype() {
        return ARCHETYPE;
    }

    @Override
    public String getInterfaceName() {
        return getUnparameterizedInterfaceName() + "<" + getProperTypeName(ARCHETYPE) + ">";
    }
}
