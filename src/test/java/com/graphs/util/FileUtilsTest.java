package com.graphs.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.graphs.AbstractTest;
import com.graphs.exception.EmptyFileException;

public class FileUtilsTest extends AbstractTest {
	
	private String[] dataLines = { "AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7" };
	
	@Test
	public void shouldPassWhenFileIsGivenAndDoesExistTest() throws IOException {
		final File file = FileUtils.getFile("data.txt");
		assertNotNull(file);
	}
	
	@Test
	public void shouldFailWhenFileIsGivenAndDoesNotExistTest() throws IOException {
		expect(FileNotFoundException.class ,"File \"not_existing_data.txt\" not found");
		FileUtils.getFile("not_existing_data.txt");
	}
	
	@Test
	public void shouldPassWhenReadingFileWithContentTest() throws IOException {
		final List<String> lines = FileUtils.readLines(new File("data.txt"));
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			assertEquals(dataLines[i], line);
		}
	}
	
	@Test
	public void shouldFailWhenReadingFileWithoutContentTest() throws IOException {
		expect(EmptyFileException.class ,"The file empty_data.txt is empty");
		FileUtils.readLines(new File("empty_data.txt"));
	}
	
}