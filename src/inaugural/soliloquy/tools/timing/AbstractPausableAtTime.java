package inaugural.soliloquy.tools.timing;

import inaugural.soliloquy.tools.Tools;
import soliloquy.specs.common.shared.PausableAtTime;

public abstract class AbstractPausableAtTime implements PausableAtTime {
    protected final TimestampValidator TIMESTAMP_VALIDATOR;

    protected int _periodModuloOffset;
    protected Long _pausedTimestamp;

    public AbstractPausableAtTime(Long pausedTimestamp, Long mostRecentTimestamp) {
        if (pausedTimestamp != null) {
            if (mostRecentTimestamp == null) {
                throw new IllegalArgumentException("AbstractPausableAtTime: cannot have null " +
                        "mostRecentTimestamp and non-null pausedTimestamp");
            }
            else if (pausedTimestamp > mostRecentTimestamp) {
                throw new IllegalArgumentException("AbstractPausableAtTime: pausedTimestamp (" +
                        pausedTimestamp + ") cannot be greater than mostRecentTimestamp (" +
                        mostRecentTimestamp + ")");
            }
        }
        TIMESTAMP_VALIDATOR = new TimestampValidator(mostRecentTimestamp);
        _pausedTimestamp = pausedTimestamp;
    }

    @Override
    public Long pausedTimestamp() {
        return _pausedTimestamp;
    }

    @Override
    public void reportPause(long timestamp) throws IllegalArgumentException {
        Long priorMostRecentTimestamp = TIMESTAMP_VALIDATOR.mostRecentTimestamp();
        TIMESTAMP_VALIDATOR.validateTimestamp(timestamp);
        if (_pausedTimestamp != null) {
            throw new IllegalArgumentException(Tools.callingClassName(2) + ".reportPause: " +
                    "cannot pause if already paused");
        }
        if (priorMostRecentTimestamp != null && timestamp < priorMostRecentTimestamp) {
            throw new IllegalArgumentException(Tools.callingClassName(2) + ".reportPause: " +
                    "cannot pause at timestamp prior to most recent unpausing");
        }
        _pausedTimestamp = timestamp;
    }

    @Override
    public void reportUnpause(long timestamp) throws IllegalArgumentException {
        Long priorMostRecentTimestamp = TIMESTAMP_VALIDATOR.mostRecentTimestamp();
        TIMESTAMP_VALIDATOR.validateTimestamp(timestamp);
        if (_pausedTimestamp == null) {
            throw new IllegalArgumentException(Tools.callingClassName(2) + ".reportUnpause: " +
                    "cannot unpause if already unpaused");
        }
        if (priorMostRecentTimestamp != null && timestamp < priorMostRecentTimestamp) {
            throw new IllegalArgumentException(Tools.callingClassName(2) + ".reportUnpause: " +
                    "cannot unpause at timestamp prior to most recent pausing");
        }

        updateInternalValuesOnUnpause(timestamp);

        _pausedTimestamp = null;
    }

    protected abstract void updateInternalValuesOnUnpause(long timestamp);
}
