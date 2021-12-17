package inaugural.soliloquy.tools.tests.persistence;

import inaugural.soliloquy.tools.tests.abstractimplementations.generic.HasTwoGenericParamsImpl;
import inaugural.soliloquy.tools.tests.abstractimplementations.persistence.TypeWithTwoGenericParamsHandlerImpl;
import inaugural.soliloquy.tools.tests.fakes.FakePersistentValuesHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.persistence.TypeHandler;
import soliloquy.specs.common.shared.HasTwoGenericParams;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class AbstractTypeWithTwoGenericParamsHandlerTests {
    private final HasTwoGenericParams<Integer, String> ARCHETYPE =
            new HasTwoGenericParamsImpl<>(0, "");
    private final FakePersistentValuesHandler PERSISTENT_VALUES_HANDLER =
            new FakePersistentValuesHandler();
    @SuppressWarnings({"rawtypes", "unchecked"})
    private final Function<Object, Function<Object, HasTwoGenericParams>> TYPE_FACTORY =
            archetype1 -> archetype2 -> new HasTwoGenericParamsImpl(archetype1, archetype2);

    @SuppressWarnings("rawtypes")
    private TypeWithTwoGenericParamsHandlerImpl<HasTwoGenericParams>
            _typeWithTwoGenericParamsHandler;

    @BeforeEach
    void setUp() {
        _typeWithTwoGenericParamsHandler =
                new TypeWithTwoGenericParamsHandlerImpl<>(
                        ARCHETYPE,
                        PERSISTENT_VALUES_HANDLER,
                        TYPE_FACTORY);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () ->
                new TypeWithTwoGenericParamsHandlerImpl<>(
                        null,
                        PERSISTENT_VALUES_HANDLER,
                        TYPE_FACTORY));
        assertThrows(IllegalArgumentException.class, () ->
                new TypeWithTwoGenericParamsHandlerImpl<>(
                        new HasTwoGenericParamsImpl<>(null, ""),
                        PERSISTENT_VALUES_HANDLER,
                        TYPE_FACTORY));
        assertThrows(IllegalArgumentException.class, () ->
                new TypeWithTwoGenericParamsHandlerImpl<>(
                        new HasTwoGenericParamsImpl<>(0, null),
                        PERSISTENT_VALUES_HANDLER,
                        TYPE_FACTORY));
        assertThrows(IllegalArgumentException.class, () ->
                new TypeWithTwoGenericParamsHandlerImpl<>(
                        ARCHETYPE,
                        null,
                        TYPE_FACTORY));
        assertThrows(IllegalArgumentException.class, () ->
                new TypeWithTwoGenericParamsHandlerImpl<>(
                        ARCHETYPE,
                        PERSISTENT_VALUES_HANDLER,
                        null));
    }

    @Test
    void testGetArchetype() {
        assertSame(ARCHETYPE, _typeWithTwoGenericParamsHandler.getArchetype());
    }

    @Test
    void testGenerateArchetype() throws InterruptedException {
        String innerType1 = "innerType1";
        String innerType2 = "innerType2";
        Integer generatedInnerArchetype1 = 123123;
        String generatedInnerArchetype2 = "archetype2";
        PERSISTENT_VALUES_HANDLER.Outputs.put(generatedInnerArchetype1);
        PERSISTENT_VALUES_HANDLER.Outputs.put(generatedInnerArchetype2);

        @SuppressWarnings("unchecked") HasTwoGenericParams<Integer, String> generatedArchetype =
                (HasTwoGenericParams<Integer, String>) _typeWithTwoGenericParamsHandler
                        .generateArchetype(innerType1, innerType2);

        assertNotNull(generatedArchetype);
        assertEquals(2, PERSISTENT_VALUES_HANDLER.Inputs.size());
        assertEquals(innerType1, PERSISTENT_VALUES_HANDLER.Inputs.get(0));
        assertEquals(innerType2, PERSISTENT_VALUES_HANDLER.Inputs.get(1));
        assertEquals(generatedInnerArchetype1, generatedArchetype.getFirstArchetype());
        assertEquals(generatedInnerArchetype2, generatedArchetype.getSecondArchetype());
    }

    @Test
    void testGenerateArchetypeWithInvalidParams() throws InterruptedException {
        PERSISTENT_VALUES_HANDLER.Outputs.put(111);
        PERSISTENT_VALUES_HANDLER.Outputs.put(222);
        PERSISTENT_VALUES_HANDLER.Outputs.put(333);
        PERSISTENT_VALUES_HANDLER.Outputs.put(444);

        assertThrows(IllegalArgumentException.class, () ->
                _typeWithTwoGenericParamsHandler.generateArchetype(null, "innerType2"));
        assertThrows(IllegalArgumentException.class, () ->
                _typeWithTwoGenericParamsHandler.generateArchetype("", "innerType2"));
        assertThrows(IllegalArgumentException.class, () ->
                _typeWithTwoGenericParamsHandler.generateArchetype("innerType1", null));
        assertThrows(IllegalArgumentException.class, () ->
                _typeWithTwoGenericParamsHandler.generateArchetype("innerType1", ""));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(TypeHandler.class.getCanonicalName() + "<" +
                HasTwoGenericParamsImpl.UNPARAMETERIZED_INTERFACE_NAME + "<" +
                Integer.class.getCanonicalName() + "," + String.class.getCanonicalName() + ">>",
                _typeWithTwoGenericParamsHandler.getInterfaceName());
    }
}
