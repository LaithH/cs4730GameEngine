package edu.virginia.engine.display;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Laith on 2/12/17.
 */
public class DisplayObjectContainer extends DisplayObject {
    private DisplayObjectContainer parent;
    private ArrayList<DisplayObjectContainer> children = new ArrayList<DisplayObjectContainer>();


    public DisplayObjectContainer(String id) {
        super(id);
    }


    public DisplayObjectContainer(String id, String fileName) {
        super(id, fileName);
    }

    public DisplayObjectContainer(String id, String fileName, DisplayObjectContainer parent) {
        super(id, fileName);
        this.parent = parent;
    }

    public DisplayObjectContainer(String id, DisplayObjectContainer parent) {
        super(id);
        this.parent = parent;
    }

    public void setParent(DisplayObjectContainer parent){
        this.parent = parent;
    }

    public DisplayObjectContainer getParent(){
        return this.parent;
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

    public int getPositionX() {
//        int posX = 0;
//        DisplayObjectContainer curr = this;
//
//        while(curr.parent != null){
//            posX += this.positionX;
//            curr = curr.parent;
//        }
//
//        return posX;
        return this.positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
//        int posY = 0;
//        DisplayObjectContainer curr = this;
//
//        while(curr.parent != null){
//            posY += this.positionY;
//            curr = curr.parent;
//        }
//
//        return posY;
        return this.positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPivotPointX() {
//        int pivX = 0;
//        DisplayObjectContainer curr = this;
//
//        while(curr.parent != null){
//            pivX += this.pivotPointX;
//            curr = curr.parent;
//        }
//
//        return pivX;
        return this.pivotPointX;
    }

    public void setPivotPointX(int pivotPointX) {
        this.pivotPointX = pivotPointX;
    }

    public int getPivotPointY() {
//        int pivY = 0;
//        DisplayObjectContainer curr = this;
//
//        while(curr.parent != null){
//            pivY += this.pivotPointY;
//            curr = curr.parent;
//        }
//
//        return pivY;
        return this.pivotPointY;
    }

    public void setPivotPointY(int pivotPointY) {
        this.pivotPointY = pivotPointY;
    }

    protected void update(ArrayList<String> pressedKeys) {
        super.update(pressedKeys);

    }

    public void draw(Graphics g) {
        super.draw(g); //Draw myself!

        Graphics2D g2d = (Graphics2D) g;
        applyTransformations(g2d); //Apply MY transformations to my children

        for(DisplayObject d : this.children){ //Draw each of my children
            d.draw(g2d);
        }

        reverseTransformations(g2d); //Reverse those transformations
    }

}
