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
	public void shouldPassWhenExecuteWithCorrectCommandFileLinesAndDataFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("distance A-B-C");
		List<String> dataFileLines = Arrays.asList("AB5", "BC4");
		final List<String> results = graphService.execute(commandFileLines, dataFileLines);
		assertEquals(1, results.size());
		assertEquals("9", results.get(0));
	}
	
	@Test
	public void shouldFailWhenExecuteWithWrongCommandFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("not_existing_command A-B-C");
		List<String> dataFileLines = Arrays.asList("AB5", "BC4");
		expect(IllegalArgumentException.class, "No command found with name: not_existing_command");
		graphService.execute(commandFileLines, dataFileLines);
	}
	
	@Test
	public void shouldFailWhenExecuteWithInvalidCommandFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("not_existing_commandA-B-C");
		List<String> dataFileLines = Arrays.asList("AB5", "BC4");
		expect(IllegalArgumentException.class, "Missing command instructions. e.g.: distance A-B-C");
		graphService.execute(commandFileLines, dataFileLines);
	}
	
	@Test
	public void shouldFailWhenExecuteWithWrongDataFileLinesTest() throws IOException {
		List<String> commandFileLines = Arrays.asList("distance A-B-C");
		List<String> dataFileLines = Arrays.asList("AB", "4");
		expect(IllegalArgumentException.class, "It should follow this order: LetterLetterNumber");
		graphService.execute(commandFileLines, dataFileLines);
	}
	
}