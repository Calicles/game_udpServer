package type;

import java.awt.image.BufferedImage;

import model.Coordinates;
import model.Rectangle;

public abstract class AbstractCharacter {
	
	protected BufferedImage[] leftAnim, rightAnim, upAnim, downAnim;
	protected BufferedImage currentImage;
	protected Rectangle position;
	
	public Coordinates getCoordinates() {return position.getCoordinates();}
	public BufferedImage getImage() {return currentImage;}

}
