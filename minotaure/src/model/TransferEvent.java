package model;

public class TransferEvent {
	
	private Coordinates newPlayerPosition, newPlayer2Position, newBossPosition;
	private Coordinates playerImages;
	
	public TransferEvent(Coordinates playerPosition, Coordinates playerImages) {
		this.newPlayerPosition= playerPosition;
		this.playerImages= playerImages;
	}
	
	public TransferEvent(Coordinates playerPosition, Coordinates playerImages, Coordinates bossPosition) {
		this.newPlayerPosition= playerPosition;
		this.newBossPosition= bossPosition;
		this.playerImages= playerImages;
	}

	public Coordinates getNewPlayerPosition() {return newPlayerPosition;}
	public Coordinates getNewBossPosition() {return newBossPosition;}
	public Coordinates getPlayerImages() {return playerImages;}
	

}
