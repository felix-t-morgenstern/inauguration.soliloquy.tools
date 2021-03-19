package inaugural.soliloquy.tools.generic;

public abstract class HasTwoGenericParams<T1, T2> extends HasGenericParams
        implements soliloquy.specs.common.shared.HasTwoGenericParams<T1, T2> {
    private final T1 ARCHETYPE_1;
    private final T2 ARCHETYPE_2;

    private String _parameterizedClassName;

    protected HasTwoGenericParams(T1 archetype1, T2 archetype2) {
        ARCHETYPE_1 = archetypeCheck(archetype1, "archetype1");
        ARCHETYPE_2 = archetypeCheck(archetype2, "archetype2");
    }

    @Override
    public T1 getFirstArchetype() {
        return ARCHETYPE_1;
    }

    @Override
    public T2 getSecondArchetype() {
        return ARCHETYPE_2;
    }

    @Override
    public String getInterfaceName() {
        if (_parameterizedClassName == null) {
            _parameterizedClassName = getUnparameterizedInterfaceName() +
                    "<" + getProperTypeName(getFirstArchetype()) + "," +
                    getProperTypeName(getSecondArchetype()) + ">";
        }
        return _parameterizedClassName;
    }
}
