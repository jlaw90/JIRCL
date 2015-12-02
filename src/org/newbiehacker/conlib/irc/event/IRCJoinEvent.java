package org.newbiehacker.conlib.irc.event;

import org.newbiehacker.conlib.irc.Channel;
import org.newbiehacker.conlib.irc.IRCConnection;
import org.newbiehacker.conlib.irc.Mask;
import org.newbiehacker.conlib.event.Event;

/**
 * Copyright 2006 James Lawrence
 * Date: 21-May-2007
 * Time: 22:04:24
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCJoinEvent extends Event<IRCConnection> {
    public final Mask user;
    public final Channel channel;

    public IRCJoinEvent(IRCConnection source, Mask user, Channel channel) {
        super(source);
        this.user = user;
        this.channel = channel;
    }

    public String toString() {
        return "IRCJoinEvent [user=" + user + ",channel=" + channel + "]";
    }
}