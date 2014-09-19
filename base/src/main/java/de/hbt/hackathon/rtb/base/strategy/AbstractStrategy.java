package de.hbt.hackathon.rtb.base.strategy;

import java.util.List;

import de.hbt.hackathon.rtb.base.message.output.OutputMessage;

public abstract class AbstractStrategy {
	
	public abstract String getName();

	public abstract List<OutputMessage> process();
}
