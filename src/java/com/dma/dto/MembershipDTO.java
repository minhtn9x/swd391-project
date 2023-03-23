/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.dto;

import com.sun.jndi.toolkit.dir.SearchFilter;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author giahu
 */
public class MembershipDTO implements Serializable{
    private static final long serialVersionUID= 1234567L;
    private int mid;
    private String userID;
    private int projectID;
    private Date startDate;
    private Date endDate;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String UserID) {
        this.userID = UserID;
    }

    public int getprojectID() {
        return projectID;
    }

    public void setprojectID(int projectID) {
        this.projectID = projectID;
    }

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

    public MembershipDTO(int mid, String userID, int projectID, Date startDate, Date endDate) {
        this.mid = mid;
        this.userID = userID;
        this.projectID = projectID;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public MembershipDTO(){
        
    }

}

