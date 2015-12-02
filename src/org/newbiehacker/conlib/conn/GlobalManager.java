package org.newbiehacker.conlib.conn;

import org.newbiehacker.conlib.event.Event;
import org.newbiehacker.conlib.event.EventListener;
import org.newbiehacker.conlib.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>The GlobalManager class is a utility class that handles connections, dialogs, events and timers.</p>
 */
public final class GlobalManager implements ActionListener, MouseListener, Runnable {
    /**
     * <p>Defines whether graphics are to be used or not</p>
     * <p>It can be set before runtime by appending -Dncon.graphics to the command line</p>
     * <p>If it is not set, then graphics are enabled by default, otherwise they are ONLY enabled if the property is "enabled"</p>
     * <p>Implementations should check this before creating any frames or dialogs</p>
     * <p>Calling showTrayIcon or showInformationDialog will simply output information to the console if this is false</p>
     */
    public static final boolean GRAPHICS_ENABLED;
    /**
     * <p>Whether we should be verbose in what we are doing or not</p>
     * <p>It can be set before runtime by appending -Dncon.verbose to the command line</p>
     * <p>It is disabled by default, and will only be enabled if the command line property is "enabled"</p>
     * <p>This can be used by developers for debugging puposes</p>
     */
    public static final boolean VERBOSE;
    /**
     * <p>Thread sleep (Added for Rictoo)</p>
     */
    public static final long SLEEP;

    private static final ArrayList<EventListener> LISTENERS;
    private static final ArrayList<Timer> TIMERS;
    private static final Queue<Event<? extends Object>> EVENT_QUEUE;
    private static final Queue<InformationDialog> DIALOG_QUEUE;

    private static TrayIcon trayIcon;
    private static PopupMenu pop;
    private static GlobalManager _inst;
    private static boolean visibleDialog;
    private static boolean destroyDialog;
    private static long dialogGenTime;

    static boolean running;
    static final ArrayList<Connection> CONNECTIONS;

    private GlobalManager() {
    }

    static void registerConnection(Connection c) {
        CONNECTIONS.add(c);
        verboseln("registerConnection(" + c + ");");
    }

    static void unregisterConnection(Connection c) {
        CONNECTIONS.remove(c);
        verboseln("unregisterConnection(" + c + ");");
    }

    /**
     * <p>Adds an event listener to be notified of any new events</p>
     * @param el The event listener
     */
    public static void addEventListener(EventListener el) {
        verboseln("addEventListener(" + el + ");");
        synchronized (LISTENERS) {
            LISTENERS.add(el);
        }
    }

    /**
     * <p>Removes an event listener from the event notification array</p>
     * @param el The event listener
     */
    public static void removeEventListener(EventListener el) {
        verboseln("removeEventListener(" + el + ");");
        synchronized (LISTENERS) {
            LISTENERS.remove(el);
        }
    }

    /**
     * <p>Fires an event which will be passed to all our EventListeners</p>
     * @param e The event to fire
     */
    public static void fireEvent(Event<? extends Object> e) {
        verboseln("fireEvent(" + e + ");");
        synchronized (EVENT_QUEUE) {
            EVENT_QUEUE.add(e);
        }
    }

    /**
     * <p>Registers a timer so that we can notify any event listeners when it "goes off"</p>
     * @param t The timer to register
     */
    public static void registerTimer(Timer t) {
        verboseln("registerTimer(" + t + ");");
        TIMERS.add(t);
    }

    /**
     * <p>Unregisters a timer which will stop it from timing</p>
     * @param t The timer to remove
     */
    public static void unregisterTimer(Timer t) {
        verboseln("unregisterTimer(" + t + ");");
        TIMERS.remove(t);
    }

    /**
     * <p>Shows a native tray dialog if supported by the system, otherwise it displays an InformationDialog</p>
     * @param title The title of the tray dialog
     * @param content The content to be displayed in the tray dialog
     * @param messageType The type of tray dialog it is
     */
    public static void showTrayDialog(String title, String content, TrayIcon.MessageType messageType) {
        verboseln("showTrayDialog(" + title + ", " + content + ", " + messageType + ");");
        if (trayIcon != null)
            trayIcon.displayMessage(title, content, messageType);
        else
            showInformationDialog(title, content);
    }

