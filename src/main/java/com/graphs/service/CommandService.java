package com.graphs.service;

import java.util.List;

import com.graphs.domain.Graph;

public interface CommandService {
	
	List<String> execute(Graph graph, List<String> commandFileLines);

}