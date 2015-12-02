package org.newbiehacker.conlib.irc.event;

import org.newbiehacker.conlib.event.Event;
import org.newbiehacker.conlib.irc.IRCConnection;
import org.newbiehacker.conlib.irc.Client;
import org.newbiehacker.conlib.irc.Channel;

/**
 * Copyright 2006 James Lawrence
 * Date: 24-May-2007
 * Time: 22:05:37
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCPartEvent extends Event<IRCConnection> {
    public final Client client;
    public final Channel channel;
    public final String message;

    public IRCPartEvent(IRCConnection source, Client client, Channel channel, String message) {
        super(source);
        this.client = client;
        this.channel = channel;
        this.message = message;
    }

    public String toString() {
        return "IRCPartEvent [channel=" + channel + ",client=" + client.nick + ",message=" + message + "]";
    }
}