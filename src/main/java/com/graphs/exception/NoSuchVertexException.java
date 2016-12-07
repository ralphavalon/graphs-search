package com.graphs.exception;

public class NoSuchVertexException extends GraphException {

	private static final long serialVersionUID = 5029613449431411297L;
	
	private String vertexId;
	
	public NoSuchVertexException(String vertexId) {
		this.vertexId = vertexId;
	}
	
	@Override
	public String getMessage() {
		return "Vertex '" + vertexId + "' not found";
	}

}