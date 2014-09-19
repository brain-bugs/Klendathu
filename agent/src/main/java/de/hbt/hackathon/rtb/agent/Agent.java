package de.hbt.hackathon.rtb.agent;

import java.io.IOException;

import de.hbt.hackathon.rtb.protocol.Reader;
import de.hbt.hackathon.rtb.protocol.Writer;
import de.hbt.hackathon.rtb.protocol.message.input.InitializeMessage;
import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;
import de.hbt.hackathon.rtb.protocol.message.output.ColourMessage;
import de.hbt.hackathon.rtb.protocol.message.output.NameMessage;
import de.hbt.hackathon.rtb.simplebot.SimpleBot;

public class Agent {
	
	public static void main(String[] args) throws IOException {
		Writer writer = new Writer();
		Communicator communicator = new Communicator();
		Thread communcatorThread = new Thread(communicator);
		communcatorThread.start();
		
		
		AbstractStrategy bot = new SimpleBot(writer);
		
		whileloop: do {
			
			
		} while (true);
	}

}
