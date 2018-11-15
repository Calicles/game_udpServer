package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import type.Controller;

@SuppressWarnings("serial")
public class JStartMenu extends JPanel {
	
	LevelChoiceButton levelClientButton, levelHostButton;

	private boolean userChoosed;
	private Dimension screenSize;

	public JStartMenu(Dimension screenDimension) {
		JLabel label= new JLabel();
		label.setForeground(Color.YELLOW);
		label.setText("Quelle type de partie voulez-vous lancer?");
		
		levelHostButton= new LevelChoiceButton("Hôte");
		levelClientButton= new LevelChoiceButton("Client");
		userChoosed= false;
		screenSize= screenDimension;
		
		this.setPreferredSize(screenDimension);
		this.setLayout(new FlowLayout());
		this.add(levelHostButton);
		this.add(levelClientButton);
		this.add(label);
		this.setBackground(Color.BLACK);
		
	}
	
	public boolean getUserChoosed() {return userChoosed;}
	
	public void setButtonListener(Controller controller) {
		
		levelHostButton.addActionListener((e)->{
			controller.createLevel(1, screenSize);
			userChoosed= true;
		});
		
		levelClientButton.addActionListener((e)->{
			controller.createLevel(2, screenSize);
			userChoosed= true;
		});
	}
	
	

}
