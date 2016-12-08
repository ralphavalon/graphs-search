package com.graphs.service;

import java.util.List;

import com.graphs.domain.Graph;

public interface GraphService {
	
	Graph getGraph(List<String> dataFileLines);

}