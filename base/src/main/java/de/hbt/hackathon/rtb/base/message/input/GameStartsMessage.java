package de.hbt.hackathon.rtb.base.message.input;

public class GameStartsMessage extends InputMessage {
	
	private static final GameStartsMessage SINGLETON = new GameStartsMessage();

	public static GameStartsMessage valueOf(String[] args) {
		return SINGLETON;
	}

}
