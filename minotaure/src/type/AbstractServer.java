package type;

import java.util.ArrayList;

import model.Coordinates;
import services.IP_reader;

public abstract class AbstractServer implements LevelListener{
	
	protected String ipAdress;
	protected int port;
	
	protected final long SLEEP= 1000/24;
	protected Coordinates playerPosition, bossPosition;
	
	protected ArrayList<NetworkListener> listeners;
	
	public AbstractServer() {
		listeners= new ArrayList<>();
		String[] socket= IP_reader.readSocket().split(" ");
		this.ipAdress= socket[0];
		this.port= Integer.parseInt(socket[1]);
		this.playerPosition= null;
		this.bossPosition= null;
		
	}
	
	public void run(Coordinates playerPosition, Coordinates bossPosition) {}
	
	public void addListener(NetworkListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(NetworkListener listener) {
		listeners.remove(listener);
	}

}
