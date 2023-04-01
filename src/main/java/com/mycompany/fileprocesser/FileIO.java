package com.mycompany.fileprocesser;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class FileIO {
	
	public static void readFile(String path) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			System.out.println(reader.readLine());
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
}

