package edu.virginia.labTest;

/**
 * Created by Laith on 2/3/17.
 */

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabTwoGame extends Game {
    int xPos = 290;
    int yPos = 155;
    int marioHealth = 100;
    int offset = 10;
    GameClock clock = new GameClock();
    double gameTime = 32000; // = (30000 - clock.getElapsedTime()) / 1000;
    DecimalFormat formatter = new DecimalFormat("#0.0");
    boolean marioWins = false;
    boolean gameOver = false;

    /* Create a sprite object for our game. We'll use mario */
    Sprite mario = new Sprite("Mario", "Mario.png");

    /**
     * Constructor. See constructor in Game.java for details on the parameters given
     * */
    public LabTwoGame() {
        super("Lab One Test Game", 700, 500);
    }


    @Override
    public void mouseClicked(MouseEvent e){
        if(e.getX() > (xPos + 20) && e.getX() < (xPos + 109) &&
                e.getY() > (yPos + 20) && e.getY() < (yPos + 153) && marioHealth > 0){
            marioHealth -= 25;
//			System.out.println(mario.getUnscaledWidth());
//			System.out.println(marioHealth);
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

        //check chars entered
//		for (String s: pressedKeys
//			 ) {
//			System.out.print(s);
//		}
//		System.out.println("");
//		System.out.println(mario.getUnscaledHeight() + " " + mario.getUnscaledWidth());


        if(gameTime > 0 && marioHealth > 0) {
            //Update his position based on the keys pressed
            if (pressedKeys.contains("←")) {
                xPos -= offset;
            }
            if (pressedKeys.contains("↑")) {
                yPos -= offset;
            }
            if (pressedKeys.contains("→")) {
                xPos += offset;
            }
            if (pressedKeys.contains("↓")) {
                yPos += offset;
            }

            //check if he is out of bounds
            if (xPos < -30) {
                xPos += offset;
            }
            if (yPos < 30) {
                yPos += offset;
            }
            if (xPos > 600) {
                xPos -= offset;
            }
            if (yPos > 350) {
                yPos -= offset;
            }

            gameTime = (32000 - clock.getElapsedTime()) / 1000;
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
        super.draw(g);

        //translate canvas then draw mario then move canvas back
        g.translate(xPos, yPos);

		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
        if(mario != null && gameTime > 0 && marioHealth > 0) mario.draw(g);

        //move it back now
        g.translate(-xPos, -yPos);

        //show health and time
        g.setColor(Color.RED);
        g.setFont(new Font("ComicSans", Font.BOLD, 24));
        g.drawString("Health: " + marioHealth, 15, 25);
        g.drawString("Time: " + formatter.format(gameTime), 560, 25);

        //game over messages
        if(gameOver){
            g.setFont(new Font("ComicSans", Font.BOLD, 36));
            if(marioWins){
                g.drawString("Mario Wins!", 250, 200);
                g.drawString("(Press Enter to Quit)", 165, 250);
            }
            else{
                g.drawString("Clicker Wins!", 250, 200);
                g.drawString("(Press Enter to Quit)", 165, 250);
            }

        }

    }

    /**
     * Quick main class that simply creates an instance of our game and starts the timer
     * that calls update() and draw() every frame
     * */
    public static void main(String[] args) {
        LabTwoGame game = new LabTwoGame();
        game.start();

    }
}

