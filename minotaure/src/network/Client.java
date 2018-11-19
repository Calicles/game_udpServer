package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import model.Coordinates;
import model.NetEvent;
import model.TransferEvent;
import services.Byte_translator;
import type.AbstractServer;
import type.NetworkListener;

public class Client extends AbstractServer {
	
	public Client() {
		super();
		
	}
	
	public void run(Coordinates playerPosition, Coordinates bossPosition) {
		this.playerPosition= playerPosition;
		this.bossPosition= bossPosition;
		initServer();
	}
	

	public void initServer() {
		//Thread launcher= new Thread(new UD_MachineGunCli());
		Thread catcher= new Thread(new UD_Catcher());
		//launcher.setDaemon(true);
		catcher.setDaemon(true);
		//launcher.start();
		catcher.start();
	}
	
	@Override
	public void update(TransferEvent te) {
		playerPosition= te.getNewPlayerPosition();
	}
	
	protected class UD_Catcher implements Runnable{
		
		@Override
		public void run() {
			
			before= System.currentTimeMillis();
			while(true) {
					
				try(DatagramSocket catcher= new DatagramSocket()){
					
					InetAddress address= InetAddress.getByName(ipAdress);
					DatagramPacket packet, packet2;
					
					byte[] buffer= coordinatesToByteArray();
					packet= new DatagramPacket(buffer, buffer.length, address, port);
					
					packet.setData(buffer);
					
					catcher.send(packet);
					
					byte[] buffer2= new byte[8192];
					
					packet2= new DatagramPacket(buffer2, buffer2.length, address, port);
					
					catcher.receive(packet2);
					fireUpdate();
					byte[] data= packet2.getData();
					print("in client", data);//TODO REMOVE

					
				}catch(Throwable t) {System.out.println(t);}
				after= System.currentTimeMillis();
				sleep();
				before= System.currentTimeMillis();
			}
			
		}
		
		protected Coordinates byteArrayToCoordinates(byte[] bytes) {
			
			return null;
		}
		
		protected synchronized byte[] coordinatesToByteArray() {
			byte[] buffer= Byte_translator.toByteArray(playerPosition);
			System.out.println("inclient  byte: "+Arrays.toString(buffer));
			return buffer;
		}

		protected void fireUpdate() {
			//TODO CHange
			NetEvent ne= new NetEvent(true);
			for(NetworkListener l:listeners) {
				l.updateState(ne);
			}
		}
		
	}
}
