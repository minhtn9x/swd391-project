/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.controllers.authentication;

import com.dma.accessgoogle.GooglePojo;
import com.dma.accessgoogle.GoogleUtils;
import com.dma.dao.UserDAO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginGoogleController.class);
    private static final String ERROR = AppUtils.pagePrefix +"login.jsp";
    private static final String USER = "US";
    private static final String USER_PAGE = AppUtils.pagePrefix +"MainController?action=UserHome";
    private static final String ADMIN = "AD";
    private static final String ADMIN_PAGE = AppUtils.pagePrefix +"admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            if (code == null || ("").equals(code)) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                String email = googlePojo.getEmail();
                String isFPT = email.split("@")[1];
                UserDAO dao = new UserDAO();
                HttpSession session = request.getSession();
                if (!dao.isDeleted(email)) {
                    if (isFPT.equalsIgnoreCase("fpt.edu.vn") || isFPT.equalsIgnoreCase("fe.edu.vn")) {
                        UserDTO user = dao.checkLoginGoogle(email);
                        if (user != null) {
                            AppUtils.storeLoginedUser(session, user);
                            if (USER.equals(user.getRoleID())) {
                                url = USER_PAGE;
                                LOGGER.info("Login sucessesfully!");
                            } else if (ADMIN.equals(user.getRoleID())) {
                                url = ADMIN_PAGE;
                                LOGGER.info("Login sucessesfully!");
                            }
                        } else {
                            request.setAttribute("STATUS", "Invalid account!");
                            LOGGER.warn("Your account is not allowed to log into the system!");
                        }
                    } else {
                        request.setAttribute("STATUS", "Invalid account!");
                        LOGGER.warn("Your account is not allowed to log into the system!");
                    }
                } else {
                    request.setAttribute("DELETED_USER",
                            "Your account has been disabled. Contact us for more information!");
                    LOGGER.warn("Your account has been disabled. Contact us for more information!");
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
