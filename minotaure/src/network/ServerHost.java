package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import model.Coordinates;
import model.TranslationEvent;
import type.NetworkListener;

public class ServerHost {
	
	private String ipAdress;
	private int port;
	private final long SLEEP= 1000/24;
	private Coordinates playerPosition, bossPosition;
	private ArrayList<NetworkListener> listeners;
	
	
	public ServerHost(String ipAdress, int port) {
		this.ipAdress= ipAdress;
		this.port= port;
	}
	
	public void run() {
		initServer();
	}
	
	public void update(TranslationEvent te){
		this.playerPosition= te.getNewPlayerPosition();
		this.bossPosition= te.getNewBossPosition();
	}
	
	public void addListener(NetworkListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(NetworkListener listener) {
		listeners.remove(listener);
	}

	private void initServer() {
			Thread launcher= new Thread(new UD_MachineGun());
			Thread catcher= new Thread(new UD_Catcher());
			 launcher.setDaemon(true);
			 catcher.setDaemon(true);
		
	}
	
	private class UD_MachineGun implements Runnable {

		@Override
		public void run() {
				
			while(true) {
					
				//Ouverture Sécurisé, fermeture auto en cas de throws
				try(DatagramSocket launcher= new DatagramSocket()) {
					//initialise l'addresse
					InetAddress address= InetAddress.getByName(ipAdress);
					DatagramPacket packet;
					
					//Créé le paquet à envoyer
					byte[] buffer= translateCoordinates();
					packet= new DatagramPacket(buffer, buffer.length, address, port);
					packet.setData(buffer);
						
					launcher.send(packet);
						
					//Met le thread en pause pour envoyer 24 fois par seconde
					Thread.sleep(SLEEP); //To change créer un delta pour réellement caler le temps
					
				}catch(Throwable t){}
						
			}
			
		}
		
		private byte[] translateCoordinates() {
			
			return new byte[10]; //TO change
		}
		
	}
	
	private class UD_Catcher implements Runnable{

		@Override
		public void run() {
			
			while(true) {
					
					try(DatagramSocket catcher= new DatagramSocket()){
					InetAddress address= InetAddress.getByName(ipAdress);
					DatagramPacket packet;
					
					byte[] buffer= new byte[8196];
					packet= new DatagramPacket(buffer, buffer.length, address, port);
					
					catcher.receive(packet);
					
					fireUpdate(packet);
					
				}catch(Throwable t) {}
				
			}
			
		}

		private synchronized void fireUpdate(DatagramPacket packet) {
			// TODO Auto-generated method stub
			
		}
		
		
	}

}
