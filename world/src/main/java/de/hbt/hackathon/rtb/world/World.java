package de.hbt.hackathon.rtb.world;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hbt.hackathon.rtb.base.geo.GeometricSet;
import de.hbt.hackathon.rtb.base.geo.JTSQuadTreeAdapter;

public class World {

	private static final Logger LOG = LoggerFactory.getLogger(World.class);

	private Arena arena;
	private final GeometricSet<Cookie> cookies;
	private final GeometricSet<Mine> mines;
	private final GeometricSet<Robot> robots;
	private MyRobot myRobot;
	private final GeometricSet<Shot> shots;
	private final GeometricSet<Wall> walls;

	public World() {
		cookies = new JTSQuadTreeAdapter<Cookie>();
		mines = new JTSQuadTreeAdapter<Mine>();
		robots = new JTSQuadTreeAdapter<Robot>();
		shots = new JTSQuadTreeAdapter<Shot>();
		walls = new JTSQuadTreeAdapter<Wall>();
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public Arena getArena() {
		return this.arena;
	}

	public void clear() {
		robots.clear();
		shots.clear();
		walls.clear();
		cookies.clear();
		mines.clear();
	}

	public void setMyRobot(MyRobot myRobot) {
		this.myRobot = myRobot;
	}

	public MyRobot getMyRobot() {
		return this.myRobot;
	}

	public void addCookie(Cookie cookie) {
		LOG.debug("Added " + cookie);
		cookies.add(cookie);
	}

	public void addMine(Mine mine) {
		LOG.debug("Added " + mine);
		mines.add(mine);
	}

	public void addWall(Wall wall) {
		LOG.debug("Added " + wall);
		walls.add(wall);
	}

	public void addRobot(Robot robot) {
		LOG.debug("Added " + robot);
		robots.add(robot);
	}

	public void addShot(Shot shot) {
		LOG.debug("Added " + shot);
		shots.add(shot);
	}

}
