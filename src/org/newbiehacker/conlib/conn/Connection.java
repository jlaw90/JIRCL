package org.newbiehacker.conlib.conn;

import org.newbiehacker.conlib.util.Misc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>This is the base class for all protocol implementations</p>
 * <p>It has methods for reading and allows adding {@code InputHandler}s to handle the input</p>
 */
public abstract class Connection {
    // Todo: implement DataOutput interface
    private final Socket socket;
    private InputStream in;
    private OutputStream out;
    private byte[] outBuffer;
    private int outLen;
    private final ArrayList<InputHandler> inputHandlers;

    /**
     * <p>Initializes a new {@code Connection} with the specified {@code Socket} to connect to</p>
     * @param socket the socket we wish to connect to
     * @throws IOException if we cannot connect to the socket
     */
    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        GlobalManager.fireEvent(new NewConnectionEvent(this));
        inputHandlers = new ArrayList<InputHandler>();
        outBuffer = new byte[5000];
        GlobalManager.registerConnection(this);
    }

    /**
     * <p>Writes a single byte to the outputstream</p>
     * @param i The byte to write
     */
    public final void write(int i) {
        write(new byte[]{(byte) i});
    }

    /**
     * <p>Writes a byte array to the outputstream</p>
     * @param data The byte array
     * @param off The offset to begin writing from
     * @param len The amount of bytes to write
     */
    public final void write(byte[] data, int off, int len) {
        if(outLen + len >= outBuffer.length)
            outBuffer = Arrays.copyOf(outBuffer, Math.max(outBuffer.length << 1, outLen + len));
        for (int i = off; i < off + len; i++)
            outBuffer[outLen++] = data[i];
        GlobalManager.verboseln("OUT: " + Misc.toHex(data, off, len));
    }

    /**
     * <p>Writes a byte array to the buffer</p>
     * @param data The byte array to write
     */
    public final void write(byte[] data) {
        write(data, 0, data.length);
    }

    /**
     * <p>Writes a line to the outputstream</p>
     * @param line The line to write
     */
    public final void writeLine(String line) {
        byte[] sData = (line.concat("\r\n")).getBytes();
        write(sData);
    }

    /**
     * <p>Handles all input and output for this connection</p>
     * <p>Input is passed on to all InputHandlers associated with this connection and output is written</p>
     */
    public final void handleIO() {
        try {
            if (in.available() > 0) {
                byte[] data = new byte[in.available()];
                int len = in.read(data);
                byte[] fin = new byte[len];
                System.arraycopy(data, 0, fin, 0, len);
                synchronized (inputHandlers) {
                    InputHandler[] handlers = new InputHandler[inputHandlers.size()];
                    handlers = inputHandlers.toArray(handlers);
                    for (InputHandler ih : handlers)
                        ih.handleInput(fin);
                }
                GlobalManager.verboseln("IN: " + Misc.toHex(data, 0, len));
            } else if (in.available() < 0)
                close();
            if (outLen > 0) {
                int len = 512;
                if (len > outLen)
                    len = outLen;
                byte[] data = new byte[len];
                System.arraycopy(outBuffer, 0, data, 0, len);
                System.arraycopy(outBuffer, len, outBuffer, 0, outLen - len);
                outLen -= len;
                out.write(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            close();
        }
    }

    /**
     * <p>Adds an InputHandler to this connection</p>
     * @param ih The InputHandler to add
     */
    public final void addInputHandler(InputHandler ih) {
        inputHandlers.add(ih);
    }

    /**
     * <p>Removes an InputHandler from this connection</p>
     * @param ih The InputHandler to remove
     */
    public final void removeInputHandler(InputHandler ih) {
        inputHandlers.remove(ih);
    }

    /**
     * <p>Closes this connection</p>
     */
    public void close() {
        try {
            inputHandlers.clear();
            GlobalManager.unregisterConnection(this);
            GlobalManager.fireEvent(new ConnectionClosedEvent(this));
            in.close();
            out.close();
            socket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String toString() {
        return getClass().getName() + "[address=" + socket.getInetAddress().getHostName() + ":" + socket.getPort() + "]";
    }
}