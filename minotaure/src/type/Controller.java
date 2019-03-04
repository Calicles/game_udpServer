package type;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface Controller {
	
	void createLevel(String levelType, Dimension screenSize) throws SAXException, IOException,
	ParserConfigurationException, ClassNotFoundException, NoSuchMethodException, SecurityException,
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	void playerMovesLeft();
	void playerMovesRight();
	void playerMovesUp();
	void playerMovesDown();
	void released();
	void drawLevel(Graphics g);
	void addListener(LevelListener listener);
	void addNetWorkListener(NetworkListener listener);
	void startGame();

}
