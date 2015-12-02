package org.newbiehacker.conlib.irc.event;

import org.newbiehacker.conlib.irc.IRCConnection;
import org.newbiehacker.conlib.event.Event;

/**
 * Copyright 2006 James Lawrence
 * Date: 25-May-2007
 * Time: 17:03:38
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCErrorEvent extends Event<IRCConnection> {
    public final Object error;

    public IRCErrorEvent(IRCConnection source, Object error) {
        super(source);
        this.error = error;
    }

    public String toString() {
        return "IRCFatalErrorEvent [error=" + error + "]";
    }
}