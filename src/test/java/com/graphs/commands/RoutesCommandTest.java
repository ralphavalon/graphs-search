package com.graphs.commands;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.graphs.AbstractTest;
import com.graphs.exception.GraphException;
import com.graphs.exception.NoSuchRouteException;

public class RoutesCommandTest extends AbstractTest {
	
	private Command command = new RoutesCommand();
	
	@Before
	public void setUp() {
		initComplexGraph();
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphTest() throws IOException, GraphException {
		assertEquals("7", command.execute(graph, new String[] {"C", "C"}, "<30"));
		assertEquals("9", command.execute(graph, new String[] {"C", "C"}, "<=30"));
		assertEquals("1", command.execute(graph, new String[] {"C", "C"}, "=32"));
		assertEquals("76", command.execute(graph, new String[] {"C", "C"}, ">50"));
		assertEquals("82", command.execute(graph, new String[] {"C", "C"}, ">=50"));
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
		expect(IllegalArgumentException.class, "Parameter is missing or not valid. e.g.: routes A-B <=4");
		command.execute(graph, new String[] {"A", "B"}, "1");
	}
	
	@Test
	public void shouldFailWhenExecuteWithCorrectGraphButInvalidExtraParameterRegexTest() throws IOException, GraphException {
		expect(IllegalArgumentException.class, "Parameter is missing or not valid. e.g.: routes A-B <=4");
		command.execute(graph, new String[] {"A", "B"}, ">>1");
	}
	
	@Test
	public void shouldFailWhenExecuteWithCorrectGraphButNoExtraParameterRegexTest() throws IOException, GraphException {
		expect(IllegalArgumentException.class, "Parameter is missing or not valid. e.g.: routes A-B <=4");
		command.execute(graph, new String[] {"A", "B"}, (String) null);
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectGraphButNotExistingRouteToGoTest() throws IOException, GraphException {
		expect(NoSuchRouteException.class, "NO SUCH ROUTE");
		command.execute(graph, new String[] {"E", "A"}, "<1" );
	}
	
}