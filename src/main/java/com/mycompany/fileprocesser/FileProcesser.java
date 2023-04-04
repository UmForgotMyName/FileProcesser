/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fileprocesser;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tylerwarwick
 */
public class FileProcesser {

    public static void main(String[] args) throws IOException {
         List<File> entries = new ArrayList<File>();
        entries.add(new File("C:\\Users\\Zebaa\\OneDrive - University of Guelph\\newDummy\\"));
       
      int  max = 2;
      String suffix = "joe";
       System.out.println(renameMethod(entries,suffix));
      //System.out.println(ListMethod(entries,max));
        
    }
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
}
