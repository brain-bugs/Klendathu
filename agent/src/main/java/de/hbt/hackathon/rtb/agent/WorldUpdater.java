package de.hbt.hackathon.rtb.agent;

import de.hbt.hackathon.rtb.base.Coordinate;
import de.hbt.hackathon.rtb.protocol.message.input.CoordinatesMessage;
import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;
import de.hbt.hackathon.rtb.protocol.message.input.RadarMessage;
import de.hbt.hackathon.rtb.protocol.type.ObjectType;
import de.hbt.hackathon.rtb.world.Cookie;
import de.hbt.hackathon.rtb.world.Mine;
import de.hbt.hackathon.rtb.world.MyRobot;
import de.hbt.hackathon.rtb.world.Robot;
import de.hbt.hackathon.rtb.world.Shot;
import de.hbt.hackathon.rtb.world.Wall;
import de.hbt.hackathon.rtb.world.World;

public class WorldUpdater implements CommunicationListener {

	private World world = new World();

	@Override
	public void onMessage(InputMessage message) {
		final World world = new World();
		MyRobot myRobot = this.world.getMyRobot();
		if (message instanceof CoordinatesMessage) {
			CoordinatesMessage coordinatesMessage = (CoordinatesMessage) message;
			Coordinate coordinate = coordinatesMessage.getCoordinate();
			if (myRobot == null) {
				myRobot = new MyRobot(coordinate);
			} else {
				myRobot.setCurrentPosition(coordinate);
			}

			double robotAngle = coordinatesMessage.getRotationAngle();
			myRobot.setRotationAngle(robotAngle);

			world.setMyRobot(myRobot);
		} else if (message instanceof RadarMessage) {
			RadarMessage radarMessage = (RadarMessage) message;
			ObjectType objectType = radarMessage.getObjectType();
			double radarAngle = radarMessage.getRadarAngle();
			double radarDistance = radarMessage.getRadarDistance();
			if (myRobot != null) {
				Coordinate currentPosition = myRobot.getCurrentPosition();
				double x = currentPosition.getX();
				double y = currentPosition.getY();
				Coordinate coordinate = null;
				switch (objectType) {
				case COOKIE:
					world.addCookie(new Cookie(coordinate));
					break;
				case MINE:
					world.addMine(new Mine(coordinate));
					break;
				case ROBOT:
					world.addRobot(new Robot(coordinate, false/* TODO */));
					break;
				case SHOT:
					world.addShot(new Shot(coordinate));
					break;
				case WALL:
					world.addWall(new Wall(coordinate));
					break;
				default:
					break;
				}
			}
		}
		this.world = world;
	}
}
