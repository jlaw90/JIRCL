package org.newbiehacker.conlib.examples;

import org.newbiehacker.conlib.conn.GlobalManager;
import org.newbiehacker.conlib.conn.UncaughtExceptionEvent;
import org.newbiehacker.conlib.conn.TimerEvent;
import org.newbiehacker.conlib.event.Event;
import org.newbiehacker.conlib.event.EventListener;
import org.newbiehacker.conlib.irc.Client;
import org.newbiehacker.conlib.irc.IRCConnection;
import org.newbiehacker.conlib.irc.Message;
import org.newbiehacker.conlib.irc.MessageType;
import org.newbiehacker.conlib.irc.event.IRCConnectionEvent;
import org.newbiehacker.conlib.irc.event.IRCMessageEvent;
import org.newbiehacker.conlib.irc.event.IRCKickEvent;
import org.newbiehacker.conlib.irc.event.IRCErrorEvent;
import org.newbiehacker.conlib.util.Timer;

import javax.script.*;
import java.io.*;
import java.net.Socket;
import java.util.Properties;
import java.util.List;
import java.awt.*;

/**
 * Copyright 2006 James Lawrence
 * Date: 12-Dec-2006
 * Time: 12:59:00
 * Modification and redistribution without explicit permission by the creator(s) is prohibited
 * This source may be modified for personal use as long as the original author is accredited
 */
public final class IRCBot implements EventListener {
    private IRCConnection con;
    private ScriptEngineManager sem;
    public static final int LAGCHK_TIMER_ID = 0xefefefef;
    public long ping;
    private static Properties config;
    private static Properties users;

    public static void main(String[] args) {
        File f = new File("data/connect.ini");
        try {
            if(!f.exists())
                throw new FileNotFoundException("data/connect.ini does not exist!");
            else if(!f.canRead())
                throw new IOException("data/connect.ini is not readable!");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            String server;
            int port, idx;
            while((line = br.readLine()) != null) {
                idx = line.lastIndexOf(':');
                if(idx >= 0) {
                    server = line.substring(0, idx);
                    port = Integer.parseInt(line.substring(idx + 1));
                } else {
                    server = line;
                    port = 6667;
                }
                new IRCBot(server, port);
            }
        } catch(Exception e) {
            System.err.println(e);
        }
    }

    public IRCBot(String server, int port) {
        try {
            load();
            GlobalManager.addEventListener(this);
            GlobalManager.registerTimer(new Timer(60, -1, LAGCHK_TIMER_ID));
            System.out.println("Connecting to " + server + "...");
            con = new IRCConnection(new Socket(server, port));
            sem = new ScriptEngineManager();
            sem.put("con", con);
            sem.put("owner", this);
            con.connect(config.getProperty("nick"), config.getProperty("login"));
            //new IRCLogger(con);
        } catch (IOException t) {
            t.printStackTrace();
            // First, display error
            GlobalManager.showTrayDialog("Error connecting to IRC server!", "We could not connect to " + server + ":" + port + ".\nPlease make sure you are connected to the internet and that the server and port are correct.", TrayIcon.MessageType.WARNING);
            // We don't need to delegate the error because execution will end anyway
            // We'll try reconnecting...
            new IRCBot(server, port);
        }
    }

    private static void load() {
        try {
            config = new Properties();
            config.load(new FileReader("data/config.ini"));
            users = new Properties();
            users.load(new FileReader("data/users.ini"));
        } catch (Exception e) {
            GlobalManager.showTrayDialog("Error loading IRC bot data", "Error loading IRC bot data.\nPlease re-download.", TrayIcon.MessageType.ERROR);
            GlobalManager.stop();
        }
    }

    private static void save() {
        try {
            config.store(new FileWriter("data/config.ini"), "No comment.");
            users.store(new FileWriter("data/users.ini"), "No comment.");
        } catch (Exception e) {
            e.printStackTrace();
            GlobalManager.stop();
        }
    }

