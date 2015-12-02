package org.newbiehacker.conlib.irc;

import org.newbiehacker.conlib.irc.Mask;

import java.util.Date;

/**
 * Copyright 2006 James Lawrence
 * Date: 13-Dec-2006
 * Time: 01:44:27
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class Ban {
    public final Mask mask;
    private final long date;
    public final Mask setter;

    public Ban(Mask mask, Mask setter, long date) {
        this.mask = mask;
        this.setter = setter;
        this.date = date;
    }

    public Date getDate() {
        return new Date(date * 1000);
    }

    public String toString() {
        return "Ban \"" + mask + "\" set by " + setter.getNick() + " on " + getDate();
    }
}