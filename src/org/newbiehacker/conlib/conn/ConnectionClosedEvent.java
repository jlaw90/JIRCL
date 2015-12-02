package org.newbiehacker.conlib.conn;

import org.newbiehacker.conlib.conn.Connection;
import org.newbiehacker.conlib.event.Event;

/**
 * <p>This event is fired when close() is called on a Connection</p>
 */
public final class ConnectionClosedEvent extends Event<Connection> {
    ConnectionClosedEvent(Connection c) {
        super(c);
    }
}