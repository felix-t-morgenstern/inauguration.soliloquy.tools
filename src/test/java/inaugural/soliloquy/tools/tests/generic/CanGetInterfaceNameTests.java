package inaugural.soliloquy.tools.tests.generic;

import inaugural.soliloquy.tools.generic.CanGetInterfaceName;
import inaugural.soliloquy.tools.tests.fakes.FakeHasOneGenericParam;
import inaugural.soliloquy.tools.tests.fakes.FakeSoliloquyClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.shared.SoliloquyClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CanGetInterfaceNameTests {
    private CanGetInterfaceName _canGetInterfaceName;

    @BeforeEach
    void setUp() {
        _canGetInterfaceName = new CanGetInterfaceName();
    }

    @Test
    void testGetNonSoliloquyClassInterfaceName() {
        assertEquals(Integer.class.getCanonicalName(), _canGetInterfaceName.getProperTypeName(0));
    }

    @Test
    void testGetSoliloquyClassInterfaceName() {
        assertEquals(SoliloquyClass.class.getCanonicalName(),
                _canGetInterfaceName.getProperTypeName(new FakeSoliloquyClass()));
    }

    @Test
    void testIgnoresTypeParameters() {
        assertEquals(FakeHasOneGenericParam.INTERFACE_NAME,
                _canGetInterfaceName.getProperTypeName(new FakeHasOneGenericParam<>(0)));
    }
}
