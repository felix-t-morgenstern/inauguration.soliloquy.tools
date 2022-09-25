package inaugural.soliloquy.tools.tests.abstractimplementations.generic;

import inaugural.soliloquy.tools.generic.AbstractHasTwoGenericParams;

public class HasTwoGenericParamsImpl<T1, T2> extends AbstractHasTwoGenericParams<T1, T2> {
    public static final String UNPARAMETERIZED_INTERFACE_NAME = "unparameterizedInterfaceName";

    public HasTwoGenericParamsImpl(T1 archetype1, T2 archetype2) {
        super(archetype1, archetype2);
    }

    @Override
    protected String getUnparameterizedInterfaceName() {
        return UNPARAMETERIZED_INTERFACE_NAME;
    }
}
