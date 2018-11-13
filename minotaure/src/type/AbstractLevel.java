package type;

import java.util.ArrayList;

import model.Coordinates;
import model.TransferEvent;

public abstract class AbstractLevel {
	
	private AbstractMap map;
	protected ArrayList<LevelListener> listeners;
	
	


	public void setMap(AbstractMap map) {
		this.map= map;
	}
	public void addListener(LevelListener listener) {
		listeners.add(listener);
	}
	public Coordinates getPlayerCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}
	public Coordinates getBossCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}
	public void start() {
		// TODO Auto-generated method stub
		
	}
	public void update(TransferEvent te) {
		// TODO Auto-generated method stub
		
	}
	
	protected void fireUpdate() {
	}


}
