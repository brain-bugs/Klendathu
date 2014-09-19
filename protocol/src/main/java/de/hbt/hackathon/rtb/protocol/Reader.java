package de.hbt.hackathon.rtb.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;

public class Reader {

	private final BufferedReader br;
	
	public Reader() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in, "ASCII"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Could not initialize Reader, encoding ASCII not supported! WTF!?!", e);
		}
	}

	// non-blocking
	public boolean ready() throws IOException {
		return br.ready();
	}

	// blocking
	public InputMessage read() {
		try {
			String input = br.readLine();
			return InputMessage.valueOf(input);
		} catch (IOException e) {
			throw new RuntimeException("Could not read from input stream.", e);
		} 
	}

}
