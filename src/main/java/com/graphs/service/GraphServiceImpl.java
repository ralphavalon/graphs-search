package com.graphs.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;

public class GraphServiceImpl implements GraphService {

	private CommandService commandService = new CommandServiceImpl();
	private Set<Vertex> vertexes;
	private Set<Edge> edges;
	
	@Override
	public List<String> execute(List<String> commandFileLines, List<String> dataFileLines) {
		setVertexesAndEdges(dataFileLines);
		Graph graph = new Graph(vertexes, edges);
		return commandService.execute(graph, commandFileLines);
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