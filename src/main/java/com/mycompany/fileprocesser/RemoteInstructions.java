package com.mycompany.fileprocesser;

import java.io.File;
import java.util.List;

public class RemoteInstructions extends Instructions {
	private final String repositoryId;
	private final String entryId;
	
	public RemoteInstructions(String filePath) {
		super(filePath);
		this.repositoryId = (String) ((getInputEntries()).get("repositoryId"));
		this.entryId = (String) ((getInputEntries()).get("entryId"));
		
	}

	
	
	@Override
	public List<File> getInitialFiles() {
		// TODO Auto-generated method stub
		return APIMethods.returnList(); 
	}
	
	
	
	
	public String getRepositoryId() {
		return repositoryId;
	}
	
	public String getEntryId() {
		return entryId;
	}





	


}
