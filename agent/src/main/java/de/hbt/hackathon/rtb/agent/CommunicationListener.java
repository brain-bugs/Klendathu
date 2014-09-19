package de.hbt.hackathon.rtb.agent;

import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;

public interface CommunicationListener {

	void onMessage(InputMessage message); 

}
