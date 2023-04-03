package com.mycompany.fileprocesser;

public class LocalInstructions extends Instructions {
	private final String path;
	
	
	public LocalInstructions(String filePath) {
		super(filePath);
		this.path = (String) ((getInputEntries()).get("path"));
	}


	public String getPath() {
		return path;
	}

	public void print() {
		System.out.println("Child");
	}


	
}
