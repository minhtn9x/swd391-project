/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.controllers.authentication;

import com.dma.dao.UserDAO;
import com.dma.utils.AppUtils;
import com.dma.validation.Validation;
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
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/ResetPasswordController"})
public class ResetPasswordController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ResetPasswordController.class);
    private static final String ERROR = AppUtils.pagePrefix +"newPassword.jsp";
    private static final String SUCCESS = AppUtils.pagePrefix +"login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String newPassword = request.getParameter("newPassword");
            String confPassword = request.getParameter("confPassword");
            if (!Validation.isValidPassword(newPassword)) {
                request.setAttribute("MESSAGE", "Minimum three character. At least one uppercase letter, one lowercase letter, and one number. ");
                LOGGER.warn("Invalid Password!");
            } else if (!confPassword.equals(newPassword)) {
                request.setAttribute("MESSAGE", "Invalid Confirm Password");
                LOGGER.warn("Invalid Confirm Password!");
            } else {
                UserDAO dao = new UserDAO();
                boolean checkReset = dao.resetPassword(newPassword, (String) session.getAttribute("EMAIL"));
                if (checkReset) {
                    request.setAttribute("STATUS", "resetSuccess");
                    url = SUCCESS;
                    LOGGER.info("Reset successfilly!");
                } else {
                    request.setAttribute("STATUS", "resetFailed");
                    LOGGER.warn("Reset unsuccessfullty!");
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
