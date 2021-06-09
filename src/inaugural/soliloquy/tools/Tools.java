package inaugural.soliloquy.tools;

import java.util.HashMap;

public class Tools {
    private static HashMap<Integer, Float> EXPONENTS_OF_TEN = new HashMap<>();

    public static String emptyIfNull(String string) {
        return string == null ? "" : string;
    }

    public static String nullIfEmpty(String string) {
        return "".equals(string) ? null : string;
    }

    public static float round(float value, int places) throws IllegalArgumentException {
        Check.ifNonNegative(places, "places");

        float multiplicand;
        //noinspection SuspiciousMethodCalls
        if (EXPONENTS_OF_TEN.containsKey(value)) {
            //noinspection SuspiciousMethodCalls
            multiplicand = EXPONENTS_OF_TEN.get(value);
        }
        else {
            EXPONENTS_OF_TEN.put(places, multiplicand = (float)Math.pow(10, places));
        }

        return Math.round(value * multiplicand) / multiplicand;
    }
}
