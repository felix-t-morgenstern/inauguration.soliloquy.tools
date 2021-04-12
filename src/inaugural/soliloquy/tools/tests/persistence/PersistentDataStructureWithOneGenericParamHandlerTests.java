package inaugural.soliloquy.tools.tests.persistence;

import inaugural.soliloquy.tools.persistence.PersistentDataStructureWithOneGenericParamHandler;
import inaugural.soliloquy.tools.tests.abstractimplementations.generic.HasOneGenericParamImpl;
import inaugural.soliloquy.tools.tests.abstractimplementations.persistence.PersistentDataStructureWithOneGenericParamHandlerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.shared.HasOneGenericParam;
import soliloquy.specs.common.shared.HasTwoGenericParams;

import static org.junit.jupiter.api.Assertions.*;

class PersistentDataStructureWithOneGenericParamHandlerTests {
    @SuppressWarnings("rawtypes")
    private PersistentDataStructureWithOneGenericParamHandlerImpl<HasOneGenericParam>
            _persistentDataStructureWithOneGenericParamHandler;

    @BeforeEach
    void setUp() {
        _persistentDataStructureWithOneGenericParamHandler =
                new PersistentDataStructureWithOneGenericParamHandlerImpl<>();
    }

    @Test
    void testGetArchetype() {
        assertThrows(UnsupportedOperationException.class,
                _persistentDataStructureWithOneGenericParamHandler::getArchetype);
    }

    @Test
    void testGetInterfaceName() {
        assertThrows(UnsupportedOperationException.class,
                _persistentDataStructureWithOneGenericParamHandler::getInterfaceName);
    }

    @Test
    void testGetInnerType() {
        String innerType = _persistentDataStructureWithOneGenericParamHandler.getInnerType(
                HasOneGenericParam.class.getCanonicalName() + "<" +
                        Integer.class.getCanonicalName() + ">",
                HasOneGenericParam.class
        );

        assertEquals(Integer.class.getCanonicalName(), innerType);
    }

    @Test
    void testGetInnerTypeWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithOneGenericParamHandler.getInnerType(
                        null,
                        HasOneGenericParam.class
                ));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithOneGenericParamHandler.getInnerType(
                        "",
                        HasOneGenericParam.class
                ));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithOneGenericParamHandler.getInnerType(
                        HasOneGenericParam.class.getCanonicalName() + "" +
                                Integer.class.getCanonicalName() + ">",
                        HasOneGenericParam.class
                ));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithOneGenericParamHandler.getInnerType(
                        HasOneGenericParam.class.getCanonicalName() + "<" +
                                Integer.class.getCanonicalName() + "",
                        HasOneGenericParam.class
                ));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithOneGenericParamHandler.getInnerType(
                        HasOneGenericParam.class.getCanonicalName() + "<" +
                                Integer.class.getCanonicalName() + ">",
                        null
                ));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentDataStructureWithOneGenericParamHandler.getInnerType(
                        HasTwoGenericParams.class.getCanonicalName() + "<" +
                                Integer.class.getCanonicalName() + ">",
                        HasOneGenericParam.class
                ));

        try {
            _persistentDataStructureWithOneGenericParamHandler.getInnerType(
                    null,
                    HasOneGenericParam.class
            );
        }
        catch (IllegalArgumentException e) {
            assertEquals(PersistentDataStructureWithOneGenericParamHandler
                            .class.getCanonicalName(),
                    e.getMessage().substring(0,
                            PersistentDataStructureWithOneGenericParamHandler
                                    .class.getCanonicalName().length()));
        }
        catch (Exception e) {
            fail("IllegalArgumentException not caught");
        }
    }
}
