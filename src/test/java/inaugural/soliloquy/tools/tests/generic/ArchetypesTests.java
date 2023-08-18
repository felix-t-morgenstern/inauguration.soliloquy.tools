package inaugural.soliloquy.tools.tests.generic;

import org.junit.jupiter.api.Test;
import soliloquy.specs.common.infrastructure.List;
import soliloquy.specs.common.infrastructure.Map;
import soliloquy.specs.gamestate.entities.Character;
import soliloquy.specs.gamestate.entities.Item;

import static inaugural.soliloquy.tools.generic.Archetypes.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ArchetypesTests {
    @Test
    void testGenerateSimpleArchetype() {
        var characterArchetype = generateSimpleArchetype(Character.class);

        assertEquals(Character.class.getCanonicalName(), characterArchetype.getInterfaceName());
    }

    @Test
    void testGenerateArchetypeWithInterfaceNameOverride() {
        var override = "override";

        Character characterArchetype =
                generateArchetypeWithInterfaceNameOverride(Character.class, override);

        assertEquals("override", characterArchetype.getInterfaceName());
    }

    @Test
    void testGenerateArchetypeWithOneGenericParam() {
        //noinspection unchecked
        var hasOneGenericParamArchetype =
                (List<Integer>) generateArchetypeWithOneGenericParam(List.class,
                        generateSimpleArchetype(Item.class));

        assertNotNull(hasOneGenericParamArchetype);
        assertNotNull(hasOneGenericParamArchetype.archetype());
        assertEquals(List.class.getCanonicalName() + "<" + Item.class.getCanonicalName() + ">",
                hasOneGenericParamArchetype.getInterfaceName());
    }

    @Test
    void testGenerateArchetypeWithOneGenericParamAndInterfaceNameOverride() {
        var interfaceNameOverride = "interfaceNameOverride";

        // noinspection unchecked
        var hasOneGenericParamArchetype =
                (List<Integer>) generateArchetypeWithOneGenericParam(List.class, 0,
                        interfaceNameOverride);

        assertNotNull(hasOneGenericParamArchetype);
        assertNotNull(hasOneGenericParamArchetype.archetype());
        assertEquals(interfaceNameOverride, hasOneGenericParamArchetype.getInterfaceName());
    }

    @Test
    void testGenerateArchetypeWithTwoGenericParams() {
        //noinspection unchecked
        var hasTwoGenericParamsArchetype =
                (Map<String, Integer>) generateArchetypeWithTwoGenericParams(Map.class,
                        generateSimpleArchetype(Item.class), 0);

        assertNotNull(hasTwoGenericParamsArchetype);
        assertNotNull(hasTwoGenericParamsArchetype.firstArchetype());
        assertNotNull(hasTwoGenericParamsArchetype.secondArchetype());
        assertEquals(Map.class.getCanonicalName() + "<" + Item.class.getCanonicalName() + "," +
                        Integer.class.getCanonicalName() + ">",
                hasTwoGenericParamsArchetype.getInterfaceName());
    }

    @Test
    void testGenerateArchetypeWithTwoGenericParamsAndInterfaceNameOverride() {
        var interfaceNameOverride = "interfaceNameOverride";

        //noinspection unchecked
        var hasTwoGenericParamsArchetype =
                (Map<String, Integer>) generateArchetypeWithTwoGenericParams(Map.class,
                        generateSimpleArchetype(Item.class), 0, interfaceNameOverride);

        assertNotNull(hasTwoGenericParamsArchetype);
        assertNotNull(hasTwoGenericParamsArchetype.firstArchetype());
        assertNotNull(hasTwoGenericParamsArchetype.secondArchetype());
        assertEquals(interfaceNameOverride, hasTwoGenericParamsArchetype.getInterfaceName());
    }
}
