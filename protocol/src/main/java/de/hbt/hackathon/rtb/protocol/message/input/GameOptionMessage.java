package de.hbt.hackathon.rtb.protocol.message.input;

import de.hbt.hackathon.rtb.protocol.type.GameOptionType;

public class GameOptionMessage {
	
	private final GameOptionType gameOptionType;
	private final double value;
	
	private GameOptionMessage(GameOptionType gameOptionType, double value) {
		this.gameOptionType = gameOptionType;
		this.value = value;
	}

	public GameOptionType getGameOptionType() {
		return gameOptionType;
	}

	public double getValue() {
		return value;
	}
	
	public GameOptionMessage valueOf(String[] args) {
		int gameOptionCodeType = Integer.parseInt(args[1]);
		GameOptionType gameOptionType = GameOptionType.valueOf(gameOptionCodeType);
		double value = Double.parseDouble(args[2]);
		return new GameOptionMessage(gameOptionType, value);
	}
}
