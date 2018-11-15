package model;

public class Coordinates {
	
	protected int x, y;
	
	public Coordinates(int x, int y) {
		this.x= x;
		this.y= y;
	}

	public int getX() {return x;}
	public int getY() {return y;}
	
	public void setCoordinates(Coordinates newPosition) {
		this.x= newPosition.getX();
		this.y= newPosition.getY();
	}

}
