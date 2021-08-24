package inaugural.soliloquy.tools.timing;

import soliloquy.specs.common.shared.PausableAtTime;

// TODO: Test this as a stub implementation, and not merely when extended in other modules
public abstract class AbstractFinitePausableAtTime
        extends AbstractPausableAtTime
        implements PausableAtTime {
    // NB: This time is either the starting time, e.g. FiniteAnimationRenderable, or the ending
    //     time, e.g. OneTimeClockBasedTimer
    protected long _anchorTime;

    // TODO: Add constructor containing both paused timestamp and most recent reported timestamp
    // NB: This constructor is used for FiniteLinearMovingProvider, which doesn't care about anchor
    // times
    protected AbstractFinitePausableAtTime(Long pausedTimestamp, Long mostRecentTimestamp) {
        super(pausedTimestamp, mostRecentTimestamp);
    }

    protected AbstractFinitePausableAtTime(long anchorTime, Long pausedTimestamp,
                                           Long mostRecentTimestamp) {
        super(pausedTimestamp, mostRecentTimestamp);
        _anchorTime = anchorTime;
    }

    @Override
    protected void updateInternalValuesOnUnpause(long timestamp) {
        _anchorTime += timestamp - _pausedTimestamp;
    }
}
