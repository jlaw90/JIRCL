package org.newbiehacker.conlib.conn;

import org.newbiehacker.conlib.conn.Connection;
import org.newbiehacker.conlib.event.Event;

/**
 * <p>This event is fired when a new connection is created</p>
 */
public final class NewConnectionEvent extends Event<Connection> {
    NewConnectionEvent(Connection con) {
        super(con);
    }
}