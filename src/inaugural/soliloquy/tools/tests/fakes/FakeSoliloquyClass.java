package inaugural.soliloquy.tools.tests.fakes;

import soliloquy.specs.common.shared.SoliloquyClass;

public class FakeSoliloquyClass implements SoliloquyClass {
    @Override
    public String getInterfaceName() {
        return SoliloquyClass.class.getCanonicalName();
    }
}
