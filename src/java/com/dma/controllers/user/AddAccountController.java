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

@WebServlet(name = "AddAccountController", urlPatterns = {"/AddAccountController"})
public class AddAccountController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(AddAccountController.class);

    private static final String ERROR = AppUtils.pagePrefix +"newAccount.jsp";
    private static final String SUCCESS = "MainController?action=Accounts";

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
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            Date birthday = Date.valueOf(request.getParameter("birthday"));
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String confirmPassword = request.getParameter("confirmPassword");
            RegisterUserErrorDTO userInput = new RegisterUserErrorDTO(userID, fullName,
                    address, birthday, phoneNumber, email);

            if (!dao.isDeleted(userID)) {
                if (!dao.checkUserExisted(userID)) {
                    if (!Validation.isValidUserID(userID)) {
                        foundError = true;
                        userError.setUserID("User ID requires 8 characters or invalid user ID pattern");
                        LOGGER.error(userError.getUserID());
                    }
                    if (fullName.trim().length() < 2 || fullName.trim().length() > 30) {
                        foundError = true;
                        userError.setFullName("Full Name requires 2 to 30 characters");
                        LOGGER.error(userError.getFullName());
                    }
                    if (!password.equals(confirmPassword)) {
                        foundError = true;
                        userError.setConfirmPassword("Please confirm your password!");
                        LOGGER.error(userError.getConfirmPassword());
                    }
                    if (address == null || address.equals("")) {
                        foundError = true;
                        userError.setAddress("Invalid address!");
                        LOGGER.error(userError.getAddress());
                    }
                    if (birthday == null) {
                        foundError = true;
                        userError.setErrorMessage("Invalid Date of Birth");
                        LOGGER.error(userError.getBirthday());
                    }
                    if (!phoneNumber.isEmpty()) {
                        if (!Validation.isValidPhoneNumber(phoneNumber.trim())) {
                            foundError = true;
                            userError.setPhoneNumber("Phone number requires 10 numbers");
                            LOGGER.error(userError.getPhoneNumber());
                        }
                    }
                    if (!Validation.isValidEmail(email.toLowerCase().trim())) {
                        foundError = true;
                        userError.setEmail("Invalid email pattern");
                        LOGGER.error(userError.getEmail());
                    }
                    if (foundError) {
                        request.setAttribute("USER_ERROR", userError);
                        request.setAttribute("USER_INFO", userInput);
                        LOGGER.info("Registered unsuccessfully");
                    } else {
                        boolean checkRegister = dao.createUser(new UserDTO(userID, fullName, roleID, password,
                                address, birthday, phoneNumber, email, false));
                        if (checkRegister) {
                            url = SUCCESS;
                            request.setAttribute("STATUS", "success");
                            LOGGER.info("Registered successfully");
                        }
                    }
                } else {
                    request.setAttribute("DUPLICATE", "User ID has existed!");
                }
            } else {
                request.setAttribute("DELETED_USER", "This user ID had been deleted. Contact us for more infomation!");
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
