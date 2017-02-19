package edu.virginia.engine.events;

/**
 * Created by Laith on 2/17/17.
 */
public interface IEventListener {

    void handleEvent(Event event); //equivalent to notify in many textbooks
}
