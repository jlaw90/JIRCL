package org.newbiehacker.conlib.conn;

import org.newbiehacker.conlib.event.Event;
import org.newbiehacker.conlib.util.Timer;

/**
 * This event is fired when a timer has expired, the source object is of type Timer and contains the timer that expired</p>
 */
public final class TimerEvent extends Event<Timer> {
    TimerEvent(Timer source) {
        super(source);
    }

    public String toString() {
        return "TimerEvent [Timer has been triggered after " + source.secs + " secs]";
    }
}