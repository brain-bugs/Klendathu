package de.hbt.hackathon.rtb.protocol;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import de.hbt.hackathon.rtb.protocol.message.AngleType;
import de.hbt.hackathon.rtb.protocol.message.output.AccelerateMessage;
import de.hbt.hackathon.rtb.protocol.message.output.BrakeMessage;
import de.hbt.hackathon.rtb.protocol.message.output.ColourMessage;
import de.hbt.hackathon.rtb.protocol.message.output.DebugMessage;
import de.hbt.hackathon.rtb.protocol.message.output.NameMessage;
import de.hbt.hackathon.rtb.protocol.message.output.OutputMessage;
import de.hbt.hackathon.rtb.protocol.message.output.PrintMessage;
import de.hbt.hackathon.rtb.protocol.message.output.RobotOptionMessage;
import de.hbt.hackathon.rtb.protocol.message.output.RotateAmountMessage;
import de.hbt.hackathon.rtb.protocol.message.output.RotateMessage;
import de.hbt.hackathon.rtb.protocol.message.output.RotateToMessage;
import de.hbt.hackathon.rtb.protocol.message.output.ShootMessage;
import de.hbt.hackathon.rtb.protocol.message.output.SweepMessage;

public class Writer {

	private final OutputStreamWriter out;
	private StringBuilder sb = new StringBuilder();

	public Writer() {
		try {
			out = new OutputStreamWriter(System.out, "ASCII");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Could not initialize Writer, encoding ASCII not supported! WTF!?!", e);
		}
	}

	public synchronized void write(OutputMessage message) {
		try {
			sb.setLength(0);
			if (message instanceof NameMessage) {
				NameMessage typedMessage = (NameMessage) message;
				sb.append("Name ").append(typedMessage.getName());
			} else if (message instanceof ColourMessage) {
				ColourMessage typedMessage = (ColourMessage) message;
				sb.append("Colour ").append(typedMessage.getHomeColour()).append(' ').append(typedMessage.getAwayColour());
			} else if (message instanceof AccelerateMessage) {
				AccelerateMessage typedMessage = (AccelerateMessage) message;
				sb.append("Accelerate ").append(typedMessage.getValue());
			} else if (message instanceof BrakeMessage) {
				BrakeMessage typedMessage = (BrakeMessage) message;
				sb.append("Brake ").append(typedMessage.getPortion());
			} else if (message instanceof DebugMessage) {
				DebugMessage typedMessage = (DebugMessage) message;
				sb.append("Debug ").append(typedMessage.getMessage());
			} else if (message instanceof PrintMessage) {
				PrintMessage typedMessage = (PrintMessage) message;
				sb.append("Print ").append(typedMessage.getMessage());
			} else if (message instanceof RotateToMessage) {
				RotateToMessage typedMessage = (RotateToMessage) message;
				int what = getWhatAsInt(typedMessage.getWhat());
				sb.append("RotateTo ").append(what).append(' ').append(typedMessage.getRotationVelocity()).append(' ')
						.append(typedMessage.getEndAngle());
			} else if (message instanceof RotateAmountMessage) {
				RotateAmountMessage typedMessage = (RotateAmountMessage) message;
				int what = getWhatAsInt(typedMessage.getWhat());
				sb.append("RotateAmount ").append(what).append(' ').append(typedMessage.getRotationVelocity()).append(' ')
						.append(typedMessage.getRelativeAngle());
			} else if (message instanceof RotateMessage) {
				RotateMessage typedMessage = (RotateMessage) message;
				int what = getWhatAsInt(typedMessage.getWhat());
				sb.append("Rotate ").append(what).append(' ').append(typedMessage.getRotationVelocity());
			} else if (message instanceof ShootMessage) {
				ShootMessage typedMessage = (ShootMessage) message;
				sb.append("Shoot ").append(typedMessage.getEnergy());
			} else if (message instanceof SweepMessage) {
				SweepMessage typedMessage = (SweepMessage) message;
				sb.append("Sweep ").append(typedMessage.getRotationVelocity()).append(' ').append(typedMessage.getLeftAngle()).append(' ')
						.append(typedMessage.getRightAngle());
			} else if (message instanceof RobotOptionMessage) {
				RobotOptionMessage typedMessage = (RobotOptionMessage) message;
				sb.append("RobotOption ").append(typedMessage.getRobotOptionType().getCode()).append(' ').append(typedMessage.getValue());
			} else {
				throw new RuntimeException("Unknown OutputMessage type: " + message.getClass());
			}
			sb.append('\n');
			out.write(sb.toString());
			out.flush();
		} catch (IOException e) {
			throw new RuntimeException("Could not write to output stream.", e);
		}
	}

	private static int getWhatAsInt(Set<AngleType> angleTypes) {
		int what = 0;
		for (AngleType angleType : angleTypes) {
			what += angleType.getCode();
		}
		return what;
	}
}
