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
    
     public static List<File> ListMethod(List<File> entries, int max) {
        List<File> result = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            if (max == 0) {
                break;
            }
            if (entries.get(i).isFile()) {
                result.add(entries.get(i));
                max--;
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
        return result;
    }
     
    public static List<File> RenameMethod(List<File> entries, String suffix) {
        List<File> result = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).isFile()) {
                String fileName = entries.get(i).getName();
                String[] parts = fileName.split("\\.");
                String baseName = parts[0];
                String extension = "." + parts[1];
                String newName = baseName + suffix + extension;
                File newFile = new File(entries.get(i).getParent(), newName);
                result.add(newFile);
            } else if (entries.get(i).isDirectory()) {
                File[] temp = entries.get(i).listFiles();
                for (int j = 0; j < temp.length; j++) {
                    String fileName = temp[j].getName();
                    String[] parts = fileName.split("\\.");
                    String baseName = parts[0];
                    String extension = "." + parts[1];
                    String newName = baseName + suffix + extension;
                    File newFile = new File(temp[j].getParent(), newName);
                    result.add(newFile);
                }
            }
        }
        return result;
    }    
    
}
