package org.newbiehacker.conlib.event;

/**
 * <p>The EventListener interface contains only one method onEvent(Event e) which is called when fireEvent is called in GlobalManager</p>
 * <p>Only interfaces who have registered with the GlobalManager via addEventListener will be notified of an Event</p>
 */
public interface EventListener {
    /**
     * <p>Notified the EventListener that an Event has been fired</p>
     * @param e The Event that was fired
     */
    void onEvent(Event e);
}