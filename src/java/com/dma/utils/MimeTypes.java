package com.dma.utils;

import java.util.HashMap;
import java.util.Map;

public class MimeTypes {
    private Map<String, String> mimeTypes;

    public MimeTypes() {
        mimeTypes= new HashMap<>();
        mimeTypes.put("folder", "application/vnd.google-apps.folder");
        
        mimeTypes.put("doc", "application/msword");
        mimeTypes.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        mimeTypes.put("xls", "application/vnd.ms-excel");
        mimeTypes.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        mimeTypes.put("ppt", "application/vnd.ms-powerpoint");
        mimeTypes.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        mimeTypes.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
        mimeTypes.put("pdf", "application/pdf");
        mimeTypes.put("php", "application/x-httpd-php");
        mimeTypes.put("swf", "application/x-shockwave-flash");
        mimeTypes.put("zip", "application/zip");
        mimeTypes.put("rar", "application/rar");
        mimeTypes.put("tar", "application/tar");
        mimeTypes.put("arj", "application/arj");
        mimeTypes.put("cab", "application/cab");
        
        mimeTypes.put("mp3", "audio/mpeg");
        
        mimeTypes.put("jpg", "image/jpeg");
        mimeTypes.put("jfif", "image/pjpeg");
        mimeTypes.put("png", "image/png");
        mimeTypes.put("gif", "image/gif");
        mimeTypes.put("bmp", "image/bmp");
        
        mimeTypes.put("xml", "text/xml");
        mimeTypes.put("csv", "text/csv");
        mimeTypes.put("tmpl", "text/plain");
        mimeTypes.put("txt", "text/plain");
        mimeTypes.put("js", "text/js");
        mimeTypes.put("html", "text/html");
        mimeTypes.put("htm", "text/html");
    }

    public Map<String, String> getMimeTypes() {
        return mimeTypes;
    }
}
