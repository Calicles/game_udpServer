package model;

import services.Pythagore;

/**
 * 
 * @author antoine
 *
 */
public class Rectangle extends Coordinates {
	
	protected int width, height;
	
	public Rectangle(Coordinates coordinates, int width, int height) {
		super(coordinates.getX(), coordinates.getY());
		this.width= width;
		this.height= height;
	}

	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getEndX() {return x + width;}
	public int getEndY() {return y + height;}
	public int getDiagonal() {return Pythagore.doCalcul(
			new Coordinates(x, y), new Coordinates (getEndX(), getEndY()));}

	public Coordinates getCoordinates() {
		return new Coordinates(x, y);
	}
	
	public static boolean isPartiallyInBox(Rectangle box, Rectangle toTest) {
		return Pythagore.doCalcul(box.getCoordinates(), toTest.getCoordinates()) < box.getDiagonal()
				&& Pythagore.doCalcul(new Coordinates(box.getEndX(), box.getEndY()), 
						new Coordinates(toTest.getEndX(), toTest.getEndY())) < box.getDiagonal();
	}
	
	public static boolean touch(Rectangle obj1, Rectangle obj2) {
		return Pythagore.doCalcul(obj1.getCoordinates(), obj2.getCoordinates()) <= obj2.getDiagonal()
				&& Pythagore.doCalcul(new Coordinates(obj1.getEndX(), obj1.getEndY()), 
						new Coordinates(obj2.getEndX(), obj2.getEndY())) <= obj2.getDiagonal();
	}
	
	public static boolean isTotallyInBox(Rectangle box, Rectangle toTest) {
		return false;
	}

	public static boolean isNext(Rectangle obj1, Rectangle obj2, int space) {
		return Pythagore.doCalcul(obj1.getCoordinates(), obj2.getCoordinates()) - obj2.getDiagonal() < space
				|| Pythagore.doCalcul(new Coordinates(obj1.getEndX(), obj1.getEndY()), 
						new Coordinates(obj2.getEndX(), obj2.getEndY())) - obj2.getDiagonal() < space;
	}

}
