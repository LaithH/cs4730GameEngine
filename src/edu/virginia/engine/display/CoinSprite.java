package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

/**
 * Created by Laith on 2/18/17.
 */
public class CoinSprite extends Sprite implements IEventListener {

    public CoinSprite(String id){
        super(id);
        this.setImage("coin.png");
    }

    public void handleEvent(Event event){
        //System.out.println("I should be invisible");
        this.setVisible(false);
    }


}
