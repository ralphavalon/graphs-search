package com.graphs.enums;

import com.graphs.commands.Command;
import com.graphs.commands.DistanceCommand;
import com.graphs.commands.RoutesCommand;
import com.graphs.commands.ShortestCommand;
import com.graphs.commands.TripsCommand;

public enum Commands {
	
	DISTANCE("distance", new DistanceCommand()),
	TRIPS("trips", new TripsCommand()),
	SHORTEST("shortest", new ShortestCommand()),
	ROUTES("routes", new RoutesCommand());
	
	private String name;
	private Command command;
	
	private Commands(String name, Command command) {
		this.name = name;
		this.command = command;
	}
	
	public static Commands getByName(String name) {
		for (Commands commands : Commands.values()) {
			if(commands.name.equalsIgnoreCase(name)) {
				return commands;
			}
		}
		throw new IllegalArgumentException("No command found with name: " + name);
	}
	
	public Command getCommand() {
		return command;
	}

}