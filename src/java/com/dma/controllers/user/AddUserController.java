/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.controllers.user;

import com.dma.dao.UserDAO;
import com.dma.dto.RegisterUserErrorDTO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import com.dma.validation.Validation;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddUserController", urlPatterns = {"/AddUserController"})
public class AddUserController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(AddUserController.class);

    private static final String ERROR = AppUtils.pagePrefix +"registration.jsp";
    private static final String SUCCESS = AppUtils.pagePrefix +"registration.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        RegisterUserErrorDTO userError = new RegisterUserErrorDTO();
        boolean foundError = false;
        try {
            UserDAO dao = new UserDAO();
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String inputDate = request.getParameter("birthday");          
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String confirmPassword = request.getParameter("confirmPassword");
            if (userID.isEmpty() && fullName.isEmpty()
                    && password.isEmpty() && inputDate.isEmpty() && phoneNumber.isEmpty()
                    && email.isEmpty() && confirmPassword.isEmpty()) {
                request.setAttribute("ERROR_MESSAGE", "Please fill in this form");
            } else {
                Date birthday = Date.valueOf(inputDate);
                RegisterUserErrorDTO inputUserInfo = new RegisterUserErrorDTO(userID, fullName,
                        address, birthday, phoneNumber, email);
                if (!dao.isDeleted(userID)) {
                    if (!dao.checkUserExisted(userID)) {
                        if (!Validation.isValidUserID(userID)) {
                            foundError = true;
                            userError.setUserID("5 - 20 letters. At least one uppercase letter, four numbers");
                            LOGGER.error("User ID: " + userError.getUserID());
                        }
                        if (fullName.trim().length() < 2 || fullName.trim().length() > 30) {
                            foundError = true;
                            userError.setFullName("Full Name requires 2 to 30 characters");
                            LOGGER.error("Full Name: " + userError.getFullName());
                        }
                        if (!Validation.isValidPassword(password)) {
                            foundError = true;
                            userError.setPassword("Minimum three characters. At least one uppercase letter, one lowercase letter and one number");
                            LOGGER.error("Password: " + userError.getPassword());
                        }
                        if (!password.equals(confirmPassword)) {
                            foundError = true;
                            userError.setConfirmPassword("Please confirm your password!");
                            LOGGER.error("Confirm password: " + userError.getConfirmPassword());
                        }
                        if (birthday == null) {
                            foundError = true;
                            userError.setErrorMessage("Invalid Date of Birth");
                            LOGGER.error("Date of Birth: " + userError.getBirthday());
                        }
                        if (!phoneNumber.isEmpty()) {
                            if (!Validation.isValidPhoneNumber(phoneNumber.trim())) {
                                foundError = true;
                                userError.setPhoneNumber("Phone number requires 10 numbers");
                                LOGGER.error("Phone Number: " + userError.getPhoneNumber());
                            }
                        }
                        if (!Validation.isValidEmail(email.toLowerCase().trim())) {
                            foundError = true;
                            userError.setEmail("Invalid email pattern");
                            LOGGER.error("Email: " + userError.getEmail());
                        } else if (dao.checkEmailExisted(email)) {
                            foundError = true;
                            userError.setEmail("This email has been registered");
                            LOGGER.error("Email: " + userError.getEmail());
                        }
                        if (foundError) {
                            request.setAttribute("USER_ERROR", userError);
                            request.setAttribute("USER_INFO", inputUserInfo);
                            LOGGER.info("Registered unsuccessfully");
                        } else {
                            boolean checkRegister = dao.createUser(new UserDTO(userID, fullName, "US", password,
                                    address, birthday, phoneNumber, email, false));
                            if (checkRegister) {
                                url = SUCCESS;
                                request.setAttribute("STATUS", "success");
                                LOGGER.info("Registered successfully");
                            }
                        }
                    } else {
                        request.setAttribute("DUPLICATE", "User ID has existed!");
                        request.setAttribute("USER_INFO", inputUserInfo);
                    }
                } else {
                    request.setAttribute("DELETED_USER", "This user ID had been deleted. Contact us for more infomation!");
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
