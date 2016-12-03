package com.graphs.commands;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.graphs.AbstractTest;
import com.graphs.domain.Graph;

public class DistanceCommandTest extends AbstractTest {
	
	private Command command = new DistanceCommand();
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphTest() throws IOException {
		assertEquals("9", command.execute(new Graph()));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphEvenWithExtraParamsTest() throws IOException {
		assertEquals("9", command.execute(new Graph(), new Graph()));
	}
	
}