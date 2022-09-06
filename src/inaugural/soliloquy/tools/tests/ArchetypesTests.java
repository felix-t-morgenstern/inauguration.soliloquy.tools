package inaugural.soliloquy.tools.tests;

import org.junit.jupiter.api.Test;
import soliloquy.specs.gamestate.entities.Character;

import static inaugural.soliloquy.tools.generic.Archetypes.generateSimpleArchetype;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArchetypesTests {
    @Test
    void testGenerate() {
        Character characterArchetype = generateSimpleArchetype(Character.class);

        assertEquals(Character.class.getCanonicalName(), characterArchetype.getInterfaceName());
    }
}
