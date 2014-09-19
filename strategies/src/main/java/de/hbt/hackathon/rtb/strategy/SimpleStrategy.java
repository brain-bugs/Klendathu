package de.hbt.hackathon.rtb.strategy;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import de.hbt.hackathon.rtb.base.message.AngleType;
import de.hbt.hackathon.rtb.base.message.output.AccelerateMessage;
import de.hbt.hackathon.rtb.base.message.output.OutputMessage;
import de.hbt.hackathon.rtb.base.message.output.RotateAmountMessage;
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;

public class SimpleStrategy extends AbstractStrategy {

	@Override
	public List<OutputMessage> process() {
		List<OutputMessage> messages =  new ArrayList<OutputMessage>();
		RotateAmountMessage rm = new RotateAmountMessage(EnumSet.of(AngleType.ROBOT), 1.0, 1.0);
		messages.add(rm);
		AccelerateMessage am = new AccelerateMessage(1.0);
		messages.add(am);
		return messages;
	}

}
