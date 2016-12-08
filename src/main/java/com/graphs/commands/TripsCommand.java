package com.graphs.commands;

import java.util.List;

import com.graphs.domain.Graph;
import com.graphs.domain.Vertex;
import com.graphs.enums.Operation;
import com.graphs.exception.GraphException;
import com.graphs.exception.NoSuchRouteException;
import com.graphs.service.PathFinder;

public class TripsCommand implements Command {

	@Override
	public String execute(Graph graph, String[] vertexesToGo, Object... params) throws GraphException {
		
		if(vertexesToGo.length <= 0) {
			return String.valueOf(0);
		}
		
		final List<List<Vertex>> existingPaths = PathFinder.getInstance(graph).findPaths(vertexesToGo);
		if(existingPaths.size() == 0) {
			throw new NoSuchRouteException();
		}
		
		Operation operation = (Operation) params[0];
		int restrictionNumber = (int) params[1];
		return getTripsQuantity(existingPaths, operation, restrictionNumber);
	}


	private String getTripsQuantity(List<List<Vertex>> existingPaths, Operation operation, int restrictionNumber) {
		Integer trips = 0;
		for (List<Vertex> existingPath : existingPaths) {
			if(operation.evaluate(existingPath.size(), restrictionNumber)) {
				trips++;
			}
		}
		return String.valueOf(trips);
	}

}