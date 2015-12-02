package org.newbiehacker.conlib.examples;

import org.newbiehacker.conlib.conn.GlobalManager;
import org.newbiehacker.conlib.conn.TimerEvent;
import org.newbiehacker.conlib.event.EventListener;
import org.newbiehacker.conlib.event.Event;
import org.newbiehacker.conlib.util.Timer;

/**
 * Copyright 2006 James Lawrence
 * Date: 31-May-2007
 * Time: 01:14:06
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class TimerTester {
    public static void main(String[] args) {
        new TimerTester();
    }

    public TimerTester() {
        for(int i = 0; i < 5; i++)
            GlobalManager.registerTimer(new Timer(i, i, i));
        GlobalManager.stop();
    }
}