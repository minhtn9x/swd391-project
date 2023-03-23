package com.dma.utils;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.DriveScopes;
//import com.google.auth.http.HttpCredentialsAdapter;
//import com.google.auth.oauth2.GoogleCredentials;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
//import java.util.Arrays;

/* Class to demonstrate use-case of drive's download file. */
public class DownloadFile {

    /**
     * Download a Document file in PDF format.
     * @param realFileId file ID of any workspace document format file.
     * @return byte array stream if successful, {@code null} otherwise.
     * @throws IOException if service account credentials file not found.
     */
    public static ByteArrayOutputStream downloadFile(String realFileId) throws IOException{
        Drive driveService = GoogleDriveUtils.getDriveService();

        try {
            OutputStream outputStream = new ByteArrayOutputStream();

            driveService.files().get(realFileId)
                    .executeMediaAndDownloadTo(outputStream);

            return (ByteArrayOutputStream) outputStream;
        }catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            System.err.println("Unable to move file: " + e.getDetails());
            throw e;
        }
    }
}