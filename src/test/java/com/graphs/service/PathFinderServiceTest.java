package com.graphs.service;

import static org.junit.Assert.assertEquals;

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
		assertEquals(3, existingPaths.size());
		
		assertPaths(1, existingPaths, a, b, c);
		assertPaths(2, existingPaths, a, b, d, c);
		assertPaths(3, existingPaths, a, d, c);
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndNoPathsTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "E"} );
		assertEquals(0, existingPaths.size());		
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndSourceEqualsDestinationTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "A"} );
		assertEquals(3, existingPaths.size());
		
		assertPaths(1, existingPaths, a, b, c, a);
		assertPaths(2, existingPaths, a, b, d, c, a);
		assertPaths(3, existingPaths, a, d, c, a);
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "A"} );
		assertEquals(3, existingPaths.size());
		
		assertPaths(1, existingPaths, a, b, c, a);
		assertPaths(2, existingPaths, a, b, d, c, a);
		assertPaths(3, existingPaths, a, d, c, a);
		
		existingPaths = pathFinder.findPaths(new String[] {"A", "B"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, a, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"A", "D"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, a, b, d);
		assertPaths(2, existingPaths, a, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "A"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, b, c, a);
		assertPaths(2, existingPaths, b, d, c, a);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "B"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, b, c, a, b);
		assertPaths(2, existingPaths, b, d, c, a, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "C"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, b, c);
		assertPaths(2, existingPaths, b, d, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "D"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, b, c, a, d);
		assertPaths(2, existingPaths, b, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "E"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "A"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, c, a);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "B"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, c, a, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "C"} );
		assertEquals(3, existingPaths.size());
		
		assertPaths(1, existingPaths, c, a, b, c);
		assertPaths(2, existingPaths, c, a, b, d, c);
		assertPaths(3, existingPaths, c, a, d, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "D"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, c, a, b, d);
		assertPaths(2, existingPaths, c, a, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "E"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "A"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, d, c, a);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "B"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, d, c, a, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "C"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, d, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "D"} );
		assertEquals(2, existingPaths.size());
		
		assertPaths(1, existingPaths, d, c, a, b, d);
		assertPaths(2, existingPaths, d, c, a, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "E"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "A"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, e, d, c, a);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "B"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, e, d, c, a, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "C"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, e, d, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "D"} );
		assertEquals(1, existingPaths.size());
		
		assertPaths(1, existingPaths, e, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "E"} );
		assertEquals(0, existingPaths.size());
		
	}

	private void assertPaths(int path, final List<List<Vertex>> existingPaths, Vertex... vertexes) {
		int size = vertexes.length;
		final List<Vertex> pathOne = existingPaths.get(path - 1);
		final Iterator<Vertex> iteratorOne = pathOne.iterator();
		
		assertEquals(size, pathOne.size());
		
		for (Vertex vertex : pathOne) {
			assertEquals(vertex, iteratorOne.next());
		}
	}

}