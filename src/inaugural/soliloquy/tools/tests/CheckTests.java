package inaugural.soliloquy.tools.tests;

import inaugural.soliloquy.tools.Check;
import org.junit.jupiter.api.Test;

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
