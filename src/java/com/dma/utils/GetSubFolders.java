package com.dma.utils;

import com.dma.dto.GoogleFileDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.util.Date;

public class GetSubFolders {

    // com.google.api.services.drive.model.File
    public static final List<File> getGoogleSubFolders(String googleFolderIdParent) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        String pageToken = null;
        List<File> list = new ArrayList<>();

        String query;
        if (googleFolderIdParent == null) {
            query = " mimeType = 'application/vnd.google-apps.folder' " //
                    + " and 'root' in parents "
                    + " and trashed=false";
        } else {
            query = " mimeType = 'application/vnd.google-apps.folder' " //
                    + " and '" + googleFolderIdParent + "' in parents "
                    + " and trashed=false";
        }

        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
                    // Fields will be assigned values: id, name, createdTime
                    .setFields("nextPageToken, files(id, name, createdTime, parents)")//
                    .setOrderBy("viewedByMeTime desc")
                    .setPageToken(pageToken).execute();
            for (File file : result.getFiles()) {
                list.add(file);
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        //
        return list;
    }
    
    public static final List<File> getGoogleSubFoldersStarred(String googleFolderIdParent) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        String pageToken = null;
        List<File> list = new ArrayList<>();

        String query;
        if (googleFolderIdParent == null) {
            query = " mimeType = 'application/vnd.google-apps.folder' " //
                    + " and 'root' in parents "
                    + " and starred=true "
                    + " and trashed=false";
        } else {
            query = " mimeType = 'application/vnd.google-apps.folder' " //
                    + " and '" + googleFolderIdParent + "' in parents "
                    + " and starred=true "
                    + " and trashed=false";
        }

        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
                    // Fields will be assigned values: id, name, createdTime
                    .setFields("nextPageToken, files(id, name, createdTime, parents, trashed)")//
                    .setOrderBy("viewedByMeTime desc")
                    .setPageToken(pageToken).execute();
            for (File file : result.getFiles()) {
                list.add(file);
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        //
        return list;
    }
    
    // com.google.api.services.drive.model.File
    public static final List<GoogleFileDTO> getGoogleSubFiles(String googleFolderIdParent) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();
        
        String pageToken = null;
        List<GoogleFileDTO> list = new ArrayList<>();

        String query;
        if (googleFolderIdParent == null) {
            query = " mimeType != 'application/vnd.google-apps.folder' " //
                    + " and 'root' in parents "
                    + " and trashed=false";
        } else {
            query = " mimeType != 'application/vnd.google-apps.folder' " //
                    + " and '" + googleFolderIdParent + "' in parents"
                    + " and trashed=false";
        }

        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
                    // Fields will be assigned values: id, name, createdTime
                    .setFields("nextPageToken, files(id, name, createdTime, modifiedTime, parents, thumbnailLink, size, webViewLink)")//
                    .setOrderBy("viewedByMeTime desc")
                    .setPageToken(pageToken).execute();
            for (File file : result.getFiles()) {
                GoogleFileDTO fileDTO= new GoogleFileDTO(
                        file.getId(), 
                        file.getName(), 
                        file.getWebViewLink(), 
                        new Date(file.getModifiedTime().getValue()), 
                        ConvertBytes.toReadableByte(file.getSize())
                );
                list.add(fileDTO);
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        //
        return list;
    }
    
    public static final List<File> getGoogleSubFilesStarred(String googleFolderIdParent) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();
        
        String pageToken = null;
        List<File> list = new ArrayList<>();

        String query;
        if (googleFolderIdParent == null) {
            query = " mimeType != 'application/vnd.google-apps.folder' " //
                    + " and 'root' in parents "
                    + " and starred=true";
        } else {
            query = " mimeType != 'application/vnd.google-apps.folder' " //
                    + " and '" + googleFolderIdParent + "' in parents"
                    + " and starred=true";
        }

        do {
            FileList result = driveService.files().list().setQ(query).setSpaces("drive") //
                    // Fields will be assigned values: id, name, createdTime
                    .setFields("nextPageToken, files(id, name, createdTime, modifiedTime, parents, thumbnailLink, size, webViewLink, trashed)")//
                    .setOrderBy("viewedByMeTime desc")
                    .setPageToken(pageToken).execute();
            for (File file : result.getFiles()) {
                list.add(file);
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        //
        return list;
    }

    // com.google.api.services.drive.model.File
    public static final List<File> getGoogleRootFolders() throws IOException {
        return getGoogleSubFolders(null);
    }
    
    // com.google.api.services.drive.model.File
    public static final List<GoogleFileDTO> getGoogleRootFiles() throws IOException {
        return getGoogleSubFiles(null);
    }

    public static void main(String[] args) throws IOException {
        List<File> googleRootFolders = getGoogleRootFolders();
        for (File folder : googleRootFolders) {
            System.out.println("Folder ID: " + folder.getId() + " --- Name: " + folder.getName());
        }
    }

}