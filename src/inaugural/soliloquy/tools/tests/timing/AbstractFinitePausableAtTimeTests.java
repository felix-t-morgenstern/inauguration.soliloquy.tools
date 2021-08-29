package inaugural.soliloquy.tools.tests.timing;

import inaugural.soliloquy.tools.tests.abstractimplementations.timing.FinitePausableAtTimeImpl;
import inaugural.soliloquy.tools.timing.AbstractFinitePausableAtTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractFinitePausableAtTimeTests {
    private long PAUSED_TIMESTAMP = 123123L;
    private long MOST_RECENT_TIMESTAMP = 456456L;
    private long ANCHOR_TIME = 789789L;

    private AbstractFinitePausableAtTime _finitePausableAtTime;
    private AbstractFinitePausableAtTime _finitePausableAtTimeWithAnchor;

    @BeforeEach
    void setUp() {
        _finitePausableAtTime =
                new FinitePausableAtTimeImpl(PAUSED_TIMESTAMP, MOST_RECENT_TIMESTAMP);
        _finitePausableAtTimeWithAnchor =
                new FinitePausableAtTimeImpl(ANCHOR_TIME, PAUSED_TIMESTAMP, MOST_RECENT_TIMESTAMP);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () ->
                new FinitePausableAtTimeImpl(MOST_RECENT_TIMESTAMP + 1, MOST_RECENT_TIMESTAMP));
        assertThrows(IllegalArgumentException.class, () ->
                new FinitePausableAtTimeImpl(ANCHOR_TIME, MOST_RECENT_TIMESTAMP + 1,
                        MOST_RECENT_TIMESTAMP));
    }

    @Test
    void testPausedTimestamp() {
        assertEquals(PAUSED_TIMESTAMP, (long)_finitePausableAtTime.pausedTimestamp());
        assertEquals(PAUSED_TIMESTAMP, (long)_finitePausableAtTimeWithAnchor.pausedTimestamp());
    }

    @Test
    void testReportPauseAndUnpauseAndUpdateInternalValuesOnUnpause() {
        long unpauseTimestamp = 567567L;
        long secondPauseTimestamp = 678678L;

        assertThrows(IllegalArgumentException.class, () ->
                _finitePausableAtTime.reportPause(unpauseTimestamp));
        assertThrows(IllegalArgumentException.class, () ->
                _finitePausableAtTimeWithAnchor.reportPause(unpauseTimestamp));

        _finitePausableAtTime.reportUnpause(unpauseTimestamp);
        _finitePausableAtTimeWithAnchor.reportUnpause(unpauseTimestamp);

        assertNull(_finitePausableAtTime.pausedTimestamp());
        assertNull(_finitePausableAtTimeWithAnchor.pausedTimestamp());
        assertEquals(ANCHOR_TIME + (unpauseTimestamp - PAUSED_TIMESTAMP),
                ((FinitePausableAtTimeImpl)_finitePausableAtTimeWithAnchor).getAnchorTime());

        assertThrows(IllegalArgumentException.class, () ->
                _finitePausableAtTime.reportUnpause(secondPauseTimestamp));
        assertThrows(IllegalArgumentException.class, () ->
                _finitePausableAtTimeWithAnchor.reportUnpause(secondPauseTimestamp));

        _finitePausableAtTime.reportPause(secondPauseTimestamp);
        _finitePausableAtTimeWithAnchor.reportPause(secondPauseTimestamp);

        assertEquals(secondPauseTimestamp, (long)_finitePausableAtTime.pausedTimestamp());
        assertEquals(secondPauseTimestamp,
                (long)_finitePausableAtTimeWithAnchor.pausedTimestamp());
    }
}
