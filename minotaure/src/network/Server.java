package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import model.Coordinates;
import model.TransferEvent;
import services.Byte_translator;
import services.IP_reader;
import type.AbstractServer;
import type.NetworkListener;

public class Server extends AbstractServer {
	
	private String ipAdress;
	private int port;
	private final long SLEEP= 1000/24;
	private Coordinates playerPosition, bossPosition;
	
	
	public Server() {
		String[] socket= IP_reader.readSocket().split(" ");
		this.ipAdress= socket[0];
		this.port= Integer.parseInt(socket[1]);
		this.playerPosition= null;
		this.bossPosition= null;
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

		@Override
		public void run() {
				
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
		
		private Coordinates byteArrayToCoordinates(byte[] bytes) {
			
			return null;
		}

		private synchronized void fireUpdate(DatagramPacket packet) {
			//TO DO
			
		}
		
		
	}

}
