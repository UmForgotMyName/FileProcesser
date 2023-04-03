package com.mycompany.LaserficheAPI;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.function.Consumer;

public class listMethods {
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
    
    // change between new file directory and original directory
    public void changeDirectory(){
        System.setProperty("user.dir", filePath);
        System.out.println("Current Directory: " +System.getProperty("user.dir"));
    }
    
    public void downloadFiles(List<Entry> temp){
        // loop through all child entries
        for (Entry childEntry : temp) {
            // create and modify file based on entry Id
            int entryIdToDownload = childEntry.getId(); // pass child Id
            String FILE_NAME = "entryIDnum.txt"; // file name template
            String fileNum = String.valueOf(childEntry.getId()); // store entry Id
            FILE_NAME = FILE_NAME.replace("num", fileNum);// replace entry Id into new file name
            String[] fileNames = new String[100];
            fileNames[i] = FILE_NAME; // finalize file name

            // actually download file
            Consumer<InputStream> consumer = inputStream -> {
                File exportedFile = new File(fileNames[i]); // idk why it wont take the FILE_NAME directly
                try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int length = inputStream.read(buffer);
                        if (length == -1) {
                            break;
                        }
                        outputStream.write(buffer, 0, length);
                    }
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
            
            // move files into created directory
            Path source = Paths.get(fileOriginPath + "/" + fileNames[i]);
            Path target = Paths.get(filePath + "/" + fileNames[i]);
            try {
                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
    
    public void clientClose(){
        client.close();
    }
    
    public void print(List<Entry> temp, Entry temp2){
        System.out.println(String.format("\nEntry ID: %d, Name: %s, EntryType: %s, FullPath: %s",temp2.getId(), temp2.getName(), temp2.getEntryType(), temp2.getFullPath()));
        for (Entry childEntry : temp) {
            System.out.println(String.format("Child Entry ID: %d, Name: %s, EntryType: %s, FullPath: %s",childEntry.getId(), childEntry.getName(), childEntry.getEntryType(), childEntry.getFullPath()));
            System.out.println("File Size: %d", childEntry.getName().length())
        }
    }
}