package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import model.NetEvent;
import model.TransferEvent;
import type.NetworkListener;

@SuppressWarnings("serial")
public class JInfoPanel extends JLabel implements NetworkListener {
	
	private boolean inGame;
	private Dimension size;
	
	public JInfoPanel(Dimension dimension) {
		Border border= BorderFactory.createLineBorder(Color.YELLOW, 2);
		inGame= false;
		size= dimension;
		this.setBackground(Color.BLACK);
		this.setBorder(border);
		updateEvent(null);
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		return size;
	}

	@Override
	public void updateState(NetEvent ne) {
		if(ne.getLineState()) {
			inGame= true;
			this.setText("Connecxion établie");
		}else {
		inGame= false;
		this.setText("En attente de connexion");
		}
	}

	@Override
	public void updateEvent(TransferEvent te) {

		
	}


	@Override
	public void update(TransferEvent te) {
		if(te.getNewPlayerPosition() != null) {
			inGame= true;
			this.setText("Connecxion établie");
		}else {
		inGame= false;
		this.setText("En attente de connexion");
		}
		
	}

}
