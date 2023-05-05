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

public class FileProcesser 
{
        
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
    public static List<File> FilePrint(List<File> entries) 
    {       
        for (Object entry : entries) 
        {
            if (entry instanceof File file) 
            {
                System.out.println("Name: " + file.getName());
                System.out.println("Length: " + file.length());
                System.out.println("Absolute path: " + file.getAbsolutePath());
             }
         }
         return entries;
    }
}