    public String loadScript(String script) {
        File f = new File(script);
        String error = null;
        if (!f.exists() || !f.isFile())
            return "No such script file '" + script + "'";
        String name = f.getName();
        String ext = name.substring(name.lastIndexOf('.') + 1);
        if (sem.getEngineByExtension(ext) == null)
            return "No known script engine to deal with extension " + ext;
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            sem.getEngineByExtension(ext).eval(fr);
        } catch (Exception e) {
            e.printStackTrace();
            error =  "Script Exception from script '" + script + "'";
        }
        try {
            if(fr != null)
                fr.close();
        } catch(Exception e) {
            /**/
        }
        return error;
    }

    public void onConnect() {
        File f = new File(config.getProperty("on_connect"));
        String error = null;
        if (!f.exists() || !f.isFile())
            return;
        String name = f.getName();
        String ext = name.substring(name.lastIndexOf('.') + 1);
        if (sem.getEngineByExtension(ext) == null)
            return;
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            ScriptEngine engine = sem.getEngineByExtension(ext);
            ScriptContext sc = new SimpleScriptContext();
            sc.setBindings(engine.getBindings(ScriptContext.GLOBAL_SCOPE), ScriptContext.GLOBAL_SCOPE);
            sc.setAttribute("network", con.server.network, ScriptContext.ENGINE_SCOPE);
            sc.setAttribute("server", con.server.host, ScriptContext.ENGINE_SCOPE);
            engine.eval(fr, sc);
        } catch (Exception e) {
            e.printStackTrace();
            error =  "Script Exception from script '" + name + "'";
        }
        try {
            if(fr != null)
                fr.close();
        } catch(Exception e) {
            /**/
        }
        if(error != null)
            System.out.println(error);
    }

    public void onEvent(Event e) {
        if(e instanceof UncaughtExceptionEvent && (e.source == this || e.source == con)) {
            Throwable t = ((UncaughtExceptionEvent) e).exception;
            t.printStackTrace();
            System.out.println("UNCAUGHT EXCEPTION, source = " + e.source + ", exception = " + t);
        }
        if (e.source != con && (!(e.source instanceof Timer)))
            return;
        if (e instanceof IRCMessageEvent) {
            Message m = ((IRCMessageEvent) e).message;
            if(m.type == MessageType.PRIVMSG && m.sender.getNick().equals(con.client.nick) && m.content.startsWith("LAGCHK :")) {
                long time = Long.parseLong(m.content.substring(m.content.indexOf(':') + 1));
                ping = System.currentTimeMillis() - time;
                return;
            }
            switch (m.type) {
                case PRIVMSG:
                    String pString = users.getProperty(m.sender.getHost());
                    int privs = -1;
                    if (pString != null)
                        privs = Integer.parseInt(pString);
                    if (!m.content.startsWith("!"))
                        return;
                    m = new Message(m.type, m.sender, m.recipient, m.content.substring(1), m.response_code);
                    handleCommand(m, privs);
                    break;
            }
        } else if (e instanceof IRCConnectionEvent) {
            GlobalManager.showTrayDialog("Connected!", "Connected to The " + ((IRCConnectionEvent) e).network + " IRC Network\n" + con.server.host + ":" + con.server.port, TrayIcon.MessageType.INFO);
            onConnect();
        } else if(e instanceof TimerEvent) {
            Timer t = ((TimerEvent) e).source;
            if(t.timerID == LAGCHK_TIMER_ID) {
                System.out.println("LAGCHECK!");
                con.sendMessage(con.client.nick, "LAGCHK :" + System.currentTimeMillis());
            }
        } else if(e instanceof IRCKickEvent) {
            IRCKickEvent ike = (IRCKickEvent) e;
            if(ike.client.nick.equals(con.client.nick))
                con.join(ike.channel.name);
        } else if(e instanceof IRCErrorEvent) {
            System.out.println("Disconnected!");
            System.out.println("Reconnecting in 10 seconds...");
            GlobalManager.removeEventListener(this);
            try {Thread.sleep(10000);}catch(Exception e1) {}
            new IRCBot(con.server.host, con.server.port);
        }
    }

    private void handleCommand(Message m, int privs) {
        String s = m.content;
        String[] data = s.split(" ");
        String c = data[0].toLowerCase();
        if (c.equals("eval") && privs >= 100) {
            if (data.length < 3 || data.length < 2) {
                con.sendMessage(m.recipient, "USAGE: !eval ENGINE_SHORT_NAME EXPRESSION...");
                return;
            }
            String engine = data[1];
            String eval = s.substring(c.length() + engine.length() + 2);
            if (sem.getEngineByName(engine) == null) {
                con.sendMessage(m.recipient, "Unknown engine type: " + engine);
                return;
            }
            try {
                Object o = sem.getEngineByName(engine).eval(eval);
                if (o != null)
                    con.sendMessage(m.recipient, "Eval: " + o);
            } catch (Exception e1) {
                con.sendMessage(m.recipient, "ScriptException!");
                e1.printStackTrace();
            }
        } else if (c.equals("adduser") && privs >= 50) {
            if (data.length < 3 || data.length < 2) {
                con.sendMessage(m.recipient, "USAGE: !adduser NICK PRIVS");
                return;
            }
            String nick = data[1];
            int uPrivs = 0;
            if (data.length > 2)
                uPrivs = Integer.parseInt(data[2]);
            Client c1 = con.clients.get(nick);
            if (c1 == null) {
                con.sendMessage(m.recipient, "No client with that nick name found.");
                return;
            }
            con.sendMessage(m.recipient, "Host " + c1.host + " added with privelages of " + uPrivs);
            users.setProperty(c1.host, String.valueOf(uPrivs));
            save();
        } else if (c.equals("script") && privs >= 50) {
            if (data.length < 2) {
                con.sendMessage(m.recipient, "USAGE: !script SCRIPT_FILE");
                return;
            }
            String script = s.substring(7);
            String res = loadScript(script);
            if(res != null)
                con.sendMessage(m.recipient, res);
        } else if(c.equals("engines") && privs >= 100) {
            StringBuilder sb = new StringBuilder("Located scripting engines: {");
            List<ScriptEngineFactory> list = sem.getEngineFactories();
            ScriptEngineFactory sef;
            for(int i = 0; i < list.size(); i++) {
                sef = list.get(i);
                sb.append(sef.getLanguageName()).append(" aliases {");
                for(String s1: sef.getNames())
                    sb.append(s1).append(", ");
                sb = sb.delete(sb.length() - 2, sb.length());
                sb.append("}").append(i + 1 == list.size()? "": ", ");
            }
            con.sendMessage(m.recipient, sb.toString());
        } else {
            File f = new File(config.getProperty("on_command"));
            if (!f.exists() || !f.isFile()) {
                System.out.println("No such script file!");
                return;
            }
            String name = f.getName();
            String ext = name.substring(name.lastIndexOf('.') + 1);
            if (sem.getEngineByExtension(ext) == null) {
                System.out.println("No known script engine to deal with extension " + ext);
                return;
            }
            FileReader fr = null;
            try {
                fr = new FileReader(f);
                ScriptContext sc = new SimpleScriptContext();
                sc.setBindings(sem.getBindings(), ScriptContext.GLOBAL_SCOPE);
                sc.setAttribute("message", m, ScriptContext.ENGINE_SCOPE);
                sc.setAttribute("privs", privs, ScriptContext.ENGINE_SCOPE);
                sem.getEngineByExtension(ext).eval(fr, sc);
            } catch (Exception e) {
                System.out.println("Script Exception!");
                e.printStackTrace();
            }
            try {
                if(fr != null)
                    fr.close();
            } catch(Exception e) {
                /**/
            }
        }
    }
}