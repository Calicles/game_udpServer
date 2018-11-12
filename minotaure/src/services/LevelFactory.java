package services;

import model.LevelHost;
import type.AbstractLevel;

public class LevelFactory {
	
	public static AbstractLevel getLevelInstance(int type) {
		if(type == 1)
			return new LevelHost();
		return null;
	}

}
