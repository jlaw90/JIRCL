package org.newbiehacker.conlib.irc.event;

import org.newbiehacker.conlib.event.Event;
import org.newbiehacker.conlib.irc.IRCConnection;
import org.newbiehacker.conlib.irc.Mask;

/**
 * Copyright 2006 James Lawrence
 * Date: 21-May-2007
 * Time: 22:11:11
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCModeChangeEvent extends Event<IRCConnection> {
    public final Mask sender;
    public final String target;
    public final String modes;

    public IRCModeChangeEvent(IRCConnection source, Mask sender, String target, String modes) {
        super(source);
        this.sender = sender;
        this.target = target;
        this.modes = modes;
    }

    public String toString() {
        return "IRCModeChangeEvent [sender=" + sender + ",target=" + target + ",modes=" + modes + "]";
    }
}