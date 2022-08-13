package inaugural.soliloquy.tools.tests.generic;

import inaugural.soliloquy.tools.generic.AbstractHasOneGenericParam;
import inaugural.soliloquy.tools.tests.abstractimplementations.generic.HasOneGenericParamImpl;
import inaugural.soliloquy.tools.tests.fakes.FakeHasOneGenericParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.shared.HasOneGenericParam;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractHasOneGenericParamTests {
    private HasOneGenericParam<HasOneGenericParam<Integer>> _level1Archetype;
    @SuppressWarnings("FieldCanBeLocal")
    private HasOneGenericParam<Integer> _level2Archetype;

    private AbstractHasOneGenericParam<
            HasOneGenericParam<HasOneGenericParam<Integer>>> _hasOneGenericParam;

    @BeforeEach
    void setUp() {
        _level2Archetype = new HasOneGenericParamImpl<>(0);
        _level1Archetype = new HasOneGenericParamImpl<>(_level2Archetype);

        _hasOneGenericParam = new HasOneGenericParamImpl<>(_level1Archetype);
    }

    @Test
    void testConstructorWithInvalidParams() {
        HasOneGenericParam<HasOneGenericParam<Integer>> archetypeWithNullArchetype =
                new FakeHasOneGenericParam<>(null);

        assertThrows(IllegalArgumentException.class,
                () -> new HasOneGenericParamImpl<>(null));
        assertThrows(IllegalArgumentException.class,
                () -> new HasOneGenericParamImpl<>(archetypeWithNullArchetype));
    }

    @Test
    void testGetArchetype() {
        assertSame(_level1Archetype, _hasOneGenericParam.getArchetype());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(HasOneGenericParamImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
                        HasOneGenericParamImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
                        HasOneGenericParamImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
                        Integer.class.getCanonicalName() + ">>>",
                _hasOneGenericParam.getInterfaceName());
    }
}
