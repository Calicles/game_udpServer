package model;

public class NetEvent {
	
	private boolean lineRunning;
	
	public NetEvent(boolean lineRunning) {
		this.lineRunning= lineRunning;
	}
	
	public boolean getLineState() {return lineRunning;}

}
