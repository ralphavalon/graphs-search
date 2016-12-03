package com.graphs.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.graphs.AbstractTest;

public class GraphServiceTest extends AbstractTest {
	
	private GraphService graphService;
	
	@Before
	public void setUp() {
		graphService = new GraphServiceImpl();
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectCommandFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("distance A-B-C");
		final List<String> results = graphService.execute(commandFileLines, null);
		assertEquals(1, results.size());
		assertEquals("9", results.get(0));
	}
	
	@Test
	public void shouldFailWhenExecuteWithWrongCommandFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("not_existing_command A-B-C");
		expect(IllegalArgumentException.class, "No command found with name: not_existing_command");
		graphService.execute(commandFileLines, null);
	}
	
	@Test
	public void shouldFailWhenExecuteWithInvalidCommandFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("not_existing_commandA-B-C");
		expect(IllegalArgumentException.class, "No command found with name: not_existing_commandA-B-C");
		graphService.execute(commandFileLines, null);
	}
	
}