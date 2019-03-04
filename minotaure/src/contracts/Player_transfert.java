package contracts;

import model.Coordinates;
import model.Rectangle;
import type.AbstractMap;

public interface Player_transfert {

	void movesLeft();
	void movesRight();
	void movesUp();
	void movesDown();
	void released();

	Coordinates memorizePlayerMoves(Rectangle position, Rectangle player2Position, AbstractMap map);

}
