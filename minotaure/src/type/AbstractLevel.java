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
		scrollBoxes= new DoubleBoxes(new Coordinates(0, 0), screenSize);
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


		map.drawMap(g, scrollBoxes.getScreenPosition());
		player.draw(g, scrollBoxes.getScreenCoordinates());
		player2.drawIfInScreen(g, scrollBoxes.getScreenPosition());
		
		g.setColor(Color.BLACK);
		g.drawRect(scrollBoxes.getScrollX(), scrollBoxes.getScrollY(), scrollBoxes.getScrollWidth(), scrollBoxes.getScrollHeight());
		g.setColor(old);
	}
	
	public void scroll(Coordinates scrollingVector) {
		if(!scrollingVector.isZero()) {
			if(scrollingVector.getX() < 0 && isPlayerOnScrollLeft()) {
				checkScrollLeft(scrollingVector);
				scrollBoxes.scroll(scrollingVector);
			}else if(scrollingVector.getX() > 0 && isPlayerOnScrollRight()) {
				checkScrollRight(scrollingVector);
				scrollBoxes.scroll(scrollingVector);
			}else if(scrollingVector.getY() < 0 && isPlayerOnScrollUp()) {
				checkScrollUp(scrollingVector);
				scrollBoxes.scroll(scrollingVector);
			}else if(isPLayerOnScrollDown()){
				checkScrollDown(scrollingVector);
				scrollBoxes.scroll(scrollingVector);
			}	
		}
	}

	private boolean isPLayerOnScrollDown() {
		return player.getEndY() == scrollBoxes.getScrollEndy();
	}

	private boolean isPlayerOnScrollUp() {
		return player.getY() == scrollBoxes.getScrollY();
	}

	private boolean isPlayerOnScrollLeft() {
		return player.getX() == scrollBoxes.getScrollX();
	}

	private boolean isPlayerOnScrollRight() {
		return player.getEndX() == scrollBoxes.getScrollEndX();
	}

	private void checkScrollLeft(Coordinates scrollingVector) {
		if(scrollBoxes.getScreenX() < 4){
			scrollingVector.setCoordinates(0 - scrollBoxes.getScreenX(), 0);
		}	
	}
	
	public void checkScrollRight(Coordinates scrollingVector) {
		if(map.getWidth() - scrollBoxes.getScreenEndX() < 4) {
			scrollingVector.setCoordinates(map.getWidth() - scrollBoxes.getScreenEndX(), 0);
		}
	}
	
	public void checkScrollUp(Coordinates scrollingVector) {
		if(scrollBoxes.getScreenEndY() < 4) {
			scrollingVector.setCoordinates(0, 0 - scrollBoxes.getScreenY());
		}
	}
	
	public void checkScrollDown(Coordinates scrollingVector) {
		if(map.getHeight() - scrollBoxes.getScreenEndY() < 4) {
			scrollingVector.setCoordinates(0, map.getHeight() - scrollBoxes.getScreenEndY());
		}
	}

	public void addListener(LevelListener listener) {
		listeners.add(listener);
	}

}
