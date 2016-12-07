package com.graphs.exception;

public class NoSuchOperationException extends RuntimeException {

	private static final long serialVersionUID = -8290885355790719498L;
	private String operationSignal;
	
	public NoSuchOperationException(String operationSignal) {
		this.operationSignal = operationSignal;
	}
	
	@Override
	public String getMessage() {
		return "Operation signal '" + operationSignal + "' not valid";
	}

}