package com.mycompany.fileprocesser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class FileProcesser 
{

    public static void printList(List<File> newFile)
    {
        String newS = newFile.toString();
        
        System.out.println(newS);
        
    }
    
    public static List<File> filterLength(List<File> file, long Length, String Operator)
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
    
    public static List<File> filterCount(List<File> file, String key, int value) throws IOException {
    List<File> newFile = new ArrayList<>();
    int keyCounter = 0;
    if (file != null) {
        for (File child : file) {
            BufferedReader br = new BufferedReader(new FileReader(child));
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (word.equals(key))
                        keyCounter++;
                }
            }
            br.close();
            if (keyCounter >= value)
                newFile.add(child);
            keyCounter = 0;
        }
    } else {
        System.out.println("An error has occurred!");
    }
    return newFile;
}

    // Not needed in the final bit of code, simply here for testing
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        // This gives an error so needs fixing
        File file = new File("C:\\Users\\hasan\\OneDrive\\Desktop\\Programming2\\Java\\Processing Files");
        List<File> dirList = new ArrayList<File>();
        if(file.isDirectory())
        {
                dirList = Arrays.asList(file);
        }
        else
        {
            dirList.add(file);
        }
        
        System.out.println("List of length: ");
        printList(filterLength(dirList ,20000 , "GTE"));
        
        System.out.println("List of count: ");
        printList(filterCount(dirList, "hasana", 1));
        
    }
}