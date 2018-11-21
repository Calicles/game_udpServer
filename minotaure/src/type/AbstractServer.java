package type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Coordinates;
import services.IP_reader;

public abstract class AbstractServer implements LevelListener{
	
	protected String ipAdress;
	protected int port;	
	
	protected final long SLEEP= 1000/24; 
	protected long before, after;
	protected Coordinates playerPosition, playerImages, player2Position, player2Images, bossPosition, bossImages;
	
	protected List<NetworkListener> listeners;
	
	public AbstractServer() {
		listeners= Collections.synchronizedList(new ArrayList<NetworkListener>());
		String[] socket= IP_reader.readSocket().split(" ");
		this.ipAdress= socket[0];
		this.port= Integer.parseInt(socket[1]);
		this.playerPosition= null;
		this.bossPosition= null;
	}
	
	protected abstract void initServer();
	
	public void run(Coordinates playerPosition, Coordinates playerImages,
					Coordinates bossPosition, Coordinates bossImages) {
		
		this.playerPosition= playerPosition;
		this.bossPosition= bossPosition;
		this.playerImages= playerImages;
		this.bossImages= bossImages;
		initServer();
	}
	
	public void addListener(NetworkListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(NetworkListener listener) {
		listeners.remove(listener);
	}
	
	
	protected void sleep() {
		long delta= after - before;
		if(delta < SLEEP) {
			try {
				Thread.sleep(SLEEP - delta);
			}catch(InterruptedException ie) {}
		}
	}
	
	protected synchronized void print(String s, byte[] data) {
		String msg= new String(data);
		System.out.println(s+"    "+msg);
	}

}
