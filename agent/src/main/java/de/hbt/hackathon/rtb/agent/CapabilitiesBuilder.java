package de.hbt.hackathon.rtb.agent;

import de.hbt.hackathon.rtb.base.message.input.GameOptionMessage;
import de.hbt.hackathon.rtb.base.message.input.InputMessage;
import de.hbt.hackathon.rtb.base.type.Capabilities;
import de.hbt.hackathon.rtb.base.type.GameOptionType;

public class CapabilitiesBuilder implements CommunicationListener {

	private Capabilities capabilities;

	public CapabilitiesBuilder() {
		capabilities = new Capabilities();
	}

	public Capabilities getCapabilities() {
		return capabilities;
	}

	@Override
	public void onMessage(InputMessage message) {
		if (message instanceof GameOptionMessage) {
			GameOptionMessage gameOptionMessage = (GameOptionMessage) message;
			GameOptionType type = gameOptionMessage.getGameOptionType();
			double value = gameOptionMessage.getValue();
			if (type == GameOptionType.ROBOT_MAX_ROTATE) {
				capabilities.setMaxRotate(value);
			} else if (type == GameOptionType.ROBOT_CANNON_MAX_ROTATE) {
				capabilities.setMaxCannonRotate(value);
			} else if (type == GameOptionType.ROBOT_RADAR_MAX_ROTATE) {
				capabilities.setMaxRadarRotate(value);
			} else if (type == GameOptionType.ROBOT_MAX_ACCELERATION) {
				capabilities.setMaxAcceleration(value);
			} else if (type == GameOptionType.ROBOT_MIN_ACCELERATION) {
				capabilities.setMinAcceleration(value);
			} else if (type == GameOptionType.ROBOT_START_ENERGY) {
				capabilities.setStartEnergy(value);
			} else if (type == GameOptionType.ROBOT_MAX_ENERGY) {
				capabilities.setMaxEnergy(value);
			} else if (type == GameOptionType.ROBOT_ENERGY_LEVELS) {
				capabilities.setEnergyLevels(value);
			} else if (type == GameOptionType.SHOT_SPEED) {
				capabilities.setShotSpeed(value);
			} else if (type == GameOptionType.SHOT_MIN_ENERGY) {
				capabilities.setMinShotEnergy(value);
			} else if (type == GameOptionType.SHOT_MAX_ENERGY) {
				capabilities.setMaxShotEnergy(value);
			} else if (type == GameOptionType.SHOT_ENERGY_INCREASE_SPEED) {
				capabilities.setShotEnergyIncreaseSpeed(value);
			} else if (type == GameOptionType.TIMEOUT) {
				capabilities.setTimeout(value);
			}
		}
	}
}
