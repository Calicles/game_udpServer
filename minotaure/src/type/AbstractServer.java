package type;

import java.util.ArrayList;

import model.Coordinates;

public abstract class AbstractServer implements LevelListener{
	
	private ArrayList<NetworkListener> listeners;
	
	public void run(Coordinates playerPosition, Coordinates bossPosition) {}
	
	public void addListener(NetworkListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(NetworkListener listener) {
		listeners.remove(listener);
	}

}
