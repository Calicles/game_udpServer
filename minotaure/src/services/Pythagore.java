package services;

import model.Coordinates;

public class Pythagore {
	
	public static int doCalcul(Coordinates position1, Coordinates position2) {
		int dx= (int) Math.pow(position1.getX() - position2.getX(), 2);
		int dy= (int) Math.pow(position1.getY() - position2.getY(), 2);
		return (int) Math.sqrt(dx + dy);
	}

}
