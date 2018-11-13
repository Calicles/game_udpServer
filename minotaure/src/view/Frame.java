package view;

import type.Controller;

import java.awt.Dimension;

import javax.swing.JFrame;


public class Frame extends JFrame {

	public Frame(Controller controller, Dimension screenDimension) {
		Container container= new Container(controller, screenDimension);
		this.add(container);
		this.setContentPane(container);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

}