    /**
     * <p>Attempts to create an InformationDialog with the specified attributes</p>
     * <p>If GRAPHICS_ENABLED is false then this will simply echo the output to the console</p>
     * @param title The title of this dialog
     * @param content The content of this dialog
     * @param titleFont The font to use in the dialogs title
     * @param contentFont The font to use for the dialogs content
     * @param titleForeground The Color of the text in the title
     * @param contentForeground The Color of the text in the content
     * @param titleBackground The Color of the background in the title
     * @param contentBackground The Color of the background in the content
     */
    public static void showInformationDialog(String title, String content, Font titleFont, Font contentFont,
                                             Color titleForeground, Color contentForeground, Color titleBackground,
                                             Color contentBackground) {
        verboseln("showInformationDialog(" + title + ", " + content + ");");
        if(!GRAPHICS_ENABLED) {
            System.out.println("Information [title=" + title + ",content=" + content + "]");
            return;
        }
        InformationDialog id = new InformationDialog(title, content, titleFont, contentFont, titleForeground,
                contentForeground, titleBackground, contentBackground);
        id.addMouseListener(_inst);
        id.setPreferredSize(new Dimension(150, 100));
        DIALOG_QUEUE.add(id);
    }

    /**
     * <p>Attempts to create an InformationDialog with the specified attributes</p>
     * <p>If GRAPHICS_ENABLED is false then this will simply echo the output to the console</p>
     * @param title The title of this dialog
     * @param content The content of this dialog
     * @param titleFont The font to use in the dialogs title
     * @param contentFont The font to use for the dialogs content
     * @param titleForeground The Color of the text in the title
     * @param contentForeground The Color of the text in the content
     */
    public static void showInformationDialog(String title, String content, Font titleFont, Font contentFont,
                                             Color titleForeground, Color contentForeground) {
        showInformationDialog(title, content, titleFont, contentFont, titleForeground, contentForeground, null, null);
    }

    /**
     * <p>Attempts to create an InformationDialog with the specified attributes</p>
     * <p>If GRAPHICS_ENABLED is false then this will simply echo the output to the console</p>
     * @param title The title of this dialog
     * @param content The content of this dialog
     * @param titleFont The font to use in the dialogs title
     * @param contentFont The font to use for the dialogs content
     */
    public static void showInformationDialog(String title, String content, Font titleFont, Font contentFont) {
        showInformationDialog(title, content, titleFont, contentFont, null, null, null, null);
    }

    /**
     * <p>Attempts to create an InformationDialog with the specified attributes</p>
     * <p>If GRAPHICS_ENABLED is false then this will simply echo the output to the console</p>
     * @param title The title of this dialog
     * @param content The content of this dialog
     */
    public static void showInformationDialog(String title, String content) {
        showInformationDialog(title, content, null, null, null, null, null, null);
    }

    /**
     * <p>If GRAPHICS_ENABLED is true and the OS supports system trays and we have added our icon to it, then this method will add the specified menu to the context menu of the tray icon</p>
     * <p>Its use is intended for simple control of protocol implementations</p>
     * @param menu The menu to add
     */
    public static void addTrayMenu(Menu menu) {
        if (pop != null)
            pop.add(menu);
    }

    /**
     * <p>If GRAPHICS_ENABLED is true and the OS supports system trays and we have added our icon to it, then this method will remove the specified menu from the context menu of the tray icon</p>
     * @param menu The menu to remove
     */
    public static void removeTrayMenu(Menu menu) {
        if (pop != null)
            pop.remove(menu);
    }

    /**
     * <p>If VERBOSE is true then this will simply call System.out.print with the Object</p>
     * @param o The object to echo if VERBOSE is true
     */
    public static void verbose(Object o) {
        if(VERBOSE)
            System.out.print(o);
    }

    /**
     * <p>If VERBOSE is true then this will simply call System.out.println with the Object</p>
     * @param o The object to echo if VERBOSE is true
     */
    public static void verboseln(Object o) {
        if(VERBOSE)
            System.out.println(o);
    }

    /**
     * <p>Stops all base threads from running</p>
     */
    public static void stop() {
        verboseln("stop();");
        running = false;
    }

    static void init() {
        if (_inst == null) {
            verboseln("init();");
            running = true;
            _inst = new GlobalManager();
            new Thread(_inst).start();
            new IOManager().start();
            if (GRAPHICS_ENABLED && SystemTray.isSupported()) {
                SystemTray tray = SystemTray.getSystemTray();
                try {
                    pop = new PopupMenu();
                    pop.setFont(new Font("System", Font.PLAIN, 10));
                    MenuItem jmi = new MenuItem("Exit");
                    jmi.addActionListener(_inst);
                    pop.add(jmi);
                    trayIcon = new TrayIcon(ImageIO.read(new File("data/icon.png")),
                            "Connection Library v1 ~ newbiehacker", pop);
                    tray.add(trayIcon);
                } catch (Exception e) {
                    trayIcon = null;
                }
            }
        }
    }

