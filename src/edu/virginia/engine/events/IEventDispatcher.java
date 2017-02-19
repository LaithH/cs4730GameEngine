package edu.virginia.engine.events;

/**
 * Created by Laith on 2/17/17.


 * IEventDispatcher: Equivalent to “observable”. Lists the methods necessary for any object that throws
 events. Contains the following methods:
 o addEventListener(IEventListener listener, String eventType)
 o removeEventListener(IEventListener listener, String eventType)
 o dispatchEvent(Event event)
 o hasEventListener(IEventListener listener, String eventType).

 */
public interface IEventDispatcher {

    void addEventListener(IEventListener listener, String eventType);
    void removeEventListener(IEventListener listener, String eventType);
    void dispatchEvent(Event event);
    boolean hasEventListener(IEventListener listener, String eventType);
}
