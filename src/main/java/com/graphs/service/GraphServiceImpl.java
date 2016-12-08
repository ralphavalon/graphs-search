package com.graphs.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;

public class GraphServiceImpl implements GraphService {

	private Set<Vertex> vertexes;
	private Set<Edge> edges;
	
	@Override
	public Graph getGraph(List<String> dataFileLines) {
		setVertexesAndEdges(dataFileLines);
		return new Graph(vertexes, edges);
	}

	private void setVertexesAndEdges(List<String> dataFileLines) {
		edges = new HashSet<Edge>();
		vertexes = new HashSet<Vertex>();
		for (String dataFileLine : dataFileLines) {
			if(dataFileLine.length() < 3) {
				throw new IllegalArgumentException("It should follow this order: LetterLetterNumber");
			}
			
			final char sourceVertexId = dataFileLine.charAt(0);
			final char destinationVertexId = dataFileLine.charAt(1);
			Integer distance;
			try {
				distance = Integer.parseInt(dataFileLine.substring(2).trim());
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("It should follow this order: LetterLetterNumber");
			} 
			
			final Vertex sourceVertex = new Vertex(Character.toString(sourceVertexId));
			final Vertex destinationVertex = new Vertex(Character.toString(destinationVertexId));
			
			vertexes.add(sourceVertex);
			vertexes.add(destinationVertex);
			
			edges.add(new Edge(sourceVertex, destinationVertex, distance));
		}
	}
	
}