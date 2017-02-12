package edu.virginia.engine.display;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Laith on 2/12/17.
 */
public class DisplayObjectContainer extends DisplayObject {
    private ArrayList<DisplayObjectContainer> children;


    public DisplayObjectContainer(String id) {
        super(id);
    }

    public DisplayObjectContainer(String id, String fileName) {
        super(id, fileName);
    }

    public void addChild(DisplayObjectContainer obj){
        this.children.add(obj);
    }

    public void addChildAtIndex(DisplayObjectContainer obj, int index){
        this.children.add(index, obj);
    }

    public void removeChildAtIndex(int index){
        this.children.remove(index);
    }

    public void removeAllChildren(){
        this.children.clear();;
    }

    public boolean contains(DisplayObject obj){
        return this.children.contains(obj);
    }

    public DisplayObject getChildByID(String id){
        for(DisplayObject d : this.children){
            if(d.getId().equals(this.getId())){
                return d;
            }
        }

        return null;
    }

    public DisplayObject getChildAtIndex(int index){
        if(index < this.children.size() - 1){
            return this.children.get(index);
        }
        else{
            return null;
        }
    }

    protected void update(ArrayList<String> pressedKeys) {


    }

    public void draw(Graphics g) {

    }

}
