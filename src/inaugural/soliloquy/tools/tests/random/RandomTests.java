package inaugural.soliloquy.tools.tests.random;

import inaugural.soliloquy.tools.random.Random;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RandomTests {
    @Test
    void testRandomInt() {
        runRandomizationTest(Random::randomInt);
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
    //     within the same realm of possibility as a duplicate UUID
    private void runRandomizationTest(Supplier<Object> randomMethod) {
        for (int i = 0; i < 1000; i++) {
            assertNotEquals(randomMethod.get(), randomMethod.get());
        }
    }
}
