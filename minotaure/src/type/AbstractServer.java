package type;

import java.util.ArrayList;

public abstract class AbstractServer implements LevelListener{
	
	private ArrayList<NetworkListener> listeners;
	
	abstract public void run();
	
	public void addListener(NetworkListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(NetworkListener listener) {
		listeners.remove(listener);
	}

}
