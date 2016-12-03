package com.graphs.commands;

import java.util.Map;

import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.exception.GraphException;
import com.graphs.exception.NoSuchRouteException;

public class DistanceCommand implements Command {

	@Override
	public String execute(Graph graph, Object params) throws GraphException {
		final Map<String, Edge> edges = graph.getEdges();
		Integer distance = 0;
		
		String[] vertexesToGo = (String[]) params;
		for (int i = 0; i < vertexesToGo.length; i++) {
			if( (i + 1) < vertexesToGo.length) {
				String sourceVertex = vertexesToGo[i];
				String destinationVertex = vertexesToGo[i+1];
				final Edge edge = edges.get(sourceVertex + destinationVertex);
				if(edge == null) {
					throw new NoSuchRouteException();
				}
				distance += edge.getWeight();
			}
		}
		return String.valueOf(distance);
	}

}