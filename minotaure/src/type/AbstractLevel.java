package type;

import java.util.ArrayList;

public abstract class AbstractLevel {
	
	private AbstractMap map;
	private ArrayList<LevelListener> listeners;
	
	


	public void setMap(AbstractMap map) {
		this.map= map;
	}
	public void addListener(LevelListener listener) {
		listeners.add(listener);
	}


}
