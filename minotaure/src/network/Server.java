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

public class Server extends AbstractServer {
	
	public Server() {
		super();
	}
	
	public void update(TransferEvent te){
		this.playerPosition= te.getNewPlayerPosition();
		this.playerImages= te.getPlayerImages();
		this.bossPosition= te.getNewBossPosition();
		this.bossImages= te.getBossImages();
	}

	protected void initServer() {
		
			Thread launcher= new Thread(new UD_MachineGun());

			launcher.setDaemon(true);

			launcher.start();

	}
	
	protected class UD_MachineGun implements Runnable {

		
		@Override
		public void run() {
				
			before= System.currentTimeMillis();
			while(true) {
					

				try(DatagramSocket launcher= new DatagramSocket(port)) {
					

					DatagramPacket packet, packet2;

					byte[] buffer= new byte[16];//coordinatesToByteArray(); TODO
					packet= new DatagramPacket(buffer, buffer.length);
						
					launcher.receive(packet);
					
					fireUpdate(packet.getData());
			
					packet.setLength(buffer.length);
					
					byte[] buffer2= toByteArray();
					packet2= new DatagramPacket(buffer2, buffer2.length,
												packet.getAddress(), packet.getPort());
					
					launcher.send(packet2);
					
				}catch(Throwable t){System.out.println(t);}
				after= System.currentTimeMillis();
				sleep();
				before= System.currentTimeMillis();		
			}
			
		}
		

		private void fireUpdate(byte[] data) {
			
			byte[] buffer1= new byte[8];
			byte[] buffer2= new byte[8];
			
			Byte_translator.copyOut(data, buffer1, buffer2);
			player2Position= Byte_translator.toCoordinates(buffer1);
			player2Images= Byte_translator.toCoordinates(buffer2);
			TransferEvent event= new TransferEvent(player2Position, player2Images);
			
			for(NetworkListener l:listeners)
				l.update(event);
		}
		
		private synchronized byte[] toByteArray() {
			byte[] res= new byte[32];
			byte[] buffer= Byte_translator.toByteArray(playerPosition);
			byte[] buffer2= Byte_translator.toByteArray(playerImages);
			byte[] buffer3= new byte[16];
			byte[] buffer4= new byte[16];
			Byte_translator.copy(buffer, buffer2, buffer3, 0);
			
			buffer= Byte_translator.toByteArray(bossPosition);
			buffer2= Byte_translator.toByteArray(bossImages);
			Byte_translator.copy(buffer, buffer2, buffer4, 0);
			
			Byte_translator.copy(buffer3, buffer4, res, 0);
			return res;
		}
		
		protected void fireUpdateState() {
			NetEvent event= new NetEvent(true);
			for(NetworkListener l:listeners) {
				l.updateState(event);
			}
		}
		
	}

}
