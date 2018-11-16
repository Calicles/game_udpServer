package view;

import type.Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Frame extends JFrame {

	private Controller controller;

	private JInfoPanel info;
	private JScreen screen;
	private JStartMenu menu;
	private Dimension screenSize;
	
	public Frame(Controller controller, Dimension screenDimension) {
		this.controller= controller;
		this.screenSize= screenDimension;
		menu= new JStartMenu(screenDimension);
		menu.setButtonListener(this.controller);
		info= new JInfoPanel(new Dimension(screenSize.width, 150));

		this.setLayout(new BorderLayout());
		this.add(menu, BorderLayout.CENTER);
		this.add(info, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public boolean getUserChoosed() {return menu.getUserChoosed();}
	
	public void loadScreen() {
		this.remove(menu);
		menu.setVisible(false);
		menu= null;
		screen= new JScreen(controller, screenSize);
		controller.addNetWorkListener(info);
		controller.addListener(screen);
		this.addKeyListener(new UserListener());
		this.add(screen);
		screen.repaint();
		this.revalidate();
		this.repaint();
		this.requestFocus();
	}

	
	private class UserListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent ke) {
			if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
				controller.playerMovesLeft();
			}else if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
				controller.playerMovesRight();
			else if(ke.getKeyCode() == KeyEvent.VK_UP)
				controller.playerMovesUp();
			else if(ke.getKeyCode() == KeyEvent.VK_DOWN)
				controller.playerMovesDown();
	
		}

		@Override
		public void keyReleased(KeyEvent ke) {
			if(direction(ke))
				controller.released();
		}

		@Override
		public void keyTyped(KeyEvent ke) {
			// TODO Auto-generated method stub
			
		}

		private boolean direction(KeyEvent ke) {
			return (ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_DOWN
					|| ke.getKeyCode() == KeyEvent.VK_LEFT || ke.getKeyCode() == KeyEvent.VK_RIGHT);
		}
		
	}

}
