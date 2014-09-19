package de.hbt.hackathon.rtb.agent;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hbt.hackathon.rtb.protocol.Reader;
import de.hbt.hackathon.rtb.protocol.Writer;
import de.hbt.hackathon.rtb.protocol.message.input.DeadMessage;
import de.hbt.hackathon.rtb.protocol.message.input.ExitRobotMessage;
import de.hbt.hackathon.rtb.protocol.message.input.GameFinishesMessage;
import de.hbt.hackathon.rtb.protocol.message.input.GameStartsMessage;
import de.hbt.hackathon.rtb.protocol.message.input.InitializeMessage;
import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;
import de.hbt.hackathon.rtb.protocol.message.input.UnknownMessage;
import de.hbt.hackathon.rtb.protocol.message.output.OutputMessage;

public class Communicator implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Communicator.class);

	private final Reader reader = new Reader();
	private final Writer writer = new Writer();
	private final List<CommunicationListener> listeners = new ArrayList<CommunicationListener>();

	private volatile boolean gameOver = false;

	public void addListener(CommunicationListener listener) {
		listeners.add(listener);
	}

	@Override
	public void run() {
		LOGGER.info("Communicator started.");

		while (!gameOver) {
			try {
				InputMessage message = reader.read();
				LOGGER.info("Communicator read message: " + message + ".");

				if (message instanceof InitializeMessage) {
					for (CommunicationListener listener : listeners) {
						listener.onGameInitialized(((InitializeMessage) message).isFirst());
					}
				} else if (message instanceof GameStartsMessage) {
					for (CommunicationListener listener : listeners) {
						listener.onGameStarted();
					}
				} else if (message instanceof DeadMessage) {
					for (CommunicationListener listener : listeners) {
						listener.onRobotDied();
					}
				} else if (message instanceof GameFinishesMessage) {
					for (CommunicationListener listener : listeners) {
						listener.onGameFinished();
					}
				} else if (message instanceof ExitRobotMessage) {
					for (CommunicationListener listener : listeners) {
						listener.onExitProgram();
					}
				} else if (message instanceof UnknownMessage) {
					System.err.println("Unknown message received: " + ((UnknownMessage) message).getInputValue());
				} else {
					throw new RuntimeException("Unexpected message received: " + message);
				}

				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
		}

		LOGGER.info("Communicator finished.");
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public void sendOutputMessage(OutputMessage outputMessage) {
		writer.write(outputMessage);
	}
}
