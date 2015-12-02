package org.newbiehacker.conlib.irc.event;

import org.newbiehacker.conlib.event.Event;
import org.newbiehacker.conlib.irc.IRCConnection;
import org.newbiehacker.conlib.irc.Client;
import org.newbiehacker.conlib.irc.Channel;

/**
 * Copyright 2006 James Lawrence
 * Date: 24-May-2007
 * Time: 22:18:42
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCKickEvent extends Event<IRCConnection> {
    public final Client kicker;
    public final Channel channel;
    public final Client client;
    public final String message;

    public IRCKickEvent(IRCConnection source, Client kicker, Channel channel, Client client, String message) {
        super(source);
        this.kicker = kicker;
        this.channel = channel;
        this.client = client;
        this.message = message;
    }

    public String toString() {
        return "IRCKickEvent [channel=" + channel.name + ",kicker=" + kicker.nick + ",kickee=" + client.nick + ",message=" + message + "]";
    }
}