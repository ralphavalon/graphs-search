package com.graphs.commands;

import com.graphs.domain.Graph;

public interface Command {
	
	String execute(Graph graph, Object... params);

}