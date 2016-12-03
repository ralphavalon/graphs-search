package com.graphs.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;

public class GraphServiceImpl implements GraphService {

	private CommandService commandService = new CommandServiceImpl();
	private Set<Vertex> vertexes;
	private Map<String, Edge> edges;
	
	@Override
	public List<String> execute(List<String> commandFileLines, List<String> dataFileLines) {
		setVertexesAndEdges(dataFileLines);
		Graph graph = new Graph(vertexes, edges);
		return commandService.execute(graph, commandFileLines);
	}

	private void setVertexesAndEdges(List<String> dataFileLines) {
		edges = new HashMap<String, Edge>();
		vertexes = new HashSet<Vertex>();
		for (String dataFileLine : dataFileLines) {
			if(dataFileLine.length() < 3) {
				throw new IllegalArgumentException("It should follow this order: LetterLetterNumber");
			}
			
			final char sourceVertexId = dataFileLine.charAt(0);
			final char destinationVertexId = dataFileLine.charAt(1);
			final Integer distance = Integer.parseInt(dataFileLine.substring(2)); 
			
			final Vertex sourceVertex = new Vertex(Character.toString(sourceVertexId));
			final Vertex destinationVertex = new Vertex(Character.toString(destinationVertexId));
			
			vertexes.add(sourceVertex);
			vertexes.add(destinationVertex);
			
			final String edgeId = Character.toString(sourceVertexId) + Character.toString(destinationVertexId);
			edges.put(edgeId, new Edge(edgeId, sourceVertex, destinationVertex, distance));
		}
	}
	
}