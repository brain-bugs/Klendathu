package de.hbt.hackathon.rtb.agent;

import de.hbt.hackathon.rtb.protocol.Reader;
import de.hbt.hackathon.rtb.protocol.message.input.InitializeMessage;
import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;

public class Communicator implements Runnable {
	private Reader reader = new Reader();
	private volatile boolean gameOver = false;
	

	@Override
	public void run() {
		
		while(!gameOver) {
		try {
			InputMessage message = reader.read();
			if(message instanceof InitializeMessage) {
//				bot.processInitialize();
			}
			
			Thread.sleep(10);
		} catch (InterruptedException e) {
			return;
		}}
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

}
