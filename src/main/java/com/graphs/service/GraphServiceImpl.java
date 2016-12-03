package com.graphs.service;

import java.util.List;

import com.graphs.domain.Graph;

public class GraphServiceImpl implements GraphService {

	private CommandService commandService = new CommandServiceImpl();
	
	@Override
	public List<String> execute(List<String> commandFileLines, List<String> dataFileLines) {
		return commandService.execute(new Graph(), commandFileLines);
	}
	
}
