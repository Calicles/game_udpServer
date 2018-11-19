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

					byte[] buffer= new byte[8192];//coordinatesToByteArray(); TODO
					packet= new DatagramPacket(buffer, buffer.length);
						
					launcher.receive(packet);
	
					fireUpdate(packet.getData());
					//print("afterUpdate",null);
					//TODO RMOVE
					//print("in server !!reçu du client:   "+packet.getAddress()+"p: "+packet.getPort()+"  ",data);//TODO change for treatment
					
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
			System.out.println("infireupdateserver"+Arrays.toString(data)+"   ");
			player2Position= Byte_translator.toCoordinates(data);
			TransferEvent event= new TransferEvent(player2Position,null);
			
				listeners.get(0).update(event);
		}
		
		private synchronized byte[] toByteArray() {
			if(playerPosition != null) {
				byte[] buffer= Byte_translator.toByteArray(playerPosition);
				return buffer;
			}else
				return new byte[16];
		}


		protected byte[] coordinatesToByteArray() {
			//byte[] res= new byte[Integer.SIZE * 4];
			byte[] bytes1= Byte_translator.toByteArray(playerPosition);
			//byte[] bytes2= Byte_translator.toByteArray(bossPosition);
			
			//Byte_translator.copy(bytes1, res, 0);
			//Byte_translator.copy(bytes2, res, bytes1.length);
			
			return bytes1; 
		}
		
	}
	


}
