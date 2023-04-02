package com.mycompany.fileprocesser;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class Instructions {
	
	//Location, path and processes should never be updated
	private final String locationType;
	private final String path;
	private final JSONArray processingElements;
	
	//Constructor for instructions object
	public Instructions(String filePath) {
			this.processingElements = getProcessingElements(storeJSON(filePath));
			this.locationType = (String) ((getInputEntries()).get("type"));
			this.path = (String) ((getInputEntries()).get("path"));
		}
		
	//Basic Getter methods
	public String getLocationType() {
		return locationType;
	}

	public String getPath() {
		return path;
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
	protected JSONArray getParameters(JSONObject obj) {
		JSONArray elements = (JSONArray) obj.get("parameters");
		return elements;
		
	}




	
	
}

