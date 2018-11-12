package view;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import type.Controller;

public class JScreen extends JPanel {
	
	Controller controller;
	
	public JScreen(Controller controller) {
		this.controller= controller;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		controller.drawLevel(g);
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
