package com.graphs.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.graphs.AbstractTest;

public class CommandsTest extends AbstractTest {
	
	@Test
	public void shouldPassWhenCommandNameDoesExistTest() throws IOException {
		Commands commands = Commands.getByName("distance");
		assertNotNull(commands);
		assertEquals(Commands.DISTANCE, commands);
		
		commands = Commands.getByName("trips");
		assertNotNull(commands);
		assertEquals(Commands.TRIPS, commands);
	}
	
	@Test
	public void shouldFailWhenCommandNameDoesNotExistTest() throws IOException {
		expect(IllegalArgumentException.class, "No command found with name: not_existing_command");
		Commands.getByName("not_existing_command");
	}
	
}