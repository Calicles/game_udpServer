package manager;

import java.awt.Graphics;

import model.Coordinates;
import network.Server;
import services.LevelFactory;
import type.AbstractLevel;
import type.AbstractMap;
import type.Controller;
import type.LevelListener;
import type.NetworkListener;

public class LevelManager implements Controller, NetworkListener {
	
	private AbstractLevel level;
	private int levelType;
	
	
	public Coordinates getBossCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	public Coordinates getPlayerCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getLevelType() {return levelType;}

	public void setMap(AbstractMap map) {
		level.setMap(map);
		
	}

	@Override
	public void createLevel(int levelType) {
		this.levelType= levelType;
		this.level= LevelFactory.getLevelInstance(levelType);
	}
	

	@Override
	public void drawLevel(Graphics g) {
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
