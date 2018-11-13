package type;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import model.Coordinates;
import model.DoubleBoxes;
import model.Map;
import model.Player;
import model.Player2;
import model.Rectangle;
import model.TransferEvent;

public abstract class AbstractLevel {
	
	protected AbstractMap map;
	protected Player player;
	protected Player2 player2;
	protected DoubleBoxes scrollBoxes;
	protected ArrayList<LevelListener> listeners;
	
	public AbstractLevel() {
		player= new Player();
		player2= new Player2();
		map= new Map();
	}

	/**
	 * initialisation de la représentation numérique de l'écran et du scrolling.
	 * @param screenSize
	 */
	public void setScreenSize(Dimension screenSize) {
		int scrollBoxX= screenSize.width / 4;
		int scrollBoxY= screenSize.height / 4;
		int width= scrollBoxX * 3;
		int height= scrollBoxY * 3;
		Rectangle rec= new Rectangle(new Coordinates(scrollBoxX, scrollBoxY), width, height);
		scrollBoxes= new DoubleBoxes(new Coordinates(0, 0), screenSize, rec);
	}
	
	public void drawLevel(Graphics g) {
		drawMap(g);
		drawPlayerOne(g);
		drawPlayerTwo(g);
	}

	private void drawPlayerTwo(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	private void drawPlayerOne(Graphics g) {
		Coordinates coor= player.getCoordinates();
		coor= translateToScreenPosition(coor);
		g.drawImage(player.getImage(), coor.getX(), coor.getY(), null);
		
	}

	private void drawMap(Graphics g) {
		map.drawMap(g);
	}

	public void addListener(LevelListener listener) {
		listeners.add(listener);
	}
	public Coordinates getPlayerCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}
	public Coordinates getBossCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}
	public void start() {
		// TODO Auto-generated method stub
		
	}
	public void update(TransferEvent te) {
		// TODO Auto-generated method stub
		
	}
	
	protected void fireUpdate() {
	}
	
	private Coordinates translateToScreenPosition(Coordinates characterPosition) {
		Coordinates result;
		
		return null;
	}


}
