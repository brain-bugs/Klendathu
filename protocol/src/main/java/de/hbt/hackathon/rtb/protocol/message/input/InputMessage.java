package de.hbt.hackathon.rtb.protocol.message.input;

public abstract class InputMessage {

	public static InputMessage valueOf(String value) {
		String[] args = value.split(" ");

		try {
			String messageType = args[0];
			switch (messageType) {
			case "Collision":
				return CollisionMessage.valueOf(args);
			case "Coordinates":
				return CoordinatesMessage.valueOf(args);
			case "Dead":
				return DeadMessage.valueOf(args);
			case "Energy":
				return EnergyMessage.valueOf(args);
			case "ExitRobot":
				return ExitRobotMessage.valueOf(args);
			case "GameFinishes":
				return GameFinishesMessage.valueOf(args);
			case "Info":
				return InfoMessage.valueOf(args);
			case "Initialize":
				return InitializeMessage.valueOf(args);
			case "Radar":
				return RadarMessage.valueOf(args);
			case "RobotInfo":
				return RobotInfoMessage.valueOf(args);
			case "RobotsLeft":
				return RobotsLeftMessage.valueOf(args);
			case "RotationReached":
				return RotationReachedMessage.valueOf(args);
			case "WarningMessage":
				return WarningMessage.valueOf(args);
			case "YourColour":
				return YourColourMessage.valueOf(args);
			case "YourName":
				return YourNameMessage.valueOf(args);
			default:
				return UnknownMessage.valueOf(value);
			}

		} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
			return UnknownMessage.valueOf(value);
		}
	}

}