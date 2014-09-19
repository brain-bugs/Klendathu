package de.hbt.hackathon.rtb.protocol.message.input;

public class InfoMessage {
	
	private final double time;
	private final double speed;
	private final double cannonAngle;
	
	private InfoMessage(double time, double speed, double cannonAngle) {
		this.time = time;
		this.speed = speed;
		this.cannonAngle = cannonAngle;
	}

	public double getTime() {
		return time;
	}

	public double getSpeed() {
		return speed;
	}

	public double getCannonAngle() {
		return cannonAngle;
	}
	
	public InfoMessage valueOf(String[] args) {
		double time = Double.parseDouble(args[1]);
		double speed = Double.parseDouble(args[2]);
		double cannonAngle = Double.parseDouble(args[3]);
		return new InfoMessage(time, speed, cannonAngle);
	}
	

}
