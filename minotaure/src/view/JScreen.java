package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import model.TransferEvent;
import type.Controller;
import type.LevelListener;

@SuppressWarnings("serial")
public class JScreen extends JPanel implements LevelListener {
	
	Controller controller;
	Dimension screenDimension;
	
	public JScreen(Controller controller, Dimension screenDimension) {
		this.controller= controller;
		this.signupLevel();
		controller.startGame();
	}
	
	@Override 
	public Dimension getPreferredSize() {return screenDimension;}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		controller.drawLevel(g);
	}
	
	private void signupLevel() {
		controller.addListener(this);
	}
	
	@Override
	public void update(TransferEvent te) {
		this.repaint();
		
	}


}
