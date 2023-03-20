/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.validation;

import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class Validation {

    public static boolean isValidEmail(String email) {
        Pattern p = Pattern.compile("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$");
        return p.matcher(email).find();
    }

    public static boolean isValidPhoneNumber(String phone) {
        Pattern p = Pattern.compile("^[0-9]{10}$");
        return p.matcher(phone).find();
    }

    public static boolean isValidUserID(String userID) {
        Pattern p = Pattern.compile("(?i)^(?=.*[a-z])[a-z0-9]{5,20}$");
        return p.matcher(userID).find();
    }

    public static boolean isValidPassword(String password) {
        Pattern p = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{3,}$");
        return p.matcher(password).find();
    }
}
