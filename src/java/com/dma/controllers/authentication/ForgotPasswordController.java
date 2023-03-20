package com.dma.controllers.authentication;

import com.dma.utils.AppUtils;
import com.dma.utils.EmailUtils;
import com.dma.validation.Validation;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "ForgotPasswordController", urlPatterns = {"/ForgotPasswordController"})
public class ForgotPasswordController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ForgotPasswordController.class);
    private static final String ERROR = AppUtils.pagePrefix +"forgotPassword.jsp";
    private static final String SUCCESS = AppUtils.pagePrefix +"enterOTP.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession mySession = request.getSession();
            String email = request.getParameter("email");
            int otpValue = 0;
            if (!Validation.isValidEmail(email)) {
                request.setAttribute("MESSAGE", "Invalid Email");
            } else {
                Random rand = new Random();
                otpValue = rand.nextInt(1255650);
                Session session = EmailUtils.createEmailSession();
                EmailUtils.sendOTPEmail(session, email, "Retrieve Your Password", "Hello from DMA", otpValue);
                request.setAttribute("MESSAGE", "An OTP has been sent to your email");
                mySession.setAttribute("OTP", otpValue);
                mySession.setAttribute("EMAIL", email);
                url = SUCCESS;
            }
        } catch (UnsupportedEncodingException | MessagingException e) {
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
