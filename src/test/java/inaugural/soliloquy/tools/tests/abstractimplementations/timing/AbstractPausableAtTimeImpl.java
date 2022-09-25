package inaugural.soliloquy.tools.tests.abstractimplementations.timing;

import inaugural.soliloquy.tools.timing.AbstractPausableAtTime;

public class AbstractPausableAtTimeImpl extends AbstractPausableAtTime {
    public Long _updateInternalValuesOnUnpauseInput = null;

    public AbstractPausableAtTimeImpl(Long pausedTimestamp, Long mostRecentTimestamp) {
        super(pausedTimestamp, mostRecentTimestamp);
    }

    @Override
    protected void updateInternalValuesOnUnpause(long timestamp) {
        _updateInternalValuesOnUnpauseInput = timestamp;
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}
