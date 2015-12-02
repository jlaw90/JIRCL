package org.newbiehacker.conlib.util;

/**
 * <p>Misc is a utility class that contains halper methods that we did not deem viable to place anywhere else</p>
 * <p>Methods in this class do not have documentation unless they are considered useful for an implementation</p>
 */
public final class Misc {
    private Misc() {}

    public static String toHex(byte[] data, int off, int len) {
        StringBuffer sb = new StringBuffer();
        for(int i = off; i < off + len; i++)
            sb.append(Integer.toHexString(data[i] & 0xff)).append(i + 1 >= off + len? "": " ");
        return sb.toString();
    }

    public static String toHex(byte[] data) {
        return toHex(data, 0, data.length);
    }
}