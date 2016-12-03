package com.graphs;

public class Main {
	
	public static void main(String[] args) {
		validate(args);
		System.out.print(9);
	}

	private static void validate(String[] args) {
		if(args.length == 0) {
			throw new IllegalArgumentException("Data file is missing");
		}
		if(args.length == 1) {
			throw new IllegalArgumentException("Command file is missing");
		}
	}

}