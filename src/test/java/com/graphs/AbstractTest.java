package com.graphs;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;

public abstract class AbstractTest {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	protected Graph graph;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	protected void expect(Class<? extends Exception> exceptionClass, String message) {
		thrown.expect(exceptionClass);
        thrown.expectMessage(message);
	}

	protected void shouldPrintExactly(String text) {
		final String printedContent = outContent.toString();
		assertTrue(printedContent.equals(text));
	}
	
	protected void initGraph() {
		final Vertex a = new Vertex("A");
		final Vertex b = new Vertex("B");
		final Vertex c = new Vertex("c");
		Set<Vertex> set = new HashSet<Vertex>(Arrays.asList(a, b, c));
		final HashMap<String, Edge> edges = new HashMap<String, Edge>();
		edges.put("AB", new Edge("AB", a, b, 5));
		edges.put("BC", new Edge("BC", b, c, 4));
		graph = new Graph(set, edges);
	}

}