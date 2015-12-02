package org.newbiehacker.conlib.util;

/**
 * <p>The Timer class is a class which is registered with the GlobalManager via registerTimer, and a TimerEvent is then fired {@code secs} later</p>
 */
public final class Timer {
    private static int timerCount;

    /**
     * <p>The amount of time this timer will wait (in seconds) until expiring</p>
     */
    public final int secs;
    /**
     * <p>The time this timer was generated (used internally)</p>
     */
    public final int genTime;
    /**
     * <p>The amount of times this timer will repeat (any number less than 0 means infinite)
     */
    public final int repeatCount;
    /**
     * <p>The ID number of this timer<p>
     */
    public final int timerID;

    /**
     * <p>Constructs a new Timer with the specfied wait time, repeat count and ID</p>
     * @param secs The amount of seconds this timer should wait until expiring
     * @param repeatCount The amount of times this timer should fire (any number less than 0 is infinite)
     * @param timerID The ID of this timer
     */
    public Timer(int secs, int repeatCount, int timerID) {
        this.genTime = (int) (System.currentTimeMillis() / 1000);
        this.secs = secs;
        this.repeatCount = repeatCount;
        this.timerID = timerID;
    }

    /**
     * <p.Constructs a new Timer with the specified wait time and repeat count</p>
     * @param secs The amount of seconds this timer should wait before expiring
     * @param repeatCount The amount of times this timer should fire (any number less than 0 is infinite)
     */
    public Timer(int secs, int repeatCount) {
        this(secs, repeatCount, timerCount++);
    }

    public String toString() {
        return "Timer [id=" + timerID + ",time=" + secs + "s,repeat=" + repeatCount + "]";
    }
}