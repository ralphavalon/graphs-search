package com.graphs.commands;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.graphs.AbstractTest;
import com.graphs.exception.GraphException;
import com.graphs.exception.NoSuchRouteException;

public class ShortestCommandTest extends AbstractTest {
	
	private Command command = new ShortestCommand();
	
	@Before
	public void setUp() {
		initComplexGraph();
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphTest() throws IOException, GraphException {
		assertEquals("9", command.execute(graph, new String[] {"A", "C"}));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphEvenWithoutVertexesToGoTest() throws IOException, GraphException {
		assertEquals("0", command.execute(graph, new String[] {} ));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphButNotExistingVertexToGoTest() throws IOException, GraphException {
		expect(GraphException.class, "Vertex 'F' not found");
		command.execute(graph, new String[] {"A", "F"} );
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphButNotExistingRouteToGoTest() throws IOException, GraphException {
		expect(NoSuchRouteException.class, "NO SUCH ROUTE");
		command.execute(graph, new String[] {"E", "A"} );
	}
	
}