package launchers;


import manager.LevelManager;
import model.Map;
import network.NetWork;
import network.Server;
import type.AbstractMap;
import view.Frame;

public class MinotaureAppli {
	
	public MinotaureAppli() {
		
		LevelManager levelManager;
		Frame frame;
		NetWork server;
		AbstractMap map= new Map();
		
		levelManager= new LevelManager();
		levelManager.setMap(map);
		
		frame= new Frame(levelManager, map.getSize());
		
		server= new NetWork();
		server.initLine(levelManager.getLevelType());
		server.addListener(levelManager);
		
		levelManager.addListener(server.getLine());
		
	}

}
