package com.graphs;

import org.junit.Test;

public class MainTest extends AbstractTest {

	@Test
	public void shouldPassWhenDataFileIsGivenAndCommandFileIsGivenTest() {
		Main.main(new String[]{"data.txt", "commands.txt"});
		shouldPrintExactly("9");
	}
	
	@Test
	public void shouldFailWhenMissingDataFileTest() {
		expect(IllegalArgumentException.class ,"Data file is missing");
		Main.main(new String[]{});
		shouldNotPrintExactly("9");
	}
	
	@Test
	public void shouldFailWhenDataFileIsGivenButMissingCommandFileTest() {
		expect(IllegalArgumentException.class ,"Command file is missing");
		Main.main(new String[]{"data.txt"});
		shouldNotPrintExactly("9");
	}

	

}