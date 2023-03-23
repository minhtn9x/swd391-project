package com.dma.utils;

import java.io.IOException;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class UpdateFolder {
    public static final File renameGoogleFile(String fileId, String newTitle) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        File file= new File();
        file.setName(newTitle);
        
        File updatedFile = driveService.files().update(fileId, file).execute();
        
        return updatedFile;
    }
    
    public static final File removeGoogleFile(String fileId, Boolean trashed) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        File file= new File();
        file.setTrashed(trashed);
        
        File updatedFile = driveService.files().update(fileId, file).setFields("starred").execute();
        
        return updatedFile;
    }
    
    public static final File starGoogleFile(String fileId, Boolean starred) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        File file= new File();
        file.setStarred(starred);
        
        File updatedFile = driveService.files().update(fileId, file).execute();
        
        return updatedFile;
    }

    public static void main(String[] args) throws IOException {
        // Create a Root Folder
        File folder = renameGoogleFile("1yogDnapALKOLmMNjdgdWDvRlHGD4dOCU", "TEST-FOLDER");
        
        System.out.println("Updated folder with id= "+ folder.getId());
        System.out.println("                    name= "+ folder.getName());

        System.out.println("Done!");
    }
    
}