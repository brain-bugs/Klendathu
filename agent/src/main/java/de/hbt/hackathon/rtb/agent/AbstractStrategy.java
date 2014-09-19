package de.hbt.hackathon.rtb.agent;

import java.util.List;

import de.hbt.hackathon.rtb.base.command.AbstractCommand;
import de.hbt.hackathon.rtb.protocol.Writer;

public abstract class AbstractStrategy {
	
	protected final Writer writer;
	
	public AbstractStrategy(Writer writer) {
		this.writer = writer;
	}
	
	public abstract void processInitialize();

	public abstract List<AbstractCommand> process();
}
