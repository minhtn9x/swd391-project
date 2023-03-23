package com.dma.dto;

import java.util.Date;

public class FileSharedDTO {
    private String id;
    private String name;
    private String owner;
    private String webViewLink;
    private Date modifiedTime;
    private Date viewedByMeTime;
    private String size;

    public FileSharedDTO() {
    }

    public FileSharedDTO(String id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }
    
    public FileSharedDTO(String id, String name, String owner, String webViewLink, Date modifiedTime, Date viewedByMeTime, String size) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.webViewLink = webViewLink;
        this.modifiedTime = modifiedTime;
        this.viewedByMeTime = viewedByMeTime;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWebViewLink() {
        return webViewLink;
    }

    public void setWebViewLink(String webViewLink) {
        this.webViewLink = webViewLink;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Date getViewedByMeTime() {
        return viewedByMeTime;
    }

    public void setViewedByMeTime(Date viewedByMeTime) {
        this.viewedByMeTime = viewedByMeTime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
