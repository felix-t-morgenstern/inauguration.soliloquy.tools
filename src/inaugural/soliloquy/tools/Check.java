package inaugural.soliloquy.tools;

// TODO: Consider breaking this out into multiple classes
public class Check {
    public static <T> T ifNull(T obj, String paramName) {
        if (obj == null) {
            throwException(paramName, "null");
        }
        return obj;
    }

    public static String ifNullOrEmpty(String str, String paramName) {
        if (ifNull(str, paramName).equals("")) {
            throwException(paramName, "empty");
        }
        return str;
    }

    public static <T> T ifNullOrEmptyIfString(T obj, String paramName) {
        if (obj instanceof String) {
            //noinspection unchecked
            return (T) ifNullOrEmpty((String) obj, paramName);
        }
        else {
            return ifNull(obj, paramName);
        }
    }

    public static int ifNonNegative(int i, String paramName) {
        if (i < 0) {
            throwException(paramName, "negative");
        }
        return i;
    }

    public static Integer ifNonNegative(Integer i, String paramName) {
        if (i != null && i < 0) {
            throwException(paramName, "negative");
        }
        return i;
    }

    public static int throwOnLteZero(int i, String paramName) {
        if (i <= 0) {
            throwException(paramName, "less than or equal to 0");
        }
        return i;
    }

    public static void throwOnSecondLte(int first, int second,
                                        String firstParamName, String secondParamName) {
        if (second <= first) {
            throwException(secondParamName + " (" + second + ") cannot be less than or equal to " +
                    firstParamName + " (" + first + ")");
        }
    }

    public static void throwOnSecondGt(int first, int second,
                                        String firstParamName, String secondParamName) {
        if (second > first) {
            throwException(secondParamName + " (" + second + ") cannot be greater than " +
                    firstParamName + " (" + first + ")");
        }
    }

    private static void throwException(String paramName, String violationType) {
        throwException(paramName + " cannot be " + violationType);
    }

    private static void throwException(String exceptionMessage) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement callingMethod = null;
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.getClassName().equals(Check.class.getName()) &&
                    !stackTraceElement.getClassName().equals(Thread.class.getName())) {
                callingMethod = stackTraceElement;
                break;
            }
        }
        assert callingMethod != null;
        String className = callingMethod.getClassName();
        String methodName = callingMethod.getMethodName();
        throw new IllegalArgumentException(className +
                (!methodName.equals("<init>") ? "." + methodName : "") + ": " + exceptionMessage);
    }
}
