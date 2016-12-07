package com.graphs.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.graphs.AbstractTest;

public class CommandServiceTest extends AbstractTest {
	
	private CommandService commandService;
	
	@Before
	public void setUp() {
		commandService = new CommandServiceImpl();
		initGraph();
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectCommandFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("distance A-B-C");
		final List<String> results = commandService.execute(graph, commandFileLines);
		assertEquals(1, results.size());
		assertEquals("9", results.get(0));
	}
	
	@Test
	public void shouldPassWhenExecuteWithNotExistingRouteOnCommandFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("distance A-B-B");
		final List<String> results = commandService.execute(graph, commandFileLines);
		assertEquals(1, results.size());
		assertEquals("NO SUCH ROUTE", results.get(0));
	}
	
	@Test
	public void shouldPassWhenExecuteWithNotExistingVertexOnCommandFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("distance A-B-F");
		final List<String> results = commandService.execute(graph, commandFileLines);
		assertEquals(1, results.size());
		assertEquals("Vertex 'F' not found", results.get(0));
	}
	
	@Test
	public void shouldFailWhenExecuteWithWrongCommandFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("not_existing_command A-B-C");
		expect(IllegalArgumentException.class, "No command found with name: not_existing_command");
		commandService.execute(graph, commandFileLines);
	}
	
	@Test
	public void shouldFailWhenExecuteWithInvalidCommandFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("not_existing_commandA-B-C");
		expect(IllegalArgumentException.class, "Missing command instructions. e.g.: distance A-B-C");
		commandService.execute(graph, commandFileLines);
	}
	
}