package edu.virginia.engine.events;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Laith on 2/17/17.
 */
public class EventDispatcher implements IEventDispatcher {

    private HashMap<String, ArrayList<IEventListener>> listeners;

    public EventDispatcher(){
        listeners = new HashMap<>();
    }

    public void addEventListener(IEventListener listener, String eventType){
        if(!this.hasEventListener(listener, eventType)){
            this.listeners.put(eventType, new ArrayList<>());
            this.listeners.get(eventType).add(listener);
        }
        else {
            this.listeners.get(eventType).add(listener);
        }
    }

    public void removeEventListener(IEventListener listener, String eventType){
        this.listeners.get(eventType).remove(listener);
    }

    public void dispatchEvent(Event event){

        for (IEventListener iel : this.listeners.get(event.getEventType())){
            iel.handleEvent(event);
        }
    }

    public boolean hasEventListener(IEventListener listener, String eventType){
        return this.listeners.containsKey(eventType);
    }

}
