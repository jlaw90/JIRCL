package org.newbiehacker.conlib.irc.event;

import org.newbiehacker.conlib.event.Event;
import org.newbiehacker.conlib.irc.IRCConnection;

/**
 * Copyright 2006 James Lawrence
 * Date: 21-May-2007
 * Time: 22:17:14
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCConnectionEvent extends Event<IRCConnection> {
    public final String network;

    public IRCConnectionEvent(IRCConnection source, String network) {
        super(source);
        this.network = network;
    }

    public String toString() {
        return "IRCConnectionEvent [network=" + network + "]";
    }
}