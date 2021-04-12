package inaugural.soliloquy.tools.generic;

public abstract class HasOneGenericParam<T> extends HasGenericParams
        implements soliloquy.specs.common.shared.HasOneGenericParam<T> {
    protected final T ARCHETYPE;

    private String _parameterizedClassName;

    // Used for PersistentDataStructureWithTwoGenericParamsHandler and
    protected HasOneGenericParam() {
        ARCHETYPE = null;
    }

    protected HasOneGenericParam(T archetype) {
        ARCHETYPE = archetypeCheck(archetype, "archetype");
    }

    @Override
    public T getArchetype() {
        return ARCHETYPE;
    }

    @Override
    public String getInterfaceName() {
        if (_parameterizedClassName == null) {
            _parameterizedClassName = getUnparameterizedInterfaceName() + "<"
                    + getProperTypeName(getArchetype()) + ">";
        }
        return _parameterizedClassName;
    }
}
