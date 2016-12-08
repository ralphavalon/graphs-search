package com.graphs.commands;

public enum Commands {
	
	DISTANCE("distance", new DistanceCommand()),
	TRIPS("trips", new TripsCommand()),
	SHORTEST("shortest", new ShortestCommand());
	
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