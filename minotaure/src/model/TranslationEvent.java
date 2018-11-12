package model;

public class TranslationEvent {
	
	private Coordinates newPlayerPosition, newBossPosition;
	
	public TranslationEvent(Coordinates playerPosition, Coordinates bossPosition) {
		this.newPlayerPosition= playerPosition;
		this.newBossPosition= bossPosition;
	}
	
	public Coordinates getNewPlayerPosition() {return newPlayerPosition;}
	public Coordinates getNewBossPosition() {return newBossPosition;}

}
