package edu.virginia.labTest;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.display.Game;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Laith on 2/12/17.
 */
public class SolarSystem extends Game {

    int offset = 5;
    double scaleOffset = 0.04;

    /* Create a sprite object for our solar system. We'll use the sun */
    Sprite game = new Sprite("Game", (DisplayObjectContainer) null);
    Sprite effects = new Sprite("Effects", game);
    Sprite sun = new Sprite("Sun", "sun.png", effects);
    Sprite eSun = new Sprite("EarthSun", sun);
    Sprite mSun = new Sprite("MarsSun", sun);
    Sprite earth = new Sprite("Earth", "earth.png", eSun);
    Sprite moon = new Sprite("Moon", "moon.png", earth);
    Sprite mars = new Sprite("Mars", "mars.png", mSun);

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public SolarSystem() {
        super("Solar System", 1100, 700);

        int gameWidth = 1100;
        int gameHeight = 700;

        game.addChild(effects);
        effects.addChild(sun);
        sun.addChild(eSun);
        sun.addChild(mSun);
        eSun.addChild(earth);
        mSun.addChild(mars);
        earth.addChild(moon);

        effects.setPositionX(gameWidth/2 - sun.getUnscaledWidth() / 2);
        effects.setPositionY(gameHeight/2 - sun.getUnscaledHeight() / 2);
        sun.setPivotPointX(sun.getUnscaledWidth()/2);
        sun.setPivotPointY(sun.getUnscaledHeight()/2);
        eSun.setPivotPointX(sun.getUnscaledWidth()/2);
        eSun.setPivotPointY(sun.getUnscaledHeight()/2);
        mSun.setPivotPointX(sun.getUnscaledWidth()/2);
        mSun.setPivotPointY(sun.getUnscaledHeight()/2);
        game.setPivotPointX(sun.getUnscaledWidth()/2);
        game.setPivotPointY(sun.getUnscaledHeight()/2);

        earth.setPositionX(150);
        mars.setPositionX(280);
        moon.setPositionX(50);
        earth.setPivotPointX(earth.getUnscaledWidth()/2 - 2);
        earth.setPivotPointY(earth.getUnscaledHeight()/2 - 2);
        moon.setPivotPointX(-32);
        moon.setPivotPointY(20);
        mars.setPivotPointX(mars.getUnscaledWidth()/2 - 2);
        mars.setPivotPointY(mars.getUnscaledHeight()/2 - 2);

    }

    public void update(ArrayList<String> pressedKeys){
        super.update(pressedKeys);

        if(effects != null && sun != null && earth != null && moon != null && mars != null &&
                mSun != null & eSun != null) {

            sun.setRotation(sun.getRotation() + 1);
            earth.setRotation(earth.getRotation() + 2);
            moon.setRotation(moon.getRotation() + 4);
            mSun.setRotation(mSun.getRotation() + 1);

//            if(sun.getRotation() % 360 == 0){
//                earth.setPositionX(earth.getPositionX() + 10);
//            }
//            else if(sun.getRotation() % 360 == 180){
//                earth.setPositionX(earth.getPositionX() - 10);
//            }

// sad attempt at ellipse
//            if(sun.getRotation() % 360 >= 0 && sun.getRotation() % 360 < 180){
//                earth.setPivotPointX( earth.getPivotPointX() - 1 );
//            }
//            else if(sun.getRotation() % 360 >= 180 && sun.getRotation() % 360 < 360){
//                earth.setPivotPointX( earth.getPivotPointX() + 1 );
//            }
//            else if(sun.getRotation() % 360 >= 90 && sun.getRotation() % 360 < 180){
//                eSun.setPivotPointX( (int)(eSun.getPivotPointX() + Math.cos(sun.getRotation())) );
//            }
//            else if(sun.getRotation() % 360 >= 180 && sun.getRotation() % 360 < 270){
//                eSun.setPivotPointX( (int)(eSun.getPivotPointX() - Math.cos(sun.getRotation())) );
//            }
//            earth.setPositionX(earth.getPositionX() + 1);
//            System.out.println(sun.getRotation());

            if (pressedKeys.contains("←")) {
                effects.setPositionX(effects.getPositionX() + offset);
            }
            if (pressedKeys.contains("↑")) {
                effects.setPositionY(effects.getPositionY() + offset);
            }
            if (pressedKeys.contains("→")) {
                effects.setPositionX(effects.getPositionX() - offset);
            }
            if (pressedKeys.contains("↓")) {
                effects.setPositionY(effects.getPositionY() - offset);
            }
            if (pressedKeys.contains("A")) {
                sun.setRotation(sun.getRotation() - 5);
            }
            if (pressedKeys.contains("S")) {
                sun.setRotation(sun.getRotation() + 5);
            }
            if (pressedKeys.contains("Q")) {
                effects.setScaleX(effects.getScaleX() - scaleOffset);
                effects.setScaleY(effects.getScaleY() - scaleOffset);
            }
            if (pressedKeys.contains("W")) {
                effects.setScaleX(effects.getScaleX() + scaleOffset);
                effects.setScaleY(effects.getScaleY() + scaleOffset);
            }

//        System.out.println("sun piv" + sun.getPivotPointX());
//        System.out.println("sun piv" + sun.getPivotPointY());
//        System.out.println("earth pos" + earth.getPositionX());
//        System.out.println("earth pos" + earth.getPositionX());
        }
    }

    public void draw(Graphics g){
        super.draw(g);

        if(game != null) {
            game.draw(g);
        }
    }

    public static void main(String[] args) {
        SolarSystem sys = new SolarSystem();
        sys.start();

    }
}
