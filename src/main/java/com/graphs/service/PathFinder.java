package com.graphs.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;
import com.graphs.exception.GraphException;


public class PathFinder {
	
	private static PathFinder instance = null;
	private HashMap<Vertex, Boolean> visited = null;
	private Graph graph = null;
	private Set<Edge> edges = null;
	private Set<Vertex> vertexes = null;
	private Map<Vertex, Set<Vertex>> edgesMap = null;
	private List<List<Vertex>> allPaths = null;
	private static final int ALLOWED_DUPLICATES = 5;
	
	private PathFinder(Graph graph) {
		this.graph = graph;
		this.edges = graph.getEdges();
		this.vertexes = graph.getVertexes();
		this.visited = new HashMap<Vertex, Boolean>();
		this.edgesMap = new HashMap<>();
		
		for (Vertex vertex : vertexes) {
			Queue<Object> queue = new LinkedList<>();
			queue.add(vertex);
			visited = new HashMap<Vertex, Boolean>();
			bfsRecursive(queue);
		}
		
		this.allPaths = new ArrayList<>();
	}
	
	public List<List<Vertex>> findPaths(String[] vertexesToGo) throws GraphException {
		instance.allPaths = new ArrayList<>();
		Vertex source = graph.getVertexById(vertexesToGo[0]);
		Vertex destination = graph.getVertexById(vertexesToGo[1]);
		
		getPaths(source, destination);
		return allPaths;
	}

	public void bfsRecursive(Queue<Object> queue) {
		if (queue.isEmpty())
			return;
		Vertex vertex = (Vertex) queue.remove();
		visited.put(vertex, true);
		
		Set<Vertex> childVertexes = getChilds(vertex);
		if(edgesMap.containsKey(vertex)) {
			Set<Vertex> existingChildVertexes = edgesMap.get(vertex);
			existingChildVertexes.addAll(childVertexes);
			edgesMap.put(vertex, existingChildVertexes);
		} else {
			edgesMap.put(vertex, childVertexes);
		}
		
		for (Vertex child : childVertexes) {
			queue.add(child);
		}
		bfsRecursive(queue);
	}

	private Set<Vertex> getChilds(Vertex vertex) {
		return edges.stream()
				.filter(edge -> vertex.equals(edge.getSource()))
				.map(edge -> edge.getDestination())
				.filter(childVertex -> !visited.containsKey(childVertex) || visited.get(childVertex) == false)
				.collect(Collectors.toSet());
	}
	
	private void getPaths(Vertex source, Vertex destination) {
		Set<Vertex> reachableVertexes = edgesMap.get(source);
		for (Vertex reachableVertex : reachableVertexes) {
			List<Vertex> vertexesPath = new ArrayList<>();
			vertexesPath.add(source);
			vertexesPath.add(reachableVertex);
			getPaths(reachableVertex, destination, vertexesPath);
		}
	}
	
	private void getPaths(Vertex current, Vertex destination, List<Vertex> vertexList) {
		if(current.equals(destination)) {
			allPaths.add(vertexList);
		}
		
		for (Vertex vertex : vertexList) {
			int duplicates = Collections.frequency(vertexList, vertex);
			if(duplicates >= ALLOWED_DUPLICATES) {
				return;
			}
		}
		
		Set<Vertex> set = edgesMap.get(current);
		for (Vertex vertex : set) {
			List<Vertex> vertexPaths = new ArrayList<>(vertexList);
			vertexPaths.add(vertex);
			getPaths(vertex, destination, vertexPaths);
		}
	}
	
	public static PathFinder getInstance(Graph graph) {
		if(instance == null) {
			instance = new PathFinder(graph);
		} else if( ! instance.graph.equals(graph) ) {
			instance = new PathFinder(graph);
		}
		return instance;
	}
	
}