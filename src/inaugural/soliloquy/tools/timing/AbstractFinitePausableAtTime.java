package inaugural.soliloquy.tools.timing;

import soliloquy.specs.common.shared.PausableAtTime;

public abstract class AbstractFinitePausableAtTime
        extends AbstractPausableAtTime
        implements PausableAtTime {
    // NB: This time is either the starting time, e.g. FiniteAnimationRenderable, or the ending
    //     time, e.g. OneTimeClockBasedTimer
    protected long _anchorTime;

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
