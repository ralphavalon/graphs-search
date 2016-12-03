package com.graphs;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MainTest {

	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}

	@Test
	public void shouldPassWhenDataFileIsGivenTest() {
		Main.main(new String[]{"data.txt"});
		final String printedContent = outContent.toString();
		assertTrue(printedContent.equals("9"));
	}
	
	@Test
	public void shouldFailWhenMissingDataFileTest() {
		thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Data file is missing");
		Main.main(new String[]{});
		final String printedContent = outContent.toString();
		assertFalse(printedContent.equals("9"));
	}

}