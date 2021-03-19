package inaugural.soliloquy.tools.tests.generic;

import inaugural.soliloquy.tools.tests.abstractimplementations.generic.HasTwoGenericParamsImpl;
import inaugural.soliloquy.tools.tests.fakes.FakeHasTwoGenericParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.shared.HasTwoGenericParams;

import static org.junit.jupiter.api.Assertions.*;

class HasTwoGenericParamsTests {
    private HasTwoGenericParams<HasTwoGenericParams<Integer,Integer>,
            HasTwoGenericParams<Boolean,Boolean>> _level1Archetype1;
    private HasTwoGenericParams<Integer,Integer> _level2Archetype1;
    @SuppressWarnings("FieldCanBeLocal")
    private HasTwoGenericParams<Boolean,Boolean> _level2Archetype2;

    private HasTwoGenericParams<HasTwoGenericParams<String,String>,
            HasTwoGenericParams<Float,Float>> _level1Archetype2;
    private HasTwoGenericParams<String,String> _level2Archetype3;
    @SuppressWarnings("FieldCanBeLocal")
    private HasTwoGenericParams<Float,Float> _level2Archetype4;

    private HasTwoGenericParams<
            HasTwoGenericParams<HasTwoGenericParams<Integer,Integer>,
                    HasTwoGenericParams<Boolean,Boolean>>,
            HasTwoGenericParams<HasTwoGenericParams<String,String>,
                    HasTwoGenericParams<Float,Float>>> _hasTwoGenericParams;

    @BeforeEach
    void setUp() {
        _level2Archetype1 = new HasTwoGenericParamsImpl<>(0,0);
        _level2Archetype2 = new HasTwoGenericParamsImpl<>(true, true);
        _level2Archetype3 = new HasTwoGenericParamsImpl<>("", "");
        _level2Archetype4 = new HasTwoGenericParamsImpl<>(0f, 0f);

        _level1Archetype1 = new HasTwoGenericParamsImpl<>(_level2Archetype1, _level2Archetype2);
        _level1Archetype2 = new HasTwoGenericParamsImpl<>(_level2Archetype3, _level2Archetype4);

        _hasTwoGenericParams = new HasTwoGenericParamsImpl<>(_level1Archetype1, _level1Archetype2);
    }

    @Test
    void testConstructorWithInvalidParams() {
        HasTwoGenericParams<HasTwoGenericParams<Integer,Integer>,
                HasTwoGenericParams<Boolean,Boolean>> archetype1WithNullChildArchetype =
                new FakeHasTwoGenericParams<>(_level2Archetype1,
                        new FakeHasTwoGenericParams<>(true, null));

        HasTwoGenericParams<HasTwoGenericParams<String,String>,
                HasTwoGenericParams<Float,Float>> archetype2WithNullChildArchetype =
                new FakeHasTwoGenericParams<>(_level2Archetype3,
                        new FakeHasTwoGenericParams<>(0f, null));

        assertThrows(IllegalArgumentException.class,
                () -> new HasTwoGenericParamsImpl<>(null, _level1Archetype2));
        assertThrows(IllegalArgumentException.class,
                () -> new HasTwoGenericParamsImpl<>(_level1Archetype1, null));

        assertThrows(IllegalArgumentException.class,
                () -> new HasTwoGenericParamsImpl<>(archetype1WithNullChildArchetype,
                        _level1Archetype2));

        assertThrows(IllegalArgumentException.class,
                () -> new HasTwoGenericParamsImpl<>(_level1Archetype1,
                        archetype2WithNullChildArchetype));
    }

    @Test
    void testGetArchetypes() {
        assertSame(_level1Archetype1, _hasTwoGenericParams.getFirstArchetype());
        assertSame(_level1Archetype2, _hasTwoGenericParams.getSecondArchetype());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(HasTwoGenericParams.class.getCanonicalName() + "<" +
                HasTwoGenericParams.class.getCanonicalName() + "<" +
                HasTwoGenericParams.class.getCanonicalName() + "<" +
                Integer.class.getCanonicalName() + "," + Integer.class.getCanonicalName() + ">," +
                HasTwoGenericParams.class.getCanonicalName() + "<" +
                Boolean.class.getCanonicalName() + "," + Boolean.class.getCanonicalName() + ">>," +
                HasTwoGenericParams.class.getCanonicalName() + "<" +
                HasTwoGenericParams.class.getCanonicalName() + "<" +
                String.class.getCanonicalName() + "," + String.class.getCanonicalName() + ">," +
                HasTwoGenericParams.class.getCanonicalName() + "<" +
                Float.class.getCanonicalName() + "," + Float.class.getCanonicalName() + ">>>",
                _hasTwoGenericParams.getInterfaceName());
    }
}
