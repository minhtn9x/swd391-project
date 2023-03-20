/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.controllers.authentication;

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
@WebServlet(name = "ValidateOTPController", urlPatterns = {"/ValidateOTPController"})
public class ValidateOTPController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ValidateOTPController.class);
    private static final String ERROR = AppUtils.pagePrefix +"enterOTP.jsp";
    private static final String SUCCESS = AppUtils.pagePrefix +"newPassword.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            int inputOTP = Integer.parseInt(request.getParameter("inputOTP"));
            int otpValue = (int) session.getAttribute("OTP");
            if (inputOTP == otpValue) {
//                request.setAttribute("STATUS", "success");
                url = SUCCESS;
                LOGGER.info("Valid OTP");
            } else {
                request.setAttribute("MESSAGE", "Wrong OTP");
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
