package type;

import java.awt.image.BufferedImage;

import model.Rectangle;

public abstract class AbstractCharacter {
	
	private BufferedImage[] leftAnim, rightAnim, upAnim, downAnim;
	private Rectangle position;
	private AbstractTransfer transfer_strategy;

}
