package model;

/**
 * 
 * @author antoine
 *
 */
public class Rectangle extends Coordinates {
	
	private int width, height;
	
	public Rectangle(Coordinates coordinates, int width, int height) {
		super(coordinates.getX(), coordinates.getY());
		this.width= width;
		this.height= height;
	}

	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getEndX() {return x + width;}
	public int getEndY() {return y + height;}
	

	public Coordinates getCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCoordinates(int x, int y) {
		
		
	}


}
