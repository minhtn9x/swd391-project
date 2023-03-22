/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class ProjectDTO implements Serializable {
    private static final long serialVersionUID= -12345678L;
    
    private int projectID;
    private String managerID;
    private String projectName;
    private String projectDesc;
    private Date createdDate;
    private boolean isDeleted;
    private String UserID;
    private Date startDate;
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public ProjectDTO(int projectID, String managerID, String projectName, String projectDesc, Date createdDate, boolean isDeleted, String UserID, Date startDate, Date endDate) {
        this.projectID = projectID;
        this.managerID = managerID;
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.createdDate = createdDate;
        this.isDeleted = isDeleted;
        this.UserID = UserID;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public ProjectDTO(){
        
    }
    
    
}

    