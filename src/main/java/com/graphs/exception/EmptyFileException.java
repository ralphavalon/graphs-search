package com.graphs.exception;

import java.io.IOException;

public class EmptyFileException extends IOException {
	
	private static final long serialVersionUID = 1L;
	
	private String filename;
 
    public EmptyFileException(String filename) {
        this.filename = filename;
    }
 
    @Override
    public String getMessage() {
        return "The file " + filename + " is empty.";
    }
}