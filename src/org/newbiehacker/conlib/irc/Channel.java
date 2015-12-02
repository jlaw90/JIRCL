package org.newbiehacker.conlib.irc;

import org.newbiehacker.conlib.irc.Client;

import java.util.Date;
import java.util.Vector;
import java.util.HashMap;

/**
 * Copyright 2006 James Lawrence
 * Date: 10-Dec-2006
 * Time: 20:39:23
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class Channel {
    public static final HashMap<Character, Boolean> HAS_ARG;
    public final String name;
    public final HashMap<Character, String> modes;
    public long creation_date;
    public Topic topic;
    public final Vector<Client> clients;
    public final Vector<Ban> bans;

    public Channel(String name) {
        this.name = name;
        this.modes = new HashMap<Character, String>();
        this.clients = new Vector<Client>();
        this.bans = new Vector<Ban>();
    }

    public Date getCreationDate() {
        return new Date(creation_date * 1000);
    }

    public Client[] getUsers() {
        Client[] clients = new Client[this.clients.size()];
        this.clients.toArray(clients);
        return clients;
    }

    public Ban[] getBans() {
        Ban[] bans = new Ban[this.bans.size()];
        this.bans.toArray(bans);
        return bans;
    }

    public void addModes(String modes) {
        String[] data = modes.split(" ");
        char[] chars = data[0].toCharArray();
        int argIndex = 1;
        boolean add = true;
        for(char c: chars) {
            if(c == '+')
                add = true;
            else if(c == '-')
                add = false;
            else if(c == 'o' || c == 'v')
                argIndex++;
            else {
                if(add) {
                    String arg = "";
                    if(HAS_ARG.get(c) != null && HAS_ARG.get(c) == Boolean.TRUE)
                        arg = data[argIndex++];
                    this.modes.put(c, arg);
                } else {
                    this.modes.remove(c);
                }
            }
        }
    }

    public String toString() {
        return name;
    }

    static {
        HAS_ARG = new HashMap<Character, Boolean>();
        HAS_ARG.put('b', true);
        HAS_ARG.put('c', false);
        HAS_ARG.put('d', true);
        HAS_ARG.put('e', true);
        HAS_ARG.put('g', false);
        HAS_ARG.put('i', false);
        HAS_ARG.put('l', true);
        HAS_ARG.put('j', false);
        HAS_ARG.put('J', true);
        HAS_ARG.put('k', true);
        HAS_ARG.put('l', true);
        HAS_ARG.put('L', false);
        HAS_ARG.put('m', false);
        HAS_ARG.put('n', false);
        HAS_ARG.put('o', true);
        HAS_ARG.put('p', false);
        HAS_ARG.put('q', true);
        HAS_ARG.put('Q', false);
        HAS_ARG.put('r', false);
        HAS_ARG.put('R', false);
        HAS_ARG.put('s', false);
        HAS_ARG.put('t', false);
        HAS_ARG.put('v', true);
        HAS_ARG.put('z', false);
    }
}