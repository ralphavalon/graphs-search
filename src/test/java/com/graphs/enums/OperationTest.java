package com.graphs.enums;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.graphs.AbstractTest;
import com.graphs.exception.NoSuchOperationException;

public class OperationTest extends AbstractTest {
	
	@Test
	public void shouldPassWhenOperationSignalDoesExistTest() throws IOException {
		Operation operation = Operation.findBySignal("<");
		assertEquals(Operation.LESS_THAN, operation);
		
		operation = Operation.findBySignal("<=");
		assertEquals(Operation.LESS_EQUAL_THAN, operation);
		
		operation = Operation.findBySignal("=");
		assertEquals(Operation.EQUAL_THAN, operation);
		
		operation = Operation.findBySignal(">");
		assertEquals(Operation.GREATER_THAN, operation);
		
		operation = Operation.findBySignal(">=");
		assertEquals(Operation.GREATER_EQUAL_THAN, operation);
	}
	
	@Test
	public void shouldFailWhenOperationSignalDoesNotExistTest() throws IOException {
		expect(NoSuchOperationException.class, "Operation signal '>>' not valid");
		Operation.findBySignal(">>");
	}
	
}