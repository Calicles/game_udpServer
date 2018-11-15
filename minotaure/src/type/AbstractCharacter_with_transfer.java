package type;

import model.Coordinates;
import model.Rectangle;

public abstract class AbstractCharacter_with_transfer extends AbstractCharacter {
	
	protected AbstractTransfer transfer_strategy;
	private int tempo;
	
	public AbstractCharacter_with_transfer(String setUrl) {
		super(setUrl);
	}

	public Coordinates memorizePlayerMoves(Rectangle player2Position, AbstractMap map) {return null;}
	public void memorizeBossMoves(Rectangle player1Position, Rectangle player2Position, AbstractMap map) {}	

	protected void changeSprite(Coordinates vectors) {
		if(vectors.getX() != 0 || vectors.getY() != 0) {
			if(vectors.getX() != 0) {
				if(vectors.getX() < 0) {
					direction= 0;
				}else{
					direction= 1;
				}
			}else {
				if(vectors.getY() < 0) {
					direction= 2;
				}else {
					direction= 3;
				}
				updateIndex();
			}
			updateIndex();
			currentImage= animation.get(direction)[++animIndex];
		}
	}
	
	public void updateIndex() {
		tempo++;
		if(tempo % 4 == 0)
			animIndex++;
		if(animIndex >= animation.get(0).length)
			animIndex= 0;
		if(tempo >= 100)
			tempo= 0;
	}

	public void animationStoped() {
		animIndex= 0;
		currentImage= animation.get(direction)[animIndex];
	}
}
