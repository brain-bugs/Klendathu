package de.hbt.hackathon.rtb.agent;

import de.hbt.hackathon.rtb.base.message.input.InputMessage;

public interface CommunicationListener {

	void onMessage(InputMessage message); 

}
