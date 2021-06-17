package inaugural.soliloquy.tools.timing;

import soliloquy.specs.common.shared.PausableAtTime;

// TODO: Test this as a stub implementation, and not merely when extended in other modules
public abstract class FinitePausableAtTimeAbstract
        extends PausableAtTimeAbstract
        implements PausableAtTime {
    // NB: This time is either the starting time, e.g. FiniteAnimationRenderable, or the ending
    //     time, e.g. OneTimeClockBasedTimer
    protected long _anchorTime;

    // TODO: Add constructor containing both paused timestamp and most recent reported timestamp
    protected FinitePausableAtTimeAbstract(long anchorTime, Long pausedTimestamp) {
        super(pausedTimestamp);
        _anchorTime = anchorTime;
    }

    @Override
    protected void updateInternalValuesOnUnpause(long timestamp) {
        _anchorTime += timestamp - _pausedTimestamp;
    }
}
