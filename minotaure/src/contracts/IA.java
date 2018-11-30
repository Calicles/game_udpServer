package contracts;

import model.Coordinates;
import model.Rectangle;
import type.AbstractMap;

public interface IA {

	void setAttributes(Rectangle position, Rectangle player1, AbstractMap map);

	Coordinates memorizeBossMoves();

	void think(Rectangle position, Coordinates player1, Coordinates player2);

}
