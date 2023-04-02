/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.fileprocesser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tylerwarwick, rehansiddiqi
 */
public class FileProcesser {

    public static void main(String[] args) {
        System.out.println("Hello Test!");
    }

    public static List<File> filterName(List<File> entries, String key) {
        for (int i = 0; i < entries.size(); i++) {
            if (!entries.get(i).getName().equals(key)) {
                //entries.get(i).delete(); not sure if this is needed
                entries.remove(i);
            }
        }
        return entries;
    }

    public static List<File> filterContent(List<File> entries, String key) {
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

    public static List<File> renameMethod(List<File> entries, String suffix) {
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

    public static List<File> FileSplit(List<File> entries, int lines) throws IOException {
        //Split files and store in output list
        List<File> result = new ArrayList<>();
        for (File entry : entries) {
            if (entry.isFile()) {
                BufferedReader reader = new BufferedReader(new FileReader(entry));
                String line;
                int count = 1;
                int part = 1;
                List<String> linesToWrite = new ArrayList<>(lines);
                while ((line = reader.readLine()) != null) {
                    linesToWrite.add(line);
                    if (count % lines == 0) {
                        File outFile = new File(entry.getParentFile(), entry.getName() + ".part" + part + ".txt");
                        result.add(outFile);
                        part++;
                        try (PrintWriter writer = new PrintWriter(new FileWriter(outFile))) {
                            for (String s : linesToWrite) {
                                writer.println(s);
                            }
                        }
                        linesToWrite.clear();
                    }
                    count++;
                }
                //Puts the last few lines into the last new file
                if (!linesToWrite.isEmpty()) {
                    File outFile = new File(entry.getParentFile(), entry.getName() + ".part" + part + ".txt");
                    result.add(outFile);
                    try (PrintWriter writer = new PrintWriter(new FileWriter(outFile))) {
                        for (String s : linesToWrite) {
                            writer.println(s);
                        }
                    }
                }
            }
        }
        return result;
    }

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

