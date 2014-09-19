package de.hbt.hackathon.rtb.agent;

import java.util.List;
import java.util.TimerTask;

import de.hbt.hackathon.rtb.base.command.AbstractCommand;
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;

public class AgentTimerTask extends TimerTask {

	private AbstractStrategy strategy;

	public void setStrategy(AbstractStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void run() {
		List<AbstractCommand> commands = strategy.process();
	}

}
