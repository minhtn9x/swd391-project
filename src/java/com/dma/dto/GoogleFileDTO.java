package com.dma.dto;

import java.util.Date;

public class GoogleFileDTO {
    private String id;
    private String name;
    private String webViewLink;
    private Date modifiedTime;
    private Date viewedByMeTime;
    private String size;

    public GoogleFileDTO() {
    }

    public GoogleFileDTO(String id, String name, String webViewLink, Date modifiedTime, Date viewedByMeTime, String size) {
        this.id = id;
        this.name = name;
        this.webViewLink = webViewLink;
        this.modifiedTime = modifiedTime;
        this.viewedByMeTime = viewedByMeTime;
        this.size = size;
    }
    
    public GoogleFileDTO(String id, String name, String webViewLink, Date modifiedTime, String size) {
        this.id = id;
        this.name = name;
        this.webViewLink = webViewLink;
        this.modifiedTime = modifiedTime;
        this.size = size;
    }
    
    public GoogleFileDTO(String id, String name) {
        this.id = id;
        this.name = name;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getViewedByMeTime() {
        return viewedByMeTime;
    }

    public void setViewedByMeTime(Date viewedByMeTime) {
        this.viewedByMeTime = viewedByMeTime;
    }
}
