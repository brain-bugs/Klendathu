package de.hbt.hackathon.rtb.protocol;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;
import de.hbt.hackathon.rtb.protocol.message.input.YourNameMessage;

public class ReaderTest {
	
	private static InputStream realSystemIn = null;
	private Reader reader;
	
	@BeforeClass
	public static void beforeClass() {
		realSystemIn = System.in;
	}
	
	@AfterClass
	public static void afterClass() {
		System.setIn(realSystemIn);
	}
	
	@Before
	public void init() {
		reader = new Reader();
	}

	@Test
	public void testRead01() throws UnsupportedEncodingException {
		ByteArrayInputStream bin = new ByteArrayInputStream(("YourName brain-bugs" + System.lineSeparator()).getBytes("ASCII"));
		System.out.println(bin.available());
		System.setIn(bin);
		
		System.out.println(System.in.getClass());
		
		//Assert.assertEquals(true, reader.ready());
		InputMessage message = reader.read();
		Assert.assertEquals(YourNameMessage.class, message.getClass());
		YourNameMessage ynm = (YourNameMessage) message;
		Assert.assertEquals("brain-bugs", ynm.getName());
	}

}
