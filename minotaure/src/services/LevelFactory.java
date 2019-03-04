package services;

import java.awt.Dimension;

import model.LevelCli;
import model.LevelHost;
import type.AbstractLevel;

public class LevelFactory {
	
	public static AbstractLevel getLevelInstance(int type, Dimension screenSize) {
		if(type == 1)
			//return new LevelHost(screenSize);
		return new LevelCli(screenSize);
		return null;
	}

}
