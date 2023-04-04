package com.mycompany.fileprocesser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalInstructions extends Instructions {
	private final String path;
	
	//Main constructor
	public LocalInstructions(String filePath) {
		super(filePath);
		this.path = (String) ((getInputEntries()).get("path"));
	}

	
	@Override
	public List<File> getInitialFiles() {
		List<File> files = new ArrayList<File>();
		files.add(new File(path));
		return files;
	}

	
	public String getPath() {
		return path;
	}


	
	

	
}
