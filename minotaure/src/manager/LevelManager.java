package manager;

import model.Coordinates;
import network.Server;
import type.AbstractLevel;
import type.AbstractMap;
import type.Controller;
import type.LevelListener;
import type.NetworkListener;

public class LevelManager implements Controller, NetworkListener {
	
	private AbstractLevel level;
	
	
	public Coordinates getBossCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	public Coordinates getPlayerCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setMap(AbstractMap map) {
		level.setMap(map);
		
	}

	@Override
	public void createLevel(int levelType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerMovesLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerMovesRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerMovesUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerMovesDown() {
		// TODO Auto-generated method stub

	}

	public void addListener(LevelListener listener) {
		level.addListener(listener);
		
	}


}
