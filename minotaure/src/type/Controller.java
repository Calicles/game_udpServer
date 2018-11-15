package type;

import java.awt.Dimension;
import java.awt.Graphics;

public interface Controller {
	
	void createLevel(int levelType, Dimension screenSize);
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
