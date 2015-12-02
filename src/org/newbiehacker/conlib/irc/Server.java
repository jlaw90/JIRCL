package org.newbiehacker.conlib.irc;

/**
 * Copyright 2006 James Lawrence
 * Date: 10-Dec-2006
 * Time: 20:19:23
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class Server extends Node {
    public final int port;
    public String network;
    public String version;
    public String created;

    public Server(String host, int port) {
        super(host);
        this.port = port;
    }

    public Server(String host) {
        this(host, 6667);
    }

    public int getPort() {
        return port;
    }

    public String getNetwork() {
        return network;
    }

    public String toString() {
        return host + (network != null? "[" + network + "]": "") + ":" + port;
    }
}