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
import services.Coordinates_translator;

public abstract class AbstractLevel {
	
	protected AbstractMap map;
	protected Player player;
	protected Player2 player2;
	protected DoubleBoxes scrollBoxes;
	protected ArrayList<LevelListener> listeners;
	
	public AbstractLevel(Dimension screenSize, Player player, Player2 player2) {
		this.player= player;
		this.player2= player2;
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
	public Coordinates getPlayerImages() {return player.getImages();}
	public void start() {} 
	
	public abstract void update(TransferEvent te);
	protected abstract void fireUpdate();
	public abstract Coordinates getBossCoordinates();
	public Coordinates getBossImages() {return null;}
	
	public void released() {player.movesReleased();}
	public void playerMovesLeft() {player.movesLeft();}
	public void playerMovesRight() {player.movesRight();}
	public void playerMovesUp() {player.movesUp();}
	public void playerMovesDown() {player.movesDown();}

	public void drawLevel(Graphics g) {
		Color old= g.getColor();


		map.drawMap(g, scrollBoxes.getScreenPosition());
		player.draw(g, scrollBoxes.getScreenCoordinates());
		if(player2 != null)
			player2.draw(g, scrollBoxes.getScreenPosition());//TODO CHANGE
		
		//TODO REMOVE
		g.setColor(Color.BLACK);
		Coordinates scroll= Coordinates_translator.toScreenCoordinates(scrollBoxes.getScrollCoordinates(), scrollBoxes.getScreenCoordinates());
		g.drawRect(scroll.getX(), scroll.getY(), scrollBoxes.getScrollWidth(), scrollBoxes.getScrollHeight());
		g.setColor(old);
		//
	}
	
	public void scroll(Coordinates scrollingVector) {
		if(!scrollingVector.isZero()) {
			if(scrollingVector.getX() < 0 && canScrollLeft()) {
				checkScrollLeft(scrollingVector);
			}else if(scrollingVector.getX() > 0 && canScrollRight()) {
				checkScrollRight(scrollingVector);
			}else if(scrollingVector.getY() < 0 && canScrollUp()) {
				checkScrollUp(scrollingVector);
			}else if(canScrollDown()){
				checkScrollDown(scrollingVector);
			}
		}
	}

	private boolean canScrollDown() {
		return scrollBoxes.getScreenEndY() < map.getHeight() && isPLayerOnScrollDown();
	}

	private boolean canScrollUp() {
		return scrollBoxes.getScreenY() > 0 && isPlayerOnScrollUp();
	}

	private boolean canScrollRight() {
		return scrollBoxes.getScreenEndX() < map.getWidth() && isPlayerOnScrollRight();
	}

	private boolean canScrollLeft() {
		return scrollBoxes.getScreenX() > 0 && isPlayerOnScrollLeft();
	}

	private boolean isPLayerOnScrollDown() {
		return player.getEndY() >= scrollBoxes.getScrollEndy();
	}

	private boolean isPlayerOnScrollUp() {
		return player.getY() <= scrollBoxes.getScrollY();
	}

	private boolean isPlayerOnScrollLeft() {
		return player.getX() <= scrollBoxes.getScrollX();
	}

	private boolean isPlayerOnScrollRight() {
		return player.getEndX() >= scrollBoxes.getScrollEndX();
	}

	private void checkScrollLeft(Coordinates scrollingVector) {
		if(scrollBoxes.getScreenX() < 4){
			scrollingVector.setCoordinates(0 - scrollBoxes.getScreenX(), 0);
		}
		scrollBoxes.scroll(scrollingVector);
	}
	
	public void checkScrollRight(Coordinates scrollingVector) {
		if(map.getWidth() - scrollBoxes.getScreenEndX() < 4) {
			scrollingVector.setCoordinates(map.getWidth() - scrollBoxes.getScreenEndX(), 0);
		}
		scrollBoxes.scroll(scrollingVector);
	}
	
	public void checkScrollUp(Coordinates scrollingVector) {
		if(scrollBoxes.getScreenEndY() < 4) {
			scrollingVector.setCoordinates(0, 0 - scrollBoxes.getScreenY());
		}
		scrollBoxes.scroll(scrollingVector);
	}
	
	public void checkScrollDown(Coordinates scrollingVector) {
		if(map.getHeight() - scrollBoxes.getScreenEndY() < 4) {
			scrollingVector.setCoordinates(0, map.getHeight() - scrollBoxes.getScreenEndY());
		}
		scrollBoxes.scroll(scrollingVector);
	}

	public void addListener(LevelListener listener) {
		listeners.add(listener);
	}

}
