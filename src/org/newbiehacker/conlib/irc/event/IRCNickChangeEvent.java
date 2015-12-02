package org.newbiehacker.conlib.irc.event;

import org.newbiehacker.conlib.irc.IRCConnection;
import org.newbiehacker.conlib.irc.Client;
import org.newbiehacker.conlib.event.Event;

/**
 * Copyright 2006 James Lawrence
 * Date: 24-May-2007
 * Time: 22:23:30
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCNickChangeEvent extends Event<IRCConnection> {
    public final Client client;
    public final String newNick;

    public IRCNickChangeEvent(IRCConnection source, Client client, String newNick) {
        super(source);
        this.client = client;
        this.newNick = newNick;
    }

    public String toString() {
        return "IRCNickChangeEvent [oldNick=" + client.nick + ",newNick=" + newNick + "]";
    }
}