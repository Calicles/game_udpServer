package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import type.Controller;
import type.LevelListener;

public class JScreen extends JPanel implements LevelListener {
	
	Controller controller;
	Dimension screenDimension;
	
	public JScreen(Controller controller, Dimension screenDimension) {
		this.controller= controller;
		this.signupLevel();
	}
	
	@Override 
	public Dimension getPreferredSize() {return screenDimension;}
	
	@Override
	public void paintComponent(Graphics g) {
		controller.drawLevel(g);
	}
	
	private void signupLevel() {
		controller.addListener(this);
	}
	
	
	
	
	private class UserListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}

}
