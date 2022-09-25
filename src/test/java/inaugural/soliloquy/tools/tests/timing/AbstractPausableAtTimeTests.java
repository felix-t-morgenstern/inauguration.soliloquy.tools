package inaugural.soliloquy.tools.tests.timing;

import inaugural.soliloquy.tools.tests.abstractimplementations.timing.AbstractPausableAtTimeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractPausableAtTimeTests {
    private long PAUSED_TIMESTAMP = 123123L;
    private long MOST_RECENT_TIMESTAMP = 456456L;

    private AbstractPausableAtTimeImpl _abstractPausableAtTime;

    @BeforeEach
    void setUp() {
        _abstractPausableAtTime =
                new AbstractPausableAtTimeImpl(PAUSED_TIMESTAMP, MOST_RECENT_TIMESTAMP);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () ->
                new AbstractPausableAtTimeImpl(PAUSED_TIMESTAMP, null));
        assertThrows(IllegalArgumentException.class, () ->
                new AbstractPausableAtTimeImpl(MOST_RECENT_TIMESTAMP + 1, MOST_RECENT_TIMESTAMP));
    }

    @Test
    void testPausedTimestamp() {
        assertEquals(PAUSED_TIMESTAMP, (long) _abstractPausableAtTime.pausedTimestamp());
    }

    @Test
    void testReportPauseOrUnpauseWithOutdatedTimestamp() {
        assertThrows(IllegalArgumentException.class, () ->
                _abstractPausableAtTime.reportPause(MOST_RECENT_TIMESTAMP - 1));
        assertThrows(IllegalArgumentException.class, () ->
                _abstractPausableAtTime.reportUnpause(MOST_RECENT_TIMESTAMP - 1));
    }

    @Test
    void testReportPauseWhilePausedAndUnpausedWhileUnpaused() {
        assertThrows(IllegalArgumentException.class, () ->
                _abstractPausableAtTime.reportPause(MOST_RECENT_TIMESTAMP));

        _abstractPausableAtTime.reportUnpause(MOST_RECENT_TIMESTAMP);

        assertThrows(IllegalArgumentException.class, () ->
                _abstractPausableAtTime.reportUnpause(MOST_RECENT_TIMESTAMP));
    }

    @Test
    void testReportUnpauseCallsUpdateInternalValuesOnUnpause() {
        long unpauseTimestamp = 789789L;

        _abstractPausableAtTime.reportUnpause(unpauseTimestamp);

        assertEquals(unpauseTimestamp,
                (long) _abstractPausableAtTime._updateInternalValuesOnUnpauseInput);
    }
}
