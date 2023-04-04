package com.mycompany.fileprocesser;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class filterCount {
    private String Key;
    private int value;
    private int keyCounter = 0;
    private File[] file;
    private ArrayList<File> newFile = new ArrayList<File>();
    
    public filterCount(File[] file, String Key, int value)
    {
        this.file = file;
        this.Key = Key;
        if (value >= 0)
            this.value = value;
        else
            this.value = -value;
    }
    
    public void counter() throws FileNotFoundException
    {
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
    
    public void printList()
    {
        String newS = newFile.toString();
        
        System.out.println(newS);
        
    }
    
}
