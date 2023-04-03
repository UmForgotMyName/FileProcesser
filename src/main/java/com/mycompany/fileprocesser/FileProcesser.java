/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fileprocesser;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tylerwarwick
 */
public class FileProcesser {

    public static void main(String[] args) {
        
    }
    //List method sorts the arraylist in regards to the max number
     public static List<File> ListMethod(List<File> entries, int max) throws IOException {
         // Parameter consists of the the arraylist and the max value
        List<File> result = new ArrayList<>();//Result arraylist which stores updated arraylist
        for (int i = 0; i < entries.size(); i++) {
            //statement if max value is 0
            if (max == 0) {
                break;
            }
            //statement if entry is a set of files
            if (entries.get(i).isFile()) {
                result.add(entries.get(i));
                max--;
                //statement if entry is a directory 
            } else if (entries.get(i).isDirectory()) {
                File[] temp = entries.get(i).listFiles();
                for (int j = 0; j < temp.length; j++) {
                    if (max == 0) {
                        break;
                    }
                    result.add(temp[j]);
                    max--;
                }
            }
        }
        //Program returns updated arraylist with set changes
        return result;
    }
    
    //Rename method which adds a suffix to the file name 
    public static List<File> RenameMethod(List<File> entries, String suffix) throws IOException {
        List<File> result = new ArrayList<>();//Result arraylist which stores updated arraylist
        //if entry is a file, rename method applies suffix to each individual file
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).isFile()) {
                String fileName = entries.get(i).getName();
                String[] parts = fileName.split("\\.");//Regular expression which looks for "." in file name
                String baseName = parts[0];
                String extension = "." + parts[1];
                String newName = baseName + suffix + extension;//newname string holds updated name
                File newFile = new File(entries.get(i).getParent(), newName);
                result.add(newFile);//Add updated arraylist to result array
                //If entry is a directory, program searches in directory and lists individual files
            } else if (entries.get(i).isDirectory()) {
                File[] temp = entries.get(i).listFiles();//temp storage for files in directory
                //Rename methods applied to temp array which holds files 
                for (int j = 0; j < temp.length; j++) {
                    String fileName = temp[j].getName();
                    String[] parts = fileName.split("\\.");
                    String baseName = parts[0];
                    String extension = "." + parts[1];
                    String newName = baseName + suffix + extension;
                    File newFile = new File(temp[j].getParent(), newName);
                    result.add(newFile);//Result arraylist receives updated names 
                }
            }
        }
        //program returns updated arraylist with set changes 
        return result;
    }    
    
}
