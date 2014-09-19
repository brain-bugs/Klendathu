package de.hbt.hackathon.rtb.agent;

import de.hbt.hackathon.rtb.base.message.input.CoordinatesMessage;
import de.hbt.hackathon.rtb.base.message.input.InfoMessage;
import de.hbt.hackathon.rtb.base.message.input.InputMessage;
import de.hbt.hackathon.rtb.base.message.input.RadarMessage;
import de.hbt.hackathon.rtb.base.message.input.RobotInfoMessage;
import de.hbt.hackathon.rtb.base.type.Coordinate;
import de.hbt.hackathon.rtb.base.type.ObjectType;
import de.hbt.hackathon.rtb.world.Cookie;
import de.hbt.hackathon.rtb.world.Mine;
import de.hbt.hackathon.rtb.world.MyRobot;
import de.hbt.hackathon.rtb.world.Robot;
import de.hbt.hackathon.rtb.world.Shot;
import de.hbt.hackathon.rtb.world.Wall;
import de.hbt.hackathon.rtb.world.World;

public class WorldUpdater implements CommunicationListener {

	private final World world;

	private RadarMessage radarMessage;

	private Coordinate coordinate;

	public WorldUpdater(World world) {
		this.world = world;
	}

	@Override
	public void onMessage(InputMessage message) {
		MyRobot myRobot = this.world.getMyRobot();
		if (message instanceof CoordinatesMessage) {
			CoordinatesMessage coordinatesMessage = (CoordinatesMessage) message;
			Coordinate coordinate = coordinatesMessage.getCoordinate();
			if (myRobot == null) {
				myRobot = new MyRobot(coordinate);
				world.setMyRobot(myRobot);
			} else {
				myRobot.setCurrentPosition(coordinate);
			}

			double robotAngle = coordinatesMessage.getRobotAngle();
			myRobot.setRotationAngle(robotAngle);
		} else if (message instanceof RadarMessage) {
			radarMessage = (RadarMessage) message;
			// wait for info message
		} else if (message instanceof InfoMessage) {
			ObjectType objectType = radarMessage.getObjectType();
			double radarAngle = radarMessage.getRadarAngle();
			double radarDistance = radarMessage.getRadarDistance();
			if (myRobot == null) {

			}
			Coordinate currentPosition = myRobot.getCurrentPosition();
			double cx = currentPosition.getX();
			double cy = currentPosition.getY();

			double dx = radarDistance * Math.cos(radarAngle);
			double dy = radarDistance * Math.sin(radarAngle);
			double timeStamp = ((InfoMessage) message).getTime();
			coordinate = new Coordinate(cx + dx, cy + dy, timeStamp);
			switch (objectType) {
			case COOKIE:
				world.addCookie(new Cookie(coordinate));
				break;
			case MINE:
				world.addMine(new Mine(coordinate));
				break;
			case SHOT:
				world.addShot(new Shot(coordinate));
				break;
			case WALL:
				world.addWall(new Wall(coordinate));
				break;
			case ROBOT:
				// wait for robot info message
				break;
			default:
				break;
			}
		} else if (message instanceof RobotInfoMessage) {
			RobotInfoMessage robotInfoMessage = (RobotInfoMessage) message;
			double energyLevel = robotInfoMessage.getEnergyLevel();
			boolean teamMate = robotInfoMessage.isTeamMate();
			Robot robot = new Robot(coordinate, teamMate);
			robot.setEnergyLevel(energyLevel);
			world.addRobot(robot);
		}
	}
}
