package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import model.Coordinates;
import model.TransferEvent;
import services.Byte_translator;
import type.AbstractServer;

public class Server extends AbstractServer {

	
	public Server() {
		super();
	}

	public void run(Coordinates playerPosition, Coordinates bossPosition) {
		this.playerPosition= playerPosition;
		this.bossPosition= bossPosition;
		initServer();
	}
	
	public void update(TransferEvent te){
		this.playerPosition= te.getNewPlayerPosition();
		this.bossPosition= te.getNewBossPosition();
	}

	private void initServer() {
			Thread launcher= new Thread(new UD_MachineGun());
			Thread catcher= new Thread(new UD_Catcher());
			launcher.setDaemon(true);
			catcher.setDaemon(true);
			launcher.start();
			catcher.start();
	}
	
	private class UD_MachineGun implements Runnable {

		private long before, after;
		
		@Override
		public void run() {
				
			before= System.currentTimeMillis();
			while(true) {
					
				//Ouverture Sécurisé, fermeture auto en cas de throws
				try(DatagramSocket launcher= new DatagramSocket()) {
					
					//initialise l'addresse
					InetAddress address= InetAddress.getByName(ipAdress);
					DatagramPacket packet;
					
					//Créé le paquet à envoyer
					byte[] buffer= coordinatesToByteArray();
					packet= new DatagramPacket(buffer, buffer.length, address, port);
					packet.setData(buffer);
						
					launcher.send(packet);
						
					//Met le thread en pause pour envoyer 24 fois par seconde
					Thread.sleep(SLEEP); //To change créer un delta pour réellement caler le temps
					
				}catch(Throwable t){}
				after= System.currentTimeMillis();
				sleep();
				before= System.currentTimeMillis();		
			}
			
		}
		
		/**
		 * met le thrad en pause
		 */
		private void sleep() {
			long delta= after - before;
			if(delta < SLEEP) {
				try {
					Thread.sleep(SLEEP - delta);
				}catch(InterruptedException ie) {}
			}
		}

		private byte[] coordinatesToByteArray() {
			byte[] res= new byte[Integer.SIZE * 4];
			byte[] bytes1= Byte_translator.toByteArray(playerPosition);
			byte[] bytes2= Byte_translator.toByteArray(bossPosition);
			
			Byte_translator.copy(bytes1, res, 0);
			Byte_translator.copy(bytes2, res, bytes1.length);
			
			return res; 
		}
		
	}
	
	private class UD_Catcher implements Runnable{

		private long before, after;
		
		@Override
		public void run() {
			
			before= System.currentTimeMillis();
			while(true) {
					
				try(DatagramSocket catcher= new DatagramSocket()){
					
					InetAddress address= InetAddress.getByName(ipAdress);
					DatagramPacket packet;
					
					byte[] buffer= new byte[8196];
					packet= new DatagramPacket(buffer, buffer.length, address, port);
					
					catcher.receive(packet);
					
					fireUpdate(packet);
					
				}catch(Throwable t) {}
				after= System.currentTimeMillis();
				sleep();
				before= System.currentTimeMillis();
			}
			
		}
		
		private Coordinates byteArrayToCoordinates(byte[] bytes) {
			
			return null;
		}

		private synchronized void fireUpdate(DatagramPacket packet) {
			//TO DO
			
		}
		
		private void sleep() {
			long delta= after - before;
			if(delta < SLEEP) {
				try {
					Thread.sleep(SLEEP - delta);
				}catch(InterruptedException ie) {}
			}
		}
		
		
	}

}
