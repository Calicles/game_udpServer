package contracts;

import java.awt.Graphics;

import model.Coordinates;
import model.Rectangle;
import type.AbstractMap;

public interface Charac_withTransfert {
	
	Coordinates getCoordinates();
	Coordinates getImages();
	Rectangle getPosition();
	int getX();
	int getY();
	int getEndX();
	int getEndY();
	
	void setAttributes(Rectangle position, AbstractMap map);
	
	void movesReleased();
	void movesLeft();
	void movesRight();
	void movesUp();
	void movesDown();
	
	Coordinates memorizePlayerMoves(Rectangle coor, AbstractMap map);
	void memorizeBossMoves();
	void think(Coordinates player, Coordinates player2);
	
	void draw(Graphics g, Coordinates screenPosition);

}
