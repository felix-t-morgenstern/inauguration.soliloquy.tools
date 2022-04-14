package inaugural.soliloquy.tools.random;

public class Random {
    private static java.util.Random RANDOM = new java.util.Random();

    public static long randomLong() {
        return RANDOM.nextLong();
    }
}
