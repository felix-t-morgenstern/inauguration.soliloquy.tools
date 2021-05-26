package inaugural.soliloquy.tools;

public class Tools {
    public static String emptyIfNull(String string) {
        return string == null ? "" : string;
    }

    public static String nullIfEmpty(String string) {
        return "".equals(string) ? null : string;
    }
}
