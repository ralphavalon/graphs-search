package com.graphs.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.graphs.AbstractTest;
import com.graphs.domain.Vertex;
import com.graphs.exception.GraphException;

public class PathFinderServiceTest extends AbstractTest {
	
	private PathFinder pathFinder;
	
	@Before
	public void setUp() {
		initComplexGraph();
	}
	
	@Test
	public void shouldPassWhenFindingPathWithSimpleGraphTest() throws GraphException {
		initGraph();
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "C"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, a, b, c);
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndManyPathsTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "C"} );
		assertEquals(4, existingPaths.size());
		
		assertPaths(1, existingPaths, a, b, c);
		assertPaths(2, existingPaths, a, d, c);
		assertPaths(3, existingPaths, a, d, e, b, c);
		assertPaths(4, existingPaths, a, e, b, c);
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndNoPathsTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "A"} );
		assertEquals(0, existingPaths.size());		
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndSourceEqualsDestinationTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"C", "C"} );
		assertEquals(3, existingPaths.size());
		
		assertPaths(1, existingPaths, c, d, c);
		assertPaths(2, existingPaths, c, d, e, b, c);
		assertPaths(3, existingPaths, c, e, b, c);
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "B"} );
		assertEquals(4, existingPaths.size());
		
		assertPaths(1, existingPaths, a, b);
		assertPaths(2, existingPaths, a, d, c, e, b);
		assertPaths(3, existingPaths, a, d, e, b);
		assertPaths(4, existingPaths, a, e, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"A", "D"} );
		assertEquals(3, existingPaths.size());
		
		assertPaths(1, existingPaths, a, b, c, d);
		assertPaths(2, existingPaths, a, d);
		assertPaths(3, existingPaths, a, e, b, c, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "A"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "B"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, b, c, d, e, b);
		assertPaths(2, existingPaths, b, c, e, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "C"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, b, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "D"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, b, c, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "E"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, b, c, d, e);
		assertPaths(2, existingPaths, b, c, e);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "A"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "B"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, c, d, e, b);
		assertPaths(2, existingPaths, c, e, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "C"} );
		assertEquals(3, existingPaths.size());
		
		assertPaths(1, existingPaths, c, d, c);
		assertPaths(2, existingPaths, c, d, e, b, c);
		assertPaths(3, existingPaths, c, e, b, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "D"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, c, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "E"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, c, d, e);
		assertPaths(2, existingPaths, c, e);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "A"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "B"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, d, c, e, b);
		assertPaths(2, existingPaths, d, e, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "C"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, d, c);
		assertPaths(2, existingPaths, d, e, b, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "D"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, d, c, d);
		assertPaths(2, existingPaths, d, e, b, c, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "E"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, d, c, e);
		assertPaths(2, existingPaths, d, e);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "A"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "B"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, e, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "C"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, e, b, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "D"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, e, b, c, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "E"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, e, b, c, d, e);
		assertPaths(2, existingPaths, e, b, c, e );		
		
	}

	private void assertPaths(int path, final List<List<Vertex>> existingPaths, Vertex... vertexes) {
		int size = vertexes.length;
		final List<Vertex> pathOne = existingPaths.get(path - 1);
		final Iterator<Vertex> iteratorOne = pathOne.iterator();
		
		final String message = pathOne + " doesn't match: " + Arrays.toString(vertexes);
		assertEquals(message, size, pathOne.size());
		
		for (Vertex vertex : vertexes) {
			assertEquals(message, vertex, iteratorOne.next());
		}
	}

}