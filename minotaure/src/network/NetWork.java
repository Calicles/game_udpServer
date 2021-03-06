package network;

import model.Coordinates;
import services.ServerFactory;
import type.AbstractServer;
import type.LevelListener;
import type.NetworkListener;

public class NetWork {
	
	private AbstractServer line;
	
	public NetWork() {
		line= null;
	}
	
	public LevelListener getLine() {return line;}
	
	public void initLine(String type) {
		line= ServerFactory.getServerInstance(type);
	}
	
	public void addListener(NetworkListener listener) {
		line.addListener(listener);
	}

	public void run(Coordinates playerPosition, Coordinates playerImages,
					Coordinates bossPosition, Coordinates bossImages) {
		line.run(playerPosition, playerImages, bossPosition, bossImages);
	}
	
	

}
