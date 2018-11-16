package type;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import model.Coordinates;
import model.Rectangle;
import services.Character_reader;
import services.Coordinates_translator;
import services.Pythagore;

public abstract class AbstractCharacter {
	
	protected HashMap<Integer,BufferedImage[]> animation;  // leftAnim, rightAnim, upAnim, downAnim
	protected BufferedImage currentImage;
	protected Rectangle position;
	
	protected int direction, animIndex; //le second est un paramètre à envoyer via le server
	
	public AbstractCharacter(String setUrl) {
		Coordinates buffer= new Coordinates(0, 0);
		animation= Character_reader.readCharactereAnimation(setUrl, buffer);
		position= new Rectangle(buffer, animation.get(0)[0].getWidth(),
				animation.get(0)[0].getHeight());
		currentImage= animation.get(0)[0];
	}
	public AbstractCharacter() {}
	
	public Rectangle getPosition() {return position;}
	public int getDirection() {return direction;}
	public int getAnimIndex() {return animIndex;}
	public Coordinates getCoordinates() {return position.getCoordinates();}
	public BufferedImage getImage() {return currentImage;}
	public int getDiagonal() {return position.getDiagonal();}
	public int getX() {return position.getX();}
	public int getEndX() { return position.getEndX();}
	public int getY() {return position.getY();}
	public int getEndY() {return position.getEndY();}
	
	public void setCoordinates(Coordinates newPosition) {
		position.setCoordinates(newPosition.getX(), newPosition.getY());
	}
	
	public void draw(Graphics g, Coordinates screen) {
		Coordinates byScreen= Coordinates_translator.toScreenCoordinates(position.getCoordinates(), 
				screen);
		g.drawImage(currentImage, byScreen.getX(), byScreen.getY(), null);
	}
	
	public void drawIfInScreen(Graphics g, Rectangle screen) {
		
	}
	

}
