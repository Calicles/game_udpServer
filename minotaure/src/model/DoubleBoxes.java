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
	
	public DoubleBoxes(Coordinates coordinates, Dimension screenSize) {
		screen= new Rectangle(coordinates, screenSize.width, screenSize.height);
		Coordinates scroll= new Coordinates(screenSize.width / 3, screenSize.height / 3);
		this.scrollBox= new Rectangle(scroll, screen.width / 3, screen.height /3);
	}

	public Rectangle getScreenPosition() {return screen;}
	public Rectangle getScrollPosition() {return scrollBox;}
	
	public Coordinates getScrollCoordinates() {return scrollBox.getCoordinates();}
	public Coordinates getScreenCoordinates() {return screen.getCoordinates();}
	
	public int getScreenX() {return screen.getX();}
	public int getScreenEndX() {return screen.getEndX();}
	public int getScreenY() {return screen.getY();}
	public int getScreenEndY() {return screen.getEndY();}
	
	public int getScrollX() {return scrollBox.getX();}
	public int getScrollEndX() {return scrollBox.getEndX();}
	public int getScrollY() {return scrollBox.getY();}
	public int getScrollEndy() {return scrollBox.getEndY();}
	public int getScrollWidth() {return scrollBox.getWidth();}
	public int getScrollHeight() {return scrollBox.getHeight();}

	/**
	 * Créer le scrolling en suivant les vecteurs du joueur.
	 * @param scrollingVector
	 */
	public void scroll(Coordinates scrollingVector) {
		screen.setCoordinates(screen.getX() + scrollingVector.getX(),
				screen.getY()+ scrollingVector.getY());
		
		scrollBox.setCoordinates(scrollBox.getX() + scrollingVector.getX(), 
				scrollBox.getY() + scrollingVector.getY());
		
	}

}
