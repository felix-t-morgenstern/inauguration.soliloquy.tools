package inaugural.soliloquy.tools.random;

public class Random {
    public static java.util.Random RANDOM = new java.util.Random();

    public static int randomInt() {
        return RANDOM.nextInt();
    }

    public static int randomIntWithInclusiveFloor(int floor) {
        return randomIntInRange(floor, Integer.MAX_VALUE);
    }

    public static int randomIntWithInclusiveCeiling(int ceiling) {
        return randomIntInRange(Integer.MIN_VALUE, ceiling);
    }

    public static int randomIntInRange(int min, int max) {
        return RANDOM.ints(min, max).findFirst().getAsInt();
    }

    public static long randomLong() {
        return RANDOM.nextLong();
    }

    public static long randomLongWithInclusiveFloor(long floor) {
        return randomLongInRange(floor, Long.MAX_VALUE);
    }

    public static long randomLongWithInclusiveCeiling(long ceiling) {
        return randomLongInRange(Long.MIN_VALUE, ceiling);
    }

    public static long randomLongInRange(long min, long max) {
        return RANDOM.longs(min, max).findFirst().getAsLong();
    }

    // NB: Taken from Baeldung, at https://www.baeldung.com/java-random-string, accessed on 2022/04/19
    public static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;

        return RANDOM.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
