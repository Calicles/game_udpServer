package model;

public class TransferEvent {
	
	private Coordinates newPlayerPosition, newBossPosition;
	private Coordinates playerImages, bossImages;
	
	public TransferEvent(Coordinates playerPosition, Coordinates playerImages) {
		this.newPlayerPosition= playerPosition;
		this.playerImages= playerImages;
	}

	public TransferEvent(Coordinates newPlayerPosition, Coordinates playerImages,
			Coordinates newBossPosition, Coordinates bossImages) {
		this.newPlayerPosition= newPlayerPosition;
		this.playerImages= playerImages;
		this.newBossPosition= newBossPosition;
		this.bossImages= bossImages;
	}

	public Coordinates getNewPlayerPosition() {return newPlayerPosition;}
	public Coordinates getNewBossPosition() {return newBossPosition;}
	public Coordinates getPlayerImages() {return playerImages;}
	public Coordinates getBossImages() {return bossImages;}
	

}
