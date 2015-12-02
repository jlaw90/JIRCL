package org.newbiehacker.conlib.irc;

import org.newbiehacker.conlib.irc.Channel;

import java.util.Vector;

/**
 * Copyright 2006 James Lawrence
 * Date: 10-Dec-2006
 * Time: 20:34:03
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class Client extends Node implements Mask {
    public String nick;
    public String host;
    public String user;
    public Vector<Channel> channels;

    public Client(String nick, String user, String host) {
        super(host);
        this.nick = nick;
        this.user = user;
        this.channels = new Vector<Channel>();
    }

    public Client(String nick, String user) {
        this(nick, user, null);
    }

    public boolean isOnChannel(Channel c) {
        return channels.contains(c);
    }

    public Channel[] getChannels() {
        Channel[] cA = new Channel[channels.size()];
        cA = channels.toArray(cA);
        return cA;
    }

    public String toString() {
        return nick + "!" + user + "@" + host;
    }

    public String getNick() {
        return nick;
    }

    public String getLogin() {
        return user;
    }

    public String getHost() {
        return host;
    }

    public boolean equals(Object o) {
        return o instanceof Client && ((Client) o).nick.equalsIgnoreCase(nick);
    }
}