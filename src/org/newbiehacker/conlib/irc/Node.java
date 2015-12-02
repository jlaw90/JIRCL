package org.newbiehacker.conlib.irc;

/**
 * Copyright 2006 James Lawrence
 * Date: 10-Dec-2006
 * Time: 20:01:45
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public abstract class Node {
    public final String host;

    public Node(String host) {
        this.host = host;
    }
}