package view;

import java.awt.Dimension;

import javax.swing.JPanel;

import type.Controller;

@SuppressWarnings("serial")
public class JStartMenu extends JPanel {
	
	private boolean userChoosed;

	public JStartMenu(Controller controller, Dimension screenDimension) {
		LevelChoiceButton levelHostButton= new LevelChoiceButton("Hôte");
		LevelChoiceButton levelClientButton= new LevelChoiceButton("Client");
		userChoosed= false;
		
		levelHostButton.addActionListener((e)->{
			controller.createLevel(1);
			userChoosed= true;
		});
		
		levelClientButton.addActionListener((e)->{
			controller.createLevel(2);
			userChoosed= true;
		});
		
		this.setPreferredSize(screenDimension);
		
		
	}
	
	public boolean getUserChoosed() {return userChoosed;}
	
	

}
