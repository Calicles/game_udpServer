package launchers;


import java.awt.Dimension;

import manager.LevelManager;
import model.Map;
import type.AbstractMap;
import view.Frame;

public class MinotaureAppli {
	
	public MinotaureAppli() {
		
		Dimension screenSize= new Dimension(640, 640);
		LevelManager levelManager;
		Frame frame;
		
		levelManager= new LevelManager();
		
		frame= new Frame(levelManager, screenSize);

		while(!frame.getUserChoosed());
		
		levelManager.setScreenSize(screenSize);
		frame.loadScreen();

	}

}
