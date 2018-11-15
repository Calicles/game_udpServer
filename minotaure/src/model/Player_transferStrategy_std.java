package model;

import type.AbstractMap;
import type.AbstractTransfer;

public class Player_transferStrategy_std extends AbstractTransfer {

	public void released() {xVector= 0; yVector= 0;}
	public void movesLeft() {xVector= -4;}
	public void movesRight() {xVector= 4;}
	public void movesUp() {yVector= -4;}
	public void movesDown() {yVector= 4;}
	
	@Override
	public Coordinates memorizeMoves(Rectangle position, AbstractMap map) {
		Coordinates vectors= adaptVectors(position, map);
		return vectors;
	}

	
}
