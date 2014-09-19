package de.hbt.hackathon.rtb.simplebot;

import java.util.List;

import de.hbt.hackathon.rtb.agent.AbstractStrategy;
import de.hbt.hackathon.rtb.base.command.AbstractCommand;
import de.hbt.hackathon.rtb.protocol.Writer;
import de.hbt.hackathon.rtb.protocol.message.output.ColourMessage;
import de.hbt.hackathon.rtb.protocol.message.output.NameMessage;

public class SimpleBot extends AbstractStrategy {
	
	public SimpleBot (Writer writer) {
		super(writer);
	}

	@Override
	public void processInitialize() {
		writer.write(new NameMessage("SimpleBot"));
		writer.write(new ColourMessage("cccccc", "000000"));
	}

	@Override
	public List<AbstractCommand> process() {
		// TODO Auto-generated method stub
		return null;
	}

}
