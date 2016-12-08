package com.graphs;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
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
	
	protected final Vertex a = new Vertex("A");
	protected final Vertex b = new Vertex("B");
	protected final Vertex c = new Vertex("C");
	protected final Vertex d = new Vertex("D");
	protected final Vertex e = new Vertex("E");
	protected Edge ab = new Edge(a, b, 5);
	protected Edge bc = new Edge(b, c, 4);
	protected Edge cd = new Edge(c, d, 8);
	protected Edge dc = new Edge(d, c, 8);
	protected Edge de = new Edge(d, e, 6);
	protected Edge ad = new Edge(a, d, 5);
	protected Edge ce = new Edge(c, e, 2);
	protected Edge eb = new Edge(e, b, 3);
	protected Edge ae = new Edge(a, e, 7);
	
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
	
	protected void shouldPrintlnExactly(String... text) {
		final String printedContent = outContent.toString();
		String[] contents = printedContent.split("\r\n");
		final String errorMessage = "Printed content does not match texts given: %s printed, %s given";
		assertTrue(String.format(errorMessage, contents.length, text.length), contents.length == text.length);
		for (int i = 0; i < contents.length; i++) {
			final String linePrinted = contents[i];
			assertTrue("Expected: '" + text[i] + "' Actual: '" + linePrinted +"'", linePrinted.equals(text[i]));
		}
	}
	
	protected void initGraph() {
		final Vertex a = new Vertex("A");
		final Vertex b = new Vertex("B");
		final Vertex c = new Vertex("C");
		Set<Vertex> set = new HashSet<Vertex>(Arrays.asList(a, b, c));
		final Set<Edge> edges = new HashSet<Edge>();
		edges.add(new Edge(a, b, 5));
		edges.add(new Edge(b, c, 4));
		graph = new Graph(set, edges);
	}
	
	protected void initComplexGraph() {
		final Set<Vertex> set = new HashSet<Vertex>(Arrays.asList(a, b, c, d, e));
		final Set<Edge> edges = new HashSet<Edge>();
		edges.addAll(Arrays.asList(ab, ad, ae, bc, ce, dc, cd, de, eb));
		this.graph = new Graph(set, edges);
	}

}