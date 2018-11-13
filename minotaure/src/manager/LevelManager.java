package manager;

import java.awt.Graphics;

import model.Coordinates;
import model.NetEvent;
import model.TransferEvent;
import network.NetWork;
import services.LevelFactory;
import type.AbstractLevel;
import type.AbstractMap;
import type.Controller;
import type.LevelListener;
import type.NetworkListener;

public class LevelManager implements Controller, NetworkListener {
	
	private AbstractLevel level;
	private NetWork server;
	private int levelType;
	
	public LevelManager() {
		level= null;
		server= null;
		levelType= 0;
	}
	
	
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
		this.server= new NetWork();
		server.initLine(levelType);
		server.addListener(this);
		this.addListener(server.getLine());
		server.run(level.getPlayerCoordinates(), level.getBossCoordinates());
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
	
	@Override
	public void addNetWorkListener(NetworkListener listener) {
		server.addListener(listener);
	}


	@Override
	public void updateState(NetEvent ne) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(TransferEvent te) {
		// TODO Auto-generated method stub
		
	}


}
