package org.newbiehacker.conlib.irc;

import java.util.Date;

/**
 * Copyright 2006 James Lawrence
 * Date: 11-Dec-2006
 * Time: 11:02:03
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class Topic {
    String topic;
    long date;
    String setter;

    public Topic(String topic, long date, String setter) {
        this.topic = topic;
        this.date = date;
        this.setter = setter;
    }

    public String getTopic() {
        return topic;
    }

    public Date getDate() {
        return new Date(date * 1000);
    }

    public String getSetter() {
        return setter;
    }

    public String toString() {
        return topic + " set by " + setter + " on " + getDate();
    }
}