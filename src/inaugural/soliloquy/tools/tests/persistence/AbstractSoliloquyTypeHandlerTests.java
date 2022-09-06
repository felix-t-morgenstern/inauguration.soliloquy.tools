package inaugural.soliloquy.tools.tests.persistence;

import inaugural.soliloquy.tools.persistence.AbstractSoliloquyTypeHandler;
import inaugural.soliloquy.tools.tests.abstractimplementations.generic.HasOneGenericParamImpl;
import inaugural.soliloquy.tools.tests.abstractimplementations.persistence.SoliloquyTypeHandlerImpl;
import inaugural.soliloquy.tools.tests.fakes.FakeHasOneGenericParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.persistence.TypeHandler;
import soliloquy.specs.common.shared.HasOneGenericParam;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractSoliloquyTypeHandlerTests {
    private HasOneGenericParam<HasOneGenericParam<Integer>> _level1Archetype;
    @SuppressWarnings("FieldCanBeLocal")
    private HasOneGenericParam<Integer> _level2Archetype;

    private AbstractSoliloquyTypeHandler<HasOneGenericParam>
            _soliloquyTypeHandler;

    @BeforeEach
    void setUp() {
        _level2Archetype = new HasOneGenericParamImpl<>(0);
        _level1Archetype = new HasOneGenericParamImpl<>(_level2Archetype);

        _soliloquyTypeHandler = new SoliloquyTypeHandlerImpl<>(HasOneGenericParam.class);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () ->
                new HasOneGenericParamImpl<>(null));
        assertThrows(IllegalArgumentException.class, () ->
                new HasOneGenericParamImpl<>(new FakeHasOneGenericParam<>(null)));
        assertThrows(IllegalArgumentException.class, () ->
                new HasOneGenericParamImpl<>(new FakeHasOneGenericParam<>(
                        new FakeHasOneGenericParam<>(null))));
    }

    @Test
    void testGetArchetype() {
        assertEquals(HasOneGenericParam.class.getCanonicalName(), _soliloquyTypeHandler.getArchetype().getInterfaceName());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(TypeHandler.class.getCanonicalName() + "<" +
                        HasOneGenericParam.class.getCanonicalName() + ">",
                _soliloquyTypeHandler.getInterfaceName());
    }
}
