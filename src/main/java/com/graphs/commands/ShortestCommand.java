package com.graphs.commands;

import java.util.List;

import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;
import com.graphs.exception.GraphException;
import com.graphs.exception.NoSuchRouteException;
import com.graphs.service.PathFinder;

public class ShortestCommand implements Command {
	
	@Override
	public String execute(Graph graph, String[] vertexesToGo, Object... params) throws GraphException {
		
		if(vertexesToGo.length <= 0) {
			return String.valueOf(0);
		}
		
		final List<List<Vertex>> existingPaths = PathFinder.getInstance(graph).findPaths(vertexesToGo);
		if(existingPaths.size() == 0) {
			throw new NoSuchRouteException();
		}
		
		return getShortestPath(graph, existingPaths);
	}


	private String getShortestPath(Graph graph, List<List<Vertex>> existingPaths) throws NoSuchRouteException {
		Integer shortestPath = Integer.MAX_VALUE;
		for (List<Vertex> existingPath : existingPaths) {
			Integer currentPathDistance = 0;
			for (int i = 0, j = existingPath.size(); i < j; i++) {
				if( hasVertexToGo(j, i) ) {
					Vertex source = existingPath.get(i);
					Vertex destination = existingPath.get(i+1);
					final Edge edge = graph.getEdgeByVertexes(source, destination);
					currentPathDistance += edge.getWeight();
				}
			}
			if(currentPathDistance < shortestPath) {
				shortestPath = new Integer(currentPathDistance);
			}
		}
		return String.valueOf(shortestPath);
	}
	
	private boolean hasVertexToGo(int vertexesToGoLength, int index) {
		return (index + 1) < vertexesToGoLength;
	}

}