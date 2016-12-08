package com.graphs.commands;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.graphs.AbstractTest;
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
		assertEquals("2", command.execute(graph, new String[] {"A", "C"}, "<3"));
		assertEquals("3", command.execute(graph, new String[] {"A", "C"}, "<=3"));
		assertEquals("1", command.execute(graph, new String[] {"A", "C"}, "=3"));
		assertEquals("70", command.execute(graph, new String[] {"C", "C"}, ">10"));
		assertEquals("86", command.execute(graph, new String[] {"C", "C"}, ">=10"));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphEvenWithoutVertexesToGoTest() throws IOException, GraphException {
		assertEquals("0", command.execute(graph, new String[] {}, "<1" ));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphButLowRestrictionNumberTest() throws IOException, GraphException {
		assertEquals("0", command.execute(graph, new String[] {"A", "B"}, "<1"));
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphButNotExistingVertexToGoTest() throws IOException, GraphException {
		expect(GraphException.class, "Vertex 'F' not found");
		command.execute(graph, new String[] {"A", "F"}, "<1");
	}
	
	@Test
	public void shouldFailWhenExecuteWithCorrectGraphButInvalidExtraParameterTest() throws IOException, GraphException {
		expect(IllegalArgumentException.class, "Parameter is missing or not valid. e.g.: trips A-B <=4");
		command.execute(graph, new String[] {"A", "B"}, "1");
	}
	
	@Test
	public void shouldFailWhenExecuteWithCorrectGraphButInvalidExtraParameterRegexTest() throws IOException, GraphException {
		expect(IllegalArgumentException.class, "Parameter is missing or not valid. e.g.: trips A-B <=4");
		command.execute(graph, new String[] {"A", "B"}, ">>1");
	}
	
	@Test
	public void shouldFailWhenExecuteWithCorrectGraphButNoExtraParameterRegexTest() throws IOException, GraphException {
		expect(IllegalArgumentException.class, "Parameter is missing or not valid. e.g.: trips A-B <=4");
		command.execute(graph, new String[] {"A", "B"}, (String) null);
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphButNotExistingRouteToGoTest() throws IOException, GraphException {
		expect(NoSuchRouteException.class, "NO SUCH ROUTE");
		command.execute(graph, new String[] {"E", "A"}, "<1" );
	}
	
}