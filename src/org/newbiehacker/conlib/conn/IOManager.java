package org.newbiehacker.conlib.conn;

final class IOManager extends Thread {
    public void run() {
        try {
            Thread.currentThread().setPriority(1);
            while (GlobalManager.running) {
                synchronized (GlobalManager.CONNECTIONS) {
                    Connection[] connections = GlobalManager.CONNECTIONS.toArray(new Connection[GlobalManager.CONNECTIONS.size()]);
                    for (Connection c : connections) {
                        try {
                            c.handleIO();
                        } catch (Throwable t) {
                            GlobalManager.fireEvent(new UncaughtExceptionEvent(c, t));
                        }
                    }
                }
            }
            Thread.sleep(GlobalManager.SLEEP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}