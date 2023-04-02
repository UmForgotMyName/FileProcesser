package com.mycompany.fileprocesser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcesser 
{

    public static void printList(List<File> newFile)
    {
        String newS = newFile.toString();
        
        System.out.println(newS);
        
    }
    
    public static List<File> filterLength(File[] file, long Length, String Operator)
    {
        List<File> newFile = new ArrayList<File>();
        switch(Operator)
        {
            case "EQ":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() == Length)
                        {
                            newFile.add(child);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
                
            case "NEQ":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() != Length)
                        {
                            newFile.add(child);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
                
            case "GT":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() > Length)
                        {
                            newFile.add(child);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
            case "GTE":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() >= Length)
                        {
                            newFile.add(child);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
            case "LT":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() < Length)
                        {
                            newFile.add(child);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
            case "LTE":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() <= Length)
                        {
                            newFile.add(child);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
        }
        return newFile;
    }
    
    public static List<File> filterCount(File[] file, String Key, int value) throws FileNotFoundException
    {
        List<File> newFile = new ArrayList<File>();
        int keyCounter = 0;
        if (file != null)
                {
                    for (File child : file)
                    {
                        String[] words = null;
                        Scanner inp = new Scanner(child);
                        String splitter;
                            while(inp.hasNext())
                            {
                                splitter = inp.nextLine();
                                words = splitter.split(" ");
                                for (String word : words)
                                {
                                    if(word.equals(Key))
                                        keyCounter++;
                                }
                            }
                            if(keyCounter >= value)
                                newFile.add(child);
                            keyCounter = 0;
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
        return newFile;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        File file = new File("C:\\Users\\hasan\\OneDrive\\Desktop\\Programming2\\Java\\Processing Files");
        File[] dirList = {file};
        if(file.isDirectory())
        {
            dirList = file.listFiles();
        }
        else
        {
            dirList[0] = file;
        }
        filterLength(dirList ,20000 , "GTE");
        System.out.println("List of length: ");
        printList(filterLength(dirList ,20000 , "GTE"));
        
        System.out.println("List of count: ");
        printList(filterCount(dirList, "hasana", 1));
        
    }
}