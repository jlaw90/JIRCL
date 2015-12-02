package org.newbiehacker.conlib.irc.event;

import org.newbiehacker.conlib.event.Event;
import org.newbiehacker.conlib.irc.IRCConnection;
import org.newbiehacker.conlib.irc.Message;

/**
 * Copyright 2006 James Lawrence
 * Date: 21-May-2007
 * Time: 21:47:54
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCMessageEvent extends Event<IRCConnection> {
    public final Message message;

    public IRCMessageEvent(IRCConnection source, Message m) {
        super(source);
        this.message = m;
    }

    public String toString() {
        return "IRCMessageEvent [" + message.toString() + "]";
    }
}