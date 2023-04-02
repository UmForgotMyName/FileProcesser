package com.mycompany.fileprocesser;
import java.io.File;
import java.util.ArrayList;

public class filterLength
{
    private long Length;
    private String Operator;
    private File[] file;
    private ArrayList<File> newFile = new ArrayList<File>();
    public filterLength(File[] file, long Length, String Operator)
    {
        this.file = file;
        this.Length = Length;
        this.Operator = Operator;
    }

    public void lengther()
    {
        switch(Operator)
        {
            case "EQ":
                if (file != null)
                {
                    for (File child : file)
                    {
                        if(child.length() == this.Length)
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
                        if(child.length() != this.Length)
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
                        if(child.length() > this.Length)
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
                        if(child.length() >= this.Length)
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
                        if(child.length() < this.Length)
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
                        if(child.length() <= this.Length)
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
    
    public void printList()
    {
        String newS = newFile.toString();
        
        System.out.println(newS);
        
    }
    
}