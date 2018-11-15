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
	public Coordinates memorizePlayerMoves(Rectangle position, Rectangle player2Position, AbstractMap map) {
		Coordinates vectors= adaptVectorsBySolidTiles(position, map);
		if(isPlayerNext(position, player2Position))
			adaptVectorsByPlayer2(position, player2Position, vectors);
		return vectors;
	}
	
	private Coordinates adaptVectorsByPlayer2(Rectangle position, Rectangle player2Position, Coordinates vectors) {
		if(vectors.getX() != 0 || vectors.getY() != 0) {
			int x= vectors.getX(), y= vectors.getY();
			
			if(x < 0) {
				checkLeft(position, player2Position, vectors);
			}else if(x > 0) {
				checkRight(position, player2Position, vectors);
			}else if(y < 0) {
				checkUp(position, player2Position, vectors);
			}else {
				checkDown(position, player2Position, vectors);
			}
		}
		
		return null;
	}
	
	private void checkUp(Rectangle position, Rectangle player2Position, Coordinates vectors) {
		int dx= (int) Math.sqrt(Math.pow(position.getX() - player2Position.getX(), 2));
		int dy= position.getY() - player2Position.getEndY();
		
		if(dy < 4 && dx < position.getWidth()) {
			vectors.setCoordinates(new Coordinates(vectors.getX(), dy));
		}
	}
	private void checkDown(Rectangle position, Rectangle player2Position, Coordinates vectors) {
		int dx= (int) Math.sqrt(Math.pow(position.getX() - player2Position.getX(), 2));
		int dy= position.getEndY() - player2Position.getY();
		
		if(dy < 4 && dx < position.getWidth()) {
			vectors.setCoordinates(new Coordinates(vectors.getX(), dy));
		}
	}
	private void checkLeft(Rectangle position, Rectangle player2Position, Coordinates vectors) {
		int dX= position.getX() - player2Position.getEndX();
		int dY= (int) Math.sqrt(Math.pow(position.getY() - player2Position.getEndY(), 2));
		
		if(dX < 4 && dY < position.getHeight()) {
			vectors.setCoordinates(new Coordinates(dX, vectors.getY()));
		}
	}
	private void checkRight(Rectangle position, Rectangle player2Position, Coordinates vectors) {
		int dx= player2Position.getX() - position.getEndX();
		int dY= (int) Math.sqrt(Math.pow(position.getY() - player2Position.getEndY(), 2));
		
		if(dx < 4 && dY < position.getHeight()) {
			vectors.setCoordinates(new Coordinates(dx, vectors.getY()));
		}
	}
	
	private boolean isPlayerNext(Rectangle position, Rectangle player2Position) {
		//Pythagore
		double space= Math.sqrt( Math.pow( position.getX() - player2Position.getX(), 2) +
				Math.pow( position.getY() - player2Position.getY(), 2));
		
		return space < (position.getWidth() + 4);
	}

	
}
