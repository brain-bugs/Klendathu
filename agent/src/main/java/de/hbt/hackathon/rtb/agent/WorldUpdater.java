package de.hbt.hackathon.rtb.agent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.vividsolutions.jts.geom.Envelope;

import de.hbt.hackathon.rtb.base.geo.GeometricSet;
import de.hbt.hackathon.rtb.base.message.input.CoordinatesMessage;
import de.hbt.hackathon.rtb.base.message.input.EnergyMessage;
import de.hbt.hackathon.rtb.base.message.input.InfoMessage;
import de.hbt.hackathon.rtb.base.message.input.InputMessage;
import de.hbt.hackathon.rtb.base.message.input.RadarMessage;
import de.hbt.hackathon.rtb.base.message.input.RobotInfoMessage;
import de.hbt.hackathon.rtb.base.type.Coordinate;
import de.hbt.hackathon.rtb.base.type.ObjectType;
import de.hbt.hackathon.rtb.world.Cookie;
import de.hbt.hackathon.rtb.world.GameObject;
import de.hbt.hackathon.rtb.world.Mine;
import de.hbt.hackathon.rtb.world.MyRobot;
import de.hbt.hackathon.rtb.world.Robot;
import de.hbt.hackathon.rtb.world.Shot;
import de.hbt.hackathon.rtb.world.Wall;
import de.hbt.hackathon.rtb.world.World;

public class WorldUpdater implements CommunicationListener {

	private final World world;

	private RadarMessage radarMessage;

	private RobotInfoMessage robotInfoMessage;

	private final JPanel debugPanel = new JPanel() {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(java.awt.Graphics g) {
			g.clearRect(0, 0, 500, 500);
			drawWorld(g);
		};
	};

	private InfoMessage infoMessage;

	void drawWorld(Graphics g) {
		MyRobot myRobot = world.getMyRobot();
		if (myRobot == null)
			return;

		Coordinate currentPosition = myRobot.getCurrentPosition();
		double x = currentPosition.getX();
		double y = currentPosition.getY();
		Envelope boundingBox = world.getBoundingBox();
		double minX = boundingBox.getMinX();
		double minY = boundingBox.getMinY();

		double offsetX = 0;
		if (minX < 0)
			offsetX = -minX;

		double offsetY = 0;
		if (minY < 0)
			offsetY = -minY;

		double height = boundingBox.getHeight();
		double width = boundingBox.getWidth();
		double scaleX = 500 / width;
		double scaleY = 500 / height;

		g.setColor(Color.ORANGE);
		drawPoint(g, x, y, offsetX, offsetY, scaleX, scaleY);

		drawGameObjects(g, offsetX, offsetY, scaleX, scaleY, world.getWalls(), Color.BLACK);
		drawGameObjects(g, offsetX, offsetY, scaleX, scaleY, world.getRobots(), Color.RED);
		drawGameObjects(g, offsetX, offsetY, scaleX, scaleY, world.getShots(), Color.YELLOW);
		drawGameObjects(g, offsetX, offsetY, scaleX, scaleY, world.getMines(), Color.CYAN);
	}

	private void drawGameObjects(Graphics g, double offsetX, double offsetY, double scaleX, double scaleY, GeometricSet<? extends GameObject> objects, Color color) {
		for (GameObject object : objects) {
			Coordinate c = object.getCurrentPosition();
			g.setColor(color);
			drawPoint(g, c.getX(), c.getY(), offsetX, offsetY, scaleX, scaleY);
		}
	}

	private void drawPoint(Graphics g, double x, double y, double offsetX, double offsetY, double scaleX, double scaleY) {
		g.fillOval((int) ((offsetX + x) * scaleX), 500 - (int) ((offsetY + y) * scaleY), 2, 2);
	}

	public WorldUpdater(World world) {
		this.world = world;
		JFrame debugFrame = new JFrame("Debug");
		debugFrame.setContentPane(debugPanel);
		debugPanel.setPreferredSize(new Dimension(500, 500));
		debugFrame.pack();
		debugFrame.setVisible(true);
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
			// wait for energy message
		} else if (message instanceof RobotInfoMessage) {
			robotInfoMessage = (RobotInfoMessage) message;
			// wait for energy message
		} else if (message instanceof InfoMessage) {
			infoMessage = (InfoMessage) message;
			// wait for energy message
		} else if (message instanceof EnergyMessage) {
			int myEnergyLevel = ((EnergyMessage) message).getEnergyLevel();
			myRobot.setEnergyLevel(myEnergyLevel);

			ObjectType objectType = radarMessage.getObjectType();
			double radarDistance = radarMessage.getRadarDistance();

			double rotationAngle = myRobot.getRotationAngle();
			double radarAngle = radarMessage.getRadarAngle() + rotationAngle;

			Coordinate currentPosition = myRobot.getCurrentPosition();
			double cx = currentPosition.getX();
			double cy = currentPosition.getY();

			double dx = radarDistance * Math.cos(radarAngle);
			double dy = radarDistance * Math.sin(radarAngle);
			double timeStamp = infoMessage.getTime();
			Coordinate coordinate = new Coordinate(cx + dx, cy + dy, timeStamp);
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
				double energyLevel = robotInfoMessage.getEnergyLevel();
				boolean teamMate = robotInfoMessage.isTeamMate();
				Robot robot = new Robot(coordinate, teamMate);
				robot.setEnergyLevel(energyLevel);
				world.addRobot(robot);
				break;
			default:
				break;
			}
		}
		debugPanel.repaint(50);
	}
}
