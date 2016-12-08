package com.graphs.commands;

import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;
import com.graphs.exception.GraphException;

public class DistanceCommand implements Command {

	@Override
	public String execute(Graph graph, String[] vertexesToGo, Object... params) throws GraphException {
		Integer distance = 0;
		
		for (int i = 0, j = vertexesToGo.length; i < j; i++) {
			if( hasVertexToGo(j, i) ) {
				Vertex source = graph.getVertexById(vertexesToGo[i]);
				Vertex destination = graph.getVertexById(vertexesToGo[i+1]);
				final Edge edge = graph.getEdgeByVertexes(source, destination);
				distance += edge.getWeight();
			}
		}
		return String.valueOf(distance);
	}

	private boolean hasVertexToGo(int vertexesToGoLength, int index) {
		return (index + 1) < vertexesToGoLength;
	}

}