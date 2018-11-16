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
					direction= 1;
				}else{
					direction= 2;
				}
			}else {
				if(vectors.getY() < 0) {
					direction= 3;
				}else {
					direction= 0;
				}
			}
			currentImage= animation.get(direction)[animIndex];
			updateIndex();
		}
	}
	
	public void updateIndex() {
		tempo++;
		if(tempo % 4 == 0) {
			animIndex++;
			if(tempo >= 100)
				tempo= 0;
		}
		if(animIndex >= animation.get(0).length)
			animIndex= 0;
	}

	public void animationStoped() {
		currentImage= animation.get(direction)[1];
		animIndex= 0;
	}
}
