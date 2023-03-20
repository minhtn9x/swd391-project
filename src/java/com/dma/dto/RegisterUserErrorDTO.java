/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.dto;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class RegisterUserErrorDTO {

    private String userID;
    private String fullName;
    private String password;
    private String address;
    private Date birthday;
    private String phoneNumber;
    private String email;
    private String confirmPassword;
    private String errorMessage;

    public RegisterUserErrorDTO() {
    }

    public RegisterUserErrorDTO(String userID, String fullName, String password, String address, Date birthday, String phoneNumber, String email, String confirmPassword, String errorMessage) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.confirmPassword = confirmPassword;
        this.errorMessage = errorMessage;
    }

    public RegisterUserErrorDTO(String userID, String fullName, String address, Date birthday, String phoneNumber, String email) {
        this.userID = userID;
        this.fullName = fullName;
        this.address = address;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
