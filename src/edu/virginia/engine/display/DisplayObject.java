package edu.virginia.engine.display;

import edu.virginia.engine.events.EventDispatcher;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject extends EventDispatcher {

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;

	//should be true iff this object is meant to be drawn
	private boolean visible;

	//x, y position where object will be drawn
	protected int positionX;
	protected int positionY;

	//Point relative to upper left corner that is the origin of the object
	protected int pivotPointX;
	protected int pivotPointY;

	//scale
	private double scaleX = 1.0;
	private double scaleY = 1.0;

	//degrees of rotation
	private double rotation;

	//transparency
	private double alpha = 1.0;
	private double prevAlpha;

	private DisplayObject parent;

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		this.setId(id);
		this.setScaleX(1.0);
		this.setScaleY(1.0);
		this.setAlpha(1.0);
		this.setRotation(0);
		this.setVisible(true);
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		this.setScaleX(1.0);
		this.setScaleY(1.0);
		this.setAlpha(1.0);
		this.setRotation(0);
		this.setVisible(true);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}


	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getPivotPointX() {
		return pivotPointX;
	}

	public void setPivotPointX(int pivotPointX) {
		this.pivotPointX = pivotPointX;
	}

	public int getPivotPointY() {
		return pivotPointY;
	}

	public void setPivotPointY(int pivotPointY) {
		this.pivotPointY = pivotPointY;
	}

	public double getScaleX() {
		return scaleX;
	}

	public void setScaleX(double scaleX) {
		if(this.getScaleX() < 0.1) {
			this.scaleX = 0.1;
		}
		else if (this.getScaleX() > 20) {
			this.scaleX = 20;
		}
		else{
			this.scaleX = scaleX;
		}
	}

	public double getScaleY() {
		return scaleY;
	}

	public void setScaleY(double scaleY) {
		if(this.getScaleY() < 0.1) {
			this.scaleY = 0.1;
		}
		else if (this.getScaleY() > 20) {
			this.scaleY = 20;
		}
		else{
			this.scaleY = scaleY;
		}
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		if(this.getAlpha() < 0.2) {
			this.alpha = 0.2;
		}
		else if (this.getAlpha() > 1) {
			this.alpha = 1;
		}
		else{
			this.alpha = alpha;
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<String> pressedKeys) {
		
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (displayImage != null && this.isVisible() == true) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(displayImage, 0, 0,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);

			//g2d.drawOval(this.getPivotPointX(), this.getPivotPointY(), 5, 5);
			
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		//translate canvas then draw mario then move canvas back
		g2d.translate(this.getPositionX(), this.getPositionY());
		g2d.scale(this.getScaleX(), this.getScaleY());

		AffineTransform t = new AffineTransform();
		t.rotate(Math.toRadians(this.getRotation()), getPivotPointX(), getPivotPointY());
		g2d.transform(t);

		prevAlpha = this.getAlpha();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) this.getAlpha()));
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) prevAlpha));
		AffineTransform t = new AffineTransform();
		t.rotate(Math.toRadians(-this.getRotation()), getPivotPointX(), getPivotPointY());
		g2d.transform(t);

		g2d.scale(1/this.getScaleX(), 1/this.getScaleY());
		g2d.translate(-this.getPositionX(), -this.getPositionY());
	}

}