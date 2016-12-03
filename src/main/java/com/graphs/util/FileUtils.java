package com.graphs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.graphs.exception.EmptyFileException;

public class FileUtils {
	
	public static List<String> readLines(File file) throws IOException, FileNotFoundException {
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    List<String> lines = new ArrayList<String>();
		    String line = br.readLine();
		    if(line == null || "".equals(line.trim())) {
		    	throw new EmptyFileException(file.getName());
		    }

		    while (line != null) {
		        lines.add(line);
		        line = br.readLine();
		    }
		    return lines;
		}
	}

	public static File getFile(String filename) throws FileNotFoundException {
		final File file = new File(filename);
		if( ! file.exists()) {
			throw new FileNotFoundException("File \"" + filename + "\" not found");
		}
		return file;
	}

}
