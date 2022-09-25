package inaugural.soliloquy.tools.tests.random;

import inaugural.soliloquy.tools.random.Random;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class RandomTests {
    @Test
    void testRandomBoolean() {
        for (int i = 0; i < 1000; i++) {
            if (Random.randomBoolean()) {
                return;
            }
        }
        fail("randomBoolean() did not return a true after 1000 trials");
    }

    @Test
    void testRandomInt() {
        runRandomizationTest(Random::randomInt);
    }

    @Test
    void testRandomIntWithInclusiveFloor() {
        int floor = new java.util.Random().nextInt();
        runRandomizationTest(() -> Random.randomIntWithInclusiveFloor(floor),
                i -> assertTrue(i >= floor));
    }

    @Test
    void testRandomIntWithInclusiveCeiling() {
        int ceiling = new java.util.Random().nextInt();
        runRandomizationTest(() -> Random.randomIntWithInclusiveCeiling(ceiling),
                i -> assertTrue(i <= ceiling));
    }

    @Test
    void testRandomIntInRange() {
        int min = -Math.abs(new java.util.Random().nextInt() / 2);
        int max = -min;
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
    void testRandomLongWithInclusiveFloor() {
        long floor = new java.util.Random().nextLong() / 2;
        runRandomizationTest(() -> Random.randomLongWithInclusiveFloor(floor),
                l -> assertTrue(l >= floor));
    }

    @Test
    void testRandomLongWithInclusiveCeiling() {
        long ceiling = new java.util.Random().nextLong() / 2;
        runRandomizationTest(() -> Random.randomLongWithInclusiveCeiling(ceiling),
                l -> assertTrue(l <= ceiling));
    }

    @Test
    void testRandomLongInRange() {
        long min = -Math.abs(new java.util.Random().nextLong() / 2L);
        long max = -min;
        runRandomizationTest(() -> Random.randomLongInRange(min, max), l -> {
            assertTrue(l >= min);
            assertTrue(l <= max);
        });
    }

    @Test
    void testRandomFloat() {
        runRandomizationTest(Random::randomFloat);
    }

    @Test
    void testRandomFloatInRange() {
        float min = -999f;
        float max = -min;
        runRandomizationTest(() -> Random.randomFloatInRange(min, max), f -> {
            assertTrue(f >= min);
            assertTrue(f <= max);
        });
    }

    @Test
    void testRandomFloatWithInclusiveFloor() {
        float floor = new java.util.Random().nextFloat();

        runRandomizationTest(() -> Random.randomFloatWithInclusiveFloor(floor),
                f -> assertTrue(f >= floor));
    }

    @Test
    void testRandomFloatWithInclusiveCeiling() {
        float ceiling = new java.util.Random().nextFloat();

        runRandomizationTest(() -> Random.randomFloatWithInclusiveCeiling(ceiling),
                f -> assertTrue(f <= ceiling));
    }

    @Test
    void testRandomColor() {
        runRandomizationTest(Random::randomColor);
    }

    @Test
    void testRandomOpaqueColor() {
        runRandomizationTest(Random::randomOpaqueColor, c -> assertEquals(255, c.getAlpha()));
    }

    @Test
    void testRandomString() {
        runRandomizationTest(Random::randomString, s -> assertEquals(20, s.length()));
    }

    @Test
    void testRandomChar() {
        runRandomizationTest(Random::randomChar, c -> {
            assertTrue((int) c >= (int) ' ');
            assertTrue((int) c <= (int) '~');
        }, false);
    }

    // NB: This is technically indeterminate, but the odds of duplicate random results should be
    //     practically within the same realm of possibility as a duplicate UUID
    private <T> void runRandomizationTest(Supplier<T> randomMethod,
                                          Consumer<T> additionalAssertions,
                                          boolean failOnEquals) {
        for (int i = 0; i < 1000; i++) {
            T val1 = randomMethod.get();
            T val2 = randomMethod.get();
            if (failOnEquals) {
                assertNotEquals(val1, val2);
            }
            if (additionalAssertions != null) {
                additionalAssertions.accept(val1);
                additionalAssertions.accept(val2);
            }
        }
    }

    private <T> void runRandomizationTest(Supplier<T> randomMethod,
                                          Consumer<T> additionalAssertions) {
        runRandomizationTest(randomMethod, additionalAssertions, true);
    }

    private void runRandomizationTest(Supplier<Object> randomMethod) {
        runRandomizationTest(randomMethod, null);
    }
}
