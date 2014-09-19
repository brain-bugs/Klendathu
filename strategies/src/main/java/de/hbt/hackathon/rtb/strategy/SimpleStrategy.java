package de.hbt.hackathon.rtb.strategy;

import java.util.ArrayList;
import java.util.List;

import de.hbt.hackathon.rtb.base.command.AbstractCommand;
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;

public class SimpleStrategy extends AbstractStrategy {

	@Override
	public List<AbstractCommand> process() {
		return new ArrayList<AbstractCommand>();
	}

}
