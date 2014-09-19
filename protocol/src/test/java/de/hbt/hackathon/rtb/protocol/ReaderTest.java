package de.hbt.hackathon.rtb.protocol;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hbt.hackathon.rtb.protocol.message.AngleType;
import de.hbt.hackathon.rtb.protocol.message.input.InputMessage;
import de.hbt.hackathon.rtb.protocol.message.input.RotationReachedMessage;
import de.hbt.hackathon.rtb.protocol.message.input.YourNameMessage;

public class ReaderTest {
	
	private static InputStream realSystemIn = null;
	
	@BeforeClass
	public static void beforeClass() {
		realSystemIn = System.in;
	}
	
	@AfterClass
	public static void afterClass() {
		System.setIn(realSystemIn);
	}
	
	@Test
	public void testRead01() throws IOException {
		ByteArrayInputStream bin = new ByteArrayInputStream(("YourName brain-bugs" + System.lineSeparator()).getBytes("ASCII"));
		System.setIn(bin);
		Reader reader = new Reader();
		
		Assert.assertEquals(true, reader.ready());
		InputMessage message = reader.read();
		Assert.assertEquals(YourNameMessage.class, message.getClass());
		YourNameMessage ynm = (YourNameMessage) message;
		Assert.assertEquals("brain-bugs", ynm.getName());
	}
	
	@Test
	public void testRead02() throws IOException {
		ByteArrayInputStream bin = new ByteArrayInputStream(("RotationReached 3" + System.lineSeparator()).getBytes("ASCII"));
		System.setIn(bin);
		Reader reader = new Reader();
		
		Assert.assertEquals(true, reader.ready());
		InputMessage message = reader.read();
		Assert.assertEquals(RotationReachedMessage.class, message.getClass());
		RotationReachedMessage rrm = (RotationReachedMessage) message;
		Assert.assertEquals(2, rrm.getReachedAngles().size());
		Assert.assertEquals(true, rrm.getReachedAngles().contains(AngleType.ROBOT));
		Assert.assertEquals(true, rrm.getReachedAngles().contains(AngleType.CANNON));
	}

}
