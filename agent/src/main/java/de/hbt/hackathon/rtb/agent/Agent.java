package de.hbt.hackathon.rtb.agent;

import java.io.IOException;
import java.util.Timer;

import de.hbt.hackathon.rtb.strategy.SimpleStrategy;

public class Agent {

	public static void main(String[] args) throws IOException {
		Communicator communicator = new Communicator();
		Thread communcatorThread = new Thread(communicator);
		communcatorThread.start();
		AgentTimerTask agentTimerTask = new AgentTimerTask();
		agentTimerTask.setStrategy(new SimpleStrategy());
		Timer timer = new Timer();
		timer.schedule(agentTimerTask, 100, 50);

	}

}
