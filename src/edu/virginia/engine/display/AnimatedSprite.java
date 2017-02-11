package edu.virginia.engine.display;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.nio.BufferOverflowException;
import java.rmi.server.ExportException;
import java.util.ArrayList;

/**
 * Created by Laith on 2/5/17.
 */
public class AnimatedSprite extends Sprite{
    private BufferedImage[] frames;
    private BufferedImage[] idle;
    private BufferedImage[] jump;
    private BufferedImage[] walk;
    private BufferedImage[] fall;
    private int currentFrame;

    private long startTime;
    private long delay;


    public AnimatedSprite(String id) {
        super(id);
        idle = new BufferedImage[1];
        jump = new BufferedImage[1];
        walk = new BufferedImage[2];
        fall = new BufferedImage[1];

        try {
            idle[0] = ImageIO.read(new File("resources/mario1.png"));
            jump[0] = ImageIO.read(new File("resources/mario4.png"));
            walk[0] = ImageIO.read(new File("resources/mario2.png"));
            walk[1] = ImageIO.read(new File("resources/mario3.png"));
            fall[0] = ImageIO.read(new File("resources/mario5.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        this.idle();

    }

    public void setFrames(BufferedImage[] images){
        frames = images;
        if(currentFrame >= frames.length){
            currentFrame = 0;
        }
    }

    public void idle(){
        this.setFrames(idle);
    }

    public void jump(){
        this.setFrames(jump);
    }


    public void walk(){
        this.setFrames(walk);
    }
    public void fall(){
        this.setFrames(fall);
    }

    public void setDelay(long d){
        delay = d;
    }

    public void update(ArrayList<String> pressedKeys){
        if(delay == -1){
            return;
        }

        long elapsedTime = (System.nanoTime() - startTime) / 1000000;

        if(elapsedTime > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }

        if(currentFrame == frames.length){
            currentFrame = 0;
        }

        super.setImage(getImage());

    }

    public BufferedImage getImage(){
        return frames[currentFrame];
    }



}
