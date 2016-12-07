package com.graphs;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.graphs.service.GraphService;
import com.graphs.service.GraphServiceImpl;
import com.graphs.util.FileUtils;

public class Main {
	
	public static void main(String[] args) throws IOException {
		validate(args);
		File dataFile = FileUtils.getFile(args[0]);
		File commandFile = FileUtils.getFile(args[1]);
		
		final List<String> dataFileLines = FileUtils.readLines(dataFile);
		final List<String> commandFileLines = FileUtils.readLines(commandFile);
		
		GraphService graphService = new GraphServiceImpl();
		List<String> results = graphService.execute(commandFileLines, dataFileLines);
		for (String result : results) {
			System.out.println(result);
		}
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