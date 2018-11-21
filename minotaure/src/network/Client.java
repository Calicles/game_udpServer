package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import model.TransferEvent;
import services.Byte_translator;
import type.AbstractServer;
import type.NetworkListener;

public class Client extends AbstractServer {
	
	public Client() {
		super();
		
	}

	public void initServer() {
		
		Thread catcher= new Thread(new UD_Catcher());
		
		catcher.setDaemon(true);
		
		catcher.start();
	}
	
	@Override
	public void update(TransferEvent te) {
		playerPosition= te.getNewPlayerPosition();
		playerImages= te.getPlayerImages();
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
					
					byte[] buffer2= new byte[32];
					
					packet2= new DatagramPacket(buffer2, buffer2.length, address, port);
					
					catcher.receive(packet2);
					
					byteArrayToCoordinates(packet2.getData());

					
				}catch(Throwable t) {System.out.println(t);}
				after= System.currentTimeMillis();
				sleep();
				before= System.currentTimeMillis();
			}
			
		}
		
		protected void byteArrayToCoordinates(byte[] bytes) {
			byte[] buffer= new byte[16];
			byte[] buffer2= new byte[16];
			Byte_translator.copyOut(bytes, buffer, buffer2);
			
			byte[] buffer3= new byte[8];
			byte[] buffer4= new byte[8];
			Byte_translator.copyOut(buffer, buffer3, buffer4);
			player2Position= Byte_translator.toCoordinates(buffer3);
			player2Images= Byte_translator.toCoordinates(buffer4);
			
			Byte_translator.copyOut(buffer2, buffer3, buffer4);
			bossPosition= Byte_translator.toCoordinates(buffer3);
			bossImages= Byte_translator.toCoordinates(buffer4);
			
			TransferEvent event= new TransferEvent(player2Position, player2Images, bossPosition, bossImages);
			fireUpdate(event);
		}
		
		protected synchronized byte[] coordinatesToByteArray() {
			byte[] res= new byte[16];
			byte[] buffer= Byte_translator.toByteArray(playerPosition);
			byte[] buffer2= Byte_translator.toByteArray(playerImages);
			
			Byte_translator.copy(buffer, buffer2, res, 0);
			
			return res;
		}
		
		protected void fireUpdate(TransferEvent event) {
			for(NetworkListener l:listeners) {
				l.update(event);
			}
		}
		
	}
}
