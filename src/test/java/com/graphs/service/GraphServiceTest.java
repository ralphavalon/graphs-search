package com.graphs.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.graphs.AbstractTest;
import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;

public class GraphServiceTest extends AbstractTest {
	
	private GraphService graphService;
	
	@Before
	public void setUp() {
		graphService = new GraphServiceImpl();
	}
	
	@Test
	public void shouldPassWhenExecuteWithCorrectCommandFileLinesAndDataFileLinesTest() throws IOException {
		List<String> dataFileLines = Arrays.asList("AB5", "BC4");
		final Graph graph = graphService.getGraph(dataFileLines);
		final Set<Edge> edges = graph.getEdges();
		final Set<Vertex> vertexes = graph.getVertexes();
		assertEquals(2, edges.size());
		assertEquals(3, vertexes.size());
		assertTrue(edges.contains(ab));
		assertTrue(edges.contains(bc));
		assertTrue(vertexes.contains(a));
		assertTrue(vertexes.contains(b));
		assertTrue(vertexes.contains(c));
	}
	
	@Test
	public void shouldFailWhenExecuteWithWrongDataFileLinesTest() throws IOException {
		List<String> dataFileLines = Arrays.asList("AB", "4");
		expect(IllegalArgumentException.class, "It should follow this order: LetterLetterNumber");
		graphService.getGraph(dataFileLines);
	}
	
	@Test
	public void shouldFailWhenExecuteWithWrongDataFileLinesOnWeightTest() throws IOException {
		List<String> dataFileLines = Arrays.asList("AB", "B");
		expect(IllegalArgumentException.class, "It should follow this order: LetterLetterNumber");
		graphService.getGraph(dataFileLines);
	}
	
}