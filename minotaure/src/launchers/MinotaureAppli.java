package launchers;


import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import manager.LevelManager;
import view.Frame;

public class MinotaureAppli {
	
	public MinotaureAppli() {
		
		//Get the Window Bounds
		GraphicsEnvironment graphics= GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle maxWindowBounds= graphics.getMaximumWindowBounds();
		
		Dimension screenSize= new Dimension(640, 640);
		LevelManager levelManager;
		Frame frame;
		
		levelManager= new LevelManager();
		
		frame= new Frame(levelManager, screenSize);

		while(!frame.getUserChoosed()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		frame.loadScreen();
	}

	

}
