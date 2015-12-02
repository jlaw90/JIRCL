package org.newbiehacker.conlib.conn;

import org.newbiehacker.conlib.event.Event;

/**
 * <p>This event is fired when an exception that is uncaught by a connection delegates through to one of out threads</p>
 * <p>The source is the object that triggered the event, and exception is the throwable that was uncaught</p>
 */
public final class UncaughtExceptionEvent extends Event<Object> {
    /**
     * <p>The Throwable that delegated to our thread</p>
     */
    public final Throwable exception;

    UncaughtExceptionEvent(Object source, Throwable exception) {
        super(source);
        this.exception = exception;
    }

    public String toString() {
        return "UncaughtExceptionEvent [source=" + source + ",exception=" + exception + "]";
    }
}