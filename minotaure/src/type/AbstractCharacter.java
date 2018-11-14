package type;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import model.Coordinates;
import model.Rectangle;
import services.Coordinates_translator;

public abstract class AbstractCharacter {
	
	protected BufferedImage[] leftAnim, rightAnim, upAnim, downAnim;
	protected BufferedImage currentImage;
	protected Rectangle position;
	
	public Coordinates getCoordinates() {return position.getCoordinates();}
	public BufferedImage getImage() {return currentImage;}
	
	public void draw(Graphics g, Coordinates screen) {
		Coordinates byScreen= Coordinates_translator.toScreenCoordinates(position.getCoordinates(), 
				screen);
		g.drawImage(currentImage, byScreen.getX(), byScreen.getY(), null);
	}

}
