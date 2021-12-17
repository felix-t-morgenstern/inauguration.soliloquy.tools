package inaugural.soliloquy.tools.tests.abstractimplementations.generic;

import inaugural.soliloquy.tools.generic.AbstractHasOneGenericParam;

public class HasOneGenericParamImpl<T> extends AbstractHasOneGenericParam<T> {
    public static final String UNPARAMETERIZED_INTERFACE_NAME = "unparameterizedInterfaceName";

    public HasOneGenericParamImpl(T archetype) {
        super(archetype);
    }

    @Override
    protected String getUnparameterizedInterfaceName() {
        return UNPARAMETERIZED_INTERFACE_NAME;
    }
}
