package com.graphs.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.graphs.AbstractTest;
import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;
import com.graphs.exception.GraphException;

public class PathFinderServiceTest extends AbstractTest {
	
	private PathFinder pathFinder;
	
	final Vertex a = new Vertex("A");
	final Vertex b = new Vertex("B");
	final Vertex c = new Vertex("C");
	final Vertex d = new Vertex("D");
	final Vertex e = new Vertex("E");
	Edge ab = new Edge(a, b, 5);
	Edge ad = new Edge(a, d, 10);
	Edge bc = new Edge(b, c, 4);
	Edge bd = new Edge(b, d, 4);
	Edge dc = new Edge(d, c, 40);
	Edge ca = new Edge(c, a, 2);
	Edge ed = new Edge(e, d, 10);
	
	@Before
	public void setUp() {
		initGraph();
	}
	
	@Test
	public void shouldPassWhenFindingPathWithSimpleGraphTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "C"} );
		assertEquals(1, existingPaths.size());
		
		final List<Vertex> existingPath = existingPaths.get(0);
		final Iterator<Vertex> iterator = existingPath.iterator();
		
		assertEquals(3, existingPath.size());
		assertEquals(a, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndManyPathsTest() throws GraphException {
		initComplexGraph();
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "C"} );
		assertEquals(3, existingPaths.size());
		
		final List<Vertex> pathOne = existingPaths.get(0);
		final Iterator<Vertex> iteratorOne = pathOne.iterator();
		
		assertEquals(3, pathOne.size());
		assertEquals(a, iteratorOne.next());
		assertEquals(b, iteratorOne.next());
		assertEquals(c, iteratorOne.next());
		
		final List<Vertex> pathTwo = existingPaths.get(1);
		final Iterator<Vertex> iteratorTwo = pathTwo.iterator();
		
		assertEquals(4, pathTwo.size());
		assertEquals(a, iteratorTwo.next());
		assertEquals(b, iteratorTwo.next());
		assertEquals(d, iteratorTwo.next());
		assertEquals(c, iteratorTwo.next());
		
		final List<Vertex> pathThree = existingPaths.get(2);
		final Iterator<Vertex> iteratorThree = pathThree.iterator();
		
		assertEquals(3, pathThree.size());
		assertEquals(a, iteratorThree.next());
		assertEquals(d, iteratorThree.next());
		assertEquals(c, iteratorThree.next());
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndNoPathsTest() throws GraphException {
		initComplexGraph();
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "E"} );
		assertEquals(0, existingPaths.size());		
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndSourceEqualsDestinationTest() throws GraphException {
		initComplexGraph();
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "A"} );
		assertEquals(3, existingPaths.size());
		
		final List<Vertex> pathOne = existingPaths.get(0);
		final Iterator<Vertex> iteratorOne = pathOne.iterator();
		
		assertEquals(4, pathOne.size());
		assertEquals(a, iteratorOne.next());
		assertEquals(b, iteratorOne.next());
		assertEquals(c, iteratorOne.next());
		assertEquals(a, iteratorOne.next());
		
		final List<Vertex> pathTwo = existingPaths.get(1);
		final Iterator<Vertex> iteratorTwo = pathTwo.iterator();
		
		assertEquals(5, pathTwo.size());
		assertEquals(a, iteratorTwo.next());
		assertEquals(b, iteratorTwo.next());
		assertEquals(d, iteratorTwo.next());
		assertEquals(c, iteratorTwo.next());
		assertEquals(a, iteratorTwo.next());
		
		final List<Vertex> pathThree = existingPaths.get(2);
		final Iterator<Vertex> iteratorThree = pathThree.iterator();
		
		assertEquals(4, pathThree.size());
		assertEquals(a, iteratorThree.next());
		assertEquals(d, iteratorThree.next());
		assertEquals(c, iteratorThree.next());
		assertEquals(a, iteratorThree.next());
	}
	
	private void initComplexGraph() {
		final Set<Vertex> set = new HashSet<Vertex>(Arrays.asList(a, b, c, d, e));
		final Set<Edge> edges = new HashSet<Edge>();
		edges.addAll(Arrays.asList(ab, ad, bc, bd, dc, ca, ed));
		this.graph = new Graph(set, edges);
	}
	
}