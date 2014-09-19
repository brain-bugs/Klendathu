package de.hbt.hackathon.rtb.simplebot;

import java.io.IOException;

import de.hbt.hackathon.rtb.protocol.Reader;
import de.hbt.hackathon.rtb.protocol.Writer;
import de.hbt.hackathon.rtb.protocol.message.input.InitializeMessage;
import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;
import de.hbt.hackathon.rtb.protocol.message.output.ColourMessage;
import de.hbt.hackathon.rtb.protocol.message.output.NameMessage;

public class SimpleBot {
	
	public static void main(String[] args) throws IOException {
		Reader reader = new Reader();
		Writer writer = new Writer();
		
		whileloop: do {
			
			try {
				InputMessage message = reader.read();
				if(message instanceof InitializeMessage) {
					writer.write(new NameMessage("SimpleBot"));
					writer.write(new ColourMessage("cccccc", "000000"));
				}
				
				Thread.sleep(500);
			} catch (InterruptedException e) {
				break whileloop;
			}
			
		} while (true);
	}

}
