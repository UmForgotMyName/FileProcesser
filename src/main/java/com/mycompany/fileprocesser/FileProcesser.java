/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fileprocesser;

/**
 *
 * @author tylerwarwick
 */
public class FileProcesser {
	
	
	
    public static void main(String[] args) {       
    	String filePath = new String("/home/tyler/Desktop/Test_Scenario.json");
    	Instructions instructions = (Instructions.getLocation(filePath).matches("local"))
    			? new LocalInstructions(filePath) : new RemoteInstructions(filePath);
    	
    	System.out.println(instructions.getClass());
    	System.out.println();
    	
    	instructions.print();
    	

    	
    
    	
    }
}

