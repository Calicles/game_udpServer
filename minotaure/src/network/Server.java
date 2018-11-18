package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import model.Coordinates;
import model.NetEvent;
import model.TransferEvent;
import services.Byte_translator;
import type.AbstractServer;
import type.NetworkListener;

public class Server extends AbstractServer {

	protected int threadnum= 0;
	
	public Server(int num) {
		super();
		threadnum = num;
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

	protected void initServer() {
			Thread launcher= new Thread(new UD_MachineGun());
			//Thread catcher= new Thread(new UD_Catcher());
			launcher.setDaemon(true);
			//catcher.setDaemon(true);
			launcher.start();
			//catcher.start();
	}
	
	protected class UD_MachineGun implements Runnable {

		protected long before, after;
		protected int nbr=0; //TODO REMOVE for test
		
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
					byte[] buffer= (++nbr+"").getBytes();//coordinatesToByteArray();
					packet= new DatagramPacket(buffer, buffer.length, address, port);
					packet.setData(buffer);
						
					launcher.send(packet);
						//System.out.println("packet: "+new String(packet.getData())+"   in:  "+threadnum);
					//Met le thread en pause pour envoyer 24 fois par seconde
					Thread.sleep(SLEEP);
					
				}catch(Throwable t){}
				after= System.currentTimeMillis();
				sleep();
				before= System.currentTimeMillis();		
			}
			
		}
		
		/**
		 * met le thrad en pause
		 */
		protected void sleep() {
			long delta= after - before;
			if(delta < SLEEP) {
				try {
					Thread.sleep(SLEEP - delta);
				}catch(InterruptedException ie) {}
			}
		}

		protected byte[] coordinatesToByteArray() {
			byte[] res= new byte[Integer.SIZE * 4];
			byte[] bytes1= Byte_translator.toByteArray(playerPosition);
			byte[] bytes2= Byte_translator.toByteArray(bossPosition);
			
			Byte_translator.copy(bytes1, res, 0);
			Byte_translator.copy(bytes2, res, bytes1.length);
			
			return res; 
		}
		
	}
	
	protected class UD_Catcher implements Runnable{

		protected long before, after;
		
		@Override
		public void run() {
			
			before= System.currentTimeMillis();
			while(true) {
					
				try(DatagramSocket catcher= new DatagramSocket()){
					
					InetAddress address= InetAddress.getByName(ipAdress);
					DatagramPacket packet;
					
					byte[] buffer= new byte[8196];
					packet= new DatagramPacket(buffer, buffer.length, address, port);
					System.out.println("inUD_Catcher");
					catcher.receive(packet);

					fireUpdate(packet);
					
				}catch(Throwable t) {}
				after= System.currentTimeMillis();
				sleep();
				before= System.currentTimeMillis();
			}
			
		}
		
		protected Coordinates byteArrayToCoordinates(byte[] bytes) {
			
			return null;
		}

		protected synchronized void fireUpdate(DatagramPacket packet) {
			//TODO CHange
			NetEvent ne= new NetEvent(true);
			for(NetworkListener l:listeners) {
				l.updateState(ne);
			}
		}
		
		protected void sleep() {
			long delta= after - before;
			if(delta < SLEEP) {
				try {
					Thread.sleep(SLEEP - delta);
				}catch(InterruptedException ie) {}
			}
		}
		
	}

}
