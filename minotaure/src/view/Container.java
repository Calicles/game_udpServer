package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import type.Controller;

public class Container extends JPanel {
	
	public Container(Controller controller, Dimension screenDimension) {
		JScreen screen;
		JStartMenu menu= new JStartMenu(controller, screenDimension);
		JInfoPanel info;
		
		
		while(!menu.getUserChoosed());
		this.remove(menu);
		
		screen= new JScreen(controller, screenDimension);
		info = new JInfoPanel();
		controller.addNetWorkListener(info);
		this.setLayout(new BorderLayout());
		this.add(screen, BorderLayout.CENTER);
		this.add(info, BorderLayout.SOUTH);
	
	}
	
	
}
