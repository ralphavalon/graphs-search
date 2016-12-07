package com.graphs.domain;

import java.util.Set;

import com.graphs.exception.NoSuchRouteException;
import com.graphs.exception.NoSuchVertexException;

public class Graph {
	
	private final Set<Vertex> vertexes;
	private final Set<Edge> edges;
	
	public Graph(Set<Vertex> vertexes, Set<Edge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}
	
	public Set<Edge> getEdges() {
		return edges;
	}
	
	public Set<Vertex> getVertexes() {
		return vertexes;
	}
	
	public Edge getEdgeByVertexes(Vertex source, Vertex destination) throws NoSuchRouteException {
		for (Edge edge : edges) {
			if(edge.getSource().equals(source) && edge.getDestination().equals(destination)) {
				return edge;
			}
		}
		throw new NoSuchRouteException();
	}
	
	public Vertex getVertexById(String id) throws NoSuchVertexException {
		for (Vertex vertex : vertexes) {
			if(vertex.getId().equals(id)){
				return vertex;
			}
		}
		throw new NoSuchVertexException(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result
				+ ((vertexes == null) ? 0 : vertexes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (edges == null) {
			if (other.edges != null)
				return false;
		} else if (!edges.equals(other.edges))
			return false;
		if (vertexes == null) {
			if (other.vertexes != null)
				return false;
		} else if (!vertexes.equals(other.vertexes))
			return false;
		return true;
	}

}