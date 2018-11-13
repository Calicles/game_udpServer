package type;

import java.awt.Graphics;

public interface Controller {
	
	void createLevel(int levelType);
	void playerMovesLeft();
	void playerMovesRight();
	void playerMovesUp();
	void playerMovesDown();
	void drawLevel(Graphics g);
	void addListener(LevelListener listener);
	void addNetWorkListener(NetworkListener listener);

}
