package inaugural.soliloquy.tools.random;

public class Random {
    private static java.util.Random RANDOM = new java.util.Random();

    public static int randomInt() {
        return RANDOM.nextInt();
    }

    public static long randomLong() {
        return RANDOM.nextLong();
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
