package launchers;


import manager.LevelManager;
import model.Map;
import network.Server;
import type.AbstractMap;
import view.Frame;

public class MinotaureAppli {
	
	public MinotaureAppli() {
		
		LevelManager levelManager;
		Frame frame;
		Server server;
		AbstractMap map= new Map();
		
		levelManager= new LevelManager();
		levelManager.setMap(map);
		
		frame= new Frame(levelManager, map.getSize());
		
		server= new Server(levelManager.getPlayerCoordinates(), levelManager.getBossCoordinates());
		server.addListener(levelManager);
		
		levelManager.addListener(server);
		
	}

}