    public void run() {
        Thread.currentThread().setPriority(1);
        int lastSec = (int) (System.currentTimeMillis() / 1000);
        try {
            while (running) {
                if (EVENT_QUEUE.size() > 0) {
                    synchronized (EVENT_QUEUE) {
                        Event<? extends Object> e = EVENT_QUEUE.poll();
                        EventListener[] els = new EventListener[LISTENERS.size()];
                        els = LISTENERS.toArray(els);
                        for (EventListener el : els) {
                            try {
                                el.onEvent(e);
                            } catch (Throwable t) {
                                if (e instanceof UncaughtExceptionEvent) {
                                    t.printStackTrace();
                                    System.err.println("Uncaught [and unhandleable] exception!");
                                } else
                                    fireEvent(new UncaughtExceptionEvent(el, t));
                            }
                        }
                    }
                }
                if (GRAPHICS_ENABLED) {
                    if (((!visibleDialog & DIALOG_QUEUE.size() > 0) | (destroyDialog & DIALOG_QUEUE.size() > 1))) {
                        synchronized (DIALOG_QUEUE) {
                            InformationDialog oldDialog = null;
                            if (destroyDialog) {
                                oldDialog = DIALOG_QUEUE.poll();
                            }
                            InformationDialog id = DIALOG_QUEUE.peek();
                            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                            Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().
                                    getDefaultScreenDevice().getDefaultConfiguration());
                            int bOffset = 0;
                            int rOffset = 0;
                            if (insets != null) {
                                bOffset = insets.bottom;
                                rOffset = insets.right;
                            }
                            id.pack();
                            id.setLocation(d.width - id.getWidth() - rOffset, d.height - id.getHeight() - bOffset);
                            id.setVisible(true);
                            visibleDialog = true;
                            dialogGenTime = System.currentTimeMillis();
                            if (oldDialog != null) {
                                oldDialog.setVisible(false);
                                oldDialog.dispose();
                                destroyDialog = false;
                            }
                        }
                    } else if (visibleDialog && destroyDialog && DIALOG_QUEUE.size() == 1) {
                        InformationDialog id = DIALOG_QUEUE.poll();
                        id.setVisible(false);
                        id.dispose();
                        visibleDialog = false;
                        destroyDialog = false;
                    }
                    if (visibleDialog && System.currentTimeMillis() - dialogGenTime >= 3000) {
                        destroyDialog = true;
                    }
                }
                if ((int) (System.currentTimeMillis() / 1000) != lastSec) {
                    lastSec = (int) (System.currentTimeMillis() / 1000);

                    Timer[] ts = new Timer[TIMERS.size()];
                    synchronized (TIMERS) {
                        ts = TIMERS.toArray(ts);
                        for (Timer t : ts)
                            if (lastSec >= t.genTime + t.secs) {
                                fireEvent(new TimerEvent(t));
                                TIMERS.remove(t);
                                if (t.repeatCount != 0)
                                    registerTimer(new Timer(t.secs, t.repeatCount > 0 ? t.repeatCount - 1 : -1, t.timerID));
                            }
                    }
                }
            }
            Thread.sleep(SLEEP);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        if (trayIcon != null)
            SystemTray.getSystemTray().remove(trayIcon);
        _inst = null;
    }

    public void actionPerformed(ActionEvent evt) {
        String command = evt.getActionCommand();
        if (command.equals("Exit")) {
            if (trayIcon != null)
                SystemTray.getSystemTray().remove(trayIcon);
            stop();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof JWindow || e.getSource() instanceof JTextArea)
            destroyDialog = true;
    }

    static {
        CONNECTIONS = new ArrayList<Connection>();
        LISTENERS = new ArrayList<EventListener>();
        TIMERS = new ArrayList<Timer>();
        EVENT_QUEUE = new LinkedList<Event<? extends Object>>();
        DIALOG_QUEUE = new LinkedList<InformationDialog>();
        GRAPHICS_ENABLED = System.getProperty("ncon.graphics") == null || System.getProperty("ncon.graphics").equals("enabled");
        VERBOSE = System.getProperty("ncon.verbose") != null && System.getProperty("ncon.verbose").equals("enabled");
        String sleep = System.getProperty("ncon.thread.sleep");
        if(sleep == null || !sleep.matches("\\d+"))
            SLEEP = 100;
        else
            SLEEP = Long.parseLong(sleep);
        init();
    }
}