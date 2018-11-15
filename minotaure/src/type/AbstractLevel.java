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
	
	public AbstractLevel(String playerUrl, String player2Url) {
		player= new Player(playerUrl);
		player2= new Player2(player2Url);
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
	
	public Coordinates getPlayerCoordinates() {return player.getCoordinates();}
	public void start() {} 
	abstract public void update(TransferEvent te);
	protected abstract void fireUpdate();
	public abstract Coordinates getBossCoordinates();

	public void drawLevel(Graphics g) {
		player.draw(g, scrollBoxes.getScreenCoordinates());
		player2.draw(g, scrollBoxes.getScreenCoordinates());
		map.drawMap(g);
	}
	
	public void scroll(Coordinates scrollingVector) {
		if(!isScreenOnBoard() && isPlayerOnScrollBox())
			scrollBoxes.scroll(scrollingVector);
	}

	
	private boolean isScreenOnBoard() {
		return map.isScreenOnBoard(scrollBoxes.getScreenPosition());
	}

	private boolean isPlayerOnScrollBox() {
		return scrollBoxes.isPlayerOnScrollBox(player.getPosition());
	}

	public void addListener(LevelListener listener) {
		listeners.add(listener);
	}


}
