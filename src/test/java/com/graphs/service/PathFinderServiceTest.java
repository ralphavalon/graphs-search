package com.graphs.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
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
		
		assertContainsPaths( existingPaths, a, b, c);
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndSourceEqualsDestinationTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"C", "C"} );
		assertEquals(120, existingPaths.size());
		
		assertContainsPaths(existingPaths, c, d, c);
		assertContainsPaths(existingPaths, c, d, c, d, c);
		assertContainsPaths(existingPaths, c, d, e, b, c);
		assertContainsPaths(existingPaths, c, e, b, c);
		assertContainsPaths(existingPaths, c, e, b, c, e, b, c, e, b, c);
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndManyPathsTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "C"} );
		
		assertContainsPaths( existingPaths, a, b, c);
		assertContainsPaths( existingPaths, a, d, c);
		assertContainsPaths( existingPaths, a, d, e, b, c);
		assertContainsPaths( existingPaths, a, e, b, c);
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphAndNoPathsTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		final List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "A"} );
		assertEquals(0, existingPaths.size());		
	}
	
	@Test
	public void shouldPassWhenFindingPathWithComplexGraphTest() throws GraphException {
		pathFinder = PathFinder.getInstance(graph);
		List<List<Vertex>> existingPaths = pathFinder.findPaths(new String[] {"A", "B"} );
		
		assertContainsPaths( existingPaths, a, b);
		assertContainsPaths( existingPaths, a, d, c, e, b);
		assertContainsPaths( existingPaths, a, d, e, b);
		assertContainsPaths( existingPaths, a, e, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"A", "D"} );
		
		assertContainsPaths( existingPaths, a, b, c, d);
		assertContainsPaths( existingPaths, a, d);
		assertContainsPaths( existingPaths, a, e, b, c, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "A"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "B"} );
		
		assertContainsPaths( existingPaths, b, c, d, e, b);
		assertContainsPaths( existingPaths, b, c, e, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "C"} );
		
		assertContainsPaths( existingPaths, b, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "D"} );
		
		assertContainsPaths( existingPaths, b, c, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"B", "E"} );
		
		assertContainsPaths( existingPaths, b, c, d, e);
		assertContainsPaths( existingPaths, b, c, e);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "A"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "B"} );
		
		assertContainsPaths( existingPaths, c, d, e, b);
		assertContainsPaths( existingPaths, c, e, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "C"} );
		
		assertContainsPaths( existingPaths, c, d, c);
		assertContainsPaths( existingPaths, c, d, e, b, c);
		assertContainsPaths( existingPaths, c, e, b, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "D"} );
		
		assertContainsPaths( existingPaths, c, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"C", "E"} );
		
		assertContainsPaths( existingPaths, c, d, e);
		assertContainsPaths( existingPaths, c, e);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "A"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "B"} );
		
		assertContainsPaths( existingPaths, d, c, e, b);
		assertContainsPaths( existingPaths, d, e, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "C"} );
		
		assertContainsPaths( existingPaths, d, c);
		assertContainsPaths( existingPaths, d, e, b, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "D"} );
		
		assertContainsPaths( existingPaths, d, c, d);
		assertContainsPaths( existingPaths, d, e, b, c, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"D", "E"} );
		
		assertContainsPaths( existingPaths, d, c, e);
		assertContainsPaths( existingPaths, d, e);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "A"} );
		assertEquals(0, existingPaths.size());
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "B"} );
		
		assertContainsPaths( existingPaths, e, b);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "C"} );
		
		assertContainsPaths( existingPaths, e, b, c);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "D"} );
		
		assertContainsPaths( existingPaths, e, b, c, d);
		
		existingPaths = pathFinder.findPaths(new String[] {"E", "E"} );
		
		assertContainsPaths( existingPaths, e, b, c, d, e);
		assertContainsPaths( existingPaths, e, b, c, e );		
	}

	private void assertContainsPaths(final List<List<Vertex>> existingPaths, Vertex... vertexes) {
		final String message = "Existing paths doesn't match: " + Arrays.toString(vertexes);
		assertEquals(message, true, existingPaths.contains(Arrays.asList(vertexes)));
	}
	
}