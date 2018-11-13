package network;

import services.ServerFactory;
import type.AbstractServer;
import type.LevelListener;
import type.NetworkListener;

public class NetWork {
	
	private AbstractServer line;
	
	public LevelListener getLine() {return line;}
	
	public void initLine(int type) {
		line= ServerFactory.getServerInstance(type);
	}
	
	public void addListener(NetworkListener listener) {
		line.addListener(listener);
	}

	public void run() {
		line.run();
	}
	
	

}
