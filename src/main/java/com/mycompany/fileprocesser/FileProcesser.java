/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fileprocesser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author tylerwarwick, rehansiddiqi
 */
public class FileProcesser {

    public static void main(String[] args) {
        System.out.println("Hello Test!");
    }
    
    public List<File> filterName(List<File> entries, String key) {
        for (int i = 0; i < entries.size(); i++) {
            if (!entries.get(i).getName().equals(key)) {
                //entries.get(i).delete(); not sure if this is needed
                entries.remove(i);
            }
        }
        return entries;
    }

    public List<File> filterContent(List<File> entries, String key) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).isFile()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(entries.get(i)))) {
                    String line;
                    boolean flag = false;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(key)) {
                            flag = true;
                            break;
                        }
                    }
                    
                    if (!flag) {
                        //entries.get(i).delete(); not sure if this is needed
                        entries.remove(i);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //entries.get(i).delete(); not sure if this is needed
                entries.remove(i);
            }
        }
        return entries;
    }
}
