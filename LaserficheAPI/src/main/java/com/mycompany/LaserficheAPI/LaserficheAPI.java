package com.mycompany.LaserficheAPI;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfEntry;

import java.util.List;

public class LaserficheAPI {
    public static void main(String[] args) {
        // Access Laserfiche API
        final String servicePrincipalKey = "5Yx6jm1xDBV8k-InPeff";
        final String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiM2JiODdlM2EtOGViOS00YWE4LTljMDEtOTlhZjUwYzQwMTUxIiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogImI2S1d2WWJaZkpYNkhPVndLY0Jra2s2elRoSHRPQ2xBY0h2eEN3SDlmcjgiLAoJCSJ4IjogImZrbW5QVEhjWVV5VHlEUTlLOGZSMG5BWlhMWkxlcy1NaVg3dW5wYnZNRlkiLAoJCSJ5IjogIkE1bklIbHFHU0lKSWE2VHF2Ym9WYWNNMUZUZThYYk41SGRiUW05cGZaNDQiLAoJCSJkIjogIm9pTzhnSUtkZ3FKTTQzeFVtMzRQOVcwXzBXanJWRUM0VVFIMG9YY2FXNVEiLAoJCSJpYXQiOiAxNjc3Mjk3MjcxCgl9Cn0=";
	final String repositoryId = "r-0001d410ba56";
        
        // Access key objects for API
        AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);
        RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(servicePrincipalKey, accessKey);

        // Get information about the ROOT entries
        int rootEntryId1 = 15; 
        int rootEntryId2 = 18;
        int rootEntryId3 = 4;
        int rootEntryId4 = 23;
                
        // create entry objects
        Entry entry1 = client.getEntriesClient().getEntry(repositoryId, rootEntryId1, null).join();
        Entry entry2 = client.getEntriesClient().getEntry(repositoryId, rootEntryId2, null).join();
        Entry entry3 = client.getEntriesClient().getEntry(repositoryId, rootEntryId3, null).join();
        Entry entry4 = client.getEntriesClient().getEntry(repositoryId, rootEntryId4, null).join();

        // Get information about the child entries of the Root entry
        ODataValueContextOfIListOfEntry result1 = client.getEntriesClient().getEntryListing(repositoryId, rootEntryId1, true, null, null, null, null, null, "name", null, null, null).join();
        ODataValueContextOfIListOfEntry result2 = client.getEntriesClient().getEntryListing(repositoryId, rootEntryId2, true, null, null, null, null, null, "name", null, null, null).join();
        ODataValueContextOfIListOfEntry result3 = client.getEntriesClient().getEntryListing(repositoryId, rootEntryId3, true, null, null, null, null, null, "name", null, null, null).join();
        ODataValueContextOfIListOfEntry result4 = client.getEntriesClient().getEntryListing(repositoryId, rootEntryId4, true, null, null, null, null, null, "name", null, null, null).join();
        
        // create entry lists
        List<Entry> entries1 = result1.getValue();
        List<Entry> entries2 = result2.getValue();
        List<Entry> entries3 = result3.getValue();
        List<Entry> entries4 = result4.getValue();
        
        // create list methods object
        listMethods useListMethods = new listMethods();

        // print entry data sets
        useListMethods.print(entries1, entry1);
        useListMethods.print(entries2, entry2);
        useListMethods.print(entries3, entry3);
        useListMethods.print(entries4, entry4);

        // use class to create new directory
        useListMethods.makeDirectory();
        useListMethods.changeDirectory();
        
        // download files locally
        useListMethods.downloadFiles(entries1);
        useListMethods.downloadFiles(entries2);
        useListMethods.downloadFiles(entries3);
        useListMethods.downloadFiles(entries4);
        useListMethods.clientClose();

        // disconnect from API
        client.close(); 
    }
    
}