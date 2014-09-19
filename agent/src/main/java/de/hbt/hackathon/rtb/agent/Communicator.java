package de.hbt.hackathon.rtb.agent;

import java.util.ArrayList;
import java.util.List;

import de.hbt.hackathon.rtb.protocol.Reader;
import de.hbt.hackathon.rtb.protocol.Writer;
import de.hbt.hackathon.rtb.protocol.message.input.InitializeMessage;
import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;

public class Communicator implements Runnable {

	private final Reader reader = new Reader();
	private final Writer writer = new Writer();
	private final List<CommunicationListener> listeners = new ArrayList<>();

	private volatile boolean gameOver = false;

	public void addListener(CommunicationListener listener) {
		listeners.add(listener);
	}

	@Override
	public void run() {

		while (!gameOver) {
			try {
				InputMessage message = reader.read();
				if (message instanceof InitializeMessage) {
					for (CommunicationListener listener : listeners) {
						listener.onGameInitialized(((InitializeMessage) message).isFirst());
					}
				}

				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

}
