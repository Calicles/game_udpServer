package type;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import contracts.Charac_withoutTransfert;
import model.Coordinates;
import model.Rectangle;
import services.Character_reader;
import services.Coordinates_translator;

public abstract class AbstractCharacter implements Charac_withoutTransfert {
	
	protected HashMap<Integer,BufferedImage[]> animation;  // leftAnim, rightAnim, upAnim, downAnim
	protected BufferedImage currentImage;
	protected Rectangle position;
	private String urlImage;
	
	protected int direction, animIndex; 
	
	public AbstractCharacter(String setUrl, String coorDepart) {
		Coordinates buffer= tradCoor(coorDepart);
		animation= Character_reader.readCharactereAnimation(setUrl, buffer);
		position= new Rectangle(buffer, animation.get(0)[0].getWidth(),
				animation.get(0)[0].getHeight());
		currentImage= animation.get(0)[0];
	}
	public AbstractCharacter() {}
	
	public void setUrlImage(String url) {
		this.urlImage= url;
	}
	
	public void setPosition(String coorDepart) {
		Coordinates position= tradCoor(coorDepart);System.out.println("in abstractChar position:  "+position);
		animation= Character_reader.readCharactereAnimation(urlImage, position);
		this.position= new Rectangle(position, animation.get(0)[0].getWidth(),
				animation.get(0)[0].getHeight());
		currentImage= animation.get(0)[0];
	}
	
	public Rectangle getPosition() {return position;}
	public int getDirection() {return direction;}
	public int getAnimIndex() {return animIndex;}
	public Coordinates getImages() {return new Coordinates(direction, animIndex);}
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
	
	public void setImages(Coordinates images) {
		currentImage= this.animation.get(images.getX())[images.getY()];
	}
	
	public void draw(Graphics g, Coordinates screen) {
		Coordinates byScreen= Coordinates_translator.toScreenCoordinates(position.getCoordinates(), 
				screen);
		g.drawImage(currentImage, byScreen.getX(), byScreen.getY(), null);
	}
	
	public void drawIfInScreen(Graphics g, Rectangle screen) {
		g.drawImage(currentImage, position.getX(), position.getY(), null);//TODO Change
	}
	
	private Coordinates tradCoor(String coor) {
		String[] buff= coor.split(",");
		return new Coordinates(Integer.parseInt(buff[0]), Integer.parseInt(buff[1]));
	}
	

}
