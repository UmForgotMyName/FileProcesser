package com.mycompany.fileprocesser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileProcesser {

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
        filterLength fL = new filterLength(dirList ,0 , "NEQ");
        fL.lengther();
        System.out.println("List of length: ");
        fL.printList();
        
        filterCount fl = new filterCount(dirList, "hasana", 1);
        fl.counter();
        System.out.println("List of count: ");
        fl.printList();
        
    }
}