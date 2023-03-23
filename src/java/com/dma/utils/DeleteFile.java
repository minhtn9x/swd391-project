package com.dma.utils;

import java.io.IOException;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class DeleteFile {
    public static final void deleteGoogleFile(String fileId) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        File file= new File();
        
        driveService.files().delete(fileId).execute();
    }
}