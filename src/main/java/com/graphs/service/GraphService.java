package com.graphs.service;

import java.util.List;

public interface GraphService {
	
	List<String> execute(List<String> commandFileLines, List<String> dataFileLines);

}