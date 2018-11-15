package type;

import java.awt.Color;
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
	
	public AbstractLevel(String playerUrl, String player2Url, Dimension screenSize) {
		player= new Player(playerUrl);
		player2= new Player2(player2Url);
		map= new Map();
		listeners= new ArrayList<>();
		setScreenSize(screenSize);
	}

	/**
	 * initialisation de la représentation numérique de l'écran et du scrolling.
	 * @param screenSize
	 */
	private void setScreenSize(Dimension screenSize) {
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
	public void released() {player.movesReleased();}
	public void playerMovesLeft() {player.movesLeft();}
	public void playerMovesRight() {player.movesRight();}
	public void playerMovesUp() {player.movesUp();}
	public void playerMovesDown() {player.movesDown();}

	public void drawLevel(Graphics g) {
		Color old= g.getColor();
		g.setColor(Color.BLACK);
		g.drawString("Arrivé", 0, 0);
		g.setColor(old);
		map.drawMap(g, scrollBoxes.getScreenPosition());
		player.draw(g, scrollBoxes.getScreenCoordinates());
		player2.drawIfInScreen(g, scrollBoxes.getScreenPosition());
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
