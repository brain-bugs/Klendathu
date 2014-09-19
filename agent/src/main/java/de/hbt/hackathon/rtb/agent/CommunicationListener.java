package de.hbt.hackathon.rtb.agent;

public interface CommunicationListener {

	void onGameInitialized(boolean first);

	void onGameStarted();

	void onGameFinished();

	void onRobotDied();

}
