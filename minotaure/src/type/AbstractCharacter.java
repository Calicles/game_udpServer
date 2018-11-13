package type;

import java.awt.image.BufferedImage;

import model.Coordinates;
import model.Rectangle;

public abstract class AbstractCharacter {
	
	protected BufferedImage[] leftAnim, rightAnim, upAnim, downAnim;
	protected Rectangle position;
	
	public void memorizeMoves() {
		
	}
	
	public Coordinates getCoordinates() {return position.getCoordinates();}

}
