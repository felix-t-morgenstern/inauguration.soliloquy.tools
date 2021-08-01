package inaugural.soliloquy.tools.timing;

import inaugural.soliloquy.tools.Check;
import soliloquy.specs.common.shared.HasPeriodModuloOffset;
import soliloquy.specs.common.shared.PausableAtTime;

// TODO: Test this as a stub implementation, and not merely when extended in other modules
public abstract class LoopingPausableAtTimeAbstract
        extends PausableAtTimeAbstract
        implements PausableAtTime, HasPeriodModuloOffset {
    protected final int PERIOD_DURATION;

    // TODO: Add constructor containing both paused timestamp and most recent reported timestamp
    public LoopingPausableAtTimeAbstract(int periodDuration, int periodModuloOffset,
                                         Long pausedTimestamp, Long mostRecentTimestamp) {
        super(pausedTimestamp, mostRecentTimestamp);
        if (periodModuloOffset >= periodDuration) {
            throw new IllegalArgumentException("GlobalLoopingAnimationImpl: periodModuloOffset (" +
                    periodModuloOffset + ") cannot be greater than period duration (" +
                    periodDuration + ")");
        }
        PERIOD_DURATION = periodDuration;
        _periodModuloOffset = Check.throwOnLtValue(periodModuloOffset, 0, "periodModuloOffset");
    }

    @Override
    public int periodModuloOffset() {
        return _periodModuloOffset;
    }

    @Override
    protected void updateInternalValuesOnUnpause(long timestamp) {
        _periodModuloOffset = (int)((_periodModuloOffset - (timestamp - _pausedTimestamp)
                + PERIOD_DURATION) % PERIOD_DURATION);
        while (_periodModuloOffset < 0) {
            _periodModuloOffset += PERIOD_DURATION;
        }
    }
}
