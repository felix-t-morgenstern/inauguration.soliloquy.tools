package inaugural.soliloquy.tools.tests;

import inaugural.soliloquy.tools.Check;
import inaugural.soliloquy.tools.tests.fakes.FakeHasOneGenericParam;
import inaugural.soliloquy.tools.tests.fakes.FakeHasTwoGenericParams;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.shared.HasOneGenericParam;

import static org.junit.jupiter.api.Assertions.*;

class CheckTests {
    @Test
    void testIfNull() {
        final String paramName = "paramName";

        final Object input = new Object();
        assertSame(input, Check.ifNull(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.ifNull(null, paramName));
        try {
            Check.ifNull(null, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests.testIfNull: " +
                            paramName + " cannot be null",
                    e.getMessage());
        }
    }

    @Test
    void testIfNullOrEmpty() {
        final String paramName = "paramName";

        final String input = "input";
        assertSame(input, Check.ifNullOrEmpty(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.ifNullOrEmpty("", paramName));
        try {
            Check.ifNullOrEmpty("", paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests.testIfNullOrEmpty: " +
                            paramName + " cannot be empty",
                    e.getMessage());
        }
    }

    @Test
    void testIfNullOrEmptyIfString() {
        final String paramName = "paramName";

        final String input = "input";
        assertSame(input, Check.ifNullOrEmptyIfString(input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.ifNullOrEmptyIfString(null, paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.ifNullOrEmptyIfString("", paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.ifNullOrEmptyIfString((Integer) null, paramName));

        try {
            Check.ifNullOrEmptyIfString(null, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests.testIfNullOrEmptyIfString: " +
                            paramName + " cannot be null",
                    e.getMessage());
        }
        try {
            Check.ifNullOrEmptyIfString("", paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests.testIfNullOrEmptyIfString: " +
                            paramName + " cannot be empty",
                    e.getMessage());
        }
        try {
            Check.ifNullOrEmptyIfString((Integer) null, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests.testIfNullOrEmptyIfString: " +
                            paramName + " cannot be null",
                    e.getMessage());
        }
    }

    @Test
    void testIfNonNegative() {
        final String paramName = "paramName";

        final int input = 123;
        assertSame(input, Check.ifNonNegative(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.ifNonNegative(-1, paramName));
        try {
            Check.ifNonNegative(-1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests.testIfNonNegative: " +
                            paramName + " cannot be negative",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteZero() {
        final String paramName = "paramName";

        final int input = 123;
        assertSame(input, Check.throwOnLteZero(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.throwOnLteZero(0, paramName));
        try {
            Check.throwOnLteZero(0, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteZero: " + paramName +
                            " cannot be less than or equal to 0",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondLte() {
        final int input1 = 123;
        final int input2 = 123;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondLte(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondLte(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondLte: input2 (" + input2 + ") cannot be less than " +
                            "or equal to input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondGt() {
        final int input1 = 123;
        final int input2 = input1 + 1;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondGt(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondGt(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondGt: input2 (" + input2 + ") cannot be greater " +
                            "than input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testArchetypeAndArchetypesOfArchetypeAreNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> Check.archetypeAndArchetypesOfArchetypeAreNotNull("archetype",
                        new FakeHasOneGenericParam<String>(null)));
        try {
            Check.archetypeAndArchetypesOfArchetypeAreNotNull("archetype",
                    new FakeHasOneGenericParam<String>(null));
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                    ".testArchetypeAndArchetypesOfArchetypeAreNotNull: archetype cannot be null",
                    e.getMessage());
        }

        assertThrows(IllegalArgumentException.class,
                () -> Check.archetypeAndArchetypesOfArchetypeAreNotNull("archetype",
                        new FakeHasTwoGenericParams<String, HasOneGenericParam<String>>("string",
                                new FakeHasOneGenericParam<>(null))));
        try {
            Check.archetypeAndArchetypesOfArchetypeAreNotNull("archetype",
                    new FakeHasTwoGenericParams<String, HasOneGenericParam<String>>("string",
                            new FakeHasOneGenericParam<>(null)));
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testArchetypeAndArchetypesOfArchetypeAreNotNull: archetype cannot be null",
                    e.getMessage());
        }
    }

    @Test
    void testCheckMethodsFromConstructor() {
        try {
            new ConstructorExceptionMessageTester();
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests$ConstructorExceptionMessageTester: " +
                            ConstructorExceptionMessageTester.ParamName + " cannot be null",
                    e.getMessage());
        }
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class ConstructorExceptionMessageTester {
        private final static String ParamName = "ConstructorExceptionMessageTesterParamName";

        private ConstructorExceptionMessageTester() {
            Check.ifNull(null, ParamName);
        }
    }
}
