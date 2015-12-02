package org.newbiehacker.conlib.irc;

/**
 * Copyright 2006 James Lawrence
 * Date: 10-Dec-2006
 * Time: 19:39:15
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class Message {
    public final MessageType type;
    public final Mask sender;
    public final String recipient;
    public String content;
    public final int response_code;//Only set on server response

    public Message(MessageType type, Mask sender, String target, String content, int response_code) {
        this.type = type;
        this.sender = sender;
        this.recipient = target;
        this.content = content;
        this.response_code = response_code;
    }

    public Message(MessageType type, Mask sender, String target, String content) {
        this(type, sender, target, content, -1);
    }

    public boolean isNotice() {
        return type == MessageType.NOTICE;
    }

    public boolean isAction() {
        return type == MessageType.ACTION;
    }

    public boolean isMessage() {
        return type == MessageType.PRIVMSG;
    }

    public boolean isCtcp() {
        return type == MessageType.CTCP;
    }

    public boolean isRaw() {
        return type == MessageType.SERVER_RESPONSE;
    }

    public String toString() {
        return "Message [type=" + type + ",sender=" + sender.getNick() +
                ",content=" + content + "]";
    }
}