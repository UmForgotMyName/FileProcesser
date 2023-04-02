/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fileprocesser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tylerwarwick
 */
public class FileProcesser {

    public static void main(String[] args) {
        System.out.println("Hello Test!");
    }
    
    public static List<File> RenameMethod( List<File> entries, String suffix){
        
        List<File> renamedEntries = new ArrayList<File>();
       
        for (File file : entries) {
            String fileName = file.getName();
            String[] parts = fileName.split("\\.");
            String baseName = parts[0];
            String extension = "." + parts[1];
            String newName = baseName + suffix + extension;
            File newFile = new File(file.getParent(), newName);
            
            if (file.renameTo(newFile)) {
                System.out.println(newName);
                renamedEntries.add(newFile);
            } else {
                System.out.println("Failed to rename file: " + file.getName());
                renamedEntries.add(newFile);
            }
        }
        
        entries = renamedEntries;
        return entries;
    }
    
    public static List<File> ListMethod (List<File> entries, int MAX){
         if (entries.size() > MAX) {
            entries = entries.subList(0, MAX); // reduce ArrayList if too large
        } 
        
        System.out.println("Reduced ArrayList:");
        for (File file : entries) {
            System.out.println(file.getName());
            file.renameTo(new File("C:\\Users\\Zebaa\\OneDrive - University of Guelph\\newDummy\\" + file.getName()));
            
        }
        
        System.out.println(entries);
        return entries;
    }
}
