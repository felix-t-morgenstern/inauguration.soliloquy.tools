package inaugural.soliloquy.tools.tests.persistence;

import inaugural.soliloquy.tools.persistence.AbstractSoliloquyTypeHandler;
import inaugural.soliloquy.tools.tests.abstractimplementations.generic.HasOneGenericParamImpl;
import inaugural.soliloquy.tools.tests.abstractimplementations.persistence.SoliloquyTypeHandlerImpl;
import inaugural.soliloquy.tools.tests.fakes.FakeHasOneGenericParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.persistence.TypeHandler;
import soliloquy.specs.gamestate.entities.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbstractSoliloquyTypeHandlerTests {
    private AbstractSoliloquyTypeHandler<Item> soliloquyTypeHandler;

    @BeforeEach
    void setUp() {
        soliloquyTypeHandler = new SoliloquyTypeHandlerImpl<>(Item.class);
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
        assertEquals(Item.class.getCanonicalName(),
                soliloquyTypeHandler.getArchetype().getInterfaceName());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(
                TypeHandler.class.getCanonicalName() + "<" + Item.class.getCanonicalName() + ">",
                soliloquyTypeHandler.getInterfaceName());
    }
}
