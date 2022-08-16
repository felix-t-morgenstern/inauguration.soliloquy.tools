package inaugural.soliloquy.tools.tests;

import inaugural.soliloquy.tools.Check;
import inaugural.soliloquy.tools.tests.fakes.FakeHasOneGenericParam;
import inaugural.soliloquy.tools.tests.fakes.FakeHasTwoGenericParams;
import org.junit.jupiter.api.Test;
import soliloquy.specs.common.shared.HasOneGenericParam;

import java.util.HashMap;

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
    void testIfMapIsNonEmptyWithRealKeysAndValuesForValidMap() {
        HashMap<String, String> map = new HashMap<>() {{
            put("k", "v");
        }};

        assertSame(map, Check.ifMapIsNonEmptyWithRealKeysAndValues(map, "map"));
    }

    @Test
    void testIfMapIsNonEmptyWithRealKeysAndValuesForNullMap() {
        assertThrows(IllegalArgumentException.class, () ->
                Check.ifMapIsNonEmptyWithRealKeysAndValues(null, "map"));

        String paramName = "map";
        try {
            Check.ifMapIsNonEmptyWithRealKeysAndValues(null, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests." +
                            "testIfMapIsNonEmptyWithRealKeysAndValuesForNullMap: " + paramName +
                            " cannot be null",
                    e.getMessage());
        }
    }

    @Test
    void testIfMapIsNonEmptyWithRealKeysAndValuesForEmptyMap() {
        HashMap<String, String> map = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () ->
                Check.ifMapIsNonEmptyWithRealKeysAndValues(map, "map"));

        String paramName = "map";
        try {
            Check.ifMapIsNonEmptyWithRealKeysAndValues(map, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests." +
                            "testIfMapIsNonEmptyWithRealKeysAndValuesForEmptyMap: " + paramName +
                            " cannot be empty",
                    e.getMessage());
        }
    }

    @Test
    void testIfMapIsNonEmptyWithRealKeysAndValuesWithNullKey() {
        HashMap<String, String> map = new HashMap<>() {{
            put(null, "v");
        }};

        assertThrows(IllegalArgumentException.class, () ->
                Check.ifMapIsNonEmptyWithRealKeysAndValues(map, "map"));

        String paramName = "map";
        try {
            Check.ifMapIsNonEmptyWithRealKeysAndValues(map, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testIfMapIsNonEmptyWithRealKeysAndValuesWithNullKey: key in " +
                            paramName + " cannot be null",
                    e.getMessage());
        }
    }

    @Test
    void testIfMapIsNonEmptyWithRealKeysAndValuesWithEmptyKey() {
        HashMap<String, String> map = new HashMap<>() {{
            put("", "v");
        }};

        assertThrows(IllegalArgumentException.class, () ->
                Check.ifMapIsNonEmptyWithRealKeysAndValues(map, "map"));

        String paramName = "map";
        try {
            Check.ifMapIsNonEmptyWithRealKeysAndValues(map, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testIfMapIsNonEmptyWithRealKeysAndValuesWithEmptyKey: key in " +
                            paramName + " cannot be empty",
                    e.getMessage());
        }
    }

    @Test
    void testIfMapIsNonEmptyWithRealKeysAndValuesWithNullValue() {
        HashMap<String, String> map = new HashMap<>() {{
            put("k", null);
        }};

        assertThrows(IllegalArgumentException.class, () ->
                Check.ifMapIsNonEmptyWithRealKeysAndValues(map, "map"));

        String paramName = "map";
        try {
            Check.ifMapIsNonEmptyWithRealKeysAndValues(map, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testIfMapIsNonEmptyWithRealKeysAndValuesWithNullValue: value in " +
                            paramName + " cannot be null",
                    e.getMessage());
        }
    }

    @Test
    void testIfMapIsNonEmptyWithRealKeysAndValuesWithEmptyValue() {
        HashMap<String, String> map = new HashMap<>() {{
            put("k", "");
        }};

        assertThrows(IllegalArgumentException.class, () ->
                Check.ifMapIsNonEmptyWithRealKeysAndValues(map, "map"));

        String paramName = "map";
        try {
            Check.ifMapIsNonEmptyWithRealKeysAndValues(map, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testIfMapIsNonEmptyWithRealKeysAndValuesWithEmptyValue: value in " +
                            paramName + " cannot be empty",
                    e.getMessage());
        }
    }

    @Test
    void testIfMapIsNonEmptyWithRealKeysAndValuesWithItemCheck() {
        HashMap<String, String> itemCheckInputs = new HashMap<>();
        HashMap<String, String> map = new HashMap<>() {{
            put("k", "v");
        }};

        Check.ifMapIsNonEmptyWithRealKeysAndValues(map, "map",
                k -> v -> itemCheckInputs.put(k, v));

        assertEquals(1, itemCheckInputs.size());
        assertTrue(itemCheckInputs.containsKey("k"));
        assertEquals("v", itemCheckInputs.get("k"));
    }

    @Test
    void testIfNonNegativeForShort() {
        final String paramName = "paramName";

        final short input = 123;
        assertEquals(input, Check.ifNonNegative(input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.ifNonNegative((short) -1, paramName));
        try {
            Check.ifNonNegative(-1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals(
                    "inaugural.soliloquy.tools.tests.CheckTests.testIfNonNegativeForShort: " +
                            paramName + " (" + -1 + ")" + " cannot be negative",
                    e.getMessage());
        }
    }

    @Test
    void testIfNonNegativeForInt() {
        final String paramName = "paramName";

        final int input = 123;
        assertEquals(input, Check.ifNonNegative(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.ifNonNegative(-input, paramName));
        try {
            Check.ifNonNegative(-1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals(
                    "inaugural.soliloquy.tools.tests.CheckTests.testIfNonNegativeForInt: " +
                            paramName + " (" + -1 + ")" + " cannot be negative",
                    e.getMessage());
        }
    }

    @Test
    void testIfNonNegativeForIntClass() {
        final String paramName = "paramName";

        final Integer input = 123;
        assertEquals(input, Check.ifNonNegative(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.ifNonNegative(-input, paramName));
        try {
            Check.ifNonNegative(-1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals(
                    "inaugural.soliloquy.tools.tests.CheckTests.testIfNonNegativeForIntClass: " +
                            paramName + " (" + -1 + ")" + " cannot be negative",
                    e.getMessage());
        }
    }

    @Test
    void testIfNonNegativeForLong() {
        final String paramName = "paramName";

        final long input = 123;
        assertEquals(input, Check.ifNonNegative(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.ifNonNegative(-1L, paramName));
        try {
            Check.ifNonNegative(-1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals(
                    "inaugural.soliloquy.tools.tests.CheckTests.testIfNonNegativeForLong: " +
                            paramName + " (" + -1 + ")" + " cannot be negative",
                    e.getMessage());
        }
    }

    @Test
    void testIfNonNegativeForFloat() {
        final String paramName = "paramName";

        final float input = 0.123f;
        assertEquals(input, Check.ifNonNegative(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.ifNonNegative(-1f, paramName));
        try {
            Check.ifNonNegative(-1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals(
                    "inaugural.soliloquy.tools.tests.CheckTests.testIfNonNegativeForFloat: " +
                            paramName + " (" + -1 + ")" + " cannot be negative",
                    e.getMessage());
        }
    }

    @Test
    void testIfNonNegativeForDouble() {
        final String paramName = "paramName";

        final double input = 0.123d;
        assertEquals(input, Check.ifNonNegative(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.ifNonNegative(-1d, paramName));
        try {
            Check.ifNonNegative(-1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals(
                    "inaugural.soliloquy.tools.tests.CheckTests.testIfNonNegativeForDouble: " +
                            paramName + " (" + -1 + ")" + " cannot be negative",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteZeroForShort() {
        final String paramName = "paramName";

        final short input = 123;
        assertEquals(input, Check.throwOnLteZero(input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteZero((short) 0, paramName));
        try {
            Check.throwOnLteZero((short) 0, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteZeroForShort: " + paramName + " (" + 0 + ")" +
                            " cannot be less than or equal to 0",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteZeroForInt() {
        final String paramName = "paramName";

        final int input = 123;
        assertEquals(input, Check.throwOnLteZero(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.throwOnLteZero(0, paramName));
        try {
            Check.throwOnLteZero(0, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteZeroForInt: " + paramName + " (" + 0 + ")" +
                            " cannot be less than or equal to 0",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteZeroForLong() {
        final String paramName = "paramName";

        final long input = 123;
        assertEquals(input, Check.throwOnLteZero(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.throwOnLteZero(0L, paramName));
        try {
            Check.throwOnLteZero(0, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteZeroForLong: " + paramName + " (" + 0 + ")" +
                            " cannot be less than or equal to 0",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteZeroForFloat() {
        final String paramName = "paramName";

        final float input = 0.123f;
        assertEquals(input, Check.throwOnLteZero(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.throwOnLteZero(0f, paramName));
        try {
            Check.throwOnLteZero(0f, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteZeroForFloat: " + paramName + " (" + 0.0 + ")" +
                            " cannot be less than or equal to 0.0",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteZeroForDouble() {
        final String paramName = "paramName";

        final double input = 0.123d;
        assertEquals(input, Check.throwOnLteZero(input, paramName));

        assertThrows(IllegalArgumentException.class, () -> Check.throwOnLteZero(0d, paramName));
        try {
            Check.throwOnLteZero(0d, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteZeroForDouble: " + paramName + " (" + 0.0 + ")" +
                            " cannot be less than or equal to 0.0",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLtValueForShort() {
        final String paramName = "paramName";

        final short input = 123;
        assertEquals(input, Check.throwOnLtValue(input, input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLtValue(input, (short) (input + 1), paramName));
        try {
            Check.throwOnLtValue(input, (short) (input + 1), paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLtValueForShort: " + paramName + " (" + input + ")" +
                            " cannot be less than " + (input + 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLtValueForInt() {
        final String paramName = "paramName";

        final int input = 123;
        assertEquals(input, Check.throwOnLtValue(input, input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLtValue(input, (input + 1), paramName));
        try {
            Check.throwOnLtValue(input, (input + 1), paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLtValueForInt: " + paramName + " (" + input + ")" +
                            " cannot be less than " + (input + 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLtValueForLong() {
        final String paramName = "paramName";

        final long input = 123L;
        assertEquals(input, Check.throwOnLtValue(input, input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLtValue(input, (input + 1), paramName));
        try {
            Check.throwOnLtValue(input, (input + 1), paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLtValueForLong: " + paramName + " (" + input + ")" +
                            " cannot be less than " + (input + 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLtValueForFloat() {
        final String paramName = "paramName";

        final float input = 0.122f;
        final float value = 0.123f;
        assertEquals(input, Check.throwOnLtValue(input, input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLtValue(input, value, paramName));
        try {
            Check.throwOnLtValue(input, value, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLtValueForFloat: " + paramName + " (" + input + ")" +
                            " cannot be less than " + value,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLtValueForDouble() {
        final String paramName = "paramName";

        final double input = 0.122d;
        final double value = 0.123d;
        assertEquals(input, Check.throwOnLtValue(input, input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLtValue(input, value, paramName));
        try {
            Check.throwOnLtValue(input, value, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLtValueForDouble: " + paramName + " (" + input + ")" +
                            " cannot be less than " + value,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteValueForShort() {
        final String paramName = "paramName";

        final short input = 123;
        assertEquals(input, Check.throwOnLteValue(input, (short) (input - 1), paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteValue(input, (short) (input + 1), paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteValue(input, input, paramName));
        try {
            Check.throwOnLteValue(input, (short) (input + 1), paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteValueForShort: " + paramName + " (" + input + ")" +
                            " cannot be less than or equal to " + (input + 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteValueForInt() {
        final String paramName = "paramName";

        final int input = 123;
        assertEquals(input, Check.throwOnLteValue(input, input - 1, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteValue(input, (input + 1), paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteValue(input, input, paramName));
        try {
            Check.throwOnLteValue(input, (input + 1), paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteValueForInt: " + paramName + " (" + input + ")" +
                            " cannot be less than or equal to " + (input + 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteValueForLong() {
        final String paramName = "paramName";

        final long input = 123L;
        assertEquals(input, Check.throwOnLteValue(input, input - 1, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteValue(input, (input + 1), paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteValue(input, input, paramName));
        try {
            Check.throwOnLteValue(input, (input + 1), paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteValueForLong: " + paramName + " (" + input + ")" +
                            " cannot be less than or equal to " + (input + 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteValueForFloat() {
        final String paramName = "paramName";

        final float input = 0.122f;
        final float value = 0.123f;
        assertEquals(input, Check.throwOnLteValue(input, input - 0.001f, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteValue(input, value, paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteValue(input, input, paramName));
        try {
            Check.throwOnLteValue(input, value, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteValueForFloat: " + paramName + " (" + input + ")" +
                            " cannot be less than or equal to " + value,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnLteValueForDouble() {
        final String paramName = "paramName";

        final double input = 0.122d;
        final double value = 0.123d;
        assertEquals(input, Check.throwOnLteValue(input, input - 0.001d, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteValue(input, value, paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnLteValue(input, input, paramName));
        try {
            Check.throwOnLteValue(input, value, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnLteValueForDouble: " + paramName + " (" + input + ")" +
                            " cannot be less than or equal to " + value,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnGtValueForShort() {
        final String paramName = "paramName";

        final short input = 123;
        assertEquals(input, Check.throwOnGtValue(input, input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGtValue(input, (short) (input - 1), paramName));
        try {
            Check.throwOnGtValue(input, (short) (input - 1), paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnGtValueForShort: " + paramName + " (" + input + ")" +
                            " cannot be greater than " + (input - 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnGtValueForInt() {
        final String paramName = "paramName";

        final int input = 123;
        assertEquals(input, Check.throwOnGtValue(input, input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGtValue(input, input - 1, paramName));
        try {
            Check.throwOnGtValue(input, input - 1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnGtValueForInt: " + paramName + " (" + input + ")" +
                            " cannot be greater than " + (input - 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnGtValueForLong() {
        final String paramName = "paramName";

        final long input = 123;
        assertEquals(input, Check.throwOnGtValue(input, input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGtValue(input, input - 1, paramName));
        try {
            Check.throwOnGtValue(input, input - 1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnGtValueForLong: " + paramName + " (" + input + ")" +
                            " cannot be greater than " + (input - 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnGtValueForFloat() {
        final String paramName = "paramName";

        final float input = 0.123f;
        final float value = 0.122f;
        assertEquals(input, Check.throwOnGtValue(input, input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGtValue(input, value, paramName));
        try {
            Check.throwOnGtValue(input, value, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnGtValueForFloat: " + paramName + " (" + input + ")" +
                            " cannot be greater than " + value,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnGtValueForDouble() {
        final String paramName = "paramName";

        final double input = 0.123f;
        final double value = 0.122f;
        assertEquals(input, Check.throwOnGtValue(input, input, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGtValue(input, value, paramName));
        try {
            Check.throwOnGtValue(input, value, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnGtValueForDouble: " + paramName + " (" + input + ")" +
                            " cannot be greater than " + value,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnGteValueForShort() {
        final String paramName = "paramName";

        final short input = 123;
        assertEquals(input, Check.throwOnGteValue(input, (short) (input + 1), paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGteValue(input, input, paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGteValue(input, (short) (input - 1), paramName));
        try {
            Check.throwOnGteValue(input, (short) (input - 1), paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnGteValueForShort: " + paramName + " (" + input + ")" +
                            " cannot be greater than " + (input - 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnGteValueForInt() {
        final String paramName = "paramName";

        final int input = 123;
        assertEquals(input, Check.throwOnGteValue(input, input + 1, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGteValue(input, input, paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGteValue(input, input - 1, paramName));
        try {
            Check.throwOnGteValue(input, input - 1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnGteValueForInt: " + paramName + " (" + input + ")" +
                            " cannot be greater than " + (input - 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnGteValueForLong() {
        final String paramName = "paramName";

        final long input = 123;
        assertEquals(input, Check.throwOnGteValue(input, input + 1, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGteValue(input, input, paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGteValue(input, input - 1, paramName));
        try {
            Check.throwOnGteValue(input, input - 1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnGteValueForLong: " + paramName + " (" + input + ")" +
                            " cannot be greater than " + (input - 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnGteValueForFloat() {
        final String paramName = "paramName";

        final float input = 0.123f;
        assertEquals(input, Check.throwOnGteValue(input, input + 1, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGteValue(input, input, paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGteValue(input, input - 1, paramName));
        try {
            Check.throwOnGteValue(input, input - 1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnGteValueForFloat: " + paramName + " (" + input + ")" +
                            " cannot be greater than " + (input - 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnGteValueForDouble() {
        final String paramName = "paramName";

        final double input = 0.123f;
        assertEquals(input, Check.throwOnGteValue(input, input + 1, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGteValue(input, input, paramName));
        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnGteValue(input, input - 1, paramName));
        try {
            Check.throwOnGteValue(input, input - 1, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnGteValueForDouble: " + paramName + " (" + input + ")" +
                            " cannot be greater than " + (input - 1),
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnEqualsValueForShort() {
        final String paramName = "paramName";

        final short input = 123;
        assertEquals(input, Check.throwOnEqualsValue(input, (short) (input + 1), paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnEqualsValue(input, input, paramName));
        try {
            Check.throwOnEqualsValue(input, input, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnEqualsValueForShort: " + paramName + " (" + input + ")" +
                            " cannot be equal to " + input,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnEqualsValueForInt() {
        final String paramName = "paramName";

        final int input = 123;
        assertEquals(input, Check.throwOnEqualsValue(input, input + 1, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnEqualsValue(input, input, paramName));
        try {
            Check.throwOnEqualsValue(input, input, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnEqualsValueForInt: " + paramName + " (" + input + ")" +
                            " cannot be equal to " + input,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnEqualsValueForLong() {
        final String paramName = "paramName";

        final long input = 123L;
        assertEquals(input, Check.throwOnEqualsValue(input, input + 1, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnEqualsValue(input, input, paramName));
        try {
            Check.throwOnEqualsValue(input, input, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnEqualsValueForLong: " + paramName + " (" + input + ")" +
                            " cannot be equal to " + input,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnEqualsValueForFloat() {
        final String paramName = "paramName";

        final float input = 0.123f;
        assertEquals(input, Check.throwOnEqualsValue(input, input + 1, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnEqualsValue(input, input, paramName));
        try {
            Check.throwOnEqualsValue(input, input, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnEqualsValueForFloat: " + paramName + " (" + input + ")" +
                            " cannot be equal to " + input,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnEqualsValueForDouble() {
        final String paramName = "paramName";

        final double input = 0.123d;
        assertEquals(input, Check.throwOnEqualsValue(input, input + 1, paramName));

        assertThrows(IllegalArgumentException.class,
                () -> Check.throwOnEqualsValue(input, input, paramName));
        try {
            Check.throwOnEqualsValue(input, input, paramName);
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnEqualsValueForDouble: " + paramName + " (" + input + ")" +
                            " cannot be equal to " + input,
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondLteForShorts() {
        final short input1 = 123;
        final short input2 = 123;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondLte(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondLte(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondLteForShorts: input2 (" + input2 +
                            ") cannot be less than or equal to input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondLteForInts() {
        final int input1 = 123;
        final int input2 = 123;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondLte(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondLte(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondLteForInts: input2 (" + input2 +
                            ") cannot be less than or equal to input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondLteForLongs() {
        final long input1 = 123;
        final long input2 = 123;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondLte(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondLte(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondLteForLongs: input2 (" + input2 +
                            ") cannot be less than or equal to input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondLteForFloats() {
        final float input1 = 123;
        final float input2 = 123;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondLte(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondLte(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondLteForFloats: input2 (" + input2 +
                            ") cannot be less than or equal to input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondLteForDoubles() {
        final double input1 = 123;
        final double input2 = 123;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondLte(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondLte(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondLteForDoubles: input2 (" + input2 +
                            ") cannot be less than or equal to input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondGtForShorts() {
        final short input1 = 123;
        final short input2 = input1 + 1;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondGt(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondGt(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondGtForShorts: input2 (" + input2 +
                            ") cannot be greater than input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondGtForInts() {
        final int input1 = 123;
        final int input2 = input1 + 1;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondGt(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondGt(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondGtForInts: input2 (" + input2 +
                            ") cannot be greater than input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondGtForLongs() {
        final long input1 = 123;
        final long input2 = input1 + 1;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondGt(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondGt(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondGtForLongs: input2 (" + input2 +
                            ") cannot be greater than input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondGtForFloats() {
        final float input1 = 123;
        final float input2 = input1 + 1;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondGt(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondGt(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondGtForFloats: input2 (" + input2 +
                            ") cannot be greater than input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testThrowOnSecondGtForDoubles() {
        final double input1 = 123;
        final double input2 = input1 + 1;

        assertThrows(IllegalArgumentException.class, () ->
                Check.throwOnSecondGt(input1, input2, "input1", "input2"));
        try {
            Check.throwOnSecondGt(input1, input2, "input1", "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testThrowOnSecondGtForDoubles: input2 (" + input2 +
                            ") cannot be greater than input1 (" + input1 + ")",
                    e.getMessage());
        }
    }

    @Test
    void testIsBetweenZeroAndOne() {
        float input1 = -0.001f;
        float input2 = 1.001f;

        try {
            Check.isBetweenZeroAndOne(input1, "input1");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testIsBetweenZeroAndOne: input1 (" + input1 +
                            ") cannot be outside of the range of [0.0, 1.0]",
                    e.getMessage());
        }
        try {
            Check.isBetweenZeroAndOne(input2, "input2");
        }
        catch (IllegalArgumentException e) {
            assertEquals("inaugural.soliloquy.tools.tests.CheckTests" +
                            ".testIsBetweenZeroAndOne: input2 (" + input2 +
                            ") cannot be outside of the range of [0.0, 1.0]",
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
                            ".testArchetypeAndArchetypesOfArchetypeAreNotNull: archetype cannot " +
                            "be null",
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
                            ".testArchetypeAndArchetypesOfArchetypeAreNotNull: archetype cannot " +
                            "be null",
                    e.getMessage());
        }
    }

    @Test
    void testCheckMethodsFromConstructor() {
        try {
            new ConstructorExceptionMessageTester();
        }
        catch (IllegalArgumentException e) {
            assertEquals(
                    "inaugural.soliloquy.tools.tests" +
                            ".CheckTests$ConstructorExceptionMessageTester: " +
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
