package model;

import type.AbstractMap;
import type.AbstractTransfer;

public class IA_transfertStrategy_std extends AbstractTransfer {
	

	@Override
	public void memorizeMoves(Rectangle position, Rectangle playerPosition, AbstractMap map) {
		//l'IA trouve une direction
		findVector();
		Coordinates vectors= adaptVectors(position, map);
		
		//Change les coordonnes du perso
		position.setCoordinates(position.getX() + vectors.getX(), position.getY() + vectors.getY());
	}

	private void findVector() {
		// TODO Auto-generated method stub
		
	}

}
