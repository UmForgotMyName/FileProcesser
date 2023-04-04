package com.mycompany.fileprocesser;

public class RemoteInstructions extends Instructions {
	private final String repositoryId;
	private final String entryId;
	
	public RemoteInstructions(String filePath) {
		super(filePath);
		this.repositoryId = (String) ((getInputEntries()).get("repositoryId"));
		this.entryId = (String) ((getInputEntries()).get("entryId"));
		
	}

	public void print() {
		System.out.print(this.locationType);
	}
	public String getRepositoryId() {
		return repositoryId;
	}
	
	public String getEntryId() {
		return entryId;
	}


}
