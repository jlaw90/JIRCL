package org.newbiehacker.conlib.irc;

import org.newbiehacker.conlib.conn.Connection;
import org.newbiehacker.conlib.conn.GlobalManager;
import org.newbiehacker.conlib.conn.InputHandler;
import org.newbiehacker.conlib.irc.event.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Date;

/**
 * Copyright 2006 James Lawrence
 * Date: 12-Dec-2006
 * Time: 12:59:21
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCConnection extends Connection implements InputHandler, NumericReplies {
    private boolean connected;
    public Server server;
    public Client client;
    public HashMap<String, Channel> channels;
    public HashMap<String, Client> clients;
    private ByteArrayOutputStream bufferOut;

    public IRCConnection(Socket s) throws IOException {
        super(s);
        server = new Server(s.getInetAddress().getHostName(), s.getPort());
        channels = new HashMap<String, Channel>();
        clients = new HashMap<String, Client>();
        bufferOut = new ByteArrayOutputStream();
        addInputHandler(this);
    }

    public void connect(String nick, String login) throws IOException {
        client = new Client(nick, login);
        clients.put(nick, client);
        writeLine("NICK " + nick);
        writeLine("USER " + login + " " + nick + " " + login + " " + nick);
    }

    public void quit() {
        sendRawLine("QUIT");
        connected = false;
    }

    public void quit(String reason) {
        sendRawLine("QUIT :" + reason);
        connected = false;
    }

    public void sendMessage(String recipient, String message) {
        sendRawLine("PRIVMSG " + recipient + " :" + message);
    }

    public void sendNotice(String recipient, String notice) {
        sendRawLine("NOTICE " + recipient + " :" + notice);
    }

    public void sendAction(String recipient, String action) {
        sendMessage(recipient, "\u0001ACTION " + action + "\u0001");
    }

    public void sendCTCP(String recipient, String request) {
        sendMessage(recipient, "\u0001" + request + "\u0001");
    }

    public void sendCTCPReply(String recipient, String type, String args) {
        sendNotice(recipient, "\u0001" + type + " " + args + "\u0001");
    }

    public void join(String channel) {
        sendRawLine("JOIN " + channel);
    }

    public void sendRawLine(String line) {
        if (!connected)
            throw new IllegalStateException("We are not connected to a server!");
        writeLine(line);
    }

    public void handleInput(byte[] data) {
        try {
            bufferOut.write(data);
            byte[] curData = bufferOut.toByteArray();
            bufferOut.reset();
            String s = new String(curData);
            int lastLineIdx = s.lastIndexOf("\r\n") + 2;
            if (lastLineIdx < 2) {
                bufferOut.write(curData);
                return;
            }
            String[] lines = s.substring(0, lastLineIdx).split("\r\n");
            if (curData.length - lastLineIdx != 0)
                bufferOut.write(curData, lastLineIdx, curData.length - lastLineIdx);
            for (String line : lines)
                handleLine(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLine(String line) {
        if (line.startsWith(":"))
            line = line.substring(1);
        String[] d = line.split("\\s+");
        String cont = "";
        if (d.length > 3)
            for (int i = 3; i < d.length; i++)
                cont += d[i] + " ";
        cont = cont.trim();
        if (cont.startsWith(":"))
            cont = cont.substring(1);
        Mask sender = new BasicMask(server.host);
        if (d[0].indexOf('@') > 0 && d[0].indexOf('!') > 0)
            sender = new BasicMask(d[0]);
        if (d.length > 1)
            if (d[1].matches("\\d\\d\\d")) {//Numeric reply!
                Message m = new Message(MessageType.SERVER_RESPONSE, sender, client.nick, cont, Integer.parseInt(d[1]));
                parseNumericReply(Integer.parseInt(d[1]), cont);
                GlobalManager.fireEvent(new IRCMessageEvent(this, m));
            } else {
                if (d[1].equals("NOTICE")) {
                    Message m = new Message(MessageType.NOTICE, sender, d[2], cont);
                    GlobalManager.fireEvent(new IRCMessageEvent(this, m));
                } else if (d[1].equals("PRIVMSG")) {
                    Message m;
                    if (cont.startsWith("\u0001ACTION "))
                        m = new Message(MessageType.ACTION, sender, d[2], cont.substring(8));
                    else if (cont.startsWith("\u0001")) {
                        m = new Message(MessageType.CTCP, sender, d[2], cont.substring(1));
                        if (m.content.equals("FINGER"))
                            sendCTCPReply(m.sender.getNick(), "FINGER", "U wish, fheg.");
                        else if (m.content.equals("VERSION"))
                            sendCTCPReply(m.sender.getNick(), "VERSION", "Conlib IRC Plugin v0.001a by newbiehacker");
                        else if (m.content.startsWith("PING :"))
                            sendCTCPReply(m.sender.getNick(), "PONG", m.content.substring(6));
                        else if (m.content.equals("TIME")) {
                            String s = new Date().toString();
                            s = s.substring(0, s.length() - 9) + s.substring(s.length() - 5);
                            sendCTCPReply(m.sender.getNick(), "TIME", s);
                        }
                    } else
                        m = new Message(MessageType.PRIVMSG, sender, d[2], cont);
                    GlobalManager.fireEvent(new IRCMessageEvent(this, m));
                } else if (d[1].equals("JOIN")) {
                    Channel c;
                    String joinChan = d[2].substring(1);
                    Client client;
                    if (clients.containsKey(sender.getNick())) {
                        client = clients.get(sender.getNick());
                    } else {
                        client = new Client(sender.getNick(), sender.getLogin(), sender.getHost());
                        clients.put(sender.getNick(), client);
                    }
                    if (sender.getNick().equals(client.nick)) {
                        channels.put(joinChan, (c = new Channel(joinChan)));
                        sendRawLine("TOPIC " + joinChan);
                        sendRawLine("WHO " + joinChan);
                        sendRawLine("MODE " + joinChan);
                        sendRawLine("MODE " + joinChan + " b");
                    } else {
                        c = channels.get(joinChan);
                        sendRawLine("WHO " + sender.getNick());
                    }
                    c.clients.add(client);
                    client.channels.add(c);
                    GlobalManager.fireEvent(new IRCJoinEvent(this, sender, c));
                } else if (d[1].equals("PART")) {
                    Channel c = channels.get(d[2]);
                    Client client = clients.get(sender.getNick());
                    c.clients.remove(client);
                    client.channels.remove(c);
                    if (client.nick.equals(this.client.nick))
                        channels.remove(c.name);
                    GlobalManager.fireEvent(new IRCPartEvent(this, client, c, cont));
                } else if (d[1].equals("QUIT")) {
                    GlobalManager.fireEvent(new IRCQuitEvent(this, clients.get(sender.getNick()), line.substring((d[0] + d[1]).length() + 3)));
                    if (sender.getNick().equals(client.nick)) {
                        clients.clear();
                        channels.clear();
                        client = null;
                        server = null;
                    }
                    for(Channel c: channels.values())
                        c.clients.remove(clients.get(sender.getNick()));
                    clients.remove(sender.getNick());
                } else if (d[1].equals("KICK")) {
                    String channel = d[2];
                    String kicked = d[3];
                    String reason = cont.substring(kicked.length() + 2);
                    GlobalManager.fireEvent(new IRCKickEvent(this, clients.get(sender.getNick()), channels.get(channel), clients.get(kicked), reason));
                    clients.get(kicked).channels.remove(channels.get(channel));
                    channels.get(channel).clients.remove(clients.get(kicked));
                    if (kicked.equals(client.nick))
                        channels.remove(channel);
                } else if (d[1].equals("NICK")) {
                    String newNick = d[2].substring(1);
                    GlobalManager.fireEvent(new IRCNickChangeEvent(this, clients.get(sender.getNick()), newNick));
                    Client c = clients.get(sender.getNick());
                    c.nick = newNick;
                    clients.remove(sender.getNick());
                    clients.put(newNick, c);
                    if (client.nick.equals(sender.getNick()))
                        client.nick = newNick;
                } else if (d[1].equals("TOPIC")) {
                    Channel channel = channels.get(d[2]);
                    long time = System.currentTimeMillis();
                    GlobalManager.fireEvent(new IRCTopicChangeEvent(this, clients.get(sender.getNick()), channel, cont));
                    channel.topic = new Topic(cont, time / 1000, sender.getNick());
                } else if (d[1].equals("MODE")) {
                    if(channels.containsKey(d[2]))
                        channels.get(d[2]).addModes(cont);
                    GlobalManager.fireEvent(new IRCModeChangeEvent(this, sender, d[2], cont));
                } else if (d[0].equals("PING")) {
                    String pong = d[1].substring(1);
                    sendRawLine("PONG :" + pong);
                } else if (d[0].equals("ERROR")) {
                    raiseFatal(new IRCErrorEvent(this, line.substring(7)));
                } else
                    System.out.println("Unknown line: " + line);
            }
    }

    private void raiseFatal(Object o) {
        GlobalManager.fireEvent(new IRCErrorEvent(this, o.toString()));
        close();
        connected = false;
    }

    private void parseNumericReply(int code, String text) {
        switch (code) {
                //Most normal replies need to be parsed
            case RPL_WELCOME:
                connected = true;
                server.network = text.substring(15, text.indexOf(" IRC Network"));
                GlobalManager.fireEvent(new IRCConnectionEvent(this, server.network));
                break;
            case RPL_CREATED:
                server.created = text.substring(24);
                break;
            case RPL_MYINFO:
                int idx = text.indexOf(' ');
                server.version = text.substring(idx, text.indexOf(' ', idx + 1) - 1);
                break;
            case RPL_TOPIC:
                idx = text.indexOf(':');
                String cString = text.substring(0, idx - 1);
                String topic = text.substring(idx + 1);
                Channel chan;
                if (channels.containsKey(cString))
                    chan = channels.get(cString);
                else
                    channels.put(cString, chan = new Channel(cString));
                if (chan.topic == null)
                    chan.topic = new Topic(null, -1, null);
                chan.topic.topic = topic;
                break;
            case RPL_TOPICWHOTIME: /*Not in the RFC!*/
                String[] topData = text.split(" ");
                if (channels.containsKey(topData[0]))
                    chan = channels.get(topData[0]);
                else
                    channels.put(topData[0], chan = new Channel(topData[0]));
                if (chan.topic == null)
                    chan.topic = new Topic(null, -1, null);
                chan.topic.setter = topData[1];
                chan.topic.date = Long.parseLong(topData[2]);
                break;
            case RPL_CHANNELMODEIS:
                String[] chanData = text.split(" ");
                channels.get(chanData[0]).addModes(text.substring(chanData[0].length() + 1));
                break;
            case RPL_CREATIONTIME: /*Not in the RFC!*/
                chanData = text.split(" ");
                channels.get(chanData[0]).creation_date = Long.parseLong(chanData[1]);
                break;
            case RPL_BANLIST:
                String[] banData = text.split(" ");
                Ban b = new Ban(new BasicMask(banData[1]), new BasicMask(banData[2]), Long.parseLong(banData[3]));
                channels.get(banData[0]).bans.add(b);
                break;
            case RPL_NAMREPLY:
                String[] data = text.split(" ");
                chan = channels.get(data[1]);
                String[] nicks = text.substring(text.indexOf(':') + 1).split(" ");
                for (String nick : nicks) {
                    if (nick.startsWith("~"))
                        nick = nick.substring(1);
                    if (nick.startsWith("@"))
                        nick = nick.substring(1);
                    if (nick.startsWith("&"))
                        nick = nick.substring(1);
                    if (nick.startsWith("%"))
                        nick = nick.substring(1);
                    if (nick.startsWith("+"))
                        nick = nick.substring(1);
                    Client c;
                    if (clients.containsKey(nick)) {
                        c = clients.get(nick);
                    } else
                        clients.put(nick, c = new Client(nick, null, null));
                    if(!c.channels.contains(chan))
                        c.channels.add(chan);
                    if(!chan.clients.contains(c))
                        chan.clients.add(c);
                }
                break;
            case RPL_WHOREPLY:
                String[] whoData = text.split(" ", 8);
                Channel channel = channels.get(whoData[0]);
                String name = whoData[1];
                String host = whoData[2];
                String nick = whoData[4];

                Client c = clients.get(nick);
                c.user = name;
                c.host = host;
                break;
                //Ignored replies:
            case RPL_LUSERCLIENT:
            case RPL_LUSEROP:
            case RPL_LUSERUNKNOWN:
            case RPL_LUSERCHANNELS:
            case RPL_LOCALUSERS:
            case RPL_GLOBALUSERS:
            case RPL_STATSCONN:    
            case RPL_NOTOPIC:
            case RPL_YOUREOPER:
            case RPL_YOURHOST:
            case RPL_MOTDSTART:
            case RPL_BOUNCE:
            case RPL_MOTD:
            case RPL_ENDOFMOTD:
            case RPL_ENDOFBANLIST:
            case RPL_ENDOFEXCEPTLIST:    
            case RPL_ENDOFNAMES:
            case RPL_ENDOFWHO:
                break;
            default:
                System.out.println(code + ": " + text);
        }
    }

    public String toString() {
        return "IRCConnection, " + client + " connected to " + server;
    }
}