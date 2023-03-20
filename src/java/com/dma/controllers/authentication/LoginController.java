/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.controllers.authentication;

import com.dma.dao.UserDAO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoginController.class);

    private static final String LOGIN_FAIL = "login.jsp";
    private static final String ADMIN = "AD";
    private static final String ADMIN_PAGE ="MainController?action=Accounts";
    private static final String USER = "US";
    private static final String USER_PAGE = "MainController?action=UserHome";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_FAIL;
        RequestDispatcher dispatcher = null;
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        if (userID == null || ("").equals(userID)) {
            request.setAttribute("STATUS", "invalidID");
            dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        if (password == null || ("").equals(password)) {
            request.setAttribute("STATUS", "invalidPassword");
            dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
        try {
            UserDAO dao = new UserDAO();
            if (!dao.isDeleted(userID)) {
                UserDTO user = dao.checkLogin(userID, password);
                HttpSession session = request.getSession();
                if (user != null) {
                    AppUtils.storeLoginedUser(session, user);
                    if (USER.equals(user.getRoleID())) {
                        url = USER_PAGE;
                        session.setAttribute("UserID", userID);
                        LOGGER.info("Login sucessesfully!");
                    } else if (ADMIN.equals(user.getRoleID())) {
                        url = ADMIN_PAGE;
                        LOGGER.info("Login sucessesfully!");
                    }
                } else {
                    request.setAttribute("STATUS", "Incorrect UserID or Password!");
                    LOGGER.warn("Incorrect UserID or Password!");
                }
            } else {
                request.setAttribute("STATUS",
                        " Account deleted ");
                LOGGER.info("Your account has been disabled. Contact us for more information!");
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
