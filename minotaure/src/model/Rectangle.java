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
		return Pythagore.doCalcul(obj1.getCoordinates(), obj2.getCoordinates()) < space;
	}
	
	public static boolean isARight(Rectangle position, Rectangle toTest) {
		return (isYAlign(position, toTest) && toTest.getX() > position.getEndX());
	}
	
	public static boolean isALeft(Rectangle position, Rectangle toTest) {
		return (isYAlign(position, toTest) && toTest.getEndX() < position.getX());
	}
	
	public static boolean isUp(Rectangle position, Rectangle toTest) {
		return (isXAlign(position, toTest) && toTest.getEndY() < position.getY());
	}
	
	public static boolean isDown(Rectangle position, Rectangle toTest) {
		return (isXAlign(position, toTest) && position.getEndY() < toTest.getY());
	}
	public static boolean isYAlign(Rectangle position, Rectangle toTest) {
		return (toTest.getY() >= position.getY() && toTest.getY() <= position.getEndY()) ||
				(toTest.getEndY() >= position.getY() && toTest.getEndY() <= position.getEndY());
	}
	
	public static boolean isXAlign(Rectangle position, Rectangle toTest) {
		return (toTest.getX() > position.getX() && toTest.getX() < position.getEndX()) ||
					(toTest.getEndX() > position.getX() && toTest.getEndX() < position.getEndX());
	}

}
