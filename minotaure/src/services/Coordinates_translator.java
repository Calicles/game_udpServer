package services;

import model.Coordinates;
import model.Rectangle;

public class Coordinates_translator {
	
	public static Coordinates toScreenCoordinates(Coordinates toTranslate, Coordinates screen) {
		int x, y;
		
		x= toTranslate.getX() - screen.getX();
		y= toTranslate.getY() - screen.getY();
		
		return new Coordinates(x, y);
	}

}
