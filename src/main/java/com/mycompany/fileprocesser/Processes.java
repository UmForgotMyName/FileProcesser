package com.mycompany.fileprocesser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Processes {
	
	
	//**********************FILTER METHOD BY LENGTH*********************************************
	// Method to filter the files by length (size in bytes)
    public static List<File> filterLength (List<File> file, long Length, String Operator)
    {
        // Create a list of file entries
        List<File> newFile = new ArrayList<File>();
        
        // Switch case with cases of each operator
        switch(Operator)
        {
            // Case for equals operator
            case "EQ":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() == Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
            
            // Case for not equals operator
            case "NEQ":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() != Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
                
            // Case for greater than operator
            case "GT":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() > Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
                
            // Case for greater than or equal to operator
            case "GTE":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() >= Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
                
            // Case for less than operator
            case "LT":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() < Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
                
            // Case for less than or equal to operator
            case "LTE":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() <= Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    // If a file is null, an error has occured
                    System.out.println("An error has occured!");
                }
                break;
        }
        // Return list with new file(s)
        return newFile;
    }
	
	
  //**********************FILTER METHOD BY KEYWORD COUNT*********************************************
    // Method to filter out files that do not contain the specifed word a specifed number of times
    public static List<File> filterCount(List<File> file, String key, int value) throws IOException 
    {
    
        // Create a list of file entries
        List<File> newFile = new ArrayList<>();
        
        // Initalize keyCounter to be equal to 0
        int keyCounter = 0;
        
        // Checks if value is greater than 0
        if(value<=0)
        {
            System.out.println("An error has occured, returning original file");
            return file;
        }
        else
        {
            // Check if file exists
            if (file != null) 
            {
                // Read every file within the directory (works for individual file)
                for (File child : file) 
                {
                    // Checks if it is a file
                    if(child.isFile())
                    {
                        // Create buffered reader object
                        BufferedReader br = new BufferedReader(new FileReader(child));
                        // Initalize line string
                        String line;
                        // A while loop that ends if the file does not contain another line
                        while ((line = br.readLine()) != null) 
                        {
                            // Split the words by space
                            String[] words = line.split(" ");

                            // For each loop to loop through every word in the file
                            for (String word : words) 
                            {
                                // If the word occurs in the file, increment keyCounter by one
                                if (word.equals(key))
                                    keyCounter++;
                            }
                        }
                        // Close buffered reader
                        br.close();

                        // Checks if the word is said a specified amount of times
                        if (keyCounter >= value)
                        newFile.add(child);
                        keyCounter = 0;
                    }

                    // Checks if it is a directory
                    else if(child.isDirectory())
                    {
                        // Goes through each file of the directory as a recursive function
                        List<File> tempList = Arrays.asList(child.listFiles());
                        tempList = filterCount(tempList, key, value);
                        for(File tempChild : tempList)
                            newFile.add(tempChild);
                    }
                }
            } 
            // Print error message if error has occured
            else 
                {
                    System.out.println("An error has occurred!");
                }
            // Return new file
            return newFile;
        }
}
	
    //**********************LIST METHOD*********************************************
    //List method sorts the arraylist in regards to the max number
    public static List<File> ListMethod(List<File> entries, int max) throws IOException {
    List<File> result = new ArrayList<>();//Result arraylist which stores updated arraylist
    for (File entry : entries) {
        //statement if max value is 0
        if (max == 0) {
            break;
        }
        //statement if entry is a file
        if (entry.isFile()) {
            result.add(entry);
            max--;
        }
        //statement if entry is a directory 
        else if (entry.isDirectory()) {
            List<File> tempList = Arrays.asList(entry.listFiles());
           tempList=ListMethod(tempList,max);
            for (File child : tempList) {
                if (max == 0) {
                    break;
                }
                result.add(child);
            }
        }
    }
    //Program returns updated arraylist with set changes
    return result;
}
    
    //**********************RENAME METHOD*********************************************
    //Rename method which adds a suffix to the file name 
    public static List<File> renameMethod(List<File> entries, String suffix) throws IOException {
    List<File> result = new ArrayList<>();//Result arraylist which stores updated arraylist
    //if entry is a file, rename method applies suffix to each individual file
    for (int i = 0; i < entries.size(); i++) {
        File entry = entries.get(i);
        if (entry.isFile()) {
            String fileName = entry.getName();
            String[] parts = fileName.split("\\.");//Regular expression which looks for "." in file name
            String baseName = parts[0];
            String extension = "." + parts[1];
            String newName = baseName + suffix + extension;//newname string holds updated name
            
            
            File newFile = new File(entry.getParent(), newName);
            if (entry.renameTo(newFile)) {
            	result.add(newFile);
            } else {
            	throw new IOException("Failed to rename file: " + entry.getAbsolutePath());
            }
            //If entry is a directory, program searches in directory and lists individual files
        	} else if (entry.isDirectory()) {

             List<File> tempList = Arrays.asList(entry.listFiles());
           tempList=renameMethod(tempList,suffix);
            for (File child : tempList) {
                
                
                result.add(child);
            }
        }
    }
    //program returns updated arraylist with set changes 
    return result;
}

   
   
   
   //**********************FILTER BY NAME METHOD*********************************************

    public static List<File> filterName(List<File> entries, String key) {
        List<File> newFile = new ArrayList<File>();
        for (File entry : entries)
        {
            if(entry.isFile())
            {
                if (entry.getName().contains(key))
                {
                    newFile.add(entry);
                }
            }
            else if(entry.isDirectory())
            {
                List<File> tempList = Arrays.asList(entry.listFiles());
                tempList = filterName(tempList,key);
                for (File tempChild: tempList)
                    newFile.add(tempChild);
            }
            
        }
        return newFile;
    }

   //**********************FILTER BY CONTENT METHOD*********************************************
    public static List<File> filterContent(List<File> entries, String key) throws IOException
    {
        List<File> newFile = new ArrayList<File>();
        Outer:
        for (File entry : entries) 
        {
            if (entry.isFile()) 
            {
                // Create buffered reader object
                        BufferedReader br = new BufferedReader(new FileReader(entry));
                        // Initalize line string
                        String line;
                        // A while loop that ends if the file does not contain another line
                        while ((line = br.readLine()) != null) 
                        {
                            // Split the words by space
                            String[] words = line.split(" ");

                            // For each loop to loop through every word in the file
                            for (String word : words) 
                            {
                                // If the word occurs in the file, increment keyCounter by one
                                if (word.equals(key))
                                {
                                    newFile.add(entry);
                                    continue Outer;
                                }
                            }
                        }
                        // Close buffered reader
                        br.close();
            }
            
            else if(entry.isDirectory())
            {
                List<File> tempList = Arrays.asList(entry.listFiles());
                tempList = filterContent(tempList,key);
                for (File tempChild: tempList)
                    newFile.add(tempChild);
            }
        }
        return newFile;
    }
   
   
   
   
   //**********************************SPLIT METHOD*********************************************

    public static List<File> FileSplit(List<File> entries, int lines) throws IOException 
    {
        // Split files and store in output list
        List<File> result = new ArrayList<File>();
        
        for (File entry : entries) 
        {
            if (entry.isFile()) 
            {
                BufferedReader reader = new BufferedReader(new FileReader(entry));
                String line;
                int count = 1;
                int part = 1;
                List<String> linesToWrite = new ArrayList<>(lines);

                // Splitting the file into parts
                while ((line = reader.readLine()) != null) 
                {
                    linesToWrite.add(line);

                    // If the number of lines has reached the limit, write the part to a file
                    if (count % lines == 0) 
                    {
                        File outFile = new File(entry.getParentFile(), entry.getName() + ".part" + part + ".txt");
                        result.add(outFile);
                        part++;

                        try (PrintWriter writer = new PrintWriter(new FileWriter(outFile))) 
                        {
                            for (String s : linesToWrite) 
                            {
                                writer.println(s);
                            }
                        }
                        linesToWrite.clear();
                    }
                    count++;
                }

                // If there are remaining lines, write them to the last part file
                if (!linesToWrite.isEmpty()) 
                {
                    File outFile = new File(entry.getParentFile(), entry.getName() + ".part" + part + ".txt");
                    result.add(outFile);

                    try (PrintWriter writer = new PrintWriter(new FileWriter(outFile))) 
                    {
                        for (String s : linesToWrite) 
                        {
                            writer.println(s);
                        }
                    }
                }
            }
            else if(entry.isDirectory())
            {
                List<File> tempList = Arrays.asList(entry.listFiles());
                tempList = FileSplit(tempList,lines);
                for (File tempChild: tempList)
                    result.add(tempChild);
            }
        }
        return result;
    }
   
  
   
 //**********************PRINT METHOD*********************************************
   public static List<File> FilePrint(List<File> entries) {

       for (Object entry : entries) {
           if (entry instanceof File file) {
               System.out.println("Name: " + file.getName());
               System.out.println("Length: " + file.length());
               System.out.println("Absolute path: " + file.getAbsolutePath());
           }
       }
       return entries;
   }
    
}
