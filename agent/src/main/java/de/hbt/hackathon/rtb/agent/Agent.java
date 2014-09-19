package de.hbt.hackathon.rtb.agent;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hbt.hackathon.rtb.base.command.AbstractCommand;
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;
import de.hbt.hackathon.rtb.protocol.message.output.ColourMessage;
import de.hbt.hackathon.rtb.protocol.message.output.NameMessage;
import de.hbt.hackathon.rtb.strategy.SimpleStrategy;

public class Agent implements CommunicationListener {

	// technical configuration
	private static final int UPDATES_PER_SECOND = 10;

	// game configuration
	private static final String NAME = "Foo";
	private static final String HOME_COLOUR = "FFFFFF";
	private static final String AWAY_COLOUR = "FF7777";

	private final Communicator communicator;
	private final AbstractStrategy strategy;
	private final Timer gameTimer;

	public Agent(Communicator communicator, AbstractStrategy strategy) {
		this.communicator = communicator;
		this.strategy = strategy;
		this.gameTimer = new Timer();
	}

	public static void main(String[] args) throws IOException {
		Communicator communicator = new Communicator();
		AbstractStrategy strategy = new SimpleStrategy();
		Agent agent = new Agent(communicator, strategy);

		Thread communicatorThread = new Thread(communicator);
		communicator.addListener(agent);
		communicatorThread.start();

	}

	public void onTimer() {
		List<AbstractCommand> commands = strategy.process();
	}

	private void startGameTimer() {
		gameTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				Agent.this.onTimer();
			}

		}, 0L, 1000L / UPDATES_PER_SECOND);
	}

	private void stopGameTimer() {
		gameTimer.cancel();
	}

	@Override
	public void onGameInitialized(boolean first) {
		if (first) {
			communicator.sendOutputMessage(new NameMessage(NAME));
			communicator.sendOutputMessage(new ColourMessage(HOME_COLOUR, AWAY_COLOUR));
		}
	}

	@Override
	public void onGameStarted() {
		startGameTimer();
	}

	@Override
	public void onGameFinished() {
		stopGameTimer();
	}

	@Override
	public void onRobotDied() {
		stopGameTimer();
	}

}
