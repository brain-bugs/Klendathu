package de.hbt.hackathon.rtb.protocol.message.input;

public abstract class InputMessage {
	
	public static InputMessage valueOf(String value) {
		String[] args = value.split(" ");
		
		try {
			if(args.equals("Radar")) {
				return RadarMessage.valueOf(args);
			}
		} catch (IllegalArgumentException e) {
			return UnknownMessage.valueOf(value);
		}
		return UnknownMessage.valueOf(value);
	}

}

/*

if (s.equals("Radar")) return RADAR;
if (s.equals("Info")) return INFO;
if (s.equals("RobotInfo")) return ROBOT_INFO;
if (s.equals("Collision")) return COLLISION;
if (s.equals("Energy")) return ENERGY;
if (s.equals("RotationReached")) return ROTATION_REACHED;
if (s.equals("RobotsLeft")) return ROBOTS_LEFT;
if (s.equals("GameOption")) return GAME_OPTION;
if (s.equals("GameStarts")) return GAME_STARTS;
if (s.equals("Initialize")) return INITIALIZE;
if (s.equals("Warning")) return WARNING;
if (s.equals("Dead"))  return DEAD;
if (s.equals("GameFinishes"))  return GAME_FINISHES;
if (s.equals("ExitRobot"))  return EXIT_ROBOT;
if (s.equals("YourName"))  return YOUR_NAME;
if (s.equals("YourColour"))  return YOUR_COLOUR;
// this line was added by Johannes Nicolai 
if (s.equals("Coordinates"))  return COORDINATES;

*/