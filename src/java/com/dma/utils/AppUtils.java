/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.utils;

import com.dma.dto.UserDTO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AppUtils {
    public static final String pagePrefix= "/WEB-INF/views/";
    public static void storeLoginedUser(HttpSession session, UserDTO loginedUser) {
        session.setAttribute("LOGIN_USER", loginedUser);
    }

    public static UserDTO getLoginedUser(HttpSession session) {
        UserDTO loginedUser = (UserDTO) session.getAttribute("LOGIN_USER");
        return loginedUser;
    }
}
