package com.mycompany.fileprocesser;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public abstract class Instructions {
	
	//Location and processes should never be updated
	protected final String locationType;
	protected final JSONArray processingElements;
	
	//Constructor for instructions object
	public Instructions(String filePath) {
			this.locationType = getLocation(filePath);
			this.processingElements = getProcessingElements(storeJSON(filePath));
		}
		
	
	
	public abstract void print();
	
	
	//Static method to determine if we should create instance of local or remote instructions
	public static String getLocation(String filePath) {
		JSONObject obj = storeJSON(filePath);
		JSONObject element = (JSONObject)((JSONArray) obj.get("processing_elements")).get(0);
		JSONObject entries = (JSONObject)((JSONArray) element.get("input_entries")).get(0);
		return (String) entries.get("type");
		
	}
	
	//Store input text file as searchable JSON object
	private static JSONObject storeJSON(String filePath) {
		JSONParser parser = new JSONParser();
		Reader reader;
		
		//Read the file if it exists
		try {
			reader = new FileReader(filePath);
			JSONObject inputJSON = (JSONObject) parser.parse(reader);
			reader.close();
			return inputJSON;

		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	//Return array of all processing elements as JSON blocks
	protected JSONArray getProcessingElements(JSONObject obj) {
		JSONArray elements = (JSONArray) obj.get("processing_elements");
		return elements;

	}
	
	//Path and location type are defined via the fist processes's input entry
	//All other processes take the output of the previous process as their input
	//We define input entries method specifically for constructor
	protected JSONObject getInputEntries() {
		JSONObject obj = (JSONObject) this.processingElements.get(0);
		JSONArray temp = (JSONArray) obj.get("input_entries");
		return (JSONObject) temp.get(0);
		
	}
	
	//Return an array of JSON objects of all parameters for a certain process
	public static JSONArray getParameters(JSONObject obj) {
		JSONArray elements = (JSONArray) obj.get("parameters");
		return elements;
		
	}
	
	//Passed a parameter JSOn object and returns it's value key
	public static String getParamValue(JSONObject obj) {
		return (String) obj.get("value");
		
	}
	
	//WORKS
	//Returns parameter values directly from processing elements
	public static String processToParamValue(JSONObject process) {
		JSONObject param = (JSONObject) (getParameters(process).get(0));
		return (String) param.get("value");
		
	}
	
	//WORKS
	//Returns specifically indexed parameter values directly from processing elements
	public static String processToParamValue(JSONObject process, int i) {
		JSONObject param = (JSONObject) (getParameters(process).get(i));
		return (String) param.get("value");
		
	}


	
	
}

