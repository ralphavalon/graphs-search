package com.graphs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.graphs.exception.EmptyFileException;

public class MainTest extends AbstractTest {

	@Test
	public void shouldPassWhenDataFileIsGivenAndDoesExistAndCommandFileIsGivenAndDoesExistTest() throws IOException {
		Main.main(new String[]{"data.txt", "commands.txt"});
		shouldPrintlnExactly("9", "5", "13", "22", "NO SUCH ROUTE", "2", "9", "9");
	}
	
	@Test
	public void shouldFailWhenDataFileIsGivenButDoesNotExistTest() throws IOException {
		expect(FileNotFoundException.class ,"File \"not_existing_data.txt\" not found");
		Main.main(new String[]{"not_existing_data.txt", "commands.txt"});
	}
	
	@Test
	public void shouldFailWhenDataFileIsGivenAndDoesExistButIsEmptyTest() throws IOException {
		expect(EmptyFileException.class ,"The file empty_data.txt is empty");
		Main.main(new String[]{"empty_data.txt", "commands.txt"});
	}
	
	@Test
	public void shouldFailWhenDataFileIsGivenAndDoesExistAndCommandFileIsGivenAndDoesExistButIsEmptyTest() throws IOException {
		expect(EmptyFileException.class ,"The file empty_commands.txt is empty");
		Main.main(new String[]{"data.txt", "empty_commands.txt"});
	}
	
	@Test
	public void shouldFailWhenMissingDataFileTest() throws IOException {
		expect(IllegalArgumentException.class ,"Data file is missing");
		Main.main(new String[]{});
	}
	
	@Test
	public void shouldFailWhenDataFileIsGivenButMissingCommandFileTest() throws IOException {
		expect(IllegalArgumentException.class ,"Command file is missing");
		Main.main(new String[]{"data.txt"});
	}

}