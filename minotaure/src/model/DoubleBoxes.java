package model;

import java.awt.Dimension;

/**
 * Le premier rectangle représente l'écran, le deuxième le cadre de scrolling.
 * @author antoine
 *
 */
public class DoubleBoxes extends Rectangle {
	
	private Rectangle scrollBox;
	
	public DoubleBoxes(Coordinates coordinates, Dimension screenSize, Rectangle scrollBox) {
		super(coordinates, screenSize.width, screenSize.height);
		this.scrollBox= scrollBox;
	}

	public Coordinates getScreenCoordinates() {return super.getCoordinates();}

	/**
	 * Créer le scrolling en suivant les vecteurs du joueur.
	 * @param scrollingVector
	 */
	public void scroll(Coordinates scrollingVector) {
		super.setCoordinates(super.getX() + scrollingVector.getX(), 
				super.getY() + scrollingVector.getY());
		
		scrollBox.setCoordinates(scrollBox.getX() + scrollingVector.getX(), 
				scrollBox.getY() + scrollingVector.getY());
		
	}

}
