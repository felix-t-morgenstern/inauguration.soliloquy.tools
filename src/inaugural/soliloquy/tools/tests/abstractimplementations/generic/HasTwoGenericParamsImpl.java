package inaugural.soliloquy.tools.tests.abstractimplementations.generic;

import inaugural.soliloquy.tools.generic.HasTwoGenericParams;

public class HasTwoGenericParamsImpl<T1, T2> extends HasTwoGenericParams<T1, T2> {
    public HasTwoGenericParamsImpl(T1 archetype1, T2 archetype2) {
        super(archetype1, archetype2);
    }

    @Override
    public String getUnparameterizedInterfaceName() {
        return soliloquy.specs.common.shared.HasTwoGenericParams.class.getCanonicalName();
    }
}
