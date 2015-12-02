package org.newbiehacker.conlib.conn;

/**
 * <p>An InputHandler gets passed all input received from a connection</p>
 */
public interface InputHandler {
    /**
     * <p>Tells this InputHandler to handle these bytes that we have just received</p>
     * @param data The byte array to handle
     */
    void handleInput(byte[] data);
}