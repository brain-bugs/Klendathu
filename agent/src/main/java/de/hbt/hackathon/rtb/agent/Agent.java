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
		Reader reader = new Reader();
		Writer writer = new Writer();
		
		AbstractStrategy bot = new SimpleBot(writer);
		
		whileloop: do {
			
			try {
				InputMessage message = reader.read();
				if(message instanceof InitializeMessage) {
					bot.processInitialize();
				}
				
				Thread.sleep(500);
			} catch (InterruptedException e) {
				break whileloop;
			}
			
		} while (true);
	}

}
