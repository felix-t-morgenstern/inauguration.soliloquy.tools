package inaugural.soliloquy.tools.tests.abstractimplementations.generic;

import inaugural.soliloquy.tools.generic.HasOneGenericParam;

public class HasOneGenericParamImpl<T> extends HasOneGenericParam<T> {
    public HasOneGenericParamImpl(T archetype) {
        super(archetype);
    }

    @Override
    public String getUnparameterizedInterfaceName() {
        return soliloquy.specs.common.shared.HasOneGenericParam.class.getCanonicalName();
    }
}
