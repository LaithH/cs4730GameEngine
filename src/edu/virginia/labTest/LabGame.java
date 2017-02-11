package edu.virginia.labTest;

/**
 * Created by Laith on 2/3/17.
 */

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.util.GameClock;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabGame extends Game {
    int marioHealth = 100;
    int offset = 8;
    GameClock clock = new GameClock();
    double gameTime = 62000; // = (30000 - clock.getElapsedTime()) / 1000;
    DecimalFormat formatter = new DecimalFormat("#0.0");
    boolean marioWins = false;
    boolean gameOver = false;
    int frameClock;


    /* Create a sprite object for our game. We'll use mario */
//    Sprite mario = new Sprite("Mario", "mario1.png");
    AnimatedSprite mario = new AnimatedSprite("Mario");
//    AnimatedSprite aMario = new AnimatedSprite("Mario");


    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabGame() {
        super("Lab Test Game", 700, 500);
        mario.setPositionX(290);
        mario.setPositionY(155);
        frameClock = 0;
    }


    @Override
    public void mouseClicked(MouseEvent e){
//        int negativeX = 1;
//        int negativeY = 1;
//
//        if(mario.getRotation() % 360 < 90){
//            negativeX = -1;
//            negativeY = 1;
//        }
//        else if(mario.getRotation() % 360 < 180){
//            negativeX = -1;
//            negativeY = -1;
//        }
//        else if(mario.getRotation() % 360 < 270){
//            negativeX = 1;
//            negativeY = -1;
//        }
//        else{
//            negativeX = 1;
//            negativeY = 1;
//        }

        if(e.getX() > /*(negativeX)**/(mario.getPositionX()) &&
                e.getX() < /*(negativeX)**/(mario.getPositionX() + 95 * mario.getScaleY()) &&
                e.getY() > /*(negativeY)**/(mario.getPositionY() + 20) &&
                e.getY() < /*(negativeY)**/(mario.getPositionY() + 153 * mario.getScaleY()) && marioHealth > 0)
        {
            if(mario.getScaleX() < 0.85){
                marioHealth -= 40;
            }
            else if(mario.getScaleX() > 1.4){
                marioHealth -= 10;
            }
            else{
                marioHealth -= 20;
            }


//			System.out.println(mario.getUnscaledWidth());
//          System.out.println(mario.getUnscaledHeight());
//			System.out.println(marioHealth);
        }
        if(marioHealth < 0){
            marioHealth = 0;
        }
//		System.out.println(xPos + " x mario y " + yPos);
//		System.out.println(e.getX() + " x click y " + e.getY() + "\n");
    }

    /**
     * Engine will automatically call this update method once per frame and pass to us
     * the set of keys (as strings) that are currently being pressed down
     * */
    @Override
    public void update(ArrayList<String> pressedKeys){
        super.update(pressedKeys);

		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
        if(mario != null) mario.update(pressedKeys);

        frameClock++;

        if(gameTime > 0 && marioHealth > 0) {

//            for(String s: pressedKeys){
//                System.out.print(s);
//            }
//            System.out.println("");

            //Update his position based on the keys pressed
            if (pressedKeys.contains("←")) {
                mario.setPositionX(mario.getPositionX() - offset);
                mario.walk();
                mario.setDelay(100);
            }
            if (pressedKeys.contains("↑")) {
                mario.setPositionY(mario.getPositionY() - offset);
                mario.jump();
                mario.setDelay(1);
            }
            if (pressedKeys.contains("→")) {
                mario.setPositionX(mario.getPositionX() + offset);
                mario.walk();
                mario.setDelay(100);
            }
            if (pressedKeys.contains("↓")) {
                mario.setPositionY(mario.getPositionY() + offset);
                mario.fall();
                mario.setDelay(1);
            }
            if(!pressedKeys.contains("←") && !pressedKeys.contains("↑") && !pressedKeys.contains("→")
                    && ! pressedKeys.contains("↓") && mario != null){
                mario.idle();
                mario.setDelay(1);
            }
            if (pressedKeys.contains("Q")) {
                mario.setRotation(mario.getRotation() - 5);
            }
            if (pressedKeys.contains("W")) {
                mario.setRotation(mario.getRotation() + 5);
            }
            if (pressedKeys.contains("A")) {
                mario.setScaleX(mario.getScaleX() - 0.01);
                mario.setScaleY(mario.getScaleY() - 0.01);
            }
            if (pressedKeys.contains("S")) {
                mario.setScaleX(mario.getScaleX() + 0.01);
                mario.setScaleY(mario.getScaleY() + 0.01);
            }
            if (pressedKeys.contains("Z") && mario.getAlpha() > 0.2) {
                mario.setAlpha(mario.getAlpha() - .02);
            }
            if (pressedKeys.contains("X") && mario.getAlpha() < 1.0) {
                mario.setAlpha(mario.getAlpha() + .02);
            }
            if (pressedKeys.contains("J")) {
                mario.setPivotPointX(mario.getPivotPointX() - offset/2);
//                mario.setPositionX(mario.getPositionX() + offset/2);
            }
            if (pressedKeys.contains("I")) {
                mario.setPivotPointY(mario.getPivotPointY() - offset/2);
//                mario.setPositionY(mario.getPositionY() + offset/2);
            }
            if (pressedKeys.contains("L")) {
                mario.setPivotPointX(mario.getPivotPointX() + offset/2);
//                mario.setPositionX(mario.getPositionX() - offset/2);
            }
            if (pressedKeys.contains("K")) {
                mario.setPivotPointY(mario.getPivotPointY() + offset/2);
//                mario.setPositionY(mario.getPositionY() - offset/2);
            }
            if (pressedKeys.contains("V") && frameClock > 10) {
                mario.setVisible(!mario.isVisible());
                frameClock = 0;
            }

            //if(mario != null) mario.update();

            //check if he is out of bounds
            if (mario != null && mario.getPositionX() < -5) {
                mario.setPositionX(mario.getPositionX() + offset);
            }
            if (mario != null && mario.getPositionY() < 30) {
                mario.setPositionY(mario.getPositionY() + offset);
            }
            if (mario != null && mario.getPositionX() > 634) {
                mario.setPositionX(mario.getPositionX() - offset);
            }
            if (mario != null && mario.getPositionY() > 347) {
                mario.setPositionY(mario.getPositionY() - offset);
            }

            gameTime = (62000 - clock.getElapsedTime()) / 1000;

            if(mario != null && !mario.isVisible()){
                marioHealth -= 1;
            }

        }
        else{
            //end the game
            gameOver = true;

            //check to see who won
            if(marioHealth > 0){
                marioWins = true;
            }

            //to fix time showing up as -0.0 after game
            if(gameTime <= 0) {
                gameTime = 0;
            }

            //press enter to win the game
            if(pressedKeys.contains("⏎")) {
                System.exit(0);
            }
        }

    }

    /**
     * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
     * the screen, we need to make sure to override this method and call mario's draw method.
     * */
    @Override
    public void draw(Graphics g){
        //
        super.draw(g);
        //show health and time
        g.setColor(Color.RED);
        g.setFont(new Font("ComicSans", Font.BOLD, 24));
        g.drawString("Health: " + marioHealth, 15, 25);
        g.drawString("Time: " + formatter.format(gameTime), 560, 25);

        if(mario != null) {
//            //translate canvas then draw mario then move canvas back
//            g.translate(mario.getPositionX(), mario.getPositionY());
//
            /* Same, just check for null in case a frame gets thrown in before Mario is initialized */
            if (mario != null && gameTime > 0 && marioHealth > 0){
                mario.draw(g);
//                g.drawImage(mario.getImage(), mario.getPositionX(), mario.getPositionY(),null);
            }

//
//            //move it back now
//            g.translate(-mario.getPositionX(), -mario.getPositionY());
        }

        //game over messages
        if(gameOver){
            g.setFont(new Font("Arial", Font.BOLD, 36));
            if(marioWins){
                g.drawString("Mario Wins!", 250, 200);
                g.drawString("(Press Enter to Quit)", 180, 250);
            }
            else{
                g.drawString("Clicker Wins!", 250, 200);
                g.drawString("(Press Enter to Quit)", 180, 250);
            }

        }

    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabGame game = new LabGame();
        game.start();

    }
}

