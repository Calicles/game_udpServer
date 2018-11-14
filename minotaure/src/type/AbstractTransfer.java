package type;

import model.Coordinates;
import model.Rectangle;

public abstract class AbstractTransfer {
	
	protected int xVector, yVector;
	
	
	public abstract void movesLeft();
	public abstract void movesRight();
	public abstract void movesUp();
	public abstract void movesDown();
	public abstract void released();
	public abstract Coordinates memorizeMoves(Rectangle position, AbstractMap map);
	
	public AbstractTile isSolidTileOnRoad(AbstractMap map) {
	
		return null;
	}
	
}
