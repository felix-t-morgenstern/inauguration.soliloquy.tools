package inaugural.soliloquy.tools.tests.persistence;

import inaugural.soliloquy.tools.tests.abstractimplementations.persistence.PersistentDataStructureWithTwoGenericParamsHandlerImpl;
import inaugural.soliloquy.tools.tests.fakes.FakePersistentValuesHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.shared.HasTwoGenericParams;

import static org.junit.jupiter.api.Assertions.*;

class PersistentDataStructureWithTwoGenericParamsHandlerTests {
    private final FakePersistentValuesHandler PERSISTENT_VALUES_HANDLER =
            new FakePersistentValuesHandler();

    @SuppressWarnings("rawtypes")
    private PersistentDataStructureWithTwoGenericParamsHandlerImpl<HasTwoGenericParams>
            _persistentDataStructureWithTwoGenericParamsHandler;

    @BeforeEach
    void setUp() {
        _persistentDataStructureWithTwoGenericParamsHandler =
                new PersistentDataStructureWithTwoGenericParamsHandlerImpl<>(
                        PERSISTENT_VALUES_HANDLER);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> new PersistentDataStructureWithTwoGenericParamsHandlerImpl<>(null));
    }

    @Test
    void testGetArchetype() {
        assertThrows(UnsupportedOperationException.class,
                _persistentDataStructureWithTwoGenericParamsHandler::getArchetype);
    }

    @Test
    void testGetInterfaceName() {
        assertThrows(UnsupportedOperationException.class,
                _persistentDataStructureWithTwoGenericParamsHandler::getInterfaceName);
    }

    @Test
    void testGenerateArchetype() {
        Object output1 = new Object();
        Object output2 = new Object();
        PERSISTENT_VALUES_HANDLER.Outputs.add(output1);
        PERSISTENT_VALUES_HANDLER.Outputs.add(output2);
        String param1 = "param1";
        String param2 = "param2";

        @SuppressWarnings("rawtypes")
        HasTwoGenericParams generated = _persistentDataStructureWithTwoGenericParamsHandler
                .generateArchetype(HasTwoGenericParams.class.getCanonicalName() + "<" +
                        param1 + "," + param2 + ">");

        assertNotNull(generated);
        assertEquals(param1, PERSISTENT_VALUES_HANDLER.Inputs.get(0));
        assertEquals(param2, PERSISTENT_VALUES_HANDLER.Inputs.get(1));
        assertSame(output1, generated.getFirstArchetype());
        assertSame(output2, generated.getSecondArchetype());
    }

    @Test
    void testGenerateArchetypeWithInvalidParams() {
        String param1 = "param1";
        String param2 = "param2";

        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithTwoGenericParamsHandler
                        .generateArchetype(null));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithTwoGenericParamsHandler
                        .generateArchetype(""));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithTwoGenericParamsHandler
                        .generateArchetype(HasTwoGenericParams.class.getCanonicalName() + "" +
                                param1 + "," + param2 + ">"));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithTwoGenericParamsHandler
                        .generateArchetype(HasTwoGenericParams.class.getCanonicalName() + "<" +
                                "" + "," + param2 + ">"));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithTwoGenericParamsHandler
                        .generateArchetype(HasTwoGenericParams.class.getCanonicalName() + "<" +
                                param1 + "" + param2 + ">"));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithTwoGenericParamsHandler
                        .generateArchetype(HasTwoGenericParams.class.getCanonicalName() + "<" +
                                param1 + "," + "" + ">"));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithTwoGenericParamsHandler
                        .generateArchetype(HasTwoGenericParams.class.getCanonicalName() + "<" +
                                param1 + "," + param2 + ""));
    }
}
