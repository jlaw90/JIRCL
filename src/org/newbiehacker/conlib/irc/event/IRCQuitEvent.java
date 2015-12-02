package org.newbiehacker.conlib.irc.event;

import org.newbiehacker.conlib.event.Event;
import org.newbiehacker.conlib.irc.IRCConnection;
import org.newbiehacker.conlib.irc.Client;

/**
 * Copyright 2006 James Lawrence
 * Date: 24-May-2007
 * Time: 22:15:09
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCQuitEvent extends Event<IRCConnection> {
    public final Client client;
    public final String message;

    public IRCQuitEvent(IRCConnection source, Client client, String message) {
        super(source);
        this.client = client;
        this.message = message;
    }

    public String toString() {
        return "IRCQuitEvent [client=" + client.nick + ",message=" + message + "]";
    }
}