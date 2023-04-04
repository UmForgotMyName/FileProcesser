package com.mycompany.LaserficheAPI;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files; // import for downloading to loca
import java.nio.file.Path; // import for downloading to local
import java.nio.file.Paths; // import for downloading to local
import java.nio.file.StandardCopyOption; // import for downloading to local
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class APIMethods {
    // Declare ArrayList to store exported files
    ArrayList <File> exportedFiles = new ArrayList<>();
    private static int i = 0;

    // create file path 
    static String path = System.getProperty("user.dir");
    static String filePath = "path/DownloadedFiles";
    static String fileOriginPath = path;

    // access Laserfiche repository
    final String servicePrincipalKey = "5Yx6jm1xDBV8k-InPeff";
    final String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiM2JiODdlM2EtOGViOS00YWE4LTljMDEtOTlhZjUwYzQwMTUxIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImI2S1d2WWJaZkpYNkhPVndLY0Jra2s2elRoSHRPQ2xBY0h2eEN3SDlmcjgiLAoJCSJ4IjogImZrbW5QVEhjWVV5VHlEUTlLOGZSMG5BWlhMWkxlcy1NaVg3dW5wYnZNRlkiLAoJCSJ5IjogIkE1bklIbHFHU0lKSWE2VHF2Ym9WYWNNMUZUZThYYk41SGRiUW05cGZaNDQiLAoJCSJkIjogIm9pTzhnSUtkZ3FKTTQzeFVtMzRQOVcwXzBXanJWRUM0VVFIMG9YY2FXNVEiLAoJCSJpYXQiOiAxNjc3Mjk3MjcxCgl9Cn0=";
    final String repositoryId = "r-0001d410ba56";    
    AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);
    RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(servicePrincipalKey, accessKey);
    
    // create new folder for files to be stored locally
    public void makeDirectory(){
        // Create new directory
        filePath = filePath.replace("path", path);// replace user computer name into filePath
//        fileOriginPath = fileOriginPath.replace("userID", userName);// replace user computer name into filePath

        File dir = new File(filePath);
        if (!dir.exists()) {
            boolean result = dir.mkdirs();
            if (result) {
                System.out.println("\nDirectory created successfully.");
            } 
            else {
                System.out.println("\nDirectory creation failed.");
            }
        }
        else {
            System.out.println("\nDirectory already exists.");
        } 
    }
    
//    // change between new file directory and original directory
//    public void changeDirectory(){
//        System.setProperty("user.dir", filePath);
//        System.out.println("Current Directory: " +System.getProperty("user.dir"));
//    }
    
        // Rest of the code

    public void downloadFiles(List<Entry> temp){
        // loop through all child entries
        for (Entry childEntry : temp) {
            // create and modify file based on entry Id
            int entryIdToDownload = childEntry.getId();
            String FILE_NAME = "entryIDnum.txt";
            String fileNum = String.valueOf(childEntry.getId());
            FILE_NAME = FILE_NAME.replace("num", fileNum);
            String[] fileNames = new String[100];
            fileNames[i] = FILE_NAME;

            // actually download file
            Consumer<InputStream> consumer = inputStream -> {
                File exportedFile = new File(fileNames[i]);
                try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int length = inputStream.read(buffer);
                        if (length == -1) {
                            break;
                        }
                        outputStream.write(buffer, 0, length);
                    }
                    // Add exported file to ArrayList
                    exportedFiles.add(exportedFile);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            
            // export file
            client.getEntriesClient().exportDocument(repositoryId, entryIdToDownload, null, consumer).join();

// comment out to not store files locally to directory
            // move files into created directory
            Path source = Paths.get(fileOriginPath + "/" + fileNames[i]);
            Path target = Paths.get(filePath + "/" + fileNames[i]);
            try {
                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            } 

        }
    }
    
    // close connection to API
    public void clientClose(){
        client.close();
    }
    
    // return file list to use as paramter
    public List <File> returnList(){
    List <File> newFile = new ArrayList <File> ();
    
    // save array list to new list
    for (File stored : exportedFiles) 
        {		      
            newFile.add(stored);
        }
    return newFile;
    }
    
    // print file data
    public void print(List<Entry> temp, Entry temp2){
        // print repo folder data
        System.out.println(String.format("\nEntry ID: %d, Name: %s, EntryType: %s, FullPath: %s",temp2.getId(), temp2.getName(), temp2.getEntryType(), temp2.getFullPath()));
        
        // access and print repo child file data
        for (Entry childEntry : temp) {
            System.out.println(String.format("Child Entry ID: %d, Name: %s, EntryType: %s, FullPath: %s, File Size (bytes): %d",childEntry.getId(), childEntry.getName(), childEntry.getEntryType(), childEntry.getFullPath(), childEntry.getName().length()));
        }
    }
}