package view;

import javax.swing.JPanel;

import type.Controller;

public class JStartMenu extends JPanel {
	
	private boolean userChoosed;

	public JStartMenu(Controller controller) {
		LevelChoiceButton levelHostButton= new LevelChoiceButton("Hôte");
		LevelChoiceButton levelClientButton= new LevelChoiceButton("Client");
		userChoosed= false;
		levelHostButton.addActionListener(l);
		
	}
	
	

}
