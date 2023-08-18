package inaugural.soliloquy.tools.tests.fakes;

import soliloquy.specs.common.shared.HasOneGenericParam;

public class FakeHasOneGenericParam<P> implements HasOneGenericParam<P> {
    public P _archetype;

    public static final String INTERFACE_NAME = "interfaceName";

    public FakeHasOneGenericParam(P archetype) {
        _archetype = archetype;
    }

    @Override
    public P archetype() {
        return _archetype;
    }

    @Override
    public String getInterfaceName() {
        return INTERFACE_NAME;
    }
}
