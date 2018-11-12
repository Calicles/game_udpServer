package type;

import java.util.ArrayList;

public abstract class AbstractLevel {
	
	private AbstractMap map;
	private ArrayList<LevelListener> listeners;
	
	
	public void addListener(LevelListener listener) {
		listeners.add(listener);
	}

}
