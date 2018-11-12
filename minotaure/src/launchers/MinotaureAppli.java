package launchers;


import manager.LevelManager;
import network.Server;
import view.Frame;

public class MinotaureAppli {
	
	public MinotaureAppli() {
		
		LevelManager levelManager;
		Frame frame;
		Server server;
		
		levelManager= new LevelManager();
		frame= new Frame(levelManager);
		server= new Server(levelManager.getPlayerCoordinates(), levelManager.getBossCoordinates());
		server.addListener(levelManager);
		levelManager.addListener(server);
		
	}

}
