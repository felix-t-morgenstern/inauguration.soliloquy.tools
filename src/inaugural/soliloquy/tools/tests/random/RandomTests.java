package inaugural.soliloquy.tools.tests.random;

import inaugural.soliloquy.tools.random.Random;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomTests {
    @Test
    void testRandomInt() {
        runRandomizationTest(Random::randomInt);
    }

    @Test
    void testRandomIntWithInclusiveFloor() {
        int floor = 123;
        runRandomizationTest(() -> Random.randomIntWithInclusiveFloor(floor),
                i -> assertTrue(i >= floor));
    }

    @Test
    void testRandomIntInRange() {
        int min = -999999999;
        int max = 999999999;
        runRandomizationTest(() -> Random.randomIntInRange(min, max), i -> {
            assertTrue(i >= min);
            assertTrue(i <= max);
        });
    }

    @Test
    void testRandomLong() {
        runRandomizationTest(Random::randomLong);
    }

    @Test
    void testRandomString() {
        runRandomizationTest(Random::randomString);
    }

    // NB: This is technically indeterminate, but the odds of duplicate random results should be
    //     practically within the same realm of possibility as a duplicate UUID
    private <T> void runRandomizationTest(Supplier<T> randomMethod,
                                          Consumer<T> additionalAssertions) {
        for (int i = 0; i < 1000; i++) {
            T val1 = randomMethod.get();
            T val2 = randomMethod.get();
            assertNotEquals(val1, val2);
            if (additionalAssertions != null) {
                additionalAssertions.accept(val1);
                additionalAssertions.accept(val2);
            }
        }
    }

    private void runRandomizationTest(Supplier<Object> randomMethod) {
        runRandomizationTest(randomMethod, null);
    }
}
