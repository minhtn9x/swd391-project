package com.dma.utils;

import com.dma.dto.FileSharedDTO;
import com.dma.dto.GoogleFileDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import java.util.Date;

public class GetFiles {

    // com.google.api.services.drive.model.File
    public static final List<GoogleFileDTO> getGoogleFolders(List<String> googleFileIds) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        List<GoogleFileDTO> list = new ArrayList<>();

        for (String googleFileId : googleFileIds) {
            File file = driveService.files().get(googleFileId).setFields("id, name, createdTime, modifiedTime, parents, thumbnailLink, size, webViewLink, trashed, starred").execute();
            GoogleFileDTO fileDTO = new GoogleFileDTO(
                    file.getId(),
//                    file.getName().length()>7? file.getName().substring(0, 7)+"...":file.getName()
                    file.getName()
            );
            list.add(fileDTO);
        }

        return list;
    }
    
    public static final List<FileSharedDTO> getGoogleFoldersShared(List<String> googleFileIds) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        List<FileSharedDTO> list = new ArrayList<>();

        for (String googleFileId : googleFileIds) {
            File file = driveService.files().get(googleFileId).setFields("id, name, createdTime, modifiedTime, parents, thumbnailLink, size, webViewLink, trashed, starred").execute();
            FileSharedDTO fileDTO = new FileSharedDTO(
                    file.getId(),
//                    file.getName().length()>7? file.getName().substring(0, 7)+"...":file.getName()
                    file.getName(),
                    ""
            );
            list.add(fileDTO);
        }

        return list;
    }

    public static final List<GoogleFileDTO> getGoogleFiles(List<String> googleFileIds) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        List<GoogleFileDTO> list = new ArrayList<>();

        for (String googleFileId : googleFileIds) {
            File file = driveService.files().get(googleFileId).setFields("id, name, createdTime, modifiedTime, parents, thumbnailLink, size, webViewLink, trashed, starred, viewedByMeTime").execute();
            GoogleFileDTO fileDTO = new GoogleFileDTO(
                    file.getId(),
                    file.getName(),
                    file.getWebViewLink(),
                    new Date(file.getModifiedTime().getValue()),
                    file.getViewedByMeTime()==null?null:new Date(file.getViewedByMeTime().getValue()),
                    ConvertBytes.toReadableByte(file.getSize())
            );
            list.add(fileDTO);
        }

        return list;
    }
    
    public static final List<FileSharedDTO> getGoogleFilesShared(List<String> googleFileIds) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        List<FileSharedDTO> list = new ArrayList<>();

        for (String googleFileId : googleFileIds) {
            File file = driveService.files().get(googleFileId).setFields("id, name, createdTime, modifiedTime, parents, thumbnailLink, size, webViewLink, trashed, starred, viewedByMeTime").execute();
            FileSharedDTO fileDTO = new FileSharedDTO(
                    file.getId(),
                    file.getName(),
                    "",
                    file.getWebViewLink(),
                    new Date(file.getModifiedTime().getValue()),
                    file.getViewedByMeTime()==null?null:new Date(file.getViewedByMeTime().getValue()),
                    ConvertBytes.toReadableByte(file.getSize())
            );
            list.add(fileDTO);
        }

        return list;
    }

    public static final GoogleFileDTO getGoogleFile(String googleFileId) throws IOException {
        Drive driveService = GoogleDriveUtils.getDriveService();

        File file = driveService.files().get(googleFileId).setFields("id, name, createdTime, modifiedTime, parents, thumbnailLink, size, webViewLink, trashed, starred").execute();
        GoogleFileDTO fileDTO = new GoogleFileDTO(
                file.getId(),
                file.getName(),
                file.getWebViewLink(),
                new Date(file.getModifiedTime().getValue()),
                ConvertBytes.toReadableByte(file.getSize())
        );

        return fileDTO;
    }

    public static void main(String[] args) throws IOException {

    }

}
