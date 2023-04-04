package com.mycompany.fileprocesser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class FileProcesser 
{

    // Not included in the final project simply here for testing
    public static void printList(List<File> newFile)
    {
        String newS = newFile.toString();
        
        System.out.println(newS);
        
    }
    
    // Method to filter the files by length (size in bytes)
    public static List<File> filterLength (List<File> file, long Length, String Operator)
    {
        // Create a list of file entries
        List<File> newFile = new ArrayList<File>();
        
        // Switch case with cases of each operator
        switch(Operator)
        {
            // Case for equals operator
            case "EQ":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() == Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
            
            // Case for not equals operator
            case "NEQ":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() != Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
                
            // Case for greater than operator
            case "GT":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() > Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
                
            // Case for greater than or equal to operator
            case "GTE":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() >= Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
                
            // Case for less than operator
            case "LT":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() < Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    System.out.println("An error has occured!");
                }
                break;
                
            // Case for less than or equal to operator
            case "LTE":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() <= Length && child.isFile())
                        {
                            newFile.add(child);
                        }
                        else if(child.isDirectory())
                        {
                            List<File> tempList = Arrays.asList(child.listFiles());
                            tempList = filterLength(tempList, Length, Operator);
                            for(File tempChild : tempList)
                                newFile.add(tempChild);
                        }
                    }
                }
                else
                {
                    // If a file is null, an error has occured
                    System.out.println("An error has occured!");
                }
                break;
        }
        // Return list with new file(s)
        return newFile;
    }
    
    // Method to filter out files that do not contain the specifed word a specifed number of times
    public static List<File> filterCount(List<File> file, String key, int value) throws IOException 
    {
    
        // Create a list of file entries
        List<File> newFile = new ArrayList<>();
        
        // Initalize keyCounter to be equal to 0
        int keyCounter = 0;
        
        // Checks if value is greater than 0
        if(value<=0)
        {
            System.out.println("An error has occured, returning original file");
            return file;
        }
        else
        {
            // Check if file exists
            if (file != null) 
            {
                // Read every file within the directory (works for individual file)
                for (File child : file) 
                {
                    // Checks if it is a file
                    if(child.isFile())
                    {
                        // Create buffered reader object
                        BufferedReader br = new BufferedReader(new FileReader(child));
                        // Initalize line string
                        String line;
                        // A while loop that ends if the file does not contain another line
                        while ((line = br.readLine()) != null) 
                        {
                            // Split the words by space
                            String[] words = line.split(" ");

                            // For each loop to loop through every word in the file
                            for (String word : words) 
                            {
                                // If the word occurs in the file, increment keyCounter by one
                                if (word.equals(key))
                                    keyCounter++;
                            }
                        }
                        // Close buffered reader
                        br.close();

                        // Checks if the word is said a specified amount of times
                        if (keyCounter >= value)
                        newFile.add(child);
                        keyCounter = 0;
                    }

                    // Checks if it is a directory
                    else if(child.isDirectory())
                    {
                        // Goes through each file of the directory as a recursive function
                        List<File> tempList = Arrays.asList(child.listFiles());
                        tempList = filterCount(tempList, key, value);
                        for(File tempChild : tempList)
                            newFile.add(tempChild);
                    }
                }
            } 
            // Print error message if error has occured
            else 
                {
                    System.out.println("An error has occurred!");
                }
            // Return new file
            return newFile;
        }
}

    
    /*
    
    REHANS CODE
    
    */
    public static List<File> filterName(List<File> entries, String key) {
        List<File> newFile = new ArrayList<File>();
        for (File entry : entries)
        {
            if(entry.isFile())
            {
                if (entry.getName().contains(key))
                {
                    newFile.add(entry);
                }
            }
            else if(entry.isDirectory())
            {
                List<File> tempList = Arrays.asList(entry.listFiles());
                tempList = filterName(tempList,key);
                for (File tempChild: tempList)
                    newFile.add(tempChild);
            }
            
        }
        return newFile;
    }
    
    public static List<File> filterContent(List<File> entries, String key) throws IOException
    {
        List<File> newFile = new ArrayList<File>();
        Outer:
        for (File entry : entries) 
        {
            if (entry.isFile()) 
            {
                // Create buffered reader object
                        BufferedReader br = new BufferedReader(new FileReader(entry));
                        // Initalize line string
                        String line;
                        // A while loop that ends if the file does not contain another line
                        while ((line = br.readLine()) != null) 
                        {
                            // Split the words by space
                            String[] words = line.split(" ");

                            // For each loop to loop through every word in the file
                            for (String word : words) 
                            {
                                // If the word occurs in the file, increment keyCounter by one
                                if (word.equals(key))
                                {
                                    newFile.add(entry);
                                    continue Outer;
                                }
                            }
                        }
                        // Close buffered reader
                        br.close();
            }
            
            else if(entry.isDirectory())
            {
                List<File> tempList = Arrays.asList(entry.listFiles());
                tempList = filterContent(tempList,key);
                for (File tempChild: tempList)
                    newFile.add(tempChild);
            }
        }
        return newFile;
    }
    
    /*
    
    END OF REHANS CODE
    
    */
    
    
    /*
    Not needed in the final bit, simply here for testing
    The code works if parameters are correct, use the following to test
    */
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        List<File> dirList = new ArrayList<File>();
        dirList.add(new File("C:\\Users\\hasan\\OneDrive\\Desktop\\Programming2\\Java\\Processing Files"));
        
        System.out.println("List of length: ");
        printList(filterLength(dirList ,19209  , "EQ"));
        
        System.out.println("List of count: ");
        printList(filterCount(dirList, "hasana", 1));
        
    }
}