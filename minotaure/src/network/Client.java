package network;

import java.net.DatagramPacket;

import model.Coordinates;
import model.TransferEvent;
import services.Byte_translator;

public class Client extends Server {
	
	public Client() {
		super();
		
	}
	
	@Override
	public void initServer() {
		Thread launcher= new Thread(new UD_MachineGunCli());
		Thread catcher= new Thread(new UD_CatcherCli());
		launcher.setDaemon(true);
		catcher.setDaemon(true);
		launcher.start();
		catcher.start();
	}
	
	@Override
	public void update(TransferEvent te) {
		
	}
	
	private class UD_MachineGunCli extends UD_MachineGun {
		
		
		@Override
		protected byte[] coordinatesToByteArray() {
			byte[] bytes1= Byte_translator.toByteArray(playerPosition);
			
			return bytes1; 
		}
		
	}
	
	
	private class UD_CatcherCli extends UD_Catcher {
		
		
		@Override
		protected Coordinates byteArrayToCoordinates(byte[] bytes) {
			
			return null;
		}

		@Override
		protected synchronized void fireUpdate(DatagramPacket packet) {
			//TO DO
			
		}
	}
}
