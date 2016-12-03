package com.graphs.commands;

import com.graphs.domain.Graph;
import com.graphs.exception.GraphException;

public interface Command {
	
	String execute(Graph graph, Object params) throws GraphException;

}