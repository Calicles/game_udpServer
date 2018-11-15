package type;

import model.Coordinates;

public abstract class AbstractCharacter_without_transfer extends AbstractCharacter {


	public AbstractCharacter_without_transfer(String setUrl) {
		super(setUrl);
		// TODO Auto-generated constructor stub
	}

	public void setCoordinates(Coordinates newPosition) {
		position.setCoordinates(newPosition.getX(), newPosition.getY());
	}

}
