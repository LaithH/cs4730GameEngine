package edu.virginia.engine.events;

/**
 * Created by Laith on 2/18/17.
 */
public class QuestManager implements IEventListener{

    public QuestManager(){

    }

    public void handleEvent(Event event){
        System.out.println("coin was picked up, quest complete");
    }
}
