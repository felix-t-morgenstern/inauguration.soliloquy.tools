package inaugural.soliloquy.tools.generic;

public abstract class AbstractHasTwoGenericParams<T1, T2> extends AbstractHasGenericParams
        implements soliloquy.specs.common.shared.HasTwoGenericParams<T1, T2> {
    private final T1 ARCHETYPE_1;
    private final T2 ARCHETYPE_2;

    protected AbstractHasTwoGenericParams(T1 archetype1, T2 archetype2) {
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
        return getUnparameterizedInterfaceName() + "<" + getProperTypeName(ARCHETYPE_1) + "," +
                getProperTypeName(ARCHETYPE_2) + ">";
    }
}
