package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.NetEvent;
import model.TransferEvent;
import type.NetworkListener;

@SuppressWarnings("serial")
public class JInfoPanel extends JPanel implements NetworkListener {
	
	private boolean inGame;
	private Dimension size;
	
	public JInfoPanel(int width) {
		inGame= false;
		size= new Dimension(width, 150);
		this.setBackground(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color old= g.getColor();
		g.setColor(Color.CYAN);
		if(!inGame) {
			g.drawString("En attente d'une connexion", 0, 0);
		}else
			g.drawString("Connecté", 0, 0);
		g.setColor(old);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return size;
	}

	@Override
	public void updateState(NetEvent ne) {
		if(ne.getLineState()) inGame= true;
		else inGame= false;
	}

	@Override
	public void update(TransferEvent te) {
		// TODO Auto-generated method stub
		
	}

}
