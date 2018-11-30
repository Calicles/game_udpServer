package contracts;

import java.awt.Graphics;

import model.Coordinates;
import model.Rectangle;

public interface Charac_withoutTransfert {
	
	Rectangle getPosition();
	
	void setCoordinates(Coordinates newPosition);
	void setImages(Coordinates newImagesNumbers);
	
	void draw(Graphics g,Coordinates screenPosition);

}
