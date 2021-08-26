package inaugural.soliloquy.tools.timing;

// TODO: Test this implementation taken from Graphics
public class TimestampValidator {
    private Long _mostRecentTimestamp;

    public TimestampValidator(Long mostRecentTimestamp) {
        _mostRecentTimestamp = mostRecentTimestamp;
    }

    public void validateTimestamp(long timestamp) {
        if (_mostRecentTimestamp != null && timestamp < _mostRecentTimestamp) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

            throw new IllegalArgumentException(stackTrace[2].getClassName() + "." +
                    stackTrace[2].getMethodName() + ": provided outdated timestamp (" + timestamp +
                    ")");
        }
        _mostRecentTimestamp = timestamp;
    }

    public void validateTimestamp(String className, long timestamp) {
        if (_mostRecentTimestamp != null && timestamp < _mostRecentTimestamp) {
            String invokingClass = "undiscoveredClass";
            String invokingMethod = "undiscoveredMethod";

            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            for(StackTraceElement element : stackTrace) {
                if (element.getClassName().equals(className)) {
                    invokingClass = element.getClassName();
                    invokingMethod = element.getMethodName();

                    break;
                }
            }

            throw new IllegalArgumentException(invokingClass + "." +invokingMethod +
                    ": provided outdated timestamp (" + timestamp + ")");
        }
        _mostRecentTimestamp = timestamp;
    }

    public Long mostRecentTimestamp() {
        return _mostRecentTimestamp;
    }
}
