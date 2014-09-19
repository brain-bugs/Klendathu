package de.hbt.hackathon.rtb.base.strategy;

import java.util.List;

import de.hbt.hackathon.rtb.base.message.output.OutputMessage;
import de.hbt.hackathon.rtb.base.type.Capabilities;

public abstract class AbstractStrategy {

	private Capabilities capabilities;

	public abstract String getName();

	public abstract List<OutputMessage> process();

	public Capabilities getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(Capabilities capabilities) {
		this.capabilities = capabilities;
	}
}
