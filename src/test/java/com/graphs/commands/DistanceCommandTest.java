package com.graphs.commands;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.graphs.AbstractTest;
import com.graphs.exception.GraphException;

public class DistanceCommandTest extends AbstractTest {
	
	private Command command = new DistanceCommand();
	
	@Before
	public void setUp() {
		initGraph();
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphTest() throws IOException, GraphException {
		assertEquals("9", command.execute(graph, new String[] {"A", "B", "C"} ));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphEvenWithoutVertexesToGoTest() throws IOException, GraphException {
		assertEquals("0", command.execute(graph, new String[] {} ));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphButNotExistingVertexToGoTest() throws IOException, GraphException {
		expect(GraphException.class, "NO SUCH ROUTE");
		command.execute(graph, new String[] {"A", "B", "D"});
	}
	
}