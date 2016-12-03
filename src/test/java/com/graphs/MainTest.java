package com.graphs;

import java.io.FileNotFoundException;

import org.junit.Test;

public class MainTest extends AbstractTest {

	@Test
	public void shouldPassWhenDataFileIsGivenAndDoesExistAndCommandFileIsGivenAndDoesExistTest() throws FileNotFoundException {
		Main.main(new String[]{"data.txt", "commands.txt"});
		shouldPrintExactly("9");
	}
	
	@Test
	public void shouldFailWhenDataFileIsGivenButDoesNotExistTest() throws FileNotFoundException {
		expect(FileNotFoundException.class ,"File \"not_existing_data.txt\" not found");
		Main.main(new String[]{"not_existing_data.txt", "commands.txt"});
	}
	
	@Test
	public void shouldFailWhenMissingDataFileTest() throws FileNotFoundException {
		expect(IllegalArgumentException.class ,"Data file is missing");
		Main.main(new String[]{});
	}
	
	@Test
	public void shouldFailWhenDataFileIsGivenButMissingCommandFileTest() throws FileNotFoundException {
		expect(IllegalArgumentException.class ,"Command file is missing");
		Main.main(new String[]{"data.txt"});
	}

}