package inaugural.soliloquy.tools.generic;

import soliloquy.specs.common.shared.HasOneGenericParam;
import soliloquy.specs.common.shared.HasTwoGenericParams;
import soliloquy.specs.common.shared.SoliloquyClass;

import static org.mockito.Mockito.*;

public class Archetypes {
    private static final CanGetInterfaceName CAN_GET_INTERFACE_NAME = new CanGetInterfaceName();

    public static <T extends SoliloquyClass> T generateSimpleArchetype(Class<T> clazz) {
        return generateArchetypeWithInterfaceNameOverride(clazz, clazz.getCanonicalName());
    }

    public static <T extends SoliloquyClass> T generateArchetypeWithInterfaceNameOverride(
            Class<T> clazz, String interfaceName) {
        var archetype = mock(clazz);
        lenient().when(archetype.getInterfaceName()).thenReturn(interfaceName);

        return archetype;
    }

    public static <T extends HasOneGenericParam<TParam>, TParam> T generateArchetypeWithOneGenericParam(
            Class<T> clazz, TParam innerArchetype) {
        var interfaceName = clazz.getCanonicalName() + "<" +
                CAN_GET_INTERFACE_NAME.getProperTypeName(innerArchetype) + ">";
        return generateArchetypeWithOneGenericParam(clazz, innerArchetype, interfaceName);
    }

    public static <T extends HasOneGenericParam<TParam>, TParam> T generateArchetypeWithOneGenericParam(
            Class<T> clazz, TParam innerArchetype, String interfaceNameOverride) {
        var archetype = mock(clazz);

        lenient().when(archetype.getInterfaceName()).thenReturn(interfaceNameOverride);
        lenient().when(archetype.archetype()).thenReturn(innerArchetype);

        return archetype;
    }

    public static <T extends HasTwoGenericParams<TParam1, TParam2>, TParam1, TParam2> T generateArchetypeWithTwoGenericParams(
            Class<T> clazz, TParam1 innerArchetype1, TParam2 innerArchetype2) {
        var interfaceName = clazz.getCanonicalName() + "<" +
                CAN_GET_INTERFACE_NAME.getProperTypeName(innerArchetype1) + "," +
                CAN_GET_INTERFACE_NAME.getProperTypeName(innerArchetype2) + ">";
        return generateArchetypeWithTwoGenericParams(clazz, innerArchetype1, innerArchetype2,
                interfaceName);
    }

    public static <T extends HasTwoGenericParams<TParam1, TParam2>, TParam1, TParam2> T generateArchetypeWithTwoGenericParams(
            Class<T> clazz, TParam1 innerArchetype1, TParam2 innerArchetype2,
            String interfaceNameOverride) {
        var archetype = mock(clazz);

        lenient().when(archetype.getInterfaceName()).thenReturn(interfaceNameOverride);
        lenient().when(archetype.firstArchetype()).thenReturn(innerArchetype1);
        lenient().when(archetype.secondArchetype()).thenReturn(innerArchetype2);

        return archetype;
    }
}
