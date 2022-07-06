package inaugural.soliloquy.tools.tests.persistence;

import inaugural.soliloquy.tools.persistence.AbstractTypeHandler;
import inaugural.soliloquy.tools.tests.abstractimplementations.generic.HasOneGenericParamImpl;
import inaugural.soliloquy.tools.tests.abstractimplementations.generic.HasTwoGenericParamsImpl;
import inaugural.soliloquy.tools.tests.abstractimplementations.persistence.TypeHandlerImpl;
import inaugural.soliloquy.tools.tests.fakes.FakeAbstractTypeHandler;
import inaugural.soliloquy.tools.tests.fakes.FakeHasOneGenericParam;
import inaugural.soliloquy.tools.tests.fakes.FakeObjectWithArbitraryHashCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.persistence.TypeHandler;
import soliloquy.specs.common.shared.HasOneGenericParam;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTypeHandlerTests {
    private HasOneGenericParam<HasOneGenericParam<Integer>> _level1Archetype;
    @SuppressWarnings("FieldCanBeLocal")
    private HasOneGenericParam<Integer> _level2Archetype;

    private final int HASH_CODE = (TypeHandler.class.getCanonicalName() + "<" +
            HasTwoGenericParamsImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
            HasTwoGenericParamsImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
            Integer.class.getCanonicalName() + ">>>").hashCode();

    private AbstractTypeHandler<HasOneGenericParam<HasOneGenericParam<Integer>>> _typeHandler;

    @BeforeEach
    void setUp() {
        _level2Archetype = new HasOneGenericParamImpl<>(0);
        _level1Archetype = new HasOneGenericParamImpl<>(_level2Archetype);

        _typeHandler = new TypeHandlerImpl<>(_level1Archetype);
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
        assertSame(_level1Archetype, _typeHandler.getArchetype());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(TypeHandler.class.getCanonicalName() + "<" +
                HasTwoGenericParamsImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
                HasTwoGenericParamsImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
                Integer.class.getCanonicalName() + ">>>",
                _typeHandler.getInterfaceName());
    }

    @Test
    void testToString() {
        assertEquals(TypeHandler.class.getCanonicalName() + "<" +
                        HasTwoGenericParamsImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
                        HasTwoGenericParamsImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
                        Integer.class.getCanonicalName() + ">>>",
                _typeHandler.toString());
    }

    @Test
    void testHashCode() {
        assertEquals(HASH_CODE, _typeHandler.hashCode());
    }

    @Test
    void testEquals() {
        FakeAbstractTypeHandler<HasOneGenericParam<HasOneGenericParam<Integer>>> equalHandler =
                new FakeAbstractTypeHandler<>(_level1Archetype);
        equalHandler.HashCode = HASH_CODE;

        FakeObjectWithArbitraryHashCode unequalHandler1 = new FakeObjectWithArbitraryHashCode();
        unequalHandler1.HashCode = HASH_CODE;

        FakeAbstractTypeHandler<HasOneGenericParam<HasOneGenericParam<Integer>>> unequalHandler2 =
                new FakeAbstractTypeHandler<>(_level1Archetype);
        unequalHandler2.HashCode = HASH_CODE + 1;

        assertEquals(_typeHandler, equalHandler);
        assertNotEquals(_typeHandler, unequalHandler1);
        assertNotEquals(_typeHandler, unequalHandler2);
    }
}
