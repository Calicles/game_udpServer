package type;

import model.Coordinates;

public abstract class AbstractCharacter_without_transfer extends AbstractCharacter {
	
	
	
	public void setCoordinates(Coordinates newPosition) {
		position.setCoordinates(newPosition.getX(), newPosition.getY());
	}

}
