package view;

import type.Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
	
	public void loadScreen() {
		this.remove(menu);
		menu= null;
		controller.addNetWorkListener(info);
		controller.addListener(screen);
		this.add(screen, BorderLayout.CENTER);
		
	}
	
	public boolean getUserChoosed() {return menu.getUserChoosed();}

}
