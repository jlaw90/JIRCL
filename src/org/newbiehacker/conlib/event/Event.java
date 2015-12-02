package org.newbiehacker.conlib.event;

/**
 * <p>Event is an abstract class that is used in the base library and can also be used by implementations</p>
 * <p>A subclass of Event MUST be created with a source object and call super(source); at the beginning of its constructor</p>
 * <p>If the source is null, a NullPointerException is thrown</p>
 * <p>The Event class uses generics so that a subclass can provide a strict source-type</p>
 */
public abstract class Event<T> {
    public final T source;

    public Event(T source) {
        if(source == null)
            throw new NullPointerException("source cannot be null");
        this.source = source;
    }
}