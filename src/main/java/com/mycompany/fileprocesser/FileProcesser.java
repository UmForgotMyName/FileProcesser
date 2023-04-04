/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fileprocesser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;

/**
 *
 * @author tylerwarwick
 */
public class FileProcesser {
	
	
	
    public static void main(String[] args) throws IOException {       
    	String filePath = new String("/home/tyler/Desktop/Test_Scenario.json");
    	Instructions instructions = (Instructions.getLocation(filePath).matches("local"))
    			? new LocalInstructions(filePath) : new RemoteInstructions(filePath);
    	
    	List<File> files = ControlSequence.storeFiles("/home/tyler/Desktop/ExportedFolderContents");
    	
    	 System.out.println(ControlSequence.getMode((JSONObject) (instructions.processingElements).get(1)));
    
		List<File> files2 = Processes.ListMethod(files, 10);
		
		System.out.println(Instructions.processToParamValue((JSONObject) (instructions.processingElements).get(1)));
		System.out.println(Instructions.processToParamValue((JSONObject) (instructions.processingElements).get(1), 1));
    	
    
    	
    }
}

