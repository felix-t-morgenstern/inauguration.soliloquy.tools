package inaugural.soliloquy.tools.generic;

import soliloquy.specs.common.shared.HasOneGenericParam;
import soliloquy.specs.common.shared.HasTwoGenericParams;
import soliloquy.specs.common.shared.SoliloquyClass;

import static org.mockito.Mockito.*;

public class Archetypes {
    public static <T extends SoliloquyClass> T generateSimpleArchetype(Class<T> clazz) {
        T archetype = mock(clazz);
        when(archetype.getInterfaceName()).thenReturn(clazz.getCanonicalName());

        // NB: Any parameterized Soliloquy types should NOT report the subtypes of their
        // archetypes, so they are properly registered by PersistentValuesHandler#addHandler
        if (archetype instanceof HasOneGenericParam) {
            //noinspection rawtypes
            when(((HasOneGenericParam) archetype).getArchetype()).thenReturn(new Object());
        }
        else if (archetype instanceof HasTwoGenericParams) {
            //noinspection rawtypes
            when(((HasTwoGenericParams) archetype).getFirstArchetype()).thenReturn(new Object());
            //noinspection rawtypes
            when(((HasTwoGenericParams) archetype).getSecondArchetype()).thenReturn(new Object());
        }

        return archetype;
    }
}
