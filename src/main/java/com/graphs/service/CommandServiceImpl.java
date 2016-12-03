package com.graphs.service;

import java.util.ArrayList;
import java.util.List;

import com.graphs.commands.Command;
import com.graphs.commands.Commands;
import com.graphs.domain.Graph;

public class CommandServiceImpl implements CommandService {
	
	public List<String> execute(Graph graph, List<String> commandFileLines) {
		List<String> results = new ArrayList<String>();
		
		for (String commandFileLine : commandFileLines) {
			final String[] commandInstructions = commandFileLine.split(" ");
			String commandName = commandInstructions[0];
			Command command = Commands.getByName(commandName).getCommand();
			results.add(command.execute(new Graph()));
		}
		
		return results;
	}

}