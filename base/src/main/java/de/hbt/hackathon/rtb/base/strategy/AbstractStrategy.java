package de.hbt.hackathon.rtb.base.strategy;

import java.util.List;

import de.hbt.hackathon.rtb.base.command.AbstractCommand;

public abstract class AbstractStrategy {

	public abstract List<AbstractCommand> process();
}
