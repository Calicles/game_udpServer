package view;

import type.Controller;

import java.awt.Dimension;

import javax.swing.JFrame;


public class Frame extends JFrame {
	
	

	public Frame(Controller controller, Dimension screenDimension) {
		Container container= new Container(controller, screenDimension);
		JStartMenu menu= new JStartMenu(controller);
		
		this.add(menu);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.add(container);
		
	}
	

	public int proposeLevelChoice() {
		// TODO Auto-generated method stub
		return 0;
	}


}
