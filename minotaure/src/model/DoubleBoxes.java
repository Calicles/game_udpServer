package model;

import java.awt.Dimension;

public class DoubleBoxes extends Rectangle {
	
	private Rectangle scrollBox;
	
	public DoubleBoxes(Coordinates coordinates, Dimension screenSize, Rectangle scrollBox) {
		super(coordinates, screenSize.width, screenSize.height);
		this.scrollBox= scrollBox;
	}

}
