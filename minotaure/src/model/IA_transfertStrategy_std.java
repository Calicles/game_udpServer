package model;

import type.AbstractMap;
import type.AbstractTransfer;

public class IA_transfertStrategy_std extends AbstractTransfer {
	
	@Override
	public void memorizeBossMoves(Rectangle position, Rectangle playerPosition, AbstractMap map) {
		//l'IA trouve une direction
		findVector(position, playerPosition, map);
		Coordinates vectors= adaptVectorsBySolidTiles(position, map);
		
		//Change les coordonnes du perso
		position.setCoordinates(position.getX() + vectors.getX(), position.getY() + vectors.getY());
	}

	private Coordinates findVector(Rectangle position, Rectangle playerPosition, AbstractMap map) {
		// TODO Auto-generated method stub
		return null;
	}


}
