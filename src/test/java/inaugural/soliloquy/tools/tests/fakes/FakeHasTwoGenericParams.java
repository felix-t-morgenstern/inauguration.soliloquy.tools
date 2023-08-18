package inaugural.soliloquy.tools.tests.fakes;

import soliloquy.specs.common.shared.HasTwoGenericParams;

public class FakeHasTwoGenericParams<P1, P2> implements HasTwoGenericParams<P1, P2> {
    public P1 _archetype1;
    public P2 _archetype2;

    public FakeHasTwoGenericParams(P1 archetype1, P2 archetype2) {
        _archetype1 = archetype1;
        _archetype2 = archetype2;
    }

    @Override
    public P1 firstArchetype() throws IllegalStateException {
        return _archetype1;
    }

    @Override
    public P2 secondArchetype() throws IllegalStateException {
        return _archetype2;
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}
