package com.graphs.exception;

public class NoSuchRouteException extends GraphException {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "NO SUCH ROUTE";
	}
	
}