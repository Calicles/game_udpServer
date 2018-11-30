package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

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
			try {
				controller.createLevel("host", screenSize);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException | SAXException
					| IOException | ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			userChoosed= true;
		});
		
		levelClientButton.addActionListener((e)->{
			try {
				controller.createLevel("cli", screenSize);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException | SAXException
					| IOException | ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			userChoosed= true;
		});
	}
	
	

}
