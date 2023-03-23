package com.dma.dto;

import java.util.Date;

public class FeedbackDTO {

    private String feedbackID;
    private String detail;
    private String subject;
    private Date createdDate;
    private String userID;
    private String option;

    public FeedbackDTO(String feedbackID, String detail, String subject, Date createdDate, String userID, String option){
        this.feedbackID = feedbackID;
        this.detail = detail;
        this.subject = subject;
        this.createdDate = createdDate;
        this.userID = userID;
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public FeedbackDTO() {
    }
}
