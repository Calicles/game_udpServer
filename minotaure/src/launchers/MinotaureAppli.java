package launchers;


import java.awt.Dimension;

import manager.LevelManager;
import model.Map;
import type.AbstractMap;
import view.Frame;

public class MinotaureAppli {
	
	public MinotaureAppli() {
		
		LevelManager levelManager;
		Frame frame;
		AbstractMap map= new Map();
		
		levelManager= new LevelManager();
		
		frame= new Frame(levelManager, map.getSize());

		while(!frame.getUserChoosed());
		
		frame.loadScreen();
		
		levelManager.setMap(map);
		
	}

}
