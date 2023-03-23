package com.dma.utils;

import static com.dma.utils.CreateGoogleFile.createGoogleFile;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UploadFileHelper {
    public static List<File> uploadFile(String googleFolderIdParent, Map<String, InputStream> filesMap) {
        List<File> files = new ArrayList<>();
        for (Map.Entry<String, InputStream> entry : filesMap.entrySet()) {
            String fileName = entry.getKey();
            
            if (fileName != null) {
                try {
                    String extension = getFileExtension(fileName).get();
                    MimeTypes mimeTypes = new MimeTypes();
                    String mimeTypesStr = mimeTypes.getMimeTypes().get(extension);
                    File file = createGoogleFile(googleFolderIdParent, mimeTypesStr, fileName, entry.getValue());
                    files.add(file);
                } catch (IOException ex) {
                    fileName = null;
                }
            }
        }
        return files;
    }

//    private static String getFileName(Part part) {
//        for (String content : part.getHeader("content-disposition").split(";")) {
//            if (content.trim().startsWith("filename")) {
//                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
//            }
//        }
//        return null;
//    }
    
    private static Optional<String> getFileExtension(String filename) {
        Optional<String> extension= Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
        return extension;
    }

}
