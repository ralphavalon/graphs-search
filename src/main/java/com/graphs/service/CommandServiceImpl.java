package com.graphs.service;

import java.util.ArrayList;
import java.util.List;

import com.graphs.commands.Command;
import com.graphs.commands.Commands;
import com.graphs.domain.Graph;
import com.graphs.exception.GraphException;

public class CommandServiceImpl implements CommandService {
	
	public List<String> execute(Graph graph, List<String> commandFileLines) {
		List<String> results = new ArrayList<String>();
		
		for (String commandFileLine : commandFileLines) {
			final String[] commandInstructions = commandFileLine.split(" ");
			if(commandInstructions.length < 2) {
				throw new IllegalArgumentException("Missing command instructions. e.g.: distance A-B-C");
			}
			String commandName = commandInstructions[0];
			String vertexesToGo = commandInstructions[1];
			String param = null;
			if(commandInstructions.length > 2) {
				param = commandInstructions[2];
			}
			Command command = Commands.getByName(commandName).getCommand();
			try {
				results.add(command.execute(graph, vertexesToGo.split("-"), param));
			} catch (GraphException e) {
				results.add(e.getMessage());
			}
		}
		
		return results;
	}

}