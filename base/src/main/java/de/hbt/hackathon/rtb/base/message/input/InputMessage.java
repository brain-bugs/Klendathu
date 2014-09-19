package de.hbt.hackathon.rtb.base.message.input;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class InputMessage {

	public static InputMessage valueOf(String value) {
		String[] args = value.split(" ");

		try {
			String messageType = args[0];
			if ("Collision".equalsIgnoreCase(messageType)) {
				return CollisionMessage.valueOf(args);
			}
			if ("Coordinates".equalsIgnoreCase(messageType)) {
				return CoordinatesMessage.valueOf(args);
			}
			if ("Dead".equalsIgnoreCase(messageType)) {
				return DeadMessage.valueOf(args);
			}
			if ("Energy".equalsIgnoreCase(messageType)) {
				return EnergyMessage.valueOf(args);
			}
			if ("ExitRobot".equalsIgnoreCase(messageType)) {
				return ExitRobotMessage.valueOf(args);
			}
			if ("GameStarts".equalsIgnoreCase(messageType)) {
				return GameStartsMessage.valueOf(args);
			}
			if ("GameFinishes".equalsIgnoreCase(messageType)) {
				return GameFinishesMessage.valueOf(args);
			}
			if ("GameOption".equalsIgnoreCase(messageType)) {
				return GameOptionMessage.valueOf(args);
			}
			if ("Info".equalsIgnoreCase(messageType)) {
				return InfoMessage.valueOf(args);
			}
			if ("Initialize".equalsIgnoreCase(messageType)) {
				return InitializeMessage.valueOf(args);
			}
			if ("Radar".equalsIgnoreCase(messageType)) {
				return RadarMessage.valueOf(args);
			}
			if ("RobotInfo".equalsIgnoreCase(messageType)) {
				return RobotInfoMessage.valueOf(args);
			}
			if ("RobotsLeft".equalsIgnoreCase(messageType)) {
				return RobotsLeftMessage.valueOf(args);
			}
			if ("RotationReached".equalsIgnoreCase(messageType)) {
				return RotationReachedMessage.valueOf(args);
			}
			if ("WarningMessage".equalsIgnoreCase(messageType)) {
				return WarningMessage.valueOf(args);
			}
			if ("YourColour".equalsIgnoreCase(messageType)) {
				return YourColourMessage.valueOf(args);
			}
			if ("YourName".equalsIgnoreCase(messageType)) {
				return YourNameMessage.valueOf(args);
			}
			return UnknownMessage.valueOf(messageType);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return UnknownMessage.valueOf(value);
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return UnknownMessage.valueOf(value);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}