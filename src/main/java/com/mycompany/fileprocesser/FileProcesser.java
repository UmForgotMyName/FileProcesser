package com.mycompany.fileprocesser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcesser 
{
    
    public static ArrayList<File> newFile = new ArrayList<File>();

    public static void printList()
    {
        String newS = newFile.toString();
        
        System.out.println(newS);
        
    }
    
    public static void filterLength(File[] file, long Length, String Operator)
    {
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
    }
    
    public static void filterCount(File[] file, String Key, int value) throws FileNotFoundException
    {
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
        printList();
        
        filterCount(dirList, "hasana", 1);
        System.out.println("List of count: ");
        printList();
        
    }
}