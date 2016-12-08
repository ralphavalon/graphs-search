package com.graphs.enums;

import com.graphs.exception.NoSuchOperationException;

public enum Operation {
	
	LESS_THAN("<") {
		@Override
		public boolean evaluate(int first, int second) {
			return getStops(first) < second;
		}
	},
	LESS_EQUAL_THAN("<=") {
		@Override
		public boolean evaluate(int first, int second) {
			return getStops(first) <= second;
		}
	},
	EQUAL_THAN("=") {
		@Override
		public boolean evaluate(int first, int second) {
			return getStops(first) == second;
		}
	},
	GREATER_THAN(">") {
		@Override
		public boolean evaluate(int first, int second) {
			return getStops(first) > second;
		}
	},
	GREATER_EQUAL_THAN(">=") {
		@Override
		public boolean evaluate(int first, int second) {
			return getStops(first) >= second;
		}
	};
	
	private String operationSignal;
	
	private Operation(String operationSignal) {
		this.operationSignal = operationSignal;
	}
	
	private static int getStops(int first) {
		return first - 1;
	}
	
	public abstract boolean evaluate(int first, int second);
	
	public static Operation findBySignal(String operationSignal) {
		for (Operation operation : Operation.values()) {
			if(operation.operationSignal.equals(operationSignal)) {
				return operation;
			}
		}
		throw new NoSuchOperationException(operationSignal);
	}
	
}