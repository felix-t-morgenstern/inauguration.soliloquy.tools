package inaugural.soliloquy.tools.tests.persistence;

import inaugural.soliloquy.tools.tests.abstractimplementations.generic.HasOneGenericParamImpl;
import inaugural.soliloquy.tools.tests.abstractimplementations.generic.HasTwoGenericParamsImpl;
import inaugural.soliloquy.tools.tests.abstractimplementations.persistence.TypeWithOneGenericParamHandlerImpl;
import inaugural.soliloquy.tools.tests.fakes.FakePersistentValuesHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.persistence.TypeHandler;
import soliloquy.specs.common.shared.HasOneGenericParam;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTypeWithOneGenericParamHandlerTests {
    private final HasOneGenericParam<Integer> ARCHETYPE = new HasOneGenericParamImpl<>(0);
    private final FakePersistentValuesHandler PERSISTENT_VALUES_HANDLER =
            new FakePersistentValuesHandler();
    @SuppressWarnings("rawtypes")
    private final Function<Object, HasOneGenericParam> TYPE_FACTORY =
            HasOneGenericParamImpl::new;

    @SuppressWarnings("rawtypes")
    private TypeWithOneGenericParamHandlerImpl<HasOneGenericParam>
            _typeWithOneGenericParameterHandler;

    @BeforeEach
    void setUp() {
        _typeWithOneGenericParameterHandler =
                new TypeWithOneGenericParamHandlerImpl<>(
                        ARCHETYPE,
                        PERSISTENT_VALUES_HANDLER,
                        TYPE_FACTORY);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () ->
                new TypeWithOneGenericParamHandlerImpl<>(
                        null,
                        PERSISTENT_VALUES_HANDLER,
                        TYPE_FACTORY));
        assertThrows(IllegalArgumentException.class, () ->
                new TypeWithOneGenericParamHandlerImpl<>(
                        new HasOneGenericParamImpl<>(null),
                        PERSISTENT_VALUES_HANDLER,
                        TYPE_FACTORY));
        assertThrows(IllegalArgumentException.class, () ->
                new TypeWithOneGenericParamHandlerImpl<>(
                        ARCHETYPE,
                        null,
                        TYPE_FACTORY));
        assertThrows(IllegalArgumentException.class, () ->
                new TypeWithOneGenericParamHandlerImpl<>(
                        ARCHETYPE,
                        PERSISTENT_VALUES_HANDLER,
                        null));
    }

    @Test
    void testGetArchetype() {
        assertSame(ARCHETYPE, _typeWithOneGenericParameterHandler.getArchetype());
    }

    @Test
    void testGenerateArchetype() throws InterruptedException {
        String innerType = "innerType";
        Integer generatedInnerArchetype = 123123;
        PERSISTENT_VALUES_HANDLER.Outputs.put(generatedInnerArchetype);

        @SuppressWarnings("unchecked") HasOneGenericParam<Integer> generatedArchetype =
                (HasOneGenericParam<Integer>) _typeWithOneGenericParameterHandler
                        .generateArchetype(innerType);

        assertNotNull(generatedArchetype);
        assertEquals(1, PERSISTENT_VALUES_HANDLER.Inputs.size());
        assertEquals(innerType, PERSISTENT_VALUES_HANDLER.Inputs.get(0));
        assertEquals(generatedInnerArchetype, generatedArchetype.getArchetype());
    }

    @Test
    void testGenerateArchetypeWithInvalidParams() throws InterruptedException {
        PERSISTENT_VALUES_HANDLER.Outputs.put(123123);

        assertThrows(IllegalArgumentException.class, () ->
                _typeWithOneGenericParameterHandler.generateArchetype(null));
        assertThrows(IllegalArgumentException.class, () ->
                _typeWithOneGenericParameterHandler.generateArchetype(""));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(TypeHandler.class.getCanonicalName() + "<" +
                HasTwoGenericParamsImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
                Integer.class.getCanonicalName() + ">>",
                _typeWithOneGenericParameterHandler.getInterfaceName());
    }
}
