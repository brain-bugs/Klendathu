package de.hbt.hackathon.rtb.world;

import de.hbt.hackathon.rtb.base.geo.GeometricSet;
import de.hbt.hackathon.rtb.base.geo.JTSQuadTreeAdapter;

public class World {

	private Arena arena;
	private GeometricSet<Cookie> cookies;
	private GeometricSet<Mine> mines;
	private GeometricSet<Robot> robots;
	private MyRobot myRobot;
	private GeometricSet<Shot> shots;
	private GeometricSet<Wall> walls;

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
		cookies.add(cookie);
	}

	public void addMine(Mine mine) {
		mines.add(mine);
	}

	public void addWall(Wall wall) {
		walls.add(wall);
	}

	public void addRobot(Robot robot) {
		robots.add(robot);
	}

	public void addShot(Shot shot) {
		shots.add(shot);
	}

}
