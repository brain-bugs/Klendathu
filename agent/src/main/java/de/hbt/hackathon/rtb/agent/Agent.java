package de.hbt.hackathon.rtb.agent;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hbt.hackathon.rtb.base.command.AbstractCommand;
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;
import de.hbt.hackathon.rtb.protocol.message.input.DeadMessage;
import de.hbt.hackathon.rtb.protocol.message.input.EnergyMessage;
import de.hbt.hackathon.rtb.protocol.message.input.ExitRobotMessage;
import de.hbt.hackathon.rtb.protocol.message.input.GameFinishesMessage;
import de.hbt.hackathon.rtb.protocol.message.input.GameStartsMessage;
import de.hbt.hackathon.rtb.protocol.message.input.InitializeMessage;
import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;
import de.hbt.hackathon.rtb.protocol.message.output.ColourMessage;
import de.hbt.hackathon.rtb.protocol.message.output.NameMessage;
import de.hbt.hackathon.rtb.strategy.SimpleStrategy;

public class Agent implements CommunicationListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(Agent.class);

	// technical configuration
	private static final int UPDATES_PER_SECOND = 10;

	// game configuration
	private static final String NAME = "Brainbug";
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
	public void onMessage(InputMessage message) {
		if (message instanceof InitializeMessage) {
			onGameInitialized(((InitializeMessage) message).isFirst());
		} else if (message instanceof GameStartsMessage) {
			onGameStarted();
		} else if (message instanceof EnergyMessage) {
			onEnergyLevel(((EnergyMessage) message).getEnergyLevel());
		} else if (message instanceof DeadMessage) {
			onRobotDied();
		} else if (message instanceof GameFinishesMessage) {
			onGameFinished();
		} else if (message instanceof ExitRobotMessage) {
			onExitProgram();
		}
	}

	private void onGameInitialized(boolean first) {
		if (first) {
			LOGGER.info("Game initialized for the first time, sending name " + NAME + " and colours.");
			communicator.sendOutputMessage(new NameMessage(NAME));
			communicator.sendOutputMessage(new ColourMessage(HOME_COLOUR, AWAY_COLOUR));
		}
	}

	private void onGameStarted() {
		LOGGER.info("Game started.");
		startGameTimer();
	}

	private void onGameFinished() {
		LOGGER.info("Game finished.");
		stopGameTimer();
	}

	private void onRobotDied() {
		LOGGER.info("Our robot died :-(");
		stopGameTimer();
	}

	private void onExitProgram() {
		LOGGER.info("Program exiting.");
		communicator.setGameOver(true);
	}

	private void onEnergyLevel(int energyLevel) {
		LOGGER.info("Received energy level: " + energyLevel);
	}
}
