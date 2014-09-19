package de.hbt.hackathon.rtb.protocol;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hbt.hackathon.rtb.base.message.output.NameMessage;

public class WriterTest {

	private static PrintStream realSystemOut = null;
	private static StringWriter out = new StringWriter();
	private Writer writer;

	@BeforeClass
	public static void beforeClass() {
		realSystemOut = System.out;
		WriterOutputStream wout = new WriterOutputStream(out);
		PrintStream pw = new PrintStream(wout);
		System.setOut(pw);
	}

	@AfterClass
	public static void afterClass() {
		System.setOut(realSystemOut);
	}

	@Before
	public void init() {
		out.getBuffer().setLength(0);
		writer = new Writer();
	}

	@Test
	public void testWrite01() {
		NameMessage message = new NameMessage("brain_bug_no.1");
		writer.write(message);
		Assert.assertEquals("Name brain_bug_no.1\n", out.getBuffer().toString());
	}

}
