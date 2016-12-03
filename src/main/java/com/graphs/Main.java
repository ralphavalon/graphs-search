package com.graphs;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		validate(args);
		File dataFile = getFile(args[0]);
		File commandFile = getFile(args[1]);
		System.out.print(9);
	}

	private static File getFile(String filename) throws FileNotFoundException {
		final File file = new File(filename);
		if( ! file.exists()) {
			throw new FileNotFoundException("File \"" + filename + "\" not found");
		}
		return file;
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