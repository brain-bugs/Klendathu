package de.hbt.hackathon.rtb.base.message.input;

public class GameFinishesMessage extends InputMessage {
	
	private static final GameFinishesMessage SINGLETON = new GameFinishesMessage();

	public static GameFinishesMessage valueOf(String[] args) {
		return SINGLETON;
	}

}
