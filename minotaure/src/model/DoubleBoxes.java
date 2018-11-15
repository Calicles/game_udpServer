package model;

import java.awt.Dimension;

/**
 * Le premier rectangle représente l'écran, le deuxième le cadre de scrolling.
 * @author antoine
 *
 */
public class DoubleBoxes {
	
	private Rectangle screen;
	private Rectangle scrollBox;
	
	public DoubleBoxes(Coordinates coordinates, Dimension screenSize, Rectangle scrollBox) {
		screen= new Rectangle(coordinates, screenSize.width, screenSize.height);
		this.scrollBox= scrollBox;
	}

	public Rectangle getScreenPosition() {return screen;}
	public Coordinates getScreenCoordinates() {return screen.getCoordinates();}

	/**
	 * Créer le scrolling en suivant les vecteurs du joueur.
	 * @param scrollingVector
	 */
	public void scroll(Coordinates scrollingVector) {
		screen.setCoordinates(scrollingVector);
		
		scrollBox.setCoordinates(scrollBox.getX() + scrollingVector.getX(), 
				scrollBox.getY() + scrollingVector.getY());
		
	}

	public boolean isPlayerOnScrollBox(Rectangle position) {
		//TODO
		return false;	
	}

}
