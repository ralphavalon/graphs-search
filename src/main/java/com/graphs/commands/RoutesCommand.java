package com.graphs.commands;

import java.util.List;

import com.graphs.domain.Edge;
import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;
import com.graphs.enums.Operation;
import com.graphs.exception.GraphException;
import com.graphs.exception.NoSuchRouteException;
import com.graphs.service.PathFinder;

public class RoutesCommand implements Command {
	
	private static final String PARAMETER_REGEX = "(<=?|=|>=?)([0-9]*)";

	@Override
	public String execute(Graph graph, String[] vertexesToGo, Object... params) throws GraphException {
		
		if(vertexesToGo.length <= 0) {
			return String.valueOf(0);
		}
		
		String param = (String) params[0];
		if(param == null || ! param.matches(PARAMETER_REGEX)) {
			throw new IllegalArgumentException("Parameter is missing or not valid. e.g.: routes A-B <=4");
		}
		
		final List<List<Vertex>> existingPaths = PathFinder.getInstance(graph).findPaths(vertexesToGo);
		if(existingPaths.size() == 0) {
			throw new NoSuchRouteException();
		}
		
		String operationSignal = param.replaceAll(PARAMETER_REGEX, "$1");
		Integer restrictionNumber = Integer.parseInt(param.replaceAll(PARAMETER_REGEX, "$2"));
		
		Operation operation = Operation.findBySignal(operationSignal);
		return getRoutesQuantity(graph, existingPaths, operation, restrictionNumber);
	}


	private String getRoutesQuantity(Graph graph, List<List<Vertex>> existingPaths, Operation operation, int restrictionNumber) throws NoSuchRouteException {
		Integer routes = 0;
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
			if(operation.evaluate(currentPathDistance, restrictionNumber)) {
				routes++;
			}
		}
		return String.valueOf(routes);
	}
	
	private boolean hasVertexToGo(int vertexesToGoLength, int index) {
		return (index + 1) < vertexesToGoLength;
	}
	
}