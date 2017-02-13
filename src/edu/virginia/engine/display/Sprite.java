package edu.virginia.engine.display;

import java.util.ArrayList;

/**
 * Nothing in this class (yet) because there is nothing specific to a Sprite yet that a DisplayObject
 * doesn't already do. Leaving it here for convenience later. you will see!
 * */
public class Sprite extends DisplayObjectContainer {

	public Sprite(String id) {
		super(id);
	}

	public Sprite(String id, String imageFileName) {
		super(id, imageFileName);
	}

	public Sprite(String id, String fileName, DisplayObjectContainer parent) {
		super(id, fileName, parent);
	}

	public Sprite(String id, DisplayObjectContainer parent) {
		super(id, parent);
	}
//
//	public Sprite(String id, DisplayObjectContainer parent, int posX, int posY) {
//		super(id, parent);
//		this.positionX = posX;
//		this.positionY = posY;
//	}


	@Override
	public void update(ArrayList<String> pressedKeys) { super.update(pressedKeys); }



}