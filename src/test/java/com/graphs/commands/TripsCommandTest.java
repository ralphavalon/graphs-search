package com.graphs.commands;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.graphs.AbstractTest;
import com.graphs.enums.Operation;
import com.graphs.exception.GraphException;
import com.graphs.exception.NoSuchRouteException;

public class TripsCommandTest extends AbstractTest {
	
	private Command command = new TripsCommand();
	
	@Before
	public void setUp() {
		initComplexGraph();
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphTest() throws IOException, GraphException {
		assertEquals("2", command.execute(graph, new String[] {"A", "C"}, Operation.LESS_THAN, 4));
		assertEquals("3", command.execute(graph, new String[] {"A", "C"}, Operation.LESS_EQUAL_THAN, 4));
		assertEquals("1", command.execute(graph, new String[] {"A", "C"}, Operation.EQUAL_THAN, 4));
		assertEquals("1", command.execute(graph, new String[] {"A", "C"}, Operation.GREATER_THAN, 3));
		assertEquals("3", command.execute(graph, new String[] {"A", "C"}, Operation.GREATER_EQUAL_THAN, 3));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphEvenWithoutVertexesToGoTest() throws IOException, GraphException {
		assertEquals("0", command.execute(graph, new String[] {}, Operation.LESS_EQUAL_THAN, 1 ));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphButLowRestrictionNumberTest() throws IOException, GraphException {
		assertEquals("0", command.execute(graph, new String[] {"A", "B"}, Operation.EQUAL_THAN, 1));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphButNotExistingVertexToGoTest() throws IOException, GraphException {
		expect(GraphException.class, "Vertex 'F' not found");
		command.execute(graph, new String[] {"A", "F"}, Operation.LESS_THAN, 1);
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphButNotExistingRouteToGoTest() throws IOException, GraphException {
		expect(NoSuchRouteException.class, "NO SUCH ROUTE");
		command.execute(graph, new String[] {"A", "E"}, Operation.LESS_THAN, 1 );
	}
	
}