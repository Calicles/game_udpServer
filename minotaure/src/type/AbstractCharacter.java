package type;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import model.Coordinates;
import model.Rectangle;
import services.Coordinates_translator;

public abstract class AbstractCharacter {
	
	protected HashMap<Integer,BufferedImage[]> animation;  // leftAnim, rightAnim, upAnim, downAnim
	protected BufferedImage currentImage;
	protected Rectangle position;
	
	protected int direction, animIndex; //le second est un paramètre à envoyer via le server
	
	public AbstractCharacter() {
		
	}
	
	public int getDirection() {return direction;}
	public int getAnimIndex() {return animIndex;}
	public Coordinates getCoordinates() {return position.getCoordinates();}
	public BufferedImage getImage() {return currentImage;}
	
	public void draw(Graphics g, Coordinates screen) {
		Coordinates byScreen= Coordinates_translator.toScreenCoordinates(position.getCoordinates(), 
				screen);
		g.drawImage(currentImage, byScreen.getX(), byScreen.getY(), null);
	}
	

}
