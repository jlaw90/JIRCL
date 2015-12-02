package org.newbiehacker.conlib.irc;

/**
 * Copyright 2006 James Lawrence
 * Date: 30-May-2007
 * Time: 09:46:59
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public class BasicMask implements Mask {
    public final String mask;

    public BasicMask(String s) {
        this.mask = s;
    }

    public String getNick() {
        if(mask.indexOf('!') < 0)
            return null;
        return mask.substring(0, mask.indexOf('!'));
    }

    public String getLogin() {
        if(mask.indexOf('!') < 0)
            return null;
        return mask.substring(mask.indexOf('!') + 1, mask.indexOf('@'));
    }

    public String getHost() {
        if(mask.indexOf('!') < 0)
            return mask;
        return mask.substring(mask.indexOf('@') + 1);
    }

    public String toString() {
        return mask;
    }
}