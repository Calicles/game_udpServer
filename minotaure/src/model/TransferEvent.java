package model;

public class TransferEvent {
	
	private Coordinates newPlayerPosition, newPlayer2Position, newBossPosition;
	
	public TransferEvent(Coordinates playerPosition, Coordinates bossPosition) {
		this.newPlayerPosition= playerPosition;
		this.newBossPosition= bossPosition;
		this.newPlayer2Position= null;
	}
	
	public TransferEvent(Coordinates playerPosition, Coordinates player2Position, Coordinates bossPosition) {
		this.newPlayerPosition= playerPosition;
		this.newBossPosition= bossPosition;
		this.newPlayer2Position= player2Position;
	}

	public Coordinates getNewPlayerPosition() {return newPlayerPosition;}
	public Coordinates getNewBossPosition() {return newBossPosition;}
	

}
