package model;

public class TransferEvent {
	
	private Coordinates newPlayerPosition, newBossPosition;
	
	public TransferEvent(Coordinates playerPosition, Coordinates bossPosition) {
		this.newPlayerPosition= playerPosition;
		this.newBossPosition= bossPosition;
	}
	
	public Coordinates getNewPlayerPosition() {return newPlayerPosition;}
	public Coordinates getNewBossPosition() {return newBossPosition;}

}
