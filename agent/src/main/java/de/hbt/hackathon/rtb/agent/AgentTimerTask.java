package de.hbt.hackathon.rtb.agent;

import java.util.List;
import java.util.TimerTask;

import de.hbt.hackathon.rtb.base.command.AbstractCommand;

public class AgentTimerTask extends TimerTask {
	
	private AbstractStrategy strategy;
	
	public AgentTimerTask(AbstractStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void run() {
		List<AbstractCommand> commands = strategy.process();
	}

}
