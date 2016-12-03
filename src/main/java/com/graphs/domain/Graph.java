package com.graphs.domain;

import java.util.Map;
import java.util.Set;

public class Graph {
	
	private final Set<Vertex> vertexes;
	private final Map<String, Edge> edges;
	
	public Graph(Set<Vertex> vertexes, Map<String, Edge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}
	
	public Map<String, Edge> getEdges() {
		return edges;
	}
	
	public Set<Vertex> getVertexes() {
		return vertexes;
	}

}