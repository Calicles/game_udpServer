package launchers;


import manager.LevelManager;
import model.Map;
import type.AbstractMap;
import view.Frame;

public class MinotaureAppli {
	
	@SuppressWarnings("unused")
	public MinotaureAppli() {
		
		LevelManager levelManager;
		Frame frame;
		AbstractMap map= new Map();
		
		levelManager= new LevelManager();
		
		frame= new Frame(levelManager, map.getSize());
		
		levelManager.setMap(map);
		
	}

}
